package com.hades.example.android.app_component._intent_and_intent_filter._action_category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public class C extends AppCompatActivity {
    private static final String TAG = C.class.getSimpleName();

    public static final String ACTION_3 = C.class.getCanonicalName() + "." + "ACTION_3";
    public static final String CATEGORY_3 = C.class.getCanonicalName() + "." + "CATEGORY_3";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intent_filter_action_and_category_b);

        TextView page = findViewById(R.id.page);
        page.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));

        TextView show = findViewById(R.id.gradienterView);
        Intent intent = getIntent();
        String str = "action：" + intent.getAction() + "\nCategories：" + intent.getCategories();
        Log.d(TAG, "onCreate: " + str);
        show.setText(str);
    }
}
