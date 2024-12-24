/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.net.ServerSocket;
import java.net.Socket;
import thread.ProcessClientRequests;

/**
 *
 * @author user
 */
public class Server {
    
    public void startServer(){
        try {
            ServerSocket ss = new ServerSocket(9000);
            while (true) {                
                System.out.println("Waiting for connection...");
                Socket socket = ss.accept();
                System.out.println("Connected with client");
                handleClient(socket);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleClient(Socket socket) {
        ProcessClientRequests pcr = new ProcessClientRequests(socket);
        pcr.start();
    }
}
