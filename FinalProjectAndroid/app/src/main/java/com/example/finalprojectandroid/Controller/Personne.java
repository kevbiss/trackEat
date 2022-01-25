package com.example.finalprojectandroid.Controller;

public class Personne {

    protected int idPersonne;


    protected String email, mdp, nom,prenom;

    public Personne( String prenom,String nom){
        this.prenom=prenom;
        this.nom=nom;
    }

    public Personne() {

    }


    public String getPrenom(){
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
