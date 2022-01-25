package com.example.finalprojectandroid.Controller;

import java.util.ArrayList;


public class FAQ {

    private ArrayList<Question> faq = new ArrayList<>();

    public FAQ(){
        initFaq();
    }


    public void initFaq(){
        for (int k=0; k< Question.getComptage();k++){
            faq.add(Question.getQuestions().get(k));
        }
    }

    public void afficherFaq(){
        System.out.println( "Bienvenue dans  la FAQ \t voici les questions les plus souvent posées \n");

        for (Question k:faq){
            System.out.println("Voici la question : "+k.getQuestion());
            System.out.println("Voici la reponse : "+k.getReponse() +"\n");
        }
    }

    public int taille(){
        return Question.getComptage();
    }

    public ArrayList<Question> getFaq() {
        return faq;
    }

    public void setFaq(ArrayList<Question> faq) {
        this.faq = faq;
    }
}
