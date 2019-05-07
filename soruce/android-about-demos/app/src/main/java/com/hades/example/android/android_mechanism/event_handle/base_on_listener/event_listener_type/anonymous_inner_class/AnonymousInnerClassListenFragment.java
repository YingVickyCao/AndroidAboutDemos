package com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.anonymous_inner_class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * 基于监听的事件处理 - 匿名内部类
 */
public class AnonymousInnerClassListenFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_handler_base_on_listener, container, false);
        // 事件源 = 按钮
        // 注册事件监听器
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTip();
            }
        });
        return view;
    }

    private void showTip() {
        Toast.makeText(getActivity(), "Button clicked！" + getResources().getString(R.string.listener_type_4_anonymous_inner_class), Toast.LENGTH_SHORT).show();
    }
}