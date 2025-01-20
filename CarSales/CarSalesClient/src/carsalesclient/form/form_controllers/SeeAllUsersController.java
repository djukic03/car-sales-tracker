/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.UsersTableForm;
import carsalesclient.form.tableModels.UsersTableModel;
import domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class SeeAllUsersController {
    private final UsersTableForm usersTableForm;

    public SeeAllUsersController(UsersTableForm usersTableForm) {
        this.usersTableForm = usersTableForm;
        addListeners();
    }
    
    public void openForm(){
        fillTable();
        usersTableForm.setVisible(true);
    }
    
    private void addListeners() {
        usersTableForm.btnSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUsers();
            }
            private void searchUsers(){
                try {
                    if (usersTableForm.getTxtSearch().getText().isBlank()) {
                        JOptionPane.showMessageDialog(usersTableForm, "Please enter user's last name for search");
                        return;
                    }
                    User user = new User();
                    user.setSearchCondition("last_name");
                    user.setSearchConditionValue(usersTableForm.getTxtSearch().getText());
                    List<User> users = ClientController.getInstance().searchUsers(user);
                    usersTableForm.getTblUsers().setModel(new UsersTableModel(users));
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        usersTableForm.getTxtSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersTableForm.getBtnSearch().doClick();
            }
        });
    }
    
    private void fillTable() {
        try {
            List<User> users = ClientController.getInstance().getAllUsers();
            usersTableForm.getTblUsers().setModel(new UsersTableModel(users));
        } catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
