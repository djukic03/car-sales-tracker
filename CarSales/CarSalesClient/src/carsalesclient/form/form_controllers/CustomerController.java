/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.Controller;
import carsalesclient.form.AddCustomerForm;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.FormMode;
import domain.Customer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalBorders;

/**
 *
 * @author user
 */
public class CustomerController {
    private final AddCustomerForm customerForm;

    public CustomerController(AddCustomerForm customerForm) {
        this.customerForm = customerForm;
        addListeners();
    }
    
    public void openForm(FormMode formMode){
        prepareForm(formMode);
        customerForm.setVisible(true);
    }

    private void addListeners() {
        customerForm.btnEnableChangesAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareForm(FormMode.UPDATE_FORM);
            }
        });
        
        customerForm.btnEditAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }

            private void update() {
                try {
                    if(!emptyFields()){
                        Customer customer = new Customer(null, customerForm.getTxtName().getText(),Integer.parseInt(customerForm.getTxtPhone().getText()) , customerForm.getTxtEmail().getText());
                        if(JOptionPane.showConfirmDialog(customerForm, "Are you sure you want to SAVE the following changes into the database: \n"+customer.getName()+" "+customer.getPhone()+", "+customer.getEmail(), "Change customer details", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                            customer.setIdCustomer(((Customer) Coordinator.getInstance().getParam("Customer_details")).getIdCustomer());
                            customer.setUpdateConditionValue(customer.getIdCustomer());
                            Controller.getInstance().updateRow(customer);
                            JOptionPane.showMessageDialog(customerForm, customer.getName()+" has been successfully added to the database!");
                            customerForm.dispose();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(customerForm, "Fill all required fields");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                
            }
        });
        
        customerForm.btnSaveAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    if(!emptyFields()){
                        Customer customer = new Customer(null, customerForm.getTxtName().getText(),Integer.parseInt(customerForm.getTxtPhone().getText()) , customerForm.getTxtEmail().getText());
                        if(JOptionPane.showConfirmDialog(customerForm, "Are you sure you want to INSERT the following customer into the database: \n"+customer.getName()+" "+customer.getPhone()+", "+customer.getEmail(), "Add customer", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                            Controller.getInstance().insertRow(customer);
                            if(JOptionPane.showConfirmDialog(customerForm, customer.getName()+" "+customer.getPhone()+" has been successfully added to the database!\n\nAdd more customers?", "Success", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                                prepareForm(FormMode.ADD_FORM);
                            }
                            else{
                                customerForm.dispose();
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(customerForm, "Fill all required fields");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        
        customerForm.btnCancelAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerForm.dispose();
            }
        });
        
        
        customerForm.getTxtName().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerForm.getBtnSave().doClick();
            }
        });
        
        customerForm.getTxtPhone().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerForm.getBtnSave().doClick();
            }
        });
        
        customerForm.getTxtEmail().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerForm.getBtnSave().doClick();
            }
        });
    }

    private void prepareForm(FormMode formMode) {
        switch(formMode){
            case FormMode.ADD_FORM:
                customerForm.getLblId().setVisible(false);
                customerForm.getTxtId().setVisible(false);
                customerForm.getTxtId().setEnabled(false);
                
                customerForm.getTxtName().setText("");
                customerForm.getTxtName().setEnabled(true);
                customerForm.getTxtPhone().setText("");
                customerForm.getTxtPhone().setEnabled(true);
                customerForm.getTxtEmail().setText("");
                customerForm.getTxtEmail().setEnabled(true);
                
                customerForm.getBtnEnableChanges().setVisible(false);
                customerForm.getBtnEdit().setVisible(false);
                customerForm.getBtnSave().setVisible(true);
                customerForm.getBtnSave().setEnabled(true);
                break;
                
            case FormMode.DETAILS_FORM:
                Customer customer = (Customer) Coordinator.getInstance().getParam("Customer_details");
                customerForm.getLblId().setVisible(true);
                customerForm.getTxtId().setVisible(true);
                customerForm.getTxtId().setText(customer.getIdCustomer().toString());
                customerForm.getTxtId().setEnabled(false);
                
                customerForm.getTxtName().setText(customer.getName());
                customerForm.getTxtName().setEnabled(false);
                customerForm.getTxtPhone().setText(Integer.toString(customer.getPhone()));
                customerForm.getTxtPhone().setEnabled(false);
                customerForm.getTxtEmail().setText(customer.getEmail());
                customerForm.getTxtEmail().setEnabled(false);
                
                customerForm.getBtnEnableChanges().setVisible(true);
                customerForm.getBtnEnableChanges().setEnabled(true);
                customerForm.getBtnEdit().setVisible(true);
                customerForm.getBtnEdit().setEnabled(false);
                customerForm.getBtnSave().setVisible(false);
                break;
                
            case FormMode.UPDATE_FORM:
                customerForm.getLblId().setVisible(true);
                customerForm.getTxtId().setVisible(true);
                customerForm.getTxtId().setEnabled(false);
                
                customerForm.getTxtName().setEnabled(true);
                customerForm.getTxtPhone().setEnabled(true);
                customerForm.getTxtEmail().setEnabled(true);
                
                customerForm.getBtnEnableChanges().setVisible(true);
                customerForm.getBtnEnableChanges().setEnabled(false);
                customerForm.getBtnEdit().setEnabled(true);
                break;
                
            default:
                break;
        }
    }
    
    private boolean emptyFields(){
        Border border = new LineBorder(Color.red, 1,true);
        Font font = new Font("Gill Sans MT", 1, 8);
        boolean empty = false;
        
        
        if(customerForm.getTxtName().getText().isBlank()){
            customerForm.getTxtName().setBorder(new TitledBorder(border, "Required Field", 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            customerForm.getTxtName().setBorder(new MetalBorders.TextFieldBorder());
                
        }
        if(customerForm.getTxtPhone().getText().isBlank()){
            customerForm.getTxtPhone().setBorder(new TitledBorder(border, "Required Field", 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            customerForm.getTxtPhone().setBorder(new MetalBorders.TextFieldBorder());
                
        }
        
        return empty;
    }
}
