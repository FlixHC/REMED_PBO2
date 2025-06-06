/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kopi;

/**
 *
 * @author User
 */
public class Customer {
    private int id;
    private String nama;
    private String order;
    private int num;
    private double total;
    
    public Customer(int id, String nama, String order, int num, double total){
        this.id = id;
        this.nama = nama;
        this.order = order;
        this.num = num;
        this.total = total;
    }
    
    public String getNama() { return nama; }
    public void setNama(String nama){ this.nama = nama;}
    public int getId() {return id; }
    public void setId(int id){ this.id = id;}
    public String getOrder() { return order; }
    public void setOrder(String order) { this.order = order;}
    public int getNum() { return num; }
    public void setNum(int num){ this.num = num;}
    public double getTotal() {return total; }
    public void setTotal(double total){this.total = total;}
    
}
