/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.tableModels;

import domain.Customer;
import domain.User;
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
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return customers.get(rowIndex).getName();
            case 1:
                return customers.get(rowIndex).getPhone();
            case 2:
                return customers.get(rowIndex).getEmail();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"Name","Phone","Email"};
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }
    
    public Customer getCustomerAt(int rowId){
        return customers.get(rowId);
    }
}
