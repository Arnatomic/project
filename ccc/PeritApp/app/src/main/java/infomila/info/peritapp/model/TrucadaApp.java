package infomila.info.peritapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class TrucadaApp implements Serializable {



    private Date dataHora;
    private String descripcio;
    private String PersonaContacte;

    protected TrucadaApp() {
    }

    public TrucadaApp(Date dataHora, String descripcio, String PersonaContacte) {

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
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        if(descripcio != null && ! descripcio.isEmpty())
            this.descripcio = descripcio;
    }

    public String getPersonaContacte() {
        return PersonaContacte;
    }

    public void setPersonaContacte(String PersonaContacte) {
        if(PersonaContacte != null)
            this.PersonaContacte = PersonaContacte;
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
        final TrucadaApp other = (TrucadaApp) obj;
        if (!Objects.equals(this.descripcio, other.descripcio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TrucadaApp{" + "dataHora=" + dataHora + ", descripcio=" + descripcio + ", PersonaContacte=" + PersonaContacte + '}';
    }


}

