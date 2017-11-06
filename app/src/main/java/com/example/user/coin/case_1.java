package com.example.user.coin;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class case_1 extends Fragment {
    TextView name,content,condition,pay,time,num,area,mess; //case_1
    private final static String preferences_name = "preferences";

    Spinner spr;
    Button re,ent,tt,dd;
    private SQLLite_StudentDataHelper helper;
    String data = "  "; //try case_item data recept
    String account;
    String datee,timee = "  ";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_case_1, container, false);
        name = (TextView) v.findViewById(R.id.c1);
        content = (TextView) v.findViewById(R.id.c3);
        condition = (TextView) v.findViewById(R.id.c4);
        time = (TextView)v.findViewById(R.id.c2);
        pay = (TextView) v.findViewById(R.id.c5);
        num = (TextView) v.findViewById(R.id.c6);
        area = (TextView) v.findViewById(R.id.c7);
        mess = (TextView) v.findViewById(R.id.message);
        spr = (Spinner) v.findViewById(R.id.spr_case);
        re = (Button) v.findViewById(R.id.rewrite);
        ent = (Button) v.findViewById(R.id.center);
        tt = (Button) v.findViewById(R.id.time);
        dd = (Button) v.findViewById(R.id.datee);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.case_item,android.R.layout.simple_spinner_dropdown_item);
        spr.setAdapter(adapter);



        // 日期選擇器
        dd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setDate();
            }
        });
        // 時間選擇器
        tt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setTime();
            }
        });

        //清空資料 button-- rewrite
        re.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clear();
            }
        });

        ent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mess.setText("");
               if(isValid()){
                   add_caseitem();
               }
            }
        });
        return v;
    }


    public static case_1 newInstance() {
        case_1 f = new case_1();
        return f;
    }

    //清空資料 button-- rewrite
    public void clear(){
        name.setText("");
        content.setText("");
        condition.setText("");
        time.setText("");
        pay.setText("");
        spr.setSelection(0);
        num.setText("");
        area.setText("");
        mess.setText("");
    }

    private Calendar m_Calendar = Calendar.getInstance();
    private Calendar h_Calendar = Calendar.getInstance();

   // fragment 的日期選擇器
    public void setDate(){
        DatePickerDialog dialog =
                new DatePickerDialog(getActivity(),
                        datepicker,
                        m_Calendar.get(Calendar.YEAR),
                        m_Calendar.get(Calendar.MONTH),
                        m_Calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    // fragment 的時間選擇器
    public void setTime(){
        TimePickerDialog dialog = new TimePickerDialog(getActivity(),timepicker,
                h_Calendar.get(Calendar.HOUR_OF_DAY),
                h_Calendar.get(Calendar.MINUTE),
               true);
             dialog.show();
    }


    // fragment 的日期選擇器 設定
    DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            m_Calendar.set(Calendar.YEAR, year);
            m_Calendar.set(Calendar.MONTH, monthOfYear);
            m_Calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "yyyy/MM/dd"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.TAIWAN);
            datee = sdf.format(m_Calendar.getTime());
            time.setText(datee + "  " + timee);
        }
    };

    // fragment 的時間選擇器 設定
    TimePickerDialog.OnTimeSetListener timepicker = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            h_Calendar.set(Calendar.YEAR, hourOfDay);
            h_Calendar.set(Calendar.YEAR,minute);


            String myFormat = "HH:mm";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.TAIWAN);
            timee =sdf.format(m_Calendar.getTime());
            time.setText(datee + "  " + timee);
        }
    };




    public void add_caseitem(){
        try{
            find_id();
            if(input_recept()){
                Long test = helper.insert_case(input());
                if(test != -1){
                    Toast.makeText(getContext(),"新增成功",Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getContext(),data.getAll(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"新增失敗，請按下重填並重新輸入",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getContext(),"重複CASE囉",Toast.LENGTH_SHORT).show();
            }
            if (helper != null) {
                helper.close();
            }

        }catch (Exception ex){
            mess.setText(ex.getMessage());
        }
    }




    //input data
    public case_item input(){
        case_item list;
        String na = name.getText().toString();
        String nat = spr.getSelectedItem().toString();
        String  tt = time.getText().toString();
        String pp = pay.getText().toString();
        String cond = condition.getText().toString();
        int n = Integer.valueOf(num.getText().toString());
        String cont  = content.getText().toString();
        String ar = area.getText().toString();
        String sta = "未解決";
        String att = "";

        list = new case_item(na,nat,tt,pp,cond,n,cont,account,ar,sta,att,"","");


        return list;
    }

    //避免重複CASE
    public boolean input_recept(){
        boolean ans = true;
        if((!data.trim().equals("")) && data.equals(input().getAll())){
            ans = false;
        }

        data = input().getAll();
        return ans;
    }



    //scearch student_data
    public void find_id(){
        if (helper == null) {
            helper = new SQLLite_StudentDataHelper(getActivity());
        }
        SharedPreferences shared = getActivity().getSharedPreferences(preferences_name,MODE_PRIVATE);
        account = shared.getString("account","0");

        if(account.equals("0")){
           Toast.makeText(getActivity(),"系統出現錯誤，請按下登出重新登入",Toast.LENGTH_SHORT).show();
       }

    }




    //驗證資料
    public boolean isValid(){
        boolean vaild = false;
        boolean v1;
        boolean v2;
        boolean v3;
        boolean v4;
        boolean v5;
        boolean v6;
        boolean v7;

        String a = name.getText().toString();
        if(a == null || a.trim().equals("")){
            v1 = false;
            name.setError("標題不可空值");
        }else{
            v1 = true;
        }

        if(spr.getSelectedItemPosition() == 0 ){
            v2 = false;
            Toast.makeText(getContext(),"請選擇性質",Toast.LENGTH_SHORT).show();
        }else{
            v2 = true;
        }

        String b = time.getText().toString();
        if(b == null || b.trim().equals("")){
            v3 = false;
            time.setError("必須設置日期");
        }else{
            v3 = true;
        }

        String c =content.getText().toString();
        if(c == null || c.trim().equals("")){
            v4 = false;
            content.setError("必須輸入內容");
        }else{
            v4 = true;
        }

        String d =num.getText().toString();
        if(d == null || d.trim().equals("")){
            v5 = false;
            num.setError("人數請記得輸入");
        }else{
            v5 = true;
        }

        String e =pay.getText().toString();
        if(e == null || e.trim().equals("")){
            v6 = false;
            pay.setError("請輸入報酬");
        }else{
            v6 = true;
        }

        String f =area.getText().toString();
        if(f == null || f.trim().equals("")){
            v7 = false;
            area.setError("請輸入地點");
        }else{
            v7 = true;
        }


        if(v1 && v2 && v3 && v4 && v5 && v6 && v7 ){
            vaild = true;
        }
        return vaild;
    }





}
