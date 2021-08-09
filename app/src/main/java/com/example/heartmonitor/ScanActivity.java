package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ScanActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    public static final String EXTRA_DEVICE_ADDRESS = "device_address";

    BluetoothAdapter adapter_bluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_scan);

        verificar_bluetooth();
        setResult(Activity.RESULT_CANCELED);

        ArrayAdapter<String> adapter_paired = new ArrayAdapter<>(this, R.layout.item_device_name);

        ListView view_paired = findViewById(R.id.list_componen);
        view_paired.setAdapter(adapter_paired);
        view_paired.setOnItemClickListener(device_ClickListener);

        Set<BluetoothDevice> paired_device = Collections.emptySet();

        try {
            paired_device = adapter_bluetooth.getBondedDevices();
        } catch (Exception e) {
            Toast.makeText(ScanActivity.this, "Error...", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (paired_device.size() > 0){
            for (BluetoothDevice device : paired_device){
                adapter_paired.add(device.getName() + "\n" + device.getAddress());
            }
        }else{
            adapter_paired.add("No hay dispositivos");
        }

    }

    private final AdapterView.OnItemClickListener device_ClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            adapter_bluetooth.cancelDiscovery();

            String info = ((TextView) view).getText().toString();

            if (info.length() > 16) {
                String addrress = info.substring(info.length() - 17);
                Intent intent = new Intent();
                intent.putExtra(EXTRA_DEVICE_ADDRESS, addrress);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    };

    private void verificar_bluetooth() {
        adapter_bluetooth = BluetoothAdapter.getDefaultAdapter();
        if (adapter_bluetooth == null) {
            Toast.makeText(ScanActivity.this, "El dispositivo no soporta bluetooth", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            if (!adapter_bluetooth.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (adapter_bluetooth != null) {
            adapter_bluetooth.cancelDiscovery();
        }
    }
}