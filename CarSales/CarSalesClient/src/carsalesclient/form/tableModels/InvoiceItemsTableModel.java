/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.tableModels;

import carsalesclient.controller.ClientController;
import domain.Car;
import domain.DefaultDomainObject;
import domain.InvoiceItem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class InvoiceItemsTableModel extends AbstractTableModel{
    private List<InvoiceItem> items;
    private List<Car> cars = new ArrayList<>();

    public InvoiceItemsTableModel(List<InvoiceItem> items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return items.get(rowIndex).getNum();
            case 1:
                return items.get(rowIndex).getCar().getBrand();
            case 2:
                return items.get(rowIndex).getCar().getModel();
            case 3:
                return items.get(rowIndex).getPriceOfOne();
            case 4:
                return items.get(rowIndex).getQuantity();
            case 5:
                return items.get(rowIndex).getSum();
            default:
                break;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String columns[] = {"Num","Brand","Model","Price","Quantity","Sum"};
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }
    
    public InvoiceItem getInvoiceItemAt(int rowId){
        return items.get(rowId);
    }

    public List<InvoiceItem> getInvoiceItems() {
        return items;
    }

    public void addInvoiceItem(InvoiceItem item) {
        item.setNum(items.size() + 1);
        items.add(item);
    }

    public void removeInvoiceItem(int rowId) {
        items.remove(rowId);
        for (int i = rowId; i < items.size(); i++) {
            items.get(i).setNum(i+1);
        }
        fireTableRowsDeleted(items.size() - 1, items.size() - 1);
    }
}
