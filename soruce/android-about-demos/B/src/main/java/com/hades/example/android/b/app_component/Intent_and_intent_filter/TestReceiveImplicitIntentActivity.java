package com.hades.example.android.b.app_component.Intent_and_intent_filter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hades.example.android.b.R;

public class TestReceiveImplicitIntentActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_filter_4_receive_implicit_intent);

        String result = getIntent().getDataString();
        ((TextView) findViewById(R.id.result)).setText(result);
    }
}