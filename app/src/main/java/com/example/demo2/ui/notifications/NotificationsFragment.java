package com.example.demo2.ui.notifications;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.demo2.MainActivity;
import com.example.demo2.R;
import com.example.demo2.ui.home.HomeViewModel;


public class NotificationsFragment extends Fragment {
     HomeViewModel homeViewModel;
    MainActivity main = new MainActivity();

    LinearLayout ll;
    LinearLayout bloodLL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);//这个就相当于你加的布局
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);




        ll = view.findViewById(R.id.ll_heartbeatNotify);
        //心率显示
        final TextView heartbeat = view.findViewById(R.id.show_heartbeatNotify);
        homeViewModel.getHeartBeat().observe(this, new Observer<String>() {
            @Override
            public void onChanged( String s) {
                int num = Integer.parseInt(s);
                if (num >= 120) {
                    heartbeat.setText("心率(次/分钟)："+s+" 异常偏高");
                    ll.setBackgroundColor(NotificationsFragment.this.getResources().getColor(R.color.red));
                } else if (num >= 60 && num < 120) {
                }else {
                    heartbeat.setText("心率(次/分钟)："+s+"异常偏低");
                    ll.setBackgroundColor(NotificationsFragment.this.getResources().getColor(R.color.gray));
                }


            }
        });

        bloodLL = view.findViewById(R.id.ll_bloodNotify);
        //血氧饱和度显示
        final TextView bloodOxygen = view.findViewById(R.id.show_bloodNotify);
        homeViewModel.getBloodOxygen().observe(this, new Observer<String>() {
            @Override
            public void onChanged( String s) {
                int num = Integer.parseInt(s);
                if (num >= 95) {

                } else if (num >= 90 && num < 95) {
                    bloodLL.setBackgroundColor(NotificationsFragment.this.getResources().getColor(R.color.colorPrimary));
                    bloodOxygen.setText("血氧饱和度："+s+"% 供氧不足");
                } else if (num < 90 && num >= 85) {
                    bloodOxygen.setText("血氧饱和度："+s+"% 轻度低氧血氧症");
                    bloodLL.setBackgroundColor(NotificationsFragment.this.getResources().getColor(R.color.progress_color_1));
                } else if (num < 85) {
                    bloodOxygen.setText("血氧饱和度："+s+"% 重度低氧血氧症");
                    bloodLL.setBackgroundColor(NotificationsFragment.this.getResources().getColor(R.color.progress_color_2));
                }else if (num<80){
                    bloodOxygen.setText("血氧饱和度："+s+"% 低氧");
                    bloodLL.setBackgroundColor(NotificationsFragment.this.getResources().getColor(R.color.red));
                }
            }
        });

        //睡眠质量显示
        final TextView sleepQuality = view.findViewById(R.id.show_sleepNotify);
        homeViewModel.getSleepQuality().observe(this, new Observer<String>() {
            @Override
            public void onChanged( String s) {
                sleepQuality.setText("睡眠质量："+s+"分");
            }
        });

        return view;
    }









}