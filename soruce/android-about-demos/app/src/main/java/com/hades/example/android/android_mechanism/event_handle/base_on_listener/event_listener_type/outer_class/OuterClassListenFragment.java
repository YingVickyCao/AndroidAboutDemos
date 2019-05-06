package com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.outer_class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * 基于监听的事件处理 - 外部类形式
 */
public class OuterClassListenFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_handler_base_on_listener, container, false);
        // 事件源 = 按钮
        // 注册事件监听器
        view.findViewById(R.id.btn).setOnClickListener(new OuterClassOnClickListener());
        return view;
    }
}