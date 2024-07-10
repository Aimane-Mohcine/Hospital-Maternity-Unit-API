package org.example;

import java.io.IOException;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTable table;

    /**
     * Constructor that sets up the GUI components.
     */
    public GUI() {
        setTitle("List of all patients");
        initializeModel();
        setupTableAndSorter();
        setupScrollPane();
        setupSearchPanel();
        setupRefreshButton(); // Adding the refresh button


        setSize(900, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    /**
     * Initializes the table model and adds data.
     */
    private void initializeModel() {
        model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("surname");
        model.addColumn("forename");

        model.addColumn("nhsNumber");

        model.addColumn("Action");

        addSampleData();
    }

    /**
     * Adds sample data to the model.
     */
    private void addSampleData() {
                try {
            List<Patient> patients = ApiClient.getPatients();
            for (Patient patient : patients) {
                model.addRow(new Object[]{patient.getId(), patient.getSurname(),patient.getForename(),patient.getNhsNumber(), "See Admissions"});

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets up the table, sorter, and custom cell renderers and editors.
     */
    private void setupTableAndSorter() {
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        table.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), table));
    }

    /**
     * Adds the table to a JScrollPane and adds it to the frame.
     */
    private void setupScrollPane() {
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Sets up the search panel above the table.
     */
    private void setupSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        searchPanel.add(new JLabel("search:"), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);

        // Document listener to filter table data based on user's search input
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                search(searchField.getText());
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                search(searchField.getText());
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                search(searchField.getText());
            }
            private void search(String str) {
                if (str.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
        });
    }

    /**
     * Button renderer for the Action column.
     */
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            return this;
        }
    }

    /**
     * Button editor for the Action column.
     */
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private final JTable table;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            button.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            button.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            button.setText(value != null ? value.toString() : "");
            isPushed = true;
            return button;
        }
        public JPanel createAdmissionViewerPanel(List<Admission> admissions) {
            JPanel mainPanel = new JPanel(new BorderLayout());

            String[] columnNames = {"num","ID", "Admission Date", "Discharge Date", "Patient ID"};

            // Create a DefaultTableModel with admission data
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            int n = 0;
            for (Admission admission : admissions) {
                n++;
                Object[] rowData = {n, admission.getId(), admission.getAdmissionDate(), admission.getDischargeDate(), admission.getPatientID()};
                model.addRow(rowData);
            }

            // Create the table with the data model
            JTable table = new JTable(model);
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setPreferredWidth(20);
            table.getColumnModel().getColumn(4).setPreferredWidth(20);

            // Add the table to a JScrollPane
            JScrollPane scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);




            return mainPanel;
        }


        public Object getCellEditorValue() {
            if (isPushed) {
                int id = Integer.parseInt(model.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0).toString());
           List<Admission> admissions=ApiClient.getAdmissionsFromAPI(id);
            JPanel p=createAdmissionViewerPanel(admissions);

                if (admissions.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "It's not admitted for this patient", "Aucune Admission", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JScrollPane scrollPane = new JScrollPane(p);
                    scrollPane.setPreferredSize(new Dimension(800, 400)); // Set the desired size

                    Object[] options = {"close"};
                    JOptionPane.showOptionDialog(null, scrollPane, "Admissions", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                }


                // Return the value of the edited cell (in this case, the button text)
                        }
            isPushed = false;
            return button.getText();
        }
    }

    private void setupRefreshButton() {
        // Setting up the refresh button at the bottom of the table
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshData(); // Method to refresh data
            }
        });
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshData() {
        // Method to refresh data by reloading the list of patients
        model.setRowCount(0); // Clear the current data from the model
        addSampleData(); // Add the patient data again
    }

    public static void main(String[] args) {



        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}