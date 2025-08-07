/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.tableModels;

import domain.Car;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class CarsTableModel extends AbstractTableModel {
    private List<Car> cars;

    public CarsTableModel(List<Car> cars) {
        this.cars = cars;
    }
    
    

    @Override
    public int getRowCount() {
        return cars.size();
    }

    @Override
    public int getColumnCount() {
        return 12;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return cars.get(rowIndex).getVin();
            case 1:
                return cars.get(rowIndex).getBrand();
            case 2:
                return cars.get(rowIndex).getModel();
            case 3:
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(cars.get(rowIndex).getFirstReg());
                return calendar.get(Calendar.YEAR);
            case 4:
                return cars.get(rowIndex).getMileage() + " km";
            case 5:
                return cars.get(rowIndex).getCategory();
            case 6:
                return cars.get(rowIndex).getFuel();
            case 7:
                return cars.get(rowIndex).getEngineCapacity() + " L";
            case 8:
                return cars.get(rowIndex).getEnginePower() + " HP";
            case 9:
                return cars.get(rowIndex).getGearbox();
            case 10:
                return cars.get(rowIndex).getPrice() + " €";
            case 11:
                return cars.get(rowIndex).getStatus().toString();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"VIN","Brand","Model","First Registration","Mileage","Category","Fuel","Engine Capacity","Engine power","Gearbox","Price","Status"};
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }
    
    public Car getCarAt(int rowId){
        return cars.get(rowId);
    }
}
