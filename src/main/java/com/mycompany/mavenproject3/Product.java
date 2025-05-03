/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3;

/**
 *
 * @author ASUS
 */
public class Product {
    private long id;
    private String code;
    private String name;
    private String category;
    private double price;
    private long stock;

    public Product(long id, String code, String name, String category, double price, int stock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) {this.code = code; }
    public String getName() { return name; }
    public void setName(String name) {this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) {this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) {this.price = price; }
    public long getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
