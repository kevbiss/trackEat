package com.example.finalprojectandroid.Controller;

import android.widget.TextView;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Comptage {

    private int idComptage;
    private boolean estSuivit;
    private  double kcal,proteine,lipide,glucide;
    private  double kcalMax,proteineMax,lipideMaax,glucideMax;

    private LocalDate date;
    private LocalDateTime heureDebut, heureFin;


    private PlanAlim planAlim;
    private Utilisateur utilisateur;

    public Comptage (Utilisateur utilisateur, PlanAlim planAlim){
        this.utilisateur=utilisateur;
        this.planAlim=planAlim;
        this.kcalMax=this.utilisateur.getPlanAlim().getKcal();
        this.proteineMax=this.utilisateur.getPlanAlim().getProt();
        this.glucideMax=this.utilisateur.getPlanAlim().getGlucide();
        this.lipideMaax=this.utilisateur.getPlanAlim().getLipide();
        //utilisateur.setComptage(this);
    }

    public Comptage(int id,Double kcal,Double prot,Double glucide,Double lipide){
        this.idComptage=id;
        this.kcal=kcal;
        this.proteine=prot;
        this.glucide=glucide;
        this.lipide=lipide;
    }

    public void ajoutAliment(Aliment aliment){
        this.proteine+=aliment.getProteine();
        this.glucide +=aliment.getGlucide();
        this.lipide +=aliment.getLipide();
        this.kcal +=aliment.getKcal();
    }


    public void check(TextView txtKcal,TextView txtGlucide,TextView txtproteine,  TextView txtLipide){

        if (this.proteine > this.utilisateur.getPlanAlim().getProt()){
            txtproteine.setText("la dose de protéine journalière est dépassée");
            this.setEstSuivit(false);
        }

        else if (this.glucide > this.utilisateur.getPlanAlim().getGlucide()){
            txtGlucide.setText("la dose de glucide journalière est dépassée");
            this.setEstSuivit(false);
        }

        else if (this.lipide > this.utilisateur.getPlanAlim().getLipide()){
            txtLipide.setText("la dose de lipide journalière est dépassée");
            this.setEstSuivit(false);
        }
        else if (this.kcal > this.utilisateur.getPlanAlim().getKcal()){
            txtKcal.setText("la dose de kcal journalière est dépassée");
            this.setEstSuivit(false);
        }

    }

    String initComptage (){
        DecimalFormat df = new DecimalFormat("0");

        String lsd="Voici le programme que vous devez respecter :" +
                "kcal = "+ df.format(this.utilisateur.getPlanAlim().getKcal() )+
                " \n proteines = " + df.format(this.utilisateur.getPlanAlim().getProt()) +
                "\n glucides =" + df.format(this.utilisateur.getPlanAlim().getGlucide()) +
                "\n lipides =" + df.format(this.utilisateur.getPlanAlim().getLipide());

        System.out.println("Voici le programme l' état de votre consommation actuelle :" +
                "kcal = "+ df.format(this.kcal) +
                " \n proteines = " + df.format(this.proteine) +
                "\n glucides =" + df.format(this.glucide )+
                "\n lipides =" + df.format(this.lipide ));

        return lsd;

    }

    void setDateDebut(){
        LocalDate date =null;
        this.heureDebut =date.atStartOfDay();
    }


    void setDateFin(){
        this.heureFin = date.atStartOfDay();
    }


    public Comptage getComptage(){
        return this;
    }


    public int getIdComptage() {
        return idComptage;
    }

    public void setIdComptage(int idComptage) {
        this.idComptage = idComptage;
    }

    public double getProteine() {
        return proteine;
    }

    public void setProteine(double proteine) {
        this.proteine = proteine;
    }

    public double getLipide() {
        return lipide;
    }

    public void setLipide(double lipide) {
        this.lipide = lipide;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getGlucide() {
        return glucide;
    }

    public void setGlucide(double glucide) {
        this.glucide = glucide;
    }

    public boolean isEstSuivit() {
        return estSuivit;
    }

    public void setEstSuivit(boolean estSuivit) {
        this.estSuivit = estSuivit;
    }


}
