/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.tableModel;

import domain.User;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class LoggedInUsersTableModel extends AbstractTableModel{
    private List<User> loggedInUsers;

    public LoggedInUsersTableModel(List<User> loggedInUsers) {
        this.loggedInUsers = loggedInUsers;
    }

    @Override
    public int getRowCount() {
        return loggedInUsers.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return loggedInUsers.get(rowIndex).getIdUser();
            case 1:
                return loggedInUsers.get(rowIndex).getFirstName();
            case 2:
                return loggedInUsers.get(rowIndex).getLastName();
            case 3:
                return loggedInUsers.get(rowIndex).getUsername();
            case 4:
                return loggedInUsers.get(rowIndex).getPassword();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"Id","First Name","Last Name","Username","Password"};
        return columns[column];
    }
    
    public void addLoggedInUser(User user) {
        loggedInUsers.add(user);
        fireTableRowsInserted(loggedInUsers.size() - 1, loggedInUsers.size() - 1);
    }

    public void removeLoggedInUser(User user) {
        loggedInUsers.remove(user);
        fireTableRowsDeleted(loggedInUsers.size() - 1, loggedInUsers.size() - 1);
    }
}
