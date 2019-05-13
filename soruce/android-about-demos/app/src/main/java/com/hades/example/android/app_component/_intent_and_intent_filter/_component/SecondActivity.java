package com.hades.example.android.app_component._intent_and_intent_filter._component;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.TextView;

import com.hades.example.android.R;

public class SecondActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_component_2);

        ComponentName comp = getIntent().getComponent();
        if (null == comp) {
            return;
        }
        TextView show = findViewById(R.id.show);
        show.setText("PackageName：" + comp.getPackageName() + "\nClassName：" + comp.getClassName());
    }
}
