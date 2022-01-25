package com.example.finalprojectandroid.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalprojectandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestAPIActivity extends AppCompatActivity {
    private TextView textView,txt;
    private Comptage comptage;

    private EditText editText;

    private Dialog dlg;

    private Map dictionary = new HashMap();// creation du dictionaire monnaie/taux dechange
    private Map nutritions = new HashMap();
    private Button btn,quit;

    private MyDatabaseHelper myDB;

    private ArrayList<String> nom,id;

    private ArrayList<Double> ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_apiactivity);

        myDB=new MyDatabaseHelper(TestAPIActivity.this);

        btn = findViewById(R.id.button_add_alim);
        quit=findViewById(R.id.buttonQuit);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestAPIActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        txt=findViewById(R.id.txt_View_cpt);
        editText =findViewById(R.id.editQtt);

        nom =new ArrayList<>();
        id =new ArrayList<>();
        ls=new ArrayList<>();

         textView = (TextView) findViewById(R.id.TXT);

        initDoc();

         textView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 dlg = new Dialog(TestAPIActivity.this);

                 dlg.setContentView(R.layout.dialogue_searchable_spinner);

                 dlg.getWindow().setLayout(650,800);

                 dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                 dlg.show();

                 // init and asign

                 EditText editText =dlg.findViewById(R.id.edit_text);
                 ListView lstView =dlg.findViewById(R.id.listView);

                 //init array adapter

                 ArrayAdapter<String > adapter = new ArrayAdapter<>(TestAPIActivity.this, android.R.layout.simple_list_item_1,nom);

                 //Set adapter

                 lstView.setAdapter(adapter);
                 editText.addTextChangedListener(new TextWatcher() {
                     @Override
                     public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                     }

                     @Override
                     public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                         //filter array list
                         adapter.getFilter().filter(charSequence);

                     }

                     @Override
                     public void afterTextChanged(Editable editable) {

                     }
                 });

                 lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                         //where item selected from list
                         //set selected item on text view

                         textView.setText((adapter.getItem(i)));
                         dlg.dismiss();
                     }
                 });

             }
         });
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(editText.getText().toString().isEmpty()){
                     txt.setText("Veuillez renseigner une quantité ");
                 }
                 else{
                     requeteApi(String.valueOf(textView.getText()),Integer.valueOf(editText.getText().toString()));


                     Toast.makeText(TestAPIActivity.this, " data", Toast.LENGTH_SHORT).show();
                 }


             }
         });

         quit=findViewById(R.id.buttonQuit);
         quit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(TestAPIActivity.this,ComptageActivity.class);
                 startActivity(intent);
             }
         });

    }

    public BufferedReader chargementDoc() {
        InputStream is = this.getResources().openRawResource(R.raw.ingredients_list);
        BufferedReader csv = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        return csv;
    }

    public void initDoc() {
        try {
            BufferedReader test = this.chargementDoc();
            String ligne = "";

            while((ligne = test.readLine()) != null) {
                String[] a = ligne.split(";");
                dictionary.put(a[0],a[1]);
                this.nom.add(a[0]);
            }

           // textView.setText(ls);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    private void addAliment(Aliment alm){
        MainActivity.getUtilisateur().getComptage().ajoutAliment(alm);
        myDB.modifieComptage(MainActivity.getUtilisateur().getComptage().getIdComptage(),alm.getKcal(),alm.getProteine(),alm.getGlucide(),alm.getLipide());
        myDB.addAliment(MainActivity.getUtilisateur().getComptage().getIdComptage(),textView.getText().toString(),Double.valueOf(editText.getText().toString()), alm.getKcal(), alm.getProteine(), alm.getGlucide(),alm.getLipide());

    }
    private void requeteApi(String keys,double amounts){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(TestAPIActivity.this);
        String test ="https://api.spoonacular.com/food/ingredients/12061/information?amount=1&apiKey=26c7218f2bcf488d9f0dc5a51c6a3d51";
        String spoonacular ="https://api.spoonacular.com/food/ingredients/";
        String id =String.valueOf(dictionary.get(keys));
        String infos ="/information?amount=1&";
        String key ="apiKey=26c7218f2bcf488d9f0dc5a51c6a3d51";
        String url = spoonacular+id+infos+key;



        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String test = "";
                        String keyVal;

                        try {

                            JSONObject mon = response.getJSONObject("nutrition");
                            JSONArray mJsonArrayProperty = mon.getJSONArray("nutrients");
                            JSONObject tst;



                            for (int i = 0; i < mJsonArrayProperty.length(); ++i) {

                                JSONObject jsn = mJsonArrayProperty.getJSONObject(i);
                                if (jsn.get("title").toString().equals("Calories")) {
                                    nutritions.put("Calories", Double.valueOf(jsn.get("amount").toString()));

                                    //ls.add(0,Double.valueOf(jsn.get("amount").toString()));
                                } else if (jsn.get("title").toString().equals("Protein")) {
                                    nutritions.put("Protein", Double.valueOf(jsn.get("amount").toString()));
                                } else if (jsn.get("title").toString().equals("Carbohydrates")) {
                                    nutritions.put("Carbohydrates", Double.valueOf(jsn.get("amount").toString()));
                                } else if (jsn.get("title").toString().equals("Fat")) {
                                    nutritions.put("Fat", Double.valueOf(jsn.get("amount").toString()));
                                }
                            }
                                nutritions.put("portions",Double.valueOf(mon.getJSONObject("weightPerServing").get("amount").toString()));


                            double coeff, kcal,glu,prot,lipides;

                            coeff =(100)/ (Double) nutritions.get("portions");
                            kcal =(amounts/100) * coeff *  Double.parseDouble(String.valueOf(nutritions.get("Calories")));
                            glu =amounts *coeff *(Double) nutritions.get("Carbohydrates");
                            prot =amounts *coeff *(Double) nutritions.get("Protein");
                            lipides =amounts *coeff *(Double) nutritions.get("Fat");

                            Aliment alm =new Aliment(textView.getText().toString(),Double.valueOf(editText.getText().toString()),kcal,prot,lipides,glu);
                            addAliment(alm);

                            String aff=alm.aff();
                            txt.setText(aff + "\n\n Choisissez un autre élement ou appuyer sur le bouton quitter si vous avez fini");



                        }catch (JSONException e) {
                            e.printStackTrace();
                        } } }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TestAPIActivity.this,"That didn't work!",Toast.LENGTH_SHORT ).show();
                txt.setText("Veuillez choisir un element à ajouter !!!");
            }});
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }




}