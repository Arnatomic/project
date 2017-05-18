/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.exceptions.CitaException;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author Mr. Robot
 */
@Embeddable
public class Cita implements Serializable{
    
    @Transient
    private Date diaHora;
    @Column(name = "duracio")
    private int horesPrevistes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_sinistre", nullable = false)
    private Sinistre sinistre;

    protected Cita() {
    }
    
    public Cita(Date diaHora, int horesPrevistes) {
        setDiaHora(diaHora);
        setHoresPrevistes(horesPrevistes);
    }

    public Cita(Date diaHora, int horesPrevistes, Sinistre sinistre) {
        setDiaHora(diaHora);
        setHoresPrevistes(horesPrevistes);
        setSinistre(sinistre);
    }    

    public Date getDiaHora() {
        return (Date) diaHora.clone();
    }

    public void setDiaHora(Date diaHora) {
        if(diaHora!=null)
        this.diaHora = new Date(diaHora.getTime());
        else throw new CitaException("Dia-Hora de la cita invàlids (valor null no permés)");
    }

    public int getHoresPrevistes() {
        return horesPrevistes;
    }

    public void setHoresPrevistes(int horesPrevistes) {
        if(horesPrevistes>0)
        this.horesPrevistes = horesPrevistes;
        else throw new CitaException("Hores previstes invàlid (valor estrictament positiu)");
    }

    public Sinistre getSinistre() {
        return sinistre;
    }

    public void setSinistre(Sinistre sinistre) {
        if(sinistre!=null)
        this.sinistre = sinistre;
        else throw new CitaException("Sinistre invàlid (valor null no permés)");
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.diaHora);
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
        if (!(this instanceof Cita)) {
            return false;
        }
        final Cita other = (Cita) obj;
        if (!Objects.equals(this.diaHora, other.diaHora)) {
            return false;
        }
        return true;
    }
    
    
}
