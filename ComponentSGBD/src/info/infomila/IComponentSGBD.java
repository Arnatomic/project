/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila;

import info.infomila.model.Client;
import info.infomila.model.EntradaInforme;
import info.infomila.model.InformePericial;
import info.infomila.model.Perit;
import info.infomila.model.Polissa;
import info.infomila.model.Sinistre;
import info.infomila.model.Trucada;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mr. Robot
 */
public interface IComponentSGBD {
    
    /**
     *  Mètode que valida el login dels usuaris.
     * @param user, login del usuari en questió
     * @param password, contrassenya del usuari en questió encriptada en md5
     * @return idSessio, com a numero identificatiu del usuari en curs, o negatiu en cas de validació errònia
     */
    public long login(String user, String password) throws IComponentSGBDException;
    
    /**
     * Mètode per recollir una llista de sinistres assignats a un perit en concret
     * @param idSessio, identificador del pèrit que fa la petició
     * @return, una llista amb el sinistres, retorno llista i no iterador, per tenir accés mes inmediat al nombre de sinistres que em retorna.
     */
    public List<Sinistre> getLlistatSinistres(long idSessio) throws IComponentSGBDException;
    
    /**
     * Mètode per anar a buscar un sinistre a partir del seu id.
     * @param idSessio, identificador del usuari que fa la petició.
     * @param numSinistre, identificador del sinistre a buscar.
     * @return, el sinistre si existeix, null si el sinistre no existeix
     */
    public Sinistre getInfoSinistre(long idSessio, int numSinistre) throws IComponentSGBDException;
    
    /**
     * Mètode per anar a buscar un informe pericial a traves del id del seu sinistre
     * @param idSessio, identificador del usuari que fa la petició.
     * @param numSinistre, identificador del sinistre al qual pertany l'informe a buscar.
     * @return, l'informe si existeix, o null si no existeix.
     */
    public InformePericial getInformePericial(long idSessio,int numSinistre) throws IComponentSGBDException;
    
    /**
     * Mètode per anar a buscar la foto d'una entrada d'un informe concrets.
     * @param idSessio, identificador del usuari que fa la petició
     * @param numSinistre, identificador del sinistre al qual pertany l'informe
     * @param numEntrada, identificador de l'entrada de l'informe que apunta l'id del segon parametre.
     * @return, un array de bytes amb la foto si existeix, o be null si no existeix
     */
    public Byte[] getFoto(long idSessio, int numSinistre, int numEntrada) throws IComponentSGBDException;
    
    /**
     * Mètode per enregistrar un Informe pericial a la base de dades.
     * @param idSessio, identificador de l'usuari que fa la petició
     * @param numSinistre, numero del sinistre associat a l'informe a desar.
     * @param informe, informe a desar
     * @return, 0 si ha anat tot bé, negatiu en cas d'error
     */
    public int desarInforme(long idSessio, InformePericial informe) throws IComponentSGBDException;
    
    /**
     * Mètode per enregistrar una entrada d'un informe pericial a la base de dades.
     * @param idSessio, identificador de l'usuari que fa la petició    
     * @param entrada, entrada a desar
     * @return, 0 si ha anat tot bé, negatiu en cas d'error
     */
    public int desarEntradaInforme(long idSessio, EntradaInforme entrada) throws IComponentSGBDException;
    
    /**
     * Mètode per esborrar una entrada d'un informe concret a la base de dades.
     * @param idSessio, identificador de l'usuari que fa la petició    
     * @param numEntrada, numero de la entrada que volem esborrar
     * @return,  0 si ha anat tot bé, negatiu en cas d'error
     */
    public int eliminarEntradaInforme(long idSessio, int numSinistre, int numEntrada) throws IComponentSGBDException;

    /**
     * Mètode per tancar la conexió amb l'origen de dades.
     */
    public void tancarConexio(long idSessio);
    /**
     * Mètode per fer commit.
     * @return, retorna 0 si s'ha pogut realitzar el commit, negatiu en cas contrari.
     */
    public int commit();
    
    /**
     * Mètode per fer rollback.
     * @return, retorna 0 si s'ha pogut realitzar el commit, negatiu en cas contrari.
     */
    public int rollback();

//    public List<Sinistre> getLlistaSinistresPerClient(int i) throws IComponentSGBDException;
    
    public List<Client> getLlistatClients();
    
    public List<Client> getClientPerDni(String dni) throws IComponentSGBDException;
    
    public List<Client> getClientPerNomCognoms(String nom, String cognom1, String cognom2) throws IComponentSGBDException;
    
    public List<Client> getClientPerDataNaix(java.sql.Date dataNaix) throws IComponentSGBDException;
    
    public List<Client> getClientFiltrat(String dni,String nom, String cognom1,String cognom2, java.sql.Date dataNaix) throws IComponentSGBDException;
    
    public List<Polissa> getLlistaPolices();
    
    public List<Polissa> getLlistaPolicesPerNumClient(int numClient) throws IComponentSGBDException;
    
    public Polissa getPolissaPerId(int idPolissa) throws IComponentSGBDException;
    
    public List<Sinistre> getLlistatSinistresPerClient(int numClient) throws IComponentSGBDException;
    
    public List<Perit> getLlistaPerits();
    
    public boolean existeixSinistre(int numSinistre);
    
    public boolean crearNouSinistre(Sinistre s) throws IComponentSGBDException;
    
    public boolean afegirTrucada(Trucada tr, Sinistre s) throws IComponentSGBDException;

    public void updateInforme(int idInforme);

    public int desarEntradaInforme(long codiPerit, int sinId, String resultat, String desc, BigDecimal importCobert);
}
