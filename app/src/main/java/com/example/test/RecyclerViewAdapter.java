package com.example.test;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import okhttp3.internal.connection.ConnectionSpecSelector;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private ArrayList<Test> data;
    OnOptionItemClickListener itemClickListener;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        View rowView;

        public MyViewHolder(View itemView) {
            super(itemView);
            rowView = itemView;
            mTitle = itemView.findViewById(R.id.txtTitle);
        }
    }

    public RecyclerViewAdapter(ArrayList<Test> data, OnOptionItemClickListener itemClickListener) {
        this.data = data;
        this.itemClickListener=itemClickListener;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTitle.setText(data.get(position).getText());
       // holder.mTitle.setOnTouchListener(this);
        holder.mTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                final Test item = data.get(holder.getAdapterPosition());
//                final Test state = new Test(data.get(holder.getAdapterPosition()).getId(), item.getText().toString());
//                return true;

                if (event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(null, shadowBuilder, v, 0);
                    itemClickListener.onSelect(data.get(position).getText(),holder.mTitle);
                    return true;
                }
                else return false;

            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnOptionItemClickListener {
        void onSelect(String text,TextView textView);
    }

}

