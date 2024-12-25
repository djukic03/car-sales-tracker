/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.Controller;
import carsalesclient.form.CustomersTableForm;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.FormMode;
import carsalesclient.tableModels.CustomersTableModel;
import domain.Customer;
import domain.DefaultDomainObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class SeeAllCustomersController {
    private final CustomersTableForm customersTableForm;

    public SeeAllCustomersController(CustomersTableForm customersTableForm) {
        this.customersTableForm = customersTableForm;
        addListeners();
    }
    
    public void openForm(){
        fillTable();
        customersTableForm.setVisible(true);
    }

    private void addListeners() {
        customersTableForm.btnSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomers();
            }

            private void searchCustomers() {
                try {
                    String conditionValue = customersTableForm.getTxtSearch().getText();
                    Customer customer = new Customer();
                    customer.setSearchCondition("name");
                    customer.setSearchConditionValue(conditionValue);
                    List<DefaultDomainObject> c = Controller.getInstance().getByCondition(customer);
                    List<Customer> customers = new ArrayList<>();
                    for (DefaultDomainObject i : c) {
                        customers.add((Customer) i);
                    }
                    customersTableForm.getTblCustomers().setModel(new CustomersTableModel(customers));
                } catch (Exception e) {
                    System.out.println("Error: "+e.getMessage());
                }
            }
        });
        customersTableForm.getTxtSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersTableForm.getBtnSearch().doClick();
            }
        });
        
        customersTableForm.btnDeleteAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }

            private void deleteCustomer() {
                try {
                    int rowId = customersTableForm.getTblCustomers().getSelectedRow();
                    if (rowId < 0) {
                        JOptionPane.showMessageDialog(customersTableForm, "Please select customer!");
                        return;
                    }
                    Customer customer = ((CustomersTableModel) customersTableForm.getTblCustomers().getModel()).getCustomerAt(rowId);
                    customer.setDeleteConditionValue(customer.getIdCustomer());
                    if(JOptionPane.showConfirmDialog(customersTableForm, "Are you sure you want to DELETE the following customer from the database: \n"+customer.getName(), "Delete customer", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                        Controller.getInstance().deleteRow(customer);
                        JOptionPane.showMessageDialog(customersTableForm, "Customer deleted successfully!");
                        fillTable();
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        
        customersTableForm.btnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCustomer();
            }

            private void selectCustomer() {
                int rowId = customersTableForm.getTblCustomers().getSelectedRow();
                if (rowId < 0) {
                    JOptionPane.showMessageDialog(customersTableForm, "Please select customer!");
                    return;
                }
                Customer customer = ((CustomersTableModel) customersTableForm.getTblCustomers().getModel()).getCustomerAt(rowId);
                Coordinator.getInstance().addParam("Customer_details", customer);
                Coordinator.getInstance().openAddCarForm(FormMode.DETAILS_FORM);
            }
        });
    }

    private void fillTable() {
        try {
            List<DefaultDomainObject> allCustomers = Controller.getInstance().getAll(new Customer());
            List<Customer> customers = new ArrayList<>();
            for (DefaultDomainObject customer : allCustomers) {
                customers.add((Customer) customer);
            }
            customersTableForm.getTblCustomers().setModel(new CustomersTableModel(customers));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
