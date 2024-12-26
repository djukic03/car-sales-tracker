/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.Controller;
import carsalesclient.form.AddInvoiceForm;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.FormMode;
import carsalesclient.form.tableModels.CarsTableModel;
import carsalesclient.form.tableModels.CustomersTableModel;
import carsalesclient.form.tableModels.InvoiceItemsTableModel;
import domain.Car;
import domain.Customer;
import domain.DefaultDomainObject;
import domain.Invoice;
import domain.InvoiceItem;
import domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author user
 */
public class InvoiceController {
    private final AddInvoiceForm invoiceForm;
    private Customer customer;

    public InvoiceController(AddInvoiceForm invoiceForm) {
        this.invoiceForm = invoiceForm;
        addListeners();
    }
    
    public void openForm(){
        fillComboBox();
        fillCustomersTable();
        fillCarsTable(null);
        fillInvoiceItemsTable();
        fillDate();
        invoiceForm.setVisible(true);
    }

    private void addListeners() {
        invoiceForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillCarsTable(null);
                fillCustomersTable();
            }
        });
        
        invoiceForm.btnCustomerSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomers();
            }
            
            private void searchCustomers(){
                try {
                    if(!invoiceForm.getTxtCustomerSearchCondition().getText().isBlank()){
                        Customer customer = new Customer();
                        customer.setSearchCondition("name");
                        customer.setSearchConditionValue(invoiceForm.getTxtCustomerSearchCondition().getText());
                        List<DefaultDomainObject> allCustomers = Controller.getInstance().getByCondition(customer);
                        List<Customer> customers = new ArrayList<>();
                        for (DefaultDomainObject c : allCustomers) {
                            customers.add((Customer) c);
                        }
                        invoiceForm.getTblCustomers().setModel(new CustomersTableModel(customers));
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                
            }
        });
        invoiceForm.getTxtCustomerSearchCondition().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoiceForm.getBtnCustomerSearch().doClick();
            }
        });
        
        invoiceForm.btnAddNewCustomerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddCustomerForm(FormMode.ADD_FORM);
            }
        });
        
        invoiceForm.btnSelectCustomerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCustomer();
            }
            
            private void selectCustomer(){
                int rowId = invoiceForm.getTblCustomers().getSelectedRow();
                if (rowId < 0) {
                    JOptionPane.showMessageDialog(invoiceForm, "Please select customer!");
                    return;
                }
                Customer customer = ((CustomersTableModel) invoiceForm.getTblCustomers().getModel()).getCustomerAt(rowId);
                invoiceForm.getTxtSelectedCustomer().setText(customer.getName() + ", " + customer.getPhone() + ", " + customer.getEmail());
                setCustomer(customer);
            }
        });
        
        invoiceForm.cbCarSearchConditionAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCars();
            }
            
            private void searchCars(){
                try {
                    invoiceForm.getTblCars().clearSelection();
                    String conditionValue = invoiceForm.getCbCarSearchCondition().getSelectedItem().toString();
                    if(conditionValue.equals("All brands")){
                        fillCarsTable(null);
                    }
                    else{
                        Car car = new Car();
                        car.setSearchCondition("brand");
                        car.setSearchConditionValue(conditionValue);
                        List<DefaultDomainObject> c = Controller.getInstance().getByCondition(car);
                        List<Car> cars = new ArrayList<>();
                        for (DefaultDomainObject i : c) {
                            cars.add((Car) i);
                        }
                        fillCarsTable(cars);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        
        invoiceForm.btnAddNewCarAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddCarForm(FormMode.ADD_FORM);
            }
        });
        
        invoiceForm.btnAddInvoiceItemAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addInvoiceItem();
            }
            
            private void addInvoiceItem(){
                int rowId = invoiceForm.getTblCars().getSelectedRow();
                if(rowId < 0){
                    JOptionPane.showMessageDialog(invoiceForm, "Please select car to add to invoice!");
                    return;
                }
                Car car = ((CarsTableModel) invoiceForm.getTblCars().getModel()).getCarAt(rowId);
                int quantity = Integer.parseInt(invoiceForm.getTxtQuantity().getText());
                InvoiceItemsTableModel model = (InvoiceItemsTableModel) invoiceForm.getTblInvoiceItems().getModel();
                model.addInvoiceItem(quantity, car.getPrice(), car.getPrice()*quantity, car);
                fillTotalAmount();
            }
        });
        invoiceForm.getTxtQuantity().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoiceForm.getBtnAddInvoiceItem().doClick();
            }
        });
        
        invoiceForm.btnRemoveItemAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeInvoiceItem();
            }
            
            private void removeInvoiceItem(){
                int rowId = invoiceForm.getTblInvoiceItems().getSelectedRow();
                if(rowId < 0){
                    JOptionPane.showMessageDialog(invoiceForm, "Please select item to remove from invoice");
                    return;
                }
                InvoiceItemsTableModel model = (InvoiceItemsTableModel) invoiceForm.getTblInvoiceItems().getModel();
                model.removeInvoiceItem(rowId);
                fillTotalAmount();
            }
        });
        
        invoiceForm.btnSaveAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveInvoice();
            }
            
            private void saveInvoice(){
                try {
                    if(invoiceForm.getTxtSelectedCustomer().getText().isEmpty()){
                        JOptionPane.showMessageDialog(invoiceForm, "Please select customer");
                        return;
                    }
                    if(invoiceForm.getTblInvoiceItems().getRowCount() == 0){
                        JOptionPane.showMessageDialog(invoiceForm, "Please select car(s)");
                        return;
                    }
                    SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
                    Date d = formater.parse(invoiceForm.getTxtDate().getText());
                    Invoice invoice = new Invoice(null, d, Double.valueOf(invoiceForm.getTxtTotalAmount().getText()),((User) Coordinator.getInstance().getParam("Logged_in_user")).getIdUser(), customer.getIdCustomer());

                    if(JOptionPane.showConfirmDialog(invoiceForm, "Are you sure you want to CREATE and INSERT this invoice into the database? \n Please check all the data before clicking Yes!", "Create invoice", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                        Controller controller = Controller.getInstance();
                        Long invoiceId = controller.insertRowAndGetId(invoice);
                        if(invoiceId != null){
                            List<InvoiceItem> items = ((InvoiceItemsTableModel) invoiceForm.getTblInvoiceItems().getModel()).getInvoiceItems();
                            for (InvoiceItem item : items) {
                                item.setInvoiceId(invoiceId);
                                controller.insertRow(item);
                            }
                        }
                        if(JOptionPane.showConfirmDialog(invoiceForm, "Invoice has been successfully added to the database!!! \n\n Create more invoices?", "Success", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION){
                            fillInvoiceItemsTable();
                            setCustomer(null);
                            invoiceForm.getTxtSelectedCustomer().setText("");
                            invoiceForm.getTxtTotalAmount().setText("");
                        }
                        else{
                            invoiceForm.dispose();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error: "+e.getMessage());
                }
                
            }
        });
        
        invoiceForm.btnCancelAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoiceForm.dispose();
            }
        });
        
        invoiceForm.getTblCars().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    int rowId = invoiceForm.getTblCars().getSelectedRow();
                    if(rowId != -1){
                        Car car = ((CarsTableModel) invoiceForm.getTblCars().getModel()).getCarAt(rowId);
                        invoiceForm.getTxtSelectedCar().setText(car.getBrand() + " " + car.getModel() + ", " + car.getPrice());
                        invoiceForm.getTxtQuantity().setEnabled(true);
                        invoiceForm.getTxtQuantity().setText("1");
                        invoiceForm.getTxtQuantity().grabFocus();
                        invoiceForm.getTxtQuantity().setSelectionStart(0);
                    }
                    else{
                        invoiceForm.getTxtSelectedCar().setText("");
                        invoiceForm.getTxtQuantity().setEnabled(false);
                        invoiceForm.getTxtQuantity().setText("");
                    }
                }
            }
        });
        
        invoiceForm.getTxtQuantity().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoiceForm.getBtnAddInvoiceItem().doClick();
            }
        });
    }
    
    private void setCustomer(Customer c){
        this.customer = c;
    }
    
    @SuppressWarnings("unchecked")
    public void fillComboBox() {
        try {
            DefaultComboBoxModel cbm = new DefaultComboBoxModel(Controller.getInstance().getAllCarBrands().toArray());
            cbm.insertElementAt("All brands", 0);
            invoiceForm.getCbCarSearchCondition().setModel(cbm);
            invoiceForm.getCbCarSearchCondition().setSelectedIndex(0);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void fillCustomersTable() {
        try {
            List<DefaultDomainObject> allCustomers = Controller.getInstance().getAll(new Customer());
            List<Customer> customers = new ArrayList<>();
            for (DefaultDomainObject customer : allCustomers) {
                customers.add((Customer) customer);
            }
            invoiceForm.getTblCustomers().setModel(new CustomersTableModel(customers));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    
    public void fillCarsTable(List<Car> c) {
        try {
            if(c == null){
                List<DefaultDomainObject> allCars = Controller.getInstance().getAllOrdered(new Car());
                List<Car> cars = new ArrayList<>();
                for (DefaultDomainObject car : allCars) {
                    cars.add((Car) car);
                }
                invoiceForm.getTblCars().setModel(new CarsTableModel(cars));
            }
            else{
                invoiceForm.getTblCars().setModel(new CarsTableModel(c));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    
    public void fillInvoiceItemsTable() {
        try {
            invoiceForm.getTblInvoiceItems().setModel(new InvoiceItemsTableModel(new ArrayList<>()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void fillDate() {
        invoiceForm.getTxtDate().setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
    }
    
    public void fillTotalAmount() {
        Double total = 0.0;
        InvoiceItemsTableModel tm = (InvoiceItemsTableModel) invoiceForm.getTblInvoiceItems().getModel();
        for (InvoiceItem item : tm.getInvoiceItems()) {
            total += item.getSum();
        }
        invoiceForm.getTxtTotalAmount().setText(Double.toString(total));
    }
    
    
}
