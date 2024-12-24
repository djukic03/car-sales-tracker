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
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public class Controller {
    private static Controller instance;
    private final Socket socket;
    private final Sender sender;
    private final Receiver receiver;

    private Controller() throws IOException {
        socket = new Socket("127.0.0.1", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    public static Controller getInstance() throws IOException{
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    public List<DefaultDomainObject> getAll(DefaultDomainObject ddo) throws SQLException, Exception{
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
    
    public List<DefaultDomainObject> getAllOrdered(DefaultDomainObject ddo) throws SQLException, Exception {
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
    
    public List<DefaultDomainObject> getByCondition(DefaultDomainObject ddo) throws SQLException, Exception {
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
    
    public void insertRow(DefaultDomainObject ddo) throws SQLException, Exception {
        Request request = new Request(Operation.INSERT_ROW, ddo);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
    
    public Long insertRowAndGetId(DefaultDomainObject ddo) throws SQLException, Exception {
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
    
    public void deleteRow(DefaultDomainObject ddo) throws SQLException, Exception {
        Request request = new Request(Operation.DELETE_ROW, ddo);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
    
    public void updateRow(DefaultDomainObject ddo) throws SQLException, Exception {
        Request request = new Request(Operation.UPDATE_ROW, ddo);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
    }
    
    public List<String> getAllCarBrands() throws SQLException, Exception{
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

    
}
