/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryDbImpl;

import dbRepository.DBConnectionFactory;
import dbRepository.DBRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import domen.GenericEntity;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Folio1040
 */
public class GenericDBRepository implements DBRepository<GenericEntity>{

    @Override
    public List<GenericEntity> getAll(GenericEntity param) throws Exception {
        List<GenericEntity> list=null;
        try {
            Connection connection=DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ").append(param.getColumnNamesForInsert()).append(" FROM ")
                    .append(param.getTableName());
            String query = sb.toString();
            System.out.println(query);
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            list=new ArrayList<>(param.getFromResultSet(rs));
            rs.close();
            statement.close();
        } catch (Exception ex) {
            Logger.getLogger(GenericDBRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void add(GenericEntity param) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(param.getTableName())
                    .append(" (").append(param.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(param.getInsertValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                int id = rsKey.getInt(1);
                param.setId(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void edit(GenericEntity param) throws Exception {
        Connection connection=DBConnectionFactory.getInstance().getConnection();
        StringBuilder sb=new StringBuilder();
        sb.append("UPDATE ")
                .append(param.getTableName())
                .append(" SET ")
                .append(param.getSetValues()).append(" WHERE ")
                .append(param.getCondition());
        String query=sb.toString();
        System.out.println(query);
        PreparedStatement ps=connection.prepareStatement(query);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(GenericEntity param) throws Exception {
        Connection connection=DBConnectionFactory.getInstance().getConnection();
        StringBuilder sb=new StringBuilder();
        sb.append("DELETE FROM ")
                .append(param.getTableName())
                .append(" WHERE ")
                .append(param.getCondition());
        String query=sb.toString();
        System.out.println(query);
        PreparedStatement ps=connection.prepareStatement(query);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public GenericEntity getOne(GenericEntity param) {
        GenericEntity par=null;
        try {
            Connection connection=DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ").append(param.getColumnNamesForInsert()).append(" FROM ")
                    .append(param.getTableName())
                    .append(" WHERE ")
                    .append(param.getOneCondition());
            String query = sb.toString();
            System.out.println(query);
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            par=(GenericEntity) param.getOneFromResultSet(rs);
        } catch (Exception ex) {
            Logger.getLogger(GenericDBRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return par;
    }

    @Override
    public List<GenericEntity> getAllWithCondition(GenericEntity param) throws Exception {
        List<GenericEntity> list=null;
        try {
            Connection connection=DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ").append(param.getColumnNamesForInsert()).append(" FROM ")
                    .append(param.getTableName())
                    .append(" WHERE ")
                    .append(param.getCondition());
            String query = sb.toString();
            System.out.println(query);
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            list=new ArrayList<>(param.getFromResultSet(rs));
            rs.close();
            statement.close();
        } catch (Exception ex) {
            Logger.getLogger(GenericDBRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<GenericEntity> getAllJoin(GenericEntity param1, GenericEntity param2, GenericEntity param3) throws Exception {
        List<GenericEntity> list=null;
        try {
            Connection connection=DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ").append(param1.getTableName()+".*, ")
                    .append(param2.getTableName()+".*, ")
                    .append(param3.getTableName()+".*")
                    .append(" FROM ")
                    .append(param1.getTableName()+" "+param1.getTableName())
                    .append(" LEFT JOIN ")
                    .append(param2.getTableName()+" "+param2.getTableName())
                    .append(" ON ")
                    .append(param1.getTableName()+".")
                    .append(param1.getJoinCondition()+"=")
                    .append(param2.getTableName()+".")
                    .append(param2.getJoinCondition())
                    .append(" LEFT JOIN ")
                    .append(param3.getTableName()+" "+param3.getTableName())
                    .append(" ON ")
                    .append(param1.getTableName()+".")
                    .append(param3.getJoinCondition()+"=")
                    .append(param3.getTableName()+".")
                    .append(param3.getJoinCondition());
            String query = sb.toString();
            System.out.println(query);
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            list=new ArrayList<>(param1.getFromResultSetJoin(rs));
            rs.close();
            statement.close();
        } catch (Exception ex) {
            Logger.getLogger(GenericDBRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
