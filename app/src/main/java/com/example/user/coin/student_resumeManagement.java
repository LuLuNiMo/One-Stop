package com.example.user.coin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class student_resumeManagement extends AppCompatActivity {
    private final static String preferences_name = "preferences";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_resume_management);
        setTitle("履歷管理");


    }


    //button onclick event
    public void resume_loading(View view){
        Intent intent = new Intent();
        intent.setClass(student_resumeManagement.this ,student_resume_loading.class);
        startActivity(intent);
    }

    //button onclick event
    public void resume_version(View view){
        Intent intent = new Intent();
        intent.setClass(student_resumeManagement.this ,student_resume_version.class);
        startActivity(intent);
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
                intent.setClass(student_resumeManagement.this ,MainActivity.class);
                startActivity(intent);
                break;


        }

        return super.onOptionsItemSelected(item);
    }



}
