package com.example.user.coin;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.example.user.coin.R.id.location;

public class student_maps extends AppCompatActivity  implements LocationListener,OnMapReadyCallback {
    private final static String preferences_name = "preferences";

    TextView test;
    AutoCompleteTextView text;
    Spinner distance, range;
    private GoogleMap map;
    LocationManager mgr;
    LatLng currPoint;
    boolean isGPSEnabled;      //GPS定位是否可用
    boolean isNetworkEnabled;  //網路定位是否可用
    HashMap data;
    double la,lo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_maps);
        setTitle("地圖");
        findView();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        checkPermission();
    }


    @Override
    protected void onStart() {
        super.onStart();
        distance.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
           public void onItemSelected(AdapterView adapterView, View view, int position, long id){
               int m ;
              switch(position){
                  case 1:
                      m = 50;
                      break;
                  case 2:
                      m = 100;
                      break;
                  case 3:
                      m = 200;
                      break;
                  case 4:
                      m = 400;
                      break;
                  case 5:
                      m = 600;
                      break;
                  case 6:
                      m = 800;
                      break;
                  case 7:
                      m = 1000;
                      break;
                  case 8:
                      m = 2000;
                      break;
                  case 9:
                      m = 3000;
                      break;
                  case 10:
                      m = 3000;
                      break;
                  default:
                      m = 0;
                      break;
              }

              try{
                  if(m != 0){
                      distance_action(m,position);
                  }
              }
              catch (IOException ex){
                  test.setText(ex.getMessage());
              }

           }
           public void onNothingSelected(AdapterView arg0) {
           }
       });

        range.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                String cond;
                switch(position){
                    case 1:
                        cond = "補習班";
                        break;
                    case 2:
                        cond = "飲料店";
                        break;
                    case 3:
                        cond = "小吃店";
                        break;
                    case 4:
                        cond = "行政業務";
                        break;
                    case 5:
                        cond = "斗六";
                        break;
                    case 6:
                        cond = "斗南";
                        break;
                    case 7:
                        cond = "刺桐";
                        break;
                    case 8:
                        cond = "林內";
                        break;
                    case 9:
                        cond = "虎尾";
                        break;
                    default:
                       cond = "";
                        break;
                }
            }
            public void onNothingSelected(AdapterView arg0) {
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(googleMap.MAP_TYPE_NORMAL);
        map.moveCamera(CameraUpdateFactory.zoomTo(18));

        LatLng yunlin = new LatLng(23.695087, 120.534967);

     //   map.setMyLocationEnabled(true); // 右上角的定位功能；這行會出現紅色底線，不過仍可正常編譯執行
        map.getUiSettings().setZoomControlsEnabled(true);  // 右下角的放大縮小功能
        map.getUiSettings().setCompassEnabled(true);       // 左上角的指南針，要兩指旋轉才會出現
        map.getUiSettings().setMapToolbarEnabled(true);    // 右下角的導覽及開啟 Google Map功能
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(yunlin, 15.0f));


        try{
            if(isConnected()){
                test_data();
            }else{
                test.setText("請連線網路");
            }
        }catch(Exception ex){
            test.setText("請重新開啟app");

        }

    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null) { // 如果可以取得座標
            la =  location.getLatitude();
            lo = location.getLongitude();
            test.setText(String.format("緯度 %.4f, 經度 %.4f (%s 定位 )",
                    la,  // 目前緯度
                    lo, // 目前經度
                    location.getProvider()));// 定位方式
            currPoint = new LatLng(                //依照目前經緯度建立LatLng 物件
                    location.getLatitude(), location.getLongitude());
            if (map != null) { // 如果 Google Map 已經啟動完畢
                map.animateCamera(CameraUpdateFactory.newLatLng(currPoint)); // 將地圖中心點移到目前位置
                map.addMarker(new MarkerOptions().position(currPoint).title("目前位置")); //標記目前位置
            }
        }
        else { // 無法取得座標
            test.setText("暫時無法取得定位資訊...");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected  void onResume(){
        super.onResume();
       // enableLocationUpdates(true);    //開啟定位更新功能
    }

    @Override
    protected void onPause() {
        super.onPause();
       enableLocationUpdates(false);    //關閉定位更新功能
    }


    //test data
    public void test_data() throws IOException{
        data = new HashMap<String,String>();
        data.put("阿國獅魷魚羹","雲林縣斗六市大同路112號");
        data.put("老謝牛肉麵","雲林縣斗六市中山路424號");
        data.put("雲林科技大學圖書館","雲林縣斗六市大學路三段123號");
        data.put("國立斗六高級家事商業職業學校","林縣斗六市成功路120號");
        data.put("鎮南國小","雲林縣斗六市南揚街60號");
        data.put("多那之斗六門市","雲林縣斗六市民生南路189號");
        data.put("成功夜市","雲林縣斗六市成功路266號");
        data.put("新世紀書局","雲林縣斗六市慶生路91號");
        data.put("正泰五金行","雲林縣斗南鎮中正路316號");
        data.put("大潤發斗南店","雲林縣斗南鎮西岐里文化街119巷21號");
        data.put("雲林故事館","雲林縣虎尾鎮林森路一段528號");
        data.put("林內國小","雲林縣林內鄉中正路111號");
        data.put("莿桐國小","雲林縣莿桐鄉中正路147號");
        data.put("環球科技大學","雲林縣斗六市鎮南路1221號");
        data.put("臺中科技大學","台中市北區三民路三段129號");


        Geocoder geoCoder = new Geocoder(student_maps.this, Locale.getDefault());
        List<Address> addressLocation;

        String tt = "";
        for (Object key : data.keySet()) {
            addressLocation = geoCoder.getFromLocationName(data.get(key).toString(),1);
            tt = tt + key.toString() + ",";
            LatLng location = new LatLng( addressLocation.get(0).getLatitude(),  addressLocation.get(0).getLongitude());
            map.addMarker(new MarkerOptions().position(location).title(key.toString()));
        }
        String[] vocabulary= tt.split(",");
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line,
                vocabulary);
        text.setThreshold(1);
        text.setAdapter(adapter);


    }

   //output 2 position distance
   public void distance_action(int m,int po) throws IOException{
       Geocoder geoCoder = new Geocoder(student_maps.this, Locale.getDefault());
       enableLocationUpdates(true);

       if(isGPSEnabled || isNetworkEnabled){
           map.clear();
           int i = 0;
           LatLng me = new LatLng(la,lo);

           map.addMarker(new MarkerOptions().position(me).title("你的位置"));
           map.moveCamera(CameraUpdateFactory.newLatLngZoom(me, 15.0f));

           for (Object key : data.keySet()) {
               List<Address> addressLocation;
               addressLocation = geoCoder.getFromLocationName(data.get(key).toString(),1);
               double laa = addressLocation.get(0).getLatitude();
               double loo = addressLocation.get(0).getLongitude();
               double stance  = getDistance(la,lo,laa,loo);

               if(stance < m && po != 10){
                   LatLng location = new LatLng(laa,loo);
                   map.addMarker(new MarkerOptions().position(location).title(key.toString()));
                   i++;
               }else if(po == 10 && m < stance){
                   LatLng location = new LatLng(laa,loo);
                   map.addMarker(new MarkerOptions().position(location).title(key.toString()));
                   i ++;
               }
           }
           Toast.makeText(this, "共找到 " + String.valueOf(i) + " 筆", Toast.LENGTH_LONG).show();

       }

   }

    //測試兩點距離
    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        float[] results=new float[1];
        Location.distanceBetween(lat1, lon1, lat2, lon2, results);
        return results[0];
}




    //搜尋地址
    public void onClick(View view) throws IOException {
        Geocoder geoCoder = new Geocoder(student_maps.this, Locale.TRADITIONAL_CHINESE);
        String address = null;
        map.clear();

        if(data.get(text.getText().toString()) != null){
            address =data.get(text.getText().toString()).toString();
        }else {
            test.setText("找不到您想找的店家");
            try { //延後執行
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                              //   TODO Auto-generated catch block
                e.printStackTrace();
            }
            List<Address> addressLocation;
            for (Object key : data.keySet()) {
                addressLocation = geoCoder.getFromLocationName(data.get(key).toString(),1);
                LatLng location = new LatLng( addressLocation.get(0).getLatitude(),  addressLocation.get(0).getLongitude());
                map.addMarker(new MarkerOptions().position(location).title(key.toString()));
            }
        }


        if(address != null && !address.trim().equals("")){
           List<Address> addressLocation;
           addressLocation = geoCoder.getFromLocationName(address,1);
           if(addressLocation.size()<=0){
               test.setText("店家地址無法找到，請直接連絡店家");
           }else{
               LatLng location = new LatLng(addressLocation.get(0).getLatitude(),addressLocation.get(0).getLongitude());
               map.addMarker(new MarkerOptions().position(location).title(address));
               CameraPosition crpo = new CameraPosition.Builder().target(location).zoom(15).build();
               map.animateCamera(CameraUpdateFactory.newCameraPosition(crpo));
               addressLocation.clear();
           }
       }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 200){
            if (grantResults.length >= 1 &&
                    grantResults[0] != PackageManager.PERMISSION_GRANTED) {  // 使用者不允許權限
                Toast.makeText(this, "程式需要定位權限才能運作", Toast.LENGTH_LONG).show();
            }
        }
    }


    //開啟或關閉定位更新功能
    private void enableLocationUpdates(boolean isTurnOn) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {  // 使用者已經允許定位權限
            if (isTurnOn) {
                //檢查 GPS 與網路定位是否可用
                isGPSEnabled = mgr.isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetworkEnabled = mgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if(isNetworkEnabled){
                    Toast.makeText(this, "取得定位資訊中...", Toast.LENGTH_LONG).show();
                    mgr.requestLocationUpdates(   //向網路定位提供者註冊位置事件監聽器
                            LocationManager.NETWORK_PROVIDER, 3000, 0, this);
                }
                else if (isGPSEnabled) {
                    Toast.makeText(this, "取得定位資訊中...", Toast.LENGTH_LONG).show();
                    mgr.requestLocationUpdates(   //向 GPS 定位提供者註冊位置事件監聽器
                            LocationManager.GPS_PROVIDER, 3000,0, this);
                }

                else{
                    // 無提供者, 顯示提示訊息
                    Toast.makeText(this, "請確認已開啟定位功能!", Toast.LENGTH_LONG).show();
                }
            }
            else {
                mgr.removeUpdates(this);    //停止監聽位置事件
            }
        }
    }


    //check network connection
    private boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    //檢查若尚未授權, 則向使用者要求定位權限
    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 200);
        }
    }

    public void findView(){
        distance = (Spinner)findViewById(R.id.spr1);
        test = (TextView) findViewById(R.id.test);
        text = (AutoCompleteTextView) findViewById(R.id.tt);
        range = (Spinner)findViewById(R.id.spr2);
        mgr = (LocationManager) getSystemService(LOCATION_SERVICE);

        ArrayAdapter<CharSequence> listA= ArrayAdapter.createFromResource(student_maps.this,
                R.array.stu_map_stance,
                android.R.layout.simple_spinner_dropdown_item);

        distance.setAdapter(listA);

        ArrayAdapter<CharSequence> listB= ArrayAdapter.createFromResource(student_maps.this,
                R.array.stu_map_range,
                android.R.layout.simple_spinner_dropdown_item);

        range.setAdapter(listB);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stu_map, menu);
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
            case location:
                enableLocationUpdates(true);
                break;
            case R.id.satellite:
                item.setChecked(!item.isChecked()); // 切換功能表項目的打勾狀態
                if(item.isChecked())               // 設定是否顯示衛星圖
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                else
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.traffic:
                item.setChecked(!item.isChecked()); // 切換功能表項目的打勾狀態
                map.setTrafficEnabled(item.isChecked()); // 設定是否顯示交通圖
                break;


            case R.id.setGPS:
                Intent i = new Intent( // 利用 Intent 啟動系統的定位服務設定
                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
                break;

            case R.id.about:
                new AlertDialog.Builder(this) // 用交談窗顯示程式版本與版權聲明
                        .setTitle("關於 One_Stop地圖")
                        .setMessage("我的地圖 體驗版 v1.0\nCopyright 2017 Flag Corp.")
                        .setPositiveButton("關閉", null)
                        .show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
