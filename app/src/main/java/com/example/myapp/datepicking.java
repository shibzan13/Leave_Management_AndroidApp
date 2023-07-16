package com.example.myapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class datepicking extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener setListener;
    TextView from,to;
    Button reqb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicking);
        from=(TextView) findViewById(R.id.from);
        to=(TextView) findViewById(R.id.to);
        reqb=(Button)findViewById(R.id.reqb);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        datepicking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date =day+"/"+month+"/"+year;
                        from.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();;

            }
        });
setListener=new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month=month+1;
        String date=day+"/"+month+"/"+year;
        from.setText(date);


    }
};
to.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DatePickerDialog datePickerDialog=new DatePickerDialog(
                datepicking.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                String date =day+"/"+month+"/"+year;
                to.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();;

    }
});
reqb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(datepicking.this,leavetype.class);
        startActivity(intent);
        finish();
    }
});

    }
}