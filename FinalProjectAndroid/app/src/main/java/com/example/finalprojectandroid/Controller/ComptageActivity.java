package com.example.finalprojectandroid.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ComptageActivity extends AppCompatActivity {

    private TextView kcal_txt, kcal_rapport, glucide_txt, glucide_rapport, proteine_txt, proteine_rapport, lipides_txt, lipides_rapport;

    private ProgressBar pgbKcal, pgbGlucide, pgbProteine, pgbLipide;

    private FloatingActionButton btn;

    private Comptage comptage;
    private TextView txt;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!MainActivity.getUtilisateur().getComptages().isEmpty()){
            setContentView(R.layout.activity_comptage);
            initAttribut();
            init();
        }
        else{
            setContentView(R.layout.activity_empty_plan);
            txt = findViewById(R.id.emptyPlantxt);
            btn1 = findViewById(R.id.emptyPlanButon);

            txt.setText("Vous n'avez pas de plan d'Alimentation en cours, \n " +
                    "Appuyer sur le bouton si vous souhaiter en créer un");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(ComptageActivity.this, CreationPlanActivity.class);
                    startActivity(intent);

                }
            });
        }


    }

    void initAttribut(){
        comptage=MainActivity.getUtilisateur().getComptage();

       // myDB = new MyDatabaseHelper(ComptageActivity.this);

        kcal_txt =findViewById(R.id.txtKcal);
        kcal_rapport=findViewById(R.id.pgb_txt_Kcal);

        glucide_txt =findViewById(R.id.txtGlucide);
        glucide_rapport=findViewById(R.id.pgb_txt_glucide);

        proteine_txt=findViewById(R.id.txtProteine);
        proteine_rapport=findViewById(R.id.pgb_txt_proteine);

        lipides_txt=findViewById(R.id.txtLipide);
        lipides_rapport=findViewById(R.id.pgb_txt_lipide);

        pgbKcal=findViewById(R.id.pgbKcal);
        pgbGlucide=findViewById(R.id.pgbGlucide);
        pgbProteine=findViewById(R.id.pgbroteine);
        pgbLipide=findViewById(R.id.pgbLipide);

        btn=findViewById(R.id.btnaddalim);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComptageActivity.this,TestAPIActivity.class);
                startActivity(intent);
            }
        });

    }

    void init(){
        DecimalFormat df = new DecimalFormat("0");

        kcal_rapport.setText("kcal MAX= "+ df.format(MainActivity.getUtilisateur().getPlanAlim().getObjectif() )+" Consommation de Kcal en cours: " + df.format(comptage.getKcal()));
        glucide_rapport.setText("glucide MAX = "+ df.format(MainActivity.getUtilisateur().getPlanAlim().getGlucides()[1]) + " Consommation de Glucides en cours: " + df.format(comptage.getGlucide()));
        proteine_rapport.setText("proteine  MAX= "+ df.format(MainActivity.getUtilisateur().getPlanAlim().getProteines()[1]) + " Consommation de Proteinees en cours: " + df.format(comptage.getProteine()));
        lipides_rapport.setText("lipides MAX = "+ df.format(MainActivity.getUtilisateur().getPlanAlim().getLipides()[1]) + "Consommation de Lipides en cours: "+df.format(comptage.getLipide()));

      pgbKcal();
      pgbGlucide();
      pgbProteine();
      pgbLipide();

    }

    private void affichage(){
        DecimalFormat df = new DecimalFormat("0");

        kcal_rapport.setText("kcal MAX= "+ df.format(MainActivity.getUtilisateur().getPlanAlim().getObjectif() )+" Consommation de Kcal en cours: " + df.format(comptage.getKcal()));
        glucide_rapport.setText("glucide MAX = "+ df.format(MainActivity.getUtilisateur().getPlanAlim().getGlucides()[1]) + " Consommation de Glucides en cours: " + df.format(comptage.getGlucide()));
        proteine_rapport.setText("proteine  MAX= "+ df.format(MainActivity.getUtilisateur().getPlanAlim().getProteines()[1]) + " Consommation de Proteinees en cours: " + df.format(comptage.getProteine()));
        lipides_rapport.setText("lipides MAX = "+ df.format(MainActivity.getUtilisateur().getPlanAlim().getLipides()[1]) + "Consommation de Lipides en cours: "+df.format(comptage.getLipide()));

    }

    public void add(Aliment alm){
        comptage.ajoutAliment(alm);
        pgbKcal();
        pgbGlucide();
        pgbLipide();
        pgbProteine();
        comptage.check(kcal_rapport,glucide_rapport,proteine_rapport,lipides_rapport);
        affichage();

    }

    void pgbKcal(){
        pgbKcal.setProgress((int) Math.round((comptage.getKcal()/MainActivity.getUtilisateur().getPlanAlim().getObjectif())*100));
        kcal_txt.setText("pourcentage de Kcal consommée :" +pgbKcal.getProgress() +" %");
    }
    void pgbGlucide(){
        pgbGlucide.setProgress((int) Math.round((comptage.getGlucide()/MainActivity.getUtilisateur().getPlanAlim().getGlucides()[1])*100));
        glucide_txt.setText("pourcentage de Kcal consommée :" +pgbGlucide.getProgress() +" %");
    }
    void pgbProteine(){
        pgbProteine.setProgress((int) Math.round((comptage.getProteine()/MainActivity.getUtilisateur().getPlanAlim().getProteines()[1])*100));
        proteine_txt.setText("pourcentage de Kcal consommée :" +pgbProteine.getProgress() +" %");
    }
    void pgbLipide(){
        pgbLipide.setProgress((int) Math.round((comptage.getLipide()/MainActivity.getUtilisateur().getPlanAlim().getLipides()[1])*100));
        lipides_txt.setText("pourcentage de Kcal consommée :" +pgbLipide.getProgress() +" %");
    }


}