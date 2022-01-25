package com.example.finalprojectandroid.Controller;
import com.example.finalprojectandroid.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private  Context context;
    private FAQ faq;

    private  ArrayList<String> questions, reponses;



    CustomAdapter(Context context , FAQ faq){
        this.context=context;
        this.faq=faq;

        questions = new ArrayList<>();
        reponses = new ArrayList<>();
        for (int k=0; k< Question.getComptage();k++){
            this.questions.add(faq.getFaq().get(k).getQuestion());
            this.reponses.add(faq.getFaq().get(k).getReponse());
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.faq,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.question_txt.setText(String.valueOf(questions.get(position)));
        holder.reponse_txt.setText(String.valueOf(reponses.get(position)));

    }

    @Override
    public int getItemCount() {
        return faq.taille();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question_txt, reponse_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question_txt =itemView.findViewById(R.id.question);
            reponse_txt=itemView.findViewById(R.id.reponse);

        }
    }


}
