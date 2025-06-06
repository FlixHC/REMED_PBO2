/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kopi;

/**
 *
 * @author User
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class MemberList extends JFrame {
    private JTable memberTable;
    private DefaultTableModel tableModel;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField numberField;
    private JButton saveButton;
    private JButton removeButton;
    private JButton editButton;
    private int idCounter = 0; 

    private List<Product> products;
    private List<Member> members;
    private Kopi mainWindow; //for main
    private SaleForm saleForm;
    private MemberList memberList;
    private ProductForm productForm;
    private CustomerForm customerForm;

    public MemberList(List<Product> products, List<Member> members, Kopi mainWindow) {
        this.products = products;
        this.members = members;
        this.mainWindow = mainWindow;

        setTitle("Member");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Nama", "Email", "Nomor HP"};
        tableModel = new DefaultTableModel(columnNames, 0);
        memberTable = new JTable(tableModel);

        numberField = new JTextField(10);
        emailField = new JTextField(10);
        nameField = new JTextField(10);
        saveButton = new JButton("Simpan");
        removeButton = new JButton("Hapus");
        editButton = new JButton("Edit");
        
        JPanel formPanel = new JPanel();

        formPanel.add(new JLabel("Nama Customer: "));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email Customer"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("No.Telp :"));
        formPanel.add(numberField);
        formPanel.add(saveButton);
        formPanel.add(removeButton);
        formPanel.add(editButton);
        
        
        

        // Table selection listener
        memberTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = memberTable.getSelectedRow();
            if (selectedRow != -1) {
                
                nameField.setText(memberTable.getValueAt(selectedRow, 0).toString());
                emailField.setText(memberTable.getValueAt(selectedRow, 1).toString());
                numberField.setText(memberTable.getValueAt(selectedRow, 2).toString());
            }
        });

        // Add product
        saveButton.addActionListener(e -> {
            try {
                String number= numberField.getText();
                String email = emailField.getText();
                String name = nameField.getText();
                

                Member member = new Member(idCounter++, name, email, number);
                members.add(member);
                tableModel.addRow(new Object[]{idCounter++, name, email, number});
                nameField.setText("");
                emailField.setText("");
                numberField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "No.Telp harus berupa angka!");
            }
        });

        // Remove product
        removeButton.addActionListener(e -> {
            int selectedRow = memberTable.getSelectedRow();
            if (selectedRow != -1) {
                members.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                nameField.setText("");
                numberField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Pilih yang ingin dihapus!");
            }
        });

        // Edit product
        editButton.addActionListener(e -> {
            int selectedRow = memberTable.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    String newNumber = numberField.getText();
                    String newEmail = emailField.getText();
                    String newName = nameField.getText();

                    Member selectedProduct = members.get(selectedRow);
                    selectedProduct.setNumber(newNumber);
                    selectedProduct.setEmail(newEmail);
                    selectedProduct.setName(newName);

                    // Update table row
                    tableModel.setValueAt(newNumber, selectedRow, 2);
                    tableModel.setValueAt(newEmail, selectedRow, 1);
                    tableModel.setValueAt(newName, selectedRow, 0);


                    numberField.setText("");
                    emailField.setText("");
                    nameField.setText("");
                    
                } catch (Exception ex) {}
        }});

        

        add(new JScrollPane(memberTable), BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);

        loadProductData(); // Load products into table at start
    }
    

    public void setProductForm(ProductForm productForm){
        this.productForm = productForm;
    }

    public void setCustomerForm(CustomerForm customerForm){
        this.customerForm = customerForm;
    }
    
    public void setSaleForm(SaleForm saleForm){
        this.saleForm = saleForm;
    }
    private void loadProductData() {
        for (Member c : members) {
            tableModel.addRow(new Object[]{
                c.getId(), c.getName(), c.getEmail(), c.getNumber()
            });
        }
    }
}