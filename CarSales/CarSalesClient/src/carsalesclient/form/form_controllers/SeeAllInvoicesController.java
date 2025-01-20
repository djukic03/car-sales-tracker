/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.form_controllers;

import carsalesclient.controller.ClientController;
import carsalesclient.form.InvoicesTableForm;
import carsalesclient.form.constants.CoordinatorParamConsts;
import carsalesclient.form.form_coordinator.Coordinator;
import carsalesclient.form.modes.AddFormMode;
import carsalesclient.form.tableModels.InvoicesTableModel;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import domain.Customer;
import domain.Invoice;
import domain.InvoiceItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author user
 */
public class SeeAllInvoicesController {
    private final InvoicesTableForm invoicesTableForm;

    public SeeAllInvoicesController(InvoicesTableForm invoicesTableForm) {
        this.invoicesTableForm = invoicesTableForm;
        addListeners();
    }
    
    public void openForm(){
        prepareForm();
        invoicesTableForm.setVisible(true);
    }
    
    private void addListeners(){
        invoicesTableForm.getDatePicker().addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent dce) {
                try {
                    String d = invoicesTableForm.getDatePicker().getDateStringOrEmptyString();
                    invoicesTableForm.getTxtDate().setText(d);
                    Invoice invoice = new Invoice();
                    invoice.setSearchCondition("date_of_issue");
                    invoice.setSearchConditionValue(d);
                    List<Invoice> invoices = ClientController.getInstance().searchInvoices(invoice);
                    if (invoices.isEmpty()) {
                        invoicesTableForm.getTblInvoices().setModel(new InvoicesTableModel(new ArrayList<>()));
                    }
                    else{
                        setTableModel(new InvoicesTableModel(invoices));
                    }
                    
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
                
            }
        });
    }

    private void prepareForm() {
        try {
            DatePickerSettings settings = invoicesTableForm.getDatePicker().getSettings();
            settings.setColor(DatePickerSettings.DateArea.DatePickerTextDisabled, Color.YELLOW);
            settings.setDateRangeLimits(LocalDate.MIN, LocalDate.now());
            settings.setFormatForDatesCommonEra("dd.MM.yyyy");
            settings.setVisibleDateTextField(false);
            
            List<Invoice> invoices = ClientController.getInstance().getAllInvoices();
            setTableModel(new InvoicesTableModel(invoices));
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void setTableModel(InvoicesTableModel model){
        invoicesTableForm.getTblInvoices().setModel(model);
        invoicesTableForm.getTblInvoices().getColumn("Items").setCellRenderer(new ButtonCellRenderer());
        invoicesTableForm.getTblInvoices().getColumn("Items").setCellEditor(new ButtonSeeItemsCellEditor("See Items"));
        invoicesTableForm.getTblInvoices().getColumn("Salesperson").setCellRenderer(new ButtonCellRenderer());
        invoicesTableForm.getTblInvoices().getColumn("Salesperson").setCellEditor(new ButtonSeeSalespersonCellEditor("See salesperson"));
        invoicesTableForm.getTblInvoices().getColumn("Customer").setCellRenderer(new ButtonCellRenderer());
        invoicesTableForm.getTblInvoices().getColumn("Customer").setCellEditor(new ButtonSeeCustomerCellEditor("See customer"));
    }

    
    
    
    
    private class ButtonCellRenderer extends JPanel implements TableCellRenderer {
        private final JButton button;

        public ButtonCellRenderer() {
            setLayout(new GridBagLayout());
            button = new JButton();
            button.setMargin(new Insets(1, 7, 1, 7));
            add(button);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            button.setText(value == null ? "" : value.toString());
            return this;
        }
    }
    
    private class ButtonSeeItemsCellEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private final JButton button;
        private String label;

        public ButtonSeeItemsCellEditor(String label) {
            panel = new JPanel(new GridBagLayout());
            button = new JButton(label);
            button.setMargin(new Insets(1,7,1,7));
            panel.add(button);

            button.addActionListener((ActionEvent e) -> {
                try {
                    int row = (int) button.getClientProperty("row");
                    Invoice invoice = ((InvoicesTableModel) invoicesTableForm.getTblInvoices().getModel()).getInvoiceAt(row);
                    InvoiceItem item = new InvoiceItem();
                    item.setSearchCondition("invoice_id");
                    item.setSearchConditionValue(invoice.getIdInvoice().toString());
                    List<InvoiceItem> items = ClientController.getInstance().searchInvoiceItems(item);
                    Coordinator.getInstance().openInvoiceItemsTableForm(items);
                    fireEditingStopped();
                } catch (Exception ex) {
                    System.out.println("Error: "+ ex.getMessage());
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
    
    private class ButtonSeeSalespersonCellEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private final JButton button;
        private String label;

        public ButtonSeeSalespersonCellEditor(String label) {
            panel = new JPanel(new GridBagLayout());
            button = new JButton(label);
            button.setMargin(new Insets(1,7,1,7));
            panel.add(button);

            button.addActionListener((ActionEvent e) -> {
                System.out.println("Button clicked in row: " + button.getClientProperty("row"));
                fireEditingStopped();
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
                    Customer customer = ((InvoicesTableModel) invoicesTableForm.getTblInvoices().getModel()).getInvoiceAt(row).getCustomer();
                    customer.setSearchCondition("id");
                    customer.setSearchConditionValue(customer.getIdCustomer().toString());
                    customer = ClientController.getInstance().searchCustomers(customer).getFirst();
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
