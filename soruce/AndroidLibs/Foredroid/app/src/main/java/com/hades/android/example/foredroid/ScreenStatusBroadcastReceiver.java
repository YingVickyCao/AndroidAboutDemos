package com.hades.android.example.foredroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenStatusBroadcastReceiver extends BroadcastReceiver {
    private ScreenStatusListener screenStatusListener;

    public ScreenStatusBroadcastReceiver(ScreenStatusListener screenStatusListener) {
        this.screenStatusListener = screenStatusListener;
    }

    public void setScreenStatusListener(ScreenStatusListener screenStatusListener) {
        this.screenStatusListener = screenStatusListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == intent.getAction()) {
            return;
        }
        switch (intent.getAction()) {
            case Intent.ACTION_SCREEN_ON:
                if (null != screenStatusListener) {
                    screenStatusListener.actionScreenOn();
                }
                break;

            case Intent.ACTION_SCREEN_OFF:
                if (null != screenStatusListener) {
                    screenStatusListener.actionScreenOff();
                }
                break;

            case Intent.ACTION_USER_PRESENT:
                if (null != screenStatusListener) {
                    screenStatusListener.actionUserPresent();
                }
                break;
        }
    }
}