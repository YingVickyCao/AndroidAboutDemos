package com.hades.example.android.lib.base;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    protected void showToast(String msg) {
        getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show());
    }
}