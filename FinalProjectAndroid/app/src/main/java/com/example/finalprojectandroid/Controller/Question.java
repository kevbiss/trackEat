package com.example.finalprojectandroid.Controller;

import java.util.ArrayList;


public class Question {
    private static int comptage=0;

    private int idQuestion, idAdmin;
    private String question,reponse;

    private Admin admin;

    static private ArrayList<Question> questions =new ArrayList<>();



    public Question(String question , String reponse){
        comptage+=1;

        this.admin= AdminActivity.getAdmin();
        this.idAdmin=idAdmin;
        this.idQuestion=comptage;
        this.question=question;
        this.reponse=reponse;

        ajout(this);
    }


    public void ajout(Question qq){
        questions.add(qq);
    }


    public static ArrayList<Question> getQuestions() {
        return questions;
    }

    public static void setQst(ArrayList<Question> qst) {
        Question.questions = qst;
    }

    public static int getComptage() {
        return comptage;
    }

    public static void setComptage(int comptage) {
        Question.comptage = comptage;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }


}
