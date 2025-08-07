/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class Reservation implements DefaultDomainObject, Serializable{
    private Long idReservaion;
    private Date dateOfReservation;
    private Date reservationDeadline;
    private ReservationStatus status;
    private String note;
    private Car car;
    private Customer customer;

    public Reservation(Long idReservaion, Date dateOfReservation, Date reservationDeadline, ReservationStatus status, String note, Car car, Customer customer) {
        this.idReservaion = idReservaion;
        this.dateOfReservation = dateOfReservation;
        this.reservationDeadline = reservationDeadline;
        this.status = status;
        this.note = note;
        this.car = car;
        this.customer = customer;
    }

    public Reservation() {
    }
    
    @Override
    public String getClassName() {
        return "reservation";
    }

    @Override
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException {
        List<DefaultDomainObject> reservations = new ArrayList<>();
        while(rs.next()){
            Car car = new Car();
            car.setIdCar(rs.getLong("car_id"));
            Customer customer = new Customer();
            customer.setIdCustomer(rs.getLong("customer_id"));
            ReservationStatus s;
            switch (rs.getString("status").toLowerCase()) {
                case "active":
                    s = ReservationStatus.ACTIVE;
                    break;
                case "canceled":
                    s = ReservationStatus.CANCELED;
                    break;
                case "realized":
                    s = ReservationStatus.REALIZED;
                    break;
                default:
                    throw new AssertionError();
            }
            reservations.add(new Reservation(rs.getLong("id"), rs.getDate("reservation_date"), rs.getDate("reservation_deadline"), s, rs.getString("note"), car, customer));
        }
        return reservations;
    }

    @Override
    public String getSearchCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getSearchConditionValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInsertColumns() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDeleteCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDeleteConditionValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateConditionValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getOrderCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Long getIdReservaion() {
        return idReservaion;
    }

    public void setIdReservaion(Long idReservaion) {
        this.idReservaion = idReservaion;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(Date dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public Date getReservationDeadline() {
        return reservationDeadline;
    }

    public void setReservationDeadline(Date reservationDeadline) {
        this.reservationDeadline = reservationDeadline;
    }
    
}
