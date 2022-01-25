package com.example.finalprojectandroid.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalprojectandroid.R;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class StatsChosiActivity extends AppCompatActivity {
    private Mesure mesure;
    private TextView txt;
    private ArrayList<Mesure> mesures;

    private TextView date,IMC,poids,grss,cou,poitrine,ventre,taille,fesse,brasG,brasD,cuisseG,cuisseD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_chosi);
        initElem();
        Bundle bundleObject = this.getIntent().getExtras();
        mesure= (Mesure) bundleObject.getSerializable("Mesure");
        Affichage();

    }

    void initElem(){

        date=findViewById(R.id.txtDateAjd1);
        IMC=findViewById(R.id.txtIMC1);
        poids=findViewById(R.id.txtPoidsMain1);
        grss=findViewById(R.id.txtGraisseCorporelle1);
        cou=findViewById(R.id.txtTcou1);
        poitrine=findViewById(R.id.txtTPoitrine1);
        ventre=findViewById(R.id.txtTVentre1);
        taille=findViewById(R.id.txtTTaille1);
        fesse=findViewById(R.id.txtTFesse1);
        brasG=findViewById(R.id.txtBrasGauche1);
        brasD=findViewById(R.id.txtBrasDroit1);
        cuisseG=findViewById(R.id.txtCuisseGauche1);
        cuisseD=findViewById(R.id.txtCuisseDroit1);
    }

    private void Affichage() {
        DecimalFormat df = new DecimalFormat("0");
        LocalDate lc = LocalDate.now();


        date.setText(mesure.getDate().toString());
        double imc= mesure.getPoids()/(Math.pow((MainActivity.getUtilisateur().getInfoPerso().getTaille()/100),2));


        IMC.setText(" IMC : \n " +String.valueOf(df.format(imc)));



        poids.setText(" Poids : \n "+String.valueOf(mesure.getPoids()));

        grss.setText(" Graisse Corporelle : \n "+String.valueOf(mesure.getPcrtMasseGraisseuse()*100)+" %");


        cou.setText(" tour de cou : \n "+String.valueOf(mesure.gettCou())+" cm");
        poitrine.setText(" tour de poitrine : \n "+String.valueOf(mesure.gettPoitrine())+" cm");
        ventre.setText(" tour de ventre  : \n "+String.valueOf(mesure.gettVentre())+" cm");


        taille.setText(" tour de taille : \n "+String.valueOf(mesure.gettTaille())+" cm");
        brasD.setText(" tour de bras droit : \n "+String.valueOf(mesure.gettBrasDroit())+" cm");
        brasG.setText(" tour de bras gauche : \n "+String.valueOf(mesure.gettBrasGauche())+" cm");
        cuisseD.setText(" tour de cuisse droit : \n "+String.valueOf(mesure.gettCuisseDroite())+" cm");
        cuisseG.setText(" tour de cuisse gauche : \n "+String.valueOf(mesure.gettCuisseGauche())+" cm");
        fesse.setText(" tour de fesse : \n "+String.valueOf(mesure.gettFesse())+" cm");


    }
}