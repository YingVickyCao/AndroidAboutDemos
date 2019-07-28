package com.hades.example.android.gps;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
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
        listAllFreeLocationProviders();
    }

    private void listAllLocationProviders() {
        /**
         * Samsung SM-G9730
         * passive, gps, network
         */
        List<String> providerNames = lm.getAllProviders();

        TextView textView = findViewById(R.id.allProviders);
        textView.setText(providerNames.toString());
    }

    private void listAllFreeLocationProviders() {
        Criteria cri = new Criteria();
        cri.setCostAllowed(false);
        cri.setAltitudeRequired(true);
        cri.setBearingRequired(true);

        /**
         * Samsung SM-G9730: null
         */
        List<String> providerNames = lm.getProviders(cri, false);

        TextView textView = findViewById(R.id.allFreeProviders);
        textView.setText(providerNames.toString());
    }
}