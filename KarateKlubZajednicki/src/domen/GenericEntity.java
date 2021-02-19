/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Folio1040
 */
public interface GenericEntity extends Serializable{
    
    public String getTableName();

    public String getColumnNamesForInsert();

    public String getInsertValues();

    public void setId(int id);
    
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs);
    
    public GenericEntity getOneFromResultSet(ResultSet rs);

    public String getCondition();

    public String getSetValues();

    public String getOneCondition();

    public String getJoinCondition();

    public ArrayList<GenericEntity> getFromResultSetJoin(ResultSet rs);


}
