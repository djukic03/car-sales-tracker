/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class User implements DefaultDomainObject, Serializable{
    private Long idUser;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    String searchCondition;
    String searchConditionValue;
    Long deleteConditionValue;
    Long updateConditionValue;

    public User() {
    }
    
    public User(Long idUser, String username, String password, String firstName, String lastName) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSearchCondition(String condition) {
        this.searchCondition = condition;
    }

    public void setSearchConditionValue(String conditionValue) {
        this.searchConditionValue = conditionValue;
    }

    public void setDeleteConditionValue(Long deleteConditionValue) {
        this.deleteConditionValue = deleteConditionValue;
    }

    public void setUpdateConditionValue(Long updateConditionValue) {
        this.updateConditionValue = updateConditionValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User){
            User user = (User) obj;
            return idUser.equals(user.idUser);
        }
        return false;
    }
    

    @Override
    public String getClassName() {
        return "user";
    }

    @Override
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException {
        List<DefaultDomainObject> users = new ArrayList<>();
        try {
            while(rs.next()){
                users.add(new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return users;
    }

    @Override
    public String getSearchCondition() {
        return searchCondition;
    }

    @Override
    public String getSearchConditionValue() {
        return searchConditionValue;
    }

    @Override
    public String getInsertValues() {
        return "'"+ username +"', '"+ password +"', '"+ firstName +"', '"+ lastName +"'";
    }

    @Override
    public String getInsertColumns() {
        return "username, password, first_name, last_name";
    }

    @Override
    public String getDeleteCondition() {
        return "id";
    }

    @Override
    public String getDeleteConditionValue() {
        return deleteConditionValue.toString();
    }

    @Override
    public String getUpdateValues() {
        return "username = '"+ username +"', password = '"+ password +"', first_name = '"+ firstName +"', last_name = '"+ lastName +"'";
    }

    @Override
    public String getUpdateCondition() {
        return "id";
    }

    @Override
    public String getUpdateConditionValue() {
        return updateConditionValue.toString();
    }

    @Override
    public String getOrderCondition() {
        return "last_name";
    }
    
    
}
