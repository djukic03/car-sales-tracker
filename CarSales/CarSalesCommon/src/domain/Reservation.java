/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class Reservation extends DefaultDomainObject implements  Serializable{
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
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException {
        List<DefaultDomainObject> reservations = new ArrayList<>();
        while(rs.next()){
            Car car = new Car();
            car.setIdCar(rs.getLong("car_id"));
            Customer customer;
            switch(rs.getString("type")){
                case "individual":
                    customer = new Individual();
                    customer.setIdCustomer(rs.getLong("customer_id"));
                    break;
                case "company":
                    customer = new Company();
                    customer.setIdCustomer(rs.getLong("customer_id"));
                    break;
                default:
                    customer = null;
                    break;
            }
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
            reservations.add(new Reservation(rs.getLong("id_reservation"), rs.getDate("reservation_date"), rs.getDate("reservation_deadline"), s, rs.getString("note"), car, customer));
        }
        return reservations;
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

    @Override
    public String getGetAllQuery() {
        return """
               SELECT r.id AS id_reservation, r.reservation_date, r.reservation_deadline, r.status, r.note, r.car_id, r.customer_id,
                        c.id AS id_customer, c.type
               FROM reservation r JOIN customer c ON r.customer_id = c.id 
               """;
    }

    @Override
    public String getGetAllOrderedQuery() {
        return """
               SELECT r.id AS id_reservation, r.reservation_date, r.reservation_deadline, r.status, r.note, r.car_id, r.customer_id,
                        c.id AS id_customer, c.type
               FROM reservation r JOIN customer c ON r.customer_id = c.id 
               ORDER BY r.reservation_date
               """;
    }

    @Override
    public String getGetByConditionQuery() {
        return "SELECT r.id AS id_reservation, r.reservation_date, r.reservation_deadline, r.status, r.note, r.car_id, r.customer_id, "+
                "c.id AS id_customer, c.type "+
                "FROM reservation r JOIN customer c ON r.customer_id = c.id "+ 
                "WHERE r."+ searchCondition +" LIKE '"+ searchConditionValue + "'%";
    }

    @Override
    public String getInsertQuery() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "INSERT INTO reservation "+
                "(reservation_date, reservation_deadline, status, note, car_id, customer_id) "+
                "values('"+ sdf.format(dateOfReservation) +"', '"+ sdf.format(reservationDeadline) +"', '"+ status.toString() +"', '"+ note +"', "+ car.getIdCar() +", "+ customer.getIdCustomer() +")";
    }

    @Override
    public String getUpdateQuery() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "UPDATE reservation "+
                "SET reservation_date = '"+ sdf.format(dateOfReservation) +"', reservation_deadline = '"+ sdf.format(reservationDeadline) +"', status = '"+ status.toString() +"', note = '"+ note +"', car_id = "+ car.getIdCar() +", customer_id = "+ customer.getIdCustomer() +" "+
                "WHERE id = "+ idReservaion;
    }

    @Override
    public String getDeleteQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
