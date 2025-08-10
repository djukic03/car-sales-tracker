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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class InvoiceItem extends DefaultDomainObject implements  Serializable{
    private Long invoiceId;
    private int num;
    private double price;
    private String note;
    private Car car;

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
    
    @Override
    public String toString() {
        return car.getBrand() + " " + car.getModel() + ", note: " + note + ", price = " +price+"\n";
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
    public String getGetAllQuery() {
        return "SELECT * FROM invoice_item";
    }

    @Override
    public String getGetAllOrderedQuery() {
        return "SELECT * FROM invoice_item ORDER BY invoice_id";
    }

    @Override
    public String getGetByConditionQuery() {
        return "SELECT * FROM invoice_item "+
                "WHERE " + searchCondition + " LIKE '" + searchConditionValue + "%'";
    }

    @Override
    public String getInsertQuery() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "INSERT INTO invoice_item "+
                "(invoice_id, rb, price, note, car_id) "+
                "values("+ invoiceId + ", "+ num + ", " + price + ", '" + note + "', "+ car.getIdCar() +")";
    }

    @Override
    public String getUpdateQuery() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "UPDATE invoice_item "+
                "SET price = "+ price +", note = '"+ note +"', car_id = "+ car.getIdCar() +" "+
                "WHERE invoice_id = "+ invoiceId + "AND rb = " + num;
    }

    @Override
    public String getDeleteQuery() {
        try {
            throw new Exception("Can't delete invoice item");
        } catch (Exception ex) {
            Logger.getLogger(InvoiceItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
