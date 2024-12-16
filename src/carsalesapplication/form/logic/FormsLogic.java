/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.form.logic;

import carsalesapplication.domain.DefaultDomainObject;
import carsalesapplication.domain.DetailsFormData;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalBorders;

/**
 *
 * @author user
 */
public class FormsLogic {

    public FormsLogic() {
    }
    
    public boolean checkEmptyTxtFields(Container container){
        List<Component> components = getAllComponents(container);
        Border border = new LineBorder(Color.red, 1,true);
        Font font = new Font("Gill Sans MT", 1, 8);
        boolean empty = false;
        
        for (Component component : components) {
            if(component instanceof JTextField){
                JTextField textField = (JTextField) component;
                if(textField.getText().isEmpty()){
                    textField.setBorder(new TitledBorder(border, "Required Field", 0, 0, font, Color.RED));
                    empty = true;
                }
                else{
                    textField.setBorder(new MetalBorders.TextFieldBorder());
                }
            }
        }
        
        return empty;
    }
    
    public void prepareAddForm(Container container) {
        List<Component> components = getAllComponents(container);
        for (Component component : components) {
            if(component.getName() != null){
                switch(component.getName()){
                    case "id" -> component.setVisible(false);
                    case "btnEnableChanges" -> component.setVisible(false);
                    default -> {
                        if(component instanceof JTextField jTextField){
                            jTextField.setText("");
                        }
                    }
                }
            }
        }
    }
    
    public void prepareDetailsForm(Container container, DefaultDomainObject ddo) {
        List<DetailsFormData> data = ddo.getDetailsFormData();
        List<Component> components = getAllComponents(container);
        List<JTextField> txtFields = new ArrayList<>();
        for (Component component : components) {
            if(component.getName() != null){
                if(component instanceof JTextField){
                    component.setEnabled(false);
                    txtFields.add((JTextField) component);
                }
                switch(component.getName()){
                    case "title" -> {
                        JLabel lbl = (JLabel) component;
                        lbl.setText(ddo.getClassName().toUpperCase() + " details");
                    }
                    case "id" -> component.setVisible(true);
                    case "btnEnableChanges" -> component.setVisible(true);
                    default -> {
                    }
                }
            }
        }
        for (DetailsFormData d : data) {
            for (JTextField txtField : txtFields) {
                if(txtField.getName().equals(d.getName())){
                    txtField.setText(d.getData());
                }
            }
        }
    }
    
    public void prepareUpdateForm(Container container) {
        List<Component> components = getAllComponents(container);
        for (Component component : components) {
            if(component instanceof JTextField && !component.getName().equals("id")){
                component.setEnabled(true);
            }
        }
    }
    
    private static List<Component> getAllComponents(Container container) {
        List<Component> components = new ArrayList<>();
        for (Component component : container.getComponents()) {
            components.add(component);
            if (component instanceof Container) {
                components.addAll(getAllComponents((Container) component));
            }
        }
        return components;
    }
}
