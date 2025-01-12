/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.controllers;

import domain.User;
import form.ServerForm;
import form.coordinator.Coordinator;
import form.tableModel.LoggedInUsersTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import thread.ServerThread;

/**
 *
 * @author user
 */
public class ServerFormController {
    private final ServerForm serverForm;
    private ServerThread serverThread;

    public ServerFormController(ServerForm serverForm) {
        this.serverForm = serverForm;
        addListeners();
    }

    public ServerForm getServerForm() {
        return serverForm;
    }

    public void openForm() {
        prepareForm();
        fillLoggedInUsersTable();
        serverForm.setVisible(true);
    }

    private void addListeners() {
        serverForm.btnStartServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
            
            private void startServer(){
                if(serverThread == null || !serverThread.isAlive()){
                    try {
                        serverThread = new ServerThread();
                        serverThread.start();
                        serverForm.getBtnStopServer().setEnabled(true);
                        serverForm.getBtnStartServer().setEnabled(false);
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        serverForm.btnStopServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopServer();
            }
            
            private void stopServer(){
                if (serverThread == null || serverThread.getServerSocket().isBound()) {
                    try {
                        serverThread.getServerSocket().close();
                        serverForm.getBtnStopServer().setEnabled(false);
                        serverForm.getBtnStartServer().setEnabled(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        serverForm.miDBConfigAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openDBConfigForm();
            }
        });
    }
    
    private void fillLoggedInUsersTable() {
        serverForm.getTblLoggedInUsers().setModel(new LoggedInUsersTableModel(new ArrayList<>()));
    }

    public void addLoggedInUser(User user) {
        LoggedInUsersTableModel tm =(LoggedInUsersTableModel) serverForm.getTblLoggedInUsers().getModel();
        tm.addLoggedInUser(user);
    }    

    public void removeLoggedInUser(User user) {
        LoggedInUsersTableModel tm =(LoggedInUsersTableModel) serverForm.getTblLoggedInUsers().getModel();
        tm.removeLoggedInUser(user);
    }

    private void prepareForm() {
        serverForm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        serverForm.getBtnStopServer().setEnabled(false);
    }
}
