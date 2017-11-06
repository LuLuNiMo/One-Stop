package com.example.user.coin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class message_content extends AppCompatActivity {
    TextView text, test;
    Button btn;
    LinearLayout ll;
    private final static String preferences_name = "preferences";
    String tmp = "";                // 暫存文字訊息
    private SQLLite_StudentDataHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_content);
        findView();
        record();


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
        write();
    }

    public void onClick(View v) {
        tmp = tmp + "\n" +text.getText().toString();
        add_mess(text.getText().toString());
        text.setText("");
     //   Toast.makeText(message_content.this,tmp,Toast.LENGTH_SHORT).show();
    }

    public void record(){
        BufferedReader br = null;
        try {
            InputStream is = getAssets().open("abc.txt");
            br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null && !text.trim().equals("")) {
                //add_mess(text);
                sb.append(text);
                sb.append("\n");
               Toast.makeText(message_content.this,text,Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(message_content.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                Toast.makeText(message_content.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void write() {
      try{
          File file = new File(getBaseContext().getFilesDir(), "abc");
          FileOutputStream outputStream;
          outputStream = openFileOutput("abc.txt", Context.MODE_PRIVATE);
          outputStream.write(tmp.getBytes());
          outputStream.close();

      //    OutputStreamWriter wr = new OutputStreamWriter(fileout);
        //  wr.write(tmp);
         // wr.close();
      }catch (IOException ex){
          Toast.makeText(message_content.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
      }
    }





    public void add_mess(String txt) {
        ll.setGravity(Gravity.RIGHT | Gravity.RIGHT);

        LinearLayout lla = new LinearLayout(this);
        lla.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        lla.setOrientation(LinearLayout.HORIZONTAL);
        ImageView img = new ImageView(this);
        img.setLayoutParams(new LinearLayout.LayoutParams(120,150));
        String url = "@drawable/stu_logo";
        int imageResource = getResources().getIdentifier(url, null, getPackageName());
        img.setImageResource(imageResource);

        TextView tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tv.setBackgroundResource(R.drawable.chat_1);
        tv.setText(txt);


        lla.addView(tv);
        lla.addView(img);
        ll.addView(lla);
    }


    void findView() {
        btn = (Button) findViewById(R.id.btn);
        text = (TextView) findViewById(R.id.text);
        ll = (LinearLayout) findViewById(R.id.contain);
        test = (TextView) findViewById(R.id.test);
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
                intent.setClass(message_content.this, MainActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }





}