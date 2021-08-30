package com.example.bluetoothapp;

abstract class Bluetooth{

    public Bluetooth()
    {

    }

    abstract int hello();

    protected void finalize()
    {
        System.out.println("garbage collected ");
    }

}
