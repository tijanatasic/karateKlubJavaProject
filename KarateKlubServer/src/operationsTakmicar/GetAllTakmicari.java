/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsTakmicar;

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
public class GetAllTakmicari extends AbstractGenericOperation{
    
    ArrayList<Takmicar> listaTakmicara=new ArrayList<>();

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null){
            throw new Exception("Neodgovarajuci podaci");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        ArrayList<Takmicar> takmicari = (ArrayList<Takmicar>) repository.getAll((Takmicar)param);
        for (Takmicar takmicar : takmicari) {
            Clan clan = new Clan();
            clan.setClanID(takmicar.getClanID().getClanID());
            clan=(Clan) repository.getOne(clan);
            takmicar.setClanID(clan);
            if (takmicar.getTimID().getTimID() != 0) {
                Tim tim = new Tim();
                tim.setTimID(takmicar.getTimID().getTimID());
                tim=(Tim) repository.getOne(tim);
                takmicar.setTimID(tim);
            } else {
                takmicar.setTimID(new Tim(0, "", false, false, false));
            }
            listaTakmicara.add(takmicar);
        }
    }

    public ArrayList<Takmicar> getListaTakmicara() {
        return listaTakmicara;
    }
}
