package com.hades.android.example.android_about_demos.resource.drawable;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hades.android.example.android_about_demos.R;

public class DrawableFragment extends Fragment {
    private static final String TAG = DrawableFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawable, container, false);
        view.findViewById(R.id.getScreenDensity).setOnClickListener(v -> getScreenDensity());
        return view;
    }

    private void getScreenDensity() {
        int xdpi = (int) getResources().getDisplayMetrics().xdpi;
        int ydpi = (int) getResources().getDisplayMetrics().ydpi;
        // xdpi=442,xdpi=443
        Log.d(TAG, "getScreenDensity: xdpi=" + xdpi + ",xdpi=" + ydpi);
        Toast.makeText(getActivity(), "xdpi=" + xdpi + ",xdpi=" + ydpi, Toast.LENGTH_SHORT).show();
    }
}
