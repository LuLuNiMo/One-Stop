package com.example.user.coin;

import java.io.Serializable;

/**
 * Created by user on 2017/8/13.
 */

public class evaulate_item  implements Serializable {
    private int img;
    private String name;
    private int score;



    public evaulate_item(){
        super();
    }

    public evaulate_item(int img, String name, int score){
        super();
        this.img = img;
        this.name = name;
        this.score = score;
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

    public void setScore(int score){this.score = score;}

    public int getScore(){return score;}



}
