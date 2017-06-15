/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.exceptions.EntradaInformeException;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Mr. Robot
 */




@Embeddable
public class EntradaInforme implements Serializable {
  
    @Transient
    private int numero;
    
    @Column(name = "data_informe" ,nullable = false)
    private Date data;
    //nullable
    @Column(length = 100)
    private String descripcio;   
    
    private Blob foto;
    @Column(name = "despres_reparacio", nullable = false)
    private boolean postReparacio;

    public EntradaInforme(Date data,String descripcio, Blob foto, boolean postReparacio) {
        
        setData(data);
        setDescripcio(descripcio);
        setFoto(foto);
        setPostReparacio(postReparacio);      
    }
    
    public EntradaInforme(Date data,String descripcio, boolean postReparacio) {        
        setData(data);
        setDescripcio(descripcio);      
        setPostReparacio(postReparacio);      
    }

    protected EntradaInforme() {
    }

    
    public Date getData() {
        return (Date) data.clone();
    }

    public void setData(Date data) {
        if(data!=null)
        this.data = new Date(data.getTime());
        else throw new EntradaInformeException("data invàlida (valor null no permés)");
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        if(descripcio != null && ! descripcio.isEmpty())
        this.descripcio = descripcio;
        else throw new EntradaInformeException("descripcio invàlida (valor null o cadena buida no permés)");
    }
    
    public int getNumero(){ return numero;}

    public void setNumero(int nunmero) {
        this.numero = nunmero;
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

    
    
    
}
