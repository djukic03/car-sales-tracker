/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.util;

import carsalesapplication.domain.Car;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author user
 */
public class CarUtil {
    public static List<Car> getAllCars(){
        Connection connection = DatabaseUtil.connect();
        
        List<Car> cars = new ArrayList();
        try {
            String query = "select * from car order by brand";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                cars.add(new Car(rs.getLong("id"), rs.getString("brand"), rs.getString("model"), rs.getDouble("price")));
            }
        } catch (Exception e) {
            System.out.println("Error while reading cars! "+e.getMessage());
        }
        
        return cars;
    }
}
