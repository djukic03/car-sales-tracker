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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        prepareForm();
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
                if (mainForm.getMenuItemEnglish().isSelected()) {
                    mainForm.getMenuItemSerbian().setSelected(false);
                    Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_LANGUAGE, "en");
                    setLanguage();
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
                if (mainForm.getMenuItemSerbian().isSelected()) {
                    mainForm.getMenuItemEnglish().setSelected(false);
                    Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_LANGUAGE, "sr");
                    setLanguage();
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
                    ClientController.getInstance().logout((User) Coordinator.getInstance().getParam(CoordinatorParamConsts.LOGGED_IN_USER));
//Ako smislim nesto bolje za logout - dodaj SO
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }
    
    private void setLanguage(){
        String language = Coordinator.getInstance().getParam(CoordinatorParamConsts.SELECTED_LANGUAGE).toString();
        Locale locale = Locale.of(language);
        ResourceBundle bundle = ResourceBundle.getBundle("resources.language", locale);
        
        mainForm.setTitle(bundle.getString("Main_form"));
        
        User user = (User) Coordinator.getInstance().getParam(CoordinatorParamConsts.LOGGED_IN_USER);
        mainForm.getLblMain().setText(bundle.getString("Welcome") + " "+user.getFirstName() + " " + user.getLastName());
        
        mainForm.getMenuInvoice().setText(bundle.getString("Invoices"));
            mainForm.getMenuItemCreateNewInvoice().setText(bundle.getString("Create_New_Invoice"));
            mainForm.getMenuItemSeeAllInvoices().setText(bundle.getString("See_All_Invoices"));
                
        mainForm.getMenuSalespersons().setText(bundle.getString("Salespersons"));
            mainForm.getMenuItemAddSalesman().setText(bundle.getString("Add_New_Salesperson"));
            mainForm.getMenuItemSeeAllSalesmen().setText(bundle.getString("See_All_Salespersons"));
                
        mainForm.getMenuCars().setText(bundle.getString("Cars"));
            mainForm.getMenuItemAddNewCar().setText(bundle.getString("Add_New_Car"));
            mainForm.getMenuItemSeeAllCars().setText(bundle.getString("See_All_Cars"));
                
        mainForm.getMenuCustomers().setText(bundle.getString("Customers"));
            mainForm.getMenuItemAddNewCustomer().setText(bundle.getString("Add_New_Customer"));
            mainForm.getMenuItemSeeAllCustomers().setText(bundle.getString("See_All_Customers"));
                
        mainForm.getMenuOptions().setText(bundle.getString("Options"));
            mainForm.getMenuItemLanguage().setText(bundle.getString("Language"));
            mainForm.getMenuItemLogOut().setText(bundle.getString("Log_Out"));
    }

    private void prepareForm() {
        if(Coordinator.getInstance().getParam(CoordinatorParamConsts.SELECTED_LANGUAGE).toString().equals("en")){
            mainForm.getMenuItemEnglish().setSelected(true);
            mainForm.getMenuItemSerbian().setSelected(false);
        }
        else{
            mainForm.getMenuItemEnglish().setSelected(false);
            mainForm.getMenuItemSerbian().setSelected(true);
        }
        setLanguage();
    }
}
