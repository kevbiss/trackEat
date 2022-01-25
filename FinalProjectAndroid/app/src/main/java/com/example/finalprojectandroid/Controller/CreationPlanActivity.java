package com.example.finalprojectandroid.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectandroid.R;

public class CreationPlanActivity extends AppCompatActivity {

    private TextView aff,tauxTXT;
    private CheckBox seche,Pmasse;

    private PlanAlim planAlim;

    private Button valider;

    private EditText taux;

    private int choix;
    private double coeff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_plan);
        aff =findViewById(R.id.textView);
        aff.setText("Veuillez remplir les champs pour créer un plan d'alimentation selon vos besoins");
        tauxTXT=findViewById(R.id.tauxtxt);

        seche =findViewById(R.id.Seche);
        Pmasse =findViewById(R.id.Pmasse);

        valider =findViewById(R.id.valider);

        taux=findViewById(R.id.tauxint);

        valider =findViewById(R.id.valider);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();

            }
        }) ;



    }

    void check(){

        if (seche.isChecked() & Pmasse.isChecked()){
            Toast.makeText(this, "Veuillez choisir un seul boutton !!!", Toast.LENGTH_SHORT).show();
        }

        else if (!(seche.isChecked()) & !(Pmasse.isChecked())){
            Toast.makeText(this, "Veuillez choisir un boutton !!!", Toast.LENGTH_SHORT).show();
        }

        else if (taux.getText().toString().equals("")){
            Toast.makeText(this, "Veuillez choisir un coefficient  !!!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Merci ", Toast.LENGTH_SHORT).show();
            choix();

            OnClick();

        }
    }

    public void OnClick(){

        Intent intent =new Intent(this,MainActivity.class);
        Bundle bundle  =new Bundle();
        creationPlan();
        addPlanAlim();
        addComptage();
        //bundle.putSerializable("choix",choix);
       // bundle.putSerializable("coeff",coeff);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    void addPlanAlim( ){
        MyDatabaseHelper myDB = new MyDatabaseHelper(CreationPlanActivity.this);
        myDB.addPlanAlim(MainActivity.getUtilisateur().getId(),choix,coeff,planAlim.getObjectif(),planAlim.getProt(),planAlim.getGlucide(),planAlim.getLipide());

    }

    public void choix(){
        if (seche.isChecked()) {
            choix =0;
        }
        else{
            choix=1;
        }
        coeff= Double.valueOf(taux.getText().toString());
    }

    public void creationPlan(){
        planAlim = new PlanAlim(choix,coeff);
    }

    void addComptage() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(CreationPlanActivity.this);
        myDB.addComptage(MainActivity.getUtilisateur().getId(),MainActivity.getUtilisateur().getPlanAlim().getObjectif(),MainActivity.getUtilisateur().getPlanAlim().getProt(),MainActivity.getUtilisateur().getPlanAlim().getGlucide(),MainActivity.getUtilisateur().getPlanAlim().getLipide());
    }






}