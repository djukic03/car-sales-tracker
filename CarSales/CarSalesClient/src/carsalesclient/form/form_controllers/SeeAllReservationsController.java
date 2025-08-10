/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.ReservationsTableForm;
import carsalesclient.form.constants.CoordinatorParamConsts;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.AddFormMode;
import carsalesclient.form.modes.TableFormMode;
import carsalesclient.form.tableModels.ReservationsTableModel;
import domain.Car;
import domain.CarStatus;
import domain.Company;
import domain.Customer;
import domain.Individual;
import domain.InvoiceItem;
import domain.Reservation;
import domain.ReservationStatus;
import domain.User;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author user
 */
public class SeeAllReservationsController {
    private final ReservationsTableForm reservationsTableForm;
    private List<Reservation> reservations;

    public SeeAllReservationsController(ReservationsTableForm reservationsTableForm) {
        this.reservationsTableForm = reservationsTableForm;
        addListeners();
    }
    
    public void openForm(){
        reservationsTableForm.setVisible(true);
    }

    private void addListeners() {
        reservationsTableForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                prepareForm();
            }
            
        });
        
        reservationsTableForm.cbStatusAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectStatus();
            }

            private void selectStatus() {
                String status = reservationsTableForm.getCbStatus().getSelectedItem().toString();
                List<Reservation> r = new ArrayList<>();
                switch (status.toLowerCase()) {
                    case "all reservations":
                        fillTable(null);
                        break;
                        
                    case "active":
                        for (Reservation reservation : reservations) {
                            if (reservation.getStatus().equals(ReservationStatus.ACTIVE)) {
                                r.add(reservation);
                            }
                        }
                        fillTable(r);
                        break;
                        
                    case "canceled":
                        for (Reservation reservation : reservations) {
                            if (reservation.getStatus().equals(ReservationStatus.CANCELED)) {
                                r.add(reservation);
                            }
                        }
                        fillTable(r);
                        break;
                        
                    case "realized":
                        for (Reservation reservation : reservations) {
                            if (reservation.getStatus().equals(ReservationStatus.REALIZED)) {
                                r.add(reservation);
                            }
                        }
                        fillTable(r);
                        break;
                    default:
                        throw new AssertionError();
                }
            }

            private void fillTable(List<Reservation> r) {
                if (r == null) {
                    setTableModel(new ReservationsTableModel(reservations));
                }
                else{
                    setTableModel(new ReservationsTableModel(r));
                }
            }
        });
        
        reservationsTableForm.btnAddNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openCarsTableForm(TableFormMode.RESERVE_CAR);
            }
        });
        
        reservationsTableForm.btnCancelAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelReservation();
            }

            private void cancelReservation() {
                try {
                    int rowId = reservationsTableForm.getTblReservations().getSelectedRow();
                    if (rowId < 0) {
                        JOptionPane.showMessageDialog(reservationsTableForm, "Please select reservation to cancel");
                        return;
                    }
                    
                    Reservation reservation =((ReservationsTableModel) reservationsTableForm.getTblReservations().getModel()).getReservationAt(rowId);
                    if (reservation.getStatus() == ReservationStatus.CANCELED) {
                        JOptionPane.showMessageDialog(reservationsTableForm, "You can't edit canceled reservation");
                        return;
                    }
                    if (reservation.getStatus() == ReservationStatus.REALIZED) {
                        JOptionPane.showMessageDialog(reservationsTableForm, "You can't edit realized reservation");
                        return;
                    }
                    
                    if (JOptionPane.showConfirmDialog(reservationsTableForm, "Are you sure you want to CANCEL following reservation:\n" + reservation.getCar().getBrand() + " " + reservation.getCar().getModel()+" for "+ ((reservation.getCustomer() instanceof Individual) ? ((Individual) reservation.getCustomer()).getFirstName(): ((Company) reservation.getCustomer()).getCompanyName()), "Cancel Reservation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        reservation.setStatus(ReservationStatus.CANCELED);
                        reservation.getCar().setStatus(CarStatus.AVAILABLE);
                        ClientController.getInstance().updateReservation(reservation);
                        JOptionPane.showMessageDialog(reservationsTableForm, "Reservation canceled successfuly!");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        
        reservationsTableForm.btnRealizeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowId = reservationsTableForm.getTblReservations().getSelectedRow();
                if (rowId < 0) {
                    JOptionPane.showMessageDialog(reservationsTableForm, "Please select reservation to realize");
                    return;
                }

                Reservation reservation =((ReservationsTableModel) reservationsTableForm.getTblReservations().getModel()).getReservationAt(rowId);
                if (reservation.getStatus() == ReservationStatus.CANCELED) {
                    JOptionPane.showMessageDialog(reservationsTableForm, "You can't edit canceled reservation");
                    return;
                }
                if (reservation.getStatus() == ReservationStatus.REALIZED) {
                    JOptionPane.showMessageDialog(reservationsTableForm, "Selected reservation is already realized");
                    return;
                }
                
                List<InvoiceItem> items = new ArrayList<>(){{
                    add(new InvoiceItem(null, 0, reservation.getCar().getPrice(), reservation.getNote(), reservation.getCar()));
                }}; 
                Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_CAR, items);
                Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_CUSTOMER, reservation.getCustomer());
                Coordinator.getInstance().addParam(CoordinatorParamConsts.SELECTED_RESERVATION, reservation);
                Coordinator.getInstance().openAddInvoiceForm(AddFormMode.REALIZE_RESERVATION);
            }
        });
        
    }

    private void prepareForm() {
        try {
            reservations = ClientController.getInstance().getAllReservations();
            for (Reservation reservation : reservations) {
                if (!reservation.getStatus().equals(ReservationStatus.REALIZED) && reservation.getReservationDeadline().before(new Date())) {
                    reservation.setStatus(ReservationStatus.CANCELED);
                    reservation.getCar().setStatus(CarStatus.AVAILABLE);
                    ClientController.getInstance().updateReservation(reservation);
                }
            }
            setTableModel(new ReservationsTableModel(reservations));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }

    private void setTableModel(ReservationsTableModel reservationsTableModel) {
        reservationsTableForm.getTblReservations().setModel(reservationsTableModel);
        reservationsTableForm.getTblReservations().getColumn("Car").setCellRenderer(new ButtonCellRenderer());
        reservationsTableForm.getTblReservations().getColumn("Car").setCellEditor(new ButtonSeeCarCellEditor("See reserved car"));
        reservationsTableForm.getTblReservations().getColumn("Customer").setCellRenderer(new ButtonCellRenderer());
        reservationsTableForm.getTblReservations().getColumn("Customer").setCellEditor(new ButtonSeeCustomerCellEditor("See customer"));
    }
    
    private class ButtonCellRenderer extends JPanel implements TableCellRenderer {
        private final JButton button;

        public ButtonCellRenderer() {
            setLayout(new GridBagLayout());
            button = new JButton();
            button.setMargin(new Insets(1, 7, 1, 7));
            button.setFocusable(true);
            add(button);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            button.setText(value == null ? "" : value.toString());
            if (isSelected){
                setBackground(UIManager.getColor("Table.selectionBackground"));;
                setForeground(UIManager.getColor("Table.selectionForeground"));
            }
            else {
                setBackground(UIManager.getColor("Table.background"));
                setForeground(UIManager.getColor("Table.foreground"));
            }
            return this;
        }
    }
    
    private class ButtonSeeCarCellEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private final JButton button;
        private String label;

        public ButtonSeeCarCellEditor(String label) {
            panel = new JPanel(new GridBagLayout());
            button = new JButton(label);
            button.setMargin(new Insets(1,7,1,7));
            panel.add(button);

            button.addActionListener((ActionEvent e) -> {
                try {
                    int row = (int) button.getClientProperty("row");
                    Car car = ((ReservationsTableModel) reservationsTableForm.getTblReservations().getModel()).getReservationAt(row).getCar();
                    Coordinator.getInstance().addParam(CoordinatorParamConsts.CAR_DETAILS, car);
                    Coordinator.getInstance().openAddCarForm(AddFormMode.DETAILS_FORM);
                    fireEditingStopped();
                } catch (Exception ex) {
                    System.out.println("Error: " +ex.getMessage());
                    ex.printStackTrace();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = value.toString();
            button.setText(label);
            button.putClientProperty("row", row);
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }
    }
    
    private class ButtonSeeCustomerCellEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private final JButton button;
        private String label;

        public ButtonSeeCustomerCellEditor(String label) {
            panel = new JPanel(new GridBagLayout());
            button = new JButton(label);
            button.setMargin(new Insets(1,7,1,7));
            panel.add(button);

            button.addActionListener((ActionEvent e) -> {
                try {
                    int row = (int) button.getClientProperty("row");
                    Customer customer = ((ReservationsTableModel) reservationsTableForm.getTblReservations().getModel()).getReservationAt(row).getCustomer();
                    Coordinator.getInstance().addParam(CoordinatorParamConsts.CUSTOMER_DETAILS, customer);
                    Coordinator.getInstance().openAddCustomerForm(AddFormMode.DETAILS_FORM);
                    fireEditingStopped();
                } catch (Exception ex) {
                    System.out.println("Error: " +ex.getMessage());
                    ex.printStackTrace();
                }
                
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = value.toString();
            button.setText(label);
            button.putClientProperty("row", row);
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }
    }
}
