/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.invoice;

import domain.DefaultDomainObject;
import domain.Invoice;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author user
 */
public class GetAllInvoicesSO extends AbstractSO {
    private List<DefaultDomainObject> invoices;

    @Override
    protected void validate(Object o) throws Exception {
    }

    @Override
    protected void execute(Object o) throws Exception {
        invoices = dbBroker.getAll(new Invoice());
    }

    @Override
    protected void commit() {
    }

    @Override
    protected void rollback() {
    }

    public List<DefaultDomainObject> getInvoices() {
        return invoices;
    }
    
}
