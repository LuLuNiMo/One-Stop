package com.example.user.coin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/11/5.
 */

public class SQLLite_ChatDataHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ChatAllData";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Person_Chat_data";
    private static final String TABLE_NAME2="Person_Chat_content";


    private static final String COL_id = "id";
    private static final String COL_cha = "chat_a";
    private static final String COL_chb = "chat_b";
    private static final String COL_imga = "img_a";
    private static final String COL_imgb = "img_b";
    private static final String COL_d = "date";
    private static final String COL_n = "nature";




    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_cha + " TEXT," +
                    COL_chb + " TEXT," +
                    COL_imga + " BLOB," +
                    COL_imgb + " BLOB," +
                    COL_d + " TEXT," +
                    COL_n + " TEXT);";


    public SQLLite_ChatDataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(TABLE_CREATE);
        }catch (Exception ex){
            Log.v("DB create fail.", Log.getStackTraceString(ex));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }catch (Exception ex){
            Log.v("DB Upgrade fail.", Log.getStackTraceString(ex));
        }
        onCreate(db);
    }


    //.............................................................Person_Chat_data.......................................................................


    public long insert_data(Chat_pername data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_cha,data.getChat_a());
        values.put(COL_chb,data.getChat_b());
        values.put(COL_imga, data.getImg_a());
        values.put(COL_imgb, data.getImg_b());
        values.put(COL_d, data.getDate());
        values.put(COL_n,data.getNature());

        return db.insert(TABLE_NAME, null, values);
    }

    public List<Chat_pername> getAll_Cha_data() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_id,COL_cha,COL_chb,COL_imga,COL_imgb,COL_d,COL_n
        };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,
                null);

        List<Chat_pername> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String cha = cursor.getString(1);
            String chb = cursor.getString(2);
            byte[] ima = cursor.getBlob(3);
            byte[] imb = cursor.getBlob(4);
            String date = cursor.getString(5);
            String na = cursor.getString(6);

            Chat_pername data = new Chat_pername(id,cha,chb,ima,imb,date,na);
            list.add(data);
        }
        cursor.close();
        return list;
    }


    public Chat_pername findById_data(int idd) {
        SQLiteDatabase db = getWritableDatabase();

        String[] columns = {
                COL_id,COL_cha,COL_chb,COL_imga,COL_imgb,COL_d,COL_n
        };
        String selection = COL_id + " = ?;";
        String[] selectionArgs = {String.valueOf(idd)};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);
        Chat_pername data = null;
        if (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String cha = cursor.getString(1);
            String chb = cursor.getString(2);
            byte[] ima = cursor.getBlob(3);
            byte[] imb = cursor.getBlob(4);
            String date = cursor.getString(5);
            String na = cursor.getString(6);

            data = new Chat_pername(id,cha,chb,ima,imb,date,na);
        }
        cursor.close();
        return data;
    }


    public int update_data(Chat_pername data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_cha,data.getChat_a());
        values.put(COL_chb,data.getChat_b());
        values.put(COL_imga, data.getImg_a());
        values.put(COL_imgb, data.getImg_b());
        values.put(COL_d, data.getDate());
        values.put(COL_n,data.getNature());


        String whereClause = COL_id + " = ?;";
        String[] whereArgs = {String.valueOf(data.getId())};
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int deleteById_data(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause =COL_id + " = ?;";
        String[] whereArgs = {String.valueOf(id)};
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public int update_data_date(int id,String date){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_d,date);

        String whereClause = COL_id + " = ?;";
        String[] whereArgs = {String.valueOf(id)};

        return db.update(TABLE_NAME, values, whereClause, whereArgs);

    }

    //..................................................Person_Chat_content..............................................................................






}
