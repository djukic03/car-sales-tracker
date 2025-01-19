/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.CustomersTableForm;
import carsalesclient.form.constants.CoordinatorParamConsts;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.AddFormMode;
import carsalesclient.form.modes.TableFormMode;
import carsalesclient.form.tableModels.CustomersTableModel;
import domain.Customer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    
    public void openForm(TableFormMode formMode){
        prepareForm(formMode);
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
                    List<Customer> customers = ClientController.getInstance().searchCustomers(customer);
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
                        ClientController.getInstance().deleteCustomer(customer);
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
                Coordinator.getInstance().addParam(CoordinatorParamConsts.CUSTOMER_DETAILS, customer);
                Coordinator.getInstance().openAddCustomerForm(AddFormMode.DETAILS_FORM);
            }
        });
        
        customersTableForm.btnAddNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddCustomerForm(AddFormMode.ADD_FORM);
            }
        });
        
        customersTableForm.btnSelectAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowId = customersTableForm.getTblCustomers().getSelectedRow();
                if (rowId < 0) {
                    JOptionPane.showMessageDialog(customersTableForm, "Please select customer!");
                    return;
                }
                Customer customer = ((CustomersTableModel) customersTableForm.getTblCustomers().getModel()).getCustomerAt(rowId);
                Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_CUSTOMER, customer);
                customersTableForm.dispose();
            }
        });
        
        customersTableForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTable();
            }
        });
    }

    private void fillTable() {
        try {
            List<Customer> customers = ClientController.getInstance().getAllCustomers();
            customersTableForm.getTblCustomers().setModel(new CustomersTableModel(customers));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void prepareForm(TableFormMode formMode) {
        switch (formMode) {
            case TableFormMode.SEE_ALL_ITEMS:
                customersTableForm.getBtnSelect().setVisible(false);
                customersTableForm.getBtnDelete().setVisible(true);
                customersTableForm.getBtnDetails().setVisible(true);
                break;
            case TableFormMode.SELECT_ITEM:
                customersTableForm.getBtnSelect().setVisible(true);
                customersTableForm.getBtnDelete().setVisible(false);
                customersTableForm.getBtnDetails().setVisible(false);
                break;
            default:
                break;
        }
    }
}
