/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.tableModels;

import carsalesapplication.domain.Car;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class MyCarTableModel extends AbstractTableModel {
    private List<Car> cars;

    public MyCarTableModel(List<Car> cars) {
        this.cars = cars;
    }
    
    

    @Override
    public int getRowCount() {
        return cars.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return cars.get(rowIndex).getBrand();
            case 1:
                return cars.get(rowIndex).getModel();
            case 2:
                return cars.get(rowIndex).getPrice();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"Brand","Model","Price"};
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }
    
    
}
