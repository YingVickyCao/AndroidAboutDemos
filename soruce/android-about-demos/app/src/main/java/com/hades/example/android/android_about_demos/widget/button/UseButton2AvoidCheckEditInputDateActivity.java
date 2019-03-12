package com.hades.example.android.android_about_demos.widget.button;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.hades.example.android.android_about_demos.R;

import java.util.Calendar;

public class UseButton2AvoidCheckEditInputDateActivity extends Activity {
    private static final int DATE_DIALOG_ID = 0;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView detailsDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_btn_2_avoid_check_edit_input_date_layout);

        detailsDate = findViewById(R.id.details_date);

        mDateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            detailsDate.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            Toast.makeText(UseButton2AvoidCheckEditInputDateActivity.this, getString(R.string.picked_date_format, monthOfYear, dayOfMonth, year), Toast.LENGTH_SHORT).show();

        };

        findViewById(R.id.details_date).setOnClickListener(v -> showDialog(DATE_DIALOG_ID));
    }

    // TODO: 21/03/2018 Use DialogFragment instead.
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener
                        , Calendar.getInstance().get(Calendar.YEAR)
                        , Calendar.getInstance().get(Calendar.MONTH)
                        , Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        }
        return super.onCreateDialog(id);
    }
}
