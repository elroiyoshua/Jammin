package com.example.jamminbaru.Lagu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jamminbaru.R;

import java.util.ArrayList;

public class LaguAdapter extends RecyclerView.Adapter<LaguAdapter.MyViewHolder> {
    private  final onClickLagu onClickLagu;
    static ArrayList<lagumodel> lagumodels;
    Context context;

    public LaguAdapter(Context context, ArrayList<lagumodel> lagumodels, onClickLagu onClickLagu){
            this.context = context;
            this.lagumodels = lagumodels;
            this.onClickLagu = onClickLagu;
    }
    public void  setFilteredlist(ArrayList<lagumodel> filteredlist){
        this.lagumodels = filteredlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new  LaguAdapter.MyViewHolder(view, onClickLagu);
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
        public  MyViewHolder(@NonNull View itemView, onClickLagu onClickLagu) {
            super(itemView);
            text1 = itemView.findViewById(R.id.title);
            text2 = itemView.findViewById(R.id.penyanyi);
            imageku = itemView.findViewById(R.id.gambar);
            mainlayout = itemView.findViewById(R.id.mainlayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(onClickLagu != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            onClickLagu.onItemClick(lagumodels.get(getAdapterPosition()));
                        }
                    }
                }
            });
        }
    }
}
