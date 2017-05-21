/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila;

import info.infomila.model.EntradaInforme;
import info.infomila.model.InformePericial;
import info.infomila.model.Sinistre;
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
    public int login(String user, String password);
    
    /**
     * Mètode per recollir una llista de sinistres assignats a un perit en concret
     * @param idSessio, identificador del pèrit que fa la petició
     * @return, una llista amb el sinistres, retorno llista i no iterador, per tenir accés mes inmediat al nombre de sinistres que em retorna.
     */
    public List<Sinistre> getLlistatSinistres(int idSessio);
    
    /**
     * Mètode per anar a buscar un sinistre a partir del seu id.
     * @param idSessio, identificador del usuari que fa la petició.
     * @param numSinistre, identificador del sinistre a buscar.
     * @return, el sinistre si existeix, null si el sinistre no existeix
     */
    public Sinistre getInfoSinistre(int idSessio, int numSinistre);
    
    /**
     * Mètode per anar a buscar un informe pericial a traves del id del seu sinistre
     * @param idSessio, identificador del usuari que fa la petició.
     * @param numSinistre, identificador del sinistre al qual pertany l'informe a buscar.
     * @return, l'informe si existeix, o null si no existeix.
     */
    public InformePericial getInformePericial(int idSessio,int numSinistre);
    
    /**
     * Mètode per anar a buscar la foto d'una entrada d'un informe concrets.
     * @param idSessio, identificador del usuari que fa la petició
     * @param numSinistre, identificador del sinistre al qual pertany l'informe
     * @param numEntrada, identificador de l'entrada de l'informe que apunta l'id del segon parametre.
     * @return, un array de bytes amb la foto si existeix, o be null si no existeix
     */
    public Byte[] getFoto(int idSessio, int numSinistre, int numEntrada);
    
    /**
     * Mètode per enregistrar un Informe pericial a la base de dades.
     * @param idSessio, identificador de l'usuari que fa la petició
     * @param numSinistre, numero del sinistre associat a l'informe a desar.
     * @param informe, informe a desar
     * @return, 0 si ha anat tot bé, negatiu en cas d'error
     */
    public int desarInforme(int idSessio,int numSinistre, InformePericial informe);
    
    /**
     * Mètode per enregistrar una entrada d'un informe pericial a la base de dades.
     * @param idSessio, identificador de l'usuari que fa la petició
     * @param numSinistre, numero del sinistre associat a l'informe a desar.
     * @param entrada, entrada a desar
     * @return, 0 si ha anat tot bé, negatiu en cas d'error
     */
    public int desarEntradaInforme(int idSessio, int numSinistre, EntradaInforme entrada);
    
    /**
     * Mètode per esborrar una entrada d'un informe concret a la base de dades.
     * @param idSessio, identificador de l'usuari que fa la petició
     * @param numSinistre, numero del sinistre associat a l'entrada a esborrar.
     * @param numEntrada, numero de la entrada que volem esborrar
     * @return,  0 si ha anat tot bé, negatiu en cas d'error
     */
    public int eliminarEntradaInforme(int idSessio, int numSinistre, int numEntrada);

    /**
     * Mètode per tancar la conexió amb l'origen de dades.
     */
    public void tancarConexio();
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
    
}
