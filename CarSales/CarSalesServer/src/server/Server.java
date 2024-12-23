/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import carsalesapplication.database.DatabaseBroker;
import carsalesapplication.domain.DefaultDomainObject;
import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Server {
    private DatabaseBroker broker;
    private Receiver receiver;
    private Sender sender;

    public Server() {
        broker = new DatabaseBroker();
    }
    
    public void startServer(){
        try {
            System.out.println("Usao u try");
            ServerSocket ss = new ServerSocket(9000);
            System.out.println("Cekam klijenta - server je pokrenut");
            Socket socket = ss.accept();
            System.out.println("Klijent povezan");
            
            receiver = new Receiver(socket);
            sender = new Sender(socket);
            while(true){
                try {
                    Request request = (Request) receiver.receive();
                    Response response = new Response();
                    
                    Operation operation = request.getOperation();
                    if(Operation.GETALL == operation){
                        DefaultDomainObject ddo = (DefaultDomainObject) request.getArgument();
                        try {
                            List<DefaultDomainObject> allObjects = broker.getAll(ddo);
                            response.setResult(allObjects);
                        } catch (SQLException e) {
                            response.setException(e);
                        }
                    }
                    sender.send(response);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
