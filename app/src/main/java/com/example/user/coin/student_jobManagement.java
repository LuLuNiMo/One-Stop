package com.example.user.coin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.argb;

public class student_jobManagement extends AppCompatActivity {
    private final static String preferences_name = "preferences";
    Button btn1,btn2,btn3;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_job_management);
        setTitle("工作管理");
        findView();
        listview.setAdapter(new JobMgAdapter(this));


    }

    //listview內容
    private class JobMgAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;
        private List<stu_job_mange_item> job_List;

        public JobMgAdapter(Context context){
            layoutInflater = LayoutInflater.from(context);
            job_List = new ArrayList<>();

            job_List.add(new stu_job_mange_item(R.drawable.logo,"雲科大","教育類","2017/08/20","140","電腦能力好","基本雜物",3,"斗六市","已讀"));
            job_List.add(new stu_job_mange_item(R.drawable.fb,"補習班","教育類","2017/08/22","350","英文能力好","教小朋友",2,"斗六市","未讀"));
            job_List.add(new stu_job_mange_item(R.drawable.back,"微笑早餐","餐飲類","2017/08/29","140","活潑外向","結帳洗碗",3,"古坑鄉","未讀"));
            job_List.add(new stu_job_mange_item(R.drawable.stu_eva_icon1,"7-11","服務類","2017/08/30","140","學習能力快,外向","服務人員",4,"莿桐鄉","未讀"));
            job_List.add(new stu_job_mange_item(R.drawable.stu_icon4,"家樂福","服務類","2017/09/01","140","學習能力快,外向","櫃台人員",5,"斗南鎮","已讀"));


        }

        @Override
        public int getCount() {
            return job_List.size();
        }

        @Override
        public Object getItem(int position) {
            return job_List.get(position);
        }

        @Override
        public long getItemId(int position) {
            return job_List.get(position).getImg();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.stujobmge_listview, parent, false);
            }
            stu_job_mange_item item = job_List.get(position);

            ImageView idimg = (ImageView) convertView
                    .findViewById(R.id.imgview);
            idimg.setImageResource(item.getImg());

            TextView titleId = (TextView) convertView
                    .findViewById(R.id.title);
            titleId.setText(String.valueOf(item.getName()));

            TextView naId = (TextView) convertView
                    .findViewById(R.id.na);
            naId.setText(String.valueOf(item.getNature()));

            TextView tmId = (TextView) convertView
                    .findViewById(R.id.time);
            tmId.setText(String.valueOf(item.getTime()));

            TextView payId = (TextView) convertView
                    .findViewById(R.id.pay);
            payId.setText(String.valueOf(item.getPay()));

            TextView condId = (TextView) convertView
                    .findViewById(R.id.cond);
            condId.setText(String.valueOf(item.getCondition()));

            TextView contId = (TextView) convertView
                    .findViewById(R.id.cont);
            contId.setText(String.valueOf(item.getContent()));

            TextView adsId = (TextView) convertView
                    .findViewById(R.id.ads);
            adsId.setText(String.valueOf(item.getAddress()));


            TextView numId = (TextView) convertView
                    .findViewById(R.id.num);
            numId.setText(String.valueOf(item.getNum()));


            TextView staId = (TextView) convertView
                    .findViewById(R.id.state);
            staId.setText(String.valueOf(item.getState()));
            item.changeTextColor(staId);


            final Bundle bundle = new Bundle();
            bundle.putSerializable("job",item);

            Button detail = (Button) convertView
                    .findViewById(R.id.detail);
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(student_jobManagement.this,"請稍候......",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(student_jobManagement.this ,all_job_content.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }


    //Button 顯示資料
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.job_catch:
                Toast.makeText(student_jobManagement.this,"已應徵......",Toast.LENGTH_SHORT).show();
                changeBtnColorA(btn1);
                changeBtnColorB(btn2);
                changeBtnColorB(btn3);
                break;
            case R.id.job_get:

                Toast.makeText(student_jobManagement.this,"已經就任......",Toast.LENGTH_SHORT).show();
                changeBtnColorA(btn2);
                changeBtnColorB(btn1);
                changeBtnColorB(btn3);
                break;
            case R.id.job_reject:

                Toast.makeText(student_jobManagement.this,"掰掰......",Toast.LENGTH_SHORT).show();
                changeBtnColorA(btn3);
                changeBtnColorB(btn1);
                changeBtnColorB(btn2);
                break;


        }
    }









    public void findView(){
        btn1 = (Button)findViewById(R.id.job_catch);
        btn2 = (Button)findViewById(R.id.job_get);
        btn3 = (Button)findViewById(R.id.job_reject);
        listview = (ListView) findViewById(R.id.list_view);
    }



    public void changeBtnColorA(Button btn){
        btn.setBackgroundColor(argb(255,255,255,255));
        btn.setTextColor(argb(255,0,0,0));
    }

    public void changeBtnColorB(Button btn){
        btn.setBackgroundColor(argb(255,0,0,0));
        btn.setTextColor(argb(255,255,255,255));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences shared =  getSharedPreferences(preferences_name,MODE_PRIVATE);
        final int id = item.getItemId();
        switch (id){
            case R.id.logout:
                shared.edit().remove("account").apply();
                Intent intent = new Intent();
                intent.setClass(student_jobManagement.this ,MainActivity.class);
                startActivity(intent);
                break;


        }

        return super.onOptionsItemSelected(item);
    }


}

