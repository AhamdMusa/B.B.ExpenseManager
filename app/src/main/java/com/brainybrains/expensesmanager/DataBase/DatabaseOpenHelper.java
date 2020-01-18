package com.brainybrains.expensesmanager.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Data.db";
    public static int VERSION = 1;
    public static String TABLE_NAME = "Data";
    public static String COL_ID = "Id";
    public static String COL_TYPE = "TransactionType";
    public static String COL_CATAGORY = "Catagory";
    public static String COL_AMOUNT = "Amount";           // DataType is int..?
    public static String COL_TIME = "Time";           // DataType is long..?
    public static String COL_DATE = "Date";           // DataType is long..?
    public static String COL_ICON = "Icon";           // DataType is int..?

    private  SQLiteDatabase database;

    public static  String create_table = "create table "+TABLE_NAME+
            " (Id integer primary key, TransactionType text, Catagory text, Amount integer, Time text, Date text, Icon integer)";
    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public void openDatabase(){
        database = getWritableDatabase();
    }

    public void closeDatabase(){
        database.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor getData(){
        openDatabase();
        String data = "Select * From "+TABLE_NAME;
        Cursor cursor = database.rawQuery(data,null);

        return cursor;

    }
    public void deleteData(int id){
        getWritableDatabase().delete(TABLE_NAME,"Id=?",new String[]{String.valueOf(id)});
    }

    public long addExpenses(String transactionType, String catagory,String amount,String time,String date,int icon ){

        openDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TYPE,transactionType);
        values.put(COL_CATAGORY,catagory);
        values.put(COL_AMOUNT,amount);
        values.put(COL_TIME,time);
        values.put(COL_DATE,date);
        values.put(COL_ICON,icon);
        long id = database.insert(TABLE_NAME,null,values);
        closeDatabase();
        return id;
    }
}