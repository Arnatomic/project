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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Query;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 *
 * @author Mr. Robot
 */
@NamedQueries({
    @NamedQuery(name="Sinistre.getLlistaSinistres", 
            query="select s from Sinistre s"),
    
    @NamedQuery(name = "Sinistre.getSinistrePeriNum",
            query = "select s from Sinistre s where s.numero = ?1"),
    
    @NamedQuery(name = "Sinistre.getSinistresFromClient",
            query = "select s from Sinistre s where s.polissa.client.numero = ?1")})


@Entity
public class Sinistre implements Serializable{

    //readOnly
    @Id
    @TableGenerator(name = "comptadors_generator", table = "comptadors",
            pkColumnName = "TAULA", pkColumnValue = "sinistre",
            valueColumnName = "COMPTADOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "comptadors_generator")
    private int numero;
    @Column(name = "data_assignacio", nullable = false)
    private Date dataAssignacio;
    @Column(name = "data_obertura")
    private Date dataObertura;
    @Column(name = "data_tancament")
    private Date dataTancament;
    @Column(length = 100, nullable = false)
    private String descripcio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estat_sinistre", nullable = false)
    private ESTAT_SINISTRE estatSinistre;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipus_sinistre", nullable = false)
    private TIPUS_SINISTRE tipusSinistre;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_polissa")
    private Polissa polissa;
    //nullable
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_perit")
    private Perit perit;
    //nullable

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @PrimaryKeyJoinColumn(name = "num_sinistre")
    private InformePericial informe;
    
    
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "trucada",
            joinColumns = @JoinColumn(name = "num_sinistre"))
    @Column(name = "trucada", nullable = false, unique = true)
    @OrderColumn(name = "ordre", columnDefinition = "auto_increment")
    private List<Trucada> trucades = new ArrayList();

    public Sinistre() {
    }

    public Sinistre(int numero, Date dataAssignacio, Date dataObertura, Date dataTancament, String descripcio,
            ESTAT_SINISTRE estatSinistre, TIPUS_SINISTRE tipusSinistre, Perit perit, Polissa polissa) {

        setNumero(numero);
        setDataAssignacio(dataAssignacio);
        setDataObertura(dataObertura);
        setDataTancament(dataTancament);
        setDescripcio(descripcio);
        setEstatSinistre(estatSinistre);
        setTipusSinistre(tipusSinistre);
        setPerit(perit);
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
        if(dataAssignacio != null)
        return (Date) dataAssignacio.clone();
        else return null;
    }

    public void setDataAssignacio(Date dataAssignacio) {
        if (dataAssignacio != null) {
            this.dataAssignacio = new Date(dataAssignacio.getTime());
        } else {
            throw new SinistreException("data assignacio invàlida (valor null no permés)");
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
        if(dataTancament != null)
        return (Date) dataTancament.clone();
        else return null;
    }

    public void setDataTancament(Date dataTancament) {
        if (dataTancament != null) {
            this.dataTancament = new Date(dataTancament.getTime());
        } else {
            throw new SinistreException("data tancament invàlida (valor null no permés)");
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
        //if (polissa != null) {
        this.polissa = polissa;
        if (!polissa.existeixSinistre(this)) {
            polissa.addSinistre(this);
        }
//        } else {
//            throw new SinistreException("polista invàlida (valor null no permés)");
//        }
    }

    public Perit getPerit() {
        return perit;
    }

    public void setPerit(Perit perit) {
        this.perit = perit;
        if (!perit.existeixSinistre(this)) {
            perit.addSinistre(this);
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
        return "Sinistre{" + "numero=" + numero + ", dataAssignacio=" + dataAssignacio + ", dataObertura=" + dataObertura + ", dataTancament=" + dataTancament + ", descripcio=" + descripcio + ", estatSinistre=" + estatSinistre + ", tipusSinistre=" + tipusSinistre + ", polissa=" + polissa + ", perit=" + perit + ", informe=" + informe + ", trucades=" + trucades + '}';
    }

    


}
