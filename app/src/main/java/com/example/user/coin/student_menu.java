package com.example.user.coin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import android.view.View;

public class student_menu extends AppCompatActivity {
    Button job;
    Button map;
    Button casejob;
    Button group;
    Button person;
    Button eva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        setTitle("學生_主畫面");

        job = (Button)findViewById(R.id.stu1);
        job.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(student_menu.this ,all_jobs.class);
                startActivity(intent);
            }
        });

        map = (Button)findViewById(R.id.stu2);
        map.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(student_menu.this ,MapsActivity.class);
                startActivity(intent);
            }
        });

        casejob = (Button)findViewById(R.id.stu3);
       casejob.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(student_menu.this ,student_case.class);
                startActivity(intent);
            }
        });

       group = (Button)findViewById(R.id.stu4);
        group.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(student_menu.this ,student_group.class);
                startActivity(intent);
            }
        });


        person = (Button)findViewById(R.id.stu5);
        person.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(student_menu.this ,student_personal.class);
                startActivity(intent);
            }
        });






    }
}
