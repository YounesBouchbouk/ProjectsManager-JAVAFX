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
public class Project {
   private int id_project;
   private String title;
   private String description;
   private Date date_debut;
   private Date date_fin;
   private String statut;
    private String client;
    private int id_team;

    public Project(int id_project, String title, String description, Date date_debut, Date date_fin, String statut, String client, int id_team) {
        this.id_project = id_project;
        this.title = title;
        this.description = description;
        this.date_debut = new Date();
        this.date_fin = new Date();
        this.statut = statut;
        this.client = client;
        this.id_team = id_team;
        
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
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

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public int getId_project() {
        return id_project;
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

    public String getStatut() {
        return statut;
    }

    public String getClient() {
        return client;
    }

    public int getId_team() {
        return id_team;
    }
    
    
}
