package com.example.finalprojectandroid.Controller;

import java.util.ArrayList;

public class Admin extends Personne {

    private int idAdmin;
    private static int compteur =0;

    boolean isActive;

    private ArrayList<Question> qsts ;

    public Admin (String prenom,String nom){
        super(prenom,nom);

        compteur+=1;

        this.prenom =prenom;
        this.idAdmin=compteur;
        this.isActive=true;
        this.qsts=new ArrayList<>();
    }

    public Question creationQuestion(String question, String reponse){
        if( this.isActive) {
            Question nvquestion= new Question(question,reponse);
            return nvquestion;
        }
        else {
            System.out.println("Désolé vous n'avez pas les droits pour créer une question");
            return null;
        }
    }

    public void activateAdmin(boolean etat){

        if (etat){
            this.isActive=true;
        }
        else{
            this.isActive=false;
        }

    }





    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
}