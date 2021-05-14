package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String dbname = "Bankdb";
    private static final int version= 1;

    public DatabaseHelper(Context context){
        super(context, dbname , null, version);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
       //table creation
       String sql = "CREATE TABLE USER_DETAILS (_id INTEGER , NAME TEXT, EMAIL VARCHAR PRIMARY KEY,BALANCE INTEGER,PHONENUMBER TEXT,ACCOUNT VARCHAR)";
       db.execSQL(sql);
       String sql_tr = "CREATE TABLE TRANSACT_DETAILS (transact_id INTEGER , FROMNAME TEXT, TONAME TEXT, AMOUNT INTEGER, STATUS TEXT)";
       db.execSQL(sql_tr);
       //to insert data
        insertData("Ansan","ansanantony@gmail.com",10000,"162165165","XXXXXXXXXXXX1234",db);
        insertData("Maria","akhila@gmail.com",150000,"6516516516","XXXXXXXXXXXX4321",db);
        insertData("Mahesh","bishh@hotmail.com",250000,"6541651665 ","XXXXXXXXXXXX6650",db);
        insertData("Vish","wanth@gmail.com",560000,"65163205495","XXXXXXXXXXXX0420",db);
        insertData("Deva","darshan@gmail.com",54000,"541320323","XXXXXXXXXXXX0690",db);
        insertData("Teddy","varadh@gmail.com",300000,"9846513202","XXXXXXXXXXXX3412",db);
        insertData("KP","shashitharoor@gmail.com",700000,"68495305620","XXXXXXXXXXXX8840",db);
        insertData("Shudu","Shrudu@gmail.com",850000,"65846520321","XXXXXXXXXXXX5816",db);
        insertData("Gokul","chandra@gmail.com",250000,"9846521546","XXXXXXXXXXXX9980",db);
        insertData("Alby","Alby@yahoo.com",450000,"62895165165","XXXXXXXXXXXX9133",db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER_DETAILS");
        db.execSQL("DROP TABLE IF EXISTS TRANSACT_DETAILS");
        onCreate(db);
    }
    public Cursor ReadAllData(){
        SQLiteDatabase db =getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USER_DETAILS",null);
        return cursor;
    }
    public Cursor readParticularData(String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "select * from USER_DETAILS where Email = '"+Email + "'";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }

    public Cursor ReadSelectedData(String Email){
        SQLiteDatabase db = getWritableDatabase();
        String select ="select * from USER_DETAILS where email <> '"+Email + "'";
        Cursor c = db.rawQuery(select,null);
        return c;
    }
    public void UpdateAmount(String Email,String amount){
        SQLiteDatabase db =this.getWritableDatabase();
        String select="UPDATE USER_DETAILS set BALANCE = '"+ amount +"' where Email = '"+Email+"'";
        db.execSQL(select);
    }

    private void insertData(String name, String email, int balance,String phoneNumber,String account,SQLiteDatabase database){
        ContentValues values= new ContentValues();
        values.put("NAME",name);
        values.put("EMAIL",email);
        values.put("PHONENUMBER",phoneNumber);
        values.put("ACCOUNT",account);
        values.put("BALANCE",balance);
        database.insert("USER_DETAILS",null,values);
    }
    public void insertTransferData(String from_name, String to_name, int amount, String Status){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("FROMNAME",from_name);
        values.put("TONAME",to_name);
        values.put("AMOUNT",amount);
        values.put("STATUS",Status);
        database.insert("TRANSACT_DETAILS",null,values);
    }


    public Cursor Read_Transfer_amount_Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "select * from TRANSACT_DETAILS";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }
}
