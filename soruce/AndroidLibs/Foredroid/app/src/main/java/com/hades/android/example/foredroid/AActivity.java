package com.hades.android.example.foredroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sjl.foreground.Foreground;

import androidx.fragment.app.Fragment;

public class AActivity extends MainActivity {
    protected static final String TAG = AActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.startA2).setVisibility(View.GONE);
    }

    protected void checkForegroundBackground() {
        super.checkForegroundBackground();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentRoot, new TestFragment(), TestFragment.TAG).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TestFragment.TAG);
        if (null != fragment) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
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