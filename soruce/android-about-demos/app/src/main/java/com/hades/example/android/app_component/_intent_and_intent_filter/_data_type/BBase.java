package com.hades.example.android.app_component._intent_and_intent_filter._data_type;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public abstract class BBase extends AppCompatActivity {
    private TextView page;
    private TextView intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_filter_data_type_b);

        page = findViewById(R.id.page);
        intent = findViewById(R.id.intent);

        page.setText(getPageName());
        /*
         Intent {
         act=action2
         cat=[android.intent.category.DEFAULT]
         dat=scheme2:/com.hades:1000/path2
         cmp=com.hades.example.android/.app_component._intent_and_intent_filter._data.B7 }
         */
        Log.d(getPageName(), "onCreate: intent:\n" + getIntent().toString());
        intent.setText(getIntent().toString());
    }

    protected abstract String getPageName();
}