package com.example.user.coin;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import java.io.Serializable;
/**
 * Created by user on 2017/8/17.
 */

public class group_item  implements Serializable{
    private int img;
    private String name;
    private String data;


    public group_item(){
        super();
    }

    public group_item(int img,String name,String data){
        this.img = img;
        this.name = name;
        this.data = data;
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

    public void setData(String data){
        this.data = data;
    }

    public String getData(){
        return data;
    }


}
