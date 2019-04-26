package com.hades.example.android.widget._layout._constraintlayout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

public class TestConstraintLayoutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.widget_constraintlayout, container, false);
//        return inflater.inflate(R.layout.widget_constraintlayout_relative_positing, container, false);
//        return inflater.inflate(R.layout.widget_constraintlayout_margins, container, false);
        return inflater.inflate(R.layout.widget_constraintlayout_circular_positioning, container, false);
    }
}