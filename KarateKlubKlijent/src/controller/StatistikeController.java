/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Communication;
import cordinator.MainCordinator;
import domen.Clan;
import domen.RezultatPolaganja;
import domen.StatistikaTakmicara;
import form.FrmStatistike;
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
import model.ModelStatistikaTakmicenja;

/**
 *
 * @author Folio1040
 */
public class StatistikeController {

    private final FrmStatistike frmStatistike;
    private Clan clan;

    public StatistikeController(FrmStatistike frmStatistike) {
        this.frmStatistike = frmStatistike;
        addActionListener();
    }

    public void openForm() {
        fillcbClanovi();
        setImage();
        frmStatistike.getPanelPolaganja().setVisible(false);
        frmStatistike.getPanelTakmicenja().setVisible(false);
        frmStatistike.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        frmStatistike.setVisible(true);
    }

    private void fillcbClanovi() {
        frmStatistike.getCbClanovi().removeAllItems();
        try {
            ArrayList<Clan> listaClanova = Communication.getInstance().getAllClanovi();
            for (Clan clan : listaClanova) {
                frmStatistike.getCbClanovi().addItem(clan);
            }
        } catch (Exception ex) {
            Logger.getLogger(StatistikeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addActionListener() {
        frmStatistike.addPolaganjeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmStatistike.getPanelPolaganja().setVisible(false);
                frmStatistike.getPanelTakmicenja().setVisible(false);
                setClan((Clan) frmStatistike.getCbClanovi().getSelectedItem());
                try {
                    ArrayList<RezultatPolaganja> listaPolaganja = Communication.getInstance().getAllRezultatePolaganja(clan);
                    if (listaPolaganja.isEmpty()) {
                        JOptionPane.showMessageDialog(frmStatistike, "Clan i dalje nije izlazio na polaganja", "Statistika polaganja", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        ModelStatistikaPolaganja model = new ModelStatistikaPolaganja(listaPolaganja);
                        frmStatistike.getTblPolaganja().setModel(model);
                        setTable();
                        frmStatistike.getPanelPolaganja().setVisible(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(StatistikeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void setTable() {
                JTableHeader header = frmStatistike.getTblPolaganja().getTableHeader();
                header.setBackground(new java.awt.Color(39, 45, 87));
                header.setForeground(java.awt.Color.WHITE);
                header.setFont(new Font("Segoa UI", Font.BOLD, 16));
                frmStatistike.getTblPolaganja().setRowHeight(20);
            }
        });
        frmStatistike.addTakmicenjeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmStatistike.getPanelPolaganja().setVisible(false);
                frmStatistike.getPanelTakmicenja().setVisible(false);
                setClan((Clan) frmStatistike.getCbClanovi().getSelectedItem());
                try {
                    ArrayList<StatistikaTakmicara> listaStatistika = Communication.getInstance().getAllStatistikeTakmicara(clan);
                    if (listaStatistika.isEmpty()) {
                        JOptionPane.showMessageDialog(frmStatistike, "Clan i dalje nije izlazio na takmicenja", "Statistika takmicenja", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        ModelStatistikaTakmicenja model = new ModelStatistikaTakmicenja(listaStatistika);
                        frmStatistike.getTblTakmicenja().setModel(model);
                        setTable();
                        frmStatistike.getPanelTakmicenja().setVisible(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(StatistikeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            private void setTable() {
                JTableHeader header = frmStatistike.getTblTakmicenja().getTableHeader();
                header.setBackground(new java.awt.Color(39, 45, 87));
                header.setForeground(java.awt.Color.WHITE);
                header.setFont(new Font("Segoa UI", Font.BOLD, 16));
                frmStatistike.getTblTakmicenja().setRowHeight(20);
            }
        });
    }
    
    
    private void setImage() {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/karate1.png")));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(frmStatistike.getLblPozadina().getWidth(), frmStatistike.getLblPozadina().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        frmStatistike.getLblPozadina().setIcon(i);
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

}
