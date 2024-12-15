/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalBorders;

/**
 *
 * @author user
 */
public class FormsController {
    private static FormsController instance;
    private final Container container;
    private final List<JTextField> txtFields2222;
    
    public FormsController(Container container) {
        this.txtFields2222 = getAllTextFields(container);
        this.container = container;
    }
    
    public static FormsController getInstance(Container con){
        instance = new FormsController(con);
        return instance;
    }
    
    public boolean checkEmptyTxtFields(){
        Border border = new LineBorder(Color.red, 1,true);
        Font font = new Font("Gill Sans MT", 1, 8);
        boolean empty = false;
        
        for (JTextField textField : txtFields2222) {
            if(textField.getText().isEmpty()){
                textField.setBorder(new TitledBorder(border, "Required Field", 0, 0, font, Color.RED));
                empty = true;
            }
            else{
                textField.setBorder(new MetalBorders.TextFieldBorder());
            }
        }
        
        return empty;
    }

    private static List<JTextField> getAllTextFields(Container container) {
        List<JTextField> textFields = new ArrayList<>();
        for (Component component : container.getComponents()) {
            if (component instanceof JTextField) {
                if (component.isVisible()) {
                    textFields.add((JTextField) component);
                }
            } else if (component instanceof Container) {
                textFields.addAll(getAllTextFields((Container) component));
            }
        }
        return textFields;
    }

    public void prepareAddForm() {
        for (JTextField txtField : txtFields2222) {
            
            if ("id".equals(txtField.getName())) {
                txtField.setVisible(false);
            }
            else{
                txtField.setText("");
            }
        }
        Component[] comp = this.container.getComponents();
        for (Component component : comp) {
            if ("lblId".equals(component.getName()) || "btnEnableChanges".equals(component.getName())) {
                component.setVisible(false);
            }
        }
    }
    
}
