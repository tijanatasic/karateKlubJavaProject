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
import domen.Polaganje;
import domen.RezultatPolaganja;
import form.FrmRezultatiPolaganja;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Folio1040
 */
public class RezultatiPolaganjaController {

    private final FrmRezultatiPolaganja frmRezultatiPolaganja;

    public RezultatiPolaganjaController(FrmRezultatiPolaganja frmRezultatiPolaganja) {
        this.frmRezultatiPolaganja = frmRezultatiPolaganja;
        addActionListeners();
    }

    public void openForm() {
        setImage();
        try {
            fillcbClanovi();
            fillcbPojas();
            fillcbPolozio();
            fillcbPolaganje();
        } catch (Exception e) {
            e.printStackTrace();
        }
        frmRezultatiPolaganja.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmRezultatiPolaganja.setVisible(true);
    }

    private void addActionListeners() {
        frmRezultatiPolaganja.addZapamatiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRezultatPolaganja();
            }

            private void addRezultatPolaganja() {
                Clan clan = (Clan) frmRezultatiPolaganja.getCbClanovi().getSelectedItem();
                Polaganje polaganje = (Polaganje) frmRezultatiPolaganja.getCbPolaganja().getSelectedItem();
                Pojas pojas = (Pojas) frmRezultatiPolaganja.getCbPojas().getSelectedItem();
                boolean uspeh = false;
                if (frmRezultatiPolaganja.getCbPolozio().getSelectedItem().equals("DA")) {
                    uspeh = true;
                } else {
                    uspeh = false;
                }
                if (pojas.compareTo(clan.getPojas()) == 1) {
                    if (JOptionPane.showConfirmDialog(frmRezultatiPolaganja, "Da li ste sigurni da zelite da upamtite rezultat polaganja?", "Polaganje", JOptionPane.YES_NO_OPTION) == 0) {
                        try {
                            RezultatPolaganja rezultat = new RezultatPolaganja(clan, polaganje, uspeh, pojas);
                            boolean izlazio = false;
                            ArrayList<RezultatPolaganja> rezultati = Communication.getInstance().getAllRezultatePolaganja(clan);
                            for (RezultatPolaganja rezultatPolaganja : rezultati) {
                                if (rezultat.getClanID().getClanID() == rezultatPolaganja.getClanID().getClanID() && rezultat.getPolaganjeID().getPolaganjeID() == rezultatPolaganja.getPolaganjeID().getPolaganjeID()) {
                                    JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Sistem ne moze da zapamti rezultat polaganja za vise zvanje", "Greska", JOptionPane.ERROR_MESSAGE);
                                    izlazio = true;
                                    return;
                                }
                            }
                            if (!izlazio) {
                                clan.setPojas(pojas);
                                Communication.getInstance().addRezultatPolaganja(rezultat);
                                JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Sistem je zapamtio rezultat polaganja za vise zvanje");
                                frmRezultatiPolaganja.dispose();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(RezultatiPolaganjaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Moguce je polagati samo za pojas koji je za jedan veci od trenutnog", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void fillcbClanovi() throws Exception {
        frmRezultatiPolaganja.getCbClanovi().removeAllItems();
        ArrayList<Clan> clanovi = Communication.getInstance().getAllClanovi();
        for (Clan clan : clanovi) {
            frmRezultatiPolaganja.getCbClanovi().addItem(clan);
        }
    }

    private void fillcbPojas() {
        frmRezultatiPolaganja.getCbPojas().removeAllItems();
        for (Pojas pojas : Pojas.values()) {
            frmRezultatiPolaganja.getCbPojas().addItem(pojas);
        }
    }

    private void fillcbPolozio() {
        frmRezultatiPolaganja.getCbPolozio().removeAllItems();
        frmRezultatiPolaganja.getCbPolozio().addItem("DA");
        frmRezultatiPolaganja.getCbPolozio().addItem("NE");
    }

    private void fillcbPolaganje() throws Exception {
        frmRezultatiPolaganja.getCbPolaganja().removeAllItems();
        ArrayList<Polaganje> lista = Communication.getInstance().getAllPolaganja();
        for (Polaganje polaganje : lista) {
            frmRezultatiPolaganja.getCbPolaganja().addItem(polaganje);
        }
    }

    private void setImage() {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/polaganje.png")));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(frmRezultatiPolaganja.getLblSlika().getWidth(), frmRezultatiPolaganja.getLblSlika().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        frmRezultatiPolaganja.getLblSlika().setIcon(i);
    }
}
