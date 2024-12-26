/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.tableModels;

import domain.User;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class UsersTableModel extends AbstractTableModel{
    private List<User> users;

    public UsersTableModel(List<User> users) {
        this.users = users;
    }
    
    

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return users.get(rowIndex).getFirstName();
            case 1:
                return users.get(rowIndex).getLastName();
            case 2:
                return users.get(rowIndex).getUsername();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"First Name","Last Name","Username"};
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }
}
