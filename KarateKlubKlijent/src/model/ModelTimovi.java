/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.Communication;
import domen.Clan;
import domen.Tim;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Folio1040
 */
public class ModelTimovi extends AbstractTableModel {

    ArrayList<Tim> listaTimova;
    String[] columns = {"Naziv", "Tim za kate", "Tim za borbe", "Tim takmicarski", "Clanovi"};

    public ModelTimovi(ArrayList<Tim> listaTimova) {
        this.listaTimova = listaTimova;
    }

    @Override
    public int getRowCount() {
        return listaTimova.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tim tim = listaTimova.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tim.getNaziv();
            case 1:
                return tim.isTimKate();
            case 2:
                return tim.isTimBorbe();
            case 3:
                return tim.isTimTakmicar();
            case 4:
                ArrayList<Clan> clanovi = getClanovi(tim);
                if (clanovi.isEmpty()) {
                    return "Nema clanova u timu";
                } else {
                    String clans="";
                    for (Clan clan : clanovi) {
                        clans += clan.toString() + ", ";
                    }
                    return clans.substring(0, clans.length() - 2);
                }
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Tim tim=listaTimova.get(rowIndex);
        switch(columnIndex){
            case 0:
                tim.setNaziv((String) aValue);
                break;
            case 1:
                tim.setTimKate((boolean) aValue);
                break;
            case 2:
                tim.setTimBorbe((boolean) aValue);
                break;
            case 3:
                tim.setTimTakmicar((boolean) aValue);
                break;
        }
    }
    
    

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 4:
                return false;
            default: return true;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    private ArrayList<Clan> getClanovi(Tim tim) {
        ArrayList<Clan> clanoviUTimu = new ArrayList<>();
        try {
            ArrayList<Clan> listaclanova = Communication.getInstance().getAllClanovi();
            for (Clan clan : listaclanova) {
                if (clan.getTim().getTimID() == tim.getTimID()) {
                    clanoviUTimu.add(clan);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ModelTimovi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clanoviUTimu;
    }

    public ArrayList<Tim> getListaTimova() {
        return listaTimova;
    }

    
}
