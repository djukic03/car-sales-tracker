/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.util;

import carsalesapplication.domain.User;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class UserUtil {  
    public static List<User> getAllUsers(){
        Connection connection = null;
        connection = DatabaseUtil.connect(connection);
        
        List<User> users = new ArrayList();
        try {
            String query = "select * from salesman order by last_name";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                users.add(new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name")));
            }
        } catch (SQLException sQLException) {
            System.out.println("Error: "+sQLException.getMessage());
        }
        
        connection = DatabaseUtil.closeConnection(connection);
        return users;
    }
}
