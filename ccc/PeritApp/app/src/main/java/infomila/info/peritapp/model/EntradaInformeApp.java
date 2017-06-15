package infomila.info.peritapp.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class EntradaInformeApp implements Serializable {


    private int numero;
    private Date data;

    private String descripcio;

    private byte[] bytesFoto;
    private boolean postReparacio;

    public EntradaInformeApp(Date data, String descripcio, Blob foto, boolean postReparacio) {

        setData(data);
        setDescripcio(descripcio);
        setFoto(foto);
        setPostReparacio(postReparacio);
    }

    public EntradaInformeApp(Date data, String descripcio, byte[] foto, boolean postReparacio) {

        setData(data);
        setDescripcio(descripcio);
       this.bytesFoto = foto;
        setPostReparacio(postReparacio);
    }

    protected EntradaInformeApp() {
    }


    public Date getData() {
        return (Date) data.clone();
    }

    public void setData(Date data) {
        if(data!=null)
            this.data = new Date(data.getTime());
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        if(descripcio != null && ! descripcio.isEmpty())
            this.descripcio = descripcio;
    }

    public void setFoto(Blob foto) {
        try {
            bytesFoto = foto.getBytes(0,(int) foto.length());
        } catch (SQLException ex) {
            bytesFoto = null;
        }
    }

    public boolean isPostReparacio() {
        return postReparacio;
    }

    public void setPostReparacio(boolean postReparacio) {
        this.postReparacio = postReparacio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public byte[] getBytesFoto() {
        return bytesFoto;
    }

    public void setBytesFoto(byte[] bytesFoto) {
        this.bytesFoto = bytesFoto;
    }


}
