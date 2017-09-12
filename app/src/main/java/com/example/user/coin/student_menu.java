package com.example.user.coin;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.content.Intent;
import android.view.View;

public class student_menu extends AppCompatActivity {
    private final static String preferences_name = "preferences";
    SharedPreferences shared;
    SQLLite_StudentDataHelper helper;
    TextView id;
    ImageView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        setTitle("學生_主畫面");
        shared=  getSharedPreferences(preferences_name,MODE_PRIVATE);
        findView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String account = shared.getString("account","0");

        if(helper == null){
            helper = new SQLLite_StudentDataHelper(this);
        }

        student_data data = helper.findById_data(account);
        id.setText(data.getAccount());
        Bitmap image = BitmapFactory.decodeByteArray(data.getImage(), 0,
                data.getImage().length);
        v.setImageBitmap(image);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(helper != null){
            helper.close();
        }
    }


    //botton 主選單傳送
    public void onClick(View view){
        Intent intent = new Intent();

        switch (view.getId()){
            case R.id.stu1:
                intent.setClass(student_menu.this ,all_jobs.class);
                break;
            case R.id.stu2:
                intent.setClass(student_menu.this ,student_maps.class);
                break;
            case R.id.stu3:
                intent.setClass(student_menu.this ,student_case.class);
                break;
            case R.id.stu4:
                intent.setClass(student_menu.this ,student_personal.class);
                break;
            case R.id.stu5:
                intent.setClass(student_menu.this ,student_group.class);
                break;
            case R.id.stu6:
                intent.setClass(student_menu.this ,student_evaulate.class);
                break;
        }
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

    public void findView(){
        id = (TextView) findViewById(R.id.id);
        v = (ImageView) findViewById(R.id.img);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stu_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences shared =  getSharedPreferences(preferences_name,MODE_PRIVATE);
        final int id = item.getItemId();
        switch (id){
            case R.id.logout:
                shared.edit().remove("account").apply();
                Intent intent = new Intent();
                intent.setClass(student_menu.this ,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.manage:


                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
