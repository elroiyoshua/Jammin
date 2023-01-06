package com.example.jamminbaru;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private  final recyclerviewinterface recyclerviewinterface;
    ArrayList<lagumodel> lagumodels;
    //String URL[];
    Context context;


    public MyAdapter(Context context, ArrayList<lagumodel> lagumodels,recyclerviewinterface recyclerviewinterface){
            this.context = context;
            this.lagumodels = lagumodels;
            this.recyclerviewinterface = recyclerviewinterface;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new  MyAdapter.MyViewHolder(view,recyclerviewinterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.text1.setText(lagumodels.get(position).getJudul());
        holder.text2.setText(lagumodels.get(position).getBand());
        holder.imageku.setImageResource(lagumodels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return lagumodels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text1,text2;
        ImageView imageku;
        ConstraintLayout mainlayout;
        public  MyViewHolder(@NonNull View itemView,recyclerviewinterface recyclerviewinterface) {
            super(itemView);
            text1 = itemView.findViewById(R.id.title);
            text2 = itemView.findViewById(R.id.penyanyi);
            imageku = itemView.findViewById(R.id.gambar);
            mainlayout = itemView.findViewById(R.id.mainlayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerviewinterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerviewinterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
