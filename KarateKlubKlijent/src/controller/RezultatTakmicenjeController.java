/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Communication;
import cordinator.MainCordinator;
import domen.Clan;
import domen.StatistikaTakmicara;
import domen.Takmicar;
import domen.Takmicenje;
import form.FrmRezultatTakmicenja;
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
import javax.swing.table.TableModel;
import model.ModelStatistikaTakmicenja;
import model.ModelTakmicari;

/**
 *
 * @author Folio1040
 */
public class RezultatTakmicenjeController {

    private final FrmRezultatTakmicenja frmRezultatTakmicenja;
    private Takmicar takmicar;

    public RezultatTakmicenjeController(FrmRezultatTakmicenja frmRezultatTakmicenja) {
        this.frmRezultatTakmicenja = frmRezultatTakmicenja;
        addActionListener();
    }

    public void openForm() {
        prepare();
        frmRezultatTakmicenja.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmRezultatTakmicenja.setVisible(true);
    }

    private void fillcbTakmicari() {
        frmRezultatTakmicenja.getCbTakmicari().removeAllItems();
        try {
            ArrayList<Takmicar> listaTakmicara = Communication.getInstance().getAllTakmicari();
            for (Takmicar takmicar : listaTakmicara) {
                frmRezultatTakmicenja.getCbTakmicari().addItem(takmicar);
            }
        } catch (Exception ex) {
            Logger.getLogger(RezultatTakmicenjeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillcbTakmicenja() {
        frmRezultatTakmicenja.getCbTakmicenja().removeAllItems();
        try {
            ArrayList<Takmicenje> listaTakmicenja = Communication.getInstance().getAllTakmicenja();
            for (Takmicenje takmicenje : listaTakmicenja) {
                frmRezultatTakmicenja.getCbTakmicenja().addItem(takmicenje);
            }
        } catch (Exception ex) {
            Logger.getLogger(RezultatTakmicenjeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addActionListener() {
        frmRezultatTakmicenja.addZapamtiIzborListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Takmicar Izabrantakmicar = (Takmicar) frmRezultatTakmicenja.getCbTakmicari().getSelectedItem();
                setTakmicar(Izabrantakmicar);
                frmRezultatTakmicenja.getPanelTim().setVisible(true);
                if (getTakmicar().isPojedinacno() && getTakmicar().isTim()) {
                    frmRezultatTakmicenja.getLblPojedinacno().setVisible(true);
                    frmRezultatTakmicenja.getChbPojedinacno().setVisible(true);
                    frmRezultatTakmicenja.getChbPojedinacno().setSelected(false);
                    frmRezultatTakmicenja.getLblTim().setVisible(true);
                    frmRezultatTakmicenja.getChbTim().setVisible(true);
                    frmRezultatTakmicenja.getChbTim().setSelected(false);
                } else if (getTakmicar().isPojedinacno() && !getTakmicar().isTim()) {
                    frmRezultatTakmicenja.getLblPojedinacno().setVisible(true);
                    frmRezultatTakmicenja.getChbPojedinacno().setVisible(true);
                    frmRezultatTakmicenja.getChbPojedinacno().setSelected(false);
                    frmRezultatTakmicenja.getLblTim().setVisible(false);
                    frmRezultatTakmicenja.getChbTim().setVisible(false);
                    frmRezultatTakmicenja.getChbTim().setSelected(false);
                } else if (!getTakmicar().isPojedinacno() && getTakmicar().isTim()) {
                    frmRezultatTakmicenja.getLblPojedinacno().setVisible(false);
                    frmRezultatTakmicenja.getChbPojedinacno().setVisible(false);
                    frmRezultatTakmicenja.getChbPojedinacno().setSelected(false);
                    frmRezultatTakmicenja.getLblTim().setVisible(true);
                    frmRezultatTakmicenja.getChbTim().setVisible(true);
                    frmRezultatTakmicenja.getChbTim().setSelected(false);
                }
                frmRezultatTakmicenja.getBtnZapamtiRezultat().setVisible(true);
            }
        });
        frmRezultatTakmicenja.addZapamtiRezultat(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ModelStatistikaTakmicenja model = (ModelStatistikaTakmicenja) frmRezultatTakmicenja.getTblRezultati().getModel();
                    ArrayList<StatistikaTakmicara> lista = model.getLista();
                    if (JOptionPane.showConfirmDialog(frmRezultatTakmicenja, "Da li ste sigurni da zelite da unesete rezultate takmicenja", "Rezultati takmicenja", JOptionPane.YES_NO_OPTION) == 0) {
                        boolean izlazio = false;
                        for (StatistikaTakmicara statistika : lista) {
                            Clan clan = statistika.getTakmicarID().getClanID();
                            ArrayList<StatistikaTakmicara> statistike = Communication.getInstance().getAllStatistikeTakmicara(clan);
                            for (StatistikaTakmicara statistikaTakmicara : statistike) {
                                if (statistika.getTakmicarID().getTakmicarID() == statistikaTakmicara.getTakmicarID().getTakmicarID() && statistika.getTakmicenjeID().getTakmicenjeID() == statistikaTakmicara.getTakmicenjeID().getTakmicenjeID()) {
                                    izlazio = true;
                                    izlazio();
                                    return;
                                }
                            }
                        }
                        if (!izlazio) {
                            Communication.getInstance().addStatistikTakmicara(lista);
                            JOptionPane.showMessageDialog(frmRezultatTakmicenja, "Sistem je zapamtio rezultat takmicenja");
                            frmRezultatTakmicenja.dispose();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmRezultatTakmicenja, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(RezultatTakmicenjeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void izlazio() {
                JOptionPane.showMessageDialog(frmRezultatTakmicenja, "Sistem ne moze da zapamti rezultat takmicenja", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        });

        frmRezultatTakmicenja.addUnesiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatistikaTakmicara statistika = new StatistikaTakmicara();
                statistika.setTakmicarID(getTakmicar());
                if (Integer.parseInt(frmRezultatTakmicenja.getTxtPoeni().getText().trim()) < 0) {
                    JOptionPane.showMessageDialog(frmRezultatTakmicenja, "Poeni ne smeju biti negativni", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                statistika.setBrojPoena(Integer.parseInt(frmRezultatTakmicenja.getTxtPoeni().getText().trim()));
                statistika.setPlasman(frmRezultatTakmicenja.getTxtPlasman().getText().trim());
                statistika.setTakmicenjeID((Takmicenje) frmRezultatTakmicenja.getCbTakmicenja().getSelectedItem());
                ModelStatistikaTakmicenja model = (ModelStatistikaTakmicenja) frmRezultatTakmicenja.getTblRezultati().getModel();
                if (!frmRezultatTakmicenja.getChbPojedinacno().isSelected() && !frmRezultatTakmicenja.getChbTim().isSelected()) {
                    JOptionPane.showMessageDialog(frmRezultatTakmicenja, "Morate da izaberete tim ili pojedinacno", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!model.getLista().contains(statistika)) {
                    model.add(statistika);
                } else {
                    JOptionPane.showMessageDialog(frmRezultatTakmicenja, "Vec ste uneli rezultat za datog takmicara", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public Takmicar getTakmicar() {
        return takmicar;
    }

    public void setTakmicar(Takmicar takmicar) {
        this.takmicar = takmicar;
    }

    private void prepare() {
        setImage();
        prepareTable();
        fillcbTakmicari();
        fillcbTakmicenja();
        ModelStatistikaTakmicenja model = new ModelStatistikaTakmicenja(new ArrayList());
        frmRezultatTakmicenja.getTblRezultati().setModel(model);
        frmRezultatTakmicenja.getPanelTim().setVisible(false);
        frmRezultatTakmicenja.getBtnZapamtiRezultat().setVisible(false);
        frmRezultatTakmicenja.getTxtPlasman().setText("");
        frmRezultatTakmicenja.getTxtPoeni().setText("");
        frmRezultatTakmicenja.getChbTim().setVisible(false);
        frmRezultatTakmicenja.getChbPojedinacno().setVisible(false);
        frmRezultatTakmicenja.getLblTim().setVisible(false);
        frmRezultatTakmicenja.getLblPojedinacno().setVisible(true);
    }

    private void setImage() {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/karate_PNG.png")));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(frmRezultatTakmicenja.getLblSlika().getWidth(), frmRezultatTakmicenja.getLblSlika().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        frmRezultatTakmicenja.getLblSlika().setIcon(i);
    }

    private void prepareTable() {
        JTableHeader header = frmRezultatTakmicenja.getTblRezultati().getTableHeader();
        header.setBackground(new java.awt.Color(39, 45, 87));
        header.setForeground(java.awt.Color.WHITE);
        header.setFont(new Font("Segoa UI", Font.BOLD, 16));
        frmRezultatTakmicenja.getTblRezultati().setRowHeight(20);
    }

}
