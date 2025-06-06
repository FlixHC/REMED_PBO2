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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.util.List;

public class CustomerForm extends JFrame {
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JTextField priceField;
    private JTextField customField;
    private JTextField quantField;
    private JTextField totalField;
    private JComboBox<String> nameField;
    private JButton saleButton;
    private ProductForm productForm;
    private List<Product> products;
    private List<Customer> customer;
    private Kopi mainWindow;
    private int idCounter = 0;
    private CustomerForm customerForm;
    private SaleForm saleForm;
    private MemberList memberList;
        
    public CustomerForm(List<Product> products, List<Customer> customer, Kopi mainWindow) {
        this.customerForm = customerForm;
        this.products = products;
        this.mainWindow = mainWindow;
        this.productForm = productForm;

        priceField = new JTextField(6);
        nameField = new JComboBox<>();
        for (Product p : products){
            nameField.addItem(p.getName());
        }
        quantField = new JTextField(5);
        totalField = new JTextField(6);
        saleButton = new JButton("Tambah Penjualan");
        tableModel = new DefaultTableModel(new String[]{"Nama", "Pesanan", "Harga Jual", "Jumlah", "Total"}, 0);
        customerTable = new JTable(tableModel);
        customField = new JTextField(5);
        
        customerTable.getSelectionModel().addListSelectionListener(event -> { //Membaca ketika list dipilih
            int selectedRow = customerTable.getSelectedRow();

            if (selectedRow != -1) {
            String selectedNama = customerTable.getValueAt(selectedRow, 0).toString();
            String selectedHarga = customerTable.getValueAt(selectedRow, 2).toString();
            String selectedQuant = customerTable.getValueAt(selectedRow, 3).toString();
   
            customField.setText(selectedNama);
            priceField.setText(selectedHarga);
            quantField.setText(selectedQuant);
    }
});     
        
        saleButton.addActionListener (e ->{

            //Try catch untuk mencegah misinput pada harga
            try{
                String nama = customField.getText();
                String name = nameField.getSelectedItem().toString();
                double harga = Double.parseDouble(priceField.getText());
                int quant = Integer.parseInt(quantField.getText());
                double total = quant * harga;
              
                Product matchedProduct = null;
                for (Product p : products) {
                    if (p.getName().equals(name)) {
                        matchedProduct = p;
                        break;
                    }
                }
                

                if (matchedProduct == null) {
                JOptionPane.showMessageDialog(customerTable, "Produk tidak ditemukan!");
                return;
                }

                if (quant > matchedProduct.getStock()) {
                JOptionPane.showMessageDialog(customerTable, "Stok tidak mencukupi!");
                return;
                }
                matchedProduct.setStock((int) (matchedProduct.getStock() - quant));

                Customer customers = new Customer(idCounter++, nama, name, quant, total);
                customer.add(customers);
                tableModel.addRow(new Object[]{nama, name, harga, quant, total});

                priceField.setText("");
                quantField.setText("");
                customField.setText("");
                

                productForm.refreshStock();

                mainWindow.updateBannerText();

                } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(customerTable, "Harga dan Jumlah harus berupa angka!");
            }
        });
        
        
        setTitle("Pemesanan");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Nama Customer"));
        formPanel.add(customField);
        formPanel.add(new JLabel("Nama Barang"));
        formPanel.add(nameField);       
        formPanel.add(new JLabel("Harga:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Jumlah:"));        
        formPanel.add(quantField);
        formPanel.add(saleButton);
               
        add (new JScrollPane(customerTable), BorderLayout.CENTER);
        add (formPanel,  BorderLayout.SOUTH);
        setVisible (true);
    
    }
    
    public void refreshItem(){
        nameField.removeAllItems();
            
        for (Product p : products){
                nameField.addItem(p.getName());
            }
        }
    
    public void setCustomerForm(CustomerForm customerForm){
        this.customerForm = customerForm;
    }
    
    public void setSaleForm(SaleForm saleForm){
        this.saleForm = saleForm;
    }
    
    public void setProductForm(ProductForm productForm){
        this.productForm = productForm;
    }
    
    public void setMemberList (MemberList memberList){
        this.memberList = memberList;
    }
}
