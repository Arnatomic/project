/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.exceptions.ClientException;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

/**
 *
 * @author Mr. Robot
 */

@NamedQueries({
    @NamedQuery(name="Client.getLlistaClients", 
            query="select c from Client c"),
    
    @NamedQuery(name = "Client.getClientPerDni",
            query = "select c from Client c where c.persona.nif like ?1"),
    
    @NamedQuery(name = "Client.getClientPerNomCognoms",
            query = "select c from Client c where (?1='' or c.persona.nom like ?1) and (?2='' or c.persona.cognom1 like ?2) and (?3='' or c.persona.cognom2 is null or c.persona.cognom2 like ?3)"),
    
    @NamedQuery(name = "Client.getClientPerDataNaix",
            query = "select c from Client c where c.persona.dataNaix = ?1"),
    @NamedQuery(name ="Client.fitreTotal",
            query = "select c from Client c where (?1='' or c.persona.nif like ?1) and (?2='' or c.persona.nom like ?2) "
                    + "and (?3='' or c.persona.cognom1 like ?3) and (?4='' or c.persona.cognom2 is null or c.persona.cognom2 like ?4) and(?5 is null or c.persona.dataNaix = ?5)")

    })


@Entity
public class Client implements Serializable {
    @Id
    @TableGenerator(name = "comptadors_generator", table = "comptadors",
            pkColumnName = "TAULA", pkColumnValue = "client",
            valueColumnName = "COMPTADOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "comptadors_generator")
    private int numero;
    @Embedded
    private Persona persona;

    protected Client() {
    }

    public Client(int numero,Persona persona) {        
        setNumero(numero);
        setPersona(persona);
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if(numero >0)
        this.numero = numero;
        else throw new ClientException("numero invàlid (valor estrictament positiu)");
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        if(persona != null)
        this.persona = persona;
        else throw new ClientException("persona invàlida (valor null no permés)");
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.numero;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(this instanceof Client)) {
            return false;
        }
        final Client other = (Client) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "numero=" + numero + ", persona=" + persona + '}';
    }
    
    

    
   
    
    
    
}
