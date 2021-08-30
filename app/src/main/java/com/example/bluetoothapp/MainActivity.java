package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button search;
    private Button connect;
    private Button onnBT;
    private Button offBT;

    private ListView listView;
    private BluetoothAdapter mBTAdapter;
    private BroadcastReceiver mReceiver;
    private Context context;
    private int duration_short;
    private int duration_long;
    private CharSequence text;



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
        duration_short = Toast.LENGTH_SHORT;
        duration_long = Toast.LENGTH_LONG;

        Bluetooth obj = new Bluetooth() {
            @Override
            int hello() {
                return 0;
            }
        };


        onnBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!mBTAdapter.isEnabled())
                {
                    Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(enable);
                }
                    else
                    {
                        text = "Bluetooth is already on";
                        Toast toast = Toast.makeText(context,text,duration_short);
                        toast.show();

                    }

            }
        });


        offBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBTAdapter.isEnabled())
                {
                    text = "Turning off BlueTooth";
                    Toast toast = Toast.makeText(context,text,duration_short);
                    toast.show();
                    mBTAdapter.disable();
                }
                else
                {
                    text = "Bluetooth is already off";
                    Toast toast = Toast.makeText(context,text,duration_short);
                    toast.show();
                }

            }
        });



        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // Code here executes on main thread after user presses button
                System.out.println("Search is clicked");

            }
        });


        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code here executes on main thread after user presses button
                System.out.println("Connected is clicked");
            }
        });

    }

}
