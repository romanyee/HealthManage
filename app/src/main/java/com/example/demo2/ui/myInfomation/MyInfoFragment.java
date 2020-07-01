package com.example.demo2.ui.myInfomation;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.demo2.LoginActivity;
import com.example.demo2.MainActivity;
import com.example.demo2.R;

public class MyInfoFragment extends Fragment  {


    TextView button;
    String phoneNum;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_myinfo, container,false);//这个就相当于你加的布局

        Bundle dataBundle = getActivity().getIntent().getExtras();//从当前<span style="font-family: Arial, Helvetica, sans-serif;">Activity中获得Intent，并获得数据Bundle</span>
        if (dataBundle != null) {
            phoneNum = dataBundle.getString("phone");
        }

        button = view.findViewById(R.id.yonghuxingming);//从根视图中查找View
        button.setText(phoneNum);


        //填写个人信息
        RelativeLayout changeInfo = view.findViewById(R.id.re_xiugai);
        changeInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle dataBundle = new Bundle();//将数据放在Bundle中
                dataBundle.putString("phone", phoneNum);
                intent.putExtras(dataBundle);
                intent.setClass(getActivity(), ChangeInfoActivity.class);
                startActivity(intent);
            }
        });


        //个人信息
        RelativeLayout infoOnclick = view.findViewById(R.id.re_myinfo);
        infoOnclick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle dataBundle = new Bundle();//将数据放在Bundle中
                dataBundle.putString("phone", phoneNum);
                intent.putExtras(dataBundle);
                intent.setClass(getActivity(), ViewInfoActivity.class);
                startActivity(intent);
            }
        });







        //注销登录
        RelativeLayout exitButton = view.findViewById(R.id.tuichu);//所以你的id都是基于你这个布局找的
        exitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("是否确定退出");
                builder.setIcon(R.drawable.ic_notifications_black_24dp);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getActivity(),LoginActivity.class);
                        startActivity(intent);
                        MainActivity.destroyActivity("MainActivity");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });




        return view;
    }

}