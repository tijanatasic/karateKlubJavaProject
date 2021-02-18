/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Communication;
import cordinator.MainCordinator;
import domen.Tim;
import form.FrmPogledajSveTimove;
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
import model.ModelTimovi;

/**
 *
 * @author Folio1040
 */
public class PrikaziSveTimoveControler {

    private final FrmPogledajSveTimove frmPrikaziSveTimove;
    private ArrayList<Tim> listaTimova = null;

    public PrikaziSveTimoveControler(FrmPogledajSveTimove frmPrikaziSveTimove) {
        this.frmPrikaziSveTimove = frmPrikaziSveTimove;
        addActionListeners();
    }

    public void openForm() {
        setImage();
        prepareTable();
        frmPrikaziSveTimove.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmPrikaziSveTimove.setVisible(true);
    }

    private void prepareTable() {
        try {
            listaTimova = Communication.getInstance().getAllTimovi();
        } catch (Exception ex) {
            Logger.getLogger(PrikaziSveTimoveControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        setModel(listaTimova);
    }

    private void addActionListeners() {
        frmPrikaziSveTimove.addPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String naziv = frmPrikaziSveTimove.getTxtPretraga().getText().trim();
                    ArrayList<Tim> timoviIzabrani = Communication.getInstance().getTimovi(naziv);
                    if (timoviIzabrani.isEmpty()) {
                        JOptionPane.showMessageDialog(frmPrikaziSveTimove, "Sistem ne moze da nadje timove po zadatim vrednostima");
                    } else {
                        setModel(timoviIzabrani);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PrikaziSveTimoveControler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        frmPrikaziSveTimove.addDeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPrikaziSveTimove.getTblTimovi().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frmPrikaziSveTimove, "Izaberite tim koji zelite da obrisete", "Izaberite tim", JOptionPane.ERROR_MESSAGE);
                } else {
                    Tim tim = listaTimova.get(row);
                    try {
                        if (JOptionPane.showConfirmDialog(frmPrikaziSveTimove, "Da li ste sigurni da zelite da obrisete tim", "Brisanje tima", JOptionPane.YES_NO_OPTION) == 0) {
                            Communication.getInstance().deleteTim(tim);
                            JOptionPane.showMessageDialog(frmPrikaziSveTimove, "Uspesno ste obrisali tim");
                            Communication.getInstance().checkTakmicari();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmPrikaziSveTimove, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(PrikaziSveTimoveControler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        frmPrikaziSveTimove.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                prepareTable();
            }
        });
        frmPrikaziSveTimove.addUpdateTimoveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModelTimovi model = (ModelTimovi) frmPrikaziSveTimove.getTblTimovi().getModel();
                ArrayList<Tim> timovi = model.getListaTimova();
                try {
                    if (JOptionPane.showConfirmDialog(frmPrikaziSveTimove, "Da li ste sigurni da zelite da sacuvate izmene?", "Izmena timova", JOptionPane.YES_NO_OPTION) == 0) {
                        int brojac;
                        boolean ponavljanje = false;
                        for (Tim tim : Communication.getInstance().getAllTimovi()) {
                            brojac = 0;
                            for (Tim tim1 : timovi) {
                                if (tim.getNaziv().equals(tim1.getNaziv())) {
                                    brojac++;
                                }
                            }
//                            if (timovi.size() == Communication.getInstance().getAllTimovi().size()) {
                                if (brojac > 1) {
                                    ponavljanje = true;
                                    break;
                                }
//                            }else{
//                                if(brojac!=1){
//                                    ponavljanje = true;
//                                    break;
//                                }
//                            }
                            System.out.println(brojac);
                        }
                        System.out.println(ponavljanje);
                        if (!ponavljanje) {
                            Communication.getInstance().updateTimovi(timovi);
                            JOptionPane.showMessageDialog(frmPrikaziSveTimove, "Sistem je zapamtio tim");
                        } else {
                            JOptionPane.showMessageDialog(frmPrikaziSveTimove, "Sistem ne moze da zapamti tim", "Greska", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPrikaziSveTimove, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(PrikaziSveTimoveControler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setImage() {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/karate-png.png")));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(frmPrikaziSveTimove.getLblSlika().getWidth(), frmPrikaziSveTimove.getLblSlika().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        frmPrikaziSveTimove.getLblSlika().setIcon(i);
    }

    private void setModel(ArrayList<Tim> listaTimova) {
        ModelTimovi model = new ModelTimovi(listaTimova);
        frmPrikaziSveTimove.getTblTimovi().setModel(model);
        JTableHeader header = frmPrikaziSveTimove.getTblTimovi().getTableHeader();
        header.setBackground(new java.awt.Color(39, 45, 87));
        header.setForeground(java.awt.Color.WHITE);
        header.setFont(new Font("Segoa UI", Font.BOLD, 16));
        frmPrikaziSveTimove.getTblTimovi().setRowHeight(20);
        JComboBox<Boolean> comboBox = new JComboBox<>();
        comboBox.addItem(Boolean.TRUE);
        comboBox.addItem(Boolean.FALSE);
        comboBox.setBackground(new java.awt.Color(220, 236, 248));
        comboBox.setFont(new Font("Segoa UI", Font.PLAIN, 14));
        frmPrikaziSveTimove.getTblTimovi().getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
        frmPrikaziSveTimove.getTblTimovi().getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));
        frmPrikaziSveTimove.getTblTimovi().getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));
    }

}
