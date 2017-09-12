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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class student_evaulate extends AppCompatActivity {
    ListView list;
    private final static String preferences_name = "preferences";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_evaulate);
        setTitle("評價");
        list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(new EvaulateAdapter(this));


    }

    private class EvaulateAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private List<evaulate_item> evaList;


        public EvaulateAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
            evaList = new ArrayList<>();

            evaList.add(new evaulate_item(R.drawable.logo, "CoCo", 5));
            evaList.add(new evaulate_item(R.drawable.fb, "全聯福利中心", 3));
            evaList.add(new evaulate_item(R.drawable.stu_ic5, "金玉堂", 4));
            evaList.add(new evaulate_item(R.drawable.stu_icon1, "雲林科技大學", 0));
            evaList.add(new evaulate_item(R.drawable.stu_ic3, "家樂福", 4));
            evaList.add(new evaulate_item(R.drawable.stu_ic5, "Google", 5));
            evaList.add(new evaulate_item(R.drawable.stu_icon4, "泡麵", 3));
            evaList.add(new evaulate_item(R.drawable.stu_eva_icon1, "Yahoo", 3));


        }

        @Override
        public int getCount() {
            return evaList.size();
        }

        @Override
        public Object getItem(int position) {
            return evaList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return evaList.get(position).getImg();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.evaulate_listview, parent, false);
            }
            evaulate_item item = evaList.get(position);


            ImageView img = (ImageView) convertView
                    .findViewById(R.id.pp);
            img.setImageResource(item.getImg());

            TextView na = (TextView) convertView
                    .findViewById(R.id.name);
            na.setText(String.valueOf(item.getName()));

            RatingBar rat = (RatingBar) convertView.findViewById(R.id.star);
            rat.setNumStars(5);
            rat.setRating(Integer.valueOf(item.getScore()));
            rat.setIsIndicator(true);


            TextView info = (TextView) convertView.findViewById(R.id.info);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(student_evaulate.this, "請稍候", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences shared = getSharedPreferences(preferences_name, MODE_PRIVATE);
        final int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                shared.edit().remove("account").apply();
                Intent intent = new Intent();
                intent.setClass(student_evaulate.this, MainActivity.class);
                startActivity(intent);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}


