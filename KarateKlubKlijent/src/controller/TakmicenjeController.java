/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Communication;
import cordinator.MainCordinator;
import domen.Takmicenje;
import form.FrmTakmicenje;
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
public class TakmicenjeController {

    private final FrmTakmicenje frmTakmicenje;

    public TakmicenjeController(FrmTakmicenje frmTakmicenje) {
        this.frmTakmicenje = frmTakmicenje;
        addActionListeners();
    }

    public void openForm() {
        setImage();
        frmTakmicenje.getTxtDatum().setText("");
        frmTakmicenje.getTxtMesto().setText("");
        frmTakmicenje.getTxtNaziv().setText("");
        frmTakmicenje.getTxtTakmicenjeID().setText("");
        frmTakmicenje.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmTakmicenje.setVisible(true);
    }

    private void addActionListeners() {
        frmTakmicenje.addKreirajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreirajTakmicenje();
            }

            private void kreirajTakmicenje() {
                Takmicenje takmicenje = new Takmicenje();
                takmicenje.setId(0);
                takmicenje.setMesto(frmTakmicenje.getTxtMesto().getText().trim());
                takmicenje.setNaziv(frmTakmicenje.getTxtNaziv().getText().trim());
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Date datum = new Date();
                String date = frmTakmicenje.getTxtDatum().getText();
                if (frmTakmicenje.getTxtDatum().getText().isEmpty() || frmTakmicenje.getTxtMesto().getText().isEmpty() || frmTakmicenje.getTxtNaziv().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frmTakmicenje, "Morate da popunite sva polja", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    boolean postoji = false;
                    datum = sdf.parse(date);
                    System.out.println(datum);
                    if (datum.after(new Date())) {
                        takmicenje.setDatum(datum);
                    } else {
                        JOptionPane.showMessageDialog(frmTakmicenje, "Datum mora da bude datum iz buducnosti", "Neodgovarajuci datuma", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ArrayList<Takmicenje> listaTakmicenja = Communication.getInstance().getAllTakmicenja();
                    for (Takmicenje takmicenje1 : listaTakmicenja) {
                        if (takmicenje1.getDatum().equals(takmicenje.getDatum()) && takmicenje1.getMesto().equals(takmicenje.getMesto()) && takmicenje1.getNaziv().equals(takmicenje.getNaziv())) {
                            JOptionPane.showMessageDialog(frmTakmicenje, "Sistem ne moze da zapamti takmicenje", "Greska", JOptionPane.ERROR_MESSAGE);
                            postoji=true;
                            return;
                        }
                    }
                    if (!postoji && JOptionPane.showConfirmDialog(frmTakmicenje, "Da li ste sigurni da zelite da kreirate polaganje", "Polaganje", JOptionPane.YES_NO_OPTION) == 0) {
                        Communication.getInstance().addTakmicenje(takmicenje);
                        JOptionPane.showMessageDialog(frmTakmicenje, "Sistem je zapamtio takmicenje");
                        frmTakmicenje.dispose();
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frmTakmicenje, "Datum mora da bude u formatu 'dd.MM.yyyy'", "Neodgovarajuci format datuma", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmTakmicenje, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(TakmicenjeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setImage() {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/karate_PNG.png")));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(frmTakmicenje.getLblSlika().getWidth(), frmTakmicenje.getLblSlika().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        frmTakmicenje.getLblSlika().setIcon(i);
    }
}
