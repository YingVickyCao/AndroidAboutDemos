package com.hades.example.android.gps;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.hades.example.android.R;

import java.util.List;

public class TestGpsActivity extends Activity {
    LocationManager lm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE); // 获取系统的LocationManager对象
        listAllLocationProviders();
    }

    private void listAllLocationProviders() {
        List<String> providerNames = lm.getAllProviders();

        TextView textView = findViewById(R.id.providers);
        textView.setText(providerNames.toString());
    }
}