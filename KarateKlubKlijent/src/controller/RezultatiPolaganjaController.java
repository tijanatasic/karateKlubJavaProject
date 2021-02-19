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
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import model.ModelStatistikaPolaganja;

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
        prepareTable();
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
                ModelStatistikaPolaganja model = (ModelStatistikaPolaganja) frmRezultatiPolaganja.getTblRezultati().getModel();
                ArrayList<RezultatPolaganja> listaRezultata = model.getLista();
                if (JOptionPane.showConfirmDialog(frmRezultatiPolaganja, "Da li ste sigurni da zelite da upamtite rezultat polaganja?", "Polaganje", JOptionPane.YES_NO_OPTION) == 0) {
                    try {
                        boolean izlazio = false;
                        for (RezultatPolaganja rezultat : listaRezultata) {
                            ArrayList<RezultatPolaganja> rezultati = Communication.getInstance().getAllRezultatePolaganja(rezultat.getClanID());
                            for (RezultatPolaganja rezultatPolaganja : rezultati) {
                                if (rezultat.getClanID().getClanID() == rezultatPolaganja.getClanID().getClanID() && rezultat.getPolaganjeID().getPolaganjeID() == rezultatPolaganja.getPolaganjeID().getPolaganjeID()) {
                                    izlazio = true;
                                    izlazio();
                                    return;
                                }
                            }
                        }
                        if (!izlazio) {
                            Communication.getInstance().addRezultatPolaganja(listaRezultata);
                            JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Sistem je zapamtio rezultate polaganja za vise zvanje");
                            frmRezultatiPolaganja.dispose();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(RezultatiPolaganjaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            private void izlazio() {
                JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Sistem ne moze da zapamti rezultat polaganja za vise zvanje", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        });
        frmRezultatiPolaganja.addUnesiUtabeluListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    RezultatPolaganja rezultat = new RezultatPolaganja(clan, polaganje, uspeh, pojas);
                    ModelStatistikaPolaganja model = (ModelStatistikaPolaganja) frmRezultatiPolaganja.getTblRezultati().getModel();
                    ArrayList<RezultatPolaganja> rezultati = model.getLista();
                    for (RezultatPolaganja rezultatPolaganja : rezultati) {
                        if (rezultatPolaganja.getClanID().equals(rezultat.getClanID()) && rezultatPolaganja.getPojas() == rezultat.getPojas() && rezultatPolaganja.isPolozio()) {
                            JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Clan je vec polozio za dati pojas", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(rezultatPolaganja.getClanID().equals(rezultat.getClanID()) && rezultatPolaganja.getPolaganjeID().equals(rezultat.getPolaganjeID())){
                            JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Clan je vec izlazio na dato polaganje", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    if (!rezultati.contains(rezultat)) {
                        model.add(rezultat);
                    } else {
                        JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Vec ste uneli rezultat polaganja za datog clana", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Moguce je polagati samo za pojas koji je za jedan veci od trenutnog", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frmRezultatiPolaganja.addObrisiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=frmRezultatiPolaganja.getTblRezultati().getSelectedRow();
                if(row==-1){
                    JOptionPane.showMessageDialog(frmRezultatiPolaganja, "Izaberite rezultat polaganja koji zelite da obrisete", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelStatistikaPolaganja model=(ModelStatistikaPolaganja) frmRezultatiPolaganja.getTblRezultati().getModel();
                model.delete(row);
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

    private void prepareTable() {
        ModelStatistikaPolaganja model = new ModelStatistikaPolaganja(new ArrayList<>());
        frmRezultatiPolaganja.getTblRezultati().setModel(model);
        JTableHeader header = frmRezultatiPolaganja.getTblRezultati().getTableHeader();
        header.setBackground(new java.awt.Color(39, 45, 87));
        header.setForeground(java.awt.Color.WHITE);
        header.setFont(new Font("Segoa UI", Font.BOLD, 16));
        frmRezultatiPolaganja.getTblRezultati().setRowHeight(20);
    }
}
