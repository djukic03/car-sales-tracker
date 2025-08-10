/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.reservation;

import domain.CarStatus;
import domain.Reservation;
import so.AbstractSO;

/**
 *
 * @author user
 */
public class InsertReservationSO extends AbstractSO{

    @Override
    protected void validate(Object o) throws Exception {
        if (!(o instanceof Reservation)) {
            throw new Exception("Wrong object type used");
        }
    }

    @Override
    protected void execute(Object o) throws Exception {
        Reservation reservation = (Reservation) o;
        
        reservation.getCar().setStatus(CarStatus.RESERVED);
        dbBroker.updateRow(reservation.getCar());
        
        dbBroker.insertRow(reservation);
        
    }

    @Override
    protected void commit() {
    }

    @Override
    protected void rollback() {
    }
    
}
