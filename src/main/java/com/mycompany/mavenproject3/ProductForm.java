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

public class ProductForm extends JFrame {
    private JTable drinkTable;
    private DefaultTableModel tableModel;
    private JTextField codeField;
    private JTextField nameField;
    private JComboBox<String> categoryField;
    private JTextField priceField;
    private JTextField stockField;
    private JButton addButton;
    private JButton removeButton;
    private JButton editButton;
    private int idCounter = 3;
    private SaleForm saleForm;
    private CustomerForm customerForm;
    private MemberList memberList;


    private List<Product> products;
        
    public ProductForm(List<Product> products, Kopi mainWindow) {
        this.products = products;
        
        priceField = new JTextField(6);
        codeField = new JTextField(6);
        nameField = new JTextField(10);
        categoryField = new JComboBox<>(new String[]{"Coffee", "Dairy", "Juice", "Soda", "Tea"});
        stockField = new JTextField(5);
        addButton = new JButton("Tambah");
        removeButton = new JButton("Hapus");
        editButton = new JButton("Edit");
        tableModel = new DefaultTableModel(new String[]{"Kode", "Nama", "Kategori", "Harga Jual", "Stok"}, 0);
        drinkTable = new JTable(tableModel);
        
        drinkTable.getSelectionModel().addListSelectionListener(event -> { //Membaca ketika list dipilih
            int selectedRow = drinkTable.getSelectedRow();

            if (selectedRow != -1) {
            String selectedName = drinkTable.getValueAt(selectedRow, 1).toString();
            String selectedCode = drinkTable.getValueAt(selectedRow, 0).toString();

            String selectedPrice = drinkTable.getValueAt(selectedRow, 3).toString();
            String selectedStock = drinkTable.getValueAt(selectedRow, 4).toString();
            
            codeField.setText(selectedCode);      
            nameField.setText(selectedName);
            priceField.setText(selectedPrice);
            stockField.setText(selectedStock);
    }
});     
        
        addButton.addActionListener (e ->{

            //Try catch untuk mencegah misinput pada harga
            try{
                
                String code = codeField.getText();
                String name = nameField.getText();
                String category = categoryField.getSelectedItem().toString();
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());
              
                Product product = new Product(idCounter++, code, name, category, price, stock);
                products.add(product);
                tableModel.addRow(new Object[]{code, name, category, price, stock});
                codeField.setText("");
                nameField.setText("");
                priceField.setText("");
                stockField.setText("");
                
                mainWindow.updateBannerText(); 
                saleForm.refreshItem();
                }

                //exception handling ketika harga bukan angka
                catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(drinkTable, "Harga dan Stok harus berupa angka!");
                }
            });
        
        removeButton.addActionListener (e ->{
            int selectedRow = drinkTable.getSelectedRow();

            if (selectedRow != -1){
                products.remove(selectedRow);
                mainWindow.updateBannerText(); 
                saleForm.refreshItem();
                tableModel.removeRow(selectedRow);
                codeField.setText("");
                nameField.setText("");
                categoryField.setSelectedIndex(0);
                priceField.setText("");
                stockField.setText("");
            }else {
                JOptionPane.showMessageDialog(drinkTable, "tidak ada yang dipilih");
            }
        });
        
        editButton.addActionListener(e -> {
            int selectedRow = drinkTable.getSelectedRow(); 

            if (selectedRow != -1) {
            String newCode = codeField.getText();
            String newName = nameField.getText();
            String newCategory = categoryField.getSelectedItem().toString();
            double newPrice;
            int newStock = Integer.parseInt(stockField.getText());

                        

            try {
                newPrice = Double.parseDouble(priceField.getText()); // Mencegah error ketika harga bukan berupa angka
            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(drinkTable, "Harga harus berupa angka!");
            return;
        }
            

        // Update data di bagian ArrayList dan menggunakan get set di class product
        products.get(selectedRow).setCode(newCode);
        products.get(selectedRow).setName(newName);
        products.get(selectedRow).setCategory(newCategory);
        products.get(selectedRow).setPrice(newPrice);
        products.get(selectedRow).setStock(newStock);

        // Update data di bagian Table Model 
        drinkTable.setValueAt(newCode, selectedRow, 0);
        drinkTable.setValueAt(newName, selectedRow, 1);
        drinkTable.setValueAt(newCategory, selectedRow, 2);
        drinkTable.setValueAt(newPrice, selectedRow, 3);
        drinkTable.setValueAt(newStock, selectedRow, 4);
 
        // Clear input pasca edit
        codeField.setText("");
        nameField.setText("");
        categoryField.setSelectedIndex(0);
        priceField.setText("");
        stockField.setText("");
        
        mainWindow.updateBannerText(); 
        saleForm.refreshItem();


        } else {
            JOptionPane.showMessageDialog(drinkTable, "Pilih produk yang ingin diubah!");
        }
        });
    
        setTitle("Produk");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Kode Barang"));
        formPanel.add(codeField);       
        formPanel.add(new JLabel("Nama Barang:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Kategori:"));
        formPanel.add(categoryField);
        formPanel.add(new JLabel("Harga Jual:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Stok Tersedia:"));        
        formPanel.add(stockField);
                
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
       
        loadProductData();
        
        add (new JScrollPane(drinkTable), BorderLayout.CENTER);
        add (formPanel,  BorderLayout.SOUTH);
        add (buttonPanel, BorderLayout.AFTER_LINE_ENDS);
        setVisible (true);
    }

    private void loadProductData() {
        for (Product a : products) {
            tableModel.addRow(new Object[]{
                a.getCode(), a.getName(), a.getCategory(), a.getPrice(), a.getStock()
            });
        }
    }
    
    public void setSaleForm(SaleForm saleForm){
        this.saleForm = saleForm;
    }
    
    public void setMemberList (MemberList memberList){
        this.memberList = memberList;
    }
    
    public void setCustomerForm(CustomerForm customerForm){
        this.customerForm = customerForm;
    }

    public void refreshStock() {
    tableModel.setRowCount(0);
    
    
    for (Product product : products) {
        tableModel.addRow(new Object[] {
            product.getCode(),
            product.getName(),
            product.getCategory(),
            product.getPrice(),
            product.getStock()
        });
    }
    
    
}
    
}
