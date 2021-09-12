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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private Button search;
    private Button connect;
    private Button onnBT;
    private Button offBT;
    private ListView listView;

    private TextView PairedText;
    private TextView FoundText;

    public static Context context;
    public static Intent intent;
    public static IntentFilter filter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search   = (Button)findViewById(R.id.search); // crearting variables for interaction buttons
        connect  = (Button)findViewById(R.id.connect);
        onnBT    = (Button)findViewById(R.id.On);
        offBT    = (Button)findViewById(R.id.Off);

        context = getApplicationContext(); // get the main intent
        intent = getIntent();

        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND); // Adding filter for searching bluetooth divices
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        ListView simpleList;
        ListView simpleList2;


        /*String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};

        simpleList = (ListView)findViewById(R.id.PairedListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_layout, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);

        simpleList2 = (ListView)findViewById(R.id.FoundListView);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.listview_layout, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);*/


        registerReceiver(BluetoothBasicFunctions.mReceiver,filter); // First reciever for search is added

        onnBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                BluetoothBasicFunctions enableBT = new BluetoothBasicFunctions(context,intent, filter); // making new object for enable BT
                enableBT.turnOnnBT();
            }
        });


        offBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                BluetoothBasicFunctions disableBT = new BluetoothBasicFunctions(context,intent,filter);// making new object for disable BT
                disableBT.turnOffBT();
            }
        });



        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                BluetoothBasicFunctions searchDevices = new BluetoothBasicFunctions(context,intent,filter); // making new object for search BT devices
                searchDevices.searchBT();

            }
        });


        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                BluetoothBasicFunctions obj = new BluetoothBasicFunctions(context,intent,filter); // making new object for connecting BT device
                obj.connectBT();
            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(BluetoothBasicFunctions.mReceiver);
    }

}
