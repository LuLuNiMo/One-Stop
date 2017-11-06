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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.argb;

    public class student_case_manage extends AppCompatActivity {
        private final static String preferences_name = "preferences";
        Button btn_my,btn_catch;
        private SQLLite_StudentDataHelper helper;
        private SharedPreferences shared;
        private String account;
        ListView list ;
        boolean jump = true;
        CaseAdapter adapt;
        private List<case_item> item =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_case_manage);
        setTitle("CASE  管理");
        findView();
        shared = getSharedPreferences(preferences_name,MODE_PRIVATE);
        account =shared.getString("account","0");
        changeBtnColorA(btn_my);
        changeBtnColorB(btn_catch);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(helper == null){
            helper = new SQLLite_StudentDataHelper(this);
        }
        item  = helper.findById_case(account);
        adapt = new CaseAdapter(this);
        list.setAdapter(adapt);
        list.setTextFilterEnabled(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(helper != null){
            helper.close();
        }
    }


    public void onClick(View view){
        switch (view.getId()) {
            case R.id.case_my:
                jump = true;
                changeBtnColorA(btn_my);
                changeBtnColorB(btn_catch);
                item.clear();
                item  = helper.findById_case(account);
                adapt.notifyDataSetChanged();
                break;
            case R.id.case_catch:
                jump = false;
                changeBtnColorA(btn_catch);
                changeBtnColorB(btn_my);
                item.clear();
                for(int i = 0;i<helper.getAllCase().size();i++){
                    String text = helper.getAllCase().get(i).getAttach();
                    case_item itemm = helper.getAllCase().get(i);
                    String[] nn = text.split(",");
                    for(int j = 0;j<nn.length;j++){
                        if(nn[j].indexOf(account) != -1){
                            item.add(itemm);
                        }
                    }
                }
                adapt.notifyDataSetChanged();
                break;

        }
    }


        private class CaseAdapter extends BaseAdapter {
            private LayoutInflater layoutInflater;

            //產生資料區
            public CaseAdapter(Context context){
                layoutInflater = LayoutInflater.from(context);

            }


            @Override
            public int getCount() {
                return item.size();
            }

            @Override
            public Object getItem(int position) {
                return item.get(position);
            }

            @Override
            public long getItemId(int position) {
                return item.get(position).getId();
            }


            //載入資料
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = layoutInflater.inflate(R.layout.case_listview, parent, false);
                }
                final case_item caseitem = item.get(position);

                TextView titleId = (TextView) convertView
                        .findViewById(R.id.title);
                titleId.setText(String.valueOf(caseitem.getName()));

                TextView tmId = (TextView) convertView
                        .findViewById(R.id.time);
                tmId.setText(String.valueOf(caseitem.getTime()));

                TextView payId = (TextView) convertView
                        .findViewById(R.id.pay);
                payId.setText(String.valueOf(caseitem.getPay()));

                TextView condId = (TextView) convertView
                        .findViewById(R.id.cond);
                condId.setText(String.valueOf(caseitem.getCondition()));


                TextView contId = (TextView) convertView
                        .findViewById(R.id.cont);
                contId.setText(String.valueOf(caseitem.getContent()));

                //接case method
                Button catch_you =(Button) convertView
                        .findViewById(R.id.catch_case);

                catch_you.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent();
                        bundle.putInt("case_no",caseitem.getId());
                        if(jump){
                            intent.setClass(student_case_manage.this,student_case_myCase.class);
                        }else{
                            intent.setClass(student_case_manage.this,student_case_catchCase.class);
                        }
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

                return convertView;
            }

        }



    void findView(){
        btn_my = (Button)findViewById(R.id.case_my);
        btn_catch =  (Button)findViewById(R.id.case_catch);
        list = (ListView)findViewById(R.id.listview);
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
                intent.setClass(student_case_manage.this ,MainActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
