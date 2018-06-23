package com.hades.android.example.android_about_demos.other_ui.dialog;

import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hades.android.example.android_about_demos.R;

import java.util.Calendar;

public class TimePickerDialogFragment extends Fragment {
    public static TimePickerDialogFragment newInstance() {
        Bundle args = new Bundle();
        TimePickerDialogFragment fragment = new TimePickerDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_timepickerdialog, container, false);

        show = view.findViewById(R.id.show);
        view.findViewById(R.id.timeBn).setOnClickListener(source -> showTimePickerDialog());
        return view;
    }

    private void showTimePickerDialog() {
        final Calendar c = Calendar.getInstance();
        new TimePickerDialog(getUsedContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                        show.setText(hourOfDay + "时" + minute + "分");
                    }
                }
                , c.get(Calendar.HOUR_OF_DAY)
                , c.get(Calendar.MINUTE)
                //true表示采用24小时制
                , true).show();
    }

    private Context getUsedContext() {
        return getActivity();
    }
}
