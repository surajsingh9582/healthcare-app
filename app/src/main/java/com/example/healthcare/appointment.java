package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class appointment extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,timeButton;
    Button buttonregister,buttonback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        tv=findViewById(R.id.textViewddhead);
        ed1=findViewById(R.id.edlabfullname);
        ed2=findViewById(R.id.edlabcontact);
        ed3=findViewById(R.id.edlabaddress);
        ed4=findViewById(R.id.edlabfees);
        dateButton=findViewById(R.id.buttonlabApptime);
        timeButton=findViewById(R.id.buttonApp1);
        buttonregister=findViewById(R.id.buttonddd2);
        buttonback=findViewById(R.id.buttondd1);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent in=getIntent();
        String title=in.getStringExtra("text1");
        String name=in.getStringExtra("text2");
        String address=in.getStringExtra("text3");
        String contact=in.getStringExtra("text4");
        String fees=in.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(name);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees: "+fees+"/-");

        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(appointment.this,doctorActivity.class));
            }
        });
        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase db=new dataBase(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences=getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                if(db.cheakAppointment(username,title+" => "+name,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                    Toast.makeText(appointment.this, "Appointment Already Booked", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addOrder(username,title+"=>"+name,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(), Float.parseFloat(fees),"Appointment");
                    Toast.makeText(appointment.this, "Your Appointment is Booked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(appointment.this, homeActivity.class));
                }
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                dateButton.setText(year+"/"+month+"/"+dayOfMonth);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int date=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,date);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeButton.setText(hourOfDay+":"+minute);
            }
        };
        Calendar cal=Calendar.getInstance();
        int har=cal.get(Calendar.HOUR);
        int min=cal.get(Calendar.MINUTE);

        int style= AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,har,min,true);
    }
}