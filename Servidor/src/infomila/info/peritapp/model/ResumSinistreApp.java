/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infomila.info.peritapp.model;

import infomila.info.peritapp.model.enums.ESTAT_INFORME;
import infomila.info.peritapp.model.enums.TIPUS_SINISTRE;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Mr. Robot
 */
public class ResumSinistreApp implements Serializable {

    private int id;
    private int numPetit;
    private int sinistreId;
    private Date diaHora;
    private String adreca;
   

    private String tipusSinistre;

    private ESTAT_INFORME estatInforme;

    protected ResumSinistreApp() {
    }

    public ResumSinistreApp(int id, int numPetit, int sinistreId, Date diaHora, String adreca, String tipusSinistre, ESTAT_INFORME estatInforme) {
        this.id = id;
        this.numPetit = numPetit;
        this.sinistreId = sinistreId;
        this.diaHora = diaHora;
        this.adreca = adreca;
        this.tipusSinistre = tipusSinistre;
        this.estatInforme = estatInforme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumPetit() {
        return numPetit;
    }

    public void setNumPetit(int numPetit) {
        this.numPetit = numPetit;
    }

    public int getSinistreId() {
        return sinistreId;
    }

    public void setSinistreId(int sinistreId) {
        this.sinistreId = sinistreId;
    }

    public Date getDiaHora() {
        return diaHora;
    }

    public void setDiaHora(Date diaHora) {
        this.diaHora = diaHora;
    }

   

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getTipusSinistre() {
        return tipusSinistre;
    }

    public void setTipusSinistre(String tipusSinistre) {
        this.tipusSinistre = tipusSinistre;
    }

    public ESTAT_INFORME getEstatInforme() {
        return estatInforme;
    }

    public void setEstatInforme(ESTAT_INFORME estatInforme) {
        this.estatInforme = estatInforme;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.id;
        hash = 19 * hash + this.numPetit;
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
        final ResumSinistreApp other = (ResumSinistreApp) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.numPetit != other.numPetit) {
            return false;
        }
        return true;
    }

    
    
}
