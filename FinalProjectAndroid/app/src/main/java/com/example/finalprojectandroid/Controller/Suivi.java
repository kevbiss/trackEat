package com.example.finalprojectandroid.Controller;

import java.util.ArrayList;

public class Suivi {

    private int id;

    private Utilisateur utilisateur;
    private ArrayList<Mesure> suivis = new ArrayList<>();

    public void initSuivi() {
        for (int k = 0; k < Mesure.getMesures().size(); k++) {
            suivis.add(Mesure.getMesures().get(k));
        }
    }

    public Suivi(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        initSuivi();
    }
}

