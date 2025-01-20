/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class Invoice implements DefaultDomainObject, Serializable{
    private Long idInvoice;
    private Long invoiceNum;
    private Date dateOfIssue;
    private Double totalAmount;
    private User user;
    private Customer customer;
    String searchCondition;
    String searchConditionValue;

    public Invoice(Long idInvoice, Long invoiceNum, Date dateOfIssue, Double totalAmount, User user, Customer customer) {
        this.idInvoice = idInvoice;
        this.invoiceNum = invoiceNum;
        this.dateOfIssue = dateOfIssue;
        this.totalAmount = totalAmount;
        this.user = user;
        this.customer = customer;
    }


    public Invoice() {
    }

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Long getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(Long invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public void setSearchConditionValue(String searchConditionValue) {
        this.searchConditionValue = searchConditionValue;
    }
    
    @Override
    public String getClassName() {
        return "invoice";
    }

    @Override
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException {
        List<DefaultDomainObject> invoices = new ArrayList<>();
        while(rs.next()){
            User user = new User();
            user.setIdUser(rs.getLong("user_id"));
            Customer customer = new Customer();
            customer.setIdCustomer(rs.getLong("customer_id"));
            invoices.add(new Invoice(rs.getLong("id"), rs.getLong("invoice_num"), rs.getDate("date_of_issue"), rs.getDouble("total_amount"), user, customer));
        }
        return invoices;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return invoiceNum +", '"+ sdf.format(dateOfIssue) +"', "+ totalAmount +", "+ user.getIdUser() +", "+ customer.getIdCustomer();
    }

    @Override
    public String getInsertColumns() {
        return "invoice_num, date_of_issue, total_amount, user_id, customer_id";
    }

    @Override
    public String getDeleteCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDeleteConditionValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateConditionValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getOrderCondition() {
        return "date_of_issue";
    }

}
