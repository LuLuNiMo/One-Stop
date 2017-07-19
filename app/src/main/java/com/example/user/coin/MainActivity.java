package com.example.user.coin;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.text.Html;
import android.text.InputType;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.*;
import android.text.method.PasswordTransformationMethod;
import android.text.method.HideReturnsTransformationMethod;
import android.widget.Toolbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView tvid ;
    TextView tvps ;
    TextView tvr ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("登入");
     //   Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);


        tvid = (TextView) findViewById(R.id.id);
        tvid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tvid.setText("");
            }
        });


        tvps = (TextView) findViewById(R.id.pass);
        tvps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                tvps.setText("");
                tvps.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                tvps.setTransformationMethod(PasswordTransformationMethod.getInstance());

              //  tvps.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); 顯示密碼原文

            }
        });


        tvr = (TextView) findViewById(R.id.register);
        tvr.setText(Html.fromHtml("<u>"+"第一次註冊嗎？請按這裡"+"</u>"));
        tvr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this ,login_role.class);
                startActivity(intent);
            }
        });


        button = (Button)findViewById(R.id.cancel);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }





}


 /*   button = (Button)findViewById(R.id.fb);
       nextpagebtn = (Button)findViewById(R.id.fb);
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
*/