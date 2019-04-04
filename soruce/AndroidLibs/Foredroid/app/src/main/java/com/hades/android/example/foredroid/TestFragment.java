package com.hades.android.example.foredroid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjl.foreground.Foreground;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestFragment extends Fragment implements Foreground.Listener {
    public static final String TAG = TestFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Foreground.get().addListener(this);
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Foreground.get().removeListener(this);
    }

    @Override
    public void onBecameForeground() {
        Log.d(TAG, "onBecameForeground," + TAG + "@" + hashCode() + ",Foreground.get().isForeground()=" + Foreground.get().isForeground());
    }

    @Override
    public void onBecameBackground() {
        Log.d(TAG, "onBecameBackground," + TAG + "@" + hashCode() + ",Foreground.get().isForeground()=" + Foreground.get().isForeground());
    }
}
