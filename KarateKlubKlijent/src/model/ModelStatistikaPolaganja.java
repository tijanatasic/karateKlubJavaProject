/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.Communication;
import domen.Polaganje;
import domen.RezultatPolaganja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Folio1040
 */
public class ModelStatistikaPolaganja extends AbstractTableModel{
    ArrayList<RezultatPolaganja> lista;
    String[] columns={"Pojas","Uspesno/Neuspesno","Datum"};

    public ModelStatistikaPolaganja(ArrayList<RezultatPolaganja> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RezultatPolaganja rezultat=lista.get(rowIndex);
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        ArrayList<Polaganje> polaganja=null;
        try {
            polaganja=Communication.getInstance().getAllPolaganja();
        } catch (Exception ex) {
            Logger.getLogger(ModelStatistikaPolaganja.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch(columnIndex){
            case 0:
                return rezultat.getPojas();
            case 1:
                if(rezultat.isPolozio()){
                    return "Uspesno";
                }else{
                    return "Neuspesno";
                }
            case 2:
                System.out.println(rezultat.getPolaganjeID().getPolaganjeID());
                for (Polaganje polaganje : polaganja) {
                    if(polaganje.getPolaganjeID()==rezultat.getPolaganjeID().getPolaganjeID()){
                        rezultat.setPolaganjeID(polaganje);
                    }
                }
                return sdf.format(rezultat.getPolaganjeID().getDatum());
                default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
    
}
