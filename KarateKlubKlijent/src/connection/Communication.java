/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import domen.Clan;
import domen.Polaganje;
import domen.RezultatPolaganja;
import domen.StatistikaTakmicara;
import domen.Takmicar;
import domen.Takmicenje;
import domen.Tim;
import java.net.Socket;
import java.util.ArrayList;
import komunikacija.Operation;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;

/**
 *
 * @author Folio1040
 */
public class Communication {

    Socket soket;
    Sender sender;
    Receiver receiver;
    private static Communication instance;

    public Communication() throws Exception {
        soket = new Socket("localhost", 9000);
        sender = new Sender(soket);
        receiver = new Receiver(soket);
    }

    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public void addClan(Clan clan) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_CLANA, clan);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public ArrayList<Clan> getAllClanovi() throws Exception {
        Request request = new Request(Operation.UCITAJ_CLANOVE, null);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() == null) {
            return (ArrayList<Clan>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public ArrayList<Tim> getAllTimovi() throws Exception {
        Request request = new Request(Operation.UCITAJ_TIMOVE, null);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() == null) {
            return (ArrayList<Tim>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void addTim(Tim tim) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_TIM, tim);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void addTakmicar(Takmicar takmicar) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_TAKMICARA, takmicar);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void deleteClan(Clan clan) throws Exception {
        Request request = new Request(Operation.IZBRISI_CLANA, clan);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void updateClan(ArrayList<Clan> clanovi) throws Exception {
        Request request = new Request(Operation.IZMENI_CLANOVE, clanovi);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void deleteTim(Tim tim) throws Exception {
        Request request = new Request(Operation.IZBRISI_TIM, tim);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void updateTimovi(ArrayList<Tim> timovi) throws Exception {
        Request request = new Request(Operation.IZMENI_TIMOVE, timovi);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void updateClan(Clan clan) throws Exception {
        Request request = new Request(Operation.IZMENI_CLANA, clan);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void addPolaganje(Polaganje polaganje) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_POLAGANJE, polaganje);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void addTakmicenje(Takmicenje takmicenje) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_TAKMICENJE, takmicenje);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public ArrayList<Takmicar> getAllTakmicari() throws Exception {
        Request request = new Request(Operation.UCITAJ_TAKMICARE, null);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() == null) {
            return (ArrayList<Takmicar>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public ArrayList<Takmicenje> getAllTakmicenja() throws Exception {
        Request request = new Request(Operation.UCITAJ_TAKMICENJA, null);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() == null) {
            return (ArrayList<Takmicenje>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void addStatistikTakmicara(ArrayList<StatistikaTakmicara> lista) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_REZULTAT_TAKMICENJA, lista);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public ArrayList<Polaganje> getAllPolaganja() throws Exception {
        Request request = new Request(Operation.UCITAJ_POLAGANJA, null);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() == null) {
            return (ArrayList<Polaganje>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void addRezultatPolaganja(ArrayList<RezultatPolaganja> listaRezultata) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_REZULTAT_POLAGANJA, listaRezultata);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void checkTakmicari() throws Exception {
        Request request = new Request(Operation.OSVEZI_TAKMICARE, null);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public ArrayList<RezultatPolaganja> getAllRezultatePolaganja(Clan clan) throws Exception {
        Request request = new Request(Operation.UCITAJ_REZULTATE_POLAGANJA, clan);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() == null) {
            return (ArrayList<RezultatPolaganja>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public ArrayList<StatistikaTakmicara> getAllStatistikeTakmicara(Clan clan) throws Exception {
        Request request = new Request(Operation.UCITAJ_STATISTIKE_TAKMICENJA, clan);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() == null) {
            return (ArrayList<StatistikaTakmicara>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void updateTakmicari(ArrayList<Takmicar> takmicari) throws Exception {
        Request request = new Request(Operation.IZMENI_TAKMICARE, takmicari);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void updateTakmicar(Takmicar takmicar) throws Exception {
        Request request = new Request(Operation.IZMENI_TAKMICARA, takmicar);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void deleteTakmicar(Takmicar takmicar) throws Exception {
        Request request = new Request(Operation.OBRISI_TAKMICARA, takmicar);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public ArrayList<Clan> getAllClanovi(String ime) throws Exception {
        Request request = new Request(Operation.PRONADJI_CLANOVE, ime);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() == null) {
            return (ArrayList<Clan>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public ArrayList<Tim> getTimovi(String tim) throws Exception {
        Request request = new Request(Operation.PRONADJI_TIM, tim);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() == null) {
            return (ArrayList<Tim>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public Clan getClan(Clan clan) throws Exception {
        Request request = new Request(Operation.UCITAJ_IZABRANOG_CLANA, clan);
        sender.send(request);
        Response response = (Response) receiver.recieve();
        if (response.getException() == null) {
            return (Clan) response.getResult();
        } else {
            throw response.getException();
        }
    }

}
