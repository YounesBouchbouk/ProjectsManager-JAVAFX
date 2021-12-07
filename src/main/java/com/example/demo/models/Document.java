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
public class Document {
    private int id_document;
     private int document_name;
      private int id_tache;
       private int id_projet;

    public Document(int id_document, int document_name, int id_tache, int id_projet) {
        this.id_document = id_document;
        this.document_name = document_name;
        this.id_tache = id_tache;
        this.id_projet = id_projet;
    }

    public void setId_document(int id_document) {
        this.id_document = id_document;
    }

    public void setDocument_name(int document_name) {
        this.document_name = document_name;
    }

    public void setId_tache(int id_tache) {
        this.id_tache = id_tache;
    }

    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }

    public int getId_document() {
        return id_document;
    }

    public int getDocument_name() {
        return document_name;
    }

    public int getId_tache() {
        return id_tache;
    }

    public int getId_projet() {
        return id_projet;
    }
       
}
