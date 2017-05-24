/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import info.infomila.model.Client;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Mr. Robot
 */
public class ClientsTableModel extends AbstractTableModel{
    
    private List<Client> clients = new ArrayList<>();
    private String[] columnes;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public ClientsTableModel(List<Client> clients){
        super();
        this.clients = clients;
        columnes = new String[] {"DNI","Nom","Cognom1","Cognom2","Data Naixement"};
    }

    @Override
    public int getRowCount() {        
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client = clients.get(rowIndex);
        switch(columnIndex){
            case 0: return client.getPersona().getNif();
            case 1: return client.getPersona().getNom();
            case 2: return client.getPersona().getCognom1();
            case 3: return client.getPersona().getCognom2();
            case 4: return new java.sql.Date(client.getPersona().getDataNaix().getTime());
            
            default: return null;
        
        }
    }
    
    public String getColumnName(int col){
        return columnes[col];
    }
    
    
    
}
