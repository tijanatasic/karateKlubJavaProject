/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Communication;
import cordinator.MainCordinator;
import domen.Clan;
import domen.Pojas;
import form.FrmPrikaziSveClanove;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import model.ModelClanovi;

/**
 *
 * @author Folio1040
 */
public class PrikaziSveClanoveController {

    private final FrmPrikaziSveClanove frmPrikaziSve;

    public PrikaziSveClanoveController(FrmPrikaziSveClanove frmPrikaziSve) {
        this.frmPrikaziSve = frmPrikaziSve;
        addActionListener();
    }

    public void openViewForm() {
        prepareTable();
        frmPrikaziSve.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmPrikaziSve.setVisible(true);
    }

    public void prepareTable() {
        try {
            ArrayList<Clan> listaClanova;
            listaClanova = Communication.getInstance().getAllClanovi();
            setModel(listaClanova);
        } catch (Exception ex) {
            Logger.getLogger(PrikaziSveClanoveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addActionListener() {
        frmPrikaziSve.addPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ime = frmPrikaziSve.getTxtPretraga().getText().trim();
                    ArrayList<Clan> clanoviZaPrikaz = Communication.getInstance().getAllClanovi(ime);
                    if (clanoviZaPrikaz.isEmpty()) {
                        JOptionPane.showMessageDialog(frmPrikaziSve, "Sistem ne moze da nadje clanove po zadatim vrednostima");
                    } else {
                        setModel(clanoviZaPrikaz);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PrikaziSveClanoveController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        frmPrikaziSve.addPrikaziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openForm();
            }

            private void openForm() {
                int row = frmPrikaziSve.getTblClanovi().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frmPrikaziSve, "Morate da izberete clana", "Greska", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        ModelClanovi model = (ModelClanovi) frmPrikaziSve.getTblClanovi().getModel();
                        Clan clan = Communication.getInstance().getClan(model.getClan(row));
                        MainCordinator.getInstance().viewClana(clan);
                    } catch (Exception ex) {
                        Logger.getLogger(PrikaziSveClanoveController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        frmPrikaziSve.addObrisiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                int row = frmPrikaziSve.getTblClanovi().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frmPrikaziSve, "Morate da izberete clana", "Greska", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (JOptionPane.showConfirmDialog(frmPrikaziSve, "Da li ste sigurni da zelite da obrisete clana", "Brisanje", JOptionPane.YES_NO_OPTION) == 0) {
                        ModelClanovi model = (ModelClanovi) frmPrikaziSve.getTblClanovi().getModel();
                        Clan clan = model.getClan(row);
                        try {
                            Communication.getInstance().deleteClan(clan);
                            JOptionPane.showMessageDialog(frmPrikaziSve, "Sistem je obrisao clana");
                            refreshTable();
                        } catch (Exception ex) {
                            Logger.getLogger(PrikaziSveClanoveController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }

            private void refreshTable() {
                prepareTable();
            }
        });
        frmPrikaziSve.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                prepareTable();
            }
        });

        frmPrikaziSve.sacuvajIzmeneListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModelClanovi model = (ModelClanovi) frmPrikaziSve.getTblClanovi().getModel();
                ArrayList<Clan> listaClanova = model.getListaClanova();
                try {
                    Communication.getInstance().updateClan(listaClanova);
                    JOptionPane.showMessageDialog(frmPrikaziSve, "Sacuvane su izmene");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPrikaziSve, "Doslo je do greske prilikom cuvanja izmena: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(PrikaziSveClanoveController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setModel(ArrayList<Clan> listaClanova) {
        ModelClanovi model = new ModelClanovi(listaClanova);
        frmPrikaziSve.getTblClanovi().setModel(model);
        JTableHeader header = frmPrikaziSve.getTblClanovi().getTableHeader();
        header.setBackground(new java.awt.Color(39, 45, 87));
        header.setForeground(java.awt.Color.WHITE);
        header.setFont(new Font("Segoa UI", Font.BOLD, 16));
        frmPrikaziSve.getTblClanovi().setRowHeight(20);
        JComboBox<Pojas> comboBox = new JComboBox<>();
        comboBox.setBackground(new java.awt.Color(220, 236, 248));
        comboBox.setFont(new Font("Segoa UI", Font.PLAIN, 14));
        for (Pojas value : Pojas.values()) {
            comboBox.addItem(value);
        }
        frmPrikaziSve.getTblClanovi().getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));
    }
}
