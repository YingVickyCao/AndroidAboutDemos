package com.hades.android.example.android_about_demos.resource.drawable.state_list.custom_selector_drawable;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;

public class CustomSelectedStatusActivity2 extends Activity {
    CustomImageView mCustomImageView;
    CustomTextView selectedStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_state_list_drawable_4_custom_selected_status);
        mCustomImageView = findViewById(R.id.message_read_status);
        selectedStatus = findViewById(R.id.selectedStatus);

        mCustomImageView.setImageResource(R.drawable.message_read_status_2);

        findViewById(R.id.read).setOnClickListener(v -> read());
        findViewById(R.id.unread).setOnClickListener(v -> unread());
    }

    private void read() {
        mCustomImageView.setMessageRead(true);
        selectedStatus.setMessageRead(true);
    }

    private void unread() {
        mCustomImageView.setMessageRead(false);
        selectedStatus.setMessageRead(false);
    }
}