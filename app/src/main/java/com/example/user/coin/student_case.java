package com.example.user.coin;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static android.graphics.Color.argb;


public class student_case extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private final static String preferences_name = "preferences";
    ViewPager viewPager;
    FragmentPagerAdapter AdapterViewPager;
    Button btn1,btn2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_case);
        setTitle("CASE");
        findView();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        AdapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(AdapterViewPager);
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    //以下三個methods為implements ViewPager.OnPageChangeListener所繼承下來的抽象方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // viewPager.setVisibility(View.VISIBLE);
        changeTab(position);
    }

    @Override
    public void onPageSelected(int position) {
        viewPager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    // change Fragment method
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0:
                    return case_2.newInstance();
                case 1:
                    return case_1.newInstance();
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    //button change color method
    private void changeTab(int num) {
        switch(num){
            case 0:
                chg_btn_colorB(btn1);
                chg_btn_colorW(btn2);
                break;
            case 1:
                chg_btn_colorW(btn1);
                chg_btn_colorB(btn2);
                break;
            default:
                break;
        }
    }


    //換頁特效+frgment
    //使用button換頁
    public void changeCase(View view){
        //viewPager.setVisibility(View.INVISIBLE);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        Fragment fragment=manager.findFragmentById(R.id.fragment);
        case_1 caseA = case_1.newInstance();
        case_2 caseB = case_2.newInstance();


        switch (view.getId()){
            case R.id.case_pop:
                if (fragment == null) {
                    transaction.add(R.id.fragment, caseB,"fragmentA");
                } else{
                    transaction.replace(R.id.fragment, caseB,"fragmentA");
                }
                chg_btn_colorW(btn2);
                chg_btn_colorB(btn1);
                break;

            case R.id.case_push:
                if (fragment == null) {
                    transaction.add(R.id.fragment, caseA, "fragmentB");
                } else {
                    transaction.replace(R.id.fragment, caseA,"fragmentB");
                }
                chg_btn_colorB(btn2);
                chg_btn_colorW(btn1);
                break;
        }
        transaction.commit();

    }


    //btn change color
    public void chg_btn_colorW(Button btn){
        btn.setBackgroundColor(argb(255,255,255,255));
        btn.setTextColor(argb(255,0,0,0));
    }
    public void chg_btn_colorB(Button btn){
        btn.setBackgroundColor(argb(255,0,0,0));
        btn.setTextColor(argb(255,255,255,255));
    }



    //case_menu
    public void findView(){
       btn1 = (Button)findViewById(R.id.case_push);
       btn2 = (Button)findViewById(R.id.case_pop);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stu_menu, menu);
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
                intent.setClass(student_case.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.manage:
                intent.setClass(student_case.this, student_case_manage.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}


























