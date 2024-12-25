/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.Controller;
import carsalesclient.form.AddCarForm;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.FormMode;
import domain.Car;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalBorders;

/**
 *
 * @author user
 */
public class CarController {
    private final AddCarForm carForm;

    public CarController(AddCarForm carForm) {
        this.carForm = carForm;
        addListeners();
    }

    public void openForm(FormMode formMode){
        prepareForm(formMode);
        carForm.setVisible(true);
    }
    
    private void addListeners() {
        carForm.btnEnableChangesAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareForm(FormMode.UPDATE_FORM);
            }
        });
        
        carForm.btnEditAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }

            private void update() {
                try {
                    if(!emptyFields()){
                        Car car = new Car(null, carForm.getTxtBrand().getText(), carForm.getTxtModel().getText(), Double.parseDouble(carForm.getTxtPrice().getText()));
                        if(JOptionPane.showConfirmDialog(carForm, "Are you sure you want to SAVE the following changes into the database: \n"+car.getBrand()+" "+car.getModel()+", "+car.getPrice()+"$", "Change car details", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                            car.setIdCar(((Car) Coordinator.getInstance().getParam("Car_details")).getIdCar());
                            car.setUpdateConditionValue(car.getIdCar());
                            Controller.getInstance().updateRow(car);
                            JOptionPane.showMessageDialog(carForm, car.getBrand()+" "+car.getModel()+" has been successfully added to the database!");
                            carForm.dispose();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(carForm, "Fill all required fields");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                
            }
        });
        
        carForm.btnSaveAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    if(!emptyFields()){
                        Car car = new Car(null, carForm.getTxtBrand().getText(), carForm.getTxtModel().getText(), Double.parseDouble(carForm.getTxtPrice().getText()));
                        if(JOptionPane.showConfirmDialog(carForm, "Are you sure you want to INSERT the following car into the database: \n"+car.getBrand()+" "+car.getModel()+", "+car.getPrice()+"$", "Add car", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                            Controller.getInstance().insertRow(car);
                            if(JOptionPane.showConfirmDialog(carForm, car.getBrand()+" "+car.getModel()+" has been successfully added to the database!\n\nAdd more cars?", "Success", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                                prepareForm(FormMode.ADD_FORM);
                            }
                            else{
                                carForm.dispose();
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(carForm, "Fill all required fields");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        
        carForm.btnCancelAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carForm.dispose();
            }
        });
        
        
        carForm.getTxtBrand().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carForm.getBtnSave().doClick();
            }
        });
        
        carForm.getTxtModel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carForm.getBtnSave().doClick();
            }
        });
        
        carForm.getTxtPrice().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carForm.getBtnSave().doClick();
            }
        });
    }

    private void prepareForm(FormMode formMode) {
        switch(formMode){
            case FormMode.ADD_FORM:
                carForm.getLblId().setVisible(false);
                carForm.getTxtId().setVisible(false);
                carForm.getTxtId().setEnabled(false);
                
                carForm.getTxtBrand().setText("");
                carForm.getTxtBrand().setEnabled(true);
                carForm.getTxtModel().setText("");
                carForm.getTxtModel().setEnabled(true);
                carForm.getTxtPrice().setText("");
                carForm.getTxtPrice().setEnabled(true);
                
                carForm.getBtnEnableChanges().setVisible(false);
                carForm.getBtnEdit().setVisible(false);
                carForm.getBtnSave().setVisible(true);
                carForm.getBtnSave().setEnabled(true);
                break;
                
            case FormMode.DETAILS_FORM:
                Car car = (Car) Coordinator.getInstance().getParam("Car_details");
                carForm.getLblId().setVisible(true);
                carForm.getTxtId().setVisible(true);
                carForm.getTxtId().setText(car.getIdCar().toString());
                carForm.getTxtId().setEnabled(false);
                
                carForm.getTxtBrand().setText(car.getBrand());
                carForm.getTxtBrand().setEnabled(false);
                carForm.getTxtModel().setText(car.getModel());
                carForm.getTxtModel().setEnabled(false);
                carForm.getTxtPrice().setText(Double.toString(car.getPrice()));
                carForm.getTxtPrice().setEnabled(false);
                
                carForm.getBtnEnableChanges().setVisible(true);
                carForm.getBtnEnableChanges().setEnabled(true);
                carForm.getBtnEdit().setVisible(true);
                carForm.getBtnEdit().setEnabled(false);
                carForm.getBtnSave().setVisible(false);
                break;
                
            case FormMode.UPDATE_FORM:
                carForm.getLblId().setVisible(true);
                carForm.getTxtId().setVisible(true);
                carForm.getTxtId().setEnabled(false);
                
                carForm.getTxtBrand().setEnabled(true);
                carForm.getTxtModel().setEnabled(true);
                carForm.getTxtPrice().setEnabled(true);
                
                carForm.getBtnEnableChanges().setVisible(true);
                carForm.getBtnEnableChanges().setEnabled(false);
                carForm.getBtnEdit().setEnabled(true);
                break;
                
            default:
                break;
        }
    }
    
    private boolean emptyFields(){
        Border border = new LineBorder(Color.red, 1,true);
        Font font = new Font("Gill Sans MT", 1, 8);
        boolean empty = false;
        
        
        if(carForm.getTxtBrand().getText().isBlank()){
            carForm.getTxtBrand().setBorder(new TitledBorder(border, "Required Field", 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getTxtBrand().setBorder(new MetalBorders.TextFieldBorder());
                
        }
        if(carForm.getTxtModel().getText().isBlank()){
            carForm.getTxtModel().setBorder(new TitledBorder(border, "Required Field", 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getTxtModel().setBorder(new MetalBorders.TextFieldBorder());
                
        }
        if(carForm.getTxtPrice().getText().isBlank()){
            carForm.getTxtPrice().setBorder(new TitledBorder(border, "Required Field", 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getTxtPrice().setBorder(new MetalBorders.TextFieldBorder());
                
        }
        
        return empty;
    }
}
