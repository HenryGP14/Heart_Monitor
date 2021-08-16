package com.example.heartmonitor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.heartmonitor.utils.Constants;

import java.util.Objects;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_config);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case Constants.CONNECT_DEVICE_INSECURE:
            case Constants.CONNECT_DEVICE_SECURE:
                if(resultCode == Activity.RESULT_OK){
                    String mac_address = Objects.requireNonNull(data.getExtras()).getString(ScanActivity.EXTRA_DEVICE_ADDRESS);
                    Log.d("Mi dato", mac_address);
                }
        }

    }

    public void init_control(View view){
        Intent intent = new Intent(ConfigActivity.this, ScanActivity.class);
        startActivityForResult(intent, Constants.CONNECT_DEVICE_SECURE);
    }
}