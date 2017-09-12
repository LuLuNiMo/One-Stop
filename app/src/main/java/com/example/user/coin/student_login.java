package com.example.user.coin;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.user.coin.R.id.editText;
import static com.example.user.coin.R.id.imageView;
import static com.example.user.coin.R.id.spr_dep;
import static com.example.user.coin.R.id.spr_old;

public class student_login extends AppCompatActivity {
    Spinner school;
    Spinner dep;
    Spinner old;
    ImageView img;
    Multiple_Spinner spe;
    CheckBox law,pass;
    RadioButton ch_man,ch_woman;
    TextView e2,e1,e3,e4,e5,e6,e7,fileinfo,e8,e9,message;
    Button filebtn;
    boolean vaild = false; //驗證確認
    ArrayAdapter<CharSequence> sch,depp,oldd;
    List<String> list_spe;
    String other = ""; //spinner other data
    private SQLLite_StudentDataHelper helper;
    private byte[] image;
    private File file;
    Uri imgurl;
    private static final int REQUEST_TAKE_PICTURE = 0;
    private static final int REQUEST_PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        setTitle("註冊");
        findView(); //元件
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(helper == null){
            helper = new SQLLite_StudentDataHelper(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.close();
        }
    }

    //資料確認輸出 button
    public void onClick(View view){
        isValid();
        message.setText("");
        if (vaild){
            if(law.isChecked()){
                check_message();
            }else{
                Toast.makeText(this,"需要同意One Stop條款才可註冊!!",Toast.LENGTH_SHORT).show();
            }
        }
    }


    //清空 button
    public void reWrite(View view){ //重新填寫
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        e6.setText("");
        e7.setText("");
        e8.setText("");
        e9.setText("");
        message.setText("");
        fileinfo.setText("");
        ch_man.setChecked(false);
        ch_woman.setChecked(false);
        school.setSelection(0);
        dep.setSelection(0);
        old.setSelection(0);
        spe.setSelection(0);
        img.setImageBitmap(null);
    }

    //拍照
    public void take_picture(View view){
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        file = new File(file, "picture.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        if (isIntentAvailable(this, intent)) {
            startActivityForResult(intent, REQUEST_TAKE_PICTURE);
        } else {
            Toast.makeText(this, "抱歉！您的手機並不支援此操作",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //確認有沒有拍照功能
    private boolean isIntentAvailable(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    //載入圖片 BUTTON
    public void load_picture(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_PICK_IMAGE);
    }

    //取得照片,圖片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
            if (resultCode == Activity.RESULT_OK) {
                switch (requestCode) {
                    case REQUEST_TAKE_PICTURE:
                        Bitmap picture = BitmapFactory.decodeFile(file.getPath());
                        img.setImageBitmap(picture);
                        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
                        picture.compress(Bitmap.CompressFormat.JPEG, 100, out1);
                        image = out1.toByteArray();
                        break;
                    case REQUEST_PICK_IMAGE: //選取從檔案抓圖片
                        Uri uri = intent.getData();
                        String[] columns = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(uri, columns,
                                null, null, null);
                        if (cursor.moveToFirst()) {
                            String imagePath = cursor.getString(0);
                            cursor.close();
                            fileinfo.setText(imagePath);
                            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                            img.setImageBitmap(bitmap);
                            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out2);
                            image = out2.toByteArray();
                        }
                        break;
                }
            }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    //設定spinner資料
    public void set_list(Spinner spr,int i,ArrayAdapter<CharSequence> list){
        list= ArrayAdapter.createFromResource(student_login.this,
                i,
                android.R.layout.simple_spinner_dropdown_item);
        spr.setAdapter(list);

    }

    //驗證資料
    public void isValid() {
        boolean v1;
        boolean v2;
        boolean v3;
        boolean v4;
        boolean v5;
        boolean v6;
        boolean v7;
        boolean v8;
        boolean v9;


        String pattern_id = "[a-zA-Z][A-Za-z0-9]{1,20}";
        String txtid = e1.getText().toString();
        if (!txtid.matches(pattern_id)) {
            e1.setError("英文開頭且與數字" +
                    "組成最多20字");
            v1 = false;
        } else {
            v1 = true;
        }

        String txtpass = e2.getText().toString();
        if (txtpass.length() < 6 || txtpass.length() > 20) {
            e2.setError("最短6碼，最長20碼");
            v2 = false;
        } else {
            v2 = true;
        }

        String txtpas = e3.getText().toString();
        if(txtpas.equals(txtpass)){
            v3 = true;
        }else{
            v3 = false;
            e3.setError("密碼不一致！！");
        }

       String name = e4.getText().toString();
        if(name == null || name.trim().equals("")){
               v4 = false;
               e4.setError("姓名不可為空白");
        }else{
            v4 = true;
        }


        String schid = e5.getText().toString();
        if(schid == null || schid.trim().equals("")){
            v5 = false;
            e5.setError("學號不可為空白");
        }else{
            v5 = true;
        }

        String pattern_mail ="[a-zA-Z0-9._%-]+@[a-zA-Z0-9._%-]+\\.[a-zA-Z]{2,4}";
        String mail = e6.getText().toString();
        if(mail == null ||  mail.trim().equals("") || !(mail.matches(pattern_mail))){
            e6.setError("信箱格式錯誤");
            v6 = false;
        }else{
            v6 = true;
        }


        String phone = e7.getText().toString();
        if(phone == null || phone.trim().equals("")){
            v9 = false;
            e7.setError("手機號碼不可為空白");
        }else{
            v9 = true;
        }

        if(ch_man.isChecked() || ch_woman.isChecked()){
            v7 = true;
        }else{
            ch_man.setError("性別沒有勾選!!");
            v7 = false;
        }


        if(dep.getSelectedItemPosition() == 0 || old.getSelectedItemPosition() == 0  ||school.getSelectedItemPosition() == 0 || spe.getSelectedItemsAsString().trim().equals("")){
            Toast.makeText(student_login.this,"請確認一下拉式選單(科系,學校,年齡,專長" +
                    ")是否未選擇!!",Toast.LENGTH_SHORT).show();
            v8 = false;
        }else{
            v8 = true;
        }


        if(v1 && v2 && v3 && v4 && v5 && v6 && v7 && v8 && v9){
            vaild = true;
        }else{
            vaild = false;
        }

    }

    //One Stop 條款輸出
    public void check_message(){
        // TODO Auto-generated method stub
        new AlertDialog.Builder(student_login.this).setTitle("One Stop 條款").setIcon(R.drawable.logo).setMessage(file_one_stop())
                .setPositiveButton("拒絕",
                        new DialogInterface.OnClickListener()/*設定跳出視窗的返回事件*/
                        {
                            public void onClick(DialogInterface dialoginterface, int i){
                                law.setChecked(false);
                            }
                        }
                ).setNegativeButton("同意",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialoginterface, int i){
                      try {
                          if (login_data()) {
                              Toast.makeText(student_login.this, "註冊成功", Toast.LENGTH_SHORT).show();
                              store_account(e1.getText().toString());
                              intent_menu();
                          } else {
                              Toast.makeText(student_login.this, "註冊失敗", Toast.LENGTH_SHORT).show();
                          }

                      }catch (Exception ex){
                          message.setText(ex.getMessage());
                      }
                    }
                }
        ).show();
     }

    //將資料載入資料庫
    public boolean login_data(){
        boolean ans = false;
        try{
            if(helper.findById_data(e1.getText().toString()) != null){
                message.setText("帳號重複!!");
            }else{
                    long num = helper.insert_data(input());
                    if(num != -1){
                        ans = true;
                    }
            }
        }catch(Exception ex){
            message.setText(ex.getMessage());
        }

        return ans;
    }

    //輸入資料
    public student_data input(){
        String account = e1.getText().toString();
        String pass = e2.getText().toString();
        String name = e4.getText().toString();
        String phone = e7.getText().toString();
        String sch = String.valueOf(school.getSelectedItem());
        String depp =  String.valueOf(dep.getSelectedItem());
        String stuid = e5.getText().toString();
        String sex;
        if(ch_man.isChecked()){
            sex = "男";
        }else{sex = "女";}
        String age =  String.valueOf(old.getSelectedItem());
        String mail =  e6.getText().toString();
        String skill = spe.getSelectedItemsAsString();
        String wish =  e8.getText().toString();
        String ho =  e9.getText().toString();

        if(image == null){ //使用系統預設圖片 先imageview 轉bitmap再轉byte array
            String url = "@drawable/stu_logo";
            int imageResource = getResources().getIdentifier(url, null, getPackageName());
            img.setImageResource(imageResource);
            img.setDrawingCacheEnabled(true);
            img.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            image = baos.toByteArray();
        }
        student_data data = new student_data(account,pass,name,phone,image,sch,depp,stuid,sex,age,mail,skill,wish,ho,null);
        return data;
    }

    //儲存帳號
    public void store_account(String text){
        SharedPreferences shared;
        shared = getSharedPreferences("preferences",MODE_PRIVATE);
        shared.edit().putString("account",text).apply();
    }


    public String file_one_stop(){
        String content = "";
        BufferedReader br = null;
        try {
            InputStream is = getAssets().open("one_stop_law.txt");
            br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text);
                sb.append("\n");
            }
            content = sb.toString();
        } catch (IOException e) {
            Toast.makeText(student_login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                Toast.makeText(student_login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
     return content;
    }


    //元件
    public void findView(){
        e1= (TextView) findViewById(R.id.e1);
        e3= (TextView) findViewById(R.id.e3);
        pass = (CheckBox) findViewById(R.id.checkpass);
        e2 = (TextView) findViewById(R.id.e2);
        e3 = (TextView) findViewById(R.id.e3);
        e4 = (TextView) findViewById(R.id.e4);
        e5 = (TextView) findViewById(R.id.e5);
        e6 = (TextView) findViewById(R.id.e6);
        e7 = (TextView) findViewById(R.id.e7);
        e8 = (TextView) findViewById(R.id.e8);
        e9 = (TextView) findViewById(R.id.e9);
        message =(TextView) findViewById(R.id.tv);
        fileinfo = (TextView) findViewById(R.id.file);
        filebtn = (Button) findViewById(R.id.put);
        ch_man = (RadioButton)findViewById(R.id.sex_man);
        ch_woman = (RadioButton)findViewById(R.id.sex_woman);
        img =  (ImageView) findViewById(R.id.image);
        school = (Spinner)findViewById(R.id.spr_sch);
        set_list(school,R.array.school,sch);
        dep = (Spinner)findViewById(R.id.spr_dep);
        set_list(dep,R.array.department,depp);
        old = (Spinner)findViewById(R.id.spr_old);
        set_list(old,R.array.old,oldd);

        spe= (Multiple_Spinner)findViewById(R.id.spr_spe);
        list_spe = Arrays.asList(getResources().getStringArray(R.array.speciality));
        spe.setItems(list_spe);

        law = (CheckBox) findViewById(R.id.law);
        law.setText(Html.fromHtml("<u>"+law.getText().toString()+"</u>"));
        //密碼設定

        pass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pass.isChecked()) {
                    e2.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); //隱藏密碼

                }else{
                    e2.setTransformationMethod(PasswordTransformationMethod.getInstance()); //顯示密碼原文
                }
            }
        });

    }

    //student_menu
    public void intent_menu(){
        Intent intent = new Intent();
        intent.setClass(student_login.this, student_menu.class);
        startActivity(intent);
    }

}
