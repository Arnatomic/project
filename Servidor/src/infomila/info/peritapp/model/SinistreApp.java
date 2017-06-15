/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infomila.info.peritapp.model;

import infomila.info.peritapp.model.enums.ESTAT_SINISTRE;
import infomila.info.peritapp.model.enums.TIPUS_SINISTRE;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mr. Robot
 */
public class SinistreApp implements Serializable {


    private int numero;
    private Date dataAssignacio;
    private Date dataObertura;
    private Date dataTancament;
    private String descripcio;


    private ESTAT_SINISTRE estatSinistre;


    private TIPUS_SINISTRE tipusSinistre;


    private PolissaApp polissaApp;


    private InformePericialApp informe;


    private List<TrucadaApp> trucades = new ArrayList();   


    public SinistreApp() {
    }

    public SinistreApp(int numero, Date dataAssignacio, Date dataObertura, Date dataTancament, String descripcio,
                       ESTAT_SINISTRE estatSinistre, TIPUS_SINISTRE tipusSinistre, PolissaApp polissaApp) {

        setNumero(numero);
        setDataAssignacio(dataAssignacio);
        setDataObertura(dataObertura);
        setDataTancament(dataTancament);
        setDescripcio(descripcio);
        setEstatSinistre(estatSinistre);
        setTipusSinistre(tipusSinistre);
        setPolissaApp(polissaApp);

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero > 0) {
            this.numero = numero;
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
        } 
    }

    public ESTAT_SINISTRE getEstatSinistre() {
        return estatSinistre;
    }

    public void setEstatSinistre(ESTAT_SINISTRE estatSinistre) {
        if (estatSinistre != null) {
            this.estatSinistre = estatSinistre;
        } 
    }

    public TIPUS_SINISTRE getTipusSinistre() {
        return tipusSinistre;
    }

    public void setTipusSinistre(TIPUS_SINISTRE tipusSinistre) {
        if (tipusSinistre != null) {
            this.tipusSinistre = tipusSinistre;
        } 
    }

    public PolissaApp getPolissaApp() {
        return polissaApp;
    }

    public void setPolissaApp(PolissaApp polissaApp) {
        this.polissaApp = polissaApp;        
    }

    public InformePericialApp getInforme() {
        return informe;
    }

    //checked
    public void setInforme(InformePericialApp informe) {
        this.informe = informe;      
    }

    public Iterator<TrucadaApp> getTrucades() {
        return trucades.iterator();
    }

    public void setTrucades(List<TrucadaApp> trucades) {
        if (trucades != null) {
            this.trucades = trucades;
        } 
    }

    public void addTrucada(TrucadaApp trucadaApp) {
        if (trucadaApp != null && !trucades.contains(trucadaApp)) {
            trucades.add(trucadaApp);
        } else {
            System.out.println("Mostre: " + trucadaApp);
            for (TrucadaApp t : trucades) {
                System.out.println("T: " + t);
            }
           
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
        if (!(this instanceof SinistreApp)) {
            return false;
        }
        final SinistreApp other = (SinistreApp) obj;
        return true;
    }

    @Override
    public String toString() {
        return "SinistreApp{" + "numero=" + numero + ", dataAssignacio=" + dataAssignacio + ", dataObertura=" + dataObertura + ", dataTancament=" + dataTancament + ", descripcio=" + descripcio + ", estatSinistre=" + estatSinistre + ", tipusSinistre=" + tipusSinistre + ", polissaApp=" + polissaApp + ", informe=" + informe + ", trucades=" + trucades + '}';
    }

}