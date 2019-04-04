package com.example.hades.androidpo;

import android.app.Fragment;
import android.widget.Toast;

public class BaseFragment extends Fragment {
    protected void showToast(String msg) {
        getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show());
    }
}
