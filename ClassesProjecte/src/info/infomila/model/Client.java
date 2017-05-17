/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.exceptions.ClientException;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Mr. Robot
 */
@Entity
public class Client {
    @Id
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
        return "Client{" + "numero=" + numero + '}';
    }

   
    
    
    
}
