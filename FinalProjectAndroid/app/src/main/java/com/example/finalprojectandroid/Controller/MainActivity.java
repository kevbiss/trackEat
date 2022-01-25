package com.example.finalprojectandroid.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectandroid.R;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static Utilisateur utilisateur=new Utilisateur();
    
    private PlanAlim planAlim;

    private static Admin admin;

    private InfoPerso infoPerso;

    private Mesure mesure;

    private Comptage comptage;

    private MyDatabaseHelper myDB;

    private boolean flag;

    ArrayList<String> ids,lastNames, names, stats,infopersos;

    private Button add,mcomptage,planA;


    private TextView name,pds;

    private TextView date,IMC,poids,grss,cou,poitrine,ventre,taille,fesse,brasG,brasD,cuisseG,cuisseD;
    private TextView objectifPoids, objectifGraisse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB =new MyDatabaseHelper(MainActivity.this);
        ids =new ArrayList<>();
        lastNames=new ArrayList<>();
        names=new ArrayList<>();
        stats =new ArrayList<>();
        infopersos = new ArrayList<>();


        StoreDataUtilisateur();

        if (flag){
            initElem();
            init();
            StoreInfoPersoData();
            StoreMesureData1();
            StorePlanAlim();
            //StoreComptage();
            //initComptage();
            Affichage();
        }
       else {
           initEmpty();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.app_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                Intent intent = new Intent(MainActivity.this,AddMesureActivity.class);
                startActivity(intent);
                return true;

            case R.id.item3:
                 intent = new Intent(MainActivity.this,StatsActivity.class);
                startActivity(intent);


            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void initEmpty(){
        setContentView(R.layout.activity_new_utiliateur);
        TextView txt=findViewById(R.id.empty_txt);
        TextView prenom =findViewById(R.id.emptyPrenom);
        TextView nom =findViewById(R.id.emptyNom);

        EditText ePrenom =findViewById(R.id.editTextPrenom);
        EditText eNom =findViewById(R.id.editNom);

        Button btn=findViewById(R.id.addEmpty);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ePrenom.getText().toString().isEmpty()  || eNom.getText().toString().isEmpty()){
                    txt.setText(" ERREUR !!! \n Veuillez saisir votre nom et prenom ");
                }
                else{
                    addUtilisateur(ePrenom.getText().toString(),eNom.getText().toString());
                    initUtiEmpty(1,ePrenom.getText().toString(),eNom.getText().toString());
                    Intent intent = new Intent(MainActivity.this,AddInfoPersoActivity.class);
                    startActivity(intent);
                }
            }
        });


        String texte ="Bonjour nouvel utilisateur \n \n veuillez renseigner votre nom et prenom";
        txt.setText(texte);

        prenom.setText("Entrez votre prenom :");
        nom.setText("Entrez votre nom :");





    }

    private void initUtiEmpty(int id, String prenom, String nom){
        utilisateur.setId(id);
        utilisateur.setPrenom(prenom);
        utilisateur.setNom(nom);
    }
    void initElem(){

        setContentView(R.layout.activity_main);

        name=findViewById(R.id.uti_name);
        pds=findViewById(R.id.txtPoids);






        date=findViewById(R.id.txtDateAjd);
        IMC=findViewById(R.id.txtIMC);
        poids=findViewById(R.id.txtPoidsMain);
        grss=findViewById(R.id.txtGraisseCorporelle);
        cou=findViewById(R.id.txtTcou);
        poitrine=findViewById(R.id.txtTPoitrine);
        ventre=findViewById(R.id.txtTVentre);
        taille=findViewById(R.id.txtTTaille);
        fesse=findViewById(R.id.txtTFesse);
        brasG=findViewById(R.id.txtBrasGauche);
        brasD=findViewById(R.id.txtBrasDroit);
        cuisseG=findViewById(R.id.txtCuisseGauche);
        cuisseD=findViewById(R.id.txtCuisseDroit);

        objectifGraisse=findViewById(R.id.txtObjectifGraisse);
        objectifPoids=findViewById(R.id.txtObjectifPoids);

        mcomptage=findViewById(R.id.comptagebtn);
        mcomptage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ComptageActivity.class);
                startActivity(intent);

            }
        });

        planA=findViewById(R.id.planAlimbtn);
        planA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PlanActivity.class);
                startActivity(intent);
            }
        });



    }

    void add(){
        addUtilisateur("kevin","bissila");
        addInfoperso();
        addSuivit();
       // ajoutMesure();


    }

    void addUtilisateur(String nom , String prenom){
        MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
        myDB.addUtilisateur(nom,prenom);

    }

    void addInfoperso(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
        myDB.addInfoperso(1,1.35,185,80,20,40);
    }

    void addSuivit(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
        //utilisateur.getId(),
        myDB.addSuivitPoid(1,Double.valueOf(185),Double.valueOf(105),Double.valueOf(17),Double.valueOf(40),Double.valueOf(91),Double.valueOf(85),Double.valueOf(34),Double.valueOf(34),Double.valueOf(77),Double.valueOf(103),Double.valueOf(60),Double.valueOf(60));
        myDB.addSuivitPoid(1,Double.valueOf(185),Double.valueOf(110),Double.valueOf(17),Double.valueOf(40),Double.valueOf(91),Double.valueOf(85),Double.valueOf(34),Double.valueOf(34),Double.valueOf(77),Double.valueOf(103),Double.valueOf(60),Double.valueOf(60));
        myDB.addSuivitPoid(1,Double.valueOf(185),Double.valueOf(120),Double.valueOf(17),Double.valueOf(40),Double.valueOf(91),Double.valueOf(85),Double.valueOf(34),Double.valueOf(34),Double.valueOf(77),Double.valueOf(103),Double.valueOf(60),Double.valueOf(60));

    }



    private void StoreDataUtilisateur() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Utilisateur", Toast.LENGTH_SHORT).show();
            flag=false;

        }
        else {
            flag=true;
            while (cursor.moveToNext()){
                ids.add(cursor.getString(0));
                names.add(cursor.getString(1));
                lastNames.add(cursor.getString(2));
            }
        }

    }


    private void StoreMesureData1() {

        Cursor cursor = myDB.readSuivi();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data in Mesure", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                mesure =new Mesure(Integer.valueOf(cursor.getString(1)),Double.valueOf(cursor.getString(4)),Double.valueOf(cursor.getString(5)),Double.valueOf(cursor.getString(6)),Double.valueOf(cursor.getString(7)),Double.valueOf(cursor.getString(8)),Double.valueOf(cursor.getString(9)),Double.valueOf(cursor.getString(10)),Double.valueOf(cursor.getString(11)),Double.valueOf(cursor.getString(12)),Double.valueOf(cursor.getString(13)),Double.valueOf(cursor.getString(14)),Double.valueOf(cursor.getString(15)));
            }
        }

    }

    private void StoreInfoPersoData() {

        Cursor cursor = myDB.readInfoPerso(utilisateur.getId());
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data in Info perso", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                infoPerso = new InfoPerso(utilisateur,Double.valueOf(cursor.getString(3)),Double.valueOf(cursor.getString(2)),Double.valueOf(cursor.getString(5)),Double.valueOf(cursor.getString(4)));
            }
        }

    }

    private void init(){
        utilisateur.setId(Integer.valueOf(ids.get(0)));
        utilisateur.setNom(lastNames.get(0));
        utilisateur.setPrenom(names.get(0));

    }

    private void StorePlanAlim() {

        Cursor cursor = myDB.readPlanAlimUtilisateur(MainActivity.getUtilisateur().getId());
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Vous n'avez pas de plan Alim en cours ", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {
                utilisateur.getListePlanAlim().add(new PlanAlim(Integer.valueOf(cursor.getString(2)),Double.valueOf(cursor.getString(3))));
            }
            planAlim=utilisateur.getListePlanAlim().get(utilisateur.getListePlanAlim().size()-1);
            StoreComptage();


            }
    }

    private void StoreComptage() {

        Cursor cursor = myDB.readComptage(utilisateur.getId());
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data Comptage", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {
               utilisateur.getComptages().add(new Comptage(Integer.valueOf(cursor.getString(0)),Double.valueOf(cursor.getString(4)),Double.valueOf(cursor.getString(5)),Double.valueOf(cursor.getString(6)),Double.valueOf(cursor.getString(7))));
            }
            initComptage();

        }
    }

    private void initComptage(){
        utilisateur.setComptage(utilisateur.getComptages().get(utilisateur.getComptages().size()-1));
         }

    private void Affichage() {
        DecimalFormat df = new DecimalFormat("0");
        LocalDate lc = LocalDate.now();

        name.setText(utilisateur.getPrenom());
        pds.setText("Poids :\n "+ String.valueOf(utilisateur.getMesure().getPoids()) + " kg");


        date.setText(String.valueOf(lc.toString()));
        double imc= mesure.getPoids()/(Math.pow((utilisateur.getInfoPerso().getTaille()/100),2));


        IMC.setText(" IMC : \n " +String.valueOf(df.format(imc)));


        objectifPoids.setText(" objectif Poids : \n "+ String.valueOf(infoPerso.getOPoids()) + " kg");
        objectifGraisse.setText(" objectif Graisse Corporelle : \n "+ String.valueOf(infoPerso.getOTauxDeGraisse())+" %");

        poids.setText(" Poids : \n "+String.valueOf(mesure.getPoids())+ " kg");

        grss.setText(" Graisse Corporelle : \n "+String.valueOf(mesure.getPcrtMasseGraisseuse()*100)+" %");




        cou.setText(" tour de cou : \n "+String.valueOf(mesure.gettCou()) +" cm");
        poitrine.setText(" tour de poitrine : \n "+String.valueOf(mesure.gettPoitrine())+" cm");
        ventre.setText(" tour de ventre  : \n "+String.valueOf(mesure.gettVentre())+" cm");


        taille.setText(" tour de taille : \n "+String.valueOf(mesure.gettTaille())+" cm");
        brasD.setText(" tour de bras droit : \n "+String.valueOf(mesure.gettBrasDroit())+" cm");
        brasG.setText(" tour de bras gauche : \n "+String.valueOf(mesure.gettBrasGauche())+" cm");
        cuisseD.setText(" tour de cuisse droit : \n "+String.valueOf(mesure.gettCuisseDroite())+" cm");
        cuisseG.setText(" tour de cuisse gauche : \n "+String.valueOf(mesure.gettCuisseGauche())+" cm");
        fesse.setText(" tour de hanche : \n "+String.valueOf(mesure.gettFesse())+" cm");


    }

    public Comptage getComptage() {
        return comptage;
    }

    public ArrayList<String> getStats() {
        return stats;
    }

    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }
}