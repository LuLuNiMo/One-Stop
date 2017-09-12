package com.example.user.coin;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class all_job_content extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,tv_2;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_job_content);
        setTitle("詳細資料");
        findView();
        showResults();
    }

    //資料顯示
    private void showResults() {
        Bundle bundle = getIntent().getExtras();
        stu_job_mange_item item = (stu_job_mange_item)bundle.getSerializable("job");
        img.setImageResource(item.getImg());
        t1.setText(item.getName());
        change_state(item);
        t2.setText(item.getState());
        t3.setText(item.getNature());
        t4.setText(String.valueOf(item.getNum()));
        t5.setText(item.getTime());
        t6.setText(item.getPay());
        t7.setText(item.getCondition());
        t8.setText(item.getAddress());
        t9.setText(item.getContent());

    }

    //更改state 缺陷
    public void change_state(stu_job_mange_item item){
        if(item.getState().equals("已讀") ){
            tv_2.setText("狀態：");
            t2.setTextColor(Color.argb(255,0,0,0));
        }else if(item.getState().equals("未讀")){
            tv_2.setText("狀態：");
            t2.setTextColor(Color.argb(255,255,0,0));
        }else{
            t2.setTextColor(Color.argb(255,0,0,0));
        }
    }

    public void findView(){
        t1 = (TextView)findViewById(R.id.title);
        t2 = (TextView)findViewById(R.id.date_time);
        t3 = (TextView)findViewById(R.id.na);
        t4 = (TextView)findViewById(R.id.num);
        t5 = (TextView)findViewById(R.id.time);
        t6 = (TextView)findViewById(R.id.pay);
        t9 = (TextView)findViewById(R.id.cont);
        t8 = (TextView)findViewById(R.id.ads);
        t7 = (TextView)findViewById(R.id.cond);
        tv_2 = (TextView)findViewById(R.id.tv_2);
        img =  (ImageView) findViewById(R.id.img);
    }

    //button
    public void onClick(View view){
        new_AlertDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    //聯絡方法
    public void new_AlertDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(all_job_content.this);
        dialog.setTitle("聯絡我");
        dialog.setMessage("請選擇以下其中一種聯絡方法");
        dialog.setNegativeButton("電話",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                Toast.makeText(all_job_content.this,"請稍候......",Toast.LENGTH_SHORT).show();

            }

        });
        dialog.setPositiveButton("即時通訊",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                Toast.makeText(all_job_content.this,"請稍候......",Toast.LENGTH_SHORT).show();

            }

        });
        dialog.setNeutralButton("視訊",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                Toast.makeText(all_job_content.this,"請稍候......",Toast.LENGTH_SHORT).show();
            }

        });
        dialog.show();
    }



}
