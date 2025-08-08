/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.reservation;

import domain.Car;
import domain.Customer;
import domain.DefaultDomainObject;
import domain.Reservation;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author user
 */
public class GetAllReservationsSO extends AbstractSO{
    private List<DefaultDomainObject> reservations;

    @Override
    protected void validate(Object o) throws Exception {
    }

    @Override
    protected void execute(Object o) throws Exception {
        reservations = dbBroker.getAll(new Reservation());
        for (DefaultDomainObject reservation : reservations) {
            Reservation res = (Reservation) reservation;
            res.getCar().setSearchCondition("id");
            res.getCar().setSearchConditionValue(res.getCar().getIdCar().toString());
            res.setCar((Car) dbBroker.getByCondition(res.getCar()).getFirst());
        
            res.getCustomer().setSearchCondition("id");
            res.getCustomer().setSearchConditionValue(res.getCustomer().getIdCustomer().toString());
            res.setCustomer((Customer) dbBroker.getByCondition(res.getCustomer()).getFirst());
        }
    }

    @Override
    protected void commit() {
    }

    @Override
    protected void rollback() {
    }
    
    public List<DefaultDomainObject> getReservations(){
        return reservations;
    }
}
