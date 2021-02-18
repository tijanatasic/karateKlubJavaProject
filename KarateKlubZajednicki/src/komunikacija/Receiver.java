/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Folio1040
 */
public class Receiver {
    
    private Socket soket;

    public Receiver(Socket soket) {
        this.soket = soket;
    }
    
    public Object recieve() throws Exception{
        try {
            ObjectInputStream in = new ObjectInputStream(soket.getInputStream());
            return in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom primanja objekta "+ex.getMessage());
        }
    }
    
    
}
