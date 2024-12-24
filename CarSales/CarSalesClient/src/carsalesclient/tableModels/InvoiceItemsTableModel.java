/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.tableModels;

import carsalesclient.controller.Controller;
import domain.Car;
import domain.DefaultDomainObject;
import domain.InvoiceItem;
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

    public InvoiceItemsTableModel(List<InvoiceItem> items) {
        this.items = items;
        updateCars();
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
        int carId = items.get(rowIndex).getCar().getIdCar().intValue();
        switch (columnIndex) {
            case 0:
                return items.get(rowIndex).getNum();
            case 1:
                for (Car car : cars) {
                    if(car.getIdCar() == carId){
                        System.out.println("Pronadjen je "+car.getBrand());
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

    public List<InvoiceItem> getInvoiceItems() {
        return items;
    }

    public void addInvoiceItem(int quantity, double priceOfOne, double sum, Car car) {
        InvoiceItem item = new InvoiceItem(null, 0, quantity, priceOfOne, sum, car);
        item.setNum(items.size() + 1);
        items.add(item);
        fireTableRowsInserted(items.size() - 1, items.size() - 1);
        updateCars();
    }

    public void removeInvoiceItem(int rowId) {
        items.remove(rowId);
        for (int i = rowId; i < items.size(); i++) {
            items.get(i).setNum(i+1);
        }
        fireTableRowsDeleted(items.size() - 1, items.size() - 1);
        updateCars();
    }
    
    private void updateCars(){
        Car car = new Car();
        car.setSearchCondition("id");
        try {
            Controller controller = Controller.getInstance();
            for (InvoiceItem item : items) {
                car.setSearchConditionValue(item.getCar().getIdCar().toString());
                List<DefaultDomainObject> c = controller.getByCondition(car);
                this.cars.add((Car) c.getFirst());
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
