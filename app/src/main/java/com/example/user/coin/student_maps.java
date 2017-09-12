package com.example.user.coin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.MapView;

public class student_maps extends AppCompatActivity {
    private final static String preferences_name = "preferences";
    MapView mapView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_maps);
        setTitle("地圖");
        findView();
        maps_show();
    }



    public void maps_show(){
        View mapp = getLayoutInflater().inflate(R.layout.activity_maps,null);
        mapView.removeAllViews();
        mapView.addView(mapp,  ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);




    }





    public void findView(){
        mapView = (MapView)findViewById(R.id.mapView);
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
                intent.setClass(student_maps.this ,MainActivity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    private class Layouarams {
    }
}
