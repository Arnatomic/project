/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.exceptions.EntradaInformeException;
import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author Mr. Robot
 */
public class EntradaInforme {
    private int numero;
    private Date data;
    //nullable
    private Blob foto;
    
    private boolean postReparacio;

    public EntradaInforme(int numero, Date data, Blob foto, boolean postReparacio) {
        
        setNumero(numero);
        setData(data);
        setFoto(foto);
        setPostReparacio(postReparacio);      
    }

    protected EntradaInforme() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if(numero >0)
        this.numero = numero;
        else throw new EntradaInformeException("numero invàlid (valor estrictament positiu)");
    }

    public Date getData() {
        return (Date) data.clone();
    }

    public void setData(Date data) {
        if(data!=null)
        this.data = new Date(data.getTime());
        else throw new EntradaInformeException("data invàlida (valor null no permés)");
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public boolean isPostReparacio() {
        return postReparacio;
    }

    public void setPostReparacio(boolean postReparacio) {
        this.postReparacio = postReparacio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.numero;
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
        if (!(this instanceof EntradaInforme)) {
            return false;
        }
        final EntradaInforme other = (EntradaInforme) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntradaInforme{" + "numero=" + numero + ", data=" + data + ", foto=" + foto + ", postReparacio=" + postReparacio + '}';
    }
    
    
}
