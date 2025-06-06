/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kopi;

/**
 *
 * @author User
 */
public class Member {
    private int id;
    private String name;
    private String email;
    private String number;
    
    public Member(int id, String name, String email, String number){
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
    }
    
    public String getName() { return name; }
    public void setName(String nama){ this.name= name;}
    public int getId() { return id; }
    public void setId(int id){ this.id= id;}
    public String getEmail() { return email; }
    public void setEmail(String nama){ this.email = email;}
    public String getNumber() { return number; }
    public void setNumber(String number){ this.number = number;}
}
