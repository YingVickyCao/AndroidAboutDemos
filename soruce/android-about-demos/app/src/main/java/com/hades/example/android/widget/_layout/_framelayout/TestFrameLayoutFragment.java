package com.hades.example.android.widget._layout._framelayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestFrameLayoutFragment extends BaseFragment {
    TextView textViewInFrameLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_layout_framelayout, container, false);

        textViewInFrameLayout = view.findViewById(R.id.textViewInFrameLayout);
        view.findViewById(R.id.setPositionCenterAndTextCenter).setOnClickListener(v -> setPositionCenterAndTextCenter());
        return view;
    }

    private void setPositionCenterAndTextCenter() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textViewInFrameLayout.getLayoutParams();
        // android:layout_gravity="center"
        layoutParams.gravity = Gravity.CENTER;
        textViewInFrameLayout.setLayoutParams(layoutParams);

//        android:gravity="center"
        textViewInFrameLayout.setGravity(Gravity.CENTER);
    }
}