package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.LinkedTransferQueue;

public class doctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        CardView exit=findViewById(R.id.cardfdback);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(doctorActivity.this, homeActivity.class));
            }
        });
        CardView family=findViewById(R.id.cardfdPhysical);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(doctorActivity.this, doctorview.class);
                it.putExtra("title","Family Physicans");
                startActivity(it);
            }
        });
        CardView dietician=findViewById(R.id.cardfdDytietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(doctorActivity.this, doctorview.class);
                it.putExtra("title","DIETICIAN");
                startActivity(it);
            }
        });
        CardView dentist=findViewById(R.id.cardfddentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(doctorActivity.this, doctorview.class);
                it.putExtra("title","DENTIST");
                startActivity(it);
            }
        });
        CardView surgeon=findViewById(R.id.cardfdsurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(doctorActivity.this, doctorview.class);
                it.putExtra("title","SURGEON");
                startActivity(it);
            }
        });
        CardView car=findViewById(R.id.cardfdcardiologists);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(doctorActivity.this, doctorview.class);
                it.putExtra("title","CARDIOLOGISTS");
                startActivity(it);
            }
        });
    }
}