package com.hades.example.android.resource.adapter_screen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

public class TestSaveAndRestoreStateActivity extends Activity {

    private static final String GAME_STATE_KEY = "GAME_STATE";
    private static final String TAG = TestSaveAndRestoreStateActivity.class.getSimpleName();

    // some transient state for the activity instance
    String mGameState;

    private TextView mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_restore_state);

        mData = findViewById(R.id.data);

        // recovering the instance state
        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate: savedInstanceState hashcode=" + hashCode());
            mGameState = savedInstanceState.getString(GAME_STATE_KEY);
            mData.setText(mGameState);
        }
    }

    // The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String saved = savedInstanceState.getString(GAME_STATE_KEY);
            Log.d(TAG, "onRestoreInstanceState: savedInstanceState hashcode=" + hashCode());
            Log.d(TAG, "onRestoreInstanceState: saved=" + saved);

            mGameState = savedInstanceState.getString(GAME_STATE_KEY);
            mData.setText(mGameState);
        }
    }

    //     invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        String saved = String.valueOf(System.currentTimeMillis());
        Log.d(TAG, "onSaveInstanceState: saved=" + saved);
        outState.putString(GAME_STATE_KEY, saved);
//        call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }
}
