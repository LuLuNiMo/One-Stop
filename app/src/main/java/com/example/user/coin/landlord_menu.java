package com.example.user.coin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import android.view.View;

public class landlord_menu extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord_menu);
        setTitle("商家_主畫面");


        button = (Button)findViewById(R.id.stu_person);
        Button nextpagebtn = (Button)findViewById(R.id.stu_person);
        nextpagebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(landlord_menu.this ,landlord_personal.class);
                startActivity(intent);
            }
        });




    }


}
