package com.hades.example.android.app_component._intent_and_intent_filter._component;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hades.example.android.R;

public class SecondActivity extends Activity {
    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_component_2);

        ComponentName comp = getIntent().getComponent();
        if (null == comp) {
            return;
        }
        TextView show = findViewById(R.id.gradienterView);
        String str = "PackageName：" + comp.getPackageName() + "\nClassName：" + comp.getClassName();
        Log.d(TAG, "onCreate: " + str);
        show.setText(str);
    }
}