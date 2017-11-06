package com.example.user.coin;

/**
 * Created by user on 2017/11/5.
 */

public class Chat_percontent {
    private int id;
    private int number;
    private String account;
    private String content;

    public Chat_percontent(int id,int number,String account,String content){
        this.id = id;
        this.number = number;
        this.account = account;
        this.content = content;
    }

    public Chat_percontent(int number,String account,String content){
        this(0,number,account,content);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setAccount(String account){this.account = account;}

    public String getAccount() {return account;}

    public void setContent(String content){this.content = content;}

    public String getContent() {return content;}


}


