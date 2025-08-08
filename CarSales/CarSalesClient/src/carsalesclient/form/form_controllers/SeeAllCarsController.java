/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.CarsTableForm;
import carsalesclient.form.constants.CoordinatorParamConsts;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.AddFormMode;
import carsalesclient.form.modes.TableFormMode;
import carsalesclient.form.tableModels.CarsTableModel;
import domain.Car;
import domain.CarStatus;
import domain.Customer;
import domain.InvoiceItem;
import domain.Reservation;
import domain.ReservationStatus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author user
 */
public class SeeAllCarsController {
    private final CarsTableForm carsTableForm;
    private Customer customer;
    private List<InvoiceItem> selectedCars = new ArrayList<>();

    public SeeAllCarsController(CarsTableForm carsTableForm) {
        this.carsTableForm = carsTableForm;
        addListeners();
    }
    
    public void openForm(TableFormMode formMode){
        prepareForm(formMode);
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
                        List<Car> cars = ClientController.getInstance().searchCars(car);
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
                        ClientController.getInstance().deleteCar(car);
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
                Coordinator.getInstance().addParam(CoordinatorParamConsts.CAR_DETAILS, car);
                Coordinator.getInstance().openAddCarForm(AddFormMode.DETAILS_FORM);
            }
        });
        
        carsTableForm.btnAddNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddCarForm(AddFormMode.ADD_FORM);
            }
        });
        
        carsTableForm.getTblCars().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    int rowId = carsTableForm.getTblCars().getSelectedRow();
                    if(rowId != -1){
                        Car car = ((CarsTableModel) carsTableForm.getTblCars().getModel()).getCarAt(rowId);
                        carsTableForm.getTxtNote().setEnabled(true);
                    }
                    else{
                        carsTableForm.getTxtNote().setEnabled(false);
                        carsTableForm.getTxtNote().setText("");
                    }
                }
            }
        });
        
        carsTableForm.btnSelectAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCar();
            }

            private void selectCar() {
                int rowId = carsTableForm.getTblCars().getSelectedRow();
                if (rowId < 0) {
                    JOptionPane.showMessageDialog(carsTableForm, "Please select car!");
                    return;
                }
                Car car = ((CarsTableModel) carsTableForm.getTblCars().getModel()).getCarAt(rowId);
                if (car.getStatus() != CarStatus.AVAILABLE) {
                    JOptionPane.showMessageDialog(carsTableForm, "Selected car is not available for sale!");
                    return;
                }
                String note = carsTableForm.getTxtNote().getText();
                selectedCars.add(new InvoiceItem(null, 0, car.getPrice(), note, car));
                if (JOptionPane.showConfirmDialog(carsTableForm, "Selected Cars:\n"+selectedCars.toString() + "\n Select more cars?", "Select more cars?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    return;
                }
                
                Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_CAR, selectedCars);
                carsTableForm.dispose();
            }
        });
        
        carsTableForm.btnSelectCustomerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCustomer();
            }

            private void selectCustomer() {
                Coordinator.getInstance().openCustomersTableForm(TableFormMode.SELECT_ITEM);
                customer = (Customer)Coordinator.getInstance().getParam(CoordinatorParamConsts.SELECTED_CUSTOMER);
                if (customer != null) {
                    carsTableForm.getTxtCustomer().setText(customer.getName());
                }
                
            }
        });
        
        carsTableForm.btnReserveAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveCar();
            }

            private void reserveCar() {
                try {
                    int rowId = carsTableForm.getTblCars().getSelectedRow();
                    if (rowId < 0) {
                        JOptionPane.showMessageDialog(carsTableForm, "Please select car!");
                        return;
                    }
                    Car car = ((CarsTableModel) carsTableForm.getTblCars().getModel()).getCarAt(rowId);
                    if (car.getStatus() == CarStatus.RESERVED) {
                        JOptionPane.showMessageDialog(carsTableForm, "Selected car is already reserved!");
                        return;
                    }
                    if (car.getStatus() == CarStatus.SOLD) {
                        JOptionPane.showMessageDialog(carsTableForm, "Selected car is already sold!");
                        return;
                    }
                    if (customer == null) {
                        JOptionPane.showMessageDialog(carsTableForm, "Please select customer!");
                        return;
                    }
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DAY_OF_MONTH, 7);
                    Date deadline = cal.getTime();
                    String note = carsTableForm.getTxtNote().getText();
                    Reservation reservation = new Reservation(null, date, deadline, ReservationStatus.ACTIVE, note, car, customer);
                    if (JOptionPane.showConfirmDialog(carsTableForm, "Are you sure you want to SAVE the following reservation: \n" + car.getBrand() + " " + car.getModel() + " for " + customer.getName(), "Save Reservation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        ClientController.getInstance().insertReservation(reservation);
                        if (JOptionPane.showConfirmDialog(carsTableForm, "Reservation saved successfuly!\nReserve more cars?", "Success", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            return;
                        }
                    }
                    Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_CUSTOMER, null);
                    carsTableForm.dispose();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                
            }
        });
        
        carsTableForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTable(null);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_CUSTOMER, null);
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
                List<Car> cars = ClientController.getInstance().getAllCars();
                carsTableForm.getTblCars().setModel(new CarsTableModel(cars));
            }
            else{
                carsTableForm.getTblCars().setModel(new CarsTableModel(c));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void prepareForm(TableFormMode formMode) {
        switch (formMode) {
            case TableFormMode.SEE_ALL_ITEMS:
                carsTableForm.getBtnReserve().setVisible(false);
                carsTableForm.getBtnSelect().setVisible(false);
                carsTableForm.getLblNote().setVisible(false);
                carsTableForm.getTxtNote().setVisible(false);
                carsTableForm.getLblCustomer().setVisible(false);
                carsTableForm.getTxtCustomer().setVisible(false);
                carsTableForm.getBtnSelectCustomer().setVisible(false);
                carsTableForm.getBtnDelete().setVisible(true);
                carsTableForm.getBtnDetails().setVisible(true);
                break;
            case TableFormMode.SELECT_ITEM:
                selectedCars.clear();
                carsTableForm.getBtnReserve().setVisible(false);
                carsTableForm.getBtnSelect().setVisible(true);
                carsTableForm.getLblNote().setVisible(true);
                carsTableForm.getTxtNote().setVisible(true);
                carsTableForm.getLblCustomer().setVisible(false);
                carsTableForm.getTxtCustomer().setVisible(false);
                carsTableForm.getBtnSelectCustomer().setVisible(false);
                carsTableForm.getBtnDelete().setVisible(false);
                carsTableForm.getBtnDetails().setVisible(false);
                break;
            case TableFormMode.RESERVE_CAR:
                selectedCars.clear();
                carsTableForm.getBtnReserve().setVisible(true);
                carsTableForm.getBtnSelect().setVisible(false);
                carsTableForm.getLblNote().setVisible(true);
                carsTableForm.getTxtNote().setVisible(true);
                carsTableForm.getLblCustomer().setVisible(true);
                carsTableForm.getTxtCustomer().setVisible(true);
                carsTableForm.getBtnDelete().setVisible(false);
                carsTableForm.getBtnDetails().setVisible(false);
                break;
            default:
                break;
        }
    }
}
