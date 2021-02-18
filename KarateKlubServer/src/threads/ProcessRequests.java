/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import controller.Controller;
import domen.Clan;
import domen.Polaganje;
import domen.RezultatPolaganja;
import domen.StatistikaTakmicara;
import domen.Takmicar;
import domen.Takmicenje;
import domen.Tim;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;

/**
 *
 * @author Folio1040
 */
public class ProcessRequests extends Thread {

    Socket soket;
    Sender sender;
    Receiver receiver;

    public ProcessRequests(Socket soket) {
        this.soket = soket;
        this.sender = new Sender(soket);
        this.receiver = new Receiver(soket);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = (Request) receiver.recieve();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        case ZAPAMTI_CLANA:
                            Clan clan = (Clan) request.getArgument();
                            Controller.getInstance().addClan(clan);
                            break;
                        case UCITAJ_CLANOVE:
                            response.setResult(Controller.getInstance().getAllClanovi());
                            break;
                        case UCITAJ_TIMOVE:
                            response.setResult(Controller.getInstance().getAllTimovi());
                            break;
                        case ZAPAMTI_TIM:
                            Controller.getInstance().addTim((Tim) request.getArgument());
                            break;
                        case ZAPAMTI_TAKMICARA:
                            Controller.getInstance().addTakmicar((Takmicar) request.getArgument());
                            break;
                        case IZBRISI_CLANA:
                            Controller.getInstance().deleteClan((Clan) request.getArgument());
                            break;
                        case IZMENI_CLANOVE:
                            Controller.getInstance().updateClanovi((ArrayList<Clan>) request.getArgument());
                            break;
                        case IZBRISI_TIM:
                            Controller.getInstance().deleteTim((Tim) request.getArgument());
                            break;
                        case IZMENI_TIMOVE:
                            Controller.getInstance().updateTimovi((ArrayList<Tim>) request.getArgument());
                            break;
                        case IZMENI_CLANA:
                            Controller.getInstance().updateClan((Clan) request.getArgument());
                            break;
                        case ZAPAMTI_POLAGANJE:
                            Controller.getInstance().addPolaganje((Polaganje) request.getArgument());
                            break;
                        case ZAPAMTI_TAKMICENJE:
                            Controller.getInstance().addTakmicenje((Takmicenje) request.getArgument());
                            break;
                        case UCITAJ_TAKMICARE:
                            response.setResult(Controller.getInstance().getAllTakmicari());
                            break;
                        case UCITAJ_TAKMICENJA:
                            response.setResult(Controller.getInstance().getAllTakmicenja());
                            break;
                        case ZAPAMTI_REZULTAT_TAKMICENJA:
                            Controller.getInstance().addStatistikTakmicara((ArrayList<StatistikaTakmicara>) request.getArgument());
                            break;
                        case UCITAJ_POLAGANJA:
                            response.setResult(Controller.getInstance().getAllPolaganja());
                            break;
                        case ZAPAMTI_REZULTAT_POLAGANJA:
                            Controller.getInstance().addRezultatPolaganja((ArrayList<RezultatPolaganja>) request.getArgument());
                            break;
                        case OSVEZI_TAKMICARE:
                            Controller.getInstance().updateTakmicari();
                            break;
                        case UCITAJ_REZULTATE_POLAGANJA:
                            response.setResult(Controller.getInstance().getAllRezultatiPolaganja((Clan) request.getArgument()));
                            break;
                        case UCITAJ_STATISTIKE_TAKMICENJA:
                            response.setResult(Controller.getInstance().getAllStatistikeTakmicenja((Clan) request.getArgument()));
                            break;
                        case IZMENI_TAKMICARE:
                            Controller.getInstance().updateTakmicarAndClan((ArrayList<Takmicar>) request.getArgument());
                            break;
                        case IZMENI_TAKMICARA:
                            Controller.getInstance().updateTakmicar((Takmicar) request.getArgument());
                            break;
                        case OBRISI_TAKMICARA:
                            Controller.getInstance().deleteTakmicar((Takmicar) request.getArgument());
                            break;
                        case PRONADJI_CLANOVE:
                            response.setResult(Controller.getInstance().getClanovi((String) request.getArgument()));
                            break;
                        case PRONADJI_TIM:
                            response.setResult(Controller.getInstance().getTimovi((String) request.getArgument()));
                            break;
                        case UCITAJ_IZABRANOG_CLANA:
                            response.setResult(Controller.getInstance().getClan((Clan) request.getArgument()));
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                    System.out.println(response.getException());
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
