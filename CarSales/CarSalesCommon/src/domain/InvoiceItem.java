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
    private int quantity;
    private double priceOfOne;
    private double sum;
    private Car car;

    public InvoiceItem() {
    }

    public InvoiceItem(Long invoiceId, int num, int quantity, double priceOfOne, double sum, Car car) {
        this.invoiceId = invoiceId;
        this.num = num;
        this.quantity = quantity;
        this.priceOfOne = priceOfOne;
        this.sum = sum;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceOfOne() {
        return priceOfOne;
    }

    public void setPriceOfOne(double priceOfOne) {
        this.priceOfOne = priceOfOne;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
    
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    
    @Override
    public String getClassName() {
        return "invoice_item";
    }

    @Override
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException {
        List<DefaultDomainObject> items = new ArrayList<>();
        try {
            while(rs.next()){
                items.add(new InvoiceItem(rs.getLong("invoice_id"), rs.getInt("rb"), rs.getInt("quantity"), rs.getDouble("price_of_one"),rs.getDouble("sum"),new Car(rs.getLong("car_id"), null, null, 0.0)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceItem.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return items;
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
        return invoiceId + ", "+ num +", "+ quantity +", "+ priceOfOne + ", " + sum + ", "+ car.getIdCar();
    }

    @Override
    public String getInsertColumns() {
        return "invoice_id, rb, quantity, price_of_one, sum, car_id";
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
    public List<DetailsFormData> getDetailsFormData() {
        List<DetailsFormData> data = new ArrayList<>(){{
            add(new DetailsFormData("num", Integer.toString(num)));
            add(new DetailsFormData("quantity", Integer.toString(quantity)));
            add(new DetailsFormData("pirce of car", Double.toString(priceOfOne)));
            add(new DetailsFormData("sum", Double.toString(sum)));
        }};
        return data;
    }

    @Override
    public String getOrderCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
