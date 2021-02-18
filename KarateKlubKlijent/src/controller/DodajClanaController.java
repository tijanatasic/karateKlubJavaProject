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
import domen.Tim;
import form.FrmDodajClana;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import options.ViewOptions;

/**
 *
 * @author Folio1040
 */
public class DodajClanaController {

    private final FrmDodajClana frmDodajClana;
    private Tim tim;
    private Clan clan;

    public DodajClanaController(FrmDodajClana frmDodajClana) {
        this.frmDodajClana = frmDodajClana;
        tim = new Tim();
        clan = new Clan();
        addActionListeners();
    }

    public void openFormAdd() {
        setImage();
        fillEmptyForm();
        frmDodajClana.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmDodajClana.setVisible(true);
    }

    private void fillEmptyForm() {
        frmDodajClana.getTxtClanID().setText("");
        frmDodajClana.getTxtIme().setText("");
        frmDodajClana.getTxtJMBG().setText("");
        frmDodajClana.getTxtPrezime().setText("");
        frmDodajClana.getBtnDodaj().setVisible(true);
        frmDodajClana.getBtnIzmeni().setVisible(false);
        frmDodajClana.getBtnObrisi().setVisible(false);
        frmDodajClana.getBtnSacuvajIzmene().setVisible(false);
        frmDodajClana.getBtnPregledaj().setEnabled(false);
        fillCbPojas();
    }

    private void fillCbPojas() {
        frmDodajClana.getCbPojas().removeAllItems();
        for (Pojas pojas : Pojas.values()) {
            frmDodajClana.getCbPojas().addItem(pojas);
        }

    }

    private void addActionListeners() {
        frmDodajClana.addBtnDodajTimListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTim(MainCordinator.getInstance().openTim(ViewOptions.VIEW));
                frmDodajClana.getBtnDodajTim().setEnabled(false);
                frmDodajClana.getBtnPregledaj().setEnabled(true);
            }
        });
        frmDodajClana.addDodajBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    if (frmDodajClana.getTxtIme().getText().isEmpty() || frmDodajClana.getTxtJMBG().getText().isEmpty() || frmDodajClana.getTxtPrezime().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frmDodajClana, "Molimo unesite sve podatke", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    clan.setClanID(0);
                    clan.setIme(frmDodajClana.getTxtIme().getText().trim());
                    clan.setPrezime(frmDodajClana.getTxtPrezime().getText().trim());
                    clan.setJMBG(Long.parseLong(frmDodajClana.getTxtJMBG().getText().trim()));
                    clan.setPojas((Pojas) frmDodajClana.getCbPojas().getSelectedItem());
                    if (tim.getTimID() == 0) {
                        tim.setTimID(-1);
                    } else {
                        tim.setTimID(0);
                    }
                    clan.setTim(tim);
                    setClan(clan);
                    boolean postoji = false;
                    if (JOptionPane.showConfirmDialog(frmDodajClana, "Da li ste sigurni da zelite da unesete novog clana?", "Potvrda unosa clana", JOptionPane.YES_NO_OPTION) == 0) {
                        ArrayList<Clan> clanovi = Communication.getInstance().getAllClanovi();
                        for (Clan clan1 : clanovi) {
                            if (clan1.getJMBG() == clan.getJMBG()) {
                                JOptionPane.showMessageDialog(frmDodajClana, "Sistem ne moze da zapamti clana", "Greska", JOptionPane.ERROR_MESSAGE);
                                postoji = true;
                                return;
                            }
                        }
                        if (!postoji) {
                            Communication.getInstance().addClan(clan);
                            JOptionPane.showMessageDialog(frmDodajClana, "Sistem je zapamtio clana");
                            frmDodajClana.dispose();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DodajClanaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        frmDodajClana.addPregledajTimListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTim(MainCordinator.getInstance().viewTim(getTim().getTimID()));
            }
        });
        frmDodajClana.addIzmeniListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSetUp();
            }

            private void changeSetUp() {
                frmDodajClana.getBtnIzmeni().setEnabled(false);
                frmDodajClana.getTxtIme().setEnabled(true);
                frmDodajClana.getTxtPrezime().setEnabled(true);
                frmDodajClana.getTxtJMBG().setEnabled(true);
                frmDodajClana.getBtnSacuvajIzmene().setEnabled(true);
                frmDodajClana.getCbPojas().setEnabled(true);
                if (frmDodajClana.getBtnDodajTim().isVisible()) {
                    frmDodajClana.getBtnDodajTim().setEnabled(true);
                } else {
                    frmDodajClana.getBtnPregledaj().setEnabled(true);
                }
            }
        });
        frmDodajClana.addDeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frmDodajClana, "Da li ste sigurni da zelite da obriste clana?", "Brisanje", JOptionPane.YES_NO_OPTION) == 0) {
                    try {
                        Communication.getInstance().deleteClan(clan);
                        JOptionPane.showMessageDialog(frmDodajClana, "Uspesno ste izbrisali clana");
                        frmDodajClana.dispose();
                    } catch (Exception ex) {
                        Logger.getLogger(DodajClanaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        frmDodajClana.addUpdateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClan();
            }

            private void updateClan() {
                try {
                    if (frmDodajClana.getTxtIme().getText().isEmpty() || frmDodajClana.getTxtJMBG().getText().isEmpty() || frmDodajClana.getTxtPrezime().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frmDodajClana, "Sistem ne moze da zapamti clana", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    getClanParameters();
                    if (JOptionPane.showConfirmDialog(frmDodajClana, "Da li ste sigurni da zelite da izmenite clana?", "Potvrda izmene clana", JOptionPane.YES_NO_OPTION) == 0) {
                        Communication.getInstance().updateClan(clan);
                        JOptionPane.showMessageDialog(frmDodajClana, "Sistem je zapamtio clana");
                        frmDodajClana.dispose();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DodajClanaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void getClanParameters() {
                clan.setClanID(Integer.parseInt(frmDodajClana.getTxtClanID().getText().trim()));
                clan.setIme(frmDodajClana.getTxtIme().getText().trim());
                clan.setPrezime(frmDodajClana.getTxtPrezime().getText().trim());
                clan.setJMBG(Long.parseLong(frmDodajClana.getTxtJMBG().getText().trim()));
                clan.setPojas((Pojas) frmDodajClana.getCbPojas().getSelectedItem());
                clan.setTim(tim);
            }
        });
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public void viewClan(Clan clan) {
        setImage();
        fillEmptyForm();
        setClan(clan);
        setTim(clan.getTim());
        if (clan.getTim().getTimID() != 0) {
            frmDodajClana.getBtnDodajTim().setVisible(false);
            frmDodajClana.getBtnPregledaj().setVisible(true);
            frmDodajClana.getBtnPregledaj().setEnabled(false);
        } else {
            frmDodajClana.getBtnDodajTim().setVisible(true);
            frmDodajClana.getBtnPregledaj().setVisible(false);
            frmDodajClana.getBtnDodajTim().setEnabled(false);
        }
        frmDodajClana.getBtnDodaj().setVisible(false);
        frmDodajClana.getBtnIzmeni().setVisible(true);
        frmDodajClana.getBtnObrisi().setVisible(true);
        frmDodajClana.getBtnSacuvajIzmene().setVisible(true);
        frmDodajClana.getTxtClanID().setEnabled(false);
        frmDodajClana.getTxtIme().setEnabled(false);
        frmDodajClana.getTxtPrezime().setEnabled(false);
        frmDodajClana.getTxtJMBG().setEnabled(false);
        frmDodajClana.getCbPojas().setEnabled(false);
        frmDodajClana.getTxtClanID().setText(String.valueOf(clan.getClanID()));
        frmDodajClana.getTxtIme().setText(clan.getIme());
        frmDodajClana.getTxtPrezime().setText(clan.getPrezime());
        frmDodajClana.getTxtJMBG().setText(String.valueOf(clan.getJMBG()));
        frmDodajClana.getCbPojas().setSelectedItem(clan.getPojas());
        frmDodajClana.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmDodajClana.setVisible(true);
        addActionListeners();
    }

    private void setImage() {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/karate1.png")));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(frmDodajClana.getLblPozadina().getWidth(), frmDodajClana.getLblPozadina().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        frmDodajClana.getLblPozadina().setIcon(i);
    }

}
