package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class leavetype extends AppCompatActivity {
    Button casual,emergency,medical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leavetype);
        casual=(Button) findViewById(R.id.cb);
        emergency=(Button) findViewById(R.id.eb);
        medical=(Button) findViewById(R.id.mb);
        casual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(leavetype.this,otherleave.class);
                startActivity(intent);
                finish();

            }
        });
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(leavetype.this,otherleave.class);
                startActivity(intent);
                finish();

            }
        });
        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(leavetype.this,upload.class);
                startActivity(intent);
                finish();

            }
        });
    }
}