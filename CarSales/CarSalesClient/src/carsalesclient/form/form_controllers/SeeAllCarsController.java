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
import domain.InvoiceItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
                        carsTableForm.getTxtQuantity().setEnabled(true);
                        carsTableForm.getTxtQuantity().setText("1");
                        carsTableForm.getTxtQuantity().grabFocus();
                        carsTableForm.getTxtQuantity().setSelectionStart(0);
                    }
                    else{
                        carsTableForm.getTxtQuantity().setEnabled(false);
                        carsTableForm.getTxtQuantity().setText("");
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
                int quantity = Integer.parseInt(carsTableForm.getTxtQuantity().getText());
                selectedCars.add(new InvoiceItem(null, 0, quantity, car.getPrice(), car.getPrice()*quantity, car));
                if (JOptionPane.showConfirmDialog(carsTableForm, "Selected Cars:\n"+selectedCars.toString() + "\n Select more cars?", "Select more cars?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    return;
                }
                
                Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_CAR, selectedCars);
                List<InvoiceItem> NovaLista = (List<InvoiceItem>) Coordinator.getInstance().getParam(CoordinatorParamConsts.SELECTED_CAR);
                carsTableForm.dispose();
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
                carsTableForm.getBtnSelect().setVisible(false);
                carsTableForm.getLblQuantity().setVisible(false);
                carsTableForm.getTxtQuantity().setVisible(false);
                carsTableForm.getBtnDelete().setVisible(true);
                carsTableForm.getBtnDetails().setVisible(true);
                break;
            case TableFormMode.SELECT_ITEM:
                selectedCars.clear();
                carsTableForm.getBtnSelect().setVisible(true);
                carsTableForm.getLblQuantity().setVisible(true);
                carsTableForm.getTxtQuantity().setVisible(true);
                carsTableForm.getBtnDelete().setVisible(false);
                carsTableForm.getBtnDetails().setVisible(false);
                break;
            default:
                break;
        }
    }
}
