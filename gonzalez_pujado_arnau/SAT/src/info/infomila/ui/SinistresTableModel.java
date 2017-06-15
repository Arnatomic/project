/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import info.infomila.model.Client;
import info.infomila.model.Sinistre;
import java.util.ArrayList;

/**
 *
 * @author Mr. Robot
 */
public class SinistresTableModel extends AbstractTableModel{
    
    private List<Sinistre> sinistres = new ArrayList<>();
    private String[] columnes;
    
    public SinistresTableModel(List<Sinistre> sinistres){
        super();
        this.sinistres = sinistres;
        columnes = new String[] {"Num","Data Assignació","Data Obertura","Data Tancament",
                            "Descripció","Num Polissa","Tipus Sinistre","Estat Sinistre"};
    }

    @Override
    public int getRowCount() {        
        return sinistres.size();
    }

    @Override
    public int getColumnCount() {
        return columnes.length;
    }
/*
    "Num","Data Assignació","Data Obertura","Data Tancament",
    "Descripció","Num Polissa","Tipus Sinistre","Estat Sinistre"};
    */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sinistre sinistre = sinistres.get(rowIndex);
        switch(columnIndex){
            case 0: return sinistre.getNumero();
            case 1: if(sinistre.getDataAssignacio() == null) return ""; 
                    else return sinistre.getDataAssignacio();
            case 2: return sinistre.getDataObertura();
            case 3: if(sinistre.getDataTancament() == null) return "";
                    else return sinistre.getDataTancament();
            case 4: return sinistre.getDescripcio();
            case 5: return sinistre.getPolissa().getNumero();
            case 6: return sinistre.getTipusSinistre();
            case 7: return sinistre.getEstatSinistre();
            
            default: return null;
        
        }
    }
    
    public String getColumnName(int col){
        return columnes[col];
    }
    
    
    
}
