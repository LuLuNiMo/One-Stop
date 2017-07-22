package com.example.user.coin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class student_login extends AppCompatActivity {

    private Button enter;
    Spinner school;
    Spinner dep;
    Spinner old;
    Spinner spe;
    CheckBox law;

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

      school = (Spinner)findViewById(R.id.spr_sch);
        ArrayAdapter<CharSequence> list_sch = ArrayAdapter.createFromResource(student_login.this,
                R.array.school,
                android.R.layout.simple_spinner_dropdown_item);
        school.setAdapter(list_sch);


        dep = (Spinner)findViewById(R.id.spr_dep);
        ArrayAdapter<CharSequence> list_dep= ArrayAdapter.createFromResource(student_login.this,
                R.array.department,
                android.R.layout.simple_spinner_dropdown_item);
        dep.setAdapter(list_dep);


        old = (Spinner)findViewById(R.id.spr_old);
        ArrayAdapter<CharSequence> list_old= ArrayAdapter.createFromResource(student_login.this,
                R.array.old,
                android.R.layout.simple_spinner_dropdown_item);
        old.setAdapter(list_old);

       spe= (Spinner)findViewById(R.id.spr_spe);
        ArrayAdapter<CharSequence> list_= ArrayAdapter.createFromResource(student_login.this,
                R.array.speciality,
                android.R.layout.simple_spinner_dropdown_item);
        spe.setAdapter(list_old);



        law = (CheckBox) findViewById(R.id.law);
        law.setText(Html.fromHtml("<u>"+"我同意，One_Stop條款"+"</u>"));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


}
