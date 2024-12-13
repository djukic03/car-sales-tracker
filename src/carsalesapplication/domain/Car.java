/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Car  implements DefaultDomainObject{
    private Long idCar;
    private String brand;
    private String model;
    private double price;
    String condition;
    String conditionValue;

    public Car(Long idCar, String brand, String model, double price) {
        this.idCar = idCar;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    @Override
    public String getClassName() {
        return "car";
    }

    @Override
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException{
        List<DefaultDomainObject> cars = new ArrayList<>();
        try {
            while(rs.next()){
                cars.add(new Car(rs.getLong("id"), rs.getString("brand"), rs.getString("model"), rs.getDouble("price")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return cars;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public String getConditionValue() {
        return conditionValue;
    }

    @Override
    public String getInsertValues() {
        return "'"+ brand +"', '"+ model +"', "+ price;
    }    

    @Override
    public String getInsertColumns() {
        return "brand, model, price";
    }
    
}
