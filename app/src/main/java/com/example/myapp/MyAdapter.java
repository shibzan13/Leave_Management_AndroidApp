package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MyData> dataList;

    public MyAdapter(List<MyData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyData data = dataList.get(position);
        holder.dataNameTextView.setText(data.getDataName());
        holder.dataDescTextView.setText(data.getDataDesc());
        Picasso.get().load(data.getDataImage()).into(holder.dataImageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dataNameTextView;
        public TextView dataDescTextView;
        public ImageView dataImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dataNameTextView = itemView.findViewById(R.id.data_name_textview);
            dataDescTextView = itemView.findViewById(R.id.data_desc_textview);
            dataImageView = itemView.findViewById(R.id.data_image_imageview);
        }
    }
}
