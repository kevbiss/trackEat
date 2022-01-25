package com.example.finalprojectandroid.Controller;

public class InfoPerso {

    private double taille,objectifPoids,objectifTauxDeGraisse,multiplicateurActivivte;
    private Utilisateur utilisateur;

    public InfoPerso(Utilisateur utilisateur, double taille,double multiplicateurActivivte, double poids ,double tauxDeGraisse){
        this.taille=taille;
        this.utilisateur=utilisateur;
        this.objectifPoids=poids;
        this.objectifTauxDeGraisse=tauxDeGraisse;
        this.multiplicateurActivivte=multiplicateurActivivte;

        this.utilisateur.setInfoPerso(this);
    }

    public InfoPerso(){

    }




    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }




    public double getOPoids() {
        return objectifPoids;
    }

    public void setPoids(double poids) {
        this.objectifPoids = poids;
    }

    public double getOTauxDeGraisse() {
        return objectifTauxDeGraisse;
    }

    public void setTauxDeGraisse(double tauxDeGraisse) {
        this.objectifTauxDeGraisse = tauxDeGraisse;
    }

    public double getMultiplicateurActivivte() {
        return multiplicateurActivivte;
    }

    public void setMultiplicateurActivivte(double multiplicateurActivivte) {
        this.multiplicateurActivivte = multiplicateurActivivte;
    }
}
