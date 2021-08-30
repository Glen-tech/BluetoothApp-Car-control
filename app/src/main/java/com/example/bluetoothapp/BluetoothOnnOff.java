package com.example.bluetoothapp;

public class BluetoothOnnOff extends Bluetooth
{
    public BluetoothOnnOff()
    {

    }

    @Override
    int hello() {
        return 0;
    }


    protected void finalize() {
        System.out.println("garbage collected ");
    }

}
