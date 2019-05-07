package com.hades.example.android.android_mechanism.event_handle.base_on_listener;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * 基于监听的事件处理
 */
public class TestEventHandlerBaseOnListenFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_handler_base_on_listener, container, false);
        // 事件源 = 按钮
        // 注册事件监听器
//        view.findViewById(R.id.bn).setOnClickListener(new MyClickListener());

        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTip();
            }
        });
        return view;
    }
    
    // 事件监听器
    class MyClickListener implements View.OnClickListener {
        // 事件处理器
        @Override
        public void onClick(View v) {
            showTip();
        }
    }

    private void showTip() {
        Toast.makeText(getActivity(), "Button clicked！", Toast.LENGTH_SHORT).show();
    }
}