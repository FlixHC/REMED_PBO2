/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kopi;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Kopi extends JFrame implements Runnable {
    private List<Product> products; 
    private List<Sale> sale;
    private List<Customer> customer;
    private List<Member> member;
    private String bannerText = ""; 
    private int x; 
    private int width;
    private BannerPanel bannerPanel; 
    private JButton addProductButton;
    private JButton sellProductButton;
    private JButton customerProductButton;
    private JButton memberListButton;
    private ProductForm productForm;
    private SaleForm saleForm;
    private CustomerForm customerForm;
    private MemberList memberList;

    public Kopi(String text) {
        
        setTitle("Kopi Cina");
        setSize(600, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Panel teks berjalan
        bannerPanel = new BannerPanel();
        add(bannerPanel, BorderLayout.CENTER);
        
        products = new ArrayList<>();
        products.add(new Product(1, "P001", "Americano", "Coffee", 18000, 10));
        products.add(new Product(2, "P002", "Pandan Latte", "Coffee", 15000, 8));
        updateBannerText();
        
        sale = new ArrayList<>();
        customer = new ArrayList<>();
        member = new ArrayList<>();
        

        // Tombol "Kelola Produk"
        JPanel bottomPanel = new JPanel();
        addProductButton = new JButton("Kelola Produk");
        sellProductButton = new JButton("Penjualan");
        customerProductButton = new JButton("Pemesanan");
        memberListButton = new JButton ("Pelanggan");
        
        bottomPanel.add(addProductButton);
        bottomPanel.add(sellProductButton);
        bottomPanel.add(customerProductButton);
        bottomPanel.add(memberListButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        saleForm = new SaleForm(products, sale, this);
        productForm = new ProductForm (products, this);
        customerForm = new CustomerForm (products, customer, this);
        memberList = new MemberList (products, member, this);
        
        saleForm.setProductForm(productForm);
        productForm.setSaleForm(saleForm);
        productForm.setCustomerForm(customerForm);
        productForm.setMemberList(memberList);
        customerForm.setMemberList(memberList);
        customerForm.setProductForm(productForm);
        memberList.setProductForm(productForm);
        
        
        addProductButton.addActionListener(e -> {
            if (productForm == null) {
            productForm = new ProductForm(products, this);
            }
            productForm.setVisible(true);
        });
        
        sellProductButton.addActionListener(e -> {
            if (productForm == null) {
            productForm = new ProductForm(products, this);
            }

            if (saleForm == null) {
            saleForm = new SaleForm(products, sale, this);
            }
            saleForm.setVisible(true);
            
            if(memberList == null){
                memberList = new MemberList (products, member, this);
            }
        });
        
        customerProductButton.addActionListener(e -> {
            if (productForm == null) {
            productForm = new ProductForm(products, this);
            }

           if (customerForm == null) {
            customerForm = new CustomerForm(products, customer, this);
            }
           
           if(memberList == null){
                memberList = new MemberList (products, member, this);
            }
           
            customerForm.setVisible(true);
        });
        
        memberListButton.addActionListener(e -> {
            if (productForm == null){
                productForm = new ProductForm(products, this);
            }
            if (saleForm == null){
                saleForm = new SaleForm(products, sale, this);
            }
            if(memberList == null){
                memberList = new MemberList (products, member, this);
            }
            memberList.setVisible(true);
        });
        
       
        setVisible(true);

        Thread thread = new Thread(this);
        thread.start();
    }

    class BannerPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(bannerText, x, getHeight() / 2);
        }
    }

    @Override
    public void run() {
        width = getWidth();
        while (true) {
            x += 5;
            if (x > width) {
                x = -getFontMetrics(new Font("Arial", Font.BOLD, 18)).stringWidth(bannerText);
            }
            bannerPanel.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    
    public void updateBannerText() {
        StringBuilder sb = new StringBuilder("Menu yang tersedia: ");
        for (Product a : products) {

            if (a.getStock()>0){
                sb.append(a.getName()).append(" | ");
            }
        }
        bannerText = sb.toString();
        bannerPanel.repaint(); // Refresh the banner after updating the text
    }

    public static void main(String[] args) {
        new Kopi("");
    }
}