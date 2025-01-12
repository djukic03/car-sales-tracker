/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.controllers;

import database.DatabaseProperties;
import form.DatabaseConfigForm;
import form.coordinator.Coordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class DBConfigController {
    private final DatabaseConfigForm dbConfigForm;

    public DBConfigController(DatabaseConfigForm dbConfigForm) {
        this.dbConfigForm = dbConfigForm;
        addListeners();
    }
    
    public void openForm(){
        prepareForm();
        dbConfigForm.setVisible(true);
    }
    
    private void addListeners() {
        dbConfigForm.btnEnableChangesAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableChanges();
            }
            private void enableChanges(){
                dbConfigForm.getTxtUrl().setEnabled(true);
                dbConfigForm.getTxtUser().setEnabled(true);
                dbConfigForm.getTxtPassword().setEnabled(true);
                dbConfigForm.getBtnEnableChanges().setEnabled(false);
                dbConfigForm.getBtnSave().setEnabled(true);
            }
        });
        
        dbConfigForm.btnSaveAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
            public void saveChanges(){
                String url = dbConfigForm.getTxtUrl().getText().trim();
                String user = dbConfigForm.getTxtUser().getText().trim();
                String password = dbConfigForm.getTxtPassword().getText().trim();
                if (JOptionPane.showConfirmDialog(dbConfigForm, """
                                                                Are you sure you want to save the following changes:
                                                                url: """ + url + "\nuser: "+ user + "\npassword: "+ password, "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    
                    DatabaseProperties.getInstance().setUrl(url);
                    DatabaseProperties.getInstance().setUser(user);
                    DatabaseProperties.getInstance().setPassword(password);
                    JOptionPane.showMessageDialog(dbConfigForm, "Configuration parameters saved successfully!");
                    prepareForm();
                }
            }
        });
    }

    private void prepareForm() {
        dbConfigForm.setLocationRelativeTo(Coordinator.getInstance().getServerFormController().getServerForm());
        DatabaseProperties props = DatabaseProperties.getInstance();
        dbConfigForm.getTxtUrl().setText(props.getProperty("url"));
        dbConfigForm.getTxtUrl().setEnabled(false);
        dbConfigForm.getTxtUser().setText(props.getProperty("user"));
        dbConfigForm.getTxtUser().setEnabled(false);
        dbConfigForm.getTxtPassword().setText(props.getProperty("password"));
        dbConfigForm.getTxtPassword().setEnabled(false);
        dbConfigForm.getBtnEnableChanges().setEnabled(true);
        dbConfigForm.getBtnSave().setEnabled(false);
    }
}
