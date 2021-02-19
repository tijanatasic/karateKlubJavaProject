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
public class Takmicar implements GenericEntity {

    private int takmicarID;
    private boolean tim;
    private boolean pojedinacno;
    private Kategorija kategorija;
    private Tim timID;
    private Clan clanID;

    public Takmicar() {
    }

    public Takmicar(int takmicarID, boolean tim, boolean pojedinacno, Kategorija kategorija, Tim timID, Clan clanID) {
        this.takmicarID = takmicarID;
        this.tim = tim;
        this.pojedinacno = pojedinacno;
        this.kategorija = kategorija;
        this.timID = timID;
        this.clanID = clanID;
    }

    public int getTakmicarID() {
        return takmicarID;
    }

    public void setTakmicarID(int takmicarID) {
        this.takmicarID = takmicarID;
    }

    public boolean isTim() {
        return tim;
    }

    public void setTim(boolean tim) {
        this.tim = tim;
    }

    public boolean isPojedinacno() {
        return pojedinacno;
    }

    public void setPojedinacno(boolean pojedinacno) {
        this.pojedinacno = pojedinacno;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Tim getTimID() {
        return timID;
    }

    public void setTimID(Tim timID) {
        this.timID = timID;
    }

    public Clan getClanID() {
        return clanID;
    }

    public void setClanID(Clan clanID) {
        this.clanID = clanID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.takmicarID;
        hash = 59 * hash + (this.tim ? 1 : 0);
        hash = 59 * hash + (this.pojedinacno ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.kategorija);
        hash = 59 * hash + Objects.hashCode(this.timID);
        hash = 59 * hash + Objects.hashCode(this.clanID);
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
        final Takmicar other = (Takmicar) obj;
        if (this.takmicarID != other.takmicarID) {
            return false;
        }
        if (this.tim != other.tim) {
            return false;
        }
        if (this.pojedinacno != other.pojedinacno) {
            return false;
        }
        if (this.kategorija != other.kategorija) {
            return false;
        }
        if (!Objects.equals(this.timID, other.timID)) {
            return false;
        }
        if (!Objects.equals(this.clanID, other.clanID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return clanID.toString();
    }

    @Override
    public String getTableName() {
        return "takmicar";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "takmicarID,tim,pojedinacno,kategorija,timID,clanID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        if (timID == null) {
            sb.append(takmicarID).append(",")
                    .append(tim).append(",")
                    .append(pojedinacno).append(",")
                    .append("'").append(kategorija).append("'").append(",")
                    .append("NULL").append(",")
                    .append(clanID.getClanID());
        } else {
            sb.append(takmicarID).append(",")
                    .append(tim).append(",")
                    .append(pojedinacno).append(",")
                    .append("'").append(kategorija).append("'").append(",")
                    .append(timID.getTimID()).append(",")
                    .append(clanID.getClanID());
        }

        return sb.toString();
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) {
        ArrayList<GenericEntity> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Takmicar takmicar = new Takmicar();
                takmicar.setTakmicarID(rs.getInt("takmicarID"));
                takmicar.setTim(rs.getBoolean("tim"));
                takmicar.setPojedinacno(rs.getBoolean("pojedinacno"));
                takmicar.setKategorija(Kategorija.valueOf(rs.getString("kategorija")));
                takmicar.setTimID(new Tim(rs.getInt("timID"), "", false, false, false));
                takmicar.setClanID(new Clan(rs.getInt("clanID"), "", "", 0, null, null));
                lista.add(takmicar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String getCondition() {
        if (takmicarID == 0) {
            return "clanID=" + clanID.getClanID();
        }
        return "takmicarID=" + takmicarID;
    }

    @Override
    public String getSetValues() {
        if(tim==false){
            return "tim=FALSE, pojedinacno="+pojedinacno+", kategorija='"+kategorija+"', timID=NULL, clanID="+clanID.getClanID();
        }
        return "tim="+tim+","+"pojedinacno="+pojedinacno+", kategorija='"+kategorija+"', timID="+timID.getTimID()+", clanID="+clanID.getClanID();
        
    }

    @Override
    public GenericEntity getOneFromResultSet(ResultSet rs) {
        Takmicar takmicar = new Takmicar();
        try {
            while (rs.next()) {
                takmicar.setTakmicarID(rs.getInt("takmicarID"));
                takmicar.setTim(rs.getBoolean("tim"));
                takmicar.setPojedinacno(rs.getBoolean("pojedinacno"));
                takmicar.setKategorija(Kategorija.valueOf(rs.getString("kategorija")));
                takmicar.setTimID(new Tim(rs.getInt("timID"), "", false, false, false));
                takmicar.setClanID(new Clan(rs.getInt("clanID"), "", "", 0, null, null));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return takmicar;
    }

    @Override
    public String getOneCondition() {
        if (takmicarID == 0) {
            return "clanID=" + clanID.getClanID();
        }
        return "takmicarID=" + takmicarID;
    }

    @Override
    public String getJoinCondition() {
        return "timID";
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSetJoin(ResultSet rs) {
        ArrayList<GenericEntity> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Takmicar takmicar = new Takmicar();
                takmicar.setTakmicarID(rs.getInt("takmicar.takmicarID"));
                takmicar.setTim(rs.getBoolean("takmicar.tim"));
                takmicar.setPojedinacno(rs.getBoolean("takmicar.pojedinacno"));
                takmicar.setKategorija(Kategorija.valueOf(rs.getString("takmicar.kategorija")));
                if(rs.getInt("takmicar.timID")==0){
                    takmicar.setTimID(new Tim(rs.getInt("timID"), "", false, false, false));
                }else{
                    Tim tim=new Tim();
                    tim.setTimID(rs.getInt("takmicar.timID"));
                    tim.setTimBorbe(rs.getBoolean("tim.timBorbe"));
                    tim.setTimKate(rs.getBoolean("tim.timKate"));
                    tim.setTimTakmicar(rs.getBoolean("tim.timTakmicar"));
                    tim.setNaziv(rs.getString("naziv"));
                    takmicar.setTimID(tim);
                }
                Clan clan=new Clan();
                clan.setClanID(rs.getInt("takmicar.clanID"));
                clan.setIme(rs.getString("ime"));
                clan.setPrezime(rs.getString("prezime"));
                clan.setJMBG(rs.getLong("JMBG"));
                clan.setPojas(Pojas.valueOf(rs.getString("pojas")));
                clan.setTim(takmicar.getTimID());
                takmicar.setClanID(clan);
                lista.add(takmicar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
