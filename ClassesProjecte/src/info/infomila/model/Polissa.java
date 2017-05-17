/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.enums.TIPUS_HABITATGE;
import info.infomila.exceptions.PolissaException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mr. Robot
 */
public class Polissa {

    //readOnly
    private int numero;
    private Date dataInici;
    private Date dataFi;
    private BigDecimal importPolissa;
    private BigDecimal importContinent;
    private BigDecimal importContingut;

    //substituir el camp adreca per poblacio string + linia adreça;
    private Adreca adreca;
    private Cobertura cobertura;
    private Client client;

    private TIPUS_HABITATGE tipusHabitatge;

    private List<Sinistre> sinistres = new ArrayList();

    protected Polissa() {
    }

    public Polissa(int numero, Adreca adreca, Cobertura cobertura, Date dataInici, Date dataFi, BigDecimal importPolissa,
            BigDecimal importContinent, BigDecimal importContingut, Client client, TIPUS_HABITATGE tipusHabitatge) {

        setNumero(numero);
        setAdreca(adreca);
        setCobertura(cobertura);
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
        } else {
            throw new PolissaException("numero invàlid (valor estrictament positiu)");
        }
    }

    public Adreca getAdreca() {
        return adreca;
    }

    public void setAdreca(Adreca adreca) {
        if (adreca != null) {
            this.adreca = adreca;
        } else {
            throw new PolissaException("adreca invàlida (valor null no permés)");
        }
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        if (cobertura != null) {
            this.cobertura = cobertura;
        } else {
            throw new PolissaException("cobertura invàlida (valor null no permés)");
        }
    }

    public Date getDataInici() {
        return (Date) dataInici.clone();
    }

    public void setDataInici(Date dataInici) {
        if (dataInici != null) {
            this.dataInici = new Date(dataInici.getTime());
        } else {
            throw new PolissaException("data inici invàlida (valor null no permés)");
        }
    }

    public Date getDataFi() {
        return (Date) dataFi.clone();
    }

    public void setDataFi(Date dataFi) {
        if (dataFi != null) {
            this.dataFi = new Date(dataFi.getTime());
        } else {
            throw new PolissaException("data fi invàlida (valor null no permés)");
        }
    }

    public BigDecimal getImportPolissa() {
        return importPolissa;
    }

    public void setImportPolissa(BigDecimal importPolissa) {
        if (importPolissa != null && importPolissa != BigDecimal.ZERO) {
            this.importPolissa = importPolissa;
        } else {
            throw new PolissaException("import polissa invàlid (valor null o no positiu no permés)");
        }
    }

    public BigDecimal getImportContinent() {
        return importContinent;
    }

    public void setImportContinent(BigDecimal importContinent) {
        if (importContinent != null && importContinent != BigDecimal.ZERO) {
            this.importContinent = importContinent;
        } else {
            throw new PolissaException("import continent invàlid (valor null o no positiu no permés)");
        }
    }

    public BigDecimal getImportContingut() {
        return importContingut;
    }

    public void setImportContingut(BigDecimal importContingut) {
        if (importContingut != null && importContingut != BigDecimal.ZERO) {
            this.importContingut = importContingut;
        } else {
            throw new PolissaException("import contingut invàlid (valor null o no positiu no permés)");
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (client != null) {
            this.client = client;
        } else {
            throw new PolissaException("client invàlid (valor null no permés)");
        }
    }

    public TIPUS_HABITATGE getTipusHabitatge() {
        return tipusHabitatge;
    }

    public void setTipusHabitatge(TIPUS_HABITATGE tipusHabitatge) {
        if (tipusHabitatge != null) {
            this.tipusHabitatge = tipusHabitatge;
        } else {
            throw new PolissaException("tipus habitatge invàlid (valor null no permés)");
        }
    }

    public Iterator<Sinistre> getSinistres() {
        return sinistres.iterator();
    }

    public void addSinistre(Sinistre sinistre) {
        if (sinistre != null && !sinistres.contains(sinistre)) {
            sinistres.add(sinistre);
            if (!sinistre.getPolissa().equals(this)) {
                sinistre.setPolissa(this);
            }
        } else {
            throw new PolissaException("sinistre invàlid (valor null o repetit no permés)");
        }
    }

    public void removeSinistreByIndex(int index) {
        if (index > 0) {
            try {
                sinistres.get(index).setPolissa(null);
                sinistres.remove(index);
            } catch (IndexOutOfBoundsException ex) {
                throw new PolissaException("index invàlid (fora de rang)", ex);
            }
        }
    }

    public void removeSinistre(Sinistre sinistre) {
        if (sinistre != null && sinistres.contains(sinistre)) {
            sinistres.get(sinistres.indexOf(sinistre)).setPolissa(null);
            sinistres.remove(sinistre);
        } else {
            throw new PolissaException("sinistre invàlid (valor null o no existent no permés)");
        }
    }

    public boolean existeixSinistre(Sinistre sinistreAcercar) {
        return sinistres.contains(sinistreAcercar);
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
        if (!(this instanceof Polissa)) {
            return false;
        }
        final Polissa other = (Polissa) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Polissa{" + "numero=" + numero + ", adreca=" + adreca + ", cobertura=" + cobertura + ", dataInici=" + dataInici + ", dataFi=" + dataFi + ", importPolissa=" + importPolissa + ", importContinent=" + importContinent + ", importContingut=" + importContingut + '}';
    }

}
