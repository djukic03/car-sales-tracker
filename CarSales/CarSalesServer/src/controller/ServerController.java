/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.DatabaseBroker;
import domain.Car;
import domain.DefaultDomainObject;
import domain.User;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;
import so.car.DeleteCarSO;
import so.car.GetAllCarsSO;
import so.car.GetCarBrandsSO;
import so.car.SearchCarsSO;
import so.user.GetAllUsersSO;
import so.user.LoginUserSO;
import so.user.SearchUsersSO;

/**
 *
 * @author user
 */
public class ServerController {
    private static ServerController instance;
    private final DatabaseBroker dbBroker;
    private final List<User> loggedInUsers;

    private ServerController() {
        dbBroker = new DatabaseBroker();
        loggedInUsers = new ArrayList<>();
    }
    
    public static ServerController getInstance(){
        if(instance == null){
            instance = new ServerController();
        }
        return instance;
    }
    
    public User login(String username, String password) throws Exception {
        LoginUserSO lu = new LoginUserSO();
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        lu.executeSO(u);
        User loggedInUser = lu.getLoggedInUser();
        if (!loggedInUsers.contains(loggedInUser)) {
            loggedInUsers.add(loggedInUser);
            return loggedInUser;
        }
        throw new Exception("You are already logged in");
    }
    
    public void logout(User user){
        if (loggedInUsers.contains(user)) {
            loggedInUsers.remove(user);
        }
    }
    
    public List<DefaultDomainObject> getAll(DefaultDomainObject ddo) throws SQLException{
        return dbBroker.getAll(ddo);
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    public List<DefaultDomainObject> getAllUsers() throws Exception{
        GetAllUsersSO so = new GetAllUsersSO();
        so.executeSO(null);
        return so.getUsers();
    }
    
    public List<DefaultDomainObject> getAllCars() throws Exception{
        GetAllCarsSO so = new GetAllCarsSO();
        so.executeSO(null);
        return so.getCars();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    public List<DefaultDomainObject> getByCondition(DefaultDomainObject ddo) throws SQLException {
        return dbBroker.getByCondition(ddo);
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public List<DefaultDomainObject> searchUsers(User u) throws Exception {
        SearchUsersSO so = new SearchUsersSO();
        so.executeSO(u);
        return so.getUsers();
    }
    
    public List<DefaultDomainObject> searchCars(Car car) throws Exception {
        SearchCarsSO so = new SearchCarsSO();
        so.executeSO(car);
        return so.getCars();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    public void insertRow(DefaultDomainObject ddo) throws SQLException {
        dbBroker.insertRow(ddo);
    }
    
    public Long insertRowAndGetId(DefaultDomainObject ddo) throws SQLException {
        return dbBroker.insertRowAndGetId(ddo);
    }
    
    public void deleteRow(DefaultDomainObject ddo) throws SQLException {
        dbBroker.deleteRow(ddo);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteCar(Car car) throws Exception {
        DeleteCarSO so = new DeleteCarSO();
        so.executeSO(car);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void updateRow(DefaultDomainObject ddo) throws SQLException {
        dbBroker.updateRow(ddo);
    }
    
    public List<String> getAllCarBrands() throws Exception{
        GetCarBrandsSO so = new GetCarBrandsSO();
        so.executeSO(null);
        return so.getBrands();
    }
    
    public void closeCon(){
        dbBroker.closeCon();
    }

    public List<DefaultDomainObject> getAllOrdered(DefaultDomainObject ddo) throws SQLException {
        return dbBroker.getAllOrdered(ddo);
    }
}
