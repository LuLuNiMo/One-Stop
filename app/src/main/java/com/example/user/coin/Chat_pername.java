package com.example.user.coin;

/**
 * Created by user on 2017/11/5.
 */

public class Chat_pername {
    private int id;
    private String chat_a;
    private String chat_b;
    private byte[] img_a;
    private byte[] img_b;
    private String date;
    private String nature;

    public Chat_pername(int id,String chat_a,String chat_b,byte[] img_a, byte[] img_b,String date,String nature){
        this.id = id;
        this.chat_a = chat_a;
        this.chat_b = chat_b;
        this.img_a = img_a;
        this.img_b = img_b;
        this.date = date;
        this.nature = nature;
}

    public Chat_pername(String chat_a,String chat_b,byte[] img_a, byte[] img_b,String date,String nature){
        this(0,chat_a,chat_b,img_a,img_b,date,nature);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setChat_a(String chat_a) {
        this.chat_a= chat_a;
    }

    public String getChat_a() {
        return chat_a;
    }

    public void setChat_b(String chat_b) {
        this.chat_b= chat_b;
    }

    public String getChat_b() {
        return chat_b;
    }


    public void setImg_a(byte[] img_a) {
        this.img_a= img_a;
    }

    public byte[] getImg_a() {
        return img_a;
    }

    public void setImg_b(byte[] img_b) {
        this.img_b= img_b;
    }

    public byte[] getImg_b() {
        return img_b;
    }

    public void setDate(String date) {
        this.date= date;
    }

    public String getDate() {
        return date;
    }

    public void setNature(String nature) {
        this.nature= nature;
    }

    public String getNature() {
        return nature;
    }

}
