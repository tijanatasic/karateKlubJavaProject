/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Clan;
import domen.Pojas;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Folio1040
 */
public class ModelClanovi extends AbstractTableModel{
    private final ArrayList<Clan> listaClanova;
    private final String[] columnName={"Ime","Prezime","JMBG","Pojas"};
    
    public ModelClanovi(ArrayList<Clan> listaClanova) {
        this.listaClanova = listaClanova;
    }

    @Override
    public int getRowCount() {
        return listaClanova.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clan clan=listaClanova.get(rowIndex);
        switch(columnIndex){
            case 0: return clan.getIme();
            case 1: return clan.getPrezime();
            case 2: return clan.getJMBG();
            case 3: return clan.getPojas();
            default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Clan clan=listaClanova.get(rowIndex);
        switch(columnIndex){
            case 3: clan.setPojas((Pojas)aValue);
            break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex==3) return true;
        return false;
    }

    public Clan getClan(int row) {
        return listaClanova.get(row);
    }

    public ArrayList<Clan> getListaClanova() {
        return listaClanova;
    }
    
    
}
