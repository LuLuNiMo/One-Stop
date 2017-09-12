package com.example.user.coin;

/**
 * Created by user on 2017/8/25.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLLite_StudentDataHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "StudentAllData";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Student_data";
    private static final String TABLE_NAME2="Case_data";

    //student_data column
    private static final String COL_id = "id";
    private static final String COL_account = "account";
    private static final String COL_pass = "password";
    private static final String COL_name = "name";
    private static final String COL_phone = "phone";
    private static final String COL_image = "image";
    private static final String COL_sch = "school";
    private static final String COL_dep = "department";
    private static final String COL_stuid = "stu_id";
    private static final String COL_sex = "sex";
    private static final String COL_age ="age";
    private static final String COL_mail = "mail";
    private static final String COL_spe = "speciality";
    private static final String COL_w = "wish";
    private static final String COL_h = "history";
    private static final String COL_ph = "photo";


    //case column
    private static final String COL_n = "nature";
    private static final String COL_t = "time";
    private static final String COL_p = "pay";
    private static final String COL_cond = "condition";
    private static final String COL_num = "num";
    private static final String COL_cont = "content";
    private static final String COL_a = "area";
    private static final String COL_s = "state";
    private static final String COL_ca= "catch";


    //建立Table 學生基本資料
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_account + " TEXT NOT NULL UNIQUE," +
                    COL_pass + " TEXT NOT NULL," +
                    COL_name + " TEXT," +
                    COL_phone + " TEXT," +
                    COL_image + " BLOB," +
                    COL_sch + " TEXT," +
                    COL_dep + " TEXT," +
                    COL_stuid + " TEXT," +
                    COL_sex + " TEXT," +
                    COL_age + " TEXT," +
                    COL_mail + " TEXT," +
                    COL_spe + " TEXT," +
                    COL_w + " TEXT," +
                    COL_h + " TEXT," +
                    COL_ph + " BLOB);";

    //建立Table case基本資料
    private static final String TABLE_CREATE2 =
            "CREATE TABLE " + TABLE_NAME2 + " ( " +
                    COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_name + " TEXT NOT NULL," +
                    COL_n + " TEXT," +
                    COL_t + " TEXT," +
                    COL_p + " TEXT," +
                    COL_cond + " TEXT," +
                    COL_num + " INTEGER," +
                    COL_cont + " TEXT," +
                    COL_account + " TEXT," +
                    COL_a + " TEXT," +
                    COL_s + " TEXT," +
                    COL_ca + " TEXT);";




    //指定連結資料庫DB_NAME
    public SQLLite_StudentDataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //建立資料表，當DB第一次建立時
    @Override
    public void onCreate(SQLiteDatabase db) {

      try{
          db.execSQL(TABLE_CREATE2);
          db.execSQL(TABLE_CREATE);
        }catch (Exception ex){
          Log.v("DB create fail.", Log.getStackTraceString(ex));
        }

    }

    //DB更新時，刪除已經存在Table並呼叫onCreate()重建資料表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        }catch (Exception ex){
            Log.v("DB Upgrade fail.", Log.getStackTraceString(ex));
        }

        onCreate(db);
    }

 //..............................................................student_data.......................................................................

    public long insert_data(student_data data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_account,data.getAccount());
        values.put(COL_pass,data.getPassword());
        values.put(COL_name, data.getName());
        values.put(COL_phone, data.getPhone());
        values.put(COL_image, data.getImage());
        values.put(COL_sch,data.getSchool());
        values.put(COL_dep,data.getDepartment());
        values.put(COL_stuid,data.getStu_id());
        values.put(COL_sex,data.getSex());
        values.put(COL_age,data.getAge());
        values.put(COL_mail,data.getMail());
        values.put(COL_spe,data.getSpeciality());
        values.put(COL_w,data.getWish());
        values.put(COL_h,data.getHistory());
        values.put(COL_ph,data.getPhoto());

        return db.insert(TABLE_NAME, null, values);
    }

    public List<student_data> getAllStu() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_id, COL_account, COL_pass, COL_name,COL_phone, COL_image ,COL_sch,COL_dep,COL_stuid
                ,COL_sex,COL_age,COL_mail,COL_spe,COL_w,COL_h,COL_ph
        };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,
                null);

        List<student_data> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String acc = cursor.getString(1);
            String pass = cursor.getString(2);
            String name = cursor.getString(3);
            String phone = cursor.getString(4);
            byte[] image = cursor.getBlob(5);
            String sch = cursor.getString(6);
            String department = cursor.getString(7);
            String stuid = cursor.getString(8);
            String sex = cursor.getString(9);
            String age = cursor.getString(10);
            String mail = cursor.getString(11);
            String spe = cursor.getString(12);
            String w = cursor.getString(13);
            String h = cursor.getString(14);
            byte[] ph = cursor.getBlob(15);

            student_data data = new student_data(id, acc,pass,name, phone,image,sch,department,stuid,sex,age,mail,spe,w,h,ph);
            list.add(data);
        }
        cursor.close();
        return list;
    }

    public student_data findById_data(String search) {
        SQLiteDatabase db = getWritableDatabase();

        String[] columns = {
                COL_id, COL_account, COL_pass, COL_name,COL_phone, COL_image ,COL_sch,COL_dep,COL_stuid
                ,COL_sex,COL_age,COL_mail,COL_spe,COL_w,COL_h,COL_ph
        };
        String selection = COL_account + " = ?;";
        String[] selectionArgs = {search};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);
        student_data data = null;
        if (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String acc = cursor.getString(1);
            String pass = cursor.getString(2);
            String name = cursor.getString(3);
            String phone = cursor.getString(4);
            byte[] image = cursor.getBlob(5);
            String sch = cursor.getString(6);
            String department = cursor.getString(7);
            String stuid = cursor.getString(8);
            String sex = cursor.getString(9);
            String age = cursor.getString(10);
            String mail = cursor.getString(11);
            String spe = cursor.getString(12);
            String w = cursor.getString(13);
            String h = cursor.getString(14);
            byte[] photo = cursor.getBlob(15);
            data = new student_data(id, acc,pass,name, phone,image,sch,department,stuid,sex,age,mail,spe,w,h,photo);
        }
        cursor.close();
        return data;
    }

    public int update_data(student_data data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_account,data.getAccount());
        values.put(COL_pass,data.getPassword());
        values.put(COL_name, data.getName());
        values.put(COL_phone, data.getPhone());
        values.put(COL_image, data.getImage());
        values.put(COL_sch,data.getSchool());
        values.put(COL_dep,data.getDepartment());
        values.put(COL_stuid,data.getStu_id());
        values.put(COL_sex,data.getSex());
        values.put(COL_age,data.getAge());
        values.put(COL_mail,data.getMail());
        values.put(COL_spe,data.getSpeciality());
        values.put(COL_w,data.getWish());
        values.put(COL_h,data.getHistory());
        values.put(COL_ph,data.getPhoto());

        String whereClause = COL_account + " = ?;";
        String[] whereArgs = {data.getAccount()};
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int deleteById_data(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause =COL_account + " = ?;";
        String[] whereArgs = {id};
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }


    //..................................................student_case..............................................................................


    public long insert_case(case_item data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COL_name,data.getName());
        values.put(COL_n,data.getNature());
        values.put(COL_t,data.getTime());
        values.put(COL_p,data.getPay());
        values.put(COL_cond,data.getCondition());
        values.put(COL_num,data.getNum());
        values.put(COL_cont,data.getContent());
        values.put(COL_account,data.getAccount());
        values.put(COL_a,data.getArea());
        values.put(COL_s,data.getState());
        values.put(COL_ca,data.getAttach());

        return db.insert(TABLE_NAME2, null, values);
    }

    public List<case_item> getAllCase() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_id, COL_name, COL_n, COL_t,COL_p, COL_cond ,COL_num,COL_cont,COL_account
                ,COL_a,COL_s,COL_ca
        };
        Cursor cursor = db.query(TABLE_NAME2, columns, null, null, null, null,
                null);

        List<case_item> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String nature = cursor.getString(2);
            String time = cursor.getString(3);
            String pay = cursor.getString(4);
            String cond = cursor.getString(5);
            int num = cursor.getInt(6);
            String cont = cursor.getString(7);
            String acc = cursor.getString(8);
            String area = cursor.getString(9);
            String state = cursor.getString(10);
            String att = cursor.getString(11);


           case_item data = new case_item(id,name,nature,time, pay,cond,num,cont,acc,area,state,att);
            list.add(data);
        }
        cursor.close();
        return list;
    }

    public case_item findById_case(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String[] columns = {
                COL_id, COL_name, COL_n, COL_t,COL_p, COL_cond ,COL_num,COL_cont,COL_account
                ,COL_a,COL_s,COL_ca
        };
        String selection = COL_id + " = ?;";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME2, columns, selection, selectionArgs,
                null, null, null);
        case_item data = null;
        if (cursor.moveToNext()) {
            int no = cursor.getInt(0);
            String name = cursor.getString(1);
            String nature = cursor.getString(2);
            String time = cursor.getString(3);
            String pay = cursor.getString(4);
            String cond = cursor.getString(5);
            int num = cursor.getInt(6);
            String cont = cursor.getString(7);
            String acc = cursor.getString(8);
            String area = cursor.getString(9);
            String state = cursor.getString(10);
            String att = cursor.getString(11);


            data = new case_item(id,name,nature,time, pay,cond,num,cont,acc,area,state,att);
        }
        cursor.close();
        return data;
    }

    public int update_case(case_item data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_name,data.getName());
        values.put(COL_n,data.getNature());
        values.put(COL_t,data.getTime());
        values.put(COL_p,data.getPay());
        values.put(COL_cond,data.getCondition());
        values.put(COL_num,data.getNum());
        values.put(COL_cont,data.getContent());
        values.put(COL_account,data.getAccount());
        values.put(COL_a,data.getArea());
        values.put(COL_s,data.getState());
        values.put(COL_ca,data.getAttach());


        String whereClause = COL_id + " = ?;";
        String[] whereArgs = {String.valueOf(data.getId())};
        return db.update(TABLE_NAME2, values, whereClause, whereArgs);
    }

    public int deleteById_case(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause =COL_id + " = ?;";
        String[] whereArgs = {String.valueOf(id)};
        return db.delete(TABLE_NAME2, whereClause, whereArgs);
    }




}
