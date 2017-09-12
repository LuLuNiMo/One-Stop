package com.example.user.coin;

/**
 * Created by user on 2017/8/9.
 */

public class case_item{
    private int id;
    private String name;
    private String nature;
    private String time;
    private String pay;
    private String condition;
    private int num;
    private String content;
    private String account;
    private String area;
    private String state;
    private String attach;




    public case_item(int id,String name, String nature, String time,String pay,String condition,int num,
                     String content,String account,String area, String state,
                             String attach){

        this.id = id;
        this.name = name;
        this.nature = nature;
        this.time = time;
        this.pay = pay;
        this.condition = condition;
        this.num = num;
        this.content = content;
        this.account = account;
        this.area = area;
        this.state = state;
        this.attach = attach;
    }

    public case_item( String name, String nature, String time,String pay,String condition,
                     int num,String content,String account,String area,String state,
                     String attach){
        this(0,name,nature,time,pay,condition,num,content,account,area,state,attach);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setAccount(String account){
        this.account = account;
    }

    public String getAccount(){
        return account;
    }

    public void seArea(String area){
        this.area = area;
    }

    public String getArea(){
        return area;
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

    public void setState(String state){
        this.state =state;
    }

    public String getState(){
        return state;
    }

    public void setAttach(String attach){
        this.attach = attach;
    }

    public String getAttach(){
        return attach;
    }

    public String getAll(){
        return  String.valueOf(id)+ ",  " +name+ ",  " +nature+ ",  " +time+ ",  " +pay+ ",  " +condition+ ",  " +String.valueOf(num)+ ",  " +
         content+ ",  " +account+ ",  " +area+ ",  " + state + ",  " + attach;
    }

    public String getSearch(){
        return   name+ ",  " +nature+ ",  " +time+ ",  " +pay+ ",  " +condition+ ",  " +String.valueOf(num)+ ",  " +
                 content+ ",  " +area;
    }

}
