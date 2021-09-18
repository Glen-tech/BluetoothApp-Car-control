package com.example.bluetoothapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class BluetoothBasicFunctions extends Bluetooth
{
    public List<String> pairedDevicesBT;
    public static List<String> foundDevicesBT;

   /* public String[] countryList;
    public String[] Animalist; */ // Teststrings

    private BluetoothAdapter mBTAdapter;

    private int duration_short;
    private CharSequence text;

    private Context c;
    private Intent i;
    private IntentFilter f;

    private int countPaired;

    public BluetoothBasicFunctions(Context context , Intent intent , IntentFilter filter)
    {
        duration_short = Toast.LENGTH_SHORT;
        mBTAdapter = BluetoothAdapter.getDefaultAdapter();

        c = context;
        i = intent;
        f = filter;


        pairedDevicesBT = new ArrayList<String>();
        foundDevicesBT = new  ArrayList<String>();

        /*countryList = new String[]{"Belgium", "Spain", "Malta", "German", "France", "Madagascar"};
        Animalist = new String[]{"Cat", "Dog", "Hamster", "Elephant", "Tiger", "Lion"};*/
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
    public void searchBT() {

        mBTAdapter.startDiscovery(); // calls the  public static final BroadcastReceiver mReceiver  for scanning the bluetooth devices

        //first the paired , then the discovered

        Set<BluetoothDevice> pairedDevices = mBTAdapter.getBondedDevices();


        if (pairedDevices.size() > 0)
        {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices)
            {

                if(countPaired < 8)
                {
                    String deviceNamePaired = device.getName();
                    String printNamePaired = (deviceNamePaired == null) ? "No name" : deviceNamePaired;
                    pairedDevicesBT.add(printNamePaired);

                    String deviceHardwareAddressPaired = device.getAddress(); // MAC address
                    String printAdressPaired = (deviceHardwareAddressPaired == null) ? "No adress" : deviceHardwareAddressPaired;
                    pairedDevicesBT.add(printAdressPaired);
                }
            }

            MainActivity.PairedView.notifyDataSetChanged();
        }
        else
        {
            System.out.println("No paired devices");
        }

        countPaired = 0;



        System.out.println("Search is clicked");


    }


    // Create a BroadcastReceiver for ACTION_FOUND.
    public static final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
                System.out.println("Started");

            }

                else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
                {
                    System.out.println("Finished");

                    //discovery finishes, dismis progress dialog
                }


                 else if (BluetoothDevice.ACTION_FOUND.equals(action)) // works when location is enabled from the app on the cellphone
                {
                    BluetoothDevice device  = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    String deviceNameFound = device.getName();
                    String printNameFound = (deviceNameFound == null) ? "No name" : deviceNameFound;

                    String deviceHardwareAddressFound = device.getAddress(); // MAC address
                    String printAdressFound = (deviceHardwareAddressFound == null) ? "No adress" : deviceHardwareAddressFound;

                    foundDevicesBT.add(printNameFound);
                    foundDevicesBT.add(printAdressFound);

                    System.out.println(foundDevicesBT);

                    MainActivity.FoundView.notifyDataSetChanged();

                }


                    else
                    {
                        System.out.println("Something went wrong");
                    }
        }
    };


    // working with List<String> can be used in future work
    @Override
    public List<String>returnPairedBT() // returns paired devices
    {
        return pairedDevicesBT;
    }

    @Override
    public List<String> returnFoundedBT() //returns found devices
    {
        return foundDevicesBT;
    }


    @Override
    public void connectBT()
    {
        // Code here executes on main thread after user presses button
        System.out.println("Connected is clicked");
    }


}
