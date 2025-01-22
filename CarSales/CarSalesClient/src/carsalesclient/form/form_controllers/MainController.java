/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.MainForm;
import carsalesclient.form.constants.CoordinatorParamConsts;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.AddFormMode;
import carsalesclient.form.modes.TableFormMode;
import domain.User;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class MainController {
    private final MainForm mainForm;

    public MainController(MainForm mainForm) {
        this.mainForm = mainForm;
        addActionListeners();
    }

    public MainForm getMainForm() {
        return mainForm;
    }
    
    public void openForm(){
        User user = (User) Coordinator.getInstance().getParam(CoordinatorParamConsts.LOGGED_IN_USER);
        mainForm.getLblMain().setText("Welcome "+user.getFirstName() + " " + user.getLastName());
        mainForm.setVisible(true);
    }

    private void addActionListeners() {
        mainForm.miCreateNewInvoiceAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddInvoiceForm();
            }
        });
        
        mainForm.miSeeAllInvoicesAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openInvoicesTableForm();
            }
        });
        
        mainForm.miAddNewSalesmanAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddUserForm(AddFormMode.ADD_FORM);
            }
        });
        
        mainForm.miSeeAllSalesmenAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openUsersTableForm();
            }
        });
        
        mainForm.miAddNewCarAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddCarForm(AddFormMode.ADD_FORM);
            }
        });
        
        mainForm.miSeeAllCarsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openCarsTableForm(TableFormMode.SEE_ALL_ITEMS);
            }
        });
        
        mainForm.miAddNewCustomerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddCustomerForm(AddFormMode.ADD_FORM);
            }
        });
        
        mainForm.miSeeAllCustomersAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openCustomersTableForm(TableFormMode.SEE_ALL_ITEMS);
            }
        });
        
        mainForm.miEnglishAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mainForm.getMenuItemEnglish().isSelected()) {
                    mainForm.getMenuItemSerbian().setSelected(false);
                    Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_LANGUAGE, "en");
                    changeLanguage();
                }
                else{
                    JOptionPane.showMessageDialog(mainForm, "English is already selected");
                    mainForm.getMenuItemEnglish().setSelected(true);
                }
                
            }
        });
        
        mainForm.miSerbianAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mainForm.getMenuItemSerbian().isSelected()) {
                    mainForm.getMenuItemEnglish().setSelected(false);
                    Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_LANGUAGE, "sr");
                    changeLanguage();
                }
                else{
                    JOptionPane.showMessageDialog(mainForm, "Српски је већ изабран");
                    mainForm.getMenuItemSerbian().setSelected(true);
                }
                
            }
        });
        
        
        mainForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
//                    ClientController.getInstance().closeCon();
                    System.out.println("log outujemo usera "+((User) Coordinator.getInstance().getParam(CoordinatorParamConsts.LOGGED_IN_USER)).getFirstName());
                    ClientController.getInstance().logout((User) Coordinator.getInstance().getParam(CoordinatorParamConsts.LOGGED_IN_USER));
//Ako smislim nesto bolje za logout - dodaj SO
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }
    
    private void changeLanguage(){
        String language = Coordinator.getInstance().getParam(CoordinatorParamConsts.SELECTED_LANGUAGE).toString();
        System.out.println(language);
        Locale locale = Locale.of(language);
        ResourceBundle bundle = ResourceBundle.getBundle("resources.language", locale);
        
        try {
            InputStream is = MainController.class.getResourceAsStream("/resources/Andika-Regular.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(13f);
            mainForm.getMenuItemCreateNewInvoice().setText(bundle.getString("Create_New_Invoice"));
            mainForm.getMenuItemCreateNewInvoice().setFont(customFont);
        } catch (Exception e) {
        }
    }
}
