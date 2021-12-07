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
public class Membre extends Utilisateur implements Methodes {

    public Membre(int id_user, int full_name, String email, String password, String gende, String type, boolean is_admin) {
        super(id_user, full_name, email, password, gende, type, is_admin);
    }
    
    
}
