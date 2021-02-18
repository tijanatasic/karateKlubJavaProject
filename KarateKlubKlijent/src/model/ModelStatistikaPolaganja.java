/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.Communication;
import domen.Clan;
import domen.Polaganje;
import domen.RezultatPolaganja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Folio1040
 */
public class ModelStatistikaPolaganja extends AbstractTableModel {

    ArrayList<RezultatPolaganja> lista;
    String[] columns = {"Ime i prezime", "Pojas", "Uspesno/Neuspesno", "Datum"};

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
        try {
            RezultatPolaganja rezultat = lista.get(rowIndex);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            ArrayList<Polaganje> polaganja = null;
            polaganja = Communication.getInstance().getAllPolaganja();
            switch (columnIndex) {
                case 0: {
                    Clan clan = Communication.getInstance().getClan(rezultat.getClanID());
                    return clan.getIme() + " " + clan.getPrezime();
                }
                case 1:
                    return rezultat.getPojas();
                case 2:
                    if (rezultat.isPolozio()) {
                        return "Uspesno";
                    } else {
                        return "Neuspesno";
                    }
                case 3:
                    System.out.println(rezultat.getPolaganjeID().getPolaganjeID());
                    for (Polaganje polaganje : polaganja) {
                        if (polaganje.getPolaganjeID() == rezultat.getPolaganjeID().getPolaganjeID()) {
                            rezultat.setPolaganjeID(polaganje);
                        }
                    }
                    return sdf.format(rezultat.getPolaganjeID().getDatum());
                default:
                    return "N/A";
            }
        } catch (Exception ex) {
            Logger.getLogger(ModelStatistikaPolaganja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "N/A";
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public ArrayList<RezultatPolaganja> getLista() {
        return lista;
    }

    public void add(RezultatPolaganja rezultat){
        lista.add(rezultat);
        fireTableDataChanged();
    }

    public void delete(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }
}
