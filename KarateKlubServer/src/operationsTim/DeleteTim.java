/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsTim;

import controller.Controller;
import domen.Clan;
import domen.Takmicar;
import domen.Tim;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class DeleteTim extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null){
            throw new Exception("Neodgovarajuci podaci");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Clan clan = new Clan();
        Tim tim=(Tim) param;
        clan.setTim(tim);
        ArrayList<Clan> clanovi=(ArrayList<Clan>) repository.getAll(clan);
        ArrayList<Takmicar> takmicari=(ArrayList<Takmicar>) repository.getAll(new Takmicar());
        for (Clan clan1 : clanovi) {
            if (clan1.getTim().getTimID() == tim.getTimID()) {
                clan1.getTim().setTimID(0);
                repository.edit(clan1);
                for (Takmicar takmicar : takmicari) {
                    if (takmicar.getClanID().getClanID() == clan1.getClanID()) {
                        takmicar.setTim(false);
                        repository.edit(takmicar);
                    }
                }
            }
        }
        repository.delete(tim);
    }
    
}
