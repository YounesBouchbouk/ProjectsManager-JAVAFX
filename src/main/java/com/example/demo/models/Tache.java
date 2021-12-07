/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.models;

import java.util.Date;

/**
 *
 * @author hp
 */
public class Tache {
    private int id_tache;
    private String title;
    private String description;
    private Date date_debut;
    private Date date_fin;
    private String etat;

    public Tache(int id_tache, String title, String description, Date date_debut, Date date_fin, String etat) {
        this.id_tache = id_tache;
        this.title = title;
        this.description = description;
        this.date_debut = new Date();
        this.date_fin = new Date();
        this.etat = etat;
    }

    public void setId_tache(int id_tache) {
        this.id_tache = id_tache;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_tache() {
        return id_tache;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public String getEtat() {
        return etat;
    }
    
   
    
}
