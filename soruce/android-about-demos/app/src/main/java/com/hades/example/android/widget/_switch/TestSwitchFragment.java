package com.hades.example.android.widget._switch;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

public class TestSwitchFragment extends Fragment {
    private static final String TAG = TestSwitchFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_switch, container, false);

        ((Switch) view.findViewById(R.id.switch1)).setOnCheckedChangeListener((buttonView, isChecked) -> onCheckedChange4Switch(isChecked));

        return view;
    }

    private void onCheckedChange4Switch(boolean isChecked) {
        Log.d(TAG, "onCheckedChanged: " + isChecked);
        Toast.makeText(getActivity(), "Switch " + (isChecked ? "checked" : "unCheck"), Toast.LENGTH_SHORT).show();
    }
}