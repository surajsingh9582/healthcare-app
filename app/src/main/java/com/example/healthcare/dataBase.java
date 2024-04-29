package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dataBase extends SQLiteOpenHelper {
    public dataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qrl="create table users(username text, email text, password text)";
        db.execSQL(qrl);

        String qrl2="create table cart(username text,product text,price float ,otype text)";
        db.execSQL(qrl2);

        String qrl3="create table orderplace(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        db.execSQL(qrl3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String username,String password,String email){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase dl= getWritableDatabase();
        dl.insert("users",null,cv);
        dl.close();
    }
    public int login(String username,String password){
        int result=0;
        String a[]=new String[2];
        a[0]=username;
        a[1]=password;
        SQLiteDatabase dl=getReadableDatabase();
        Cursor c=dl.rawQuery("select * from users where username=? and password=?",a);
        if(c.moveToFirst()){
            result=1;
        }
        dl.close();
        return result;
    }
    public void addcart(String username,String product,float price,String otype){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase dl= getWritableDatabase();
        dl.insert("cart",null,cv);
        dl.close();
    }
    public int cheakCart(String username,String product){
        int result=0;
        String[] a =new String[2];
        a[0]=username;
        a[1]=product;
        SQLiteDatabase dl=getReadableDatabase();
        Cursor c=dl.rawQuery("select * from cart where username=? and product=?",a);
        if(c.moveToFirst()){
            result=1;
        }
        dl.close();
        return result;
    }
    public void removeCart(String username,String otype){
        String str[]=new String[2];
        str[0]=username;
        str[1]=otype;
        SQLiteDatabase db=getWritableDatabase();
        db.delete("cart","username=? and otype=?",str);
        db.close();
    }
    public ArrayList getCartData(String username,String otype){
        ArrayList<String>ar=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String[] str=new String[2];
        str[0] =username;
        str[1]=otype;
        Cursor c=db.rawQuery("select * from cart where username=? and otype=?",str);
        if(c.moveToFirst()){
            do{
                String product=c.getString(1);
                String price=c.getString(2);
                ar.add(product+"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return ar;
    }
    public void addOrder(String username,String fullname,String address,String contact,int pincode,String date,String time,Float price,String otype){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contactno",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }
    public ArrayList getOrderDetails(String username){
        ArrayList<String> arr=new ArrayList<>();
        String[] str=new String[1];
        str[0]=username;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from orderplace where username=?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }
    public int cheakAppointment(String username,String fullname,String address,String contact,String date,String time) {
        int result = 0;
        String[] str = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username=? and fullname=? and address=? and contactno=? and date=? and time=?", str);
        if(c.moveToFirst()){
            result=1;

        }
        db.close();
        c.close();
        return result;
    }}
