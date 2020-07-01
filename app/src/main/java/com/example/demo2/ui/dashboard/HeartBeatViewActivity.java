package com.example.demo2.ui.dashboard;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.example.demo2.LoginActivity;
import com.example.demo2.MainActivity;
import com.example.demo2.R;
import com.example.demo2.ui.GetedHardwareData;
import com.example.demo2.ui.home.HomeViewModel;


public class HeartBeatViewActivity extends Activity {
    private HeartBeatView clock_view2;
    HomeViewModel homeViewModel;
    TextView showRelut;
    TextView showText1;
    TextView showText2;

    ImageView backMain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockview);

        clock_view2 =  findViewById(R.id.clock_view);
        clock_view2.setCompleteDegree(GetedHardwareData.HEARTBEAT);      //设置指针最终位置，附带动画效果

        showRelut = findViewById(R.id.tv_showHeartResult);
        showRelut.setMovementMethod(ScrollingMovementMethod.getInstance());
        int numHeart = GetedHardwareData.HEARTBEAT;
        if (numHeart >= 120) {
            showRelut.setText("心率(次/分钟)："+numHeart+ "，心率异常偏高。\n" +
                    "\t\t成人安静时心率超过100次/分钟(一般不超过160次/分钟)，称为窦性心动过速。\n" +
                    "\t\t常见于兴奋、激动、吸烟、饮酒、喝浓茶或咖啡后；\n" +
                    "\t\t或见于感染、发热、休克、贫血、缺氧、甲亢、心力衰竭等病理状态下；\n" +
                    "\t\t或见于应用阿托品、肾上腺素、麻黄素等药物后。\n");
        } else if (numHeart >= 60 && numHeart < 120) {
            showRelut.setText("心率(次/分钟)："+numHeart+"，心率正常！");
        }else {
            showRelut.setText("心率(次/分钟)："+numHeart+"。心率异常偏低。\n" +
                    "\t\t成人安静时心率低于60次/分钟(一般在45次/分钟以上)，称为窦性心动过缓。\n" +
                    "\t\t可见于长期从事重体力劳动的健康人和运动员；\n" +
                    "\t\t或见于甲状腺机能低下、颅内压增高、阻塞性黄疸以及洋地黄、奎尼丁或心得安类药物过量。\n" +
                    "\t\t如果心率低于40次/分钟，应考虑有病态窦房结综合征、房室传导阻滞等情况。\n" +
                    "\t\t如果脉搏强弱不等、不齐且脉率少于心率，应考虑心房纤颤。\n");
        }

        showText1 = findViewById(R.id.tv_heartText);
//        showText1.setMovementMethod(ScrollingMovementMethod.getInstance());
        showText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(HeartBeatViewActivity.this);
                builder.setTitle("心率");
                builder.setMessage("\t\t心率是指正常人安静状态下每分钟心跳的次数，" +
                        "也叫安静心率，-般为60 ~ 100次/分，可因年龄、性别或其他生理因素产生个体差异。\n" +
                        "\t\t一般来说，年龄越小，心率越快，老年人心跳比年轻人慢，女性的心率比同龄男性快，这些都是正常的生理现象。\n" +
                        "\t\t安静状态下，成人正常心率为60 ~ 100次/分钟，理想心率应为55 ~ 70次分钟(运动员的心率较普通成人偏慢，一般为50次/分钟左右) 。\n");
                builder.setIcon(R.drawable.ic_notifications_black_24dp);

                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });



        //返回分析页面
        backMain = findViewById(R.id.iv_heartBeatAnayactivity_back);
        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeartBeatViewActivity.this.finish();
            }
        });


    }

    public void onClick2(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("正确看待窦性心动过缓?");
        builder.setMessage("\t\t很多人都会有窦性心动过缓伴不齐，对于多数人来说是正常的，不必过于担心。\n" +
                "\t\t窦性心动过缓是指心率低于60次/分钟的人，是否会出现此症状，与其心跳过缓的频率和引起心跳过缓的原因有关。\n" +
                "\t\t在安静状态下，成年人的心率若在50 ~ 60次/分钟之间一般不会出现明显症状。" +
                "尤其是一些训练有素的运动员以及长期从事体力劳动的人，在安静状态下即使其心率在40次/分钟左右也不会出现明显症状。" +
                "但是一般人的心率若在40 ~ 50次/分钟之间，就会出现胸闷、乏力、头晕等症状。\n" +
                "\t\t若其心率降至35 ~ 40次/分钟则会发生血流动力学改变,使心脑器官的供血受到影响，从而出现胸部闷痛、头晕、晕厥甚至猝死。\n" +
                "\t\t如果自我感觉没有任何不适，不用去理会所说的“窦性心动过缓伴不齐”，" +
                "但如果出现胸闷、乏力、头晕等不适症状，应立即到医院进一步检查，比如动态心电图、心脏彩超等检查，" +
                "了解心动过缓的病因。\n" +
                "\t\t如果心跳过慢，可以通过安装心脏起搏器缓解症状，改善预后。\n");
        builder.setIcon(R.drawable.ic_notifications_black_24dp);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void onClick3(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("日常保健");
        builder.setMessage("心率增快危害健康、缩短寿命，增加心血管病的发病率和死亡率。如何才能使静息心率能保持在60次/分钟左右：\n" +
                "1.运动\n" +
                "\t\t常参加各种强度适宜的运动，就会使静息心率变慢。虽然运动时心率加快,但运动能使心功能得到锻炼，" +
                "从而使静息心率减慢。一般适宜的运动心率是“170-年龄”，如一个50岁的人，运动心率控制在120次/分钟为宜，" +
                "过快说明运动量过大，达不到也起不到效果。运动前要自觉舒适、无疲劳感，一般运动不要超过1小时，" +
                "而且每次最佳时间为30分钟~ 60分钟，每周至少坚持3次运动。\n" +
                "2.改正不良的生活方式\n" +
                "\t\t熬夜、吸烟、饮酒均可使静息心率加快。少喝浓茶，特别是不要在睡前喝，否则容易导致失眠。还应定时大便，保持排便顺畅。\n" +
                "3.保持适当体重\n" +
                "\t\t肥胖会使心脏负担加重，心率加快，因此肥胖者要通过健身运动，调节饮食来保持适宜的体重。\n" +
                "4.保持心态平和\n" +
                "\t\t生活中心态要平和，不要总着急、生气，如果因为紧张、生气等情况出现心率过快，可以通过听音乐、静心冥想等方式逐渐恢复平静。\n" +
                "5.药物治疗\n" +
                "\t\t某些疾病如高血压及冠心病引起的心率加快，可根据医嘱服用药物，使心率减慢，保护心脏，减少与控制心肌缺血事件，改善心功能，延长寿命。\n");
        builder.setIcon(R.drawable.ic_notifications_black_24dp);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}

