/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.form.InvoiceItemsTableForm;
import carsalesclient.form.tableModels.InvoiceItemsTableModel;
import domain.InvoiceItem;
import java.util.List;

/**
 *
 * @author user
 */
public class SeeItemsController {
    private final InvoiceItemsTableForm invoiceItemsForm;

    public SeeItemsController(InvoiceItemsTableForm invoiceItemsForm) {
        this.invoiceItemsForm = invoiceItemsForm;
    }

    
    public void openForm(List<InvoiceItem> items){
        fillTable(items);
        invoiceItemsForm.setVisible(true);
    }

    private void fillTable(List<InvoiceItem> items) {
        invoiceItemsForm.getTblInvoiceItems().setModel(new InvoiceItemsTableModel(items));
    }
}
