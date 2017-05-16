/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.enums.ESTAT_INFORME;
import info.infomila.enums.RESULTAT_PERITATGE;
import info.infomila.exceptions.InformePericialException;
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
public class InformePericial {

    private Date dataEmisio;
    private BigDecimal importCobert;
    private String informe;

    private Perit perit;

    private RESULTAT_PERITATGE resultatPeritatge;
    private ESTAT_INFORME estatInforme;

    private List<EntradaInforme> entrades = new ArrayList();
    private List<Sinistre> sinistres = new ArrayList();

    protected InformePericial() {
    }

    public InformePericial(Date dataEmisio, BigDecimal importCobert, String informe, Perit perit, RESULTAT_PERITATGE resultatPeritatge, ESTAT_INFORME estatInforme) {

        setDataEmisio(dataEmisio);
        setImportCobert(importCobert);
        setInforme(informe);
        setPerit(perit);
        setResultatPeritatge(resultatPeritatge);
        setEstatInforme(estatInforme);

    }

    public Date getDataEmisio() {
        return (Date) dataEmisio.clone();
    }

    public void setDataEmisio(Date dataEmisio) {
        if (dataEmisio != null) {
            this.dataEmisio = new Date(dataEmisio.getTime());
        } else {
            throw new InformePericialException("data emisio invàlida (valor null no permés)");
        }
    }

    public BigDecimal getImportCobert() {
        return importCobert;
    }

    public void setImportCobert(BigDecimal importCobert) {
        if (importCobert != null && importCobert != BigDecimal.ZERO) {
            this.importCobert = importCobert;
        } else {
            throw new InformePericialException("import cobert invàlid (valor estrictament positiu)");
        }
    }

    public Perit getPerit() {
        return perit;
    }

    public void setPerit(Perit perit) {
        if (perit != null) {
            this.perit = perit;
        } else {
            throw new InformePericialException("perit invàlid (valor null no permés)");
        }
    }

    public RESULTAT_PERITATGE getResultatPeritatge() {
        return resultatPeritatge;
    }

    public void setResultatPeritatge(RESULTAT_PERITATGE resultatPeritatge) {
        if (resultatPeritatge != null) {
            this.resultatPeritatge = resultatPeritatge;
        } else {
            throw new InformePericialException("resultat peritatge invàlid (valor null no permés)");
        }
    }

    public ESTAT_INFORME getEstatInforme() {
        return estatInforme;
    }

    public void setEstatInforme(ESTAT_INFORME estatInforme) {
        if (estatInforme != null) {
            this.estatInforme = estatInforme;
        } else {
            throw new InformePericialException("estat informe invàlid (valor null no permés)");
        }
    }

    public Iterator<Sinistre> getSinistres() {
        return sinistres.iterator();
    }

    public void setSinistres(List<Sinistre> sinistres) {
        if (sinistres != null) {
            this.sinistres = sinistres;
        } else {
            throw new InformePericialException("sinistres invàlids (valor null no permés)");
        }
    }

    public void addSinistre(Sinistre sinistre) {

        if (sinistre != null && !sinistres.contains(sinistre)) {
            sinistres.add(sinistre);
        } else {
            throw new InformePericialException("sinistre invàlid (valor null o repetit no permés)");
        }
    }

    public void removeSinistreByIndex(int index) {
        if (index >= 0) {
            try {
                sinistres.remove(index);
            } catch (IndexOutOfBoundsException ex) {
                throw new InformePericialException("index invàlid (fora de rang)", ex);
            }
        }
    }
    
    public void removeSinistre(Sinistre sinistre){
        if(sinistre != null && sinistres.contains(sinistre))
            sinistres.remove(sinistre);
        else throw new InformePericialException("sinistre invàlid (valor null o no existent a sinistres)");
        
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        if (informe != null && !informe.isEmpty()) {
            this.informe = informe;
        } else {
            throw new InformePericialException("informe invàlid (valor null o cadena buida no permés)");
        }
    }

    public Iterator<EntradaInforme> getEntrades() {
        return entrades.iterator();
    }

    public void setEntrades(List<EntradaInforme> entrades) {
        if (entrades != null) {
            this.entrades = entrades;
        } else {
            throw new InformePericialException("entrades invàlides (valor null no permés)");
        }
    }
    
    public void addEntrada(EntradaInforme entrada){
        if(entrada != null && ! entrades.contains(entrada))
            entrades.add(entrada);
        else throw new InformePericialException("entrada invàlida (valor null o repetit no permés)");
    }
    
    public void removeEntradaByIndex(int index){
        if (index >= 0) {
            try {
                entrades.remove(index);
            } catch (IndexOutOfBoundsException ex) {
                throw new InformePericialException("index invàlid (fora de rang)", ex);
            }
        }
    }
    
    public void removeEntrada(EntradaInforme entrada){
        if(entrada != null && entrades.contains(entrada))
            entrades.remove(entrada);
        else throw new InformePericialException("entrada invàlida (valor null o no existent no permés)");
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
        if (!(this instanceof InformePericial)) {
            return false;
        }
        final InformePericial other = (InformePericial) obj;
        if (!Objects.equals(this.dataEmisio, other.dataEmisio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InformePericial{" + "dataEmisio=" + dataEmisio + ", importCobert=" + importCobert + ", informe=" + informe + ", entrades=" + entrades + '}';
    }

}
