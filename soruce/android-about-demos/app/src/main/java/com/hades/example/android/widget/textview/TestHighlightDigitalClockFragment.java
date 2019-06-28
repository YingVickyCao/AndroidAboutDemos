package com.hades.example.android.widget.textview;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

import java.util.Calendar;
import java.util.Locale;

public class TestHighlightDigitalClockFragment extends BaseFragment {
    private static final String DATE_FORMAT = "%02d:%02d:%02d";
    private static final int REFRESH_DELAY = 500;
    private final Handler mHandler = new Handler();

    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_textview_4_highlight_digital_clock, container, false);
        mTextView = view.findViewById(R.id.main_clock_time);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.post(mTimeRefresher);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mTimeRefresher);
    }

    private final Runnable mTimeRefresher = new Runnable() {
        @Override
        public void run() {
            mTextView.setText(String.format(Locale.getDefault()
                    , DATE_FORMAT
                    , Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                    , Calendar.getInstance().get(Calendar.MINUTE)
                    , Calendar.getInstance().get(Calendar.SECOND)));

            mHandler.postDelayed(this, REFRESH_DELAY);
        }
    };
}
