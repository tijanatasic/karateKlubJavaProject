/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Communication;
import cordinator.MainCordinator;
import domen.Clan;
import domen.Kategorija;
import domen.Takmicar;
import domen.Tim;
import form.FrmTakmicar;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.ModelTimovi;
import options.ViewOptions;

/**
 *
 * @author Folio1040
 */
public class NapraviTakmicaraController {

    private final FrmTakmicar frmTakmicar;
    private Tim tim;
    private Clan clan;
    private Takmicar takmicar;

    public NapraviTakmicaraController(FrmTakmicar frmTakmicar) {
        this.frmTakmicar = frmTakmicar;
        this.takmicar = new Takmicar();
        this.tim = null;
        this.clan = new Clan();
        addActionListener();
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public void openTakmicar() {
        frmTakmicar.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        prepare();
        frmTakmicar.setVisible(true);
    }

    private void prepare() {
        setImage();
        fillcbKategorije();
        fillcbClanoci();
        frmTakmicar.getTxtTakmicarID().setText("");
        frmTakmicar.getBtnKreiraj().setVisible(false);
        frmTakmicar.getPanelDalje().setVisible(false);
        frmTakmicar.getPanelTimova().setVisible(false);
    }

    private void fillcbKategorije() {
        frmTakmicar.getCbKategorija().removeAllItems();
        for (Object kategorija : Kategorija.values()) {
            frmTakmicar.getCbKategorija().addItem(kategorija);
        }
    }

    private void fillcbClanoci() {
        frmTakmicar.getCbClanovi().removeAllItems();
        try {
            ArrayList<Clan> listaClanova = Communication.getInstance().getAllClanovi();
            for (Clan clan : listaClanova) {
                frmTakmicar.getCbClanovi().addItem(clan);
            }
        } catch (Exception ex) {
            Logger.getLogger(NapraviTakmicaraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addActionListener() {
        frmTakmicar.addDaljeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clan clan = (Clan) frmTakmicar.getCbClanovi().getSelectedItem();
                setClan(clan);
                try {
                    ArrayList<Tim> listaTimova = Communication.getInstance().getAllTimovi();
                    for (Tim tim1 : listaTimova) {
                        if (tim1.getTimID() == clan.getTim().getTimID()) {
                            clan.setTim(tim1);
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(NapraviTakmicaraController.class.getName()).log(Level.SEVERE, null, ex);
                }
                frmTakmicar.getPanelDalje().setVisible(true);
                frmTakmicar.getChbPojedinacno().setSelected(false);
                frmTakmicar.getChbTimski().setSelected(false);
                frmTakmicar.getPanelTimova().setVisible(false);
            }
        });
        frmTakmicar.addTimsliListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareTim();
            }

            private void prepareTim() {
                System.out.println(clan.getTim());
                if (clan.getTim().getTimID() == 0) {
                    JOptionPane.showMessageDialog(frmTakmicar, "Dati clan se ne nalazi ni u jednom timu");
                } else if (!clan.getTim().isTimTakmicar()) {
                    JOptionPane.showMessageDialog(frmTakmicar, "Tim u kom se nalazi clan nije takmicarski");
                } else {
                    ArrayList<Tim> listaTimova = null;
                    try {
                        listaTimova = Communication.getInstance().getAllTimovi();
                    } catch (Exception ex) {
                        Logger.getLogger(NapraviTakmicaraController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (Tim tim1 : listaTimova) {
                        if (tim1.getTimID() == clan.getTim().getTimID()) {
                            setTim(tim1);
                        }
                    }
                    frmTakmicar.getBtnKreiraj().setVisible(true);
                    frmTakmicar.getPanelTimova().setVisible(true);
                    frmTakmicar.getTxtTimTakmicara().setText(tim.getNaziv());
                }
            }
        });
        frmTakmicar.addPojedinacnoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmTakmicar.getChbPojedinacno().setVisible(true);
                frmTakmicar.getChbPojedinacno().setSelected(true);
                frmTakmicar.getBtnKreiraj().setVisible(true);
            }
        });
        frmTakmicar.addIzaberiTimListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmTakmicar.getChbTimski().setSelected(true);

            }
        });
        frmTakmicar.addKreirajTakmicaraListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTakmicar();
            }

            private void createTakmicar() {
                takmicar.setClanID(clan);
                takmicar.setTimID(tim);
                takmicar.setKategorija((Kategorija) frmTakmicar.getCbKategorija().getSelectedItem());
                takmicar.setPojedinacno(frmTakmicar.getChbPojedinacno().isSelected());
                takmicar.setTim(frmTakmicar.getChbTimski().isSelected());
                boolean postoji = false;
                try {
                    ArrayList<Takmicar> listaTakmicara = Communication.getInstance().getAllTakmicari();
                    for (Takmicar takmicar1 : listaTakmicara) {
                        if (takmicar1.getClanID().getClanID() == takmicar.getClanID().getClanID()) {
                            JOptionPane.showMessageDialog(frmTakmicar, "Sistem ne moze da zapamti takmicara", "Greska", JOptionPane.ERROR_MESSAGE);
                            postoji = true;
                            return;
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(NapraviTakmicaraController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!postoji && JOptionPane.showConfirmDialog(frmTakmicar, "Da li ste sigurni da zelite da kreirate takmicara", "Takmicar", JOptionPane.YES_NO_OPTION) == 0) {
                    try {
                        Communication.getInstance().addTakmicar(takmicar);
                        JOptionPane.showMessageDialog(frmTakmicar, "Sistem je zapamtio takmicara");
                        frmTakmicar.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmTakmicar, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(NapraviTakmicaraController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private void setImage() {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/karate_PNG.png")));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(frmTakmicar.getLblSlika().getWidth(), frmTakmicar.getLblSlika().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        frmTakmicar.getLblSlika().setIcon(i);
    }
}
