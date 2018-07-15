package com.hades.android.example.android_about_demos.resource.drawable.state_list;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.resource.drawable.state_list.custom_selector_drawable.CustomImageView;

public class StateListDrawableActivity extends Activity {
    CustomImageView mCustomImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_state_list_drawable);
        mCustomImageView = findViewById(R.id.message_read_status);
        mCustomImageView.setImageResource(R.drawable.message_read_status_2);

        findViewById(R.id.read).setOnClickListener(v -> read());
        findViewById(R.id.unread).setOnClickListener(v -> unread());
    }

    private void read() {
        mCustomImageView.setMessageRead(true);
    }

    private void unread() {
        mCustomImageView.setMessageRead(false);
    }
}
