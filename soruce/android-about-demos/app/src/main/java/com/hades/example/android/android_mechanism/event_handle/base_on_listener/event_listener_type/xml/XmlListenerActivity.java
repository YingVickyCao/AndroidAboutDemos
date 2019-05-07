package com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.xml;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

/**
 * 基于监听的事件处理 - xml onClick 属性
 */
public class XmlListenerActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_listener_type_xml);
    }

    public void onBtnClick(View v) {
        showTip();
    }

    private void showTip() {
        Toast.makeText(this, "Button clicked！" + getResources().getString(R.string.listener_type_activity_itself), Toast.LENGTH_SHORT).show();
    }
}