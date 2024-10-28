/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.controller;

import carsalesapplication.domain.User;
import carsalesapplication.util.UserUtil;

/**
 *
 * @author user
 */
public class FrontController {
    private final UserUtil userUtil;

    public FrontController() {
        userUtil = new UserUtil();
    }
    
    public User login(User user) throws Exception{
        try {
            return userUtil.login(user);
        } catch (Exception e) {
            throw e;
        }
    }
}
