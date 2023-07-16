package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Fhomescreen extends AppCompatActivity {
    Button request,approved,denied,report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fhomescreen);
        request=(Button) findViewById(R.id.button5);
        approved=(Button) findViewById(R.id.button6);
        denied=(Button) findViewById(R.id.button7);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fhomescreen.this,facultyrequests.class);
                startActivity(intent);
            }
        });
        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fhomescreen.this,approvedrequests.class);
                startActivity(intent);
                finish();
            }
        });
        denied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fhomescreen.this,deniedrequests.class);
                startActivity(intent);
                finish();
            }
        });

    }
}