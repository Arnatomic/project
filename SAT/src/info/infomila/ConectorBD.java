/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila;

import info.infomila.gestio.UserOnline;
import info.infomila.model.EntradaInforme;
import info.infomila.model.InformePericial;
import info.infomila.model.Perit;
import info.infomila.model.Sinistre;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Mr. Robot
 */
public class ConectorBD implements IComponentSGBD{
    
    private EntityManager em;
    private EntityManagerFactory emf;
    private Query q;
   
    
    public ConectorBD(){
        this("propietatsUnitatPersistencia.txt");
    }
    
    public ConectorBD(String nomFitxerPropietats){
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
    public int login(String user, String password) {
        q = em.createQuery("select p from Perit p where p.login=?1 and p.password=?2");
        q.setParameter(1, user);
        q.setParameter(2, password);
        
        Perit p = (Perit)q.getSingleResult();
        
        return p.getNumero();
    }

    @Override
    public List<Sinistre> getLlistatSinistres(int idSessio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sinistre getInfoSinistre(int idSessio, int numSinistre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InformePericial getInformePericial(int idSessio, int numSinistre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Byte[] getFoto(int idSessio, int numSinistre, int numEntrada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int desarInforme(int idSessio, int numSinistre, InformePericial informe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int desarEntradaInforme(int idSessio, int numSinistre, EntradaInforme entrada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminarEntradaInforme(int idSessio, int numSinistre, int numEntrada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tancarConexio() {
        if(em!=null){
            em.close();
        }
    }

    @Override
    public int commit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rollback() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
