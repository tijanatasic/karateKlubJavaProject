/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Folio1040
 */
public class Tim implements GenericEntity {

    private int timID;
    private String naziv;
    private boolean timBorbe;
    private boolean timKate;
    private boolean timTakmicar;

    public Tim() {
    }

    public Tim(int timID, String naziv, boolean timBorbe, boolean timKate, boolean timTakmicar) {
        this.timID = timID;
        this.naziv = naziv;
        this.timBorbe = timBorbe;
        this.timKate = timKate;
        this.timTakmicar = timTakmicar;
    }

    public boolean isTimTakmicar() {
        return timTakmicar;
    }

    public void setTimTakmicar(boolean timTakmicar) {
        this.timTakmicar = timTakmicar;
    }

    public int getTimID() {
        return timID;
    }

    public void setTimID(int timID) {
        this.timID = timID;
    }

    public boolean isTimBorbe() {
        return timBorbe;
    }

    public void setTimBorbe(boolean timBorbe) {
        this.timBorbe = timBorbe;
    }

    public boolean isTimKate() {
        return timKate;
    }

    public void setTimKate(boolean timKate) {
        this.timKate = timKate;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.timID;
        hash = 79 * hash + Objects.hashCode(this.naziv);
        hash = 79 * hash + (this.timBorbe ? 1 : 0);
        hash = 79 * hash + (this.timKate ? 1 : 0);
        hash = 79 * hash + (this.timTakmicar ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tim other = (Tim) obj;
        if (this.timBorbe != other.timBorbe) {
            return false;
        }
        if (this.timKate != other.timKate) {
            return false;
        }
        if (this.timTakmicar != other.timTakmicar) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naziv + ": borbe - " + timBorbe + ", kate - " + timKate + ", takmicar - " + timTakmicar;
    }

    @Override
    public String getTableName() {
        return "tim";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "timID,timBorbe,timKate,timTakmicar,naziv";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(timID).append(",")
                .append(timBorbe).append(",")
                .append(timKate).append(",")
                .append(timTakmicar).append(",")
                .append("'").append(naziv).append("'");
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        setTimID(id);
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) {
        ArrayList<Tim> lista = new ArrayList<>();
        try {
            while(rs.next()){
                Tim tim=new Tim();
                tim.setId(rs.getInt("timID"));
                tim.setTimBorbe(rs.getBoolean("timBorbe"));
                tim.setTimKate(rs.getBoolean("timKate"));
                tim.setTimTakmicar(rs.getBoolean("timTakmicar"));
                tim.setNaziv(rs.getString("naziv"));
                lista.add(tim);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(lista);
    }

    @Override
    public String getCondition() {
        if(timID==0){
            return "naziv LIKE '"+naziv+"%'";
        }
        return "timID="+timID;
    }

    @Override
    public String getSetValues() {
        return "timBorbe="+timBorbe+", timKate="+timKate+", timTakmicar="+timTakmicar+", naziv='"+naziv+"'";
        
    }

    @Override
    public GenericEntity getOneFromResultSet(ResultSet rs) {
        Tim tim=new Tim();
        try {
            while(rs.next()){
                tim.setId(rs.getInt("timID"));
                tim.setTimBorbe(rs.getBoolean("timBorbe"));
                tim.setTimKate(rs.getBoolean("timKate"));
                tim.setTimTakmicar(rs.getBoolean("timTakmicar"));
                tim.setNaziv(rs.getString("naziv"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tim;
    }

    @Override
    public String getOneCondition() {
        if(timID>0){
            return getCondition();
        }
        return "naziv='"+naziv+"'";
    }

}
