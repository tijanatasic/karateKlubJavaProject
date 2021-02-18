/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Kategorija;
import domen.Takmicar;
import domen.Tim;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Folio1040
 */
public class ModelTakmicari extends AbstractTableModel{
    
    ArrayList<Takmicar> listaTakmicara;
    String[] columns={"Takmicar","Tim","Pojedinacno","Kategorija","Naziv tima"};

    public ModelTakmicari(ArrayList<Takmicar> listaTakmicara) {
        this.listaTakmicara = listaTakmicara;
    }
    

    @Override
    public int getRowCount() {
        return listaTakmicara.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Takmicar takmicar=listaTakmicara.get(rowIndex);
        switch(columnIndex){
            case 0: 
                return takmicar.getClanID().getIme()+" "+takmicar.getClanID().getPrezime();
            case 1:
                return takmicar.isTim();
            case 2:
                return takmicar.isPojedinacno();
            case 3:
                return takmicar.getKategorija();
            case 4:
                if(takmicar.getTimID().getTimID()==0){
                    return "Nema tim";
                }else{
                    return takmicar.getTimID().getNaziv();
                }
                default: return "N/A";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Takmicar takmicar=listaTakmicara.get(rowIndex);
        switch(columnIndex){
            case 1:
                takmicar.setTim((Boolean)aValue);
                break;
            case 2:
                takmicar.setPojedinacno((Boolean)aValue);
                break;
            case 3:
                takmicar.setKategorija((Kategorija)aValue);
                break;
            case 4:
                takmicar.setTimID((Tim)aValue);
                break;
        }
    }
    
    

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return false;
        }
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public ArrayList<Takmicar> getListaTakmicara() {
        return listaTakmicara;
    }
    
    
}
