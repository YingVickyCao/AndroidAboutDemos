package com.hades.android.example.android_about_demos.widget.pickers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hades.android.example.android_about_demos.R;

import java.util.Calendar;

public class DateTimePickerFragment extends Fragment {

    public static DateTimePickerFragment newInstance() {

        Bundle args = new Bundle();

        DateTimePickerFragment fragment = new DateTimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView mShow;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_datepicker_timepicker, container, false);

        mShow = view.findViewById(R.id.show);

        DatePicker mDatePicker = view.findViewById(R.id.datePicker);
        TimePicker timePicker = view.findViewById(R.id.timePicker);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR);
        mMinute = c.get(Calendar.MINUTE);

        mDatePicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker arg0, int year, int month, int day) {
                mYear = year;
                mMonth = month;
                mDay = day;

                showDate(mYear, mMonth, mDay, mHour, mMinute);
            }
        });

        timePicker.setEnabled(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                showDate(mYear, mMonth, mDay, mHour, mMinute);
            }
        });
        return view;
    }

    private void showDate(int year, int month, int day, int hour, int minute) {
        mShow.setText(year + "年" + (month + 1) + "月" + day + "日  " + hour + "时" + minute + "分");
    }
}
