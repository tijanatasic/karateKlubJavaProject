/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsTakmicar;

import controller.Controller;
import domen.Takmicar;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class UpdateTakmicari extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null){
            throw new Exception("Neodgovarajuci podaci");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        ArrayList<Takmicar> listaTakmicara=(ArrayList<Takmicar>) repository.getAll((Takmicar)param);
        for (Takmicar takmicar : listaTakmicara) {
            if(!takmicar.isPojedinacno() && !takmicar.isTim()){
                repository.delete(takmicar);
            }
        }
    }
    
}
