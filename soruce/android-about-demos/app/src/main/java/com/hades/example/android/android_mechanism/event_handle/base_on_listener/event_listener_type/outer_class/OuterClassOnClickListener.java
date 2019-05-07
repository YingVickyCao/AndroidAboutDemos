package com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.outer_class;

import android.view.View;
import android.widget.Toast;

import com.hades.example.android.R;

public class OuterClassOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        showTip(v);
    }

    private void showTip(View v) {
        Toast.makeText(v.getContext(), "Button clicked！" + v.getContext().getResources().getString(R.string.listener_type_outer_class), Toast.LENGTH_SHORT).show();
    }
}
