package com.example.user.coin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import android.view.View;

public class student_menu extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        setTitle("學生_主畫面");

        button = (Button)findViewById(R.id.stu_job);
        Button nextpagebtn = (Button)findViewById(R.id.stu_job);
        nextpagebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(student_menu.this ,all_jobs.class);
                startActivity(intent);
            }
        });

        button = (Button)findViewById(R.id.stu_person);
         nextpagebtn = (Button)findViewById(R.id.stu_person);
        nextpagebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(student_menu.this ,student_personal.class);
                startActivity(intent);
            }
        });

        button = (Button)findViewById(R.id.stu_map);
        nextpagebtn = (Button)findViewById(R.id.stu_map);
        nextpagebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(student_menu.this ,MapsActivity.class);
                startActivity(intent);
            }
        });


    }
}
