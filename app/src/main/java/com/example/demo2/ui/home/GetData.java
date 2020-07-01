package com.example.demo2.ui.home;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo2.R;

public class GetData extends Activity {

    private BluetoothService mBluetoothService; //自定义蓝牙服务类
    private BluetoothAdapter mBluetoothAdapter;
    private String mConnectedDeviceName = null; //连接设备的名称

    //默认是1,因为程序启动时首先会连接一个蓝牙
    private int current_pos = 1;

    //hanlder消息标识 message.what
    public static final int MESSAGE_STATE_CHANGE = 1; // 状态改变
    public static final int MESSAGE_READ = 2;          // 读取数据
    public static final int MESSAGE_WRITE = 3;         // 给硬件传数据，暂不需要，看具体需求
    public static final int MESSAGE_DEVICE_NAME = 4;  // 设备名字
    public static final int MESSAGE_TOAST = 5;         // Toast

    //传感器 ,这里默认同时需要和三个硬件连接，分别设置id 1,2,3进行区分，demo中实际只用到 MAGIKARE_SENSOR_DOWN = 1
    //可以根据情况自行添加删除
//    public static final int MAGIKARE_SENSOR_UP = 2;
//    public static final int MAGIKARE_SENSOR_DOWN = 1;
//    public static final int MAGIKARE_SENSOR_CENTER = 3;
    public static final int MAGIKARE_SENSOR_HEARTBEAT = 4;

//    public static float[] m_receive_data_up;                    //传感器的数据
//    public static float[] m_receive_data_down;                  //传感器的数据 ,demo中我们只需要这一个，因为只有一个硬件设备，
//    public static float[] m_receive_data_center;                //传感器的数据
    public static int m_receive_data_heartBeat;               //获取到的心率

    TextView heartBeatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        heartBeatButton = findViewById(R.id.ttv_heartBeat);

        //获取蓝牙适配器
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // 1、判断设备是否支持蓝牙功能
        if (mBluetoothAdapter == null) {
            //设备不支持蓝牙功能
            Toast.makeText(this, "当前设备不支持蓝牙功能！", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2、打开设备的蓝牙功能
        if (!mBluetoothAdapter.isEnabled()) {
            boolean enable = mBluetoothAdapter.enable(); //返回值表示 是否成功打开了蓝牙设备
            if (enable) {
                Toast.makeText(this, "打开蓝牙功能成功！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "打开蓝牙功能失败，请到'系统设置'中手动开启蓝牙功能！", Toast.LENGTH_SHORT).show();
                return;
            }
        }


        // 3、创建自定义蓝牙服务对象
        if (mBluetoothService == null) {
            mBluetoothService = new BluetoothService(GetData.this, mHandler);
        }
        if (mBluetoothService != null) {
            //根据MAC地址远程获取一个蓝牙设备，这里固定了，实际开发中，需要动态设置参数（MAC地址）
            BluetoothDevice sensor_down = mBluetoothAdapter.getRemoteDevice("10:E9:53:f0:63:B1");
            if (sensor_down != null) {
                //成功获取到远程蓝牙设备（传感器），这里默认只连接MAGIKARE_SENSOR_HEARTBEAT = 4这个设备
//                mBluetoothService.connect(sensor_down, MAGIKARE_SENSOR_DOWN);
                mBluetoothService.connect(sensor_down, MAGIKARE_SENSOR_HEARTBEAT);
            }
        }


    }
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_READ:
                    try {
                        String str=msg.getData().getString("index");
                        int index=Integer.valueOf(str);
                        switch (index)
                        {
                            //获取到蓝牙传输过来的数据
//                            case MAGIKARE_SENSOR_UP:
//                                m_receive_data_up=msg.getData().getFloatArray("Data");
//                                break;
//                            //实际只用到这个case ，因为demo只连接了一个硬件设备
//                            case MAGIKARE_SENSOR_DOWN:
//                                m_receive_data_down=msg.getData().getFloatArray("Data");
//                                break;
//                            case MAGIKARE_SENSOR_CENTER:
//                                m_receive_data_center=msg.getData().getFloatArray("Data");
//                                break;
                            case MAGIKARE_SENSOR_HEARTBEAT:
                                m_receive_data_heartBeat = msg.getData().getInt("Data");
                                break;

                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    break;
                case MESSAGE_STATE_CHANGE:
//                    连接状态
                    switch (msg.arg1) {
                        case BluetoothService.STATE_CONNECTED:
                            break;
                        case BluetoothService.STATE_CONNECTING:
                            break;
                        case BluetoothService.STATE_LISTEN:
                            break;
                        case BluetoothService.STATE_NONE:
                            break;
                    }
                    break;
                case MESSAGE_DEVICE_NAME:
                    mConnectedDeviceName = msg.getData().getString("device_name");
                    Log.i("bluetooth","成功连接到:"+mConnectedDeviceName);
                    Toast.makeText(getApplicationContext(),"成功连接到设备" + mConnectedDeviceName,Toast.LENGTH_SHORT).show();

                    break;
                case MESSAGE_TOAST:
                    int index=msg.getData().getInt("device_id");
                    Toast.makeText(getApplicationContext(),msg.getData().getString("toast"), Toast.LENGTH_SHORT).show();
                    //当失去设备或者不能连接设备时，重新连接
                    Log.d("Magikare","当失去设备或者不能连接设备时，重新连接");
                    //重新连接硬件设备
                    if(mBluetoothService!=null)
                    {
                        switch (index) {
//                            case MAGIKARE_SENSOR_DOWN:
//                                //根据你的硬件的MAC地址写参数，每一个硬件设备都有一个MAC地址，此方法是根据MAC地址得到蓝牙设备
//                                BluetoothDevice sensor_down = mBluetoothAdapter.getRemoteDevice("10:E9:53:f0:63:B1");
//                                if (sensor_down != null)
//                                    mBluetoothService.connect(sensor_down, MAGIKARE_SENSOR_DOWN);
//                                break;
//                            case MAGIKARE_SENSOR_UP:
//                                BluetoothDevice sensor_up = mBluetoothAdapter.getRemoteDevice("");  //参数写你这个设备的MAC码
//                                if (sensor_up != null)
//                                    mBluetoothService.connect(sensor_up, MAGIKARE_SENSOR_UP);
//                                break;
//                            case MAGIKARE_SENSOR_CENTER:
//                                BluetoothDevice center = mBluetoothAdapter.getRemoteDevice("");    //参数写你这个设备的MAC码
//                                if (center != null)
//                                    mBluetoothService.connect(center, MAGIKARE_SENSOR_CENTER);
//                                break;
                            case MAGIKARE_SENSOR_HEARTBEAT:
                                //根据你的硬件的MAC地址写参数，每一个硬件设备都有一个MAC地址，此方法是根据MAC地址得到蓝牙设备
                                BluetoothDevice sensor_heartbeat = mBluetoothAdapter.getRemoteDevice("10:E9:53:f0:63:B1");
                                if (sensor_heartbeat != null)
                                    mBluetoothService.connect(sensor_heartbeat, MAGIKARE_SENSOR_HEARTBEAT);
                                break;
                        }
                    }

                    break;
            }
            return false;
        }
    });





    public synchronized void onResume() {
        super.onResume();

        if (mBluetoothService != null) {
            if (mBluetoothService.getState() == BluetoothService.STATE_NONE) {
                mBluetoothService.start();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBluetoothService != null) mBluetoothService.stop();
    }
    // 硬件通过蓝牙传输的byte类型已经转换为float类型，并且通过handler传输到 m_receive_data_down[]数组中，一下操作是获取这个数据，根据个人情况使用
    //获取角度


    public int GetHeartBeat(int index) {
        int heartBeat = 0;
        if (m_receive_data_heartBeat == 0) {
            return m_receive_data_heartBeat;
        }
        switch (index)
        {
            case  MAGIKARE_SENSOR_HEARTBEAT:
                heartBeat  = m_receive_data_heartBeat;
                heartBeatButton.setText("心率：" + String.valueOf(heartBeat));
                break;
//            case MAGIKARE_SENSOR_UP:
//                break;
        }
        return heartBeat;

    }
//    public float[] GetAngle(int index)
//    {
//        float[] angles=new float[3];
//        if(m_receive_data_up==null ||m_receive_data_down==null) {
//            return angles;
//        }
//        switch (index)
//        {
//            case  MAGIKARE_SENSOR_DOWN:
//                angles[0]=m_receive_data_down[6];
//                angles[1]=m_receive_data_down[7];
//                angles[2]=m_receive_data_down[8];
//                break;
//            case MAGIKARE_SENSOR_UP:
//                angles[0]=m_receive_data_up[6];
//                angles[1]=m_receive_data_up[7];
//                angles[2]=m_receive_data_up[8];
//                Log.d("安卓 Up 角度",angles[0]+","+angles[1]+","+angles[2]);
//                break;
//        }
//        return angles;
//    }
//    //获取角速度
//    public static float[] GetAngleSpeed(int index)
//    {
//
//        float [] anglespeed=new float[3];
//
//        if(m_receive_data_down==null)
//        {
//
//            return anglespeed;
//        }
//        switch (index)
//        {
//            case MAGIKARE_SENSOR_DOWN:
//
//                anglespeed[0]=m_receive_data_down[3];
//                anglespeed[1]=m_receive_data_down[4];
//                anglespeed[2]=m_receive_data_down[5];
//                break;
//            case MAGIKARE_SENSOR_UP:
//                anglespeed[0]=m_receive_data_up[3];
//                anglespeed[1]=m_receive_data_up[4];
//                anglespeed[2]=m_receive_data_up[5];
//                break;
//        }
//        return  anglespeed;
//    }
//
//    public float[] GetQuaternion(int index)
//    {
//        float[] quaternion=new float[4];
//
//        if(m_receive_data_down==null)
//        {
//            return quaternion;
//        }
//        switch (index)
//        {
//            case  MAGIKARE_SENSOR_DOWN:
//                quaternion[0]=m_receive_data_down[23];
//                quaternion[1]=m_receive_data_down[24];
//                quaternion[2]=m_receive_data_down[25];
//                quaternion[3]=m_receive_data_down[26];
//                Log.i("saveinfo","m_receive_data_down23"+m_receive_data_down[23]);
//                Log.i("saveinfo","m_receive_data_down24"+m_receive_data_down[24]);
//                Log.i("saveinfo","m_receive_data_down25"+m_receive_data_down[25]);
//                Log.i("saveinfo","m_receive_data_down26"+m_receive_data_down[26]);
//                break;
//            case MAGIKARE_SENSOR_UP:
//                quaternion[0]=m_receive_data_up[23];
//                quaternion[1]=m_receive_data_up[24];
//                quaternion[2]=m_receive_data_up[25];
//                quaternion[3]=m_receive_data_up[26];
//                break;
//            case MAGIKARE_SENSOR_CENTER:
//                quaternion[0]=m_receive_data_center[23];
//                quaternion[1]=m_receive_data_center[24];
//                quaternion[2]=m_receive_data_center[25];
//                quaternion[3]=m_receive_data_center[26];
//        }
//        return  quaternion;
//    }



}