/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class InvoiceItem implements DefaultDomainObject, Serializable{
    private Long invoiceId;
    private int num;
    private double price;
    private String note;
    private Car car;
    String searchCondition;
    String searchConditionValue;

    public InvoiceItem() {
    }

    public InvoiceItem(Long invoiceId, int num, double price, String note, Car car) {
        this.invoiceId = invoiceId;
        this.num = num;
        this.price = price;
        this.note = note;
        this.car = car;
    }
    
    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long userId) {
        this.invoiceId = userId;
    }
    
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    
    public void setSearchCondition(String condition) {
        this.searchCondition = condition;
    }

    public void setSearchConditionValue(String conditionValue) {
        this.searchConditionValue = conditionValue;
    }

    @Override
    public String toString() {
        return car.getBrand() + " " + car.getModel() + ", note: " + note + ", price = " +price+"\n";
    }
    
    @Override
    public String getClassName() {
        return "invoice_item";
    }

    @Override
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException {
        List<DefaultDomainObject> items = new ArrayList<>();
        while(rs.next()){
            Car car = new Car();
            car.setIdCar(rs.getLong("car_id"));
            items.add(new InvoiceItem(rs.getLong("invoice_id"), rs.getInt("rb"), rs.getDouble("price"),rs.getString("note"), car));
        }
        return items;
    }

    @Override
    public String getSearchCondition() {
        return searchCondition;
    }

    @Override
    public String getSearchConditionValue() {
        return searchConditionValue;
    }

    @Override
    public String getInsertValues() {
        return invoiceId + ", "+ num + ", " + price + ", '" + note + "', "+ car.getIdCar();
    }

    @Override
    public String getInsertColumns() {
        return "invoice_id, rb, price, note, car_id";
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
        return "invoice_id";
    }

    

}
