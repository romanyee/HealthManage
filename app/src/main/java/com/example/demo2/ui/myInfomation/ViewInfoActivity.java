package com.example.demo2.ui.myInfomation;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo2.DBOpenHelper;
import com.example.demo2.PatientInfo;
import com.example.demo2.R;

import java.util.ArrayList;

public class ViewInfoActivity extends Activity  {
    DBOpenHelper dbOpenHelper;
     String name;
     String sex;
     int hight;
     int weight;
     String heart;
     String feibing;
    String phoneNum;


    private ImageView back;
    private TextView showNametmp;
    private TextView showSextmp;
    private TextView showHighttmp;
    private TextView showWeighttmp;
    private TextView showHearttmp;
    private TextView showPneamopathytmp;
    PatientInfo patientInfo;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_showinfo);

        dbOpenHelper = new DBOpenHelper(this);


        Bundle dataBundle = this.getIntent().getExtras();//从当前<span style="font-family: Arial, Helvetica, sans-serif;">Activity中获得Intent，并获得数据Bundle</span>
        if (dataBundle != null) {
            phoneNum = dataBundle.getString("phone");
        }

        boolean match = false;
        ArrayList<PatientInfo> data = dbOpenHelper.getPatientInfoAllData();
        for (int i = 0; i < data.size(); i++) {
            patientInfo = data.get(i);
            if (phoneNum.equals(patientInfo.getPhone()) ){
                match = true;
                break;
            }else {
                match = false;
            }
        }
        if (match) {
            name = patientInfo.getTname().trim();
            sex = patientInfo.getTsex().trim();
            hight = patientInfo.getCm();
            weight = patientInfo.getKg();
            heart = patientInfo.getIsHeartDisease().trim();
            feibing = patientInfo.getIsPneamopathy().trim();
        }


        showNametmp = findViewById(R.id.showName);
        showNametmp.setText(name);
        showSextmp = findViewById(R.id.showSex);
        showSextmp.setText(sex);
        showHighttmp = findViewById(R.id.showHight_tv);
        showHighttmp.setText(String.valueOf(hight));

        showWeighttmp = findViewById(R.id.showWeight);
        showWeighttmp.setText(String.valueOf(weight));

        showHearttmp = findViewById(R.id.showHeart);
        showHearttmp.setText(heart);

        showPneamopathytmp = findViewById(R.id.showPneamopathy);
        showPneamopathytmp.setText(feibing);


        back = findViewById(R.id.view_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewInfoActivity.this.finish();

            }
        });

    }

//    public void onClick(View view) {
//
//        switch (view.getId()) {
//            case R.id.view_back:
//                ViewInfoActivity.this.finish();
//                break;
//            case R.id.showName:
//                showNametmp.setText(name);
//                break;
//            case R.id.showSex:
//                showSextmp.setText(sex);
//                break;
//            case R.id.showHight_tv:
//                showHighttmp.setText(String.valueOf(hight));
//                break;
//            case R.id.showWeight:
//                showWeighttmp.setText(String.valueOf(weight));
//
//                break;
//            case R.id.showHeart:
//                showHearttmp.setText(heart);
//
//                break;
//            case R.id.showPneamopathy:
//                showPneamopathytmp.setText(feibing);
//
//                break;
//
//
//
//        }
//    }
}
