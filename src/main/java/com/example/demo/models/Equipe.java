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
public class Equipe {
    private int id_team;
    private String role;
    private String statut;
    private int projext_id;
    private int user_id;

    public Equipe(int id_team, String role, String statut, int projext_id, int user_id) {
        this.id_team = id_team;
        this.role = role;
        this.statut = statut;
        this.projext_id = projext_id;
        this.user_id = user_id;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setProjext_id(int projext_id) {
        this.projext_id = projext_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId_team() {
        return id_team;
    }

    public String getRole() {
        return role;
    }

    public String getStatut() {
        return statut;
    }

    public int getProjext_id() {
        return projext_id;
    }

    public int getUser_id() {
        return user_id;
    }
    
    
    
}
