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

public class buyMedicine extends AppCompatActivity {
    TextView tvPackage,tvTotal;
    EditText edDetails;
    Button buyGoTo,buyBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        tvPackage=findViewById(R.id.viewBMRcarttitle);
        tvTotal=findViewById(R.id.textViewbuyans);
        edDetails=findViewById(R.id.multibuycartmid);
        buyBack=findViewById(R.id.buttonbuy1back);
        buyGoTo=findViewById(R.id.buttonbuygotocart);
        edDetails.setKeyListener(null);

        Intent intent=getIntent();
        tvPackage.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotal.setText(intent.getStringExtra("text3"));

        buyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buyMedicine.this,buyMedicineDetails.class));
            }
        });
        buyGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=tvPackage.getText().toString();
                Float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                dataBase db=new dataBase(getApplicationContext(),"healthcare",null,1);
                if(db.cheakCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addcart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(), "Record Inserted into Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(buyMedicine.this, buyMedicineDetails.class));
                }
            }
        });
    }
}