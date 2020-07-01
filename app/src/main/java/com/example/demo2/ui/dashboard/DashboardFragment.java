package com.example.demo2.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.demo2.MainActivity;
import com.example.demo2.R;

public class DashboardFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //心率按钮
        LinearLayout heartButton = root.findViewById(R.id.aya_heartbeat);
        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HeartBeatViewActivity.class);
                startActivity(intent);
            }
        });
        //血氧饱和度按钮
        LinearLayout blood = root.findViewById(R.id.aya_bloodOxygen);
        blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgressBarViewActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
}