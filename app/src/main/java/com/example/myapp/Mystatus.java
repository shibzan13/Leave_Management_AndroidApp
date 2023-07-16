package com.example.myapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Mystatus extends AppCompatActivity {
    private RecyclerView rcv;
    private MyAdapter adapter;
    private List<MyData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystatus);

        DatabaseReference approvedDataRef = FirebaseDatabase.getInstance().getReference().child("APPROVE");

        rcv = findViewById(R.id.rc_1);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        adapter = new MyAdapter(dataList);
        rcv.setAdapter(adapter);

        Query query = approvedDataRef.orderByChild("dataName");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String dataName = childSnapshot.child("dataName").getValue(String.class);
                    String dataDesc  =childSnapshot.child("dataDesc").getValue(String.class);
                    String dataImage = childSnapshot.child("dataImage").getValue(String.class);
                    dataList.add(new MyData(dataName, dataImage,dataDesc));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<MyData> dataList;

        public MyAdapter(List<MyData> dataList) {
            this.dataList = dataList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MyData data = dataList.get(position);
            holder.dataNameTextView.setText(data.getDataName());
            holder.dataDescTextView.setText(data.getDataDesc());

            Picasso.get().load(data.getDataImage()).into(holder.dataImageView);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView dataNameTextView;
            public TextView dataDescTextView;
            public ImageView dataImageView;

            public ViewHolder(View itemView) {
                super(itemView);
                dataNameTextView = itemView.findViewById(R.id.data_name_textview);
                dataDescTextView = itemView.findViewById(R.id.data_desc_textview);
                dataImageView = itemView.findViewById(R.id.data_image_imageview);
            }
        }
    }
}
