package com.hades.example.android.app_component._intent_and_intent_filter._flag;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.app_component._intent_and_intent_filter._launch_mode.E;

public class D extends com.hades.example.android.app_component._intent_and_intent_filter._launch_mode.D {
    private static final String TAG = D.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView topic = findViewById(R.id.topic);
        topic.setText(R.string.page_intent_Flag);


        findViewById(R.id.openD_FLAG_ACTIVITY_NO_HISTORY).setOnClickListener(v -> openD_FLAG_ACTIVITY_NO_HISTORY());
        findViewById(R.id.openD_FLAG_ACTIVITY_NO_ANIMATION).setOnClickListener(v -> openD_FLAG_ACTIVITY_NO_ANIMATION());
    }

    protected int getLayoutId() {
        return R.layout.intent_flag;
    }

    protected String getTag() {
        return TAG;
    }


    protected void openD() {
        Intent intent = new Intent(this, D.class);
        startActivity(intent);
    }

    protected void openE() {
        startActivity(new Intent(this, E.class));
    }

    /**
     * FLAG_ACTIVITY_NO_HISTORY
     * FLAG_ACTIVITY_SINGLE_TOP
     * FLAG_ACTIVITY_NEW_TASK
     * FLAG_ACTIVITY_MULTIPLE_TASK
     * FLAG_ACTIVITY_CLEAR_TOP
     * FLAG_ACTIVITY_FORWARD_RESULT
     * FLAG_ACTIVITY_PREVIOUS_IS_TOP
     * FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
     * FLAG_ACTIVITY_BROUGHT_TO_FRONT
     * FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
     * FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY
     * FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET
     * FLAG_ACTIVITY_NEW_DOCUMENT
     * FLAG_ACTIVITY_NO_USER_ACTION
     * FLAG_ACTIVITY_REORDER_TO_FRONT
     * FLAG_ACTIVITY_NO_ANIMATION
     * FLAG_ACTIVITY_TASK_ON_HOME
     * FLAG_ACTIVITY_RETAIN_IN_RECENTS
     * FLAG_ACTIVITY_LAUNCH_ADJACENT
     * FLAG_ACTIVITY_MATCH_EXTERNAL
     */
    private void openD_FLAG_ACTIVITY_NO_HISTORY() {

    }

    private void openD_FLAG_ACTIVITY_NO_ANIMATION() {
        Intent intent = new Intent(this, D.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}