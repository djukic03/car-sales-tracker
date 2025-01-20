/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.tableModels;

import domain.Invoice;
import java.awt.Component;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author user
 */
public class InvoicesTableModel extends AbstractTableModel {
    private List<Invoice> invoices;

    public InvoicesTableModel(List<Invoice> invoices) {
        this.invoices = invoices;
    }
    
    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return invoices.get(rowIndex).getInvoiceNum();
            case 1:
                return invoices.get(rowIndex).getDateOfIssue();
            case 2:
                return invoices.get(rowIndex).getTotalAmount() + " €";
            case 3:
                return "See salesperson";
            case 4:
                return "See customer";
            case 5:
                return "See Items";
            default:
                break;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String columns[] = {"Invoice Number","Issue Date","Amount","Salesperson","Customer","Items"};
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3 || columnIndex == 4 || columnIndex == 5) {
            return JPanel.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3 || columnIndex == 4 || columnIndex == 5;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
    
    public Invoice getInvoiceAt(int rowId){
        return invoices.get(rowId);
    }
}
