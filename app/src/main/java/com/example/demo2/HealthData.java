package com.example.demo2;

public class HealthData {
    private String phone;
    private int HR;
    private int SpO2;

    public HealthData(String phone, int HR, int spO2) {
        this.phone = phone;
        this.HR = HR;
        SpO2 = spO2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getHR() {
        return HR;
    }

    public void setHR(int HR) {
        this.HR = HR;
    }

    public int getSpO2() {
        return SpO2;
    }

    public void setSpO2(int spO2) {
        SpO2 = spO2;
    }

    @Override
    public String toString() {
        return "HealthData{" +
                "phone='" + phone + '\'' +
                ", HR=" + HR +
                ", SpO2=" + SpO2 +
                '}';
    }
}
