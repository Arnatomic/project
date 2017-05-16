/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.enums.ESTAT_SINISTRE;
import info.infomila.enums.RESULTAT_PERITATGE;
import info.infomila.enums.TIPUS_SINISTRE;
import info.infomila.exceptions.SinistreException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mr. Robot
 */
public class Sinistre {
    
    //readOnly
    private int numero;
    private Date dataAssignacio;
    private Date dataObertura;
    private Date dataTancament;
    private String descripcio;    
    
    private ESTAT_SINISTRE estatSinistre;
    private TIPUS_SINISTRE tipusSinistre;
    private RESULTAT_PERITATGE resultatPeritatge;
    
    //nullable
    private Polissa polissa;
    
    private Perit perit;
    
    private List<InformePericial> informes = new ArrayList();
    private List<Trucada> trucades = new ArrayList();

    public Sinistre() {
    }

    public Sinistre(int numero, Date dataAssignacio, Date dataObertura, Date dataTancament, String descripcio,
                ESTAT_SINISTRE estatSinistre, TIPUS_SINISTRE tipusSinistre, RESULTAT_PERITATGE resultatPeritatge,
                Perit perit,Polissa polisa) {
        
        
        setNumero(numero);
        setDataAssignacio(dataAssignacio);
        setDataObertura(dataObertura);
        setDataTancament(dataTancament);
        setDescripcio(descripcio);
        setEstatSinistre(estatSinistre);
        setTipusSinistre(tipusSinistre);
        setResultatPeritatge(resultatPeritatge);
        setPerit(perit);
        setPolissa(polissa);
        
    }

    public int getNumero() {
        return numero;
    }  
    
    private void setNumero(int numero){
        if(numero > 0)
        this.numero = numero;
        else throw new SinistreException("numero invàlid (valor estrictament positiu)");
    }

    public Date getDataAssignacio() {
        return (Date) dataAssignacio.clone();
    }

    public void setDataAssignacio(Date dataAssignacio) {
        if(dataAssignacio != null)
        this.dataAssignacio = new Date(dataAssignacio.getTime());
        else throw new SinistreException("data assignacio invàlida (valor null no permés)");
    }

    public Date getDataObertura() {
        return (Date) dataObertura.clone();
    }

    public void setDataObertura(Date dataObertura) {
        if(dataObertura != null)
        this.dataObertura = new Date(dataObertura.getTime());
        else throw new SinistreException("data obertura invàlida (valor null no permés)");
    }

    public Date getDataTancament() {
        return (Date) dataTancament.clone();
    }

    public void setDataTancament(Date dataTancament) {
        if(dataTancament != null)
        this.dataTancament = new Date(dataTancament.getTime());
        else throw new SinistreException("data tancament invàlida (valor null no permés)");
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        if(descripcio != null && ! descripcio.isEmpty())
        this.descripcio = descripcio;
        else throw new SinistreException("descripcio invàlida (valor null o cadena buida no permés)");
    }

    public ESTAT_SINISTRE getEstatSinistre() {
        return estatSinistre;
    }

    public void setEstatSinistre(ESTAT_SINISTRE estatSinistre) {
        if(estatSinistre != null)
        this.estatSinistre = estatSinistre;
        else throw new SinistreException("estat sinistre invàlid (valor null no permés)");
    }

    public TIPUS_SINISTRE getTipusSinistre() {
        return tipusSinistre;
    }

    public void setTipusSinistre(TIPUS_SINISTRE tipusSinistre) {
        if(tipusSinistre != null)
        this.tipusSinistre = tipusSinistre;
        else throw new SinistreException("tipus sinistre invàlid (valor null no permés)");
    }

    public RESULTAT_PERITATGE getResultatPeritatge() {
        return resultatPeritatge;
    }

    public void setResultatPeritatge(RESULTAT_PERITATGE resultatPeritatge) {
        if(resultatPeritatge != null)
        this.resultatPeritatge = resultatPeritatge;
        else throw new SinistreException("resultat peritatge invàlid (valor null no permés)");
    }

    public Polissa getPolissa() {
        return polissa;
    }

    public void setPolissa(Polissa polissa) {
       // if(polissa != null)
        this.polissa = polissa;
      //  else throw new SinistreException("polista invàlida (valor null no permés)");
    }

    public Perit getPerit() {
        return perit;
    }

    public void setPerit(Perit perit) {
        if(perit != null)
        this.perit = perit;
        else throw new SinistreException("perit invàlid (valor null no permés)");
    }

    public Iterator<InformePericial> getInformes() {
        return informes.iterator();
    }

    public void setInformes(List<InformePericial> informes) {
        if(informes != null)
        this.informes = informes;
        else throw new SinistreException("informes invàlids (valor null no permés)");
    }
    
    public void addInforme(InformePericial informe){
        if(informe != null && ! informes.contains(informe))
            informes.add(informe);
        else throw new SinistreException("informe invàlid (valor null o repetit no permés)");
    }

    public Iterator<Trucada> getTrucades() {
        return trucades.iterator();
    }

    public void setTrucades(List<Trucada> trucades) {
        if(trucades != null)
        this.trucades = trucades;
        else throw new SinistreException("trucades invàlides (valor null no permés)");                
    }
    
    public void addTrucada(Trucada trucada){
        if(trucada != null && ! trucades.contains(trucada))
            trucades.add(trucada);
        else throw new SinistreException("trucada invàlida (valor null o repetit no permés)");
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
        return "Sinistre{" + "numero=" + numero + ", dataAssignacio=" + dataAssignacio + ", dataObertura=" + dataObertura + ", dataTancament=" + dataTancament + ", descripcio=" + descripcio + '}';
    }
    
    
    
         
}
