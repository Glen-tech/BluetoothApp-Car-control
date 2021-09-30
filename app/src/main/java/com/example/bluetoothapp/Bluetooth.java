package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public abstract class Bluetooth extends AppCompatActivity {

    public abstract void turnOnnBT();
    public abstract void turnOffBT();
    public abstract void searchBT();
    public abstract void connectBT();

}
