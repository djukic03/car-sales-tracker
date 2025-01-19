/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_coordinator;

import carsalesclient.form.AddCarForm;
import carsalesclient.form.AddCustomerForm;
import carsalesclient.form.AddInvoiceForm;
import carsalesclient.form.CarsTableForm;
import carsalesclient.form.CustomersTableForm;
import carsalesclient.form.LoginForm;
import carsalesclient.form.MainForm;
import carsalesclient.form.UsersTableForm;
import carsalesclient.form.constants.CoordinatorParamConsts;
import carsalesclient.form.form_controllers.CarController;
import carsalesclient.form.form_controllers.CustomerController;
import carsalesclient.form.form_controllers.LoginController;
import carsalesclient.form.form_controllers.MainController;
import carsalesclient.form.form_controllers.InvoiceController;
import carsalesclient.form.form_controllers.SeeAllCarsController;
import carsalesclient.form.form_controllers.SeeAllCustomersController;
import carsalesclient.form.form_controllers.SeeAllUsersController;
import carsalesclient.form.modes.AddFormMode;
import carsalesclient.form.modes.TableFormMode;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class Coordinator {
    private static Coordinator instance;
    private final MainController mainController;
    private final Map<CoordinatorParamConsts, Object> params;

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
    
    public void addParam(CoordinatorParamConsts name, Object key){
        params.put(name, key);
    }
    
    public Object getParam(CoordinatorParamConsts name){
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
        SeeAllUsersController controller = new SeeAllUsersController(new UsersTableForm(mainController.getMainForm(), true));
        controller.openForm();
    }

    public void openAddCarForm(AddFormMode formMode) {
        CarController controller = new CarController(new AddCarForm(mainController.getMainForm(), true));
        controller.openForm(formMode);
    }

    public void openCarsTableForm(TableFormMode formMode) {
        SeeAllCarsController controller = new SeeAllCarsController(new CarsTableForm(mainController.getMainForm(), true));
        controller.openForm(formMode);
    }

    public void openAddCustomerForm(AddFormMode formMode) {
        CustomerController controller = new CustomerController(new AddCustomerForm(mainController.getMainForm(), true));
        controller.openForm(formMode);
    }

    public void openCustomersTableForm(TableFormMode formMode) {
        SeeAllCustomersController controller = new SeeAllCustomersController(new CustomersTableForm(mainController.getMainForm(), true));
        controller.openForm(formMode);
    }
}
