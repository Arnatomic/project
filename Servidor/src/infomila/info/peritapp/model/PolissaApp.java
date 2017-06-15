/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infomila.info.peritapp.model;

import infomila.info.peritapp.model.enums.TIPUS_HABITATGE;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mr. Robot
 */
public class PolissaApp implements Serializable {

    //readOnly

    private int numero;

    private Date dataInici;
    private Date dataFi;
    private BigDecimal importPolissa;
    private BigDecimal importContinent;
    private BigDecimal importContingut;

    private String poblacio;
    private String liniaAdreca;


    private String clientName;

    private TIPUS_HABITATGE tipusHabitatge;
    

    protected PolissaApp() {
    }
    
    
    public PolissaApp(int numero){
        setNumero(numero);
    }

    public PolissaApp(int numero, String poblacio, String liniaAdreca, Date dataInici, Date dataFi, BigDecimal importPolissa,
                      BigDecimal importContinent, BigDecimal importContingut, String client, TIPUS_HABITATGE tipusHabitatge) {

        setNumero(numero);
        setPoblacio(poblacio);
        setLiniaAdreca(liniaAdreca);
        setDataInici(dataInici);
        setDataFi(dataFi);
        setImportPolissa(importPolissa);
        setImportContinent(importContinent);
        setImportContingut(importContingut);
        setClient(client);
        setTipusHabitatge(tipusHabitatge);

    }

    public int getNumero() {
        return numero;
    }

    private void setNumero(int numero) {
        if (numero > 0) {
            this.numero = numero;
        } 
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        if (poblacio != null && poblacio.length() > 2) {
            this.poblacio = poblacio;
        } 
    }

    public String getLiniaAdreca() {
        return liniaAdreca;
    }

    public void setLiniaAdreca(String liniaAdreca) {
        if (liniaAdreca != null && liniaAdreca.length() > 3) {
            this.liniaAdreca = liniaAdreca;
        } 
    }

    public Date getDataInici() {
        return (Date) dataInici.clone();
    }

    public void setDataInici(Date dataInici) {
        if (dataInici != null) {
            this.dataInici = new Date(dataInici.getTime());
        }
    }

    public Date getDataFi() {
        return (Date) dataFi.clone();
    }

    public void setDataFi(Date dataFi) {
        if (dataFi != null) {
            this.dataFi = new Date(dataFi.getTime());
        } 
    }

    public BigDecimal getImportPolissa() {
        return importPolissa;
    }

    public void setImportPolissa(BigDecimal importPolissa) {
        if (importPolissa != null && importPolissa != BigDecimal.ZERO) {
            this.importPolissa = importPolissa;
        } 
    }

    public BigDecimal getImportContinent() {
        return importContinent;
    }

    public void setImportContinent(BigDecimal importContinent) {
        if (importContinent != null && importContinent != BigDecimal.ZERO) {
            this.importContinent = importContinent;
        } 
    }

    public BigDecimal getImportContingut() {
        return importContingut;
    }

    public void setImportContingut(BigDecimal importContingut) {
        if (importContingut != null && importContingut != BigDecimal.ZERO) {
            this.importContingut = importContingut;
        } 
    }

    public String getClient() {
        return clientName;
    }

    public void setClient(String client) {
        if (client != null) {
            this.clientName = client;
        } 
    }

    public TIPUS_HABITATGE getTipusHabitatge() {
        return tipusHabitatge;
    }

    public void setTipusHabitatge(TIPUS_HABITATGE tipusHabitatge) {
        if (tipusHabitatge != null) {
            this.tipusHabitatge = tipusHabitatge;
        } 
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
        if (!(this instanceof PolissaApp)) {
            return false;
        }
        final PolissaApp other = (PolissaApp) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PolissaApp{" + "numero=" + numero + ", adreca=" + poblacio + " - " + liniaAdreca + ", dataInici=" + dataInici + ", dataFi=" + dataFi + ", importPolissa=" + importPolissa + ", importContinent=" + importContinent + ", importContingut=" + importContingut + '}';
    }

}


