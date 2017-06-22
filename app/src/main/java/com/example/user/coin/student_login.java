package com.example.user.coin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class student_login extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        setTitle("註冊_學生");


        button = (Button)findViewById(R.id.center);
        Button nextpagebtn = (Button)findViewById(R.id.center);
        nextpagebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(student_login.this ,student_menu.class);
                startActivity(intent);
            }
        });




    }
}
