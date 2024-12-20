/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.controller;

import carsalesapplication.domain.Car;
import carsalesapplication.domain.DefaultDomainObject;
import carsalesapplication.form.AddCarForm;
import carsalesapplication.form.logic.FormsLogic;
import java.awt.Container;

/**
 *
 * @author user
 */
public class FormsController {
    private static FormsController instance;
    private FormsLogic frmLogic;
    
    private FormsController() {
        frmLogic = new FormsLogic();
    }
    
    public static FormsController getInstance(){
        if(instance == null){
            instance = new FormsController();
        }
        return instance;
    }
    
    public boolean checkEmptyTxtFields(Container container){
        return frmLogic.checkEmptyTxtFields(container);
    }
    
    public void prepareAddForm(Container container) {
        frmLogic.prepareAddForm(container);
    }

    public void prepareDetailsForm(Container container, DefaultDomainObject ddo) {
        frmLogic.prepareDetailsForm(container, ddo);
    }

    public void prepareUpdateForm(Container container) {
        frmLogic.prepareUpdateForm(container);
    }
}
