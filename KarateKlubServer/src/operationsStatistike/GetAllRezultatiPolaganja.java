/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsStatistike;

import domen.Clan;
import domen.RezultatPolaganja;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class GetAllRezultatiPolaganja extends AbstractGenericOperation {

    ArrayList<RezultatPolaganja> listaRezultataPolaganja;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Neodgovarajuci podaci!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Clan clan = (Clan) param;
        RezultatPolaganja rezultatPolaganja = new RezultatPolaganja();
        rezultatPolaganja.setClanID(clan);
        listaRezultataPolaganja = (ArrayList<RezultatPolaganja>) repository.getAllWithCondition(rezultatPolaganja);
    }

    public ArrayList<RezultatPolaganja> getListaRezultataPolaganja() {
        return listaRezultataPolaganja;
    }

}
