package com.example.demo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.demo2.ui.myInfomation.MyInfoFragment;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public String phoneNum;

    private DBOpenHelper mDBOpenHelper;
    private TextView mTvLoginactivityRegister;
    private RelativeLayout mRlLoginactivityTop;
    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    private LinearLayout mLlLoginactivityTwo;
    private Button mBtLoginactivityLogin;

    private CheckBox checkBox;
//    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);
    }
    private void initView() {
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.bt_loginactivity_login);
        mTvLoginactivityRegister = findViewById(R.id.tv_loginactivity_register);
        mRlLoginactivityTop = findViewById(R.id.rl_loginactivity_top);
        mEtLoginactivityUsername = findViewById(R.id.et_loginactivity_username);
        mEtLoginactivityPassword = findViewById(R.id.et_loginactivity_password);
        mLlLoginactivityTwo = findViewById(R.id.ll_loginactivity_two);

        checkBox = (CheckBox)findViewById(R.id.cb_remember);


        SharedPreferences sharedPreferences = getSharedPreferences("config",0);
        String name = sharedPreferences.getString("name","");
        String password = sharedPreferences.getString("password","");
        boolean checkbox = sharedPreferences.getBoolean("checkbox",false);

        mEtLoginactivityUsername.setText(name);
        phoneNum = mEtLoginactivityUsername.getText().toString().trim();
        mEtLoginactivityPassword.setText(password);
        checkBox.setChecked(checkbox);

        // 设置点击事件监听器
        mBtLoginactivityLogin.setOnClickListener(this);
        mTvLoginactivityRegister.setOnClickListener(this);



    }
    public String getTitles(){
        String name = mEtLoginactivityUsername.getText().toString().trim();
        return name;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            // 跳转到注册界面
            case R.id.tv_loginactivity_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            case R.id.bt_loginactivity_login:
                String name = mEtLoginactivityUsername.getText().toString().trim();
                String password = mEtLoginactivityPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getPhone()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        if (checkBox.isChecked()) {
                            SharedPreferences sp = getSharedPreferences("config",0);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("name", name);
                            editor.putString("password",password);
                            editor.putBoolean("checkbox",checkBox.isChecked());
                            editor.commit();
                        }

                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        Bundle dataBundle = new Bundle();//将数据放在Bundle中
                        dataBundle.putString("phone", name);
                        intent.putExtras(dataBundle);
                        intent.setClass(this, MainActivity.class);
                        startActivity(intent);

                        finish();//销毁此Activity
                    } else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}