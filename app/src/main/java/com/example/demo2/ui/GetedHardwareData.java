package com.example.demo2.ui;



import com.example.demo2.ui.home.GetData;

import java.util.Random;

public class GetedHardwareData {

    //硬件设备测试到的数据
    public static int HEARTBEAT = 129;
    public static int BLOODOXYGEN = 98;
    public static int SLEEPQUALITY = 87;

//    GetData getData = new GetData();
//
//
//    public int HEARTBEAT = getData.GetHeartBeat(4);
//    public int BLOODOXYGEN = 98;


    Random random = new Random();
    int heartRandom = random.nextInt(171);
    int bloodRandom = random.nextInt(101);
    int sleepRandom = random.nextInt(101);

    public int getHeartRandom() {
        return heartRandom;
    }

    public void setHeartRandom(int heartRandom) {
        this.heartRandom = heartRandom;
    }

    public int getBloodRandom() {
        return bloodRandom;
    }

    public void setBloodRandom(int bloodRandom) {
        this.bloodRandom = bloodRandom;
    }

    public int getSleepRandom() {
        return sleepRandom;
    }

    public void setSleepRandom(int sleepRandom) {
        this.sleepRandom = sleepRandom;
    }
}