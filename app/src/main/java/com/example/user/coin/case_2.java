package com.example.user.coin;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class case_2 extends Fragment implements SearchView.OnQueryTextListener{ //searchview
    ListView list ;
    TextView message;
    SearchView search;
    private SQLLite_StudentDataHelper helper;
    private final static String preferences_name = "preferences";
    private String account;
    private List<case_item> ans_case =  new ArrayList<>();
    List<case_item> search_case;

    public case_2() {
        // Required empty public constructor
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(helper == null){
            helper = new SQLLite_StudentDataHelper(getActivity());
        }



    }

    public void onDestroy() {
        if(helper != null){
            helper.close();
        }
        super.onDestroy();
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_case_2, container, false);
        list = (ListView)view.findViewById(R.id.list_view);
        search = (SearchView)view.findViewById(R.id.case_search);
        message = (TextView)view.findViewById(R.id.message);

        search_case  = case_item_all();
        ans_case = case_item_all();


        try{
            CaseAdapter adapt = new CaseAdapter(getActivity().getBaseContext());
            list.setAdapter(adapt);
            adapt.notifyDataSetChanged();
            list.setTextFilterEnabled(true);
        }catch(Exception ex){
            message.setText(ex.getMessage());
        }

        search.setIconifiedByDefault(false);
        search.setSubmitButtonEnabled(true);
        search.setOnQueryTextListener(this);

        SharedPreferences shared = getActivity().getSharedPreferences(preferences_name,MODE_PRIVATE);
        account = shared.getString("account","0");


        return view;
    }


    public static case_2 newInstance() {
        case_2 f = new case_2();
        return f;
    }

    //输入text时激发该方法
    @Override
    public boolean onQueryTextChange(String newText) {
        // TODO Auto-generated method stub
        if(TextUtils.isEmpty(newText)) {
            list.clearTextFilter();
            ans_case =case_item_all();
        }else{
            list.setFilterText(newText);
        }
        return true;
    }

    //单击搜索按钮时激发该方法
    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Auto-generated method stub
        try{
            ans_case.clear();
            for(int i = 0;i< search_case.size();i++){
                String id = search_case.get(i).getSearch() + "," + stu_data_search(search_case.get(i).getAccount()).getName();
                String[] idd = id.split(",");
                for(int j = 0;j<idd.length;j++){
                    if(idd[j].indexOf(query) != -1){
                        ans_case.add(search_case.get(i));
                    }
                }
            }
            if(ans_case.isEmpty()){
                Toast.makeText(getActivity(),"No Found",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            message.setText(ex.getMessage());
        }

        return true;
    }

    //show all case_item
    private List<case_item> case_item_all(){
        List<case_item> caseList;
        caseList = helper.getAllCase_state("未解決");
        return caseList;
    }


    //show case_item 相對應 student_data
    private student_data stu_data_search(String account){
        student_data data;
        data = helper.findById_data(account);
        return data;
    }



    //listView method
    private class CaseAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;

        //產生資料區
        public CaseAdapter(Context context){
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return ans_case.size();
        }

        @Override
        public Object getItem(int position) {
             return ans_case.get(position);
        }

        @Override
        public long getItemId(int position) {
            return ans_case.get(position).getId();
        }


        //載入資料
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.case_listview, parent, false);
            }

            final case_item caseitem = ans_case.get(position);
            final student_data data =  stu_data_search(caseitem.getAccount());

            Bitmap image = BitmapFactory.decodeByteArray(data.getImage(), 0,
                    data.getImage().length);
            ImageView img = (ImageView)convertView.findViewById(R.id.imgview);
            img.setImageBitmap(image);


            TextView titleId = (TextView) convertView
                    .findViewById(R.id.title);
            titleId.setText(String.valueOf(caseitem.getName()));

            TextView tmId = (TextView) convertView
                    .findViewById(R.id.time);
            tmId.setText(String.valueOf(caseitem.getTime()));

            TextView payId = (TextView) convertView
                    .findViewById(R.id.pay);
            payId.setText(String.valueOf(caseitem.getPay()));

            TextView condId = (TextView) convertView
                    .findViewById(R.id.cond);
            condId.setText(String.valueOf(caseitem.getCondition()));


            TextView contId = (TextView) convertView
                    .findViewById(R.id.cont);
            contId.setText(String.valueOf(caseitem.getContent()));


            //接case method
            Button catch_you =(Button) convertView
                    .findViewById(R.id.catch_case);
            catch_you.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent();
                    bundle.putInt("case_no",caseitem.getId());
                    intent.setClass(getActivity(),student_case_content.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            return convertView;
        }

    }



}
