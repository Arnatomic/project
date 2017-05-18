/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.exceptions.TrucadaException;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

/**
 *
 * @author Mr. Robot
 */
@Embeddable
public class Trucada implements Serializable {

    private Date dataHora;
    private String descripcio;
    private String PersonaContacte;

    protected Trucada() {
    }

    public Trucada(Date dataHora, String descripcio, String PersonaContacte) {
        
        setDataHora(dataHora);
        setDescripcio(descripcio);
        setPersonaContacte(PersonaContacte);
        
    }

    public Date getDataHora() {
        return (Date) dataHora.clone();
    }

    public void setDataHora(Date dataHora) {
        if(dataHora != null)
        this.dataHora = new Date(dataHora.getTime());
        else throw new TrucadaException("data i hora invàlida (valor null no permés)");
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        if(descripcio != null && ! descripcio.isEmpty())
        this.descripcio = descripcio;
        else throw new TrucadaException("descripcio invàlida (valor null no permés)");
    }

    public String getPersonaContacte() {
        return PersonaContacte;
    }

    public void setPersonaContacte(String PersonaContacte) {
        if(PersonaContacte != null)
        this.PersonaContacte = PersonaContacte;
        else throw new TrucadaException("persona contacte invàlida (valor null no permés)");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.dataHora);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trucada other = (Trucada) obj;
        if (!Objects.equals(this.dataHora, other.dataHora)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trucada{" + "dataHora=" + dataHora + ", descripcio=" + descripcio + ", PersonaContacte=" + PersonaContacte + '}';
    }
    
    
}
