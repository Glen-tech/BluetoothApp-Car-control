package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button search;
    private Button connect;
    private ListView listView;
    private BluetoothAdapter mBTAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search   = (Button)findViewById(R.id.search);
        connect  = (Button)findViewById(R.id.connect);
        listView = (ListView) findViewById(R.id.listview);


        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code here executes on main thread after user presses button
                System.out.println("Search clicked");
            }
        });


        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code here executes on main thread after user presses button
                System.out.println("Connected clicked");
            }
        });








    }


}
