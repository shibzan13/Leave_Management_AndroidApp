package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class deniedrequests extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private List<Certificate> certificateList;
    private CertificateAdapter adapter;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deniedrequests);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        certificateList = new ArrayList<>();
        adapter = new CertificateAdapter(certificateList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myapp-e3efe-default-rtdb.firebaseio.com/DENIED DATA");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                certificateList.clear();
                for (DataSnapshot certificateSnapshot : dataSnapshot.getChildren()) {
                    Certificate certificate = certificateSnapshot.getValue(Certificate.class);
                    certificateList.add(certificate);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors here
            }
        });
    }

    public static class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.CertificateViewHolder> {

        private static List<Certificate> certificateList;

        public CertificateAdapter(List<Certificate> certificateList) {
            this.certificateList = certificateList;
        }

        @NonNull
        @Override
        public CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_certificate, parent, false);
            return new CertificateViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CertificateViewHolder holder, int position) {
            Certificate certificate = certificateList.get(position);
            holder.bind(certificate);
        }

        @Override
        public int getItemCount() {
            return certificateList.size();
        }



        public static class CertificateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView dataDescTextView;
            private TextView dataNameView;
            private ImageView dataImageView;

            public CertificateViewHolder(View itemView) {
                super(itemView);
                dataDescTextView = itemView.findViewById(R.id.dataDescTextView);
                dataNameView = itemView.findViewById(R.id.dataNameView);
                dataImageView = itemView.findViewById(R.id.dataImageView);

                // Set the click listener on the item view
                itemView.setOnClickListener(this);
            }

            public void bind(Certificate certificate) {
                dataNameView.setText(certificate.getDataName());
                dataDescTextView.setText(certificate.getDataDesc());
                Picasso.get().load(certificate.getDataImage()).into(dataImageView);
            }

            @Override
            public void onClick(View view) {
                // Get the clicked item position
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Get the clicked item from the list
                    Certificate certificate = certificateList.get(position);
                    // Perform the desired action with the clicked item
                    // For example, open a new activity with the clicked item details
                    Intent intent = new Intent(view.getContext(), Detailsdisplay.class);
                    intent.putExtra("certificate", (Serializable) certificate);
                    view.getContext().startActivity(intent);
                }
            }
        }

    }


}

