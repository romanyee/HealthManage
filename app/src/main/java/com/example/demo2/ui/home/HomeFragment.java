package com.example.demo2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.demo2.R;



//        （1） 配对蓝牙设备
//
//       （2） 连接蓝牙设备，根据MAC地址，代码中修改
//
//       （3） 接收数据
//
//       （4） 处理数据，根据硬件厂商提供给你的数据转换公式，在BluetoothService类中 修改
//
//       （5） 传数据给折线图，展现实时变化
public class HomeFragment extends Fragment {

    HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);//这个就相当于你加的布局
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        Button bindevice = view.findViewById(R.id.bindDevice);//所以你的id都是基于你这个布局找的
        bindevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), GetBluetoothMac.class);
//                startActivity(intent);
            }
        });

        //心率显示
        final TextView heartbeat = view.findViewById(R.id.ttv_heartBeat);
        homeViewModel.getHeartBeat().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                    heartbeat.setText(s);
            }
        });

        //血氧饱和度显示
        final TextView bloodOxygen = view.findViewById(R.id.tv_bloodOxygen);
        homeViewModel.getBloodOxygen().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                bloodOxygen.setText(s);
            }
        });

        //睡眠质量显示
        final TextView sleepQuality = view.findViewById(R.id.tv_sleep);
        homeViewModel.getSleepQuality().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                sleepQuality.setText(s);
            }
        });

        return view;
    }
}