/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila;

import info.infomila.enums.ESTAT_INFORME;
import info.infomila.enums.RESULTAT_PERITATGE;
import info.infomila.gestio.UserOnline;
import info.infomila.model.Client;
import info.infomila.model.EntradaInforme;
import info.infomila.model.InformePericial;
import info.infomila.model.Perit;
import info.infomila.model.Polissa;
import info.infomila.model.Sinistre;
import info.infomila.model.Trucada;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;

/**
 *
 * @author Mr. Robot
 */
public class ConectorBD implements IComponentSGBD {

    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    private EntityManager em;
    private int idPerit;
    private EntityManagerFactory emf;
    private Query q;

    public ConectorBD() throws IComponentSGBDException {
        this("propietatsUnitatPersistencia.txt");
    }

    public ConectorBD(String nomFitxerPropietats) throws IComponentSGBDException {
        Properties props = new Properties();
        try {
            props.load(new FileReader(nomFitxerPropietats));
        } catch (FileNotFoundException ex) {
            throw new IComponentSGBDException("No es troba fitxer de propietats", ex);
        } catch (IOException ex) {
            throw new IComponentSGBDException("Error en carregar fitxer de propietats", ex);
        }
        String up = props.getProperty("up");
        if (up == null) {
            throw new IComponentSGBDException("Manca la propietat up en el fitxer de propietats");
        }
        try {
            emf = Persistence.createEntityManagerFactory(up);
        } catch (Exception ex) {
            throw new IComponentSGBDException("Problemes en crear EntityManagerFactory.", ex);
        }
        try {
            em = emf.createEntityManager();
        } catch (Exception ex) {
            throw new IComponentSGBDException("Problemes en crear EntityManager", ex);
        }

        System.out.println("Entity manager Creat");
    }

    @Override
    public long login(String user, String password) throws IComponentSGBDException {
        q = em.createNamedQuery("Perit.login", Long.class);
        q.setParameter(1, user);
        q.setParameter(2, password);

        long res = (long) q.getSingleResult();

        if (res == 1) {
            q = em.createNamedQuery("Perit.getPeritPerLogin", Integer.class);
            q.setParameter(1, user);
            idPerit = (int) q.getSingleResult();

            List<Long> connectionCodes = getIdsSessio();

            UserOnline usuariEntrant = new UserOnline(idPerit, user, connectionCodes);

            try {
                UserOnline usuari = em.find(UserOnline.class, user);

                if (usuari != null) {
                    usuari.setConnectonCode(usuariEntrant.getConnectionCode());
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                } else {
                    em.persist(usuariEntrant);
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                }

            } catch (Exception e) {

            }

            return usuariEntrant.getConnectionCode();
        }
        throw new IComponentSGBDException("Usuari invàlid, usuari o contrassenya errònis.");
    }

    @Override
    public List<Sinistre> getLlistatSinistres(long idSessio) throws IComponentSGBDException {

        System.out.println("IDSESSIO: " + idSessio);
        if (validaIdSessio(idSessio)) {
            q = em.createNamedQuery("Sinistre.getSinistrePeriNum", Sinistre.class);
            q.setParameter(1, idPerit);
            return q.getResultList();

        }
        throw new IComponentSGBDException("Identificador de Sessio invàlid, entrada no desitjada");

    }

    @Override
    public Sinistre getInfoSinistre(long idSessio, int numSinistre) throws IComponentSGBDException {
        if (validaIdSessio(idSessio)) {
            q = em.createNamedQuery("Sinistre.getSinistrePerId", Sinistre.class);
            q.setParameter(1, numSinistre);

            return (Sinistre) q.getSingleResult();
        }
        throw new IComponentSGBDException("Identificador de Sessio invàlid, entrada no desitjada");
    }

    @Override
    public InformePericial getInformePericial(long idSessio, int numSinistre) throws IComponentSGBDException {

        if (validaIdSessio(idSessio)) {
            q = em.createNamedQuery("InformePericial.getInformePericial", InformePericial.class);
            q.setParameter(1, numSinistre);

            return (InformePericial) q.getSingleResult();
        }
        throw new IComponentSGBDException("Identificador de Sessio invàlid, entrada no desitjada");
    }

    @Override
    public Byte[] getFoto(long idSessio, int numSinistre, int numEntrada) throws IComponentSGBDException {
        if (validaIdSessio(idSessio)) {

        }
        return null;
    }

    @Override
    public int desarInforme(long idSessio, InformePericial informe) throws IComponentSGBDException {

        if (validaIdSessio(idSessio)) {

            em.find(InformePericial.class, informe.getNumero());

            if (em == null) {
                em.getTransaction().begin();
                em.persist(informe);
                em.getTransaction().commit();
                return 0;
            }
            System.out.println("Informe ja existent");
            return -1;

        }
        throw new IComponentSGBDException("Identificador de Sessio invàlid, entrada no desitjada");
    }

    @Override
    public int desarEntradaInforme(long idSessio, EntradaInforme entrada) throws IComponentSGBDException {
        
        
        InformePericial i = em.find(InformePericial.class, entrada.getNumero());
            
            i.addEntrada(entrada);
            
            em.persist(i);
            em.getTransaction().begin();
            em.getTransaction().commit();
            
            return 1;
    }

    @Override
    public int eliminarEntradaInforme(long idSessio, int numSinistre, int numEntrada) throws IComponentSGBDException {
        if (validaIdSessio(idSessio)) {
            System.out.println("SINISTRE " +numSinistre);
             InformePericial  i = em.find(InformePericial.class, numSinistre);
            
            Iterator<EntradaInforme> itEntrades = i.getEntrades();
            List<EntradaInforme> entrades = new ArrayList<>();

            while (itEntrades.hasNext()) {
                EntradaInforme e = itEntrades.next();
                if (e != null) {
                    entrades.add(e);
                }
            }
            
            i.removeEntrada(entrades.get(numEntrada));
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
            return 1;
        }
        return -1;
    }

    @Override
    public void tancarConexio(long idSessio) {
        if (em != null) {
            UserOnline usuari = em.find(UserOnline.class, idSessio);
            em.getTransaction().begin();
            em.remove(usuari);
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public int commit() {
        try {
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int rollback() {
        try {
            em.getTransaction().rollback();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

//   public List<Sinistre> getLlistaSinistresPerClient(int numClient) throws IComponentSGBDException {
//       if(numClient >0){
//       q = em.createNamedQuery("Polissa.getPolissaPerNumClient",Polissa.class);
//       q.setParameter(1, numClient);
//       return q.getResultList();
//       }
//       throw new IComponentSGBDException("Identificador invàlid, num client estrictament posisiu");
//   }
    //------- UTILS ---------
    public List<Long> getIdsSessio() {
        q = em.createNamedQuery("UserOnline.getConnectionCodes", Long.class);
        return q.getResultList();
    }

    public boolean validaIdSessio(long idSessio) {
        q = em.createNamedQuery("UserOnline.validaConnectionCode", Long.class);
        q.setParameter(1, idSessio);

        return (long) q.getSingleResult() == 1;
    }

    @Override
    public List<Client> getLlistatClients() {
        q = em.createNamedQuery("Client.getLlistaClients", Client.class);
        return q.getResultList();
    }

    @Override
    public List<Client> getClientPerDni(String dni) throws IComponentSGBDException {
        q = em.createNamedQuery("Client.getClientPerDni");
        q.setParameter(1, "%" + dni + "%");
        return q.getResultList();
    }

    @Override
    public List<Client> getClientPerNomCognoms(String nom, String cognom1, String cognom2) throws IComponentSGBDException {
        q = em.createNamedQuery("Client.getClientPerNomCognoms", Client.class);
        if (nom == null) {
            nom = "";
        } else {
            nom = "%" + nom + "%";
        }
        if (cognom1 == null) {
            cognom1 = "";
        } else {
            cognom1 = "%" + cognom1 + "%";
        }
        if (cognom2 == null) {
            cognom2 = "";
        } else {
            cognom2 = "%" + cognom2 + "%";
        }
        q.setParameter(1, nom);
        q.setParameter(2, cognom1);
        q.setParameter(3, cognom2);
        return q.getResultList();
    }

    @Override
    public List<Client> getClientPerDataNaix(java.sql.Date dataNaix) throws IComponentSGBDException {

        System.out.println("MEtode: " + dataNaix);

        q = em.createNamedQuery("Client.getClientPerDataNaix", Client.class);
        q.setParameter(1, dataNaix);
        return q.getResultList();
    }

    @Override
    public List<Client> getClientFiltrat(String dni, String nom, String cognom1, String cognom2, java.sql.Date dataNaix) throws IComponentSGBDException {
        q = em.createNamedQuery("Client.fitreTotal", Client.class);
        q.setParameter(1, "%" + dni + "%");
        q.setParameter(2, "%" + nom + "%");
        q.setParameter(3, "%" + cognom1 + "%");
        q.setParameter(4, "%" + cognom2 + "%");
        q.setParameter(5, dataNaix);

        return q.getResultList();
    }

    @Override
    public List<Polissa> getLlistaPolices() {
        q = em.createNamedQuery("Polissa.getLlistaPolices", Polissa.class);
        return q.getResultList();
    }

    @Override
    public List<Polissa> getLlistaPolicesPerNumClient(int numClient) throws IComponentSGBDException {
        if (numClient > 0) {
            q = em.createNamedQuery("Polissa.getPolissaPerNumClient", Polissa.class);
            q.setParameter(1, numClient);
            return q.getResultList();
        }
        throw new IComponentSGBDException("Identificador invàlid, num client estrictament posisiu");
    }

    public Polissa getPolissaPerId(int idPolissa) throws IComponentSGBDException {
        q = em.createNamedQuery("Polissa.getPolissaPerId", Polissa.class);
        q.setParameter(1, idPolissa);
        return (Polissa) q.getSingleResult();
    }

    @Override
    public List<Sinistre> getLlistatSinistresPerClient(int numClient) throws IComponentSGBDException {
        if (numClient > 0) {
            q = em.createNamedQuery("Sinistre.getSinistresFromClient", Sinistre.class);
            q.setParameter(1, numClient);
            return q.getResultList();
        }
        throw new IComponentSGBDException("Identificador invàlid, num client estrictament positiu");
    }

    @Override
    public boolean existeixSinistre(int numSinistre) {
        if (numSinistre > 0) {
            return em.find(Sinistre.class, numSinistre) != null;
        }
        return false;
    }

    @Override
    public boolean crearNouSinistre(Sinistre s) throws IComponentSGBDException {
        if (!existeixSinistre(s.getNumero())) {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return true;
        } else {
            throw new IComponentSGBDException("Sinistre ja existent");
        }
    }

    @Override
    public boolean afegirTrucada(Trucada tr, Sinistre s) {
        if (existeixSinistre(s.getNumero())) {
            s.addTrucada(tr);
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public List<Perit> getLlistaPerits() {
        q = em.createNamedQuery("Perit.getLlistaPerits", Perit.class);
        return q.getResultList();
    }

    @Override
    public void updateInforme(int idInforme) {
        q = em.createNamedQuery("InformePericial.getInformePericial", InformePericial.class);
        q.setParameter(1, idInforme);
        InformePericial informe = (InformePericial) q.getSingleResult();

        informe.setEstatInforme(ESTAT_INFORME.TANCAT);

        em.persist(informe);
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    @Override
    public int desarEntradaInforme(long idSessio, int sinId, String resultat, String desc, BigDecimal importCobert) {
        if (validaIdSessio(idSessio)) {
            
            InformePericial i = em.find(InformePericial.class, sinId);
            
            i.setResultatPeritatge(RESULTAT_PERITATGE.valueOf(resultat));
            i.setInforme(desc);
            i.setImportCobert(importCobert);
            
            em.persist(i);
            em.getTransaction().begin();
            em.getTransaction().commit();
            
            return 1;
        }
        return -1;
        
        
    }

}
