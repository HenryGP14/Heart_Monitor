package com.example.heartmonitor;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class ScanActivity extends AppCompatActivity {

    public static final String EXTRA_DEVICE_ADDRESS = "device_address";

    static final int REQUEST_ENABLE_BT = 1;
    static final String TAG = "ScanActivity";
    BluetoothAdapter adapter_bluetooth;
    ArrayAdapter<String> adapter_dispositivos;
    ArrayAdapter<String> adapter_sync_dispositivos;
    boolean enable_permise = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_scan);

        // Verificar si el adaptador de bluetooth existe en el movil y activa los permisos necesarios
        verifica_bluetooth();

        // Pide permiso de ubicación

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            activar_busqueda();
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            AlertDialog.Builder dialogo = new AlertDialog.Builder(ScanActivity.this);
            dialogo.setTitle(Html.fromHtml("<font color='#05294A'>Permisos obligatorios</font>"));
            dialogo.setMessage("Debe aceptar los permisos para detectar los dispositivos bluetooth cercanos");

            dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(ScanActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ENABLE_BT);
                }
            });
            dialogo.show();
            activar_busqueda();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ENABLE_BT);
        }


//        // Evento click para cada elemento encontrado por el bluetooth
//        view_paired.setOnItemClickListener(device_ClickListener);

        adapter_sync_dispositivos = new ArrayAdapter<>(this, R.layout.item_device_name);

        Set<BluetoothDevice> paired_device = adapter_bluetooth.getBondedDevices();
        if (paired_device.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : paired_device) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                adapter_sync_dispositivos.add(deviceName + "\n" + deviceHardwareAddress);
            }
        }else{
            adapter_sync_dispositivos.add("No hay dispositivos sincronizados");
        }

        ListView list_sync = findViewById(R.id.list_sync);
        list_sync.setAdapter(adapter_sync_dispositivos);

    }

    public void activar_busqueda() {
        // Obtener un layout para guardar mostrar los elementos
        adapter_dispositivos = new ArrayAdapter<>(this, R.layout.item_device_name);

        IntentFilter bluetooth_filter = new IntentFilter();
        bluetooth_filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        bluetooth_filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        bluetooth_filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        bluetooth_filter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, bluetooth_filter);
        enable_permise = true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (!(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                final CharSequence[] opciones = {"Si", "No"};
                final AlertDialog.Builder alerta = new AlertDialog.Builder(ScanActivity.this);
                alerta.setTitle(Html.fromHtml("<font color='#05294A'>¿Desea configurar los permisos de forma manual?</font>"));
                alerta.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (opciones[which].equals("Si")) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                            activar_busqueda();
                        } else {
                            Toast.makeText(getApplicationContext(), "Los permisos no fueron aceptados", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                alerta.show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void buscar_dipositivosClick(View view) {
        if (adapter_bluetooth.isDiscovering()) {
            Log.d(TAG, "Botón de busqueda: Ya está buscando dispositivos.");
        } else if (adapter_bluetooth.startDiscovery()) {
            Log.d(TAG, "Botón de busqueda: Buscando dispositivos...");
        } else {
            Log.d(TAG, "Botón de busqueda: Error al buscar dispositivos.");
        }
    }

    public void detener_busquedaClick(View view) {
        if (adapter_bluetooth.cancelDiscovery()) {
            Log.d(TAG, "Detener la busqueda: Deteniendo la búsqueda de dispositivos...");
        } else {
            Log.d(TAG, "Detener la busqueda: Error de detener la búsqueda de dispositivos");
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

    private void verifica_bluetooth() {
        adapter_bluetooth = BluetoothAdapter.getDefaultAdapter();
        if (adapter_bluetooth == null) {
            Toast.makeText(ScanActivity.this, "El dispositivo no soporta bluetooth", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Retorna un cuadro de mensaje para activar el bluetooth
            if (!adapter_bluetooth.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                Log.d(TAG, "La busqúeda esta iniciada");
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.d(TAG, "La busqúeda finalizada");
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName(); // Nombre del dispositivo
                String deviceHardwareAddress = device.getAddress(); // Nombre de MAC address del dispositivo
                Log.d(TAG, deviceName + "\n" + deviceHardwareAddress);

                // Mostrar los dispositivos en el listView
                adapter_dispositivos.add(deviceName + "\n" + deviceHardwareAddress);
                ListView list_scan = findViewById(R.id.list_scan);
                list_scan.setAdapter(adapter_dispositivos);
            }
        }
    };

    @Override
    protected void onDestroy() {
        if (enable_permise) {
            unregisterReceiver(receiver);
        }
        if (adapter_bluetooth != null) {
            adapter_bluetooth.cancelDiscovery();
        }
        super.onDestroy();
    }
}