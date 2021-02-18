/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Folio1040
 */
public class Sender {
    private Socket soket;

    public Sender(Socket soket) {
        this.soket = soket;
    }
    
    public void send(Object object) throws Exception{ 
        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(soket.getOutputStream()));
            out.writeObject(object);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Doslo je do greske prilikom slanja objekta "+ex.getMessage());
        }
    }
}
