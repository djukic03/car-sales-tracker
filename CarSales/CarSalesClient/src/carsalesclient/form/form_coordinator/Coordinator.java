/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_coordinator;

import carsalesclient.form.AddInvoiceForm;
import carsalesclient.form.LoginForm;
import carsalesclient.form.MainForm;
import carsalesclient.form.form_controllers.LoginController;
import carsalesclient.form.form_controllers.MainController;
import carsalesclient.form.form_controllers.InvoiceController;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class Coordinator {
    private static Coordinator instance;
    private final MainController mainController;
    private final Map<String, Object> params;

    private Coordinator() {
        mainController = new MainController(new MainForm());
        params = new HashMap<>();
    }
    
    public static Coordinator getInstance(){
        if (instance == null) {
            instance = new Coordinator();
        }
        return instance;
    }
    
    public void openLoginForm(){
        LoginController loginController = new LoginController(new LoginForm());
        loginController.openForm();
    }
    
    public void openMainForm(){
        mainController.openForm();
    }
    
    public MainController getMainController(){
        return mainController;
    }
    
    public void addParam(String name, Object key){
        params.put(name, key);
    }
    
    public Object getParam(String name){
        return params.get(name);
    }

    public void openAddInvoiceForm() {
        InvoiceController controller = new InvoiceController(new AddInvoiceForm(mainController.getMainForm(), true));
        controller.openForm();
    }

    public void openInvoicesTableForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void openUsersTableForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void openAddCarForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void openCarsTableForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void openAddCustomerForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void openCustomersTableForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
