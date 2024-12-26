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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Customer implements DefaultDomainObject, Serializable{
    private Long idCustomer;
    private String name;
    private String phone;
    private String email;
    String searchCondition;
    String searchConditionValue;
    Long deleteConditionValue;
    Long updateConditionValue;

    public Customer(Long idCustomer, String name, String phone, String email) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Customer() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public void setSearchConditionValue(String searchConditionValue) {
        this.searchConditionValue = searchConditionValue;
    }

    public void setDeleteConditionValue(Long deleteConditionValue) {
        this.deleteConditionValue = deleteConditionValue;
    }

    public void setUpdateConditionValue(Long updateConditionValue) {
        this.updateConditionValue = updateConditionValue;
    }
    
    

    
    @Override
    public String getClassName() {
        return "customer";
    }

    @Override
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException {
        List<DefaultDomainObject> customers = new ArrayList<>();
        try {
            while(rs.next()){
                customers.add(new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("phone"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return customers;
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
        return "'"+ name +"', '"+ phone +"', '"+ email +"'";
    }

    @Override
    public String getInsertColumns() {
        return "name, phone, email";
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
        return "name = '"+ name +"', phone = '"+ phone +"', email = '"+ email +"'";
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
