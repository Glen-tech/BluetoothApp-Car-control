package com.example.bluetoothapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.provider.Settings;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class BluetoothBasicFunctions extends Bluetooth
{
    public static List<String> pairedDevicesBT;
    public static List<String> foundDevicesBT;
    public static List<String> copyFoundBT;

    public static List<String> pairedBT;
    public static List<String> foundBT;
    public static int finished;

    private static int searchedClicked;

    private static int duration_short;
    private static CharSequence text;

    private static Context c;
    private Intent i;
    private IntentFilter f;

    private static String getFound;
    private static String getPaired;
    private static String BTmodule;
    public  static String BTmoduleAdress;
    private int countPaired;

    private BluetoothAdapter mBTAdapter;
    private BluetoothSocket hc05socket;
    public static boolean result;





    public BluetoothBasicFunctions(Context context , Intent intent , IntentFilter filter)
    {
        duration_short = Toast.LENGTH_SHORT;
        mBTAdapter = BluetoothAdapter.getDefaultAdapter();

        c = context;
        i = intent;
        f = filter;

        pairedDevicesBT = new ArrayList<String>();
        foundDevicesBT = new  ArrayList<String>();
        pairedBT = new ArrayList<String>();
        foundBT = new ArrayList<String>();
        copyFoundBT = new ArrayList<String>();

        BTmodule = new String("HC-05");
        getFound = new String("");
        getPaired = new String("");

        result = true;

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
            mBTAdapter.disable();
            text = "Turning off BlueTooth";
            Toast toast = Toast.makeText(c,text,duration_short);
            toast.show();
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

        if(mBTAdapter.isEnabled())
        {
            text = "Searching Devices";
            Toast toast = Toast.makeText(c,text,duration_short);
            toast.show();
        }

            else
            {
                text = "Search failed, turn on Bluetooth";
                Toast toast = Toast.makeText(c,text,duration_short);
                toast.show();
            }

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
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);


            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
                System.out.println("Started");
            }

                else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
                {
                    finished = 1;
                    copyFoundBT = foundDevicesBT;
                    System.out.println("finished in On Recieve= 1");
                    printAndSavePaired(pairedDevicesBT);
                    printAndSaveFound(foundDevicesBT);
                    //result = device.fetchUuidsWithSdp();
                }

            if (BluetoothDevice.ACTION_FOUND.equals(action)) // works when location is enabled from the app on the cellphone
                {
                    device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    Parcelable[] uuidExtra = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_UUID);


                    String deviceNameFound = device.getName();
                    String printNameFound = (deviceNameFound == null) ? "No name" : deviceNameFound;


                    String deviceHardwareAddressFound = device.getAddress(); // MAC address
                    String printAdressFound = (deviceHardwareAddressFound == null) ? "No adress" : deviceHardwareAddressFound;

                    foundDevicesBT.add(printNameFound);
                    foundDevicesBT.add(printAdressFound);

                    System.out.println(foundDevicesBT);

                    System.out.println("Call before UUID");
                }

                    else if(BluetoothDevice.ACTION_UUID.equals(action))
                    {
                        BluetoothDevice d = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        Parcelable[] uuidExtra = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_UUID);
                    }


                    else
                    {
                        System.out.println("Something went wrong");
                    }
        }
    };

    public static void printAndSavePaired( List<String> paired)
    {
        BTmoduleAdress = "";
        int i = 0;
        pairedBT = paired;
        System.out.println("PairedBT" + pairedBT);

            while(i < pairedBT.size())
            {
                getPaired = pairedBT.get(i); // save the element of the list in a string

                if (getPaired.equals(BTmodule) == true)
                {
                    BTmoduleAdress = pairedBT.get(i+1); // when found , next element is the hardware adress
                    System.out.println("Found bluetooth module :"+ getPaired);
                    System.out.println("Hardware adress is :"+ BTmoduleAdress);

                    text = "RC car found, connection can be made";
                    Toast toast = Toast.makeText(c,text,duration_short);
                    toast.show();
                }
                    else
                    {
                        System.out.println("Not located please try again");
                        System.out.println(getPaired);
                    }
                i++;
            }

    }

    public static void printAndSaveFound(List<String> found)
    {
        int i = 0;
        searchedClicked = 0;
        foundBT = found;
        System.out.println("FoundBT" + foundBT);

            while(i < foundBT.size())
            {
                System.out.println("In printandsave found");
                getFound = foundBT.get(i);

                if (getFound.equals(BTmodule) == true)
                {
                    BTmoduleAdress = foundBT.get(i+1);
                    System.out.println("Found bluetooth module : " + getFound);
                    System.out.println("Hardware adress is : " + BTmoduleAdress);

                    text = "RC car found, connection can be made";
                    Toast toast = Toast.makeText(c,text,duration_short);
                    toast.show();
                    searchedClicked = 1;
                }
                    else
                    {
                        System.out.println(getFound);
                    }
                i++;
            }

    }

    public List<String> returnFoundedBT() //returns found devices
    {
        System.out.println("Function return foundedBT"+copyFoundBT);
        return copyFoundBT;
    }

    @Override
    public void connectBT()
    {



       for (BluetoothDevice x : mBTAdapter.getBondedDevices()) {
             System.out.println("Main Address: " + x.getAddress());
            for (ParcelUuid uuid : x.getUuids()) {
                System.out.println("Main parceluuid:" + uuid.toString());
            }
        }


        final UUID nUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        System.out.println("Adress is " + BTmoduleAdress);
        System.out.println("Connected is clicked");

        if((mBTAdapter.isEnabled()) && (searchedClicked == 1))
        {
            BluetoothDevice hc05 = mBTAdapter.getRemoteDevice(BTmoduleAdress);
            try {
                hc05socket = hc05.createRfcommSocketToServiceRecord(nUUID);
                hc05socket.connect();
                System.out.println(hc05socket.isConnected());

            } catch (IOException e) {
                e.printStackTrace();
            }
            // Code here executes on main thread after user presses button

            text = "Connecting to RC car";
            Toast toast = Toast.makeText(c,text,duration_short);
            toast.show();
        }

        else
        {
            text = "RC car not found";
            Toast toast = Toast.makeText(c,text,duration_short);
            toast.show();
        }
    }
}