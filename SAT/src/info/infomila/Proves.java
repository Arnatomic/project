/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila;

import info.infomila.enums.ESTAT_INFORME;
import info.infomila.enums.RESULTAT_PERITATGE;
import static info.infomila.enums.RESULTAT_PERITATGE.REPARAT;
import info.infomila.enums.TIPUS_HABITATGE;
import info.infomila.enums.TIPUS_SINISTRE;
import info.infomila.model.InformePericial;
import info.infomila.model.Perit;
import info.infomila.model.Persona;
import info.infomila.model.Polissa;
import info.infomila.model.Sinistre;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mr. Robot
 */
public class Proves {
    public static void main(String[] args) {
    
        if (args.length != 1 && args.length != 2) {
            System.out.println("L'execució d'aquest programa necessita d'1 o 2 arguments: ");
            System.out.println("Argument 1: Nom de la classe de la capa de persistència (obligatori)");
            System.out.println("Argument 2: Nom del fitxer de configuració que espera la classe (optatiu)");
            System.exit(1);
        }
    
        String classeCapaPersistencia = args[0];
        String nomFitxerConfiguracio = null;
        if (args.length == 2) {
            nomFitxerConfiguracio = args[1];
        }

        IComponentSGBD obj = null;
        Class c = null;
        try {
            System.out.println(classeCapaPersistencia);
            c = Class.forName(classeCapaPersistencia);
        } catch (ClassNotFoundException ex) {
            System.out.println("No es troba la classe " + classeCapaPersistencia);
            System.exit(1);
        }
        try {
            if (nomFitxerConfiguracio == null) {
                obj = (IComponentSGBD) c.newInstance();
            } else {
                try {
                    Constructor x = c.getConstructor(String.class);
                    obj = (IComponentSGBD) x.newInstance(nomFitxerConfiguracio);
                } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    System.out.println("Problemes en intentar executar constructor de la " + classeCapaPersistencia + " amb paràmetre=" + nomFitxerConfiguracio);
                    System.out.println("Més informació: " + ex.getMessage());
                    if (ex.getCause() != null) {
                        System.out.println("Causat per: " + ex.getCause().getMessage());
                        if (ex.getCause().getCause() != null) {
                            System.out.println(ex.getCause().getCause().getMessage());
                        }
                    }
                    System.exit(1);
                }
            }
            System.out.println("Connexió establerta");
            
            
            long num = 0;
            try {
                num = obj.login("nilcente", "4567527766403b33df1717882f8c2d3c");
            
            
            System.out.println("He trobat: " + num);
            
            
            for(Sinistre ss: obj.getLlistatSinistres(num)){
                System.out.println("Sinistre: " + ss);
            }
            
            
            
            Sinistre xx = obj.getInfoSinistre(num, 2);
            
                System.out.println("Sinistre Suelto: " + xx);
                
                InformePericial ip = obj.getInformePericial(num, 2);
//                Polissa p = new Polissa(0, nomFitxerConfiguracio, classeCapaPersistencia, dataInici, dataFi, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO, client, TIPUS_HABITATGE.TRASTER)
//                Sinistre jj = new Sinistre(22, new Date(), new Date(), new Date(), "Sinistre Prova", ESTAT_SINISTRE.ASSIGNAT, TIPUS_SINISTRE.ELECTRICITAT, pp, new Polissa(), ip), TIPUS_SINISTRE.ELECTRICITAT, perit, polissa, ip);
//                System.out.println("Informe Suelto: " + ip);
//                Persona p = new Persona("47111651S","nom", "cognom1", "cognom2", new Date());
//               // Perit pp = new Perit("pepe","gotera123456",p);
//                InformePericial vv = new InformePericial(new Date(), new BigDecimal(1203.33), "Informe Molt detallat",
//                        pp, RESULTAT_PERITATGE.COBERT_PARCIAL,ESTAT_INFORME.PENDENT,xx);
//                        
//                obj.desarInforme(num, vv);
                
            } catch (IComponentSGBDException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            
            obj.tancarConexio(num);
            
           System.exit(0);
            
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("No es pot obtenir l'objecte de persistència");
            System.out.println("Més informació: " + ex.getMessage());
            if (ex.getCause() != null) {
                System.out.println("Causat per: " + ex.getCause().getMessage());
            }
            System.exit(1);
        } 
    
    
    
    }
    
}
