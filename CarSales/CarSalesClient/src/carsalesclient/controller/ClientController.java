/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.controller;

import domain.DefaultDomainObject;
import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Car;
import domain.Customer;
import domain.User;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author user
 */
public class ClientController {
    private static ClientController instance;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private ClientController() throws IOException {
        socket = new Socket("127.0.0.1", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    public static ClientController getInstance() throws IOException{
        if(instance == null){
            instance = new ClientController();
        }
        return instance;
    }
    
    public User login(User user) throws Exception {
        Request request = new Request(Operation.LOGIN, user);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (User) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    
    
    
    
    
    
    
    
    
    
    public List<DefaultDomainObject> getAll(DefaultDomainObject ddo) throws Exception{
        Request request = new Request(Operation.GET_ALL, ddo);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (List<DefaultDomainObject>) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////
    
    public List<User> getAllUsers() throws Exception{
        Request request = new Request(Operation.GET_ALL_USERS, null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (List<User>) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    
    public List<Car> getAllCars() throws Exception{
        Request request = new Request(Operation.GET_ALL_CARS, null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (List<Car>) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    
    public List<Customer> getAllCustomers() throws Exception{
        Request request = new Request(Operation.GET_ALL_CUSTOMERS, null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (List<Customer>) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    
    /////////////////////////////////////////////////////////////////////////////////
    
    
    public List<DefaultDomainObject> getAllOrdered(DefaultDomainObject ddo) throws Exception {
        Request request = new Request(Operation.GET_ALL_ORDERED, ddo);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (List<DefaultDomainObject>) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    public List<DefaultDomainObject> getByCondition(DefaultDomainObject ddo) throws Exception {
        Request request = new Request(Operation.GET_BY_CONDITION, ddo);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (List<DefaultDomainObject>) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<User> searchUsers(User u) throws Exception {
        Request request = new Request(Operation.SEARCH_USERS, u);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (List<User>) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    
    public List<Car> searchCars(Car car) throws Exception {
        Request request = new Request(Operation.SEARCH_CARS, car);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (List<Car>) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    
    public List<Customer> searchCustomers(Customer customer) throws Exception {
        Request request = new Request(Operation.SEARCH_CUSTOMERS, customer);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (List<Customer>) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    
    
    
    
    
    
    
    public void insertRow(DefaultDomainObject ddo) throws Exception {
        Request request = new Request(Operation.INSERT_ROW, ddo);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void insertCustomer(Customer customer) throws Exception {
        Request request = new Request(Operation.INSERT_CUSTOMER, customer);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
    
    public void insertCar(Car car) throws Exception {
        Request request = new Request(Operation.INSERT_CAR, car);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public Long insertRowAndGetId(DefaultDomainObject ddo) throws Exception {
        Request request = new Request(Operation.INSERT_ROW_AND_GET_ID, ddo);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (Long) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public void deleteRow(DefaultDomainObject ddo) throws Exception {
        Request request = new Request(Operation.DELETE_ROW, ddo);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteCar(Car car) throws Exception {
        Request request = new Request(Operation.DELETE_CAR, car);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
    
    public void deleteCustomer(Customer customer) throws Exception {
        Request request = new Request(Operation.DELETE_CUSTOMER, customer);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void updateRow(DefaultDomainObject ddo) throws Exception {
        Request request = new Request(Operation.UPDATE_ROW, ddo);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateCustomer(Customer customer) throws Exception {
        Request request = new Request(Operation.UPDATE_CUSTOMER, customer);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
    
    public void updateCar(Car car) throws Exception {
        Request request = new Request(Operation.UPDATE_CAR, car);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    
    
    
    
    
    
    
    
    public List<String> getAllCarBrands() throws Exception{
        Request request = new Request(Operation.GET_ALL_CAR_BRANDS, null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() == null){
            return (List<String>) response.getResult();
        }
        else{
            throw response.getException();
        }
    }
    
    public void closeCon() throws Exception{
        Request request = new Request(Operation.CLOSE_CON, null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }

    public void logout(User user) throws Exception {
        Request request = new Request(Operation.LOG_OUT, user);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }

}
