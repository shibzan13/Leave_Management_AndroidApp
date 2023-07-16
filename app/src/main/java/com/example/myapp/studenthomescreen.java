package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class studenthomescreen extends AppCompatActivity {
    Button rl,ls,lr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenthomescreen);
        rl=(Button) findViewById(R.id.rl);
        lr=(Button) findViewById(R.id.lr);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(studenthomescreen.this,datepicking.class);
                startActivity(intent);
                finish();

            }
        });
        lr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(studenthomescreen.this,Mystatus.class);
                startActivity(intent);
                finish();

            }
        });
    }
}