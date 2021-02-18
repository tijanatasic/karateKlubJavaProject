/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.Clan;
import domen.Polaganje;
import domen.RezultatPolaganja;
import domen.StatistikaTakmicara;
import domen.Takmicar;
import domen.Takmicenje;
import domen.Tim;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import operationsClan.AddClan;
import operationsPolaganje.AddPolaganje;
import operationsStatistike.AddRezultatPolaganja;
import operationsStatistike.AddStatistikaTakmicara;
import operationsTakmicar.AddTakmicar;
import operationsTakmicenje.AddTakmicenje;
import operationsTim.AddTim;
import operationsClan.DeleteClan;
import operationsTakmicar.DeleteTakmicar;
import operationsTim.DeleteTim;
import operationsClan.GetAllClanovi;
import operationsPolaganje.GetAllPolaganja;
import operationsStatistike.GetAllRezultatiPolaganja;
import operationsStatistike.GetAllStatistikeTakmicenja;
import operationsTakmicar.GetAllTakmicari;
import operationsTakmicenje.GetAllTakmicenja;
import operationsTim.GetAllTimovi;
import operationsClan.GetClan;
import operationsClan.GetClanovi;
import operationsClan.UpdateClan;
import operationsTim.GetTimovi;
import operationsClan.UpdateClanovi;
import operationsTakmicar.UpdateTakmicar;
import operationsTakmicar.UpdateTakmicarAndClan;
import operationsTakmicar.UpdateTakmicari;
import operationsTim.UpdateTimovi;

/**
 *
 * @author Folio1040
 */
public class Controller {

    private static Controller instance;

    public Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void addClan(Clan clan) throws Exception {
        AbstractGenericOperation operation=new AddClan();
        operation.execute(clan);
    }

    public ArrayList<Clan> getAllClanovi() throws Exception {
        AbstractGenericOperation operation = new GetAllClanovi();
        operation.execute(new Clan());
        return ((GetAllClanovi) operation).getListClanova();
    }

    public ArrayList<Tim> getAllTimovi() throws Exception {
        AbstractGenericOperation operation = new GetAllTimovi();
        operation.execute(new Tim());
        return ((GetAllTimovi) operation).getListaTimova();
    }

    public void addTim(Tim tim) throws Exception {
        AbstractGenericOperation operation = new AddTim();
        operation.execute(tim);
    }

    public void addTakmicar(Takmicar takmicar) throws Exception {
        AbstractGenericOperation operation = new AddTakmicar();
        operation.execute(takmicar);
    }

    public void deleteClan(Clan clan) throws Exception {
        AbstractGenericOperation operation = new DeleteClan();
        operation.execute(clan);
    }

    public void updateClanovi(ArrayList<Clan> clanovi) throws Exception {
        AbstractGenericOperation operation = new UpdateClanovi();
        operation.execute(clanovi);
    }

    public void updateClan(Clan clan) throws Exception {
        AbstractGenericOperation operation = new UpdateClan();
        operation.execute(clan);
    }

    public void deleteTim(Tim tim) throws Exception {
        AbstractGenericOperation operation=new DeleteTim();
        operation.execute(tim);
    }

    public void updateTimovi(ArrayList<Tim> timovi) throws Exception {
        AbstractGenericOperation operation = new UpdateTimovi();
        operation.execute(timovi);
    }

    public void addPolaganje(Polaganje polaganje) throws Exception {
        AbstractGenericOperation operation = new AddPolaganje();
        operation.execute(polaganje);
    }

    public void addTakmicenje(Takmicenje takmicenje) throws Exception {
        AbstractGenericOperation operation = new AddTakmicenje();
        operation.execute(takmicenje);
    }

    public ArrayList<Takmicar> getAllTakmicari() throws Exception {
        AbstractGenericOperation operation = new GetAllTakmicari();
        operation.execute(new Takmicar());
        return ((GetAllTakmicari) operation).getListaTakmicara();
    }

    public ArrayList<Takmicenje> getAllTakmicenja() throws Exception {
        AbstractGenericOperation operation = new GetAllTakmicenja();
        operation.execute(new Takmicenje());
        return ((GetAllTakmicenja) operation).getListaTakmicenja();
    }

    public void addStatistikTakmicara(ArrayList<StatistikaTakmicara> statistikaTakmicara) throws Exception {
        AbstractGenericOperation operation = new AddStatistikaTakmicara();
        operation.execute(statistikaTakmicara);
    }

    public ArrayList<Polaganje> getAllPolaganja() throws Exception {
        AbstractGenericOperation operation = new GetAllPolaganja();
        operation.execute(new Polaganje());
        return ((GetAllPolaganja) operation).getListaPolaganja();
    }

    public void addRezultatPolaganja(ArrayList<RezultatPolaganja> rezultatPolaganja) throws Exception {
        AbstractGenericOperation operation = new AddRezultatPolaganja();
        operation.execute(rezultatPolaganja);
    }

    public void updateTakmicari() throws Exception {
        AbstractGenericOperation operation=new UpdateTakmicari();
        operation.execute(new Takmicar());
    }

    public void deleteTakmicar(Takmicar takmicar) throws Exception {
        AbstractGenericOperation operation = new DeleteTakmicar();
        operation.execute(takmicar);
    }

    public ArrayList<RezultatPolaganja> getAllRezultatiPolaganja(Clan clan) throws Exception {
        AbstractGenericOperation operation=new GetAllRezultatiPolaganja();
        operation.execute(clan);
        return ((GetAllRezultatiPolaganja) operation).getListaRezultataPolaganja();
    }

    public ArrayList<StatistikaTakmicara> getAllStatistikeTakmicenja(Clan clan) throws Exception {
        AbstractGenericOperation operation=new GetAllStatistikeTakmicenja();
        operation.execute(clan);
        return ((GetAllStatistikeTakmicenja) operation).getStatistike();
    }
    
    public void updateTakmicar(Takmicar takmicar) throws Exception {
        AbstractGenericOperation operation = new UpdateTakmicar();
        operation.execute(takmicar);
    }

    public void updateTakmicarAndClan(ArrayList<Takmicar> takmicari) throws Exception {
        AbstractGenericOperation operation=new UpdateTakmicarAndClan();
        operation.execute(takmicari);
    }

    public ArrayList<Clan> getClanovi(String kriterijum) throws Exception {
        AbstractGenericOperation operation=new GetClanovi();
        operation.execute(kriterijum);
        return ((GetClanovi) operation).getListaClanova();
    }

    public ArrayList<Tim> getTimovi(String kriterijum) throws Exception {
        AbstractGenericOperation operation=new GetTimovi();
        operation.execute(kriterijum);
        return ((GetTimovi) operation).getListaTimova();
    }

    public Clan getClan(Clan clan) throws Exception {
        AbstractGenericOperation operation=new GetClan();
        operation.execute(clan);
        return ((GetClan) operation).getClan();
    }
}
