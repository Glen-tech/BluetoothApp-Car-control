package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;

public abstract class DriveCar extends AppCompatActivity {

    public abstract void forward();
    public abstract void backward();
    public abstract void right();
    public abstract void left();
    public abstract void DistanceSensor(); // multithread if connection is made
}
