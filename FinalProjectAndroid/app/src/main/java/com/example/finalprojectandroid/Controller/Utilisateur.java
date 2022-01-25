package com.example.finalprojectandroid.Controller;

import java.util.ArrayList;

public class Utilisateur extends Personne{

    private int id;

    private static int compteur=0;


    public InfoPerso infoPerso;
    private PlanAlim planAlim;
    private Comptage comptage;

    private Mesure mesure;

    private ArrayList<PlanAlim> listePlanAlim =new ArrayList<>();
    private ArrayList<Comptage> comptages =new ArrayList<>();

    private static ArrayList<Utilisateur> utilisateurs =new ArrayList<>();


    public Utilisateur( String prenom,String nom){
        super();
        compteur+=1;
        this.idPersonne=id;
        this.prenom=prenom;
        this.nom =nom;
        this.id =compteur;

        utilisateurs.add(this);
    }

    public Utilisateur(){
        super();
    }

    public InfoPerso getInfoPerso() {
        return infoPerso;
    }

    public void setInfoPerso(InfoPerso infoPerso) {
        this.infoPerso = infoPerso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlanAlim getPlanAlim() {
        return planAlim;
    }

    public void setPlanAlim(PlanAlim planAlim) {
        this.planAlim = planAlim;
    }


    public Mesure getMesure() {
        return mesure;
    }

    public void setMesure(Mesure mesure) {
        this.mesure = mesure;
    }

    public void setComptage(Comptage comptage) {
        this.comptage = comptage;
    }

    public Comptage getComptage() {
        return comptage;
    }

    public ArrayList<PlanAlim> getListePlanAlim() {
        return listePlanAlim;
    }

    public ArrayList<Comptage> getComptages() {
        return comptages;
    }
}
