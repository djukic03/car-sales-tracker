/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Car implements DefaultDomainObject, Serializable{
    private Long idCar;
    private String vin;
    private String brand;
    private String model;
    private Date firstReg;
    private int mileage;
    private String category;
    private String fuel;
    private Double engineCapacity;
    private Double enginePower;
    private String gearbox;
    private double price;
    private CarStatus status;
    String searchCondition;
    String searchConditionValue;
    Long deleteConditionValue;
    Long updateConditionValue;

    public Car() {
    }

    public Car(Long idCar, String vin, String brand, String model, Date firstReg, int mileage, String category, String fuel, Double engineCapacity, Double enginePower, String gearbox, double price, CarStatus status) {
        this.idCar = idCar;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.firstReg = firstReg;
        this.mileage = mileage;
        this.category = category;
        this.fuel = fuel;
        this.engineCapacity = engineCapacity;
        this.enginePower = enginePower;
        this.gearbox = gearbox;
        this.price = price;
        this.status = status;
    }

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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
    
    public Date getFirstReg() {
        return firstReg;
    }

    public void setFirstReg(Date firstReg) {
        this.firstReg = firstReg;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Double enginePower) {
        this.enginePower = enginePower;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSearchCondition(String condition) {
        this.searchCondition = condition;
    }

    public void setSearchConditionValue(String conditionValue) {
        this.searchConditionValue = conditionValue;
    }

    public void setDeleteConditionValue(Long deleteConditionValue) {
        this.deleteConditionValue = deleteConditionValue;
    }

    public void setUpdateConditionValue(Long updateConditionValue) {
        this.updateConditionValue = updateConditionValue;
    }
    
    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
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
                CarStatus s;
                switch (rs.getString("status").toLowerCase()) {
                    case "available":
                        s = CarStatus.AVAILABLE;
                        break;
                    case "reserved":
                        s = CarStatus.RESERVED;
                        break;
                    case "sold":
                        s = CarStatus.SOLD;
                        break;
                    default:
                        s = CarStatus.AVAILABLE;
                        break;
                }
                cars.add(new Car(rs.getLong("id"), rs.getString("vin"), rs.getString("brand"), rs.getString("model"),rs.getDate("first_reg"), rs.getInt("mileage"), rs.getString("category"), rs.getString("fuel"), rs.getDouble("engine_capacity"), rs.getDouble("engine_power"), rs.getString("gearbox"), rs.getDouble("price"), s));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return cars;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "'"+ vin + "', '"+ brand +"', '"+ model +"', '"+ sdf.format(firstReg) +"', "+ mileage +", '"+ category +"', '"+ fuel +"', "+ engineCapacity +", "+ enginePower + ", '"+ gearbox +"', "+ price+ ", '"+ status.toString() +"'";
    }    

    @Override
    public String getInsertColumns() {
        return "vin, brand, model, first_reg, mileage, category, fuel, engine_capacity, engine_power, gearbox, price, status";
    }

    @Override
    public String getDeleteCondition() {
        return "id";
    }

    @Override
    public String getDeleteConditionValue() {
        return deleteConditionValue.toString();
    }

    @Override
    public String getUpdateValues() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "vin = '"+ vin +"', brand = '"+ brand +"', model = '"+ model +"', first_reg = '"+ sdf.format(firstReg) +"', mileage = "+ mileage +", category = '"+ category +"', fuel = '"+ fuel +"', engine_capacity = "+ engineCapacity +", engine_power = "+ enginePower + ", gearbox = '"+ gearbox +"', price = "+ price + ", status = '" + status.toString() + "'";
    }

    @Override
    public String getUpdateCondition() {
        return "id";
    }

    @Override
    public String getUpdateConditionValue() {
        return updateConditionValue.toString();
    }

    @Override
    public String getOrderCondition() {
        return "brand";
    }

}
