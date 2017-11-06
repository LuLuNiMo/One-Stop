package com.example.user.coin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class student_case_catchCase extends student_case_content {
    private TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_case_catch_case);
        setTitle("已應徵的 CASE ");
        findView();
        state = (TextView)findViewById(R.id.state);
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

    public void onClick(View v) {
        new AlertDialog.Builder(student_case_catchCase.this).setTitle("取消確認").setIcon(R.drawable.logo).setMessage("確定要取消應徵這個CASE?")
                .setPositiveButton("取消",
                        new DialogInterface.OnClickListener()/*設定跳出視窗的返回事件*/
                        {
                            public void onClick(DialogInterface dialoginterface, int i){}
                        }
                ).setNegativeButton("確認",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialoginterface, int i){
                     String text = "";
                        String[] arr = item.getAttach().split(",");

                        for(int j = 0;j<arr.length;j++){
                        //        if(arr[j].indexOf('(') != -1){
                         //       arr[j] = arr[j].substring(0,arr[j].indexOf('(')-1);
                         //   }
                            if(arr[j].trim().equals(account) || arr[j].trim().equals("")){
                            }else{
                                text = text + "," + arr[j];
                            }
                        }
                         item.setAttach(text);
                       // Toast.makeText(student_case_catchCase.this,String.valueOf(arr.length),Toast.LENGTH_SHORT).show();
                       // Toast.makeText(student_case_catchCase.this,text,Toast.LENGTH_SHORT).show();
                         cancel();

                    }
                }
        ).show();
    }

    public void cancel(){
        int y = helper.update_case(item);
        if(y != -1){
            Toast.makeText(student_case_catchCase.this,"取消成功",Toast.LENGTH_SHORT).show();
            Intent exit = new Intent();
            exit.setClass(student_case_catchCase.this, student_case_manage.class);
            startActivity(exit);
        }else{
            Toast.makeText(student_case_catchCase.this,"取消失敗",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences shared =  getSharedPreferences(preferences_name,MODE_PRIVATE);
        final int id = item.getItemId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.logout:
                shared.edit().remove("account").apply();
                intent.setClass(student_case_catchCase.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.manage:
                intent.setClass(student_case_catchCase.this, student_case_manage.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
