package com.example.user.coin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class student_group extends AppCompatActivity {
    GridView gridview;
    private final static String preferences_name = "preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_group);
        setTitle("群組");
        gridview = (GridView)findViewById(R.id.grid_view);
        gridview.setAdapter(new Group_Adapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent();
                intent.setClass(student_group.this ,student_group_content.class);
                startActivity(intent);

              /*  group_item group = (group_item) parent.getItemAtPosition(position);
                ImageView imageView = new ImageView(student_group.this);
                imageView.setImageResource(group.getImg());
                Toast toast = new Toast(student_group.this);
                toast.setView(imageView);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show(); */
            }
        });



    }

    private class Group_Adapter extends BaseAdapter{
        private LayoutInflater layoutInflater;
        private List<group_item> g_List;

        public Group_Adapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
            g_List = new ArrayList<>();

            g_List.add(new group_item(R.drawable.fb,"FB大團購","2017/08/20加入"));
            g_List.add(new group_item(R.drawable.logo,"打工一級棒","2017/08/22加入"));
            g_List.add(new group_item(R.drawable.stu_icon4,"yahoo拍賣","2017/08/29加入"));
            g_List.add(new group_item(R.drawable.stu_eva_icon1,"斗六鐘表行","2017/08/25加入"));
            g_List.add(new group_item(R.drawable.stu_icon1,"yuntech_XX系辦","2017/08/31加入"));
            g_List.add(new group_item(R.drawable.stu_ic5,"人文夜市","2017/09/03加入"));




        }

        @Override
        public int getCount() {
            return g_List.size();}

        @Override
        public Object getItem(int position) {
            return g_List.get(position);
        }

        @Override
        public long getItemId(int position) {
            return g_List.get(position).getImg();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.stu_group_listview, parent, false);
            }
            group_item item = g_List.get(position);

            ImageView idimg = (ImageView) convertView
                    .findViewById(R.id.imgview);
            idimg.setImageResource(item.getImg());

            TextView titleId = (TextView) convertView
                    .findViewById(R.id.title);
            titleId.setText(String.valueOf(item.getName()));

            TextView dateId = (TextView) convertView
                    .findViewById(R.id.date);
            dateId.setText(String.valueOf(item.getData()));
            dateId.setText(Html.fromHtml("<u>" + String.valueOf(item.getData()) + "</u>"));
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
        SharedPreferences shared =  getSharedPreferences(preferences_name,MODE_PRIVATE);
        final int id = item.getItemId();
        switch (id){
            case R.id.logout:
                shared.edit().remove("account").apply();
                Intent intent = new Intent();
                intent.setClass(student_group.this ,MainActivity.class);
                startActivity(intent);
                break;


        }

        return super.onOptionsItemSelected(item);
    }






}
