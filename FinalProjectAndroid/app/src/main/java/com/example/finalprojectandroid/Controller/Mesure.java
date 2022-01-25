package com.example.finalprojectandroid.Controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Mesure implements Serializable {


    private Utilisateur utilisateur;

    private LocalDate date;

    private int id;

    private double taille,poids, pcrtMasseGraisseuse ,tCou, tPoitrine, tBrasGauche, tBrasDroit, tTaille, tVentre, tFesse, tCuisseGauche, tCuisseDroite;

    static private ArrayList<Mesure> mesures =new ArrayList<>();

    private boolean actif;

    private static int comptage =0;


    public Mesure(int id,double taille, double poids, double pcrtMasseGraisseuse ,double tCou, double tpoitrine,double  tVentre, double tBrasGauche, double tBrasDroit, double tTaille, double tFesse,double tCuisseDroite, double tCuisseGauche ){
        comptage+=1;

        this.id=id;
        this.date=LocalDate.now();

        this.actif=true;

        this.taille=taille;

        this.poids =poids;
        this.pcrtMasseGraisseuse=pcrtMasseGraisseuse;
        this.tCou =tCou;
        this.tPoitrine =tpoitrine;
        this.tBrasGauche =tBrasGauche;
        this.tBrasDroit =tBrasDroit;
        this.tTaille =tTaille;
        this.tVentre =tVentre;
        this.tFesse =tFesse;
        this.tCuisseDroite =tCuisseDroite;
        this.tCuisseGauche =tCuisseGauche;

        MainActivity.getUtilisateur().setMesure(this);

        mesures.add(this);
    }

    public Mesure(){

    }

    public double getPoids() {
        return poids;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getPcrtMasseGraisseuse() {
        return pcrtMasseGraisseuse;
    }

    public double gettCou() {
        return tCou;
    }

    public double gettPoitrine() {
        return tPoitrine;
    }

    public double gettVentre() {
        return tVentre;
    }

    public double gettTaille() {
        return tTaille;
    }

    public double gettBrasGauche() {
        return tBrasGauche;
    }

    public double gettBrasDroit() {
        return tBrasDroit;
    }

    public double gettFesse() {
        return tFesse;
    }

    public double gettCuisseGauche() {
        return tCuisseGauche;
    }

    public double gettCuisseDroite() {
        return tCuisseDroite;
    }

    public boolean isActif() {
        return actif;
    }

    public static ArrayList<Mesure> getMesures() {
        return mesures;
    }

    public int getId() {
        return id;
    }

    public static void setMesures(ArrayList<Mesure> mesures) {
        Mesure.mesures = mesures;
    }
}
