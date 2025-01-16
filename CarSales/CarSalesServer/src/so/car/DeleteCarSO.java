/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.car;

import domain.Car;
import so.AbstractSO;

/**
 *
 * @author user
 */
public class DeleteCarSO extends AbstractSO{

    @Override
    protected void validate(Object o) throws Exception {
        if(!(o instanceof Car)){
            throw new Exception("Wrong object type used");
        }
    }

    @Override
    protected void execute(Object o) throws Exception {
        dbBroker.deleteRow((Car) o);
    }

    @Override
    protected void commit() {
    }

    @Override
    protected void rollback() {
    }
    
}
