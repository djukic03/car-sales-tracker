/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.invoice;

import domain.CarStatus;
import domain.Invoice;
import domain.InvoiceItem;
import so.AbstractSO;

/**
 *
 * @author user
 */
public class InsertInvoiceSO extends AbstractSO {

    @Override
    protected void validate(Object o) throws Exception {
        if(!(o instanceof Invoice)){
            throw new Exception("Wrong object type used");
        }
    }

    @Override
    protected void execute(Object o) throws Exception {
        Invoice invoice = (Invoice) o;
        Long invoiceId = dbBroker.insertRowAndGetId(invoice);
        for (InvoiceItem invoiceItem : invoice.getInvoiceItems()) {
            invoiceItem.getCar().setStatus(CarStatus.SOLD);
            invoiceItem.getCar().setUpdateConditionValue(invoiceItem.getCar().getIdCar());
            dbBroker.updateRow(invoiceItem.getCar());
            
            invoiceItem.setInvoiceId(invoiceId);
            dbBroker.insertRow(invoiceItem);
            
        }
    }

    @Override
    protected void commit() {
    }

    @Override
    protected void rollback() {
    }
}
