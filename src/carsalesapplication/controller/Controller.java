/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.controller;

import carsalesapplication.database.DatabaseBroker;
import carsalesapplication.domain.Car;
import carsalesapplication.domain.DefaultDomainObject;
import carsalesapplication.domain.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public class Controller {
    private static Controller instance;
    private DatabaseBroker dbBroker;

    public Controller() {
        dbBroker = new DatabaseBroker();
    }
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    public List<DefaultDomainObject> getAll(DefaultDomainObject ddo) throws SQLException{
        return dbBroker.getAll(ddo);
    }
    
    public List<DefaultDomainObject> getByCondition(DefaultDomainObject ddo) throws SQLException {
        return dbBroker.getByCondition(ddo);
    }
    
    public void InsertRow(DefaultDomainObject ddo) throws SQLException {
        dbBroker.insertRow(ddo);
    }
    
    public void closeCon(){
        dbBroker.closeCon();
    }
}
