/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.controllers;

import com.sun.java.accessibility.util.AWTEventMonitor;
import form.ServerForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        serverForm.setVisible(true);
    }

    private void addListeners() {
        serverForm.btnStartServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Server server = new Server();
                server.startServer();
            }
        });
    }
    
    
}
