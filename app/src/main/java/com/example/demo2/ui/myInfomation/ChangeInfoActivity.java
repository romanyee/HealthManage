package com.example.demo2.ui.myInfomation;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;

import android.database.Cursor;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.demo2.DBOpenHelper;
import com.example.demo2.PatientInfo;
import com.example.demo2.R;

import java.util.ArrayList;


public class ChangeInfoActivity extends Activity implements View.OnClickListener {
    DBOpenHelper dbOpenHelper;
    private EditText tv_nametmp;
    private TextView re_sextmp;
    private EditText tv_highttmp;
    private EditText tv_weighttmp;
    private TextView tv_hearttmp;
    private TextView tv_pneamopathy;
    private ImageView back;
    private TextView saveAll;
    private String phoneNum;

    private String[]sexArry=new String[]{"男","女"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeinfo);

        dbOpenHelper = new DBOpenHelper(this);


        tv_nametmp = findViewById(R.id.tv_name);
        tv_highttmp = findViewById(R.id.tv_hight);
        tv_weighttmp = findViewById(R.id.tv_weight);
        tv_hearttmp = findViewById(R.id.tv_heart);
        tv_hearttmp.setOnClickListener(this);

        tv_pneamopathy = findViewById(R.id.pneamopathy);
        tv_pneamopathy.setOnClickListener(this);

        back = findViewById(R.id.iv_back);
        back.setOnClickListener(this);


        re_sextmp=findViewById(R.id.tv_sex);
        re_sextmp.setOnClickListener(this);


        saveAll= findViewById(R.id.save);
        saveAll.setOnClickListener(this);


        Bundle dataBundle = this.getIntent().getExtras();//从当前<span style="font-family: Arial, Helvetica, sans-serif;">Activity中获得Intent，并获得数据Bundle</span>
        phoneNum = dataBundle.getString("phone");

        TextView button = findViewById(R.id.tv_phoneshow);//从根视图中查找View
        button.setText(phoneNum);



    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_heart:
                AlertDialog.Builder builder=new AlertDialog.Builder(ChangeInfoActivity.this);
                builder.setMessage("请选择");
                builder.setIcon(R.drawable.ic_notifications_black_24dp);
                builder.setNeutralButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_hearttmp.setText("是");
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_hearttmp.setText("否");
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                break;
            case R.id.pneamopathy:
                AlertDialog.Builder builder1=new AlertDialog.Builder(ChangeInfoActivity.this);
                builder1.setMessage("请选择");
                builder1.setIcon(R.drawable.ic_notifications_black_24dp);
                builder1.setNeutralButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_pneamopathy.setText("是");
                        dialog.dismiss();
                    }
                });
                builder1.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_pneamopathy.setText("否");
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog1=builder1.create();
                alertDialog1.show();
                break;
            case R.id.iv_back:
                ChangeInfoActivity.this.finish();
                break;
            case R.id.tv_sex:
                AlertDialog.Builder builder2=new AlertDialog.Builder(ChangeInfoActivity.this);
                builder2.setMessage("请选择你的性别");
                builder2.setIcon(R.drawable.ic_notifications_black_24dp);
                builder2.setNeutralButton("男", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        re_sextmp.setText(sexArry[0]);
                        dialog.dismiss();
                    }
                });
                builder2.setNegativeButton("女", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        re_sextmp.setText(sexArry[1]);
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog2=builder2.create();
                alertDialog2.show();
                break;
            case  R.id.save:

                String phone = phoneNum;
                String name = tv_nametmp.getText().toString().trim();
                String sex = re_sextmp.getText().toString().trim();
                int  hight = Integer.parseInt(tv_highttmp.getText().toString().trim());
                int weight = Integer.parseInt(tv_weighttmp.getText().toString().trim());
                String heart = tv_hearttmp.getText().toString().trim();
                String bloodOxygen = tv_pneamopathy.getText().toString().trim();

                boolean match = false;
                PatientInfo patientInfo;
                ArrayList<PatientInfo> data = dbOpenHelper.getPatientInfoAllData();
                for (int i = 0; i < data.size(); i++) {
                    patientInfo = data.get(i);
                    if (phone.equals(patientInfo.getPhone()) ){
                        match = true;
                        break;
                    }else {
                        match = false;
                    }
                }
                if (match) {
                    dbOpenHelper.updataPatientInfo(phone,name, sex,hight,weight,heart,bloodOxygen);
                }else {
                    dbOpenHelper.addPatientInfo(phone,name, sex,hight,weight,heart,bloodOxygen);
                }
                ChangeInfoActivity.this.finish();
                break;
        }

    }

}
