package com.example.bluetoothapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;

public class BluetoothFunctions extends Bluetooth
{
    private BluetoothAdapter mBTAdapter;
    private BroadcastReceiver mReceiver;
    private int duration_short;
    private int duration_long;
    private CharSequence text;

    private Context c;

    public BluetoothFunctions(Context context)
    {
        duration_short = Toast.LENGTH_SHORT;
        duration_long = Toast.LENGTH_LONG;
        mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        c = context;
    }



    @Override
    public void turnOnnBT()
    {
        if(!mBTAdapter.isEnabled())
        {

            Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            enable.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(enable);
        }

            else
            {
                text = "Bluetooth is already on";
                Toast toast = Toast.makeText(MainActivity.context,text,duration_short);
                toast.show();

            }
    }


    @Override
    public void turnOffBT()
    {
       if(mBTAdapter.isEnabled())
        {
            text = "Turning off BlueTooth";
            Toast toast = Toast.makeText(c,text,duration_short);
            toast.show();
            mBTAdapter.disable();
        }
        else
        {
            text = "Bluetooth is already off";
            Toast toast = Toast.makeText(c,text,duration_short);
            toast.show();
        }

    }

    @Override
    public void searchBT()
    {
        // Code here executes on main thread after user presses button
        System.out.println("Search is clicked");

    }

    @Override
    public void connectBT()
    {
        // Code here executes on main thread after user presses button
        System.out.println("Connected is clicked");
    }

}
