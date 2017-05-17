/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.exceptions.PersonaException;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Mr. Robot
 */
@Embeddable
public class Persona {
    
    
    private String nif;
    private String nom;
    private String cognom1;
    private String cognom2;
    private Date dataNaix;

    protected Persona() {
    }

    public Persona(String nif, String nom, String cognom1, String cognom2, Date dataNaix) {
        
        setNif(nif);
        setNom(nom);
        setCognom1(cognom1);
        setCognom2(cognom2);
        setDataNaix(dataNaix);     
    }
    

    public String getNif() {
        return nif;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public Date getDataNaix() {
        return (Date) dataNaix.clone();
    }

    public void setNif(String nif) {
        if(nif != null && nif.length() == 9)
        this.nif = nif;
        else throw new PersonaException("nif invàlid (valor null o longitud no igual a 9 no permés)");
    }

    public void setNom(String nom) {
        if(nom != null && ! nom.isEmpty())
        this.nom = nom;
        else throw new PersonaException("nom invàlid (valor null o cadena buida no permés)");
    }

    public void setCognom1(String cognom1) {
        if(cognom1 != null && ! cognom1.isEmpty())
        this.cognom1 = cognom1;
        else throw new PersonaException("cognom1 invàlid (valor null o cadena buida no permés)");
    }

    public void setCognom2(String cognom2) {
        if(cognom2 != null && ! cognom2.isEmpty())
        this.cognom2 = cognom2;
        else throw new PersonaException("cognom2 invàlid (valor null o cadena buida no permés)");
    }

    public void setDataNaix(Date dataNaix) {
        if(dataNaix != null)
        this.dataNaix = new Date(dataNaix.getTime());
        else throw new PersonaException("data naix invàlida (valor null no permés)");
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.nif);
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
        if (!(this instanceof Persona)) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.nif, other.nif)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "nif=" + nif + ", nom=" + nom + ", cognom1=" + cognom1 + ", cognom2=" + cognom2 + ", dataNaix=" + dataNaix + '}';
    }
    
    
}
