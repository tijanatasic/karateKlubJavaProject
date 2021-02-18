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
public class RezultatPolaganja implements GenericEntity {
    private Clan clanID;
    private Polaganje polaganjeID;
    private boolean polozio;
    private Pojas pojas;

    public RezultatPolaganja() {
    }

    public RezultatPolaganja(Clan clanID, Polaganje polaganjeID, boolean polozio, Pojas pojas) {
        this.clanID = clanID;
        this.polaganjeID = polaganjeID;
        this.polozio = polozio;
        this.pojas = pojas;
    }


    public Pojas getPojas() {
        return pojas;
    }

    public void setPojas(Pojas pojas) {
        this.pojas = pojas;
    }

    public Clan getClanID() {
        return clanID;
    }

    public void setClanID(Clan clanID) {
        this.clanID = clanID;
    }

    public Polaganje getPolaganjeID() {
        return polaganjeID;
    }

    public void setPolaganjeID(Polaganje polaganjeID) {
        this.polaganjeID = polaganjeID;
    }

    public boolean isPolozio() {
        return polozio;
    }

    public void setPolozio(boolean polozio) {
        this.polozio = polozio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.clanID);
        hash = 89 * hash + Objects.hashCode(this.polaganjeID);
        hash = 89 * hash + (this.polozio ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.pojas);
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
        final RezultatPolaganja other = (RezultatPolaganja) obj;
        if (this.polozio != other.polozio) {
            return false;
        }
        if (!Objects.equals(this.clanID, other.clanID)) {
            return false;
        }
        if (!Objects.equals(this.polaganjeID, other.polaganjeID)) {
            return false;
        }
        if (this.pojas != other.pojas) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "rezultatpolaganja";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "clanID,polaganjeID,polozio,pojas";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(clanID.getClanID()).append(",")
                .append(polaganjeID.getPolaganjeID()).append(",")
                .append(polozio).append(",")
                .append("'").append(pojas).append("'");
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
                RezultatPolaganja rezultatPolaganja=new RezultatPolaganja();
                Clan clan=new Clan();
                clan.setClanID(rs.getInt("clanID"));
                rezultatPolaganja.setClanID(clan);
                Polaganje polaganje=new Polaganje();
                polaganje.setPolaganjeID(rs.getInt("polaganjeID"));
                rezultatPolaganja.setPolaganjeID(polaganje);
                rezultatPolaganja.setPolozio(rs.getBoolean("polozio"));
                rezultatPolaganja.setPojas(Pojas.valueOf(rs.getString("pojas")));
                lista.add(rezultatPolaganja);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String getCondition() {
        return "clanID="+clanID.getClanID();
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
        return "clanID="+clanID.getClanID();
    }
  
}
