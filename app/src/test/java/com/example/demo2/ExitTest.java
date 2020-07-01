package com.example.demo2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import org.junit.Test;

public class ExitTest extends Activity {
    @Test
    public void exitTest() {
        RelativeLayout tuichu = (RelativeLayout) ExitTest.this.findViewById(R.id.tuichu);
        tuichu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder=new AlertDialog.Builder(ExitTest.this);
                builder.setTitle("提示");
                builder.setMessage("是否确定退出");
                builder.setIcon(R.drawable.ic_notifications_black_24dp);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent();
                        intent.setClass(ExitTest.this,LoginActivity.class);
                        startActivity(intent);
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
    }

    public static void main(String[] args) {

    }
}
