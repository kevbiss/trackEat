package com.example.finalprojectandroid.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectandroid.R;

import java.io.Serializable;
import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity implements StatsAdapter.OnStatsListener{
    private MyDatabaseHelper myDB;

    Mesure mesure;

    private Button add;

    private ArrayList<String> stats;

    private ArrayList<Mesure> mesures;

    RecyclerView recyclerView;
    private StatsAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        myDB =new MyDatabaseHelper(StatsActivity.this);
        recyclerView= findViewById(R.id.recyclerViewStats);

        stats =new ArrayList<>();
        mesures =new ArrayList<>();

        StoreMesureData();

        customAdapter =new StatsAdapter(StatsActivity.this,mesures,this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(StatsActivity.this));



       // add =findViewById(R.id.next);

       /*add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StatsActivity.this,CreationPlanActivity.class);
                startActivity(intent);

            }
        });*/



    }


    public void StoreMesureData() {

        Cursor cursor = myDB.readSuivi();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
               mesure =new Mesure(Integer.valueOf(cursor.getString(1)),Double.valueOf(cursor.getString(4)),Double.valueOf(cursor.getString(5)),Double.valueOf(cursor.getString(6)),Double.valueOf(cursor.getString(7)),Double.valueOf(cursor.getString(8)),Double.valueOf(cursor.getString(9)),Double.valueOf(cursor.getString(10)),Double.valueOf(cursor.getString(11)),Double.valueOf(cursor.getString(12)),Double.valueOf(cursor.getString(13)),Double.valueOf(cursor.getString(14)),Double.valueOf(cursor.getString(15)));
               mesures.add(mesure);

            }
        }

    }

    public void ajoutMesure(){
       // mesure = new Mesure(Integer.valueOf(cursor.getString(1)),Double.valueOf(cursor.getString(4)),Double.valueOf(cursor.getString(5)),Double.valueOf(cursor.getString(6)),Double.valueOf(cursor.getString(7)),Double.valueOf(cursor.getString(8)),Double.valueOf(cursor.getString(9)),Double.valueOf(cursor.getString(10)),Double.valueOf(cursor.getString(11)),Double.valueOf(cursor.getString(12)),Double.valueOf(cursor.getString(13)),Double.valueOf(cursor.getString(14)),Double.valueOf(cursor.getString(15)));
    }

    private void Affichage(){

    }

    @Override
    public void onStatsCLick(int position) {
        //ArrayList<Mesure> mes =new ArrayList();
         //mes.add(mesures.get(position));

        Intent intent = new Intent(this, StatsChosiActivity.class);
        Bundle bundle = new Bundle();
        Mesure mesure1=mesures.get(position);
        bundle.putSerializable("Mesure", mesure1);

        intent.putExtras(bundle);
        this.startActivity(intent);

    }
}