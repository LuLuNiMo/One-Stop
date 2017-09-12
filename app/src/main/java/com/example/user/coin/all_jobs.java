package com.example.user.coin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class all_jobs extends AppCompatActivity {
    private final static String preferences_name = "preferences";
    ListView listview;
    Button lession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_jobs);
        setTitle("職缺");
        findView();
        listview.setAdapter(new JobMgAdapter(this));


    }

    private class JobMgAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;
        private List<stu_job_mange_item> job_List;

        public JobMgAdapter(Context context){
            layoutInflater = LayoutInflater.from(context);
            job_List = new ArrayList<>();

            job_List.add(new stu_job_mange_item(R.drawable.logo,"雲科大","教育類","星期一~五 8:00~12:00","140","電腦能力好","基本雜物",2,"斗六市","2017/08/20刊登"));
            job_List.add(new stu_job_mange_item(R.drawable.fb,"補習班","教育類","星期二,四 18:00~21:00","350","英文能力好","教小朋友",3,"斗六市","2017/08/22刊登"));
            job_List.add(new stu_job_mange_item(R.drawable.back,"微笑早餐","餐飲類","星期一~五 6:00~8:00 12:00~14:00","140","活潑外向","結帳洗碗",2,"古坑鄉","2017/08/29刊登"));
            job_List.add(new stu_job_mange_item(R.drawable.stu_eva_icon1,"7-11","服務類","星期一~五 早班8:00~17:00","140","學習能力快,外向","服務人員",3,"莿桐鄉","2017/08/30刊登"));
            job_List.add(new stu_job_mange_item(R.drawable.stu_icon4,"家樂福","服務類","星期一~五 夜班22:00~5:00","140","學習能力快,外向","櫃台人員",2,"斗南鎮","2017/09/01刊登"));


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
            staId.setTextSize(14);

            Button detail = (Button) convertView
                    .findViewById(R.id.detail);

            final Bundle bundle = new Bundle();
            bundle.putSerializable("job",item);

            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(all_jobs.this,"請稍候......",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(all_jobs.this ,all_job_content.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

    public void findView() {
        listview  = (ListView) findViewById(R.id.list_view);
        lession = (Button) findViewById(R.id.lession);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

   // private final static String preferences_name = "preferences";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences shared =  getSharedPreferences(preferences_name,MODE_PRIVATE);
        final int id = item.getItemId();
        switch (id){
            case R.id.logout:
                shared.edit().remove("account").apply();
                Intent intent = new Intent();
                intent.setClass(all_jobs.this ,MainActivity.class);
                startActivity(intent);
                break;


        }

        return super.onOptionsItemSelected(item);
    }





}
