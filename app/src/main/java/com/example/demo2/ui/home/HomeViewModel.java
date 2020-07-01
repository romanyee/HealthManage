package com.example.demo2.ui.home;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.demo2.ui.GetedHardwareData;


public class HomeViewModel extends ViewModel {

    //传入固定的数值
//    int heart = GetedHardwareData.HEARTBEAT;
//    int blood = GetedHardwareData.BLOODOXYGEN;
//    int score = GetedHardwareData.SLEEPQUALITY;

    //模拟随机数值
    GetedHardwareData getedHardwareData = new GetedHardwareData();
    int heart = getedHardwareData.getHeartRandom();
    int blood = getedHardwareData.getBloodRandom();
    int score = getedHardwareData.getSleepRandom();


    private MutableLiveData<String> heartBeat;
    private MutableLiveData<String> bloodOxygen;
    private MutableLiveData<String> sleepQuality;


    public HomeViewModel() {
        heartBeat = new MutableLiveData<>();
        heartBeat.setValue(String.valueOf(heart));

        bloodOxygen = new MutableLiveData<>();
        bloodOxygen.setValue(String.valueOf(blood));

        sleepQuality = new MutableLiveData<>();
        sleepQuality.setValue(String.valueOf(score));
    }

    public LiveData<String> getHeartBeat() {
        return heartBeat;
    }

    public LiveData<String> getBloodOxygen() {
        return bloodOxygen;
    }

    public LiveData<String> getSleepQuality() {
        return sleepQuality;
    }
}