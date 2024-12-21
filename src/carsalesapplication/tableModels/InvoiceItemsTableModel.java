/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.tableModels;

import carsalesapplication.controller.Controller;
import carsalesapplication.domain.Car;
import carsalesapplication.domain.DefaultDomainObject;
import carsalesapplication.domain.InvoiceItem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class InvoiceItemsTableModel extends AbstractTableModel{
    private List<InvoiceItem> items;
    private List<Car> cars = new ArrayList<>();

    public InvoiceItemsTableModel(List<InvoiceItem> items) throws SQLException {
        this.items = items;
        Car car = new Car(null, null, null, 0);
        car.setSearchCondition("id");
        Controller controller = Controller.getInstance();
        for (InvoiceItem item : items) {
            car.setSearchConditionValue(item.getCarId().toString());
            List<DefaultDomainObject> c = controller.getByCondition(car);
            this.cars.add((Car) c.getFirst());
        }
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int carId = items.get(rowIndex).getCarId().intValue();
        switch (columnIndex) {
            case 0:
                return items.get(rowIndex).getNum();
            case 1:
                for (Car car : cars) {
                    if(car.getIdCar() == carId){
                        return car.getBrand();
                    }
                }
                System.out.println("Nije pronadjen auto");
                break;
            case 2:
                for (Car car : cars) {
                    if(car.getIdCar() == carId){
                        return car.getModel();
                    }
                }
                System.out.println("Nije pronadjen auto");
                break;
            case 3:
                return items.get(rowIndex).getPriceOfOne();
            case 4:
                return items.get(rowIndex).getQuantity();
            case 5:
                return items.get(rowIndex).getSum();
            default:
                throw new AssertionError();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String columns[] = {"Num","Brand","Model","Price","Quantity","Sum"};
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }
    
    public InvoiceItem getInvoiceItemAt(int rowId){
        return items.get(rowId);
    }
    
}
