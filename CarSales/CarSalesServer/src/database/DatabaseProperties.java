/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author user
 */
public class DatabaseProperties {
    private static DatabaseProperties instance;
    private Properties props;

    public DatabaseProperties() {
        try {
            props = new Properties();
            props.load(new FileInputStream("databaseProperties.properties"));
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public static DatabaseProperties getInstance() {
        if(instance == null){
            instance = new DatabaseProperties();
        }
        return instance;
    }
    
    public String getProperty(String propertyName){
        return props.getProperty(propertyName);
    }
    
    public void setUrl(String url){
        try {
            props.setProperty("url", url);
            props.store(new FileOutputStream(new File("databaseProperties.properties")), null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void setUser(String user){
        try {
            props.setProperty("user", user);
            props.store(new FileOutputStream(new File("databaseProperties.properties")), null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void setPassword(String password){
        try {
            props.setProperty("password", password);
            props.store(new FileOutputStream(new File("databaseProperties.properties")), null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
