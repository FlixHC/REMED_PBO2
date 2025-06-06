/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kopi;

/**
 *
 * @author User
 */
public class Sale {
    private String name;
    private double harga;
    private int quant;
    
    public Sale(String name, double harga, int quant){
        this.name = name;
        this.harga = harga;
        this.quant = quant;
    }
    
    public String getName() { return name; }
    public void setName(String name) {this.name = name; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) {this.harga = harga; }
    public int getQuant() { return quant; }
    public void setQuant(int quant) {this.quant = quant; }
}
