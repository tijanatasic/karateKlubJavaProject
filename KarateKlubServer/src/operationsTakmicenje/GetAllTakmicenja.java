/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsTakmicenje;

import domen.Takmicenje;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class GetAllTakmicenja extends AbstractGenericOperation{
    
    ArrayList<Takmicenje> listaTakmicenja;

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null){
            throw new Exception("Neodgovarajuci podaci!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        listaTakmicenja = (ArrayList<Takmicenje>) repository.getAll((Takmicenje)param);
    }

    public ArrayList<Takmicenje> getListaTakmicenja() {
        return listaTakmicenja;
    }
    
}