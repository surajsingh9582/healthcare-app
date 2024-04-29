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

public class labTestBook extends AppCompatActivity {
    EditText name,address,pin,contact;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        name=findViewById(R.id.edlabfullname);
        address=findViewById(R.id.edlabcontact);
        pin=findViewById(R.id.edlabaddress);
        contact=findViewById(R.id.edlabfees);
        book=findViewById(R.id.medbutton14);
        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length()==0 && address.length()==0 && pin.length()==0 && contact.length()==0){
                    Toast.makeText(labTestBook.this, "Fill all Details", Toast.LENGTH_SHORT).show();
                }
                else{
                SharedPreferences sharedPreferences=getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                dataBase db=new dataBase(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,name.getText().toString(),address.getText().toString(),contact.getText().toString(),Integer.parseInt(pin.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");
                Toast.makeText(getApplicationContext(), "Your Booking is Successfully done", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(labTestBook.this, homeActivity.class));
            }}
        });
    }
}