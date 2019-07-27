package com.hades.example.android.app_component._intent_and_intent_filter._action_category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public class B extends AppCompatActivity {
    private static final String TAG = B.class.getSimpleName();

    public static final String ACTION_1 = B.class.getCanonicalName() + "." + "ACTION_1";
    public static final String ACTION_2 = B.class.getCanonicalName() + "." + "ACTION_2";

    public static final String CATEGORY_1 = B.class.getCanonicalName() + "." + "CATEGORY_1";
    public static final String CATEGORY_2 = B.class.getCanonicalName() + "." + "CATEGORY_2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intent_filter_action_and_category_b);

        TextView show = findViewById(R.id.gradienterView);
        Intent intent = getIntent();
        String str = "action：" + intent.getAction() + "\nCategories：" + intent.getCategories();
        Log.d(TAG, "onCreate: " + str);
        show.setText(str);
    }
}
