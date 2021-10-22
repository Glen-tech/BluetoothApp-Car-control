package com.example.bluetoothapp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.io.IOException;

public class DriveCar_Commands extends DriveCar{


    public DriveCar_Commands(Context context , Intent intent , IntentFilter filter)
    {

    }

    @Override
    public void forward() {
    /*   if(BluetoothBasicFunctions.hc05socket != null)
       {
           try { // Converting the string to bytes for transferring
               BluetoothBasicFunctions.hc05socket.getOutputStream().write("F".toString().getBytes());
           }
           catch (IOException e) {
               e.printStackTrace();
           }

       }*/
    }

    @Override
    public void backward() {

      /*  if(BluetoothBasicFunctions.hc05socket != null)
        {
            try { // Converting the string to bytes for transferring
                BluetoothBasicFunctions.hc05socket.getOutputStream().write("B".toString().getBytes());
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }*/
    }

    @Override
    public void left() {
       /* if(BluetoothBasicFunctions.hc05socket != null)
        {
            try { // Converting the string to bytes for transferring
                BluetoothBasicFunctions.hc05socket.getOutputStream().write("L".toString().getBytes());
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }*/
    }

    @Override
    public void right(){
       /* if(BluetoothBasicFunctions.hc05socket != null)
        {
            try { // Converting the string to bytes for transferring
                BluetoothBasicFunctions.hc05socket.getOutputStream().write("R".toString().getBytes());
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }*/
    }

    @Override
    public void DistanceSensor() {

    }

}
