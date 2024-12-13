/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.database;

import carsalesapplication.domain.Car;
import carsalesapplication.domain.DefaultDomainObject;
import carsalesapplication.domain.User;
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
    
    public List<DefaultDomainObject> getByCondition(DefaultDomainObject ddo) throws SQLException{
        String query = "select * from "+ddo.getClassName()+" where "+ddo.getCondition()+" Like '%"+ddo.getConditionValue()+"%'";
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
    
    public void closeCon(){
        DatabaseConnection.getInstance().closeConnection();
    }
}
