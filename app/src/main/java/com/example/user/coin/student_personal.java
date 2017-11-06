package com.example.user.coin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class student_personal extends AppCompatActivity {
    private final static String preferences_name = "preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_personal);
        setTitle("個人");

    }

    public void job_mge(View view){
        Intent intent = new Intent();
        intent.setClass(student_personal.this ,student_jobManagement.class);
        startActivity(intent);
    }

    public void resume_mge(View view){
        Intent intent = new Intent();
        intent.setClass(student_personal.this ,student_resumeManagement.class);
        startActivity(intent);
    }


    public void message_record(View v){
        Intent intent = new Intent();
        intent.setClass(student_personal.this ,student_message_record.class);
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
                intent.setClass(student_personal.this ,MainActivity.class);
                startActivity(intent);
                break;


        }

        return super.onOptionsItemSelected(item);
    }


}
