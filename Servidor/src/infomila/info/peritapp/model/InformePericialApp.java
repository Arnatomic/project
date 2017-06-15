/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infomila.info.peritapp.model;

import infomila.info.peritapp.model.enums.ESTAT_INFORME;
import infomila.info.peritapp.model.enums.RESULTAT_PERITATGE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mr. Robot
 */
public class InformePericialApp implements Serializable {


    private int numero;
    private Date dataEmisio;
    private BigDecimal importCobert;
    private String informe;


    private RESULTAT_PERITATGE resultatPeritatge;
    private ESTAT_INFORME estatInforme;


    private List<EntradaInformeApp> entrades = new ArrayList();


    protected InformePericialApp() {
    }
    
    public InformePericialApp(String i){
    }

    public InformePericialApp(Date dataEmisio, BigDecimal importCobert, String informe, RESULTAT_PERITATGE resultatPeritatge,
                              ESTAT_INFORME estatInforme) {

        setDataEmisio(dataEmisio);
        setImportCobert(importCobert);
        setInforme(informe);
        setResultatPeritatge(resultatPeritatge);
        setEstatInforme(estatInforme);
       

    }

    public int getNumero(){
        return numero;
    }

    public Date getDataEmisio() {
        return (Date) dataEmisio.clone();
    }

    public void setDataEmisio(Date dataEmisio) {
        if (dataEmisio != null) {
            this.dataEmisio = new Date(dataEmisio.getTime());
        } 
    }

    public BigDecimal getImportCobert() {
        return importCobert;
    }

    public void setImportCobert(BigDecimal importCobert) {
        if (importCobert != null && importCobert != BigDecimal.ZERO) {
            this.importCobert = importCobert;
        } 
    }



    public RESULTAT_PERITATGE getResultatPeritatge() {
        return resultatPeritatge;
    }

    public void setResultatPeritatge(RESULTAT_PERITATGE resultatPeritatge) {
        if (resultatPeritatge != null) {
            this.resultatPeritatge = resultatPeritatge;
        } 
    }

    public ESTAT_INFORME getEstatInforme() {
        return estatInforme;
    }

    public void setEstatInforme(ESTAT_INFORME estatInforme) {
        if (estatInforme != null) {
            this.estatInforme = estatInforme;
        } 
    }

   
    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        if (informe != null && !informe.isEmpty()) {
            this.informe = informe;
        }
    }

    public Iterator<EntradaInformeApp> getEntrades() {
        return entrades.iterator();
    }

    public void setEntrades(List<EntradaInformeApp> entrades) {
        if (entrades != null) {
            this.entrades = entrades;
        } 
    }

    public void addEntrada(EntradaInformeApp entrada) {
        if (entrada != null && !entrades.contains(entrada)) {
            entrades.add(entrada);
        }
    }

    public void removeEntradaByIndex(int index) {
        if (index >= 0) {
            try {
                entrades.remove(index);
            } catch (IndexOutOfBoundsException ex) {
               
            }
        }
    }

    public void removeEntrada(EntradaInformeApp entrada) {
        if (entrada != null && entrades.contains(entrada)) {
            entrades.remove(entrada);
        } 
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.dataEmisio);
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
        if (!(this instanceof InformePericialApp)) {
            return false;
        }
        final InformePericialApp other = (InformePericialApp) obj;
        if (!Objects.equals(this.dataEmisio, other.dataEmisio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InformePericialApp{" + "dataEmisio=" + dataEmisio + ", importCobert=" + importCobert + ", informe=" + informe + ", entrades=" + entrades + '}';
    }

}
