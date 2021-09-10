package com.example.bluetoothapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothFunctions extends Bluetooth
{
    private BluetoothAdapter mBTAdapter;

    private int duration_short;
    private int duration_long;
    private CharSequence text;

    private String deviceName;
    private String deviceHardwareAddress;

    private Context c;
    private Intent i;
    private IntentFilter f;

    public BluetoothFunctions(Context context , Intent intent , IntentFilter filter )
    {
        duration_short = Toast.LENGTH_SHORT;
        duration_long = Toast.LENGTH_LONG;
        mBTAdapter = BluetoothAdapter.getDefaultAdapter();

        c = context;
        i = intent;
        f = filter;
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
        mBTAdapter.startDiscovery(); // calls the reciever for scanning the bluetooth devices
        ArrayList<String> arrayList = new ArrayList<>();

        Set<BluetoothDevice> pairedDevices = mBTAdapter.getBondedDevices();

        if (pairedDevices.size() > 0)
        {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                deviceName = device.getName();
                deviceHardwareAddress = device.getAddress(); // MAC address
                System.out.println(deviceName);
                System.out.println(deviceHardwareAddress);
            }
        }
            else
            {
                System.out.println("No paired devices");
            }



        System.out.println("Search is clicked");

    }


    @Override
    public void connectBT()
    {
        // Code here executes on main thread after user presses button
        System.out.println("Connected is clicked");
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    public static final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            System.out.println("action is = " + action);


                if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                    //discovery starts, we can show progress dialog or perform other tasks
                    System.out.println("Started");

                }

                if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    System.out.println("Finished");
                    //discovery finishes, dismis progress dialog
                }

                if (BluetoothDevice.ACTION_FOUND.equals(action)) // works when location is enabled from the app on the cellphone
                {
                    BluetoothDevice device  = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress(); // MAC address
                    System.out.println(deviceName);
                    System.out.println(deviceHardwareAddress);

                }

        }
    };

}
