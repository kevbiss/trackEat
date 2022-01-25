package com.example.finalprojectandroid.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.finalprojectandroid.R;

import java.util.ArrayList;

public class FAQActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private FAQ faq;
    private CustomAdapter customAdapter;

    private ArrayList<Question> questions;

    private MyDatabaseHelper myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        myDB=new MyDatabaseHelper(FAQActivity.this);
        questions=new ArrayList<>();

        //addQuestion("TEAM ?","JUL JUL JUL");


        faq =new FAQ();

        storeFAQ();



        customAdapter =new CustomAdapter(FAQActivity.this,faq);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FAQActivity.this));

        Toast.makeText(this, faq.taille(), Toast.LENGTH_SHORT).show();
    }

    void storeFAQ(){
        Cursor cursor = myDB.readFAQ();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                questions.add(new Question(cursor.getString(2),cursor.getString(3)));

            }
        }

        this.faq.setFaq(questions);

    }

    void addQuestion(String question , String reponse){
        MyDatabaseHelper myDB = new MyDatabaseHelper(FAQActivity.this);
        myDB.addQuestion(AdminActivity.getAdmin().getIdAdmin(),question,reponse);
    }
}
