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
public class Invoice extends DefaultDomainObject implements  Serializable{
    private Long idInvoice;
    private Long invoiceNum;
    private Date dateOfIssue;
    private Double totalAmount;
    private User user;
    private Customer customer;
    private List<InvoiceItem> invoiceItems;

    public Invoice(Long idInvoice, Long invoiceNum, Date dateOfIssue, Double totalAmount, User user, Customer customer, List<InvoiceItem> invoiceItems) {
        this.idInvoice = idInvoice;
        this.invoiceNum = invoiceNum;
        this.dateOfIssue = dateOfIssue;
        this.totalAmount = totalAmount;
        this.user = user;
        this.customer = customer;
        this.invoiceItems = invoiceItems;
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
    
    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    @Override
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException {
        List<DefaultDomainObject> invoices = new ArrayList<>();
        while(rs.next()){
            User user = new User();
            user.setIdUser(rs.getLong("user_id"));
            Customer customer;
            switch(rs.getString("type")){
                case "individual":
                    customer = new Individual();
                    customer.setIdCustomer(rs.getLong("customer_id"));
                    break;
                case "company":
                    customer = new Company();
                    customer.setIdCustomer(rs.getLong("customer_id"));
                    break;
                default:
                    customer = null;
                    break;
            }
            invoices.add(new Invoice(rs.getLong("id_invoice"), rs.getLong("invoice_num"), rs.getDate("date_of_issue"), rs.getDouble("total_amount"), user, customer, new ArrayList<>()));
        }
        return invoices;
    }

    @Override
    public String getGetAllQuery() {
        return """
               SELECT i.id AS id_invoice, i.invoice_num, i.date_of_issue, i.total_amount, i.user_id, i.customer_id,
                        c.id AS id_customer, c.type
               FROM invoice i JOIN customer c ON i.customer_id = c.id 
               """;
    }

    @Override
    public String getGetAllOrderedQuery() {
        return """
               SELECT i.id AS id_invoice, i.invoice_num, i.date_of_issue, i.total_amount, i.user_id, i.customer_id,
                        c.id AS id_customer, c.type
               FROM invoice i JOIN customer c ON i.customer_id = c.id 
               ORDER BY i.date_of_issue
               """;
    }

    @Override
    public String getGetByConditionQuery() {
        return "SELECT i.id AS id_invoice, i.invoice_num, i.date_of_issue, i.total_amount, i.user_id, i.customer_id, "+
               "c.id AS id_customer, c.type "+
               "FROM invoice i JOIN customer c ON i.customer_id = c.id "+ 
               "WHERE i." + searchCondition + " LIKE '" + searchConditionValue +"%'";
    }

    @Override
    public String getInsertQuery() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "INSERT INTO invoice "+
                "(invoice_num, date_of_issue, total_amount, user_id, customer_id) "+
                "values("+ invoiceNum +", '"+ sdf.format(dateOfIssue) +"', "+ totalAmount +", "+ user.getIdUser() +", "+ customer.getIdCustomer() +")";
    }

    @Override
    public String getUpdateQuery() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "UPDATE invoice "+
                "SET invoice_num=" + invoiceNum + ", date_of_issue = '" + sdf.format(dateOfIssue) + "', total_amount = " + totalAmount + ", user_id = "+ user.getIdUser()+", customer_id = " + customer.getIdCustomer()+" "+
                "WHERE id = " + idInvoice;
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM invoice WHERE id = " + idInvoice;
    }

}
