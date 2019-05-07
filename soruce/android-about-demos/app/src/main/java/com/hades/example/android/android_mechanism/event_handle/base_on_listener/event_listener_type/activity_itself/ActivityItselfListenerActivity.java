package com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.activity_itself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

/**
 * 基于监听的事件处理 - Activity本身
 */
public class ActivityItselfListenerActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_handler_base_on_listener);

        // 事件源 = 按钮
        // 注册事件监听器
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showTip();
    }

    private void showTip() {
        Toast.makeText(this, "Button clicked！" + getResources().getString(R.string.listener_type_activity_itself), Toast.LENGTH_SHORT).show();
    }
}