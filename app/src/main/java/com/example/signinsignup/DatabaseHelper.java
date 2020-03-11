package com.example.signinsignup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_Name ="register.db";
    public static final String Table_Name = "registeruser";
    public static final String Col_1 ="ID";
    public static final String Col_2 ="username";
    public static final String Col_3 ="password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE registerUser ( ID INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);

    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return res;

    }

    public boolean checkUser(String username, String password){
        String[] coloumns = {Col_1};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = Col_2 + "=?"+" and "+Col_3+ "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(Table_Name,coloumns,selection,selectionArgs,null,null,null);

        int count = cursor.getCount();
        cursor.close();
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}
