package com.hades.example.android.android_mechanism.event_handle.base_on_callback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * 基于回调的事件处理
 */
public class EventHandlerBaseOnCallbackFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_handler_base_on_callback, container, false);

        /**
         * QA: setOnClickListener设置无效
         * 1 boolean onTouchEvent(MotionEvent event) return true, onTouchEvent生效，setOnClickListener无效。
         * 因为onTouchEvent 已经处理完事件，不会继续传递。
         *
         * 2 boolean onTouchEvent(MotionEvent event) return false, onTouchEvent调用后，setOnClickListener也有效。
         * 因为onTouchEvent 没处理完事件，会继续传递。
         */
        View btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTip();
            }
        });

        return view;

    }

    private void showTip() {
        Toast.makeText(getActivity(), "Button clicked！", Toast.LENGTH_SHORT).show();
    }
}