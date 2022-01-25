package com.example.finalprojectandroid.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectandroid.R;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    private static Admin admin;
    private MyDatabaseHelper myDBZ;
    private  ArrayList<String> ids,lastNames, names;

    private Button btn;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        txt =findViewById(R.id.admin);

        btn =findViewById(R.id.faqA);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminActivity.this,FAQActivity.class);
                startActivity(intent);
            }
        });

        ids =new ArrayList<>();
        lastNames=new ArrayList<>();
        names=new ArrayList<>();

        myDBZ=new MyDatabaseHelper(AdminActivity.this);


        //addAdmin("kevin","bissila");

        storeDataAdmin();
        initAdmin();
        Toast.makeText(this, admin.getPrenom(), Toast.LENGTH_SHORT).show();
        aff();
    }

    public static Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    private void storeDataAdmin(){
        Cursor cursor = myDBZ.readAdmin();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                ids.add(cursor.getString(0));
                names.add(cursor.getString(1));
                lastNames.add(cursor.getString(2));
            }
        }


    }

    private void aff(){
        String l = "id = " +admin.getIdAdmin() + "  NOm =" + admin.getNom() + " prenom" +admin.getPrenom();
        txt.setText(l);
    }

    private void initAdmin(){
        admin=new Admin("kevin","bissila");
        admin.setIdAdmin(Integer.valueOf(ids.get(0)));
        admin.setNom(lastNames.get(0));
        admin.setPrenom(names.get(0));

    }

    void addAdmin(String nom , String prenom){
        MyDatabaseHelper myDB = new MyDatabaseHelper(AdminActivity.this);
        myDB.addAdmin(nom,prenom);

    }

    void ajoutQuestion(){

    }


}