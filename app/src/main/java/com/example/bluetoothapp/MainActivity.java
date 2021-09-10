package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity{

    private Button search;
    private Button connect;
    private Button onnBT;
    private Button offBT;
    private ListView listView;

    public static Context context;
    public static Intent intent;
    public static IntentFilter filter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search   = (Button)findViewById(R.id.search);
        connect  = (Button)findViewById(R.id.connect);
        onnBT    = (Button)findViewById(R.id.On);
        offBT    = (Button)findViewById(R.id.Off);


        listView = (ListView)findViewById(R.id.listview);

        context = getApplicationContext();
        intent = getIntent();

        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(BluetoothFunctions.mReceiver,filter);

        onnBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                BluetoothFunctions enableBT = new BluetoothFunctions(context,intent, filter);
                enableBT.turnOnnBT();
            }
        });


        offBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                BluetoothFunctions  disableBT = new BluetoothFunctions(context,intent,filter);
                disableBT.turnOffBT();
            }
        });



        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                BluetoothFunctions searchDevices = new BluetoothFunctions(context,intent,filter);
                searchDevices.searchBT();

            }
        });


        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                BluetoothFunctions obj = new BluetoothFunctions(context,intent,filter);
                obj.connectBT();
            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(BluetoothFunctions.mReceiver);
    }

}
