package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class labActivity extends AppCompatActivity {
    private String[][] packages ={
        {"Package 1 : Full Body Cheakup","","","","999"},
        {"Package 2 : Blood Glucose Fasting","","","","399"},
            {"Package 3 : COVID-19 Antibody - IgG","","","","499"},
            {"Package 4 : Thyroid Cheak","","","","599"},
            {"Package 5 : Immunity Cheak","","","","699"}
    };
    private String[] packages_details={
            "Blood Glucose Fasting\n"+"Complete Hemogram\n"+"HbA1c\n"+"Iron Studies\n"+"Kidney Function Test\n"
            +"LDH Lactate Dehydrogenase\n"+"Lipid Profile\n"+"Liver Function Test\n","Blood Glucose Fasting\n",
            "COVID-19 Antibody - IgG","Thyroid Profile-Total(T3,T4 & TSH Ultra-Sensitive)",
            "Complete Hemogram\n"+"CRP (C Reactive Protein) Quantitative, Serum\n"+"Iron Studies\n"+
                    "Kidney Function Test\n"+"Vitamin D Total-25 Hydroxy\n"+"Liver Function Test\n"+"Lipid Profile"
    };
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    Button btngoto,buttonBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
        btngoto=findViewById(R.id.buttonlab1gotocart2);
        buttonBack=findViewById(R.id.button1labback2);
        listView=findViewById(R.id.listlabview);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(labActivity.this,homeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("Line1",packages[i][0]);
            item.put("Line2",packages[i][1]);
            item.put("Line3",packages[i][2]);
            item.put("Line4",packages[i][3]);
            item.put("Line5","Total Cost"+packages[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi,new String[] {"Line1","Line2","Line3","Line4","Line5"},
                new int[]{R.id.textViewdd1,R.id.textViewdd2,R.id.lineView3,R.id.lineView4,R.id.lineView5});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(labActivity.this,Labtestview.class);
                it.putExtra("text1",packages[position][0]);
                it.putExtra("text2",packages_details[position]);
                it.putExtra("text3",packages[position][4]);
                startActivity(it);
            }
        });
        btngoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(labActivity.this,labcartview.class));
            }
        });
    }
}