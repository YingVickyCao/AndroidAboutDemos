package com.hades.example.android.other_ui._dialog.depressed;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

import java.util.Calendar;

public class TimePickerDialogFragment extends BaseFragment {

    private TextView show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_timepickerdialog, container, false);

        show = view.findViewById(R.id.tableContentList);
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
