package com.example.finalprojectandroid.Controller;

import java.text.DecimalFormat;

public class Aliment {
    private  double quantite,kcal,proteine,lipide,glucide;
    private String name;


    public Aliment(String name, double quantite, double kcal, double proteine, double lipide, double glucide){
        this.name=name;
        this.quantite=quantite;
        this.kcal=kcal;
        this.proteine=proteine;
        this.lipide=lipide;
        this.glucide=glucide;
    }

    public String aff(){
        DecimalFormat df = new DecimalFormat("0");
        String aff=" Voici le nom de l'aliment " + this.getName()
                +"\nNombre de calories :"+ df.format(this.getKcal())
                + "\nGlucides : "+ df.format(this.getGlucide())
                + "\nProteines : "+ df.format(this.getProteine())
                + "\nLipides : "+ df.format(this.getLipide());
        return aff;
    }

    public String getName() {
        return name;
    }

    public double getKcal() {
        return kcal;
    }

    public double getProteine() {
        return proteine;
    }

    public double getLipide() {
        return lipide;
    }

    public double getGlucide() {
        return glucide;
    }
}
