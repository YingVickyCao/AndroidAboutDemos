package com.hades.android.example.android_about_demos.other_ui.dialog;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;

import java.util.Calendar;

public class DateTimePickerDialogFragment extends Fragment {
    public static DateTimePickerDialogFragment newInstance() {
        Bundle args = new Bundle();
        DateTimePickerDialogFragment fragment = new DateTimePickerDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    TextView show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_datepickerdialog, container, false);

        show = view.findViewById(R.id.tableContentList);
        view.findViewById(R.id.dateBn).setOnClickListener(source -> showDatePickerDialog());
        return view;
    }

    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(getUsedContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                        show.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
                    }
                }
                , c.get(Calendar.YEAR)
                , c.get(Calendar.MONTH)
                , c.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private Context getUsedContext() {
        return getActivity();
    }

}
