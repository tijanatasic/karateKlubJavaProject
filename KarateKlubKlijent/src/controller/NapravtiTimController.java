/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Communication;
import cordinator.MainCordinator;
import domen.Clan;
import domen.Tim;
import form.FrmTim;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import options.ViewOptions;

/**
 *
 * @author Folio1040
 */
public class NapravtiTimController {

    private final FrmTim frmTim;
    private Tim tim;
    private ArrayList<Tim> listaTimova;

    public NapravtiTimController(FrmTim frmTim) {
        this.frmTim = frmTim;
        tim = new Tim();
        frmTim.getBtnObrisiTim().setVisible(false);
        try {
            listaTimova = Communication.getInstance().getAllTimovi();
        } catch (Exception ex) {
            Logger.getLogger(NapravtiTimController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addActionListeners();
    }

    public void openForm(ViewOptions option) {
        prepare(option);
        if (tim.equals(new Tim())) {
            frmTim.getBtnIzmeni().setEnabled(false);
        } else {
            frmTim.getBtnIzmeni().setEnabled(true);
        }
        frmTim.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmTim.setVisible(true);
    }

    private void addActionListeners() {
        frmTim.addBtnZapamtiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frmTim.getTxtNazivTima().getText().isEmpty() || (!frmTim.getChbTimKate().isSelected() && !frmTim.getChbTimBorbe().isSelected() && !frmTim.getChbTakmicarskiTim().isSelected())){
                    JOptionPane.showMessageDialog(frmTim, "Molimo popunite sva polja", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                   saveTim(); 
                }
                if (JOptionPane.showConfirmDialog(frmTim, "Da li ste sigurni da zelite da unesete novi tim?", "Potvrda unosa tima", JOptionPane.YES_NO_OPTION) == 0) {
                    boolean postoji = false;
                    try {
                        for (Tim tim1 : Communication.getInstance().getAllTimovi()) {
                            if (tim1.getNaziv().equals(tim.getNaziv())) {
                                JOptionPane.showMessageDialog(frmTim, "Sistem ne moze da zapamti tim", "Greska", JOptionPane.ERROR_MESSAGE);
                                postoji = true;
                                return;
                            }
                        }
                        if (!postoji) {
                            Communication.getInstance().addTim(tim);
                            ArrayList<Tim> timovi = Communication.getInstance().getAllTimovi();
                            for (Tim tim1 : timovi) {
                                if (tim1.equals(tim)) {
                                    tim.setId(tim1.getTimID());
                                }
                            }
                            JOptionPane.showMessageDialog(frmTim, "Sistem je zapamtio tim");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmTim, "Niste uspesno uneli tim: " + ex.getMessage());
                    }
                }
                frmTim.dispose();
            }

            private void saveTim() {
                Tim tim1 = new Tim();
                String ime = frmTim.getTxtNazivTima().getText().trim();
                boolean timBorbe = frmTim.getChbTimBorbe().isSelected();
                boolean timKate = frmTim.getChbTimKate().isSelected();
                boolean timTakmicar = frmTim.getChbTakmicarskiTim().isSelected();
                tim1.setTimID(0);
                tim1.setNaziv(ime);
                tim1.setTimBorbe(timBorbe);
                tim1.setTimKate(timKate);
                tim1.setTimTakmicar(timTakmicar);
                setTim(tim1);
            }
        });
        frmTim.addZapamtiPostojeciTimListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMyChoice();
            }

            private void saveMyChoice() {
                if (frmTim.getCbTimovi().getSelectedItem().equals("Nov tim")) {
                    JOptionPane.showMessageDialog(frmTim, "Unesite novi tim ili izaberite postojeci");
                } else {
                    setTim((Tim) frmTim.getCbTimovi().getSelectedItem());
                    frmTim.dispose();
                }
            }
        });

        frmTim.addIzadjiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmTim.dispose();
            }
        });
        frmTim.addIzmeniListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniTim();
            }

            private void izmeniTim() {
                frmTim.getBtnObrisiTim().setVisible(true);
                frmTim.getChbTimKate().setEnabled(true);
                frmTim.getChbTimBorbe().setEnabled(true);
                frmTim.getChbTakmicarskiTim().setEnabled(true);
                frmTim.getTxtNazivTima().setEnabled(true);
                frmTim.getCbTimovi().setEnabled(true);
                frmTim.getBtnZapamti().setEnabled(true);
                frmTim.getBtnZapamtiIzbor().setEnabled(true);
            }
        });

        frmTim.addUkloniTimListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTim(new Tim());
                frmTim.dispose();
            }
        });
    }

    private void prepare(ViewOptions option) {
        fillcbTimovi();
//        if(option.equals(ViewOptions.VIEW)){
//            return;
//        }
        if (option.equals(ViewOptions.ADD)) {
            frmTim.getPanelPostojeciTim().setVisible(false);
            frmTim.getBtnIzmeni().setVisible(false);
        } else {
            frmTim.getPanelPostojeciTim().setVisible(true);
            frmTim.getBtnIzmeni().setVisible(true);
        }
        frmTim.getChbTimBorbe().setSelected(false);
        frmTim.getChbTakmicarskiTim().setSelected(false);
        frmTim.getChbTimKate().setSelected(false);
        frmTim.getTxtTimID().setText("");
        frmTim.getCbTimovi().setSelectedIndex(0);
    }

    private void fillcbTimovi() {
        frmTim.getCbTimovi().removeAllItems();
        frmTim.getCbTimovi().addItem("Nov tim");
        try {
            for (Tim tim1 : listaTimova) {
                frmTim.getCbTimovi().addItem(tim1);
            }
        } catch (Exception ex) {
            Logger.getLogger(NapravtiTimController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public void viewTim(int timID) {
        if (timID == 0) {
            setTim(new Tim());
        } else {
            fillcbTimovi();
            for (Tim tim1 : listaTimova) {
                if (tim1.getTimID() == (timID)) {
                    setTim(tim1);
                }
            }
        }
        setUpForm();
        frmTim.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmTim.setVisible(true);
    }

    private void setUpForm() {
        frmTim.getTxtTimID().setText(String.valueOf(tim.getTimID()));
        frmTim.getChbTimBorbe().setSelected(tim.isTimBorbe());
        frmTim.getChbTakmicarskiTim().setSelected(tim.isTimTakmicar());
        frmTim.getChbTimKate().setSelected(tim.isTimKate());
        frmTim.getTxtNazivTima().setText(tim.getNaziv());
        if (tim.getTimID() != 0) {
            frmTim.getCbTimovi().setSelectedItem(tim);
        } else {
            frmTim.getCbTimovi().setSelectedIndex(-1);
        }
        frmTim.getChbTimKate().setEnabled(false);
        frmTim.getChbTimBorbe().setEnabled(false);
        frmTim.getChbTakmicarskiTim().setEnabled(false);
        frmTim.getTxtNazivTima().setEnabled(false);
        frmTim.getCbTimovi().setEnabled(false);
        frmTim.getBtnZapamti().setEnabled(false);
        frmTim.getBtnZapamtiIzbor().setEnabled(false);
    }
}
