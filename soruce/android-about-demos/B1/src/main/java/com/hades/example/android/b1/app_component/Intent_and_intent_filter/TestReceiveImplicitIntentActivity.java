package com.hades.example.android.b1.app_component.Intent_and_intent_filter;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.hades.example.android.b1.R;

public class TestReceiveImplicitIntentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_filter_4_receive_implicit_intent);

        String result = getIntent().getDataString();
        ((TextView) findViewById(R.id.result)).setText(result);
    }
}