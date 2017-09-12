package com.example.user.coin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.graphics.Color.argb;

public class student_resume_version extends AppCompatActivity {
    Button btn1,btn2,edit,push;
    private String account;
    ImageView img;
    private byte[] image = null;
    private byte[] image_ori;
    private final static String preferences_name = "preferences";
    TextView e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11;
     private SQLLite_StudentDataHelper helper;
     private String pass;
     private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_resume_version);
        setTitle("履歷版本");
        findView();
        changeBtnColorA(btn1);
        changeBtnColorB(btn2);
        wr_state(false);
        shared = getSharedPreferences(preferences_name,MODE_PRIVATE);
        account =shared.getString("account","0");

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(helper == null){
            helper = new SQLLite_StudentDataHelper(this);
        }
        text_output();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(helper != null){
           helper.close();
        }
    }

    //Button 顯示資料
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.resume_easy:
                changeBtnColorA(btn1);
                changeBtnColorB(btn2);
                break;
            case R.id.resume_complex:
                changeBtnColorA(btn2);
                changeBtnColorB(btn1);
                break;

        }
    }

    //edittext only read
    public void wr_state(boolean state){
        e1.setEnabled(state);
        e2.setEnabled(state);
        e3.setEnabled(state);
        e4.setEnabled(state);
        e5.setEnabled(state);
        e6.setEnabled(state);
        e7.setEnabled(state);
        e8.setEnabled(state);
        e9.setEnabled(state);
        e10.setEnabled(state);
        e11.setEnabled(state);
        push.setEnabled(state);
    }

    //output data
    public void text_output(){
        if(!account.equals("0")){
            student_data data = helper.findById_data(account);
            e1.setText(data.getName());
            e2.setText(data.getSex());
            e3.setText(data.getAge());
            e4.setText(data.getDepartment());
            e5.setText(data.getPhone());
            e6.setText(data.getSchool());
            e7.setText(data.getStu_id());
            e8.setText(data.getMail());
            e9.setText(data.getSpeciality());
            e10.setText(data.getHistory());
            e11.setText(data.getWish());
            pass = data.getPassword();
            image_ori = data.getImage();
            Bitmap bitmap;
            if(data.getPhoto() != null){
                bitmap = BitmapFactory.decodeByteArray(data.getPhoto() , 0,
                        data.getPhoto() .length);
                img.setImageBitmap(bitmap);
            }
        }else{
            Toast.makeText(student_resume_version.this,"系統出現錯誤，請按登出並重新登入",Toast.LENGTH_SHORT).show();
        }
    }

    public void button_input(View v){
        int pos;
        if(edit.getText().equals("修改資料")){
            pos = 1;
            edit.setText("確認");
        }else{
            pos = 0;
            edit.setText("修改資料");
        }

        switch (pos){
            case 0:
                text_input();
                wr_state(false);
                text_output();
                break;
            case 1:
                wr_state(true);
                break;
        }
    }

    //input data
    public void  text_input(){
        try{
            int ans = helper.update_data(input());
            if(ans != -1){
                Toast.makeText(student_resume_version.this,"更新成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(student_resume_version.this,"更新失敗！請重試一次",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Toast.makeText(student_resume_version.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public student_data input(){
        String name = e1.getText().toString();
        String sex = e2.getText().toString();
        String age =  e3.getText().toString();
        String depp =  e4.getText().toString();
        String phone = e5.getText().toString();
        String sch = e6.getText().toString();
        String stuid = e7.getText().toString();
        String mail =  e8.getText().toString();
        String skill =e9.getText().toString();
        String history =  e10.getText().toString();
        String wish =  e11.getText().toString();
        student_data data = new student_data(account,pass,name,phone,image_ori,sch,depp,stuid,sex,age,mail,skill,wish,history,image);
        return data;
    }

    public void picture_push(View v){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            Uri uri = intent.getData();
            String[] columns = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(uri, columns,
                    null, null, null);
            if (cursor.moveToFirst()) {
                String imagePath = cursor.getString(0);
                cursor.close();
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                img.setImageBitmap(bitmap);
                ByteArrayOutputStream out2 = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out2);
                image = out2.toByteArray();
            }
        }else{
            Toast.makeText(student_resume_version.this,"上傳失敗",Toast.LENGTH_SHORT).show();
        }
    }


    public void findView(){
        e1 = (TextView)findViewById(R.id.name);
        e2 = (TextView)findViewById(R.id.sex);
        e3 = (TextView)findViewById(R.id.age);
        e4 = (TextView)findViewById(R.id.dep);
        e5 = (TextView)findViewById(R.id.phone);
        e6 = (TextView)findViewById(R.id.school);
        e7 = (TextView)findViewById(R.id.id);
        e8 = (TextView)findViewById(R.id.e_mail);
        e9 = (TextView)findViewById(R.id.spec);
        e10 = (TextView)findViewById(R.id.job);
        e11 = (TextView)findViewById(R.id.wish);
        btn1 = (Button)findViewById(R.id.resume_easy);
        btn2 = (Button)findViewById(R.id.resume_complex);
        img = (ImageView) findViewById(R.id.img);
        edit = (Button)findViewById(R.id.edit);
        push = (Button)findViewById(R.id.push);
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
                intent.setClass(student_resume_version.this ,MainActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }




}
