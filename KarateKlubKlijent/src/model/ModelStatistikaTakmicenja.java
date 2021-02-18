/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.Communication;
import domen.Clan;
import domen.StatistikaTakmicara;
import domen.Takmicar;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Folio1040
 */
public class ModelStatistikaTakmicenja extends AbstractTableModel {

    ArrayList<StatistikaTakmicara> lista;
    String[] columns = {"Takmicenje", "Plasman", "Poeni", "Ime i prezime"};

    public ModelStatistikaTakmicenja(ArrayList<StatistikaTakmicara> lista) {
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
        StatistikaTakmicara statistika = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return statistika.getTakmicenjeID();
            case 1:
                return statistika.getPlasman();
            case 2:
                return statistika.getBrojPoena();
            case 3: {
                try {
                    for (Takmicar takmicar : Communication.getInstance().getAllTakmicari()) {
                        if (takmicar.getTakmicarID() == statistika.getTakmicarID().getTakmicarID()) {
                            Clan clan = Communication.getInstance().getClan(takmicar.getClanID());
                            return clan.getIme() + " " + clan.getPrezime();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ModelStatistikaTakmicenja.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public void add(StatistikaTakmicara statistika) {
        lista.add(statistika);
        fireTableDataChanged();
    }

    public ArrayList<StatistikaTakmicara> getLista() {
        return lista;
    }

}
