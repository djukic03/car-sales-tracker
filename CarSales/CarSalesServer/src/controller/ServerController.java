/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.DatabaseBroker;
import domain.DefaultDomainObject;
import domain.User;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class ServerController {
    private static ServerController instance;
    private final DatabaseBroker dbBroker;

    private ServerController() {
        dbBroker = new DatabaseBroker();
    }
    
    public static ServerController getInstance(){
        if(instance == null){
            instance = new ServerController();
        }
        return instance;
    }
    
    public User login(String username, String password) throws Exception {
        List<DefaultDomainObject> users = ServerController.getInstance().getAll(new User());
        for (DefaultDomainObject u : users) {
            User user = (User) u;
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        throw new Exception("Wrong username or password");
    }
    
    public List<DefaultDomainObject> getAll(DefaultDomainObject ddo) throws SQLException{
        return dbBroker.getAll(ddo);
    }
    
    public List<DefaultDomainObject> getByCondition(DefaultDomainObject ddo) throws SQLException {
        return dbBroker.getByCondition(ddo);
    }
    
    public void insertRow(DefaultDomainObject ddo) throws SQLException {
        dbBroker.insertRow(ddo);
    }
    
    public Long insertRowAndGetId(DefaultDomainObject ddo) throws SQLException {
        return dbBroker.insertRowAndGetId(ddo);
    }
    
    public void deleteRow(DefaultDomainObject ddo) throws SQLException {
        dbBroker.deleteRow(ddo);
    }
    
    public void updateRow(DefaultDomainObject ddo) throws SQLException {
        dbBroker.updateRow(ddo);
    }
    
    public List<String> getAllCarBrands() throws SQLException{
        return dbBroker.getAllCarBrands();
    }
    
    public void closeCon(){
        dbBroker.closeCon();
    }

    public List<DefaultDomainObject> getAllOrdered(DefaultDomainObject ddo) throws SQLException {
        return dbBroker.getAllOrdered(ddo);
    }
}
