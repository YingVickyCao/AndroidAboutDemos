package com.hades.example.android.widget._layout._framelayout;

import android.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hades.example.android.R;

public class TestFrameLayoutFragment extends Fragment {
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