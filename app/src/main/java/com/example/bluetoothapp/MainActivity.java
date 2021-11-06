package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{


    private Button search;
    private Button connect;
    private Button onnBT;
    private Button offBT;
    private Button sendForward;


    public static Context context;
    public static Intent intent;
    public static IntentFilter filter;

    public List<String> ReturnPaired;
    public List<String> ReturnFounded;
    public List<String> getPaired;
    public List<String> getFounded;

    public static ArrayAdapter<String> PairedView;
    public static ArrayAdapter<String> FoundView;

    private  ListView listViewPaired;
    private  ListView listViewFound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (Button) findViewById(R.id.search); // crearting variables for interaction buttons
        connect = (Button) findViewById(R.id.connect);
        onnBT = (Button) findViewById(R.id.On);
        offBT = (Button) findViewById(R.id.Off);

        sendForward = (Button) findViewById(R.id.sendForward);

        context = getApplicationContext(); // get the main intent
        intent = getIntent();

        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND); // Adding filter for searching bluetooth devices
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        ReturnPaired = new ArrayList<String>();
        ReturnFounded = new ArrayList<String>();


        onnBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothCommands enableBT = new BluetoothCommands(context, intent, filter); // making new object for enable BT
                enableBT.turnOnnBT();
            }
        });


        offBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothCommands disableBT = new BluetoothCommands(context, intent, filter);// making new object for disable BT
                disableBT.turnOffBT();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {
                BluetoothCommands searchDevices = new BluetoothCommands(context, intent, filter); // making new object for search BT devices

                onStart();
                searchDevices.searchBT();
                getFounded = searchDevices.returnFoundedBT();

                if(BluetoothCommands.finished == 1)
                {
                    System.out.println("finished = 1");

                    System.out.println("Return main paired"  + BluetoothCommands.pairedDevicesBT);
                    System.out.println("Return main founded" + getFounded);

                    PairedView = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, BluetoothCommands.pairedDevicesBT);
                    FoundView = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getFounded);

                    listViewPaired = (ListView) findViewById(R.id.PairedListView);
                    listViewFound = (ListView) findViewById(R.id.FoundListView);

                    listViewPaired.setAdapter(PairedView);
                    listViewFound.setAdapter(FoundView);
                }

            }


        });


        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BluetoothCommands obj = new BluetoothCommands(context, intent, filter); // making new object for connecting BT device
                obj.connectBT();
            }
        });


        sendForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothCommands obj = new BluetoothCommands(context, intent, filter); // making new object for connecting BT device
                obj.forward();
                };
        });

    }


    @Override
    protected void onStart()
    {
        super.onStart();
        registerReceiver(BluetoothCommands.mReceiver,filter); // First reciever for search is added
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        unregisterReceiver(BluetoothCommands.mReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the ACTION_FOUND receiver.
    }


}