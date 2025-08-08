/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.tableModels;

import domain.Reservation;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class ReservationsTableModel extends AbstractTableModel {
    private List<Reservation> reservations;

    public ReservationsTableModel(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public int getRowCount() {
        return reservations.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return reservations.get(rowIndex).getDateOfReservation();
            case 1:
                return reservations.get(rowIndex).getReservationDeadline();
            case 2:
                return reservations.get(rowIndex).getStatus();
            case 3:
                if (reservations.get(rowIndex).getNote() != null) {
                    return reservations.get(rowIndex).getNote();
                }
                else
                    return "";
            case 4:
                return "See reserved car";
            case 5:
                return "See customer";
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        String columns[] = {"Reservation Date", "Reserved Until", "Reservation Status", "Note", "Car", "Customer"};
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 4 || columnIndex == 5) {
            return JPanel.class;
        }
        return String.class;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4 || columnIndex == 5;
    }
    
    public List<Reservation> getReservations(){
        return reservations;
    }
    
    public Reservation getReservationAt(int rowId){
        return reservations.get(rowId);
    }
}
