/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsStatistike;

import domen.Clan;
import domen.StatistikaTakmicara;
import domen.Takmicar;
import domen.Takmicenje;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class GetAllStatistikeTakmicenja extends AbstractGenericOperation{
    
    ArrayList<StatistikaTakmicara> statistike;

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null){
            throw new Exception("Neodgovarajuci podaci!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Takmicar takmicar = new Takmicar();
        Clan clan=(Clan) param;
        takmicar.setClanID(clan);
        takmicar=(Takmicar) repository.getOne(takmicar);
        StatistikaTakmicara statistika = new StatistikaTakmicara();
        statistika.setTakmicarID(takmicar);
        statistike=(ArrayList<StatistikaTakmicara>) repository.getAllWithCondition(statistika);
        ArrayList<Takmicenje> listaTakmicenja = (ArrayList<Takmicenje>) repository.getAll(new Takmicenje());
        for (StatistikaTakmicara statistikaTakmicara : statistike) {
            for (Takmicenje takmicenje : listaTakmicenja) {
                if (statistikaTakmicara.getTakmicenjeID().getTakmicenjeID() == takmicenje.getTakmicenjeID()) {
                    statistikaTakmicara.setTakmicenjeID(takmicenje);
                }
            }
        }
    }

    public ArrayList<StatistikaTakmicara> getStatistike() {
        return statistike;
    }
    
}
