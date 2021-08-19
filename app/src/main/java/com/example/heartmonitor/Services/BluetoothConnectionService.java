package com.example.heartmonitor.Services;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothConnectionService extends Service {
    private String TAG = "BluetoothConnectionService";

    private static final UUID MY_UUID_SECURE = UUID.fromString("dbeb0942-5920-47b7-98fe-d7aae27c169b" );
    private static final UUID MY_UUID_INSECURE = UUID.fromString("dbeb0942-5920-47b7-98fe-d7aae27c169b" );

    BluetoothAdapter bluetoothAdapter;
    ConnectThread thread_cliente; // Hilo del cliente
    ConnectedThread thread_connected; // Hilo de conexión

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private final String socket_type;

        public ConnectThread(BluetoothDevice device, boolean secure) {
            BluetoothSocket tmp = null;
            mmDevice = device;
            socket_type = secure ? "Secure" : "Insecure";

            try {
                if (secure) {
                    tmp = device.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
                }else{
                    tmp = device.createInsecureRfcommSocketToServiceRecord(MY_UUID_INSECURE);
                }
            } catch (IOException | NullPointerException e) {
                Log.e(TAG, "Socket's create() method failed", e);
            }
            mmSocket = tmp;
        }

        public void run() {
            bluetoothAdapter.cancelDiscovery();
            try {
                mmSocket.connect();
            } catch (IOException connectException) {
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                    Log.e(TAG, "Could not close the client socket", closeException);
                }
                return;
            }

            synchronized (thread_cliente){
                thread_cliente = null;
            }
            connect(mmSocket, mmDevice, socket_type);
        }

        // Closes the client socket and causes the thread to finish.
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the client socket", e);
            }
        }
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInSteam;
        private final OutputStream mmOutSteam;

        public ConnectedThread(BluetoothSocket socket, String type) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException | NullPointerException e) {
                Log.e(TAG, "Socket's create() method failed", e);
            }

            mmInSteam = tmpIn;
            mmOutSteam = tmpOut;
        }

        public void run() {
            // Leer la información cuando se conecta en el SmartWatch
        }

        public void write(byte[] buffer){
            // Enviar los datos al dispositivo conectado: esto hacerlo con |try catch|
//            try {
//                mmOutSteam.write(buffer);
//            }catch (IOException e){
//                Log.e(TAG, "Error: ", e);
//            }
        }

        // Closes the client socket and causes the thread to finish.
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException | NullPointerException e) {
                Log.e(TAG, "Could not close the client socket", e);
            }
        }
    }

    synchronized void stop() {
        Log.d(TAG, "stop");

        if (thread_cliente != null) {
            thread_cliente.cancel();
            thread_cliente = null;
        }

        if (thread_connected != null) {
            thread_connected.cancel();
            thread_connected = null;
        }
    }

    private synchronized void connect(BluetoothSocket socket, BluetoothDevice device, final String type){
        // Cancelar el hilo completo de la conexión
        if (thread_cliente != null){
            thread_cliente.cancel();
            thread_cliente = null;
        }

        // Cancelar el hilo cuando esta corriendo la conexión
        if(thread_connected != null){
            thread_connected.cancel();
            thread_connected = null;
        }
    }
}
