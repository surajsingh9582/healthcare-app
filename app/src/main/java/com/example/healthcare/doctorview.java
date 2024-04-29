package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class doctorview extends AppCompatActivity {
    private String[][] doctorDetail={
            {"Doctor Name : Suraj Singh","Hospital Address : Delhi","Exp : 10yrs","Mobile No. : 9898989898","900"},
            {"Doctor Name : Vishal Kumar","Hospital Address : Delhi","Exp : 1yrs","Mobile No. : 6565656566","200"},
            {"Doctor Name : Shashank Tiwari","Hospital Address : Jhasi","Exp : 5yrs","Mobile No. : 7474747474","500"},
            {"Doctor Name : Harshikesh Kumar","Hospital Address : Patna","Exp : 7yrs","Mobile No. : 9748989898","800"},
            {"Doctor Name : Suraj Singh","Hospital Address : Agra","Exp : 5yrs","Mobile No. : 989874521","400"},
    };
    private String[][] doctorDetail1={
            {"Doctor Name : Suraj Singh","Hospital Address : Delhi","Exp : 10yrs","Mobile No. : 9898989898","900"},
            {"Doctor Name : Vishal Kumar","Hospital Address : Delhi","Exp : 1yrs","Mobile No. : 6565656566","200"},
            {"Doctor Name : Shashank Tiwari","Hospital Address : Jhasi","Exp : 5yrs","Mobile No. : 7474747474","500"},
            {"Doctor Name : Harshikesh Kumar","Hospital Address : Patna","Exp : 7yrs","Mobile No. : 9748989898","800"},
            {"Doctor Name : Suraj Singh","Hospital Address : Agra","Exp : 5yrs","Mobile No. : 989874521","400"},
    };
    private String[][] doctorDetail2={
            {"Doctor Name : Suraj Singh","Hospital Address : Delhi","Exp : 10yrs","Mobile No. : 9898989898","900"},
            {"Doctor Name : Vishal Kumar","Hospital Address : Delhi","Exp : 1yrs","Mobile No. : 6565656566","200"},
            {"Doctor Name : Shashank Tiwari","Hospital Address : Jhasi","Exp : 5yrs","Mobile No. : 7474747474","500"},
            {"Doctor Name : Harshikesh Kumar","Hospital Address : Patna","Exp : 7yrs","Mobile No. : 9748989898","800"},
            {"Doctor Name : Suraj Singh","Hospital Address : Agra","Exp : 5yrs","Mobile No. : 989874521","400"},
    };
    private String[][] doctorDetail3={
            {"Doctor Name : Suraj Singh","Hospital Address : Delhi","Exp : 10yrs","Mobile No. : 9898989898","900"},
            {"Doctor Name : Vishal Kumar","Hospital Address : Delhi","Exp : 1yrs","Mobile No. : 6565656566","200"},
            {"Doctor Name : Shashank Tiwari","Hospital Address : Jhasi","Exp : 5yrs","Mobile No. : 7474747474","500"},
            {"Doctor Name : Harshikesh Kumar","Hospital Address : Patna","Exp : 7yrs","Mobile No. : 9748989898","800"},
            {"Doctor Name : Suraj Singh","Hospital Address : Agra","Exp : 5yrs","Mobile No. : 989874521","400"},
    };
    private String[][] doctorDetail4={
            {"Doctor Name : Suraj Singh","Hospital Address : Delhi","Exp : 10yrs","Mobile No. : 9898989898","900"},
            {"Doctor Name : Vishal Kumar","Hospital Address : Delhi","Exp : 1yrs","Mobile No. : 6565656566","200"},
            {"Doctor Name : Shashank Tiwari","Hospital Address : Jhasi","Exp : 5yrs","Mobile No. : 7474747474","500"},
            {"Doctor Name : Harshikesh Kumar","Hospital Address : Patna","Exp : 7yrs","Mobile No. : 9748989898","800"},
            {"Doctor Name : Suraj Singh","Hospital Address : Agra","Exp : 5yrs","Mobile No. : 989874521","400"},
    };
TextView tv;
Button btn;
String[][] doctorDetails={};
ArrayList e;
HashMap<String,String> item;
SimpleAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorview);
        tv=findViewById(R.id.textViewddTitle);
        btn=findViewById(R.id.button1labback);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicans")==0){
            doctorDetails=doctorDetail;
        }
        else if(title.compareTo("DIETICIAN")==0){
            doctorDetails=doctorDetail1;
        }
        else if(title.compareTo("DENTIST")==0){
            doctorDetails=doctorDetail2;
        } else if (title.compareTo("SURGEON")==0) {
            doctorDetails=doctorDetail3;
        }
        else{
            doctorDetails=doctorDetail4;
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(doctorview.this, doctorActivity.class));
            }
        });
        e=new ArrayList();
        for(int i=0;i<doctorDetails.length;i++){
            item=new HashMap<String,String>();
            item.put("Line1",doctorDetails[i][0]);
            item.put("Line2",doctorDetails[i][1]);
            item.put("Line3",doctorDetails[i][2]);
            item.put("Line4",doctorDetails[i][3]);
            item.put("Line5","Cons Fees"+doctorDetails[i][4]+"/-");
            e.add(item);
        }
        ad=new SimpleAdapter(this,e,R.layout.multi,new String[]{"Line1","Line2","Line3","Line4","Line5"},
                new int[]{R.id.textViewdd1,R.id.textViewdd2,R.id.lineView3,R.id.lineView4,R.id.lineView5});

        ListView lst=findViewById(R.id.listlabview);
        lst.setAdapter(ad);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent ij=new Intent(doctorview.this, appointment.class);
                ij.putExtra("text1",title);
                ij.putExtra("text2",doctorDetails[i][0]);
                ij.putExtra("text3",doctorDetails[i][1]);
                ij.putExtra("text4",doctorDetails[i][3]);
                ij.putExtra("text5",doctorDetails[i][4]);
                startActivity(ij);
            }
        });
    }
}