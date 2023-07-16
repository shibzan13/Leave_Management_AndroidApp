package com.example.myapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
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

import java.io.ByteArrayOutputStream;

public class approvedeny extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameTextView, descTextView;
    private Button saveButton, denyButton;

    private DatabaseReference approvedDataReference;
    private DatabaseReference denyDataReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvedeny);

        imageView = findViewById(R.id.imageView4);
        nameTextView = findViewById(R.id.textView9);
        descTextView = findViewById(R.id.textView10);
        saveButton = findViewById(R.id.storeButton);
        denyButton = findViewById(R.id.denib);

        approvedDataReference = FirebaseDatabase.getInstance().getReference().child("APPROVE");
        denyDataReference = FirebaseDatabase.getInstance().getReference().child("DENIED DATA");

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("itemId")) {
            String itemId = extras.getString("itemId");
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("UPLOADED MEDICAL CERTIFICATES").child(itemId);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Certificate certificate = dataSnapshot.getValue(Certificate.class);
                    if (certificate != null) {
                        String imageUrl = certificate.getDataImage();
                        String name = certificate.getDataName();
                        String desc = certificate.getDataDesc();

                        nameTextView.setText(name);
                        descTextView.setText(desc);
                        Picasso.get().load(imageUrl).into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                // Handle success here
                            }

                            @Override
                            public void onError(Exception e) {
                                // Handle errors here
                                Toast.makeText(approvedeny.this, "Failed to load image", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors here
                    Toast.makeText(approvedeny.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            });
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTextView.getText().toString().trim();
                String desc = descTextView.getText().toString().trim();

                if (imageView.getDrawable() != null) {
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageData = baos.toByteArray();

                    String imageString = Base64.encodeToString(imageData, Base64.DEFAULT);

                    DatabaseReference newCertificateRef = approvedDataReference.push();
                    String certificateId = newCertificateRef.getKey();

                    newCertificateRef.child("dataName").setValue(name);
                    newCertificateRef.child("dataDesc").setValue(desc);
                    newCertificateRef.child("dataImage").setValue(imageString);

                    Toast.makeText(approvedeny.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(approvedeny.this, "No image selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTextView.getText().toString().trim();
                String desc = descTextView.getText().toString().trim();
                if (imageView.getDrawable() != null) {
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageData = baos.toByteArray();
                    String imageString = Base64.encodeToString(imageData, Base64.DEFAULT);
                    DatabaseReference newCertificateRef = denyDataReference.push();
                    String certificateId = newCertificateRef.getKey();

                    newCertificateRef.child("dataName").setValue(name);
                    newCertificateRef.child("dataDesc").setValue(desc);
                    newCertificateRef.child("dataImage").setValue(imageString);

                    Toast.makeText(approvedeny.this, "Data rejected successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(approvedeny.this, "No image selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
