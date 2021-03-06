package infomila.info.peritapp.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import infomila.info.peritapp.model.exceptions.EntradaInformeException;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class EntradaInforme implements Serializable {


    private int numero;
    private Date data;

    private String descripcio;

    private Blob foto;
    private boolean postReparacio;

    public EntradaInforme(Date data,String descripcio, Blob foto, boolean postReparacio) {

        setData(data);
        setDescripcio(descripcio);
        setFoto(foto);
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
