package com.hades.example.android.widget.pickers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class NumberPickerFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_numberpicker, container, false);

        NumberPicker np = view.findViewById(R.id.np);
        TextView mShow = view.findViewById(R.id.tableContentList);

        /**
         * 格式化NumberPicker内部的数字
         */
        np.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                // TODO: 23/06/2018 NumberPicker.setFormatter
//                return String.valueOf(value);
                return "Num = " + String.valueOf(value);
            }
        });

        /**
         * 值变化监听事件
         */
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                mShow.setText(String.valueOf(newVal));
                mShow.setText(String.valueOf(picker.getValue()));
            }
        });

        /**
         * 滑动状态变化监听事
         */
        np.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                switch (scrollState) {
                    /**
                     * 用户按下去然后滑动
                     */
                    case SCROLL_STATE_TOUCH_SCROLL:
                        Toast.makeText(getUsedContext(), "scroll state touch scroll", Toast.LENGTH_LONG).show();
                        break;

                    /**
                     * 正在滑动中的状态
                     */
                    case SCROLL_STATE_FLING:
                        Toast.makeText(getUsedContext(), "scroll state fling", Toast.LENGTH_LONG).show();
                        break;

                    /**
                     * 停止滑动
                     */
                    case SCROLL_STATE_IDLE:
                        Toast.makeText(getUsedContext(), "scroll state idle", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        np.setMinValue(1);
        np.setMaxValue(10);
        np.setValue(5);
        return view;
    }

    private Context getUsedContext() {
        return getActivity();
    }
}
