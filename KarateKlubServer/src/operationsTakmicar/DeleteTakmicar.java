/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationsTakmicar;

import controller.Controller;
import domen.Clan;
import domen.StatistikaTakmicara;
import domen.Takmicar;
import operation.AbstractGenericOperation;

/**
 *
 * @author Folio1040
 */
public class DeleteTakmicar extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Neodgovarajuci podaci");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Takmicar takmicar = (Takmicar) param;
        StatistikaTakmicara statistika=new StatistikaTakmicara();
        statistika.setTakmicarID(takmicar);
        repository.delete(statistika);
        repository.delete(takmicar);
    }

}
