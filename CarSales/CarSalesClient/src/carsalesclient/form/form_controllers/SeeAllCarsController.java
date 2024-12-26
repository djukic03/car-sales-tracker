/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.CarsTableForm;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.FormMode;
import carsalesclient.form.tableModels.CarsTableModel;
import domain.Car;
import domain.DefaultDomainObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class SeeAllCarsController {
    private final CarsTableForm carsTableForm;

    public SeeAllCarsController(CarsTableForm carsTableForm) {
        this.carsTableForm = carsTableForm;
        addListeners();
    }
    
    public void openForm(){
        fillComboBox();
        fillTable(null);
        carsTableForm.setVisible(true);
    }
    
    private void addListeners(){
        carsTableForm.cbBrandAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectBrand();
            }
            private void selectBrand(){
                try {
                    String conditionValue = carsTableForm.getCbBrand().getSelectedItem().toString();
                    if(conditionValue.equals("All brands")){
                        fillTable(null);
                    }
                    else{
                        Car car = new Car();
                        car.setSearchCondition("brand");
                        car.setSearchConditionValue(conditionValue);
                        List<DefaultDomainObject> c = ClientController.getInstance().getByCondition(car);
                        List<Car> cars = new ArrayList<>();
                        for (DefaultDomainObject i : c) {
                            cars.add((Car) i);
                        }
                        fillTable(cars);
                    }
                } catch (Exception e) {
                    System.out.println("Error: "+e.getMessage());
                }
            }
        });
        
        carsTableForm.btnDeleteAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCar();
            }
            
            private void deleteCar(){
                try {
                    int rowId = carsTableForm.getTblCars().getSelectedRow();
                    if (rowId < 0) {
                        JOptionPane.showMessageDialog(carsTableForm, "Please select car!");
                        return;
                    }
                    Car car = ((CarsTableModel) carsTableForm.getTblCars().getModel()).getCarAt(rowId);
                    car.setDeleteConditionValue(car.getIdCar());
                    if(JOptionPane.showConfirmDialog(carsTableForm, "Are you sure you want to DELETE the following car from the database: \n"+car.getBrand()+" "+car.getModel()+", "+car.getPrice()+"$", "Delete car", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                        ClientController.getInstance().deleteRow(car);
                        JOptionPane.showMessageDialog(carsTableForm, "Car deleted successfully!");
                        fillTable(null);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        
        carsTableForm.btnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowId = carsTableForm.getTblCars().getSelectedRow();
                if (rowId < 0) {
                    JOptionPane.showMessageDialog(carsTableForm, "Please select car!");
                    return;
                }
                Car car = ((CarsTableModel) carsTableForm.getTblCars().getModel()).getCarAt(rowId);
                Coordinator.getInstance().addParam("Car_details", car);
                Coordinator.getInstance().openAddCarForm(FormMode.DETAILS_FORM);
            }
        });
        
        carsTableForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTable(null);
            }
            
        });
    }

    @SuppressWarnings("unchecked")
    private void fillComboBox() {
        try {
            DefaultComboBoxModel cbm = new DefaultComboBoxModel(ClientController.getInstance().getAllCarBrands().toArray());
            cbm.insertElementAt("All brands", 0);
            carsTableForm.getCbBrand().setModel(cbm);
            carsTableForm.getCbBrand().setSelectedIndex(0);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void fillTable(List<Car> c) {
        try {
            if(c == null){
                List<DefaultDomainObject> allCars = ClientController.getInstance().getAllOrdered(new Car());
                List<Car> cars = new ArrayList<>();
                for (DefaultDomainObject car : allCars) {
                    cars.add((Car) car);
                }
                carsTableForm.getTblCars().setModel(new CarsTableModel(cars));
            }
            else{
                carsTableForm.getTblCars().setModel(new CarsTableModel(c));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
