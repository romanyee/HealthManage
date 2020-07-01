package com.example.demo2.ui.home;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelUuid;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.demo2.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

public class GetBluetoothMac extends Activity {

    private ArrayList<String> bledata_compare = null; //用于比较
    private ArrayList<String> bledata = null;    //用于存储
    ArrayAdapter<String> adapter = null;
    private Button Search;
    private Button Stop;
    private ListView lv;


    //获取蓝牙适配器
    private BluetoothAdapter bleadapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_bluetooth);
        //获取权限
        ActivityCompat.requestPermissions(GetBluetoothMac.this, new String[]{ Manifest.permission.ACCESS_COARSE_LOCATION},1);
        //创建容器，用于数据传递
        this.bledata_compare = new ArrayList<>();
        this.bledata = new ArrayList<>();
        //控件初始化
        initView();
        //数据传递
        this.adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, this.bledata);
        //显示
        lv.setAdapter(adapter);

    }



    private void initView(){

        //找到控件对象
        Search = findViewById(R.id.buttonSearch);
        Stop = findViewById(R.id.buttonStop);
        lv = findViewById(R.id.ll_showmessage);

        //创建单击事件
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开启蓝牙
                enableBle();
                //开始扫描
                bleadapter.startLeScan(leScanCallback);
            }
        });
        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //扫描停止
                bleadapter.stopLeScan(leScanCallback);
            }
        });
    }

    //开启蓝牙
    private void enableBle(){
        if(!bleadapter.isEnabled()){
            bleadapter.enable();
        }
    }

    //数据回传接口
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {

            final ble_device ble_device = new ble_device();  //此处为新建的一个类,为ble_device
            ble_device.ble_address = bluetoothDevice.getAddress();

            if(bledata_compare.contains(ble_device.ble_address)) {
            }   //若列表中已经有了相应设备信息，则不添加进去
            else {
                bledata_compare.add(ble_device.ble_address);
                bledata.add("address:"+bluetoothDevice.getAddress()+"\nname:"+bluetoothDevice.getName()+"\nrssi:"+i);
                adapter.notifyDataSetChanged();
            }
        }
    };
}

