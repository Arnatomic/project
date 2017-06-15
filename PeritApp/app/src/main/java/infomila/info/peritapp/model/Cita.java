package infomila.info.peritapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import infomila.info.peritapp.model.exceptions.CitaException;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class Cita implements Serializable {


    private Date diaHora;
    private int horesPrevistes;
    private int sinistreId;

    protected Cita() {
    }

    public Cita(Date diaHora, int horesPrevistes) {
        setDiaHora(diaHora);
        setHoresPrevistes(horesPrevistes);
    }

    public Cita(Date diaHora, int horesPrevistes, int sinistreId) {
        setDiaHora(diaHora);
        setHoresPrevistes(horesPrevistes);
        setSinistre(sinistreId);
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

    public int getSinistre() {
        return sinistreId;
    }

    public void setSinistre(int sinistre) {
        if(sinistre>0)
            this.sinistreId = sinistre;
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

