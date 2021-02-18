/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsTakmicar;

import controller.Controller;
import domen.Clan;
import domen.Takmicar;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class UpdateTakmicarAndClan extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null){
            throw new Exception("Neodgovarajuci podaci!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        ArrayList<Takmicar> listaTakmicara=(ArrayList<Takmicar>) param;
        for (Takmicar takmicar : listaTakmicara) {
            repository.edit(takmicar);
            Clan clan = takmicar.getClanID();
            clan.setTim(takmicar.getTimID());
            repository.edit(clan);
        }
    }
    
}
