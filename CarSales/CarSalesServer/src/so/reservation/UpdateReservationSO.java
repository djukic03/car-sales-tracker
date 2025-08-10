/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.reservation;

import domain.Reservation;
import so.AbstractSO;

/**
 *
 * @author user
 */
public class UpdateReservationSO extends AbstractSO{

    @Override
    protected void validate(Object o) throws Exception {
        if (!(o instanceof Reservation)) {
            throw new Exception("Wrong object type");
        }
    }

    @Override
    protected void execute(Object o) throws Exception {
        Reservation res = (Reservation) o;
        dbBroker.updateRow(res.getCar());
        dbBroker.updateRow(res);
    }

    @Override
    protected void commit() {
    }

    @Override
    protected void rollback() {
    }
    
}
