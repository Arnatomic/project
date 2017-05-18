/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.exceptions.CoberturaException;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mr. Robot
 */
@Embeddable
public class Cobertura {
    
    
    private int codi;
    @Column(length = 100, nullable = false)
    private String descripcio;

    protected Cobertura() {
    }

    public Cobertura(int codi, String descripcio) {
        setCodi(codi);
        setDescripcio(descripcio);
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        if(codi >0)
        this.codi = codi;
        else throw new CoberturaException("codi invàlid (valor estrictament positiu)");
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        if(descripcio!=null && ! descripcio.isEmpty())
        this.descripcio = descripcio;
        else throw new CoberturaException("descripció invàlida (valor null o cadena buida no permés)");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codi;
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
        if (!(this instanceof Cobertura)) {
            return false;
        }
        final Cobertura other = (Cobertura) obj;
        if (this.codi != other.codi) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cobertura{" + "codi=" + codi + ", descripcio=" + descripcio + '}';
    }
    
    
    
    
}
