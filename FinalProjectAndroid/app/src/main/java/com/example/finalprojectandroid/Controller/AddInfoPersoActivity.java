package com.example.finalprojectandroid.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectandroid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddInfoPersoActivity extends AppCompatActivity {
    private ArrayList<String> multiplicateurs = new ArrayList<>();
    private AutoCompleteTextView autoTxt;
    private ArrayAdapter<String> adapterItems;
    private Double multiplicateur;

    private Map dictionary = new HashMap();

    private TextView txt;

    private EditText taux,objectifTaux,objectifPoids,taille;

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_perso);
        autoTxt =findViewById(R.id.choix);
        txt=findViewById(R.id.txtInfopersoempty);
        taux=findViewById(R.id.tauxDeGraisseInfoPerso);
        objectifTaux=findViewById(R.id.ObjectifTauxDeGraisseInfoPerso);
        objectifPoids=findViewById(R.id.ObjectifPoids);
        taille =findViewById(R.id.tailleEdit);
        multiplicateurs=new ArrayList<>();

        dictionary.put("x1.15 : peu ou pas d’exercice/sport (E/S)",1.15);
        dictionary.put("x1.25 : E/S 1-2 fois par semaine",1.25);
        dictionary.put("x1.4 : E/S 3-5 fois par semaine",1.4);
        dictionary.put("x1.55 : E/S 6-7 fois par semaine",1.55);
        dictionary.put("x1.8 : E/S 6-7 fois par semaine & travail physique",1.8);

        multiplicateurs.add("x1.15 : peu ou pas d’exercice/sport (E/S)");
        multiplicateurs.add("x1.25 : E/S 1-2 fois par semaine");
        multiplicateurs.add("x1.4 : E/S 3-5 fois par semaine");
        multiplicateurs.add("x1.55 : E/S 6-7 fois par semaine");
        multiplicateurs.add("x1.8 : E/S 6-7 fois par semaine & travail physique");

        adapterItems = new ArrayAdapter<String>(this,R.layout.dropdown_item,multiplicateurs);
        autoTxt.setAdapter(adapterItems);

        autoTxt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                multiplicateur = (Double) dictionary.get(parent.getItemAtPosition(position).toString());
            }
        });

        btn= findViewById(R.id.InfoPersoValider);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (multiplicateur == null ){
                    Toast.makeText(AddInfoPersoActivity.this,"Veuillez saissir un multiplicateur !!!",Toast.LENGTH_SHORT ).show();
                }
                else if (taux.getText().toString().isEmpty() || objectifTaux.getText().toString().isEmpty() || objectifPoids.getText().toString().isEmpty()){
                    Toast.makeText(AddInfoPersoActivity.this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT ).show();

                }
                else{
                    addInfoperso(multiplicateur,Double.valueOf(taille.getText().toString()),
                            Double.valueOf(objectifPoids.getText().toString()),
                            Double.valueOf(objectifTaux.getText().toString()),
                            Double.valueOf(taux.getText().toString()));
                    Intent intent = new Intent(AddInfoPersoActivity.this,AddMesureActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    void addInfoperso(Double mlt, Double taille, Double oPoids, Double oTauxdeG, Double tauxdeG){
        MainActivity.getUtilisateur().setInfoPerso(new InfoPerso(MainActivity.getUtilisateur(),taille,mlt,oPoids,oTauxdeG));
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddInfoPersoActivity.this);
        myDB.addInfoperso(MainActivity.getUtilisateur().getId(),mlt,taille,oPoids,oTauxdeG,tauxdeG);
    }
}