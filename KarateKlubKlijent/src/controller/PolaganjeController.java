/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Communication;
import cordinator.MainCordinator;
import domen.Clan;
import domen.Polaganje;
import form.FrmPolaganje;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Folio1040
 */
public class PolaganjeController {

    private final FrmPolaganje frmPolaganje;
    private ArrayList<Clan> listaClanova;

    public PolaganjeController(FrmPolaganje frmPolaganje) {
        this.frmPolaganje = frmPolaganje;
        addActionListeners();
    }

    public void openForm() {
        setImage();
        frmPolaganje.getTxtDatumPolaganja().setText("");
        frmPolaganje.getTxtPolaganjeID().setText("");
        frmPolaganje.getTxtMestoPolaganja().setText("");
        frmPolaganje.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmPolaganje.setVisible(true);
    }

    private void addActionListeners() {
        frmPolaganje.addKreirajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreirajPolaganje();
            }

            private void kreirajPolaganje() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Polaganje polaganje = new Polaganje();
                polaganje.setId(0);
                polaganje.setMestoPolaganja(frmPolaganje.getTxtMestoPolaganja().getText().trim());
                Date datum = new Date();
                if (frmPolaganje.getTxtDatumPolaganja().getText().isEmpty() || frmPolaganje.getTxtMestoPolaganja().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frmPolaganje, "Molimo popunite sva polja", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    datum = sdf.parse(frmPolaganje.getTxtDatumPolaganja().getText());
                    System.out.println(datum);
                    if (datum.after(new Date())) {
                        polaganje.setDatum(datum);
                    } else {
                        JOptionPane.showMessageDialog(frmPolaganje, "Datum mora da bude datum iz buducnosti", "Neodgovarajuci datuma", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ArrayList<Polaganje> listaPolaganja = Communication.getInstance().getAllPolaganja();
                    boolean postoji = false;
                    for (Polaganje polaganje1 : listaPolaganja) {
                        if (polaganje1.getDatum().equals(polaganje.getDatum()) && polaganje1.getMestoPolaganja().equals(polaganje.getMestoPolaganja())) {
                            postoji = true;
                            JOptionPane.showMessageDialog(frmPolaganje, "Sistem ne moze da zapamti polaganje za vise zvanje", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    if (!postoji && JOptionPane.showConfirmDialog(frmPolaganje, "Da li ste sigurni da zelite da kreirate polaganje", "Polaganje", JOptionPane.YES_NO_OPTION) == 0) {
                        Communication.getInstance().addPolaganje(polaganje);
                        JOptionPane.showMessageDialog(frmPolaganje, "Sistem je zapamtio polaganje za vise zvanje");
                        frmPolaganje.dispose();
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frmPolaganje, "Datum mora da bude u formatu 'dd.MM.yyyy'", "Neodgovarajuci format datuma", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPolaganje, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(PolaganjeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void setImage() {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/polaganje.png")));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(frmPolaganje.getLblSlika().getWidth(), frmPolaganje.getLblSlika().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        frmPolaganje.getLblSlika().setIcon(i);
    }
}
