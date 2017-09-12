package com.example.user.coin;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.text.method.PasswordTransformationMethod;
import android.text.method.HideReturnsTransformationMethod;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String preferences_name = "preferences";

    private Button button,fb;
    private TextView tvid ;
    private TextView tvps ;
    private TextView tvr ;
    private CheckBox pas;
    private List<student_data> list;
    private SQLLite_StudentDataHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("登入");
        findView();
        tv_event();

    }

    //載入DB
    @Override
    protected void onStart() {
        super.onStart();
        String text =  getSharedPreferences(preferences_name,MODE_PRIVATE).getString("account","0");
        if(!text.trim().equals("0")){
            intent_menu();
        }

        if (helper == null) {
            helper = new SQLLite_StudentDataHelper(this);
        }
           list = helper.getAllStu();
         //   for (int i = 0; i <= list.size() - 1; i++) {
       //    Toast.makeText(MainActivity.this,list.get(i).getAll(),Toast.LENGTH_SHORT).show();}
          //test_data();
          //Toast.makeText(this, String.valueOf(list.size()),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    //測試資料  每每執行一次，就會再新增一次
    public void test_data() {
        student_data s1 = new student_data("jingting", "dadf16561", "陳靜蜓", "0933654161", null, "雲林科技大學", "視覺傳達設計系",
                "B10532075", "女", "19", "B10532075@yuntech.edu.tw", "多媒體動畫設計、繪圖軟體應用", "", "",null);
        student_data s2 = new student_data("jiarong", "gh161hr61", "周傢蓉", "0928292919", null, "雲林科技大學", "文化資產維護系",
                "B10342078", "女", "22", "B10342078@yuntech.edu.tw", "善於傳統技藝", "按公司規定", "7-11店員",null);

        long rowId2 = helper.insert_data(s1);
        long rowId = helper.insert_data(s2);

        if (rowId != -1 && rowId2 != -1) {
            Toast.makeText(this, "0",
                    Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this,"-1",
                    Toast.LENGTH_SHORT).show();
        }
    }


    //按鈕的事件
    public void exit_app(View view){
     // TODO Auto-generated method stub
        new AlertDialog.Builder(MainActivity.this).setTitle("退出確認").setIcon(R.drawable.logo).setMessage("確定退此操出程式?")
                .setPositiveButton("取消",
                        new DialogInterface.OnClickListener()/*設定跳出視窗的返回事件*/
                        {
                            public void onClick(DialogInterface dialoginterface, int i){}
                        }
                ).setNegativeButton("確認",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialoginterface, int i){
                        finish();/*關閉視窗 等同android.os.Process.killProcess(android.os.Process.myPid());*/
                    }
                }
        ).show();}

    //元件
     public void findView(){
         tvid = (TextView) findViewById(R.id.pp);
         tvps = (TextView) findViewById(R.id.pass);
         pas = (CheckBox) findViewById(R.id.passcheck);
         tvr = (TextView) findViewById(R.id.register);
         tvr.setText(Html.fromHtml("<u>"+"第一次註冊嗎？請按這裡"+"</u>"));
         button = (Button)findViewById(R.id.login);
         fb = (Button)findViewById(R.id.fb);
     }


     //登入確認
     public void loggin(View view){
         if(check(tvid.getText().toString(),tvps.getText().toString())){
             Toast.makeText(MainActivity.this,"登入成功",Toast.LENGTH_SHORT).show();
             store_account(tvid.getText().toString());
             intent_menu();
         }else{
             Toast.makeText(MainActivity.this,"帳號或密碼輸入錯誤",Toast.LENGTH_SHORT).show();
         }
     }



   //驗證帳號,密碼
     public boolean check(String in_id,String in_pass) {
         boolean ans = false;
         for (int i = 0; i <= list.size() - 1; i++) {
             String id = list.get(i).getAccount();
             String pass = list.get(i).getPassword();

             if(id.equals(in_id) && pass.equals(in_pass)){
                 ans = true;
             }
         } return ans;

     }



     //儲存帳號_維持登入狀態
     public void store_account(String text){
         if (helper != null) {
             helper.close();
         }
         SharedPreferences shared = getSharedPreferences(preferences_name,MODE_PRIVATE);
         shared.edit().putString("account",text).apply(); //store in internal store
     }



     //checkbox 相關事件
     public void tv_event(){

         //密碼顯示隱藏
         pas.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 if (pas.isChecked()) {
                     tvps.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); //隱藏密碼
                 }else{
                     tvps.setTransformationMethod(PasswordTransformationMethod.getInstance()); //顯示密碼原文
                 }
             }
         });
         //註冊
         tvr.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent();
                 intent.setClass(MainActivity.this ,login_role.class);
                 startActivity(intent);
             }
         });

     }

    public void intent_menu(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, student_menu.class);
        startActivity(intent);
    }

   /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
*/




}


