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

public class SaleForm extends JFrame {
    private JTable saleTable;
    private DefaultTableModel tableModel;
    private JTextField priceField;
    private JTextField quantField;
    private JComboBox<String> nameField;
    private JButton saleButton;
    private CustomerForm customerForm;
    private MemberList memberList;
    private ProductForm productForm;
    private List<Product> products;
    private List<Sale> sale;
    private List<Customer> customer;
    private Kopi mainWindow;
        
    public SaleForm(List<Product> products, List<Sale> sale, Kopi mainWindow) {
        this.sale = sale;
        this.products = products;
        this.mainWindow = mainWindow;
        this.productForm = productForm;

        priceField = new JTextField(6);
        nameField = new JComboBox<>();
        for (Product p : products){
            nameField.addItem(p.getName());
        }
        quantField = new JTextField(5);
        saleButton = new JButton("Tambah Penjualan");
        tableModel = new DefaultTableModel(new String[]{"Nama", "Harga Jual", "Jumlah"}, 0);
        saleTable = new JTable(tableModel);
        
        
        saleTable.getSelectionModel().addListSelectionListener(event -> { //Membaca ketika list dipilih
            int selectedRow = saleTable.getSelectedRow();

            if (selectedRow != -1) {
            String selectedHarga = saleTable.getValueAt(selectedRow, 1).toString();
            String selectedQuant = saleTable.getValueAt(selectedRow, 2).toString();
   
            priceField.setText(selectedHarga);
            quantField.setText(selectedQuant);
            
            
    }
});     
        
        saleButton.addActionListener (e ->{

            //Try catch untuk mencegah misinput pada harga
            try{

                String name = nameField.getSelectedItem().toString();
                double harga = Double.parseDouble(priceField.getText());
                int quant = Integer.parseInt(quantField.getText());
              
                Product matchedProduct = null;
                for (Product p : products) {
                    if (p.getName().equals(name)) {
                        matchedProduct = p;
                        break;
                    }
                }
                

                if (matchedProduct == null) {
                JOptionPane.showMessageDialog(saleTable, "Produk tidak ditemukan!");
                return;
                }

                if (quant > matchedProduct.getStock()) {
                JOptionPane.showMessageDialog(saleTable, "Stok tidak mencukupi!");
                return;
                }
                matchedProduct.setStock((int) (matchedProduct.getStock() - quant));

                Sale sales = new Sale(name, harga, quant);
                sale.add(sales);
                tableModel.addRow(new Object[]{name, harga, quant});

                priceField.setText("");
                quantField.setText("");

                productForm.refreshStock();

                mainWindow.updateBannerText();

                } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(saleTable, "Harga dan Jumlah harus berupa angka!");
            }
        });
        
        

        
        setTitle("Penjualan");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Nama Barang"));
        formPanel.add(nameField);       
        formPanel.add(new JLabel("Harga:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Jumlah:"));        
        formPanel.add(quantField);
        formPanel.add(saleButton);

        add (new JScrollPane(saleTable), BorderLayout.CENTER);
        add (formPanel,  BorderLayout.SOUTH);
        setVisible (true);
    
    }
    
    public void refreshItem(){
        nameField.removeAllItems();
            
        for (Product p : products){
                nameField.addItem(p.getName());
            }
        }
    
    public void setProductForm(ProductForm productForm){
        this.productForm = productForm;
    }
    public void setCustomerForm(CustomerForm customerForm){
        this.customerForm = customerForm;
    }
    public void setMemberList (MemberList memberList){
        this.memberList = memberList;
    }
}
