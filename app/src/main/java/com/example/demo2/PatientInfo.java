package com.example.demo2;

public class PatientInfo {
    private String phone;
    private String tname;
    private String tsex;
    private int cm ;
    private int kg ;
    private String  IsHeartDisease;
    private String  IsPneamopathy;


    public PatientInfo(String phone, String tname, String tsex, int cm, int kg, String isHeartDisease, String isPneamopathy) {
        this.phone = phone;
        this.tname = tname;
        this.tsex = tsex;
        this.cm = cm;
        this.kg = kg;
        IsHeartDisease = isHeartDisease;
        IsPneamopathy = isPneamopathy;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTsex() {
        return tsex;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex;
    }

    public int getCm() {
        return cm;
    }

    public void setCm(int cm) {
        this.cm = cm;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    public String getIsHeartDisease() {
        return IsHeartDisease;
    }

    public void setIsHeartDisease(String isHeartDisease) {
        IsHeartDisease = isHeartDisease;
    }

    public String getIsPneamopathy() {
        return IsPneamopathy;
    }

    public void setIsPneamopathy(String isPneamopathy) {
        IsPneamopathy = isPneamopathy;
    }

    @Override
    public String toString() {
        return "PatientInfo{" +
                "phone='" + phone + '\'' +
                ", tname='" + tname + '\'' +
                ", tsex='" + tsex + '\'' +
                ", cm=" + cm +
                ", kg=" + kg +
                ", IsHeartDisease=" + IsHeartDisease +
                ", IsPneamopathy=" + IsPneamopathy +
                '}';
    }
}
