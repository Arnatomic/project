package info.infomila.model;

import info.infomila.enums.TIPUS_VIA;
import info.infomila.exceptions.AdrecaException;
import info.infomila.exceptions.CitaException;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mr. Robot
 */
public class Adreca {

    private String nomVia;
    private int num;
    private int pis;
    private String porta;
    //nullable
    private String bloc;
    //nullable
    private String escala;
    private String liniaAdreca;
    
    private TIPUS_VIA tipusVia;

    protected Adreca() {
    }

    public Adreca(String nomVia, int num, int pis, String porta, String bloc, String escala, String liniaAdreca, TIPUS_VIA tipusVia) {
        setNomVia(nomVia);
        setNum(num);
        setPis(pis);
        setPorta(porta);
        setBloc(bloc);
        setEscala(escala);
        setLiniaAdreca(liniaAdreca);
        setTipusVia(tipusVia);
    }

    

    public String getNomVia() {
        return nomVia;
    }

    public void setNomVia(String nomVia) {
        if(nomVia!=null && !nomVia.isEmpty())
        this.nomVia = nomVia;
        else throw new AdrecaException("Via invàlida (Null o cadena buida no permés)");
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        if(num>0)
        this.num = num;
        else throw new AdrecaException("Numero de via invàlid (Estrictament positiu)");
    }

    public int getPis() {
        return pis;
    }

    public void setPis(int pis) {
        if(pis>0)
        this.pis = pis;
        else throw new AdrecaException("Pis invàlid (Estrictament positiu)");
    }

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        if(bloc!=null){
            if(!bloc.isEmpty()) this.bloc = bloc;
            else throw new AdrecaException("Bloc valor cadena buida no permés");
        }
        
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        if(escala!=null){
            if(!escala.isEmpty())  this.escala = escala;
            else throw new AdrecaException("Escala valor cadena buida no permés");
        }
       
    }

    public String getLiniaAdreca() {
        return liniaAdreca;
    }

    public void setLiniaAdreca(String liniaAdreca) {
        if(liniaAdreca!=null && ! liniaAdreca.isEmpty())
        this.liniaAdreca = liniaAdreca;
        else throw new AdrecaException("Linia Adreca invàlida (Null o cadena buida no permès");
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        if(porta!=null && ! porta.isEmpty())
        this.porta = porta;
        else throw new AdrecaException("Porta invàlida (Valor null o cadena buida no permès)");
    }

    public TIPUS_VIA getTipusVia() {
        return tipusVia;
    }

    public void setTipusVia(TIPUS_VIA tipusVia) {
        if(tipusVia !=null)
        this.tipusVia = tipusVia;
        else throw new CitaException("Tipus Via invàlida (Valor null no permés)");
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nomVia);
        hash = 97 * hash + this.num;
        hash = 97 * hash + this.pis;
        hash = 97 * hash + Objects.hashCode(this.porta);
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
        if (!(this instanceof Adreca)) {
            return false;
        }
        final Adreca other = (Adreca) obj;
        if (this.num != other.num) {
            return false;
        }
        if (this.pis != other.pis) {
            return false;
        }
        if (!Objects.equals(this.nomVia, other.nomVia)) {
            return false;
        }
        if (!Objects.equals(this.porta, other.porta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Adreca{" + "nomVia=" + nomVia + ", num=" + num + ", pis=" + pis + ", porta=" + porta + ", bloc=" + bloc + ", escala=" + escala + ", liniaAdreca=" + liniaAdreca + '}';
    }
    
    
    
    

}
