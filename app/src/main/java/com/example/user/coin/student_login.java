package com.example.user.coin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class student_login extends AppCompatActivity {

    private Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        setTitle("註冊");

        enter = (Button)findViewById(R.id.stulog_enter);

       enter.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               Intent intent = new Intent();
               intent.setClass(student_login.this ,student_menu.class);
               startActivity(intent);
           }
       });




    }
}
