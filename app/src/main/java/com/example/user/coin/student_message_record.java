package com.example.user.coin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import static android.graphics.Color.argb;

public class student_message_record extends AppCompatActivity {
    private final static String preferences_name = "preferences";
    Button chat,video,phone;
    private SQLLite_ChatDataHelper helper;
    private SharedPreferences shared;
    private String account;
    ListView list ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_message_record);
        setTitle("聯絡紀錄");
        shared = getSharedPreferences(preferences_name,MODE_PRIVATE);
        account =shared.getString("account","0");
        findView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(helper == null){
            helper = new SQLLite_ChatDataHelper(this);
        }
        list.setTextFilterEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(helper != null){
            helper.close();
        }
    }




    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.phone:

                changeBtnColorW(phone);
                changeBtnColorB(video);
                changeBtnColorB(chat);
                break;
            case R.id.chat:
                intent.setClass(student_message_record.this,message_content.class);
                startActivity(intent);
                changeBtnColorW(chat);
                changeBtnColorB(video);
                changeBtnColorB(phone);
                break;
            case R.id.video:

                changeBtnColorB(phone);
                changeBtnColorW(video);
                changeBtnColorB(chat);
                break;


        }

    }





    void findView(){
        chat = (Button)findViewById(R.id.chat);
        video =  (Button)findViewById(R.id.video);
        phone =  (Button)findViewById(R.id.phone);
        list = (ListView)findViewById(R.id.listview);
    }

    public void changeBtnColorW(Button btn){
        btn.setBackgroundColor(argb(255,255,255,255));
        btn.setTextColor(argb(255,0,0,0));
    }

    public void changeBtnColorB(Button btn){
        btn.setBackgroundColor(argb(255,0,0,0));
        btn.setTextColor(argb(255,255,255,255));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
                intent.setClass(student_message_record.this ,MainActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
