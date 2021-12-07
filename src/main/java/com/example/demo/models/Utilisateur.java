/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.demo.models;
/**
 *
 * @author hp
 */
public class Utilisateur {
    private int id_user;
    private int full_name;
    private String email;
    private  String password;
    private String gende;
    private String type;
    private boolean is_admin;

    public Utilisateur(int id_user, int full_name, String email, String password, String gende, String type, boolean is_admin) {
        this.id_user = id_user;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.gende = gende;
        this.type = type;
        this.is_admin = is_admin;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setFull_name(int full_name) {
        this.full_name = full_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGende(String gende) {
        this.gende = gende;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
    
    

    public int getId_user() {
        return id_user;
    }

    public int getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGende() {
        return gende;
    }

    public String getType() {
        return type;
    }

    public boolean isIs_admin() {
        return is_admin;
    }
    
    
}
