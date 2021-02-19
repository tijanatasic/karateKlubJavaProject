/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cordinator.MainCordinator;
import form.FrmMain;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import options.ViewOptions;

/**
 *
 * @author Folio1040
 */
public class MainController {

    private final FrmMain frmMain;

    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

    private void addActionListener() {
        frmMain.getJmiKreirajClana().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openKreirajClana();
            }
        });

        frmMain.getJmiPrikaziSve().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openPrikaziSveClanove();
            }
        });

        frmMain.getJmiKreirajTim().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openTim(ViewOptions.ADD);
            }
        });

        frmMain.getJmiKreirajTakmicara().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openTakmicar();
            }
        });
        frmMain.getJmiPogledajSveTimove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openViewAllTimove();
            }
        });
        frmMain.getJmiKreirajPolaganje().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openKreirajPolaganje();
            }
        });
        frmMain.getJmiKreirajTakmicenje().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openKreirajTakmicenje();
            }
        });
        frmMain.getJmiRezultatPolaganja().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openRezultatPolaganja();
            }
        });
        frmMain.getJmiRezultatTakmicenja().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openRezultatTakmicenja();
            }
        });
        frmMain.getJmiStatistike().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openStatistike();
            }
        });
        frmMain.getJmiPrikaziTakmicare().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openTakmicari();
            }
        });

    }

    public void openForm() {
        frmMain.setVisible(true);
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/logo.jpg")));
        Image img1 = image.getImage();
        ImageIcon i = new ImageIcon(img1);
        frmMain.getLblPozadina().setIcon(i);
    }

}
