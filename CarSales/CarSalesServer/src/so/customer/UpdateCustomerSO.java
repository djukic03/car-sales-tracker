/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.customer;

import domain.Customer;
import so.AbstractSO;

/**
 *
 * @author user
 */
public class UpdateCustomerSO extends AbstractSO {
    
    @Override
    protected void validate(Object o) throws Exception {
        if(!(o instanceof Customer)){
            throw new Exception("Wrong object type used");
}
    }

    @Override
    protected void execute(Object o) throws Exception {
        dbBroker.updateCustomer((Customer) o);
    }

    @Override
    protected void commit() {
    }

    @Override
    protected void rollback() {
    }
    
}
