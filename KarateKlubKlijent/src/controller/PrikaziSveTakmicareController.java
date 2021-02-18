/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Communication;
import cordinator.MainCordinator;
import domen.Kategorija;
import domen.Takmicar;
import domen.Tim;
import form.FrmPrikaziSveTakmicare;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import model.ModelTakmicari;

/**
 *
 * @author Folio1040
 */
public class PrikaziSveTakmicareController {

    private final FrmPrikaziSveTakmicare frmPrikaziSveTakmicare;
    ArrayList<Takmicar> listaTakmicara;

    public PrikaziSveTakmicareController(FrmPrikaziSveTakmicare frmPrikaziSveTakmicare) {
        this.frmPrikaziSveTakmicare = frmPrikaziSveTakmicare;
        addActionListeners();
    }

    public void openForm() {
        setImage();
        try {
            prepareTable();
        } catch (Exception ex) {
            Logger.getLogger(PrikaziSveTakmicareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmPrikaziSveTakmicare.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmPrikaziSveTakmicare.setVisible(true);
    }

    private void prepareTable() throws Exception {
        listaTakmicara = Communication.getInstance().getAllTakmicari();
        ModelTakmicari model = new ModelTakmicari(listaTakmicara);
        frmPrikaziSveTakmicare.getTblTakmicari().setModel(model);
        JTableHeader header = frmPrikaziSveTakmicare.getTblTakmicari().getTableHeader();
        header.setBackground(new java.awt.Color(39, 45, 87));
        header.setForeground(java.awt.Color.WHITE);
        header.setFont(new Font("Segoa UI", Font.BOLD, 16));
        frmPrikaziSveTakmicare.getTblTakmicari().setRowHeight(20);
        JComboBox<Boolean> comboBox = new JComboBox<>();
        comboBox.addItem(Boolean.TRUE);
        comboBox.addItem(Boolean.FALSE);
        comboBox.setBackground(new java.awt.Color(220, 236, 248));
        comboBox.setFont(new Font("Segoa UI", Font.PLAIN, 14));
        frmPrikaziSveTakmicare.getTblTakmicari().getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
        frmPrikaziSveTakmicare.getTblTakmicari().getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));
        JComboBox<Kategorija> comboBoxKategorije = new JComboBox<>();
        comboBoxKategorije.setBackground(new java.awt.Color(220, 236, 248));
        comboBoxKategorije.setFont(new Font("Segoa UI", Font.PLAIN, 14));
        for (Kategorija kategorije : Kategorija.values()) {
            comboBoxKategorije.addItem(kategorije);
        }
        frmPrikaziSveTakmicare.getTblTakmicari().getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBoxKategorije));
        ArrayList<Tim> timovi = Communication.getInstance().getAllTimovi();
        JComboBox<Object> comboBoxTim = new JComboBox<>();
        comboBoxTim.setBackground(new java.awt.Color(220, 236, 248));
        comboBoxTim.setFont(new Font("Segoa UI", Font.PLAIN, 14));
        for (Tim tim : timovi) {
            comboBoxTim.addItem(tim);
        }
        frmPrikaziSveTakmicare.getTblTakmicari().getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(comboBoxTim));
    }

    private void addActionListeners() {
        frmPrikaziSveTakmicare.addBtnIzmeniListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(frmPrikaziSveTakmicare, "Da li ste sigurni da zelite da izmenite takmicara?", "Izmena takmicara", JOptionPane.YES_NO_OPTION) == 0) {
                        ModelTakmicari model = (ModelTakmicari) frmPrikaziSveTakmicare.getTblTakmicari().getModel();
                        ArrayList<Takmicar> takmicari = model.getListaTakmicara();
                        Communication.getInstance().updateTakmicari(takmicari);
                        Communication.getInstance().checkTakmicari();
                        prepareTable();
                        JOptionPane.showMessageDialog(frmPrikaziSveTakmicare, "Uspesno ste izmenili takmicara");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPrikaziSveTakmicare, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(PrikaziSveTakmicareController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        frmPrikaziSveTakmicare.addBtnUkloniTimListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPrikaziSveTakmicare.getTblTakmicari().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frmPrikaziSveTakmicare, "Izaberite takmicara");
                } else {
                    try {
                        if (JOptionPane.showConfirmDialog(frmPrikaziSveTakmicare, "Da li ste sigurni da zelite da uklonite tim?", "Izmena takmicara", JOptionPane.YES_NO_OPTION) == 0) {
                            Takmicar takmicar = listaTakmicara.get(row);
                            takmicar.setTim(false);
                            Communication.getInstance().updateTakmicar(takmicar);
                            Communication.getInstance().checkTakmicari();
                            prepareTable();
                            JOptionPane.showMessageDialog(frmPrikaziSveTakmicare, "Uspesno ste izmenili takmicara");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmPrikaziSveTakmicare, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(PrikaziSveTakmicareController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        frmPrikaziSveTakmicare.addObrisiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPrikaziSveTakmicare.getTblTakmicari().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frmPrikaziSveTakmicare, "Izaberite takmicara");
                } else {
                    try {
                        if (JOptionPane.showConfirmDialog(frmPrikaziSveTakmicare, "Da li ste sigurni da zelite da Obrisete?", "Izmena takmicara", JOptionPane.YES_NO_OPTION) == 0) {
                            Takmicar takmicar = listaTakmicara.get(row);
                            Communication.getInstance().deleteTakmicar(takmicar);
                            prepareTable();
                            JOptionPane.showMessageDialog(frmPrikaziSveTakmicare, "Uspesno ste izmenili takmicara");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmPrikaziSveTakmicare, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(PrikaziSveTakmicareController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        frmPrikaziSveTakmicare.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    prepareTable();
                } catch (Exception ex) {
                    Logger.getLogger(PrikaziSveTakmicareController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void setImage() {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/karate_PNG.png")));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(frmPrikaziSveTakmicare.getLblSlika().getWidth(), frmPrikaziSveTakmicare.getLblSlika().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        frmPrikaziSveTakmicare.getLblSlika().setIcon(i);
    }
}
