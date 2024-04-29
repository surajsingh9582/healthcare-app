package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registerActivity1 extends AppCompatActivity {
    EditText ed_Regusername,ed_Regemail,ed_Regpassword,ed_RegCpassword;
    Button button1;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        ed_Regusername=findViewById(R.id.edlabfullname);
        ed_Regemail=findViewById(R.id.edlabcontact);
        ed_Regpassword=findViewById(R.id.edlabaddress);
        ed_RegCpassword=findViewById(R.id.edlabfees);
        button1=findViewById(R.id.labbutton14);
        textView3=findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerActivity1.this,loginActivity.class));
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=ed_Regusername.getText().toString();
                String password=ed_Regpassword.getText().toString();
                String confirm=ed_RegCpassword.getText().toString();
                String email=ed_Regemail.getText().toString();
                dataBase db=new dataBase(getApplicationContext(),"healthcare",null,1);
                if(password.length()==0 || username.length()==0 || confirm.length()==0 || email.length()==0){
                    Toast.makeText(registerActivity1.this, "Fill all Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(confirm)==0){
                        if(isValid(password)){
                            db.register(username,password,email);
                            Toast.makeText(registerActivity1.this, "Data Recorded", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(registerActivity1.this,loginActivity.class));
                        }
                        else{
                            Toast.makeText(registerActivity1.this, "Password does not contain 8 Character, having letter,having digit , any special Character", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(registerActivity1.this, "Password and confirm Password didn't Match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public static boolean isValid(String password){
        int f1=0,f2=0,f3=0;
        if(password.length()<8){
            return false;
        }
        else{
            for(int i=0;i<password.length();i++){
                if(Character.isDigit(password.charAt(i))){
                    f1=1;
                }
            }
            for(int j=0;j<password.length();j++){
                if(Character.isLetter(password.charAt(j))){
                    f2=1;
                }
            }
            for(int k=0;k<password.length();k++){
                char p=password.charAt(k);
                if(p>=33 && p<=46 || p==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1){
                return true;
            }
            return false;
        }
    }
}