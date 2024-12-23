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
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    public DatabaseConnection() {
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
    
    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection(){
        try {
            if(connection!=null && !connection.isClosed()){
                connection.close();
                System.out.println("Connection closed successfully!");
            }
        } catch (SQLException ex) {
            System.out.println("Error with closing the database connection!\n"+ex.getMessage());
        }
    }
}
