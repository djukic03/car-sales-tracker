/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class DatabaseUtil {
    public static Connection connect(Connection connection){
        try {
            String url = "jdbc:mysql://localhost:3306/car_sales";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");

        } catch (SQLException ex) {
            System.out.println("Error with database connection: " + ex.getMessage());
        } 
        return connection;
    }
    
    public static Connection closeConnection(Connection connection){
        try {
            if(connection!=null && !connection.isClosed()){
                connection.close();
                System.out.println("Connection closed successfully!");
            }
        } catch (SQLException ex) {
            System.out.println("Error with closing the database connection!\n"+ex.getMessage());
        }
        return connection;
    }
}
