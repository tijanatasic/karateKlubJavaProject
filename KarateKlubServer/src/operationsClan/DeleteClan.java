/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsClan;

import controller.Controller;
import domen.Clan;
import domen.RezultatPolaganja;
import domen.StatistikaTakmicara;
import domen.Takmicar;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class DeleteClan extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Neodgovarajuci podaci");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Clan clan = (Clan) param;
        Takmicar takmicar = new Takmicar();
        takmicar.setClanID(clan);
        takmicar = (Takmicar) repository.getOne(takmicar);
        if (takmicar.getTakmicarID() != 0) {
            StatistikaTakmicara statistika = new StatistikaTakmicara();
            statistika.setTakmicarID(takmicar);
            repository.delete(statistika);
            repository.delete(takmicar);
        }
        RezultatPolaganja rezultatPolaganja = new RezultatPolaganja();
        rezultatPolaganja.setClanID(clan);
        repository.delete(rezultatPolaganja);
        repository.delete(clan);
    }

}
