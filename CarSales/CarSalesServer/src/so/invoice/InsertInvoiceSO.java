/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.invoice;

import domain.Invoice;
import so.AbstractSO;

/**
 *
 * @author user
 */
public class InsertInvoiceSO extends AbstractSO {
    private Long invoiceId;

    @Override
    protected void validate(Object o) throws Exception {
        if(!(o instanceof Invoice)){
            throw new Exception("Wrong object type used");
        }
    }

    @Override
    protected void execute(Object o) throws Exception {
        invoiceId = dbBroker.insertRowAndGetId((Invoice) o);
    }

    @Override
    protected void commit() {
    }

    @Override
    protected void rollback() {
    }

    public Long getInvoiceId() {
        return invoiceId;
    }
    
    
}
