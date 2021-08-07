package com.saggy.wielearner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class Scoredb extends SQLiteOpenHelper {
    private static final String db_name = "score.db";

    public Scoredb(@Nullable Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "create table scoredb(subjectcode text, topic text, score text)";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists scoredb");
        onCreate(db);
    }

    public boolean insert_data(String subjectcode , String topic , String score){
        SQLiteDatabase g = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("subjectcode",subjectcode);
        c.put("topic",topic);
        c.put("score",score);
        long r = g.insert("scoredb", null , c);
        return r != -1;
    }

    public String getScore(String subjectcode, String topic){
        SQLiteDatabase g = this.getReadableDatabase();

        String query = "select * from scoredb where subjectcode=? and topic=?";
        Cursor cursor = g.rawQuery(query, new String[]{subjectcode,topic});
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getString(2);
        }
        else{
            return "noscore";
        }
    }
    public boolean updatescore(String subjectcode, String topic , String score){
        SQLiteDatabase g = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("score",score);
        Cursor cursor = g.rawQuery("select * from scoredb where subjectcode=? and topic=?",new String[]{subjectcode, topic});
        if(cursor.getCount() >0){
            long r = g.update("scoredb", c, "subjectcode=? and topic=?",new String[]{subjectcode, topic});
            return r != -1;
        }
        return false;
    }
}
