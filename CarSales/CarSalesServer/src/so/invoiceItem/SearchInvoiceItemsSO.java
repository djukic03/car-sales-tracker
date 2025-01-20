/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.invoiceItem;

import domain.Car;
import domain.DefaultDomainObject;
import domain.Invoice;
import domain.InvoiceItem;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author user
 */
public class SearchInvoiceItemsSO extends AbstractSO {
    private List<DefaultDomainObject> items;

    @Override
    protected void validate(Object o) throws Exception {
        if(!(o instanceof InvoiceItem)){
            throw new Exception("Wrong object type used");
        }
    }

    @Override
    protected void execute(Object o) throws Exception {
        items = dbBroker.getByCondition((InvoiceItem) o);
        for (DefaultDomainObject item : items) {
            InvoiceItem i = (InvoiceItem) item;
            i.getCar().setSearchCondition("id");
            i.getCar().setSearchConditionValue(i.getCar().getIdCar().toString());
            i.setCar((Car) dbBroker.getByCondition(i.getCar()).getFirst());
        }
    }

    @Override
    protected void commit() {
    }

    @Override
    protected void rollback() {
    }

    public List<DefaultDomainObject> getItems() {
        return items;
    }
}
