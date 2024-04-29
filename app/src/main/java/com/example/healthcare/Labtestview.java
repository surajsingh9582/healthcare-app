package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Labtestview extends AppCompatActivity {
    TextView packagesView,tvs;
    EditText multi;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtestview);
        packagesView=findViewById(R.id.viewlabcarttitle);
        tvs=findViewById(R.id.textViewlabans);
        multi=findViewById(R.id.multilabcartmid);
        btn1=findViewById(R.id.buttonlab1gotocart);
        btn2=findViewById(R.id.button1labback);

        multi.setKeyListener(null);

        Intent its=getIntent();
        packagesView.setText(its.getStringExtra("text1"));
        multi.setText(its.getStringExtra("text2"));
        tvs.setText("Total Cost : "+its.getStringExtra("text3"));

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Labtestview.this,labActivity.class));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=packagesView.getText().toString();
                float price=Float.parseFloat(its.getStringExtra("text3").toString());

                dataBase db=new dataBase(getApplicationContext(),"healthcare",null,1);

                if(db.cheakCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addcart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(), "Record Inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Labtestview.this,labActivity.class));
                }
            }
        });
    }
}