package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class orderMedicineTab extends AppCompatActivity {
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView tvtotal;
    private DatePickerDialog datePickerDialog;
    private Button dateButton,btnCheakout,btnBack;
    private String[][] packages={};
    ListView lst;
    TextView tot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_medicine_tab);
        lst=findViewById(R.id.multibuyMedcartmid2);
        tot=findViewById(R.id.textViewbuyans);
        dateButton=findViewById(R.id.buttonbuyAppdate);
        btnCheakout=findViewById(R.id.button1labcheakout);
        btnBack=findViewById(R.id.buttonbuycartback);
        tvtotal=findViewById(R.id.viewbuyMedcarttitle);

        SharedPreferences sharedPreferences=getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();

        dataBase db=new dataBase(getApplicationContext(),"healthcare",null,1);

        float totalAmount=0;
        ArrayList dbData=db.getCartData(username,"medicine");

        packages=new String[dbData.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }

        for(int i=0;i<dbData.size();i++){
            String arrDate=dbData.get(i).toString();
            String[] strData=arrDate.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost : "+strData[1]+"/-";
            totalAmount =totalAmount+Float.parseFloat(strData[1]);
        }

        tot.setText("Total Cost : "+totalAmount);
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);
        }
        sa= new SimpleAdapter(this,list,R.layout.multi,new String[]{"line1","line2","line3","line4","line5"},new int[]
                {R.id.textViewdd1,R.id.textViewdd2,R.id.lineView3,R.id.lineView4,R.id.lineView5});
        lst.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(orderMedicineTab.this, buyMedicineDetails.class));
            }
        });
        btnCheakout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(orderMedicineTab.this,medicineTestbook.class);
                it.putExtra("price",tot.getText());
                it.putExtra("date",dateButton.getText());
                startActivity(it);
            }
        });
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
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
}