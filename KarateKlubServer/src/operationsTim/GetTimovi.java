/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsTim;

import domen.Tim;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class GetTimovi extends AbstractGenericOperation {

    ArrayList<Tim> listaTimova;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Neodgovarajuci podaci!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Tim tim=new Tim();
        tim.setNaziv((String)param);
        listaTimova=(ArrayList<Tim>) repository.getAllWithCondition(tim);
    }

    public ArrayList<Tim> getListaTimova() {
        return listaTimova;
    }

    
}
