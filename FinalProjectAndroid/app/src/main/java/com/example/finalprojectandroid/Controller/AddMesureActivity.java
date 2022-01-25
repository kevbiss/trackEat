package com.example.finalprojectandroid.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalprojectandroid.R;

public class AddMesureActivity extends AppCompatActivity {

    private TextView tailleCM,poids,grss,cou,poitrine,ventre,taille,fesse,brasG,brasD,cuisseG,cuisseD;
    private Button btn;

    private MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mesure);
        initElem();



    }

    void initElem(){
        myDB =new MyDatabaseHelper(AddMesureActivity.this);

        poids=findViewById(R.id.poids2);
        grss=findViewById(R.id.p100mg);
        tailleCM=findViewById(R.id.tailleCM);
        cou=findViewById(R.id.cou2);
        poitrine=findViewById(R.id.poids2);
        ventre=findViewById(R.id.ventre2);
        taille=findViewById(R.id.taille2);
        fesse=findViewById(R.id.hanche2);
        brasG=findViewById(R.id.bras_Gauche2);
        brasD=findViewById(R.id.brad_Droit2);
        cuisseG=findViewById(R.id.Cuisse_Gauche2);
        cuisseD=findViewById(R.id.Cuisse_Droit2);


        btn=findViewById(R.id.AddMesure);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMesure();
                Intent intent = new Intent(AddMesureActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });



    }

    private void addMesure(){

        myDB.addSuivitPoid(MainActivity.getUtilisateur().getId(),
                Double.valueOf(tailleCM.getText().toString()),
                Double.valueOf(poids.getText().toString()),
                Double.valueOf(grss.getText().toString()),
                Double.valueOf(cou.getText().toString()),
                Double.valueOf(poitrine.getText().toString()),
                Double.valueOf(ventre.getText().toString()),
                Double.valueOf( brasG.getText().toString()),
                Double.valueOf( brasD.getText().toString()),
                Double.valueOf(taille.getText().toString()),
                Double.valueOf( fesse.getText().toString()),
                Double.valueOf(cuisseD.getText().toString()),
                Double.valueOf(cuisseG.getText().toString()));

        MainActivity.getUtilisateur().setMesure(
                new Mesure(MainActivity.getUtilisateur().getId(),
                Double.valueOf(tailleCM.getText().toString()),
                Double.valueOf(poids.getText().toString()),
                Double.valueOf(grss.getText().toString()),
                Double.valueOf(cou.getText().toString()),
                Double.valueOf(poitrine.getText().toString()),
                Double.valueOf(ventre.getText().toString()),
                Double.valueOf( brasG.getText().toString()),
                Double.valueOf( brasD.getText().toString()),
                Double.valueOf(taille.getText().toString()),
                Double.valueOf( fesse.getText().toString()),
                Double.valueOf(cuisseD.getText().toString()),
                Double.valueOf(cuisseG.getText().toString())));
    }
}