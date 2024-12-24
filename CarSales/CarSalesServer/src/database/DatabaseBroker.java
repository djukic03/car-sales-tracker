/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import domain.Car;
import domain.DefaultDomainObject;
import domain.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class DatabaseBroker {

    public DatabaseBroker() {
    }
    
    public List<DefaultDomainObject> getAll(DefaultDomainObject ddo) throws SQLException{
        String query = "select * from "+ddo.getClassName();
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        return ddo.returnList(rs);
    }
    
    public List<DefaultDomainObject> getAllOrdered(DefaultDomainObject ddo) throws SQLException{
        String query = "select * from "+ddo.getClassName() + " order by "+ddo.getOrderCondition();
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        return ddo.returnList(rs);
    }
    
    public List<DefaultDomainObject> getByCondition(DefaultDomainObject ddo) throws SQLException{
        String query = "select * from "+ddo.getClassName()+" where "+ddo.getSearchCondition()+" Like '"+ddo.getSearchConditionValue()+"%'";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        return ddo.returnList(rs);
    }
    
    public void insertRow(DefaultDomainObject ddo) throws SQLException{
        String query = "insert into "+ddo.getClassName()+" ("+ddo.getInsertColumns()+") values("+ddo.getInsertValues()+")";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement s = connection.createStatement();
        s.executeUpdate(query);
    }
    
    public Long insertRowAndGetId(DefaultDomainObject ddo) throws SQLException{
        String query = "insert into "+ddo.getClassName()+" ("+ddo.getInsertColumns()+") values("+ddo.getInsertValues()+")";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int affectedRows = ps.executeUpdate();
        Long id = null;
        if (affectedRows > 0) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                } else {
                    System.out.println("No ID generated.");
                }
            }
        } else {
            System.out.println("Insert failed, no rows affected.");
        }
        return id;
    }
    
    public void deleteRow(DefaultDomainObject ddo) throws SQLException {
        String query = "delete from "+ddo.getClassName()+" where "+ddo.getDeleteCondition()+" = "+ddo.getDeleteConditionValue();
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement s = connection.createStatement();
        s.executeUpdate(query);
    }
    
    public void updateRow(DefaultDomainObject ddo) throws SQLException {
        String query = "update "+ddo.getClassName()+" set "+ddo.getUpdateValues()+" where "+ddo.getUpdateCondition()+" = "+ddo.getUpdateConditionValue();
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement s = connection.createStatement();
        s.executeUpdate(query);
    }
    
    public List<String> getAllCarBrands() throws SQLException {
        String query = "select distinct brand from car order by brand";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        List<String> brands = new ArrayList<>();
        while(rs.next()){
            brands.add(rs.getString("brand"));
        }
        return brands;
    }
    
    public void closeCon(){
        DatabaseConnection.getInstance().closeConnection();
    }

}
