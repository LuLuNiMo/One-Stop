package com.example.user.coin;
/**
 * Created by user on 2017/8/25.
 */

public class student_data {
    private int id;
    private String account;
    private String password;
    private String name;
    private String phone;
    private byte[] image;
    private String school;
    private String department;
    private String stu_id;
    private String sex;
    private String age;
    private String mail;
    private String speciality;
    private String wish;
    private String history;
    private byte[] photo;
    private int score;


    public student_data(String account,String password,String name,String phone,byte[] image,String school,String department,String stu_id
            ,String sex,String age,String mail,String speciality,String wish,String history,byte[] photo){
        this(0,account,password,name,phone,image,school,department,stu_id,sex,age,mail,speciality,wish,history,photo,0);
    }


    public student_data(int id,String account,String password,String name,String phone,byte[] image,String school,String department,String stu_id
            ,String sex,String age,String mail,String speciality,String wish,String history,byte[] photo,int score){
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.school=school;
        this.department = department;
        this.stu_id = stu_id;
        this.sex = sex;
        this.age = age;
        this.mail = mail;
        this.speciality = speciality;
        this.wish = wish;
        this.history = history;
        this.photo = photo;
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getWish() {
        return wish;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getHistory() {
        return history;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }


    public String getAll(){
        String ans = String.valueOf(id) + " , "+ account + " , "+ password + " , "+ name + " , "+ phone + " , "+ school + " , "+ department + " , "+ stu_id
                + " , "+ sex + " , "+ String.valueOf(age) + " , "+ mail + " , "+ speciality + " , "+ wish + " , "+ history;
        return ans;
    }


    public String show_data(){
        String ans = " 帳號："+ account +"\n"+"姓名：" + name + "\n "+ "手機號碼："+ phone + "\n "+"學校："+ school + "\n "+"系級：" + department
                + " \n"+  " 性別： "+ sex + " \n "+  " 專題： "+ speciality + "\n"+ "經歷：" + history;
        return ans;

    }

}



