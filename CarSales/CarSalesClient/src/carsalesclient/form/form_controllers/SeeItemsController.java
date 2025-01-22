/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.InvoiceItemsTableForm;
import carsalesclient.form.constants.CoordinatorParamConsts;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.AddFormMode;
import carsalesclient.form.tableModels.InvoiceItemsTableModel;
import domain.InvoiceItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class SeeItemsController {
    private final InvoiceItemsTableForm invoiceItemsForm;

    public SeeItemsController(InvoiceItemsTableForm invoiceItemsForm) {
        this.invoiceItemsForm = invoiceItemsForm;
        addListeners();
    }

    
    public void openForm(List<InvoiceItem> items){
        fillTable(items);
        invoiceItemsForm.setVisible(true);
    }
    
    private void addListeners() {
        invoiceItemsForm.btnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItem();
                
            }

            private void selectItem() {
                try {
                    int row = invoiceItemsForm.getTblInvoiceItems().getSelectedRow();
                    if (row < 0) {
                        JOptionPane.showMessageDialog(invoiceItemsForm, "Please select item.");
                        return;
                    }
                    InvoiceItem i = ((InvoiceItemsTableModel) invoiceItemsForm.getTblInvoiceItems().getModel()).getInvoiceItemAt(row);
                    i.getCar().setSearchCondition("id");
                    i.getCar().setSearchConditionValue(i.getCar().getIdCar().toString());
                    i.setCar(ClientController.getInstance().searchCars(i.getCar()).getFirst());
                    Coordinator.getInstance().addParam(CoordinatorParamConsts.CAR_DETAILS, i.getCar());
                    Coordinator.getInstance().openAddCarForm(AddFormMode.DETAILS_FORM);
                } catch (Exception e) {
                }
            }
        });
    }
    
    private void fillTable(List<InvoiceItem> items) {
        invoiceItemsForm.getTblInvoiceItems().setModel(new InvoiceItemsTableModel(items));
    }
}
