package com.example.user.coin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_role extends AppCompatActivity {
    private Button btstu;
    private Button btlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_role);
        setTitle("選擇身分");

      btlan = (Button)findViewById(R.id.role_land);
      btlan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent2 = new Intent();
                intent2.setClass(login_role.this ,landlord_login.class);
                startActivity(intent2);
            }
        });




        btstu = (Button)findViewById(R.id.role_stu);
        btstu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(login_role.this ,student_login.class);
                startActivity(intent1);

            }
        });








    }
}
