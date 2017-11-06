package com.example.user.coin;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class student_case_content extends AppCompatActivity {
    protected SQLLite_StudentDataHelper helper;
    protected  final static String preferences_name = "preferences";
    protected  TextView id, na, time, pay, num, area, cond, cont, title;
    protected  ImageView img;
    protected  String account;
    protected  int no;
    protected  case_item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_case_content);
        SharedPreferences shared = this.getSharedPreferences(preferences_name, MODE_PRIVATE);
        account = shared.getString("account", "0");
        setTitle("詳細資料");
        Bundle bundle = getIntent().getExtras();
        no = bundle.getInt("case_no");
        findView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (helper == null) {
            helper = new SQLLite_StudentDataHelper(this);
        }
        item = helper.findById_case(no);
        output();
    }

    @Override
    public void onDestroy() {
        if (helper != null) {
            helper.close();
        }
        super.onDestroy();
    }


    protected void output() {
        title.setText(item.getName());
        na.setText(item.getNature());
        time.setText(item.getTime());
        pay.setText(item.getPay());
        num.setText(String.valueOf(item.getNum()));
        area.setText(item.getArea());
        cont.setText(item.getContent());
        cond.setText(item.getCondition());


        student_data data = helper.findById_data(item.getAccount());
        Bitmap image = BitmapFactory.decodeByteArray(data.getImage(), 0,
                data.getImage().length);
        img.setImageBitmap(image);
        String name = data.getName().substring(0, 1);
        if (data.getSex().equals("男")) {
            name = name + "先生";
        } else {
            name = name + "小姐";
        }
        id.setText(name);

    }


    public void onClick(View v) {
        if (catch_case(item) != -1) {
            Toast.makeText(student_case_content.this, "CASE Successful，請等候對方回應", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(student_case_content.this, "CASE Fail", Toast.LENGTH_SHORT).show();
        }
    }

    //connect method
    public void question(View v){
        new_AlertDialog(helper.findById_data(item.getAccount()));
    }

    //接CASE
    public int catch_case(case_item item) {
        int i = -1;
        try {
            if (test_id(item.getAccount(), item.getAttach())) {
                item.setAttach(item.getAttach() + account + " ,");
                if (helper == null) {
                    helper = new SQLLite_StudentDataHelper(student_case_content.this);
                }
                i = helper.update_case(item);
                helper.close();
            }
        } catch (Exception ex) {
            Toast.makeText(student_case_content.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return i;
    }

    //try 是否CASE自己的CASE或重複
    public boolean test_id(String text, String attach) {
        boolean t = true;
        if (text.trim().equals(account)) {
            t = false;
            Toast.makeText(student_case_content.this, "不能CASE自己的CASE", Toast.LENGTH_SHORT).show();
        } else if (!attach.trim().equals("")) {
            String[] arr = attach.split(",");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].trim().equals(account)) {
                    t = false;
                    Toast.makeText(student_case_content.this, "你已經CASE過該CASE", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return t;
    }

    //產生連絡對話框
    public void new_AlertDialog(final student_data data) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("聯絡我");
        dialog.setMessage("請選擇以下其中一種聯絡方法");
        dialog.setNegativeButton("電話", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                try {
                    String phone = data.getPhone();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                            + phone));
                    if (ActivityCompat.checkSelfPermission(student_case_content.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        Toast.makeText(student_case_content.this,"請允許開啟通話權限",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startActivity(intent);
                }catch(Exception ex){
                    Toast.makeText(student_case_content.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        });
        dialog.setNeutralButton
                ("即時通訊",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(student_case_content.this,"請稍候......",Toast.LENGTH_SHORT).show();

                    }

                });
        dialog.setPositiveButton
                ("視訊",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(student_case_content.this,"請稍候......",Toast.LENGTH_SHORT).show();
                    }

                });
        dialog.show();
    }



    protected  void findView(){
        title = (TextView)findViewById(R.id.title);
        id = (TextView)findViewById(R.id.id);
        na = (TextView)findViewById(R.id.na);
        time = (TextView)findViewById(R.id.time);
        pay = (TextView)findViewById(R.id.pay);
        num = (TextView)findViewById(R.id.num);
        area = (TextView)findViewById(R.id.area);
        cond = (TextView)findViewById(R.id.cond);
        cont = (TextView)findViewById(R.id.cont);
        img = (ImageView)findViewById(R.id.imgview);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences shared =  getSharedPreferences(preferences_name,MODE_PRIVATE);
        final int id = item.getItemId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.logout:
                shared.edit().remove("account").apply();
                intent.setClass(student_case_content.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.manage:
                intent.setClass(student_case_content.this, student_case_manage.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }










}
