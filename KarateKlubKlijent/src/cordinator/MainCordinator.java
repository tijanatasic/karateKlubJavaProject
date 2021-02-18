/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cordinator;

import controller.DodajClanaController;
import controller.MainController;
import controller.NapraviTakmicaraController;
import controller.NapravtiTimController;
import controller.PolaganjeController;
import controller.PrikaziSveClanoveController;
import controller.PrikaziSveTakmicareController;
import controller.PrikaziSveTimoveControler;
import controller.RezultatTakmicenjeController;
import controller.RezultatiPolaganjaController;
import controller.StatistikeController;
import controller.TakmicenjeController;
import domen.Clan;
import domen.Tim;
import form.FrmDodajClana;
import form.FrmMain;
import form.FrmTim;
import form.FrmPogledajSveTimove;
import form.FrmPolaganje;
import form.FrmPrikaziSveClanove;
import form.FrmPrikaziSveTakmicare;
import form.FrmRezultatTakmicenja;
import form.FrmRezultatiPolaganja;
import form.FrmStatistike;
import form.FrmTakmicar;
import form.FrmTakmicenje;
import options.ViewOptions;

/**
 *
 * @author Folio1040
 */
public class MainCordinator {

    private static MainCordinator instance;
    private MainController mainController;

    private MainCordinator() {
        mainController = new MainController(new FrmMain());
    }

    public void openMainForm() {
        mainController.openForm();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    public MainController getMainContoller() {
        return mainController;
    }

    public void openKreirajClana() {
        DodajClanaController dodajClanaController =new DodajClanaController(new FrmDodajClana(mainController.getFrmMain() , true));
        dodajClanaController.openFormAdd();
    }

    public void openPrikaziSveClanove() {
        PrikaziSveClanoveController prikaziSveClanoveController = new PrikaziSveClanoveController(new FrmPrikaziSveClanove(mainController.getFrmMain(), true));
        prikaziSveClanoveController.openViewForm();
    }

    public Tim openTim(ViewOptions option) {
        NapravtiTimController napraviTimController = new NapravtiTimController(new FrmTim(mainController.getFrmMain(), true));
        napraviTimController.openForm(option);
        return napraviTimController.getTim();

    }

    public Tim viewTim(int timId) {
        NapravtiTimController napraviTimController = new NapravtiTimController(new FrmTim(mainController.getFrmMain(), true));
        napraviTimController.viewTim(timId);
        return napraviTimController.getTim();
    }

    public void viewClana(Clan clan) {
        DodajClanaController dodajClanaController = new DodajClanaController(new FrmDodajClana(mainController.getFrmMain() , true));
        dodajClanaController.viewClan(clan);
    }

    public void openTakmicar() {
        NapraviTakmicaraController napraviTakmicaraController = new NapraviTakmicaraController(new FrmTakmicar(mainController.getFrmMain(), true));
        napraviTakmicaraController.openTakmicar();
    }

    public void openViewAllTimove() {
        PrikaziSveTimoveControler prikaziSveTimoveController = new PrikaziSveTimoveControler(new FrmPogledajSveTimove(mainController.getFrmMain(), true));
        prikaziSveTimoveController.openForm();
    }

    public void openKreirajPolaganje() {
        PolaganjeController polaganjeController=new PolaganjeController(new FrmPolaganje(mainController.getFrmMain(), true));
        polaganjeController.openForm();
    }

    public void openKreirajTakmicenje() {
        TakmicenjeController takmicenjeController=new TakmicenjeController(new FrmTakmicenje(mainController.getFrmMain(), true));
        takmicenjeController.openForm();
    }

    public void openRezultatPolaganja() {
        RezultatiPolaganjaController rezultatiPolaganjaController=new RezultatiPolaganjaController(new FrmRezultatiPolaganja(mainController.getFrmMain(), true));
        rezultatiPolaganjaController.openForm();
    }

    public void openRezultatTakmicenja() {
        RezultatTakmicenjeController rezultatTakmicenjeController=new RezultatTakmicenjeController(new FrmRezultatTakmicenja(mainController.getFrmMain(), true));
        rezultatTakmicenjeController.openForm();
    }

    public void openStatistike() {
        StatistikeController statistikeController=new StatistikeController(new FrmStatistike(mainController.getFrmMain(), true));
        statistikeController.openForm();
    }

    public void openTakmicari() {
        PrikaziSveTakmicareController prikaziSveTakmicare=new PrikaziSveTakmicareController(new FrmPrikaziSveTakmicare(mainController.getFrmMain(), true));
        prikaziSveTakmicare.openForm();
    }

}
