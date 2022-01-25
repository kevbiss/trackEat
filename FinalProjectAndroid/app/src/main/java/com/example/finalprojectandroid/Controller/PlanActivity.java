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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    private PlanAlim planAlim;
    private MyDatabaseHelper myDB;

    private ArrayList<PlanAlim> planAlims;

    private TextView txt, glucideG, proteineG, lipideG, glucideKcal, proteineKcal, lipideKcal, Totalkcal, glucideP100, proteineP100, lipides100, totalG, totalp100;

    private Button btn_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new MyDatabaseHelper(PlanActivity.this);
        // StorePlanAlim();
        if (!MainActivity.getUtilisateur().getListePlanAlim().isEmpty()) {
            setContentView(R.layout.activity_plan);
            Bundle bundleObject = getIntent().getExtras();
            // myDB =new MyDatabaseHelper(PlanActivity.this);

            txt = findViewById(R.id.textView2);

            glucideG = findViewById(R.id.glucide_g_txt);
            lipideG = findViewById(R.id.lipide_g_txt);
            proteineG = findViewById(R.id.proteine_g_txt);

            glucideKcal = findViewById(R.id.glucide_kcal_txt);
            lipideKcal = findViewById(R.id.lipide_kcal_txt);
            proteineKcal = findViewById(R.id.proteine_kcal_txt);
            Totalkcal = findViewById(R.id.kcal_total_txt);

            glucideP100 = findViewById(R.id.glucide_p100_txt);
            lipides100 = findViewById(R.id.lipide_p100_txt);
            proteineP100 = findViewById(R.id.proteine_p100_txt);

            totalG = findViewById(R.id.g_total_txt);
            totalp100 = findViewById(R.id.p100total_txt);

            btn_next = findViewById(R.id.buttonswitch);

            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(PlanActivity.this, StatsActivity.class);
                    startActivity(intent);

                }
            });


            //init();
            //StoreData1();
            affichage();
        } else {
            setContentView(R.layout.activity_empty_plan);
            txt = findViewById(R.id.emptyPlantxt);
            btn_next = findViewById(R.id.emptyPlanButon);

            txt.setText("Vous n'avez pas de plan d'Alimentation en cours, \n " +
                    "Appuyer sur le bouton si vous souhaiter en créer un");

            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(PlanActivity.this, CreationPlanActivity.class);
                    startActivity(intent);

                }
            });

        }

    }

    private void StorePlanAlim() {

        Cursor cursor = myDB.readPlanAlimUtilisateur(MainActivity.getUtilisateur().getId());
        planAlims = new ArrayList<>();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {
                planAlims.add(new PlanAlim(Integer.valueOf(cursor.getString(2)), Double.valueOf(cursor.getString(3))));

            }
        }
    }

    private void affichage() {
        DecimalFormat df = new DecimalFormat("0");

        txt.setText(MainActivity.getUtilisateur().getPlanAlim().voirPlan());
        proteineKcal.setText(String.valueOf(df.format(MainActivity.getUtilisateur().getPlanAlim().getProt() * 4)));
        glucideKcal.setText(String.valueOf(df.format(MainActivity.getUtilisateur().getPlanAlim().getGlucide() * 4)));
        lipideKcal.setText(String.valueOf(df.format(MainActivity.getUtilisateur().getPlanAlim().getLipide() * 9)));
        Totalkcal.setText(df.format(MainActivity.getUtilisateur().getPlanAlim().getObjectif()));

        proteineG.setText(String.valueOf(df.format(MainActivity.getUtilisateur().getPlanAlim().getProt())));
        glucideG.setText(String.valueOf(df.format(MainActivity.getUtilisateur().getPlanAlim().getGlucide())));
        lipideG.setText(String.valueOf(df.format(MainActivity.getUtilisateur().getPlanAlim().getLipide())));

        proteineP100.setText(String.valueOf(df.format((MainActivity.getUtilisateur().getPlanAlim().getProt() * 4 / MainActivity.getUtilisateur().getPlanAlim().getObjectif()) * 100)) + " %");
        glucideP100.setText(String.valueOf(df.format((MainActivity.getUtilisateur().getPlanAlim().getGlucide() * 4 / MainActivity.getUtilisateur().getPlanAlim().getObjectif()) * 100)) + " %");
        lipides100.setText(String.valueOf(df.format((MainActivity.getUtilisateur().getPlanAlim().getLipide() * 9 / MainActivity.getUtilisateur().getPlanAlim().getObjectif()) * 100)) + " %");

        totalp100.setText("100 %");
        totalG.setText("-");

    }


}