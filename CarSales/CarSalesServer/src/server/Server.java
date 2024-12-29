/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domain.User;
import form.coordinator.Coordinator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import thread.ProcessClientRequests;

/**
 *
 * @author user
 */
public class Server extends Thread{
    ServerSocket serverSocket;
    private List<ProcessClientRequests> clientThreads;
    boolean threadEnd = false;
    
    
    private List<ProcessClientRequests> loggedInUsers;
//    private int numberOfClients;

    public Server() {
        clientThreads = new ArrayList<>();
    }
    
    public List<ProcessClientRequests> getClientThreads() {
        return clientThreads;
    }

    public void setClientThreads(List<ProcessClientRequests> clientThreads) {
        this.clientThreads = clientThreads;
    }
    
    @Override
    public void run() {
        System.out.println("Server Thread started");
        try {
            serverSocket = new ServerSocket(9000);
            Socket socket;
            while (!threadEnd) {              
                System.out.println("Waiting for connection...");
                socket = serverSocket.accept();
                System.out.println("Connected with client");
                ProcessClientRequests pcr = new ProcessClientRequests(this, socket);
                clientThreads.add(pcr);
                pcr.start();
            }
        } catch (Exception ex) {
            System.out.println("Server never started, error: " + ex.getMessage());
        }
        System.out.println("Server thread ended");
    }

    
    public void login(ProcessClientRequests pcr) {
        System.out.println("User "+pcr.getLoggedInUser().getFirstName()+" is logging in");
        loggedInUsers.add(pcr);
        Coordinator.getInstance().getServerFormController().addLoggedInUser(pcr.getLoggedInUser());
    }

    public boolean alreadyLoggedIn(User user) {
        List<User> users = new LinkedList<>();
        loggedInUsers.forEach(x -> users.add(x.getLoggedInUser()));
        
        return users.contains(user);
    }

    public void logout(ProcessClientRequests pcr) {
        if (loggedInUsers.contains(pcr)){
            System.out.println("User " + pcr.getLoggedInUser().getFirstName() + " logged out");
            loggedInUsers.remove(pcr);
            Coordinator.getInstance().getServerFormController().removeLoggedInUser(pcr.getLoggedInUser());
        }
        clientThreads.remove(pcr);
    }
}
