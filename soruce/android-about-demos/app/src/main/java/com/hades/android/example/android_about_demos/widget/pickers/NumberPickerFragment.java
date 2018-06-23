package com.hades.android.example.android_about_demos.widget.pickers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;

public class NumberPickerFragment extends Fragment {
    public static NumberPickerFragment newInstance() {

        Bundle args = new Bundle();

        NumberPickerFragment fragment = new NumberPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_numberpicker, container, false);

        NumberPicker np = view.findViewById(R.id.np);
        TextView mShow = view.findViewById(R.id.show);

        np.setMinValue(1);
        np.setMaxValue(10);
        np.setValue(5);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mShow.setText(String.valueOf(newVal));
            }
        });
        return view;
    }
}
