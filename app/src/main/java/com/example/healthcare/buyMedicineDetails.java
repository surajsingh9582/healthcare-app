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

public class buyMedicineDetails extends AppCompatActivity {
    private String[][] packages={
        {"Uprise-D3 1000IU Capsule","","","","50"},
            {"5-Amino salicylic Acid (5-ASA)","","","","50"},
            {"Amoxicillin+Clavulinic acid","","","","40"},
            {"Atorvastatin","","","","44"},
            {"Betamethasone","","","","23"},
            {"Chlorambucil","","","","52"},
            {"Hydroxychloroquine phosphate","","","","20"},
            {"Ipratropium bromide","","","","25"},
            {"Zidovudine+Lamivudine+Nevirapine","","","","15"}

    };
    private String[] packages_details={"Treatment of Osteoporosis\n"+"Osteomalacia (Rickets)\n"+"Hypoparathyroidism and Latent tetany",
            "Mesalamine, also known as 5-aminosalicylic acid (5-ASA), is a medication used to treat ulcerative colitis.\n"+
            "It is usually used to induce or maintain remission of mildly to moderately active ulcerative colitis.",
            "Amoxicillin and clavulanate combination is used to treat bacterial infections in many different parts of the body (eg, ear, lungs, sinus, skin, urinary tract).",
            "Atorvastatin is used together with a proper diet to lower cholesterol and triglyceride (fats) levels in the blood.",
            "to help relieve redness, itching, swelling, or other discomforts caused by certain skin conditions.",
            "used treat a certain type of chronic lymphocytic leukemia",
            "used to treat discoid lupus erythematosus (DLE) or systemic lupus erythematosus (SLE or lupus).",
            "used to help control the symptoms of lung diseases, such as asthma, chronic bronchitis, and emphysema.",
            "to treat HIV/AIDS"

    };
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    Button BmBack,BmgotoCart;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details2);
        lst=findViewById(R.id.listBM);
        BmBack=findViewById(R.id.buttonBMbutton);
        BmgotoCart=findViewById(R.id.buttonBmbutton2);

        BmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buyMedicineDetails.this, homeActivity.class
                ));
            }
        });
        list=new ArrayList<>();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost"+packages[i][4]);
            list.add(item);
        }
        sa= new SimpleAdapter(this,list,R.layout.multi,new String[]{"line1","line2","line3","line4","line5"},new int[]
                {R.id.textViewdd1,R.id.textViewdd2,R.id.lineView3,R.id.lineView4,R.id.lineView5});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(buyMedicineDetails.this,buyMedicine.class);
                it.putExtra("text1",packages[position][0]);
                it.putExtra("text2",packages_details[position]);
                it.putExtra("text3",packages[position][4]);
                startActivity(it);
            }
        });
        BmgotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buyMedicineDetails.this,orderMedicineTab.class));
            }
        });
    }
}