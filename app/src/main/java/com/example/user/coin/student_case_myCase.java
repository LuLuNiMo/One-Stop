package com.example.user.coin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class student_case_myCase extends AppCompatActivity{
    private SQLLite_StudentDataHelper helper;
    private final static String preferences_name = "preferences";
    private TextView  na, time, pay, num, area, cond, cont, title,state;
    private Button edit,delete,comp;
    private ListView listview;
    private CaseAdapter adapt;
    private String account;
    int i;
    private int no;
    private int del_num;
    private case_item item;
    SharedPreferences shared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_case_my_case);
        setTitle("CASE  內容");
        shared = this.getSharedPreferences(preferences_name, MODE_PRIVATE);
        account = shared.getString("account", "0");
        Bundle bundle = getIntent().getExtras();
        no = bundle.getInt("case_no");
        del_num= shared.getInt(String.valueOf(no) + "_num", 0); //record delete number
        findView();
        tv_state(false);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (helper == null) {
            helper = new SQLLite_StudentDataHelper(this);
        }
        item = helper.findById_case(no);
        i = item.getNum();
        adapt = new CaseAdapter(this);
        listview.setAdapter(adapt);
        output();
        button_state();

    }

    @Override
    public void onDestroy() {
        if (helper != null) {
            helper.close();
        }
        super.onDestroy();
    }

    private void output() {
        title.setText(item.getName());
        na.setText(item.getNature());
        time.setText(item.getTime());
        pay.setText(item.getPay());
        num.setText(String.valueOf(item.getNum()));
        area.setText(item.getArea());
        cont.setText(item.getContent());
        cond.setText(item.getCondition());
        state.setText(item.getState());
        adapt.notifyDataSetChanged();

    }

    //delete button
    public void onDel(View v){
        if(delete.getText().equals("刪除")){
            del_num++;
            if(del_num >= 2){
                real_delete();
            }else{
                del_rec_message("刪除","已取消");
            }
            shared.edit().putInt(String.valueOf(no) + "_num",del_num).apply();
            delete.setText("復原");
        }else{
            del_rec_message("復原","未完成");
            delete.setText("刪除");
        }
    }


    public void del_rec_message(String text,final String sta){
        new AlertDialog.Builder(student_case_myCase.this).setTitle(text +"確認").setIcon(R.drawable.logo).setMessage("確定要" + text + "這個CASE嗎？")
                .setPositiveButton("取消",
                        new DialogInterface.OnClickListener()/*設定跳出視窗的返回事件*/
                        {
                            public void onClick(DialogInterface dialoginterface, int i){}
                        }
                ).setNegativeButton("確認",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialoginterface, int i){
                        item.setState(sta);
                        int ans = helper.update_case(item);
                        if(ans != -1){
                            Toast.makeText(student_case_myCase.this,"更改成功！請注意刪除超過1次系統就會刪除",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(student_case_myCase.this,"操作失敗！請重試一次",Toast.LENGTH_SHORT).show();
                        }
                        output();
                    }
                }
        ).show();
    }


    public void real_delete(){
        int j = helper.deleteById_case(no);
        if(no != -1){
            Toast.makeText(student_case_myCase.this,"刪除成功",Toast.LENGTH_SHORT).show();
            Intent exit = new Intent();
            exit.setClass(student_case_myCase.this, student_case_manage.class);
            startActivity(exit);
        }else{
            Toast.makeText(student_case_myCase.this,"刪除失敗",Toast.LENGTH_SHORT).show();
        }
    }

    //edit button
    public void onClick(View v){
        int pos;
        if(edit.getText().equals("修改")){
            pos = 1;
            edit.setText("確認");
        }else{
            pos = 0;
            edit.setText("修改");
        }

        switch (pos){
            case 0:
                text_input();
                input();
                tv_state(false);
                output();
                break;
            case 1:
                tv_state(true);
                break;
        }
    }

    public void input(){
        try{
            int ans = helper.update_case(item);
            if(ans != -1){
                Toast.makeText(student_case_myCase.this,"更新成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(student_case_myCase.this,"更新失敗！請重試一次",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Toast.makeText(student_case_myCase.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void text_input(){
        item.setName(title.getText().toString());
        item.setNature(na.getText().toString());
        item.setTime(time.getText().toString());
        item.setPay(pay.getText().toString());
        item.setNum(Integer.valueOf(num.getText().toString()));
        item.setArea(area.getText().toString());
        item.setCondition(cond.getText().toString());
        item.setContent(cont.getText().toString());
    }


    private class CaseAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;
        private List<String> list;
        private List<Boolean> list2;


        //產生資料區
        public CaseAdapter(Context context){
            String[] str = item.getNumber().trim().split(",");
            list = new ArrayList<String>();
            list2= new ArrayList<Boolean>();

            for(int i = 0;i< str.length;i++){
                if(!str[i].trim().equals("")){
                    list.add(str[i]);
                }
            }

            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return list.get(position).length();
        }

        //載入資料
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView== null){
                convertView = layoutInflater.inflate(R.layout.student_case_number, parent, false);
            }
            final String sta = list.get(position);
            final boolean sta2 = list2.get(position);

            TextView tv = (TextView) convertView
                    .findViewById(R.id.tv);
            ImageView img = (ImageView) convertView
                    .findViewById(R.id.img);
            final CheckBox nn = (CheckBox) convertView
                    .findViewById(R.id.check);
            final CheckBox nm = (CheckBox) convertView
                    .findViewById(R.id.checck);

            final Button detail =(Button) convertView
                    .findViewById(R.id.detail);

            try{
                final student_data data;
                data = helper.findById_data(sta.trim());
               // Toast.makeText(student_case_myCase.this,sta,Toast.LENGTH_SHORT).show();

                if(data!=null){
                    tv.setText(sta);

                    Bitmap image = BitmapFactory.decodeByteArray(data.getImage(), 0,
                            data.getImage().length);
                    img.setImageBitmap(image);

                    if(sta2){
                        nn.setChecked(true);
                    }

                    nn.setOnClickListener(new View.OnClickListener() {
                      public void onClick(View v) {

                        }
                    });

                    nm.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                        }
                    });

                    detail.setOnClickListener(new View.OnClickListener() {
                         @Override
                               public void onClick(View v) {
                                  number_message(data.show_data());
                                                 }
                                    });
                }else{
                    nn.setVisibility(View.INVISIBLE);
                    nm.setVisibility(View.INVISIBLE);
                    img.setVisibility(View.INVISIBLE);
                    detail.setVisibility(View.INVISIBLE);
                    tv.setText("尚未有人應徵");
                }

            }catch(Exception ex){
                Toast.makeText(student_case_myCase.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(student_case_myCase.this,data.getAll(),Toast.LENGTH_SHORT).show();
            //Toast.makeText(student_case_myCase.this,item.getAttach(),Toast.LENGTH_SHORT).show();
            return convertView;
        }

    }

    //complete button
    public void on_comp(View v){
        if(comp.getText().toString().trim().equals("已解決")){
            item.setState("已解決");
            comp.setText("未解決");
        }else{
            item.setState("未解決");
            comp.setText("已解決");
        }
        input();
        output();
    }

    //case personal data
    public void number_message(String str){
        new AlertDialog.Builder(student_case_myCase.this).setTitle("詳細資料").setIcon(R.drawable.logo).setMessage(str)
                .setNegativeButton("確認",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialoginterface, int i){
                    }
                }
        ).show();
    }

    public void button_state(){
        if(item.getState().trim().equals("已解決")){
            comp.setText("未解決");
        }else if(item.getState().trim().equals("已取消")){
            delete.setText("復原");
        }
    }

    //textview onlyread or write state
    public void tv_state(Boolean state){
        title.setEnabled(state);
        na.setEnabled(state);
        time.setEnabled(state);
        pay.setEnabled(state);
        num.setEnabled(state);
        area.setEnabled(state);
        cond.setEnabled(state);
        cont.setEnabled(state);
    }


    private void findView(){
        title = (TextView)findViewById(R.id.title);
        na = (TextView)findViewById(R.id.na);
        time = (TextView)findViewById(R.id.time);
        pay = (TextView)findViewById(R.id.pay);
        num = (TextView)findViewById(R.id.num);
        area = (TextView)findViewById(R.id.area);
        cond = (TextView)findViewById(R.id.cond);
        cont = (TextView)findViewById(R.id.cont);
        edit = (Button)findViewById(R.id.edit);
        state = (TextView)findViewById(R.id.state);
        delete = (Button)findViewById(R.id.delete);
        comp = (Button)findViewById(R.id.complete);
        listview = (ListView) findViewById(R.id.listview);
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
                intent.setClass(student_case_myCase.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.manage:
                intent.setClass(student_case_myCase.this, student_case_manage.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
