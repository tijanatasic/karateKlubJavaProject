/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.ProcessRequests;

/**
 *
 * @author Folio1040
 */
public class Server extends Thread {

    ServerSocket ss;
    boolean stop = false;
    ArrayList<Socket> klijenti;

    @Override
    public void run() {
        klijenti = new ArrayList<>();
        try {
            ss = new ServerSocket(9000);
            System.out.println("Server je pokrenut. Ceka se klijent za konekciju...");
            while (!stop) {
                Socket soket = ss.accept();
                klijenti.add(soket);
                System.out.println("Klijent je povezan!");
                handleClient(soket);
            }
        } catch (Exception ex) {
            System.out.println("Konekcija je zatvorena: " + ex.getMessage());
        }
    }

    public void stopServer() {
        try {
            ss.close();
            stop = true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleClient(Socket soket) {
        ProcessRequests processRequests = new ProcessRequests(soket);
        processRequests.start();
    }

}
