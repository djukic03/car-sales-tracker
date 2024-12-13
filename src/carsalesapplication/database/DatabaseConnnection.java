/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DatabaseConnnection {
    private static DatabaseConnnection instance;
    private Connection connection;

    public DatabaseConnnection() {
        String url = "jdbc:mysql://localhost:3306/car_sales";
        String user = "root";
        String password = "";
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");
        } catch (SQLException ex) {
            System.out.println("Connection error: "+ex.getMessage());
        }
    }
    
    public static DatabaseConnnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    
}
