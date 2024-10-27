/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesapplication.util;

import carsalesapplication.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class UserUtil {
    public static List<User> getUsers(){
        List<User> users = new ArrayList(){
            {
                add(new User(1,"nemanja","nemanja","Nemanja","Djukic"));
                add(new User(2,"nemanja2","nemanja2","Nemanja2","Djukic"));
                add(new User(3,"nemanja3","nemanja3","Nemanja3","Djukic"));
            }
        };
        return users;
    }
}
