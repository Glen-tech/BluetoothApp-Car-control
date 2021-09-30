package com.example.bluetoothapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
public class BroadcastRecieverBT extends BroadcastReceiver {

    public static List<String> foundDevicesBT;
    public static  List<String> searchDevicesBT;
    public static int flag;

    public BroadcastRecieverBT() {
        System.out.print("Destructor BroadcastReciever");
        foundDevicesBT = new ArrayList<String>();
        searchDevicesBT = new ArrayList<String>();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        System.out.println("onRecieve");

        if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
            //discovery starts, we can show progress dialog or perform other tasks
            System.out.println("Started");
        }

        else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            searchDevicesBT.addAll(foundDevicesBT);
            System.out.println(searchDevicesBT);
            System.out.println("Finished");
            flag = 0;
            //discovery finishes, dismis progress dialog
        }

        else if (BluetoothDevice.ACTION_FOUND.equals(action)) // works when location is enabled from the app on the cellphone
        {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            String deviceNameFound = device.getName();
            String printNameFound = (deviceNameFound == null) ? "No name" : deviceNameFound;

            String deviceHardwareAddressFound = device.getAddress(); // MAC address
            String printAdressFound = (deviceHardwareAddressFound == null) ? "No adress" : deviceHardwareAddressFound;

            foundDevicesBT.add(printNameFound);
            foundDevicesBT.add(printAdressFound);

            System.out.println("Finished" + foundDevicesBT);

            // MainActivity.FoundView.notifyDataSetChanged();

        }

        else {
            System.out.println("Something went wrong");
        }

    }

}
