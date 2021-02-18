/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsPolaganje;

import domen.Polaganje;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class GetAllPolaganja extends AbstractGenericOperation {
    
    ArrayList<Polaganje> listaPolaganja;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Neodgovarajuci podaci!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        listaPolaganja = (ArrayList<Polaganje>) repository.getAll((Polaganje) param);
    }

    public ArrayList<Polaganje> getListaPolaganja() {
        return listaPolaganja;
    }

    
}
