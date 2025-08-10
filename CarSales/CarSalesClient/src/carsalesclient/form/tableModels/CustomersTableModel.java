/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.tableModels;

import domain.Company;
import domain.Customer;
import domain.Individual;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class CustomersTableModel extends AbstractTableModel{
    private List<Customer> customers;

    public CustomersTableModel(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (customers.getFirst() instanceof Individual) {
            switch (columnIndex) {
                case 0:
                    return ((Individual) customers.get(rowIndex)).getFirstName();
                case 1:
                    return ((Individual) customers.get(rowIndex)).getLastName();
                case 2:
                    return ((Individual) customers.get(rowIndex)).getPhone();
                case 3:
                    return ((Individual) customers.get(rowIndex)).getEmail();
                case 4:
                    return ((Individual) customers.get(rowIndex)).getAddress();
                case 5:
                    return ((Individual) customers.get(rowIndex)).getJmbg();
                case 6:
                    return ((Individual) customers.get(rowIndex)).getIdCardNumber();
                default:
                    throw new AssertionError();
            }
        }
        else{
            switch (columnIndex) {
                case 0:
                    return ((Company) customers.get(rowIndex)).getCompanyName();
                case 1:
                    return ((Company) customers.get(rowIndex)).getTaxNumber();
                case 2:
                    return ((Company) customers.get(rowIndex)).getRegistrationNumber();
                case 3:
                    return ((Company) customers.get(rowIndex)).getPhone();
                case 4:
                    return ((Company) customers.get(rowIndex)).getEmail();
                case 5:
                    return ((Company) customers.get(rowIndex)).getAddress();
                case 6:
                    return ((Company) customers.get(rowIndex)).getAuthorizedPerson();
                
                default:
                    throw new AssertionError();
            }
        }
        
    }

    @Override
    public String getColumnName(int column) {
        if (customers.getFirst() instanceof Individual){
            String[] columns = {"First Name", "Last Name", "Phone", "Email", "Address", "JMBG", "ID Card Number"};
            return columns[column];
        }
        else{
            String[] columns = {"Company Name", "Tax Number", "Registration Number", "Phone", "Email", "Address", "Authorized Person"};
            return columns[column];
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }
    
    public Customer getCustomerAt(int rowId){
        return customers.get(rowId);
    }
}
