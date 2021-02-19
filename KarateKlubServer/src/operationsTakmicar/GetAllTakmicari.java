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
public class GetAllTakmicari extends AbstractGenericOperation {

    ArrayList<Takmicar> listaTakmicara = new ArrayList<>();

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Neodgovarajuci podaci");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        listaTakmicara = (ArrayList<Takmicar>) repository.getAllJoin((Takmicar) param, new Tim(), new Clan());
    }

    public ArrayList<Takmicar> getListaTakmicara() {
        return listaTakmicara;
    }
}
