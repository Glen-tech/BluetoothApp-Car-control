package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;

public abstract class Bluetooth extends AppCompatActivity {

    public abstract void turnOnnBT();
    public abstract void turnOffBT();

    public abstract void searchBT();
    public abstract String[] returnPairedBT();
    public abstract String[] returnFoundedBT();
    public abstract void connectBT();

}
