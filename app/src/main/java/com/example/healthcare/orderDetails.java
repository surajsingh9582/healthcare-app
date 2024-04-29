package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class orderDetails extends AppCompatActivity {
    private String[][] order={};

    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button order_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        lst=findViewById(R.id.ListVieworderdetails);
        order_back=findViewById(R.id.buttonorderDetails);

        order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(orderDetails.this, homeActivity.class));
            }
        });
        dataBase db=new dataBase(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sharedPreferences=getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();

        ArrayList dbData=db.getOrderDetails(username);

        order=new String[dbData.size()][];

        for(int i=0;i<order.length;i++){
            order[i]=new String[5];
            String arrData=dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            order[i][0]=strData[0];
            order[i][1]=strData[1];
            if(strData[7].compareTo("medicine")==0){
                order[i][3]="Del: "+strData[4];
            }else{
                order[i][3]="Del: "+strData[4]+" "+strData[5];
            }
            order[i][2]="Rs. "+strData[6];
            order[i][4]=strData[7];
        }
        list=new ArrayList();
        for(int i=0;i<order.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",order[i][0]);
            item.put("line2",order[i][1]);
            item.put("line3",order[i][2]);
            item.put("line4",order[i][3]);
            item.put("line5",order[i][4]);
            list.add(item);
        }
        sa= new SimpleAdapter(this,list,R.layout.multi,new String[]{"line1","line2","line3","line4","line5"},new int[]
                {R.id.textViewdd1,R.id.textViewdd2,R.id.lineView3,R.id.lineView4,R.id.lineView5});
        lst.setAdapter(sa);
    }
}