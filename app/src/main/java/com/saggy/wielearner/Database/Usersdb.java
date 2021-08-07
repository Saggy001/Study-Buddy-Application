package com.saggy.wielearner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.saggy.wielearner.Model.UserInfoModel;

public class Usersdb extends SQLiteOpenHelper {
    private static final String db_name = "userinfo.db";
    public Usersdb(@Nullable Context context) {
        super(context, db_name , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "create table userinfo (username text Primary key, board text, course text," +
                " sub1 text, sub2 text, sub3 text, sub4 text, sub5 text, sub6 text)";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userinfo");
        onCreate(db);
    }

    public boolean insert_data(String username , String board , String course , String sub1, String sub2, String sub3,
                               String sub4, String sub5, String sub6){
        SQLiteDatabase g = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("username",username);
        c.put("board",board);
        c.put("course",course);
        c.put("sub1",sub1);
        c.put("sub2",sub2);
        c.put("sub3",sub3);
        c.put("sub4",sub4);
        c.put("sub5",sub5);
        c.put("sub6",sub6);

        long r = g.insert("userinfo", null , c);
        return r != -1;
    }

    public boolean delete_data(String username){
        SQLiteDatabase g = this.getWritableDatabase();
        Cursor cursor = g.rawQuery("select * from userinfo where username=?", new String[]{username});
        if(cursor.getCount() > 0){
            long r = g.delete("userinfo", "username=?" , new String[]{username});
            return r != -1;
        }
        else
            return false;
    }

    public UserInfoModel getuserdetails(String username){
        UserInfoModel userdetail = new UserInfoModel();
        SQLiteDatabase g = this.getReadableDatabase();

        String query = "select * from userinfo where username=?";
        Cursor cursor = g.rawQuery(query, new String[]{username});
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            userdetail.setUsername(username);
            userdetail.setBoard(cursor.getString(1));
            userdetail.setCourse(cursor.getString(2));
            userdetail.setSub1(cursor.getString(3));
            userdetail.setSub2(cursor.getString(4));
            userdetail.setSub3(cursor.getString(5));
            userdetail.setSub4(cursor.getString(6));
            userdetail.setSub5(cursor.getString(7));
            userdetail.setSub6(cursor.getString(8));
            return userdetail;
        }
        else{
            userdetail.setUsername("DataNotFound");
            return userdetail;
        }
    }

    public boolean updatesubjects(String username, String sub1 , String sub2, String sub3,
                                  String sub4, String sub5 , String sub6){
        SQLiteDatabase g = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("sub1",sub1);
        c.put("sub2",sub2);
        c.put("sub3",sub3);
        c.put("sub4",sub4);
        c.put("sub5",sub5);
        c.put("sub6",sub6);
        Cursor cursor = g.rawQuery("select * from userinfo where username=?",new String[]{username});
        if(cursor.getCount() >0){
            long r = g.update("userinfo", c, "username =?",new String[]{username});
            return r != -1;

        }
        return false;
    }
}
