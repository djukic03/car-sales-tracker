/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.AddCarForm;
import carsalesclient.form.constants.CoordinatorParamConsts;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.AddFormMode;
import domain.Car;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
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
    ResourceBundle bundle;

    public CarController(AddCarForm carForm) {
        this.carForm = carForm;
        addListeners();
    }

    public void openForm(AddFormMode formMode){
        prepareForm(formMode);
        carForm.setVisible(true);
    }
    
    private void addListeners() {
        carForm.btnEnableChangesAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareForm(AddFormMode.UPDATE_FORM);
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
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                        Date reg = sdf.parse(carForm.getTxtFirstReg().getText());
                        Car car = new Car(null, carForm.getTxtBrand().getText(), carForm.getTxtModel().getText(), reg, Integer.parseInt(carForm.getTxtMileage().getText()), getCategory(), getFuel(), Double.valueOf(carForm.getTxtEngineCapacity().getText()), Double.valueOf(carForm.getTxtEnginePower().getText()), getGearbox(), Double.parseDouble(carForm.getTxtPrice().getText()));
                        if(JOptionPane.showConfirmDialog(carForm, "Are you sure you want to SAVE the following changes into the database: \n"+car.getBrand()+" "+car.getModel()+", "+car.getPrice()+"$", "Change car details", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                            car.setIdCar(((Car) Coordinator.getInstance().getParam(CoordinatorParamConsts.CAR_DETAILS)).getIdCar());
                            car.setUpdateConditionValue(car.getIdCar());
                            ClientController.getInstance().updateCar(car);
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
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                        Date reg = sdf.parse(carForm.getTxtFirstReg().getText());
                        Car car = new Car(null, carForm.getTxtBrand().getText(), carForm.getTxtModel().getText(), reg, Integer.parseInt(carForm.getTxtMileage().getText()), getCategory(), getFuel(), Double.valueOf(carForm.getTxtEngineCapacity().getText()), Double.valueOf(carForm.getTxtEnginePower().getText()), getGearbox(), Double.parseDouble(carForm.getTxtPrice().getText()));
                        if(JOptionPane.showConfirmDialog(carForm, "Are you sure you want to INSERT the following car into the database: \n"+car.getBrand()+" "+car.getModel()+", "+car.getPrice()+"$", "Add car", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                            ClientController.getInstance().insertCar(car);
                            if(JOptionPane.showConfirmDialog(carForm, car.getBrand()+" "+car.getModel()+" has been successfully added to the database!\n\nAdd more cars?", "Success", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                                prepareForm(AddFormMode.ADD_FORM);
                            }
                            else{
                                carForm.dispose();
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(carForm, bundle.getString("Fill_all_required_fields"));
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

    private String getCategory(){
        int i = carForm.getCbCategory().getSelectedIndex();
        switch (i) {
            case 0:
                return "Cabrio";
            case 1:
                return "Coupe";
            case 2:
                return "Estate/Wagon";
            case 3:
                return "Hatchback";
            case 4:
                return "Limousine/Salon";
            case 5:
                return "Minivan/Van";
            case 6:
                return "SUV";
            default:
                return null;
        }
    }
    
    private String getFuel(){
        int i = carForm.getCbFuel().getSelectedIndex();
        switch (i) {
            case 0:
                return "Petrol";
            case 1:
                return "Diesel";
            case 2:
                return "CNG";
            case 3:
                return "Hybrid";
            case 4:
                return "Electric";
            default:
                return null;
        }
    }
    
    private String getGearbox(){
        int i = carForm.getCbGearbox().getSelectedIndex();
        switch (i) {
            case 0:
                return "Manual";
            case 1:
                return "Automatic";
            case 2:
                return "Semi-automatic";
            default:
                return null;
        }
    }
    
    private boolean emptyFields(){
        Border border = new LineBorder(Color.red, 1,true);
        Font font = new Font("Comic Sans MS", 1, 8);
        boolean empty = false;
        
        
        if(carForm.getTxtBrand().getText().isBlank()){
            carForm.getTxtBrand().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getTxtBrand().setBorder(new MetalBorders.TextFieldBorder());
        }
        
        if(carForm.getTxtModel().getText().isBlank()){
            carForm.getTxtModel().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getTxtModel().setBorder(new MetalBorders.TextFieldBorder());
        }
        
        if(carForm.getTxtFirstReg().getText().isBlank()){
            carForm.getTxtFirstReg().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getTxtFirstReg().setBorder(new MetalBorders.TextFieldBorder());
        }
        
        if(carForm.getTxtMileage().getText().isBlank()){
            carForm.getTxtMileage().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getTxtMileage().setBorder(new MetalBorders.TextFieldBorder());
        }
        
        if(carForm.getCbCategory().getSelectedIndex() == -1){
            carForm.getCbCategory().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getCbCategory().setBorder(UIManager.getBorder("ComboBox.border"));
        }
        
        if(carForm.getCbFuel().getSelectedIndex() == -1){
            carForm.getCbFuel().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getCbFuel().setBorder(UIManager.getBorder("ComboBox.border"));
        }
        
        if(carForm.getTxtEngineCapacity().getText().isBlank()){
            carForm.getTxtEngineCapacity().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getTxtEngineCapacity().setBorder(new MetalBorders.TextFieldBorder());
        }
        
        if(carForm.getTxtEnginePower().getText().isBlank()){
            carForm.getTxtEnginePower().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getTxtEnginePower().setBorder(new MetalBorders.TextFieldBorder());
        }
        
        if(carForm.getCbGearbox().getSelectedIndex() == -1){
            carForm.getCbGearbox().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getCbGearbox().setBorder(UIManager.getBorder("ComboBox.border"));
        }
        
        if(carForm.getTxtPrice().getText().isBlank()){
            carForm.getTxtPrice().setBorder(new TitledBorder(border, bundle.getString("Required_Field"), 0, 0, font, Color.RED));
            empty = true;
        }
        else{
            carForm.getTxtPrice().setBorder(new MetalBorders.TextFieldBorder());
        }
        
        return empty;
    }
    
    private void prepareForm(AddFormMode formMode) {
        setLanguage();
        switch(formMode){
            case AddFormMode.ADD_FORM:
                carForm.getLblTitle().setText(bundle.getString("ADD_NEW_CAR"));
                
                carForm.getLblId().setVisible(false);
                carForm.getTxtId().setVisible(false);
                carForm.getTxtId().setEnabled(false);
                
                carForm.getTxtBrand().setText("");
                carForm.getTxtBrand().setEnabled(true);
                carForm.getTxtModel().setText("");
                carForm.getTxtModel().setEnabled(true);
                carForm.getTxtFirstReg().setText("");
                carForm.getTxtFirstReg().setEnabled(true);
                carForm.getTxtMileage().setText("");
                carForm.getTxtMileage().setEnabled(true);
                carForm.getCbCategory().setSelectedIndex(-1);
                carForm.getCbCategory().setEnabled(true);
                carForm.getCbFuel().setSelectedIndex(-1);
                carForm.getCbFuel().setEnabled(true);
                carForm.getTxtEngineCapacity().setText("");
                carForm.getTxtEngineCapacity().setEnabled(true);
                carForm.getTxtEnginePower().setText("");
                carForm.getTxtEnginePower().setEnabled(true);
                carForm.getCbGearbox().setSelectedIndex(-1);
                carForm.getCbGearbox().setEnabled(true);
                carForm.getTxtPrice().setText("");
                carForm.getTxtPrice().setEnabled(true);
                
                carForm.getBtnEnableChanges().setVisible(false);
                carForm.getBtnEdit().setVisible(false);
                carForm.getBtnSave().setVisible(true);
                carForm.getBtnSave().setEnabled(true);
                break;
                
            case AddFormMode.DETAILS_FORM:
                carForm.getLblTitle().setText(bundle.getString("CAR_DETAILS"));
                
                Car car = (Car) Coordinator.getInstance().getParam(CoordinatorParamConsts.CAR_DETAILS);
                carForm.getLblId().setVisible(true);
                carForm.getTxtId().setVisible(true);
                carForm.getTxtId().setText(car.getIdCar().toString());
                carForm.getTxtId().setEnabled(false);
                
                carForm.getTxtBrand().setText(car.getBrand());
                carForm.getTxtBrand().setEnabled(false);
                carForm.getTxtModel().setText(car.getModel());
                carForm.getTxtModel().setEnabled(false);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(car.getFirstReg());
                carForm.getTxtFirstReg().setText(Integer.toString(calendar.get(Calendar.YEAR)));
                carForm.getTxtFirstReg().setEnabled(false);
                carForm.getTxtMileage().setText(Integer.toString(car.getMileage()));
                carForm.getTxtMileage().setEnabled(false);
                carForm.getCbCategory().setSelectedItem(car.getCategory());
                carForm.getCbCategory().setEnabled(false);
                carForm.getCbFuel().setSelectedItem(car.getFuel());
                carForm.getCbFuel().setEnabled(false);
                carForm.getTxtEngineCapacity().setText(Double.toString(car.getEngineCapacity()));
                carForm.getTxtEngineCapacity().setEnabled(false);
                carForm.getTxtEnginePower().setText(Double.toString(car.getEnginePower()));
                carForm.getTxtEnginePower().setEnabled(false);
                carForm.getCbGearbox().setSelectedItem(car.getGearbox());
                carForm.getCbGearbox().setEnabled(false);
                carForm.getTxtPrice().setText(Double.toString(car.getPrice()));
                carForm.getTxtPrice().setEnabled(false);
                
                carForm.getBtnEnableChanges().setVisible(true);
                carForm.getBtnEnableChanges().setEnabled(true);
                carForm.getBtnEdit().setVisible(true);
                carForm.getBtnEdit().setEnabled(false);
                carForm.getBtnSave().setVisible(false);
                break;
                
            case AddFormMode.UPDATE_FORM:
                carForm.getLblId().setVisible(true);
                carForm.getTxtId().setVisible(true);
                carForm.getTxtId().setEnabled(false);
                
                carForm.getTxtBrand().setEnabled(true);
                carForm.getTxtModel().setEnabled(true);
                carForm.getTxtFirstReg().setEnabled(true);
                carForm.getTxtMileage().setEnabled(true);
                carForm.getCbCategory().setEnabled(true);
                carForm.getCbFuel().setEnabled(true);
                carForm.getTxtEngineCapacity().setEnabled(true);
                carForm.getTxtEnginePower().setEnabled(true);
                carForm.getCbGearbox().setEnabled(true);
                carForm.getTxtPrice().setEnabled(true);
                
                carForm.getBtnEnableChanges().setVisible(true);
                carForm.getBtnEnableChanges().setEnabled(false);
                carForm.getBtnEdit().setEnabled(true);
                break;
                
            default:
                break;
        }
        
    }
    
    private void setLanguage() {
        try {
            String language = Coordinator.getInstance().getParam(CoordinatorParamConsts.SELECTED_LANGUAGE).toString();
            Locale locale = Locale.of(language);
            bundle = ResourceBundle.getBundle("resources.language", locale);
            
            carForm.getLblBrand().setText(bundle.getString("Brand")+":");
            carForm.getLblModel().setText(bundle.getString("Model")+":");
            carForm.getLblFirstReg().setText(bundle.getString("First_Registration")+bundle.getString("(Y)")+":");
            carForm.getLblMileage().setText(bundle.getString("Mileage")+"(km):");
            carForm.getLblCategory().setText(bundle.getString("Category")+":");
            String[] categories = {
                bundle.getString("Cabrio"),
                bundle.getString("Coupe"),
                bundle.getString("Estate/Wagon"),
                bundle.getString("Hatchback"),
                bundle.getString("Limousine/Salon"),
                bundle.getString("Minivan/Van"),
                bundle.getString("SUV"),  
            };
            carForm.getCbCategory().setModel(new DefaultComboBoxModel<>(categories));
            
            carForm.getLblFuel().setText(bundle.getString("Fuel")+":");
            String[] fuel = {
                bundle.getString("Petrol"),
                bundle.getString("Diesel"),
                bundle.getString("CNG"),
                bundle.getString("Hybrid"),
                bundle.getString("Electric")
            };
            carForm.getCbFuel().setModel(new DefaultComboBoxModel<>(fuel));
            
            carForm.getLblEngCapacity().setText(bundle.getString("Engine_Capacity")+"(L):");
            carForm.getLblEngPower().setText(bundle.getString("Engine_Power")+bundle.getString("(HP)")+":");
            carForm.getLblGearbox().setText(bundle.getString("Gearbox")+":");
            String[] gearbox = {
                bundle.getString("Manual"),
                bundle.getString("Automatic"),
                bundle.getString("Semi-automatic")
            };
            carForm.getCbGearbox().setModel(new DefaultComboBoxModel<>(gearbox));
            
            carForm.getLblPrice().setText(bundle.getString("Price")+"(€):");
            
            carForm.getBtnEnableChanges().setText(bundle.getString("Enable_changes"));
            carForm.getBtnEdit().setText(bundle.getString("Edit"));
            carForm.getBtnSave().setText(bundle.getString("Save"));
            carForm.getBtnCancel().setText(bundle.getString("Cancel"));
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
