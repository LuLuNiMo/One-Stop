package com.example.user.coin;
import android.view.View;
import java.io.Serializable;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import static android.graphics.Color.argb;

/**
 * Created by user on 2017/8/16.
 */

public class stu_job_mange_item implements Serializable{
    private int img;
    private String name;
    private String content;
    private String condition;
    private String pay;
    private String time;
    private String nature;
    private int num;
    private String address;
    private String state;


    public stu_job_mange_item(){
        super();
    }


    public stu_job_mange_item(int img, String name, String nature, String time,String pay,String condition,String content,int num,String address,String state){
        this.img = img;
        this.name = name;
        this.nature = nature;
        this.time = time;
        this.pay = pay;
        this.num = num;
        this.condition = condition;
        this.content = content;
        this.address = address;
        this.state = state;
    }


    public void setImg(int img){
        this.img = img;
    }

    public int getImg(){
        return img;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setContent(String content){
        this.content = content;
    }

    public  String getContent(){
        return content;
    }

    public void setCondition(String condition){
        this.condition= condition;
    }

    public String getCondition(){
        return condition;
    }

    public void setPay(String pay){
        this.pay = pay;
    }

    public String getPay(){
        return pay;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime(){
        return time;
    }

    public void setNature(String nature){
        this.nature = nature;
    }

    public String getNature(){
        return nature;
    }

    public void setNum(int num){
        this.num = num;
    }

    public int getNum(){
        return num;
    }


    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setState(String state){
        this.state= state;
    }

    public String getState(){
        return state;
    }

    public void changeTextColor(TextView tv){
        String text = tv.getText().toString();
        if(text.equals("未讀")){
            tv.setTextColor(argb(255,255,0,0));
        }else{
            tv.setTextColor(argb(255,0,0,0));
        }

    }



}



