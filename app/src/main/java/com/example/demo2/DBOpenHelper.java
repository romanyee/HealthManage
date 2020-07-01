package com.example.demo2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


//工具类
public class DBOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public DBOpenHelper(Context context) {
        super(context,"db_test", null, 1);
        db=getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,name TEXT ,password TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS patientInfo(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "phone TEXT ," +
                "Tname TEXT," +
                "Tsex TEXT," +
                "cm INT," +
                "kg INT," +
                "IsHeartDisease TEXT," +
                "IsPneamopathy TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS healthData(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "phone TEXT ,"+
                "HR INT," +
                "SpO2 INT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS patientInfo");
        db.execSQL("DROP TABLE IF EXISTS healthData");
        onCreate(db);
    }

    //插入数据
    public void add(String phone,String password){
        db.execSQL("INSERT INTO user(name,password) VALUES(?,?)",new Object[]{phone,password});
    }
    public void addPatientInfo(String phone,String Tname, String Tsex, int cm, int kg, String IsHeartDisease, String IsPneamopathy) {
        db.execSQL("INSERT INTO patientInfo(phone,Tname,Tsex,cm,kg,IsHeartDisease,IsPneamopathy) VALUES(?,?,?,?,?,?,?)", new Object[]{phone, Tname, Tsex, cm, kg, IsHeartDisease, IsPneamopathy});

    }
    public void addHealthData(String phone,int HR,int SpO2){
        db.execSQL("INSERT INTO healthData(phone,HR,SpO2) VALUES(?,?,?)",new Object[]{phone,HR,SpO2});
    }

    //删除用户
    public void delete(String phone,String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+phone+password);
    }

//    public void deletePatientInfo(String Tname,String Tsex,int cm, int kg, String IsHeartDisease, String IsPneamopathy){
//        db.execSQL("DELETE FROM patientInfo WHERE Tname = `rm`");
//    }


    //更新信息
    public void updata(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }
    public void updataPatientInfo(String phone,String Tname,String Tsex,int cm, int kg, String IsHeartDisease, String IsPneamopathy){
        db.execSQL("UPDATE patientInfo SET Tname = ? WHERE phone = ?",new Object[]{Tname,phone});
        db.execSQL("UPDATE patientInfo SET Tsex = ? WHERE phone = ?",new Object[]{Tsex,phone});
        db.execSQL("UPDATE patientInfo SET cm = ? WHERE phone = ?",new Object[]{cm,phone});
        db.execSQL("UPDATE patientInfo SET kg = ? WHERE phone = ?",new Object[]{kg,phone});
        db.execSQL("UPDATE patientInfo SET IsHeartDisease = ? WHERE phone = ?",new Object[]{IsHeartDisease,phone});
        db.execSQL("UPDATE patientInfo SET IsPneamopathy = ? WHERE phone = ?",new Object[]{IsPneamopathy,phone});
    }
    public void updataHealthData(int HR,int SpO2){
        db.execSQL("UPDATE healthData SET HR = ?",new Object[]{HR});
        db.execSQL("UPDATE healthData SET SpO2 = ?",new Object[]{SpO2});
    }

    //获取数据
    public ArrayList<User> getAllData(){
        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String phone = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(phone,password));
        }
        cursor.close();
        return list;
    }
    public ArrayList<PatientInfo> getPatientInfoAllData(){
        ArrayList<PatientInfo> list = new ArrayList<PatientInfo>();
        Cursor cursor = db.query("patientInfo",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String phonetmp = cursor.getString(cursor.getColumnIndex("phone"));
            String Tnametmp = cursor.getString(cursor.getColumnIndex("Tname"));
            String Tsextmp = cursor.getString(cursor.getColumnIndex("Tsex"));
            int cmtmp = cursor.getInt(cursor.getColumnIndex("cm"));
            int kgtmp = cursor.getInt(cursor.getColumnIndex("kg"));
            String HeartDiseasetmp = cursor.getString(cursor.getColumnIndex("IsHeartDisease"));
            String Pneamopathytmp = cursor.getString(cursor.getColumnIndex("IsPneamopathy"));

            list.add(new PatientInfo(phonetmp,Tnametmp,Tsextmp,cmtmp,kgtmp,HeartDiseasetmp,Pneamopathytmp));


        }
        cursor.close();
        return list;
    }
    public ArrayList<HealthData> getHealthAllData(){
        ArrayList<HealthData> list = new ArrayList<HealthData>();
        Cursor cursor = db.query("healthData",null,null,null,null,null,"phone DESC");
        while(cursor.moveToNext()){
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            int HR = cursor.getInt(cursor.getColumnIndex("HR"));
            int SpO2 = cursor.getInt(cursor.getColumnIndex("SpO2"));
            list.add(new HealthData(phone,HR,SpO2));
        }
        cursor.close();
        return list;
    }

}
