/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.exceptions.CitaException;
import info.infomila.exceptions.PeritException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 *
 * @author Mr. Robot
 */


@NamedQueries({
    @NamedQuery(name="Perit.login", 
            query="select count(p.numero) from Perit p where p.login=?1 and p.password=?2"),

    @NamedQuery(name="Perit.getPeritPerLogin",
            query="select p.numero from Perit p where p.login=?1")})  
   


@Entity
public class Perit  implements Serializable{
    @Transient
    private static final int passwordLenght = 9;
    @Id
    @TableGenerator(name = "comptadors_generator", table = "comptadors",
            pkColumnName = "TAULA", pkColumnValue = "perit",
            valueColumnName = "COMPTADOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "comptadors_generator")
    private int numero;
    @Column(length = 30, nullable = false)
    private String login;
    //min Lenght (9)
    @Column(length = 50, nullable = false)
    private String password;
    @Embedded
    private Persona persona;
    
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "cita", joinColumns = @JoinColumn(name = "num_perit"))
    @Column(name = "cita")
    @OrderColumn(name = "id", columnDefinition = "auto_increment")
    private List<Cita> cites = new ArrayList();
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "perit")
    private List<Sinistre> sinistres = new ArrayList();

    protected Perit() {
    }

    public Perit(String login, String password, Persona persona) {
        setLogin(login);
        setPassword(password);
        setPersona(persona);
    }

    public final int getNumero() {
        return numero;
    }

    public final String getLogin() {
        return login;
    }

    public final void setLogin(String login) {
        if (login != null && !login.isEmpty()) {
            this.login = login;
        } else {
            throw new PeritException("login invàlid (valor null o cadena buida no permés)");
        }
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        if (password != null && !password.isEmpty() && password.length() >= passwordLenght) {
            this.password = password;
        } else {
            throw new PeritException("password invàlida (valor null o cadena buida no permés, min length = 9)");
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        if (persona != null) {
            this.persona = persona;
        } else {
            throw new PeritException("persona invàlida (valor null no permés)");
        }
    }

    public final Iterator<Cita> getCites() {
        return cites.iterator();
    }

    public final void setCites(List<Cita> cites) {
        if (cites != null) {
            this.cites = cites;
        } else {
            throw new PeritException("cites invàlides (valor null no permés)");
        }
    }

    public void addCita(Cita cita) {
        if (cita != null && !cites.contains(cita)) {
            cites.add(cita);
        } else {
            throw new CitaException("Cita Invàlida (valor null o repetit no permés)");
        }
    }

    public void removeCitaByIndex(int index) {
        if (index >= 0) {
            try {
                cites.remove(index);
            } catch (IndexOutOfBoundsException ex) {
                throw new PeritException("index invàlid (fora de rang)", ex);
            }
        }
    }

    public void removeCita(Cita cita) {
        if (cita != null && cites.contains(cita)) {
            cites.remove(cita);
        } else {
            throw new PeritException("cita invàlida (valor null o no existent no permés)");
        }
    }

    public boolean existeixCita(Cita citaAcercar) {
        return cites.contains(citaAcercar);
    }

    public Iterator<Sinistre> getSinistres() {
        return sinistres.iterator();
    }

    private void setSinistres(List<Sinistre> sinistres) {
        if (sinistres != null) {
            this.sinistres = sinistres;
        } else {
            throw new PeritException("sinistres invàlids (valor null no permés)");
        }
    }

    public void addSinistre(Sinistre sinistre) {
        if (sinistre != null && !sinistres.contains(sinistre)) {
            sinistres.add(sinistre);
            if (!sinistre.getPerit().equals(this)) {
                sinistre.setPerit(this);
            }
        } else {
            throw new PeritException("sinistre invàlid (valor null o repetit no permés)");
        }
    }

    public void removeSinistreByIndex(int index) {
        if (index >= 0) {
            try {
                sinistres.remove(index);
                sinistres.get(index).setPerit(null);
            } catch (IndexOutOfBoundsException ex) {
                throw new PeritException("index invàlid (fora de rang)", ex);
            }
        }

    }

    public void removeSinistre(Sinistre sinistre) {
        if (sinistre != null && sinistres.contains(sinistre)) {
            sinistres.remove(sinistre);
            sinistres.get(sinistres.indexOf(sinistre)).setPerit(null);
        } else {
            throw new PeritException("sinistre invàlid (valor null o no existent no permés)");
        }
    }

    public boolean existeixSinistre(Sinistre sinistreAcercar) {
        return sinistres.contains(sinistreAcercar);
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        if (!(this instanceof Perit)) {
            return false;
        }
        final Perit other = (Perit) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }

}
