
package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
EditText ed_username,ed_password;
Button button1;
TextView textView3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_username=findViewById(R.id.ed_username);
        ed_password=findViewById(R.id.ed_password);
        button1=findViewById(R.id.buttonstartLogin);
        textView3=findViewById(R.id.textView3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=ed_username.getText().toString();
                String password=ed_password.getText().toString();
                dataBase db=new dataBase(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(loginActivity.this, "Please Fill Username and Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(db.login(username,password)==1){
                        Toast.makeText(loginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences SharedPreferences=getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=SharedPreferences.edit();
                        editor.putString("username",username);
                        editor.apply();
                        startActivity(new Intent(loginActivity.this,homeActivity.class));
                    }
                    else{
                        Toast.makeText(loginActivity.this, "username and password are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
             }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,registerActivity1.class));
            }
        });
    }
}