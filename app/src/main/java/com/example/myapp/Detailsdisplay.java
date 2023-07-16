package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Detailsdisplay extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameTextView, descTextView;
    private Button saveButton,denyButton;

    private DatabaseReference databaseReference;
    private DatabaseReference savedDataReference;// Reference to the node where data will be saved
    private DatabaseReference denyDataReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsdisplay);

        imageView = findViewById(R.id.imageView4);
        nameTextView = findViewById(R.id.textView9);
        descTextView = findViewById(R.id.textView10);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("UPLOADED MEDICAL CERTIFICATES");
        savedDataReference = FirebaseDatabase.getInstance().getReference().child("SAVED_DATA"); // Change to your desired node name
        denyDataReference = FirebaseDatabase.getInstance().getReference().child("DENIED DATA");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String imageUrl = snapshot.child("dataImage").getValue(String.class);
                    String name = snapshot.child("dataName").getValue(String.class);
                    String desc = snapshot.child("dataDesc").getValue(String.class);

                    Picasso.get().load(imageUrl).into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            nameTextView.setText(name);
                            descTextView.setText(desc);
                        }

                        @Override
                        public void onError(Exception e) {
                            // Handle errors here
                            Toast.makeText(Detailsdisplay.this, "Failed to load image", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors here
                Toast.makeText(Detailsdisplay.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

    }}