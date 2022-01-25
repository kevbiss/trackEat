package com.example.finalprojectandroid.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectandroid.R;

import java.util.ArrayList;

public class StatsAdapter extends RecyclerView.Adapter<com.example.finalprojectandroid.Controller.StatsAdapter.MyViewHolder> {


        private Context context;
        private Mesure mesure;

        private ArrayList<Mesure> mesures;

        private StatsAdapter.OnStatsListener monStatsListener;



    StatsAdapter(Context context , ArrayList<Mesure> mesures,OnStatsListener onStatsListener) {
        this.context = context;
        this.mesures=mesures;
        this.monStatsListener=onStatsListener;
    }
        @NonNull
        @Override
        public com.example.finalprojectandroid.Controller.StatsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater =LayoutInflater.from(context);
            View view =inflater.inflate(R.layout.stats,parent,false);

            return new com.example.finalprojectandroid.Controller.StatsAdapter.MyViewHolder(view,monStatsListener);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.finalprojectandroid.Controller.StatsAdapter.MyViewHolder holder, int position) {
            holder.date_txt.setText(String.valueOf(mesures.get(position).getDate()));
            holder.poids_txt.setText(String.valueOf(mesures.get(position).getPoids()));

        }

        @Override
        public int getItemCount() {
            return mesures.size();
        }

    public interface OnStatsListener {
        void onStatsCLick(int position);
    }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView date_txt, poids_txt;
            StatsAdapter.OnStatsListener onStatsListener;
            public MyViewHolder(@NonNull View itemView, OnStatsListener onStatsListener) {
                super(itemView);
                date_txt =itemView.findViewById(R.id.statsDatetxt);
                poids_txt=itemView.findViewById(R.id.statsPoidstxt);
                this.onStatsListener=onStatsListener;
                itemView.setOnClickListener(this);
            }



            public void onClick(View view) {
                this.onStatsListener.onStatsCLick(this.getAdapterPosition());
            }
        }


    }


