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
public class Clan implements GenericEntity{
    private int clanID;
    private String ime;
    private String prezime;
    private long JMBG;
    private Pojas pojas;
    private Tim tim;

    public Clan() {
    }

    public Clan(int clanID, String ime, String prezime, long JMBG, Pojas pojas, Tim tim) {
        this.clanID = clanID;
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.pojas = pojas;
        this.tim = tim;
       
    }

    public int getClanID() {
        return clanID;
    }

    public void setClanID(int clanID) {
        this.clanID = clanID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public long getJMBG() {
        return JMBG;
    }

    public void setJMBG(long JMBG) {
        this.JMBG = JMBG;
    }

    public Pojas getPojas() {
        return pojas;
    }

    public void setPojas(Pojas pojas) {
        this.pojas = pojas;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.clanID;
        hash = 37 * hash + Objects.hashCode(this.ime);
        hash = 37 * hash + Objects.hashCode(this.prezime);
        hash = 37 * hash + (int) (this.JMBG ^ (this.JMBG >>> 32));
        hash = 37 * hash + Objects.hashCode(this.pojas);
        hash = 37 * hash + Objects.hashCode(this.tim);
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
        final Clan other = (Clan) obj;
        if (this.clanID != other.clanID) {
            return false;
        }
        if (this.JMBG != other.JMBG) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (this.pojas != other.pojas) {
            return false;
        }
        if (!Objects.equals(this.tim, other.tim)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime+" "+prezime+", pojas: "+pojas;
    }
    
    
    @Override
    public String getTableName() {
        return "clan";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "clanID,ime,prezime,JMBG,pojas,timID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb=new StringBuilder();
        if(tim.getTimID()==-1){
            sb.append(clanID).append(",")
                .append("'").append(ime).append("'").append(",")
                .append("'").append(prezime).append("'").append(",")
                .append(JMBG).append(",")
                .append("'").append(pojas).append("'").append(",")
                .append("NULL");
        }else{
            sb.append(clanID).append(",")
                .append("'").append(ime).append("'").append(",")
                .append("'").append(prezime).append("'").append(",")
                .append(JMBG).append(",")
                .append("'").append(pojas).append("'").append(",")
                .append(tim.getTimID());
        }
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        setClanID(id);
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) {
        ArrayList<Clan> lista = new ArrayList<>();
        try {
            while(rs.next()){
                Clan clan=new Clan();
                clan.setId(rs.getInt("clanID"));
                clan.setIme(rs.getString("ime"));
                clan.setPrezime(rs.getString("prezime"));
                clan.setJMBG(rs.getLong("JMBG"));
                clan.setPojas(Pojas.valueOf(rs.getString("pojas")));
                clan.setTim(new Tim(rs.getInt("timID"), "", true, true, true));
                lista.add(clan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(lista);
    }

    @Override
    public String getCondition() {
        if(clanID==0){
            return "ime LIKE '"+ime+"%'";
        }
        return "clanID="+clanID;
    }

    @Override
    public String getSetValues() {
        if(tim.getTimID()==0){
            return "ime='"+ime+"', prezime='"+prezime+"', JMBG="+JMBG+", pojas='"+pojas+"'"+", timID=NULL";
        }else{
            return "ime='"+ime+"', prezime='"+prezime+"', JMBG="+JMBG+", pojas='"+pojas+"', timID="+tim.getTimID();
        }
        
    }

    @Override
    public GenericEntity getOneFromResultSet(ResultSet rs) {
        GenericEntity entity=null;
        try {
            while(rs.next()){
                Clan clan=new Clan();
                clan.setId(rs.getInt("clanID"));
                clan.setIme(rs.getString("ime"));
                clan.setPrezime(rs.getString("prezime"));
                clan.setJMBG(rs.getLong("JMBG"));
                clan.setPojas(Pojas.valueOf(rs.getString("pojas")));
                clan.setTim(new Tim(rs.getInt("timID"), "", true, true, true));
                entity=clan;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @Override
    public String getOneCondition() {
        if(clanID==0){
            return "timID="+tim.getTimID();
        }
        return "clanID="+clanID;
    }

    @Override
    public String getJoinCondition() {
        return "clanID";
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSetJoin(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
