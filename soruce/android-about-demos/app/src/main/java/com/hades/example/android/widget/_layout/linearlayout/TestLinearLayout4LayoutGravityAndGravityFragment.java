package com.hades.example.android.widget._layout.linearlayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestLinearLayout4LayoutGravityAndGravityFragment extends BaseFragment {
    TextView textViewInLinearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_layout_linearlayout_4_layout_gravity_and_gravity, container, false);

        textViewInLinearLayout = view.findViewById(R.id.textViewInLinearLayout);

        view.findViewById(R.id.setPositionCenterAndTextCenter).setOnClickListener(v -> setPositionCenterAndTextCenter());
        return view;
    }

    private void setPositionCenterAndTextCenter() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textViewInLinearLayout.getLayoutParams();
        layoutParams.gravity = Gravity.CENTER;
        textViewInLinearLayout.setLayoutParams(layoutParams);

//        android:gravity="center"
        textViewInLinearLayout.setGravity(Gravity.CENTER);
    }
}