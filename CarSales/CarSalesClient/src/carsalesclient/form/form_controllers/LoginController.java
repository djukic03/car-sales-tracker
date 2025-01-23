/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.LoginForm;
import carsalesclient.form.constants.CoordinatorParamConsts;
import carsalesclient.form.form_coordinator.Coordinator;
import domain.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
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
    private ResourceBundle bundle;

    public LoginController(LoginForm loginForm) {
        this.loginForm = loginForm;
        addListeners();
    }
    
    public void openForm(){
        prepareForm();
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
                        JOptionPane.showMessageDialog(loginForm, bundle.getString("Login_successfull!_Welcome")+" "+ loggedInUser.getFirstName() +"!");
                        loginForm.dispose();
                        Coordinator.getInstance().addParam(CoordinatorParamConsts.LOGGED_IN_USER, loggedInUser);
                        Coordinator.getInstance().openMainForm();
                                
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(loginForm, ex.getMessage());
                    }
                }
                else{
                    JOptionPane.showMessageDialog(loginForm, bundle.getString("Fill_all_required_fields"));
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
        
        loginForm.cbLanguageAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectLanguage();
            }

            private void selectLanguage() {
                Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_LANGUAGE, loginForm.getCbLanguage().getSelectedItem().toString());
                setLanguage();
            }
        });
        
        loginForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
//                    ClientController.getInstance().closeCon();
                    ClientController.getInstance().logout(null);
//dodaj logoutSO ako smislis nesto bolje
                } catch (Exception ex) {
                    System.out.println("Error: "+ ex.getMessage());
                }
            }
        });
    }
    
    private boolean emptyFields(){
        Border border = new LineBorder(Color.red, 1,true);
        Font font = new Font("Comic Sans MS", 1, 9);
        boolean empty = false;
        
        
        if(loginForm.getTxtUsername().getText().isBlank()){
            loginForm.getTxtUsername().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            loginForm.getTxtUsername().setBorder(new MetalBorders.TextFieldBorder());
                
        }
        if(String.valueOf(loginForm.getTxtPassword().getPassword()).isBlank()){
            loginForm.getTxtPassword().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            loginForm.getTxtPassword().setBorder(new MetalBorders.TextFieldBorder());
                
        }
        
        return empty;
    }

    private void prepareForm() {
        Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_LANGUAGE, "en");
        setLanguage();
    }

    private void setLanguage() {
        String language = Coordinator.getInstance().getParam(CoordinatorParamConsts.SELECTED_LANGUAGE).toString();
        Locale locale = Locale.of(language);
        bundle = ResourceBundle.getBundle("resources.language", locale);


        loginForm.getLblTitle().setText(bundle.getString("LOGIN"));
        loginForm.getLblUsername().setText(bundle.getString("Username")+":");
        loginForm.getLblPassword().setText(bundle.getString("Password")+":");
        loginForm.getBtnLogin().setText(bundle.getString("Login"));
        loginForm.setTitle(bundle.getString("Login_form"));
    }
    
}
