package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Button search;
    private Button connect;
    private Button onnBT;
    private Button offBT;
    private ListView listView;
    public static Context context;

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

        onnBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                BluetoothFunctions enableBT = new BluetoothFunctions(context);
                enableBT.turnOnnBT();
            }
        });


        offBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                BluetoothFunctions  disableBT = new BluetoothFunctions(context);
                disableBT.turnOffBT();
            }
        });



        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                BluetoothFunctions searchDevices = new BluetoothFunctions(context);
                searchDevices.searchBT();

            }
        });


        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                BluetoothFunctions obj = new BluetoothFunctions(context);
                obj.connectBT();
            }
        });

    }

}
