/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsStatistike;

import domen.StatistikaTakmicara;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class AddStatistikaTakmicara extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null){
            throw new Exception("Neodgovarajuci podaci");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        ArrayList<StatistikaTakmicara> lista=(ArrayList<StatistikaTakmicara>) param;
        for (StatistikaTakmicara statistikaTakmicara : lista) {
            repository.add(statistikaTakmicara);
        }
    }
}
