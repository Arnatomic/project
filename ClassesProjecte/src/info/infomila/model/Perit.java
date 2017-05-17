/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import info.infomila.exceptions.CitaException;
import info.infomila.exceptions.PeritException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mr. Robot
 */
public class Perit {

    private static final int passwordLenght = 9;

    private int numero;
    private String login;
    //min Lenght (9)
    private String password;

    private Persona persona;

    private List<Cita> cites = new ArrayList();
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
