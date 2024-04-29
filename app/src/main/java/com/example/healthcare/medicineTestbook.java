package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class medicineTestbook extends AppCompatActivity {
    EditText name,address,pin,contact;
    Button book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_testbook);

        name=findViewById(R.id.edmedfullname);
        address=findViewById(R.id.edmedcontact);
        pin=findViewById(R.id.edmedaddress);
        contact=findViewById(R.id.edmedfees);
        book=findViewById(R.id.medbutton14);
        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length()==0 && address.length()==0 && pin.length()==0 && contact.length()==0){
                    Toast.makeText(medicineTestbook.this, "Fill all Details", Toast.LENGTH_SHORT).show();

                }
                else{
                    SharedPreferences sharedPreferences=getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
                    String username=sharedPreferences.getString("username","").toString();
                    dataBase db=new dataBase(getApplicationContext(),"healthcare",null,1);
                    db.addOrder(username,name.getText().toString(),address.getText().toString(),contact.getText().toString(),Integer.parseInt(pin.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                    db.removeCart(username,"lab");
                    Toast.makeText(getApplicationContext(), "Your Booking is Successfully done", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(medicineTestbook.this, homeActivity.class));
                }}
        });
    }
}