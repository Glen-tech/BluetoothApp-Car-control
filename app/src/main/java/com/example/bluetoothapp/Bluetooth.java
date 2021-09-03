package com.example.bluetoothapp;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public abstract class Bluetooth extends AppCompatActivity {

    public abstract void turnOnnBT();
    public abstract void turnOffBT();
    public abstract void searchBT();
    public abstract void connectBT();

}
