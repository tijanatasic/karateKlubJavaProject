/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Folio1040
 */
public class Takmicenje implements GenericEntity {

    private int takmicenjeID;
    private Date datum;
    private String mesto;
    private String naziv;

    public Takmicenje() {
    }

    public Takmicenje(int takmicenjeID, Date datum, String mesto, String naziv) {
        this.takmicenjeID = takmicenjeID;
        this.datum = datum;
        this.mesto = mesto;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTakmicenjeID() {
        return takmicenjeID;
    }

    public void setTakmicenjeID(int takmicenjeID) {
        this.takmicenjeID = takmicenjeID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.takmicenjeID;
        hash = 97 * hash + Objects.hashCode(this.datum);
        hash = 97 * hash + Objects.hashCode(this.mesto);
        hash = 97 * hash + Objects.hashCode(this.naziv);
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
        final Takmicenje other = (Takmicenje) obj;
        if (this.takmicenjeID != other.takmicenjeID) {
            return false;
        }
        if (!Objects.equals(this.mesto, other.mesto)) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naziv+" - "+datum;
    }
    
    

    @Override
    public String getTableName() {
        return "takmicenje";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "takmicenjeID,datum,mesto,naziv";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(takmicenjeID).append(",")
                .append("'").append(new java.sql.Date(datum.getTime())).append("'").append(",")
                .append("'").append(mesto).append("'").append(",")
                .append("'").append(naziv).append("'");
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        setTakmicenjeID(id);
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) {
        ArrayList<Takmicenje> lista = new ArrayList<>();
        try {
            while(rs.next()){
                Takmicenje takmicenje=new Takmicenje();
                takmicenje.setId(rs.getInt("takmicenjeID"));
                takmicenje.setDatum(rs.getDate("datum"));
                takmicenje.setMesto(rs.getString("mesto"));
                takmicenje.setNaziv(rs.getString("naziv"));
                lista.add(takmicenje);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(lista);
    }

    @Override
    public String getCondition() {
        return "takmicenjeiD="+takmicenjeID;
    }

    @Override
    public String getSetValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericEntity getOneFromResultSet(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOneCondition() {
        return "takmicenjeiD="+takmicenjeID;
    }

    @Override
    public String getJoinCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSetJoin(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
