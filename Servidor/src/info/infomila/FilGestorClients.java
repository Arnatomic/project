/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila;

import com.mysql.jdbc.Blob;
import info.infomila.model.Cita;
import info.infomila.model.EntradaInforme;
import info.infomila.model.InformePericial;
import info.infomila.model.Perit;
import info.infomila.model.Sinistre;
import info.infomila.model.Trucada;
import infomila.info.peritapp.model.EntradaInformeApp;
import infomila.info.peritapp.model.InformePericialApp;
import infomila.info.peritapp.model.PolissaApp;
import infomila.info.peritapp.model.ResumSinistreApp;
import infomila.info.peritapp.model.SinistreApp;
import infomila.info.peritapp.model.TrucadaApp;
import infomila.info.peritapp.model.enums.ESTAT_INFORME;
import infomila.info.peritapp.model.enums.ESTAT_SINISTRE;
import infomila.info.peritapp.model.enums.RESULTAT_PERITATGE;
import infomila.info.peritapp.model.enums.TIPUS_HABITATGE;
import infomila.info.peritapp.model.enums.TIPUS_SINISTRE;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author Mr. Robot
 */
public class FilGestorClients implements Runnable {

    private Socket socketClient;
    private IComponentSGBD dbConnection;
    private DataInputStream dis;
    private DataOutputStream dos;
    private static long codiPerit;

    FilGestorClients(Socket socketClient, IComponentSGBD dbConnection) {
        this.socketClient = socketClient;
        this.dbConnection = dbConnection;

        try {
            this.dis = new DataInputStream(socketClient.getInputStream());
            this.dos = new DataOutputStream(socketClient.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        int request = 0;
        try {
            //dos.flush();            
            request = dis.readInt();
            
            System.out.println("REQUEST: " +request);

            switch (request) {

                case 1:

                    String user = dis.readUTF();
                    String password = dis.readUTF();

                    try {
                        codiPerit = dbConnection.login(user, password);

                        System.out.println("CodiRebut: " + codiPerit);
                        dos.writeLong(codiPerit);
                    } catch (IComponentSGBDException ex) {
                        dos.writeLong(-1);
                    }

                    break;

                case 2:
                    try {
                        List<ResumSinistreApp> cites = new ArrayList<>();
                        List<Sinistre> sinistres = dbConnection.getLlistatSinistres(codiPerit);
                        Perit p = null;
                        for (Sinistre s : sinistres) {
                            p = s.getPerit();
                            break;
                        }
                        Iterator<Cita> itCites = p.getCites();
                        int i = 1;
                        while (itCites.hasNext()) {
                            Cita c = (Cita) itCites.next();
                            if (c != null) {
                                ESTAT_INFORME ei;
                                if (c.getSinistre().getInforme() == null) {
                                    ei = ESTAT_INFORME.INEXISTENT;
                                } else {
                                    ei = ESTAT_INFORME.getEstatInformeFromString(c.getSinistre().getInforme().getEstatInforme().toString());
                                }

                                cites.add(new ResumSinistreApp(i, p.getNumero(), c.getSinistre().getNumero(), c.getDiaHora(), c.getSinistre().getPolissa().getPoblacio(),
                                        c.getSinistre().getTipusSinistre().toString(), ei));
                            } else {
                                System.out.println("Null saltat.");
                            }
                        }
                        // int id, int numPetit, int sinistreId, Date diaHora, String adreca, String tipusSinistre, ESTAT_INFORME estatInforme)
                        dos.writeInt(cites.size());
                        System.out.println("He enviat: " + cites.size());
                        ObjectOutputStream os = new ObjectOutputStream(socketClient.getOutputStream());
                        for (ResumSinistreApp resum : cites) {
                            os.writeObject(resum);
                            System.out.println("Envio Cita: " + resum);
                        }
                    } catch (IComponentSGBDException ex) {
                        Logger.getLogger(FilGestorClients.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 3:

                    int idInforme = dis.readInt();

                    dbConnection.updateInforme(idInforme);

                    break;
                case 4:

                    int idSinistre = dis.readInt();
                    System.out.println("ID REBUT: " + idSinistre);
                    Sinistre s = null;
                    try {
                        s = dbConnection.getInfoSinistre(codiPerit, idSinistre);
                        if (s != null) {
                            SinistreApp sinistre = new SinistreApp();
                            sinistre.setNumero(idSinistre);
                            sinistre.setDataAssignacio(s.getDataAssignacio());
                            sinistre.setDataObertura(s.getDataObertura());
                            sinistre.setDataTancament(s.getDataTancament());
                            sinistre.setDescripcio(s.getDescripcio());
                            sinistre.setEstatSinistre(ESTAT_SINISTRE.getEstatSinistreFromString(s.getEstatSinistre().toString()));
                            sinistre.setTipusSinistre(TIPUS_SINISTRE.getTipusFromString(s.getTipusSinistre().toString()));

                            PolissaApp polissa = new PolissaApp(s.getPolissa().getNumero());
                            polissa.setPoblacio(s.getPolissa().getPoblacio());
                            polissa.setLiniaAdreca(s.getPolissa().getLiniaAdreca());
                            polissa.setDataInici(s.getPolissa().getDataInici());
                            polissa.setDataFi(s.getPolissa().getDataFi());
                            polissa.setImportPolissa(s.getPolissa().getImportPolissa());

                            String nomClient = s.getPolissa().getClient().getPersona().getNom() + " "
                                    + s.getPolissa().getClient().getPersona().getCognom1();
                            polissa.setClient(nomClient);
                            polissa.setTipusHabitatge(TIPUS_HABITATGE.getTipusHabitatgeFromString(s.getPolissa().getTipusHabitatge().toString()));

                            sinistre.setPolissaApp(polissa);
                            List<TrucadaApp> trucadesSinistre = new ArrayList<>();

                            Iterator<Trucada> trucades = s.getTrucades();
                            while (trucades.hasNext()) {
                                Trucada t = trucades.next();
                                if (t != null) {
                                    trucadesSinistre.add(new TrucadaApp(t.getDataHora(), t.getDescripcio(), t.getPersonaContacte()));
                                }
                            }

                            sinistre.setTrucades(trucadesSinistre);

                            socketClient.getOutputStream().flush();
                            ObjectOutputStream os = new ObjectOutputStream(socketClient.getOutputStream());
                            
                            os.writeObject(sinistre);

                        }

                        System.out.println("Sinistre: " + s);
                    } catch (IComponentSGBDException ex) {
                    }
                    break;
                case 5:

                    int informeId = dis.readInt();

                     {
                        try {
                            InformePericial i = dbConnection.getInformePericial(codiPerit, informeId);
                            System.out.println("*********INFOIRME: " + i);
                            InformePericialApp informe = new InformePericialApp("");
                            if (i != null) {
                                informe.setDataEmisio(i.getDataEmisio());
                                informe.setImportCobert(i.getImportCobert());
                                informe.setInforme(i.getInforme());
                                informe.setResultatPeritatge(RESULTAT_PERITATGE.getResultatPeritatgeFromString(i.getResultatPeritatge().toString()));
                                informe.setEstatInforme(ESTAT_INFORME.getEstatInformeFromString(i.getEstatInforme().toString()));

                                Iterator<EntradaInforme> itEntrades = i.getEntrades();
                                List<EntradaInformeApp> entrades = new ArrayList<>();

                                while (itEntrades.hasNext()) {
                                    EntradaInforme e = itEntrades.next();
                                    if (e != null) {
                                        EntradaInformeApp entradaInforme = new EntradaInformeApp(e.getData(), e.getDescripcio(), e.getFoto(), e.isPostReparacio());
                                        entradaInforme.setNumero(informeId);
                                        entrades.add(entradaInforme);
                                        
                                    }
                                }

                                informe.setEntrades(entrades);

                                ObjectOutputStream os = new ObjectOutputStream(socketClient.getOutputStream());

                                os.writeObject(informe);
                                
                                dos.writeInt(1);

                            }
                        } catch (IComponentSGBDException ex) {
                            dos.writeInt(-1);
                        }
                    }

                    break;

                case 6:

                    int numSinistre = dis.readInt();
                    int numEntrada = dis.readInt();

                    int res = -1;
                    try {
                        res = dbConnection.eliminarEntradaInforme(codiPerit, numSinistre, numEntrada);
                        dos.writeInt(res);
                    } catch (IComponentSGBDException ex) {
                        dos.writeInt(res);
                    }

                    break;
                    
                case 7:
                    
                    int sinId = Integer.valueOf(dis.readUTF());
                    String resultat = dis.readUTF();
                    String desc = dis.readUTF();
                    BigDecimal importCobert = new BigDecimal(dis.readUTF());
                    
                    dos.writeInt(dbConnection.desarEntradaInforme(codiPerit, sinId,resultat,desc,importCobert));
                    
                    break;
                    
                case 8:
                    
                    ObjectInputStream is = new ObjectInputStream(socketClient.getInputStream());
                    int result = 1;
                    
                try {
                    EntradaInformeApp e = (EntradaInformeApp) is.readObject();
                    System.out.println("EN: " +e);
                    
                    EntradaInforme inf = new EntradaInforme(e.getData(), e.getDescripcio(), e.isPostReparacio());
                    inf.setNumero(e.getNumero());
                try {
                    result = dbConnection.desarEntradaInforme(codiPerit, inf);
                } catch (IComponentSGBDException ex) {
                    result = -1;
                }
                } catch (ClassNotFoundException ex) {
                    result = -1;
                }
            
                dos.writeInt(result);
                    
                    break;
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

}
