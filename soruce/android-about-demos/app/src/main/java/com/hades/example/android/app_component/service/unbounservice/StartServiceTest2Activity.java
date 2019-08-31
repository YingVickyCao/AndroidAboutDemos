package com.hades.example.android.app_component.service.unbounservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hades.example.android.R;

public class StartServiceTest2Activity extends Activity {
    private static final String TAG = StartServiceTest2Activity.class.getSimpleName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_start_service_test);

        findViewById(R.id.subject).setVisibility(View.GONE);
        findViewById(R.id.start).setOnClickListener(v -> start());
        findViewById(R.id.stopRecord).setOnClickListener(v -> stop());
        findViewById(R.id.jump).setVisibility(View.GONE);
    }

    private void start() {
        final Intent intent = new Intent(this, FirstService.class);
        /*
        TODO: When click button, always print log "ViewRootImpl@a1d9495"
        D/ViewRootImpl@a1d9495[StartServiceTest2Activity]: ViewPostImeInputStage processPointer 0
        D/ViewRootImpl@a1d9495[StartServiceTest2Activity]: ViewPostImeInputStage processPointer 1
         */
        Log.d(TAG, "start: startService");
        startService(intent);
    }

    private void stop() {
        final Intent intent = new Intent(this, FirstService.class);
        //  When stopService, Intent can be a new one. No need to keep intent when startService();
        Log.d(TAG, "stop: stopService()");
        stopService(intent);
    }
}