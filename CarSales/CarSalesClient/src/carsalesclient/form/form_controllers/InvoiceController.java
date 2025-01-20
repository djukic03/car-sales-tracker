/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.AddInvoiceForm;
import carsalesclient.form.constants.CoordinatorParamConsts;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.TableFormMode;
import carsalesclient.form.tableModels.InvoiceItemsTableModel;
import carsalesclient.form.tableModels.UsersTableModel;
import domain.Customer;
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
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class InvoiceController {
    private final AddInvoiceForm invoiceForm;
    private Customer customer;
    private final List<InvoiceItem> invoiceItems = new ArrayList<>();

    public InvoiceController(AddInvoiceForm invoiceForm) {
        this.invoiceForm = invoiceForm;
        addListeners();
    }
    
    public void openForm(){
        prepareForm();
        invoiceForm.setVisible(true);
    }

    private void addListeners() {
        invoiceForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillItemsTable();
                fillSelectedCustomer();
            }

            private void fillItemsTable() {
                List<InvoiceItem> items = (List<InvoiceItem>) Coordinator.getInstance().getParam(CoordinatorParamConsts.SELECTED_CAR);
                if (items != null) {
                    for (InvoiceItem item : items) {
                        item.setNum(invoiceItems.size() + 1);
                        invoiceItems.add(item);
                    }
                    invoiceForm.getTblInvoiceItems().setModel(new InvoiceItemsTableModel(invoiceItems));
                    fillTotalAmount();
                }
                Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_CAR, null);
            }

            private void fillSelectedCustomer() {
                Customer customer = (Customer)Coordinator.getInstance().getParam(CoordinatorParamConsts.SELECTED_CUSTOMER);
                if (customer == null) {
                    invoiceForm.getTxtSelectedCustomer().setText("");
                }
                else{
                    invoiceForm.getTxtSelectedCustomer().setText(customer.getName());
                    setCustomer(customer);
                }
            }
        });
        
        invoiceForm.btnSelectCustomerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openCustomersTableForm(TableFormMode.SELECT_ITEM);
            }
        });
        
        invoiceForm.btnAddItemAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openCarsTableForm(TableFormMode.SELECT_ITEM);
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
                    Invoice invoice = new Invoice(null, Long.valueOf(invoiceForm.getTxtInvoiceNumber().getText()), d, Double.valueOf(invoiceForm.getTxtTotalAmount().getText()),((User) Coordinator.getInstance().getParam(CoordinatorParamConsts.LOGGED_IN_USER)), customer);
                    
                    if(JOptionPane.showConfirmDialog(invoiceForm, "Are you sure you want to CREATE and INSERT this invoice into the database? \n Please check all the data before clicking Yes!", "Create invoice", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                        ClientController controller = ClientController.getInstance();
                        Long invoiceId = controller.insertInvoice(invoice);
                        if(invoiceId != null){
                            List<InvoiceItem> items = ((InvoiceItemsTableModel) invoiceForm.getTblInvoiceItems().getModel()).getInvoiceItems();
                            for (InvoiceItem item : items) {
                                item.setInvoiceId(invoiceId);
                                controller.insertInvoiceItem(item);
                            }
                        }
                        if(JOptionPane.showConfirmDialog(invoiceForm, "Invoice has been successfully added to the database!!! \n\n Create more invoices?", "Success", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION){
                            prepareForm();
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
    }
    
    private void setCustomer(Customer c){
        this.customer = c;
    }
    
    public void fillTotalAmount() {
        Double total = 0.0;
        InvoiceItemsTableModel tm = (InvoiceItemsTableModel) invoiceForm.getTblInvoiceItems().getModel();
        for (InvoiceItem item : tm.getInvoiceItems()) {
            total += item.getSum();
        }
        invoiceForm.getTxtTotalAmount().setText(Double.toString(total));
    }

    private void prepareForm(){
        try {
            invoiceForm.getTxtDate().setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
            List<Invoice> invoices = ClientController.getInstance().getAllInvoices();
            invoiceForm.getTxtInvoiceNumber().setText(Integer.toString(invoices.size()+1));

            List<User> salesman = new ArrayList<>(){{
                add((User) Coordinator.getInstance().getParam(CoordinatorParamConsts.LOGGED_IN_USER));
            }};
            invoiceForm.getTblSalesman().setModel(new UsersTableModel(salesman));

            invoiceForm.getTblInvoiceItems().setModel(new InvoiceItemsTableModel(new ArrayList<>()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
