/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
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
public class Polaganje implements GenericEntity {

    private int polaganjeID;
    private Date datum;
    private String mestoPolaganja;

    public Polaganje() {
    }

    public Polaganje(int polaganjeID, Date datum, String mestoPolaganja) {
        this.polaganjeID = polaganjeID;
        this.datum = datum;
        this.mestoPolaganja = mestoPolaganja;
    }

    public String getMestoPolaganja() {
        return mestoPolaganja;
    }

    public void setMestoPolaganja(String mestoPolaganja) {
        this.mestoPolaganja = mestoPolaganja;
    }

    public int getPolaganjeID() {
        return polaganjeID;
    }

    public void setPolaganjeID(int polaganjeID) {
        this.polaganjeID = polaganjeID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.polaganjeID;
        hash = 67 * hash + Objects.hashCode(this.datum);
        hash = 67 * hash + Objects.hashCode(this.mestoPolaganja);
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
        final Polaganje other = (Polaganje) obj;
        if (this.polaganjeID != other.polaganjeID) {
            return false;
        }
        if (!Objects.equals(this.mestoPolaganja, other.mestoPolaganja)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return mestoPolaganja+" - "+datum;
    }
    
    

    @Override
    public String getTableName() {
        return "polaganje";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "polaganjeID,datum,mesto";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(polaganjeID).append(",")
                .append("'").append(new java.sql.Date(datum.getTime())).append("'").append(",")
                .append("'").append(mestoPolaganja).append("'");

        return sb.toString();
    }

    @Override
    public void setId(int id) {
        setPolaganjeID(id);
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) {
        ArrayList<Polaganje> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Polaganje polaganje=new Polaganje();
                polaganje.setPolaganjeID(rs.getInt("polaganjeID"));
                polaganje.setDatum(rs.getDate("datum"));
                polaganje.setMestoPolaganja(rs.getString("mesto"));
                lista.add(polaganje);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(lista);
    }

    @Override
    public String getCondition() {
        return "polaganjeID=" + polaganjeID;
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
        return "polaganjeID=" + polaganjeID;
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
