package infomila.info.peritapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import infomila.info.peritapp.model.enums.ESTAT_SINISTRE;
import infomila.info.peritapp.model.enums.TIPUS_SINISTRE;
import infomila.info.peritapp.model.exceptions.SinistreException;
import infomila.info.peritapp.model.Trucada;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class Sinistre implements Serializable {


    private int numero;
    private Date dataAssignacio;
    private Date dataObertura;
    private Date dataTancament;
    private String descripcio;


    private ESTAT_SINISTRE estatSinistre;


    private TIPUS_SINISTRE tipusSinistre;


    private Polissa polissa;


    private InformePericial informe;


    private List<Trucada> trucades = new ArrayList();

    public Sinistre() {
    }

    public Sinistre(int numero, Date dataAssignacio, Date dataObertura, Date dataTancament, String descripcio,
                    ESTAT_SINISTRE estatSinistre, TIPUS_SINISTRE tipusSinistre, Polissa polissa) {

        setNumero(numero);
        setDataAssignacio(dataAssignacio);
        setDataObertura(dataObertura);
        setDataTancament(dataTancament);
        setDescripcio(descripcio);
        setEstatSinistre(estatSinistre);
        setTipusSinistre(tipusSinistre);
        setPolissa(polissa);

    }

    public int getNumero() {
        return numero;
    }

    private void setNumero(int numero) {
        if (numero > 0) {
            this.numero = numero;
        } else {
            throw new SinistreException("numero invàlid (valor estrictament positiu)");
        }
    }

    public Date getDataAssignacio() {
        if (dataAssignacio != null) {
            return (Date) dataAssignacio.clone();
        } else {
            return null;
        }
    }

    public void setDataAssignacio(Date dataAssignacio) {
        if (dataAssignacio != null) {
            this.dataAssignacio = new Date(dataAssignacio.getTime());
        }
    }

    public Date getDataObertura() {
        return (Date) dataObertura.clone();
    }

    public void setDataObertura(Date dataObertura) {
        if (dataObertura != null) {
            this.dataObertura = new Date(dataObertura.getTime());
        } else {
            throw new SinistreException("data obertura invàlida (valor null no permés)");
        }
    }

    public Date getDataTancament() {
        if (dataTancament != null) {
            return (Date) dataTancament.clone();
        } else {
            return null;
        }
    }

    public void setDataTancament(Date dataTancament) {
        if (dataTancament != null) {
            this.dataTancament = new Date(dataTancament.getTime());
        }
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        if (descripcio != null && !descripcio.isEmpty()) {
            this.descripcio = descripcio;
        } else {
            throw new SinistreException("descripcio invàlida (valor null o cadena buida no permés)");
        }
    }

    public ESTAT_SINISTRE getEstatSinistre() {
        return estatSinistre;
    }

    public void setEstatSinistre(ESTAT_SINISTRE estatSinistre) {
        if (estatSinistre != null) {
            this.estatSinistre = estatSinistre;
        } else {
            throw new SinistreException("estat sinistre invàlid (valor null no permés)");
        }
    }

    public TIPUS_SINISTRE getTipusSinistre() {
        return tipusSinistre;
    }

    public void setTipusSinistre(TIPUS_SINISTRE tipusSinistre) {
        if (tipusSinistre != null) {
            this.tipusSinistre = tipusSinistre;
        } else {
            throw new SinistreException("tipus sinistre invàlid (valor null no permés)");
        }
    }

    public Polissa getPolissa() {
        return polissa;
    }

    public void setPolissa(Polissa polissa) {
        this.polissa = polissa;
        if (!polissa.existeixSinistre(this)) {
            polissa.addSinistre(this);
        }
    }



    public InformePericial getInforme() {
        return informe;
    }

    //checked
    public void setInforme(InformePericial informe) {
        this.informe = informe;
        informe.setSinistre(this);
    }

    public Iterator<Trucada> getTrucades() {
        return trucades.iterator();
    }

    public void setTrucades(List<Trucada> trucades) {
        if (trucades != null) {
            this.trucades = trucades;
        } else {
            throw new SinistreException("trucades invàlides (valor null no permés)");
        }
    }

    public void addTrucada(Trucada trucada) {
        if (trucada != null && !trucades.contains(trucada)) {
            trucades.add(trucada);
        } else {
            System.out.println("Mostre: " + trucada);
            for (Trucada t : trucades) {
                System.out.println("T: " + t);
            }
            throw new SinistreException("trucada invàlida (valor null o repetit no permés)");
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.numero;
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
        if (!(this instanceof Sinistre)) {
            return false;
        }
        final Sinistre other = (Sinistre) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Sinistre{" + "numero=" + numero + ", dataAssignacio=" + dataAssignacio + ", dataObertura=" + dataObertura + ", dataTancament=" + dataTancament + ", descripcio=" + descripcio + ", estatSinistre=" + estatSinistre + ", tipusSinistre=" + tipusSinistre + ", polissa=" + polissa + ", informe=" + informe + ", trucades=" + trucades + '}';
    }

}