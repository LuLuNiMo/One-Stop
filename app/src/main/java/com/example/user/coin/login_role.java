package com.example.user.coin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class login_role extends AppCompatActivity {
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_role);
        setTitle("選擇身分");
        shared = getSharedPreferences("preferences",MODE_PRIVATE);

    }

    @Override
    protected void onStart() {
        String account = shared.getString("account","0");
        if(!account.equals("0")){
            Intent intent = new Intent();
            intent.setClass(login_role.this ,student_menu.class);
            startActivity(intent);
        }

        super.onStart();
    }




    //button onclick event
    public void login(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.role_stu:
                intent.setClass(login_role.this ,student_login.class);
                break;

            case R.id.role_land:
                intent.setClass(login_role.this ,landlord_login.class);
                break;
        }
        startActivity(intent);
    }



}
