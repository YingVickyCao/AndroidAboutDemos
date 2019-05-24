package com.hades.example.android.app_component._intent_and_intent_filter._launch_mode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public class D extends AppCompatActivity {
    private static final String TAG = D.class.getSimpleName();
    public static int num = 0;
    private final String NAME_D = "D";

    protected TextView name;
    protected TextView id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        findViewById(R.id.openD).setOnClickListener(v -> openD());
        findViewById(R.id.openE).setOnClickListener(v -> openE());

        name = findViewById(R.id.name);
        name.setText(getName());

        num++;
        id = findViewById(R.id.id);
        id.setText(String.valueOf(num));

        TextView taskId = findViewById(R.id.taskId);
        taskId.setText(String.valueOf(getTaskId()));

        Log.e(getTag(), "onCreate:" + getOnCreatePrint());
    }

    protected int getLayoutId() {
        return R.layout.activity_launch_mode;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(getTag(), "onNewIntent:" + getOnCreatePrint());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(getTag(), "onRestart: " + getOnCreatePrint());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(getTag(), "onStart: " + getOnCreatePrint());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(getTag(), "onResume:" + getOnCreatePrint());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(getTag(), "onDestroy: " + getOnDestroyPrint());
    }

    protected String getName() {
        return NAME_D;
    }

    protected void openD() {
        startActivity(new Intent(this, D.class));
    }

    protected void openE() {
        startActivity(new Intent(this, E.class));
    }

    protected String getOnCreatePrint() {
        return "taskId=" + getTaskId() + ',' + getName() + "," + num + ",@" + hashCode();
    }

    protected String getOnDestroyPrint() {
        return "taskId=" + getTaskId() + ',' + getName() + "," + name.getText() + ",@" + hashCode();
    }

    protected String getTag() {
        return TAG;
    }

}