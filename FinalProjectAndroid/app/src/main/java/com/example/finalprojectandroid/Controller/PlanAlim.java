package com.example.finalprojectandroid.Controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PlanAlim {


    private Utilisateur utilisateur;

    private int id;

    private static int compteur=0;

    private boolean isActive;
    private double kcal, prot,glucide,lipide,eau;

    private LocalDate dateDebut, dateFin;

    private ArrayList<PlanAlim> planAlims = new ArrayList<>();

    private double[] glucides,lipides,proteines ;

    // kclas[4] = [proteineG,proteineKcal,p100]


    private final static double coefProt = 1.8;
    private final static double coefLip = 0.8;


    private  static double coefSeche;
    private static double coeffPmasse;
    private  static double objectif;

    public PlanAlim(int choix,double coeff){
        compteur+=1;

        this.utilisateur =MainActivity.getUtilisateur();
        this.isActive=true;
        this.id=compteur;

        objectif1(choix,coeff);
        creationPlanAlim();

        planAlims.add(this);

        this.utilisateur.setPlanAlim(this);




    }

    void objectif1 (int choix,Double a){
        double maintenance;
        switch (choix) {
            case 0:
                this.setCoefSeche(a / 100);
                maintenance = calculMaitenance(1.50);
                this.setObjectif((1 - this.getCoefSeche()) * maintenance);
                break;

            case 1:
                this.setCoeffPmasse(a / 100);
                maintenance = calculMaitenance(1.50);
                this.setObjectif((1 + this.getCoeffPmasse()) * maintenance);
                break;
            }
    }

    double calculMaitenance(double MultiplicateurActivite){
        double maintenance = (370 +21.6 *(1-MainActivity.getUtilisateur().getMesure().getPcrtMasseGraisseuse())* MainActivity.getUtilisateur().getMesure().getPoids())*MultiplicateurActivite;
        return maintenance;

    }
    void creationPlanAlim(){
        glucides=new double[4];
        lipides=new double[4];
        proteines=new double[4];


        double proteineG, proteineKcal, lipideG, lipideKcal, glucideG, glucideKcal;

        this.setDateDebut();

        proteineG=coefProt*(1-MainActivity.getUtilisateur().getMesure().getPcrtMasseGraisseuse())*MainActivity.getUtilisateur().getMesure().getPoids();
        proteineKcal = proteineG*4;

        proteines[0]=proteineG ;
        proteines[1]=proteineKcal;
        proteines[2]=(proteineKcal/this.getObjectif())*100;

        lipideG =coefLip*(1-MainActivity.getUtilisateur().getMesure().getPcrtMasseGraisseuse())*MainActivity.getUtilisateur().getMesure().getPoids();
        lipideKcal= lipideG*9;

        lipides[0]=lipideG;
        lipides[1]=lipideKcal;
        lipides[2]=(lipideKcal/this.getObjectif())*100;

        glucideKcal=objectif-proteineKcal-lipideKcal;
        glucideG=glucideKcal/4;

        glucides[0]=glucideG;
        glucides[1]=glucideKcal;
        glucides[2]=(glucideKcal/this.getObjectif())*100;

        this.setActive(true);
        this.setGlucide(glucideG);
        this.setLipide(lipideG);
        this.setProt(proteineG);
    }

    void Pmasse(){
        double proteineG, proteineKcal,proteineP100;
        double lipideG, lipideKcal,lipideP100;
        double glucideG, glucideKcal,glucideP100;


        proteineG=coefProt*(1-MainActivity.getUtilisateur().getMesure().getPcrtMasseGraisseuse())*MainActivity.getUtilisateur().getMesure().getPoids();
        proteineKcal = proteineG*4;

        lipideG =coefLip*(1-MainActivity.getUtilisateur().getMesure().getPcrtMasseGraisseuse())*MainActivity.getUtilisateur().getMesure().getPoids();
        lipideKcal= lipideG*9;

        glucideKcal=objectif-proteineKcal-lipideKcal;
        glucideG=glucideKcal/4;



    }

    void finPlanAlim(){
        setDateFin();
        this.setActive(false);
    }


    String voirPlan(){
        DecimalFormat df = new DecimalFormat("0");
        String plan ="Voici votre plan d'alimentation : \n  proteines :"+df.format(this.prot) +" g"
                + "\n lipides =" + df.format(this.lipide) + " g"
                + "\n glucides =" +df.format(this.glucide) +" g";
        return plan;
    }

    void setDateDebut(){
        this.dateDebut =LocalDate.now();
    }

    void setDateFin(){
        this.dateFin = LocalDate.now();
    }


    public LocalDate getDateFin() {
        return dateFin;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getProt() {
        return prot;
    }

    public double getGlucide() {
        return glucide;
    }

    public void setGlucide(double glucide) {
        this.glucide = glucide;
    }

    public void setProt(double prot) {
        this.prot = prot;
    }

    public double getLipide() {
        return lipide;
    }

    public void setLipide(double lipide) {
        this.lipide = lipide;
    }

    public double getEau() {
        return eau;
    }

    public void setEau(double eau) {
        this.eau = eau;
    }

    public static double getCoefSeche() {
        return coefSeche;
    }

    public static void setCoefSeche(double coefSeche) {
        PlanAlim.coefSeche = coefSeche;
    }

    public  double getObjectif() {
        return objectif;
    }

    public static void setObjectif(double objectif) {
        PlanAlim.objectif = objectif;
    }

    public  double getCoeffPmasse() {
        return coeffPmasse;
    }

    public  void setCoeffPmasse(double coeffPmasse) {
        PlanAlim.coeffPmasse = coeffPmasse;
    }

    public double[] getGlucides() {
        return glucides;
    }

    public double[] getLipides() {
        return lipides;
    }

    public double[] getProteines() {
        return proteines;
    }
}

