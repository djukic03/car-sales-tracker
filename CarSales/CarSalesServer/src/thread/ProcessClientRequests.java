/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.ServerController;
import domain.DefaultDomainObject;
import domain.User;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author user
 */
public class ProcessClientRequests extends Thread{
    private final Server server;
    private Socket socket;
    private final Sender sender;
    private final Receiver receiver;
    private User loggedInUser;
//    private final int clientNumber;
    private boolean threadEnd = false;

    public ProcessClientRequests(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
//        this.clientNumber = clientNumber;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public boolean isThreadEnd() {
        return threadEnd;
    }

    public void setThreadEnd(boolean threadEnd) {
        this.threadEnd = threadEnd;
    }

    @Override
    public void run() {
        System.out.println("Client thread started");
        while(!threadEnd){
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    ServerController controller = ServerController.getInstance();
                    switch(request.getOperation()){
                        case Operation.LOGIN:
                            User user = (User) request.getArgument();
                            response.setResult(login(controller.login(user.getUsername(), user.getPassword())));
                            break;
                        case Operation.GET_ALL:
                            response.setResult(controller.getAll((DefaultDomainObject) request.getArgument()));
                            break;
                        case Operation.GET_ALL_ORDERED:
                            response.setResult(controller.getAllOrdered((DefaultDomainObject) request.getArgument()));
                            break;
                        case Operation.GET_BY_CONDITION:
                            response.setResult(controller.getByCondition((DefaultDomainObject) request.getArgument()));
                            break;
                        case Operation.INSERT_ROW:
                            controller.insertRow((DefaultDomainObject) request.getArgument());
                            break;
                        case Operation.INSERT_ROW_AND_GET_ID:
                            response.setResult(controller.insertRowAndGetId((DefaultDomainObject) request.getArgument()));
                            break;
                        case Operation.DELETE_ROW:
                            controller.deleteRow((DefaultDomainObject) request.getArgument());
                            break;
                        case Operation.UPDATE_ROW:
                            controller.updateRow((DefaultDomainObject) request.getArgument());
                            break;
                        case Operation.GET_ALL_CAR_BRANDS:
                            response.setResult(controller.getAllCarBrands());
                            break;
                        case Operation.CLOSE_CON:
                            controller.closeCon();
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            } catch (Exception ex) {
                System.out.println("client disconected.");
                logout();
            }
        }
        System.out.println("Client thread ended");
    }

    private User login(User user) throws Exception {
        alreadyLoggedIn(user);
        this.loggedInUser = user;
        server.login(this);
        return user;
    }

    private void logout() {
        try {
            server.logout(this);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProcessClientRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void alreadyLoggedIn(User user) throws Exception {
        if(server.alreadyLoggedIn(user)){
            throw new Exception("You are already logged in!");
        }
    }
    
}
