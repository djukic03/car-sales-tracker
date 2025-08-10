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
public class Company extends Customer implements Serializable{
    private String companyName;
    private String taxNumber;
    private String registrationNumber;
    private String authorizedPerson;

    public Company(Long idCustomer, String companyName, String taxNumber, String registrationNumber, String authorizedPerson, String phone, String email, String address) {
        super(idCustomer, phone, email, address);
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.registrationNumber = registrationNumber;
        this.authorizedPerson = authorizedPerson;
    }

    public Company(){
        
    }
    
    
    @Override
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException {
        List<DefaultDomainObject> companies = new ArrayList<>();
        try {
            while(rs.next()){
                companies.add(new Company(rs.getLong("id"), rs.getString("company_name"), rs.getString("pib"), rs.getString("mb"), rs.getString("authorized_person"), rs.getString("phone"), rs.getString("email"), rs.getString("address")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return companies;
    }

    @Override
    public String getGetAllQuery() {
        return "SELECT * FROM company com JOIN customer c ON com.id = c.id";
    }

    @Override
    public String getGetAllOrderedQuery() {
        return "SELECT * FROM company com JOIN customer c ON com.id = c.id ORDER BY com.company_name";
    }

    @Override
    public String getGetByConditionQuery() {
        return "SELECT * FROM company com JOIN customer c ON com.id = c.id WHERE com."+ searchCondition +" LIKE '"+ searchConditionValue +"%'";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO company "+
                "(id, company_name, pib, mb, authorized_person) "+
                "values("+ idCustomer +", '"+ companyName +"', '"+ taxNumber +"', '"+ registrationNumber +"', '"+ authorizedPerson +"')";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE company "+
                "SET company_name = '"+ companyName +"', pib = '"+ taxNumber +"', mb = '"+ registrationNumber +"', authorized_person = '"+ authorizedPerson +"' "+
                "WHERE id = "+ idCustomer;
    }

    @Override
    public String getDeleteQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAuthorizedPerson() {
        return authorizedPerson;
    }

    public void setAuthorizedPerson(String authorizedPerson) {
        this.authorizedPerson = authorizedPerson;
    }
    
}
