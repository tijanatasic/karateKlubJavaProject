/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import form.FrmAbout;
import form.FrmMain;
import form.FrmSettings;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import server.Server;

/**
 *
 * @author Folio1040
 */
public class FormController {

    private static FormController instance;
    private static FrmMain frmMain;
    private static FrmSettings frmSettings;
    private static Properties properties;
    private static FrmAbout frmAbout;
    Server server;

    private FormController() {
        properties = new Properties();
        frmSettings = new FrmSettings(frmMain, true);
        frmMain = new FrmMain();
        frmAbout = new FrmAbout(frmMain, true);
        try {
            properties.load(new FileInputStream("config/dbconfig.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        addActionListeners();
    }

    public static FormController getInstance() {
        if (instance == null) {
            instance = new FormController();
        }
        return instance;
    }

    public void openMainForm() {
        frmMain.setVisible(true);
        frmMain.getLblKonekcija().setText("Niste povezani na server");
        frmMain.getLblKonekcija().setForeground(Color.red);
    }

    private void addActionListeners() {
        frmMain.addStartServerListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server = new Server();
                server.start();
                frmMain.getBtnStartSever().setEnabled(false);
                frmMain.getBtnStopServer().setEnabled(true);
                frmMain.getLblKonekcija().setText("Povezani ste na server");
                frmMain.getLblKonekcija().setForeground(Color.decode("142259"));
            }
        });
        frmMain.addStopServerListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopServer();
                frmMain.getBtnStartSever().setEnabled(true);
                frmMain.getBtnStopServer().setEnabled(false);
                frmMain.getLblKonekcija().setText("Niste povezani na server");
                frmMain.getLblKonekcija().setForeground(Color.red);
            }
        });
        frmMain.addSettingsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareForm();
                frmSettings.setVisible(true);
            }

            private void prepareForm() {
                frmSettings.getTxtURL().setText(properties.getProperty("url"));
                frmSettings.getTxtUsername().setText(properties.getProperty("username"));
                frmSettings.getTxtPassword().setText(properties.getProperty("password"));
            }
        });
        frmMain.addAboutListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareAbout();
                frmAbout.setLocationRelativeTo(frmMain);
                frmAbout.setVisible(true);
            }

            private void prepareAbout() {
                setImage();

            }

            private void setImage() {
                ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/logo.jpg")));
                Image img1 = image.getImage();
                Image img2 = img1.getScaledInstance(frmAbout.getLblSlika().getWidth(), frmAbout.getLblSlika().getHeight(), Image.SCALE_SMOOTH);
                ImageIcon i = new ImageIcon(img2);
                frmAbout.getLblSlika().setIcon(i);
            }
        });
        frmSettings.addSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String url = frmSettings.getTxtURL().getText().trim();
                    String username = frmSettings.getTxtUsername().getText().trim();
                    String password = frmSettings.getTxtPassword().getText().trim();
                    FileOutputStream out = new FileOutputStream("config/dbconfig.properties");
                    properties.setProperty("url", url);
                    properties.setProperty("username", username);
                    properties.setProperty("password", password);
                    properties.store(out, null);
                    out.close();
                    JOptionPane.showMessageDialog(frmSettings, "Sacuvane su promene", "Promena baze podataka", JOptionPane.INFORMATION_MESSAGE);
                    frmSettings.dispose();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FrmSettings.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FrmSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
