/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.controllers;

import com.sun.java.accessibility.util.AWTEventMonitor;
import domain.User;
import form.ServerForm;
import form.tableModel.LoggedInUsersTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import server.Server;

/**
 *
 * @author user
 */
public class ServerFormController {
    private final ServerForm serverForm;

    public ServerFormController(ServerForm serverForm) {
        this.serverForm = serverForm;
        addListeners();
    }

    public ServerForm getServerForm() {
        return serverForm;
    }

    public void openForm() {
        fillLoggedInUsersTable();
        serverForm.setVisible(true);
    }

    private void addListeners() {
        serverForm.btnStartServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Server server = new Server();
                    server.start();
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                
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
}
