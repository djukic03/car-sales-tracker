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
    public List<User> users;

    public UserUtil() {
        users = new ArrayList(){
            {
                add(new User(1,"nemanja","nemanja","Nemanja","Djukic"));
                add(new User(2,"nemanja2","nemanja2","Nemanja2","Djukic"));
                add(new User(3,"nemanja3","nemanja3","Nemanja3","Djukic"));
            }
        };
    }
    
      
    public List<User> getUsers(){
        return users;
    }
    
    public User login(User user) throws Exception{
        for (int i = 0; i < users.size(); i++) {
            if(user.getUsername().equals(users.get(i).getUsername()) && user.getPassword().equals(users.get(i).getPassword())){
                return users.get(i);
            }
        }
        throw new Exception("User not found");
    }
}
