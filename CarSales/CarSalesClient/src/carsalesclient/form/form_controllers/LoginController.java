/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.LoginForm;
import carsalesclient.form.form_coordinator.Coordinator;
import domain.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalBorders;

/**
 *
 * @author user
 */
public class LoginController {
    private final LoginForm loginForm; 

    public LoginController(LoginForm loginForm) {
        this.loginForm = loginForm;
        addListeners();
    }
    
    public void openForm(){
        loginForm.setVisible(true);
    }

    private void addListeners() {
        loginForm.btnLoginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
            
            private void login(){
                if(!emptyFields()){
                    User user = new User();
                    user.setUsername(loginForm.getTxtUsername().getText());
                    user.setPassword(String.valueOf(loginForm.getTxtPassword().getPassword()));
                    
                    try {
                        User loggedInUser = ClientController.getInstance().login(user);
                        JOptionPane.showMessageDialog(loginForm, "Login successfull! \nWelcome "+ loggedInUser.getFirstName() +"!");
                        loginForm.dispose();
                        Coordinator.getInstance().addParam("Logged_in_user", loggedInUser);
                        Coordinator.getInstance().openMainForm();
                                
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(loginForm, ex.getMessage());
                    }
                }
                else{
                    JOptionPane.showMessageDialog(loginForm, "Fill all required fields");
                }
            }
        });
        
        loginForm.getTxtUsername().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginForm.getBtnLogin().doClick();
            }
        });
        
        loginForm.getTxtPassword().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginForm.getBtnLogin().doClick();
            }
        });
        
        loginForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    ClientController.getInstance().closeCon();
                } catch (Exception ex) {
                    System.out.println("Error: "+ ex.getMessage());
                }
            }
        });
    }
    
    private boolean emptyFields(){
        Border border = new LineBorder(Color.red, 1,true);
        Font font = new Font("Gill Sans MT", 1, 8);
        boolean empty = false;
        
        
        if(loginForm.getTxtUsername().getText().isBlank()){
            loginForm.getTxtUsername().setBorder(new TitledBorder(border, "Required Field", 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            loginForm.getTxtUsername().setBorder(new MetalBorders.TextFieldBorder());
                
        }
        if(String.valueOf(loginForm.getTxtPassword().getPassword()).isBlank()){
            loginForm.getTxtPassword().setBorder(new TitledBorder(border, "Required Field", 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            loginForm.getTxtPassword().setBorder(new MetalBorders.TextFieldBorder());
                
        }
        
        return empty;
    }
    
}
