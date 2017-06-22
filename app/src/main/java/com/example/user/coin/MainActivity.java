package com.example.user.coin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;
public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("登入");


        button = (Button)findViewById(R.id.fb);
       Button nextpagebtn = (Button)findViewById(R.id.fb);
        nextpagebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this ,landlord_login.class);
                startActivity(intent);
            }
        });


        button = (Button)findViewById(R.id.login);
        nextpagebtn = (Button)findViewById(R.id.login);
        nextpagebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this ,student_login.class);
                startActivity(intent);
            }
        });


    }
}
