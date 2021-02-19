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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Folio1040
 */
public class StatistikaTakmicara implements GenericEntity {

    private Takmicar takmicarID;
    private Takmicenje takmicenjeID;
    private String plasman;
    private int brojPoena;

    public StatistikaTakmicara() {
    }

    public StatistikaTakmicara(Takmicar takmicarID, Takmicenje takmicenjeID, String plasman, int brojPoena) {
        this.takmicarID = takmicarID;
        this.takmicenjeID = takmicenjeID;
        this.plasman = plasman;
        this.brojPoena = brojPoena;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }

    public Takmicar getTakmicarID() {
        return takmicarID;
    }

    public void setTakmicarID(Takmicar takmicarID) {
        this.takmicarID = takmicarID;
    }

    public Takmicenje getTakmicenjeID() {
        return takmicenjeID;
    }

    public void setTakmicenjeID(Takmicenje takmicenjeID) {
        this.takmicenjeID = takmicenjeID;
    }

    public String getPlasman() {
        return plasman;
    }

    public void setPlasman(String plasman) {
        this.plasman = plasman;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.takmicarID);
        hash = 67 * hash + Objects.hashCode(this.takmicenjeID);
        hash = 67 * hash + Objects.hashCode(this.plasman);
        hash = 67 * hash + this.brojPoena;
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
        final StatistikaTakmicara other = (StatistikaTakmicara) obj;
        if (this.brojPoena != other.brojPoena) {
            return false;
        }
        if (!Objects.equals(this.plasman, other.plasman)) {
            return false;
        }
        if (!Objects.equals(this.takmicarID, other.takmicarID)) {
            return false;
        }
        if (!Objects.equals(this.takmicenjeID, other.takmicenjeID)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "statistikatakmicara";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "takmicarID,takmicenjeID,plasman,brojPoena";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(takmicarID.getTakmicarID()).append(",")
                .append(takmicenjeID.getTakmicenjeID()).append(",")
                .append("'").append(plasman).append("'").append(",")
                .append(brojPoena);
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) {
        ArrayList<GenericEntity> lista = new ArrayList<>();
        try {
            while(rs.next()){
                StatistikaTakmicara statistika=new StatistikaTakmicara();
                Takmicar takmicar=new Takmicar();
                takmicar.setTakmicarID(rs.getInt("takmicarID"));
                statistika.setTakmicarID(takmicar);
                Takmicenje takmicenje=new Takmicenje();
                takmicenje.setTakmicenjeID(rs.getInt("takmicenjeID"));
                statistika.setTakmicenjeID(takmicenje);
                statistika.setPlasman(rs.getString("plasman"));
                statistika.setBrojPoena(rs.getInt("brojPoena"));
                lista.add(statistika);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String getCondition() {
        return "takmicarID="+takmicarID.getTakmicarID();
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
        return "takmicarID="+takmicarID.getTakmicarID();
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
