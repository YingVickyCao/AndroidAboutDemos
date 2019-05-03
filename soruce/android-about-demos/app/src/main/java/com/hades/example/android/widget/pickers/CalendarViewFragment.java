package com.hades.example.android.widget.pickers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class CalendarViewFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_calendarview, container, false);
        ((CalendarView) view.findViewById(R.id.calendarView)).setOnDateChangeListener((view1, year, month, dayOfMonth)
                -> Toast.makeText(getUsedContext(), "你生日是" + year + "年" + month + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show());
        return view;
    }

    private Context getUsedContext() {
        return getActivity();
    }
}
