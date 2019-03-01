/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.displayingbitmaps.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.StrictMode;

import com.example.android.displayingbitmaps.ui.ImageDetailActivity;
import com.example.android.displayingbitmaps.ui.ImageGridActivity;

/**
 * Class containing some static utility methods.
 */
public class Utils {
    private Utils() {};


    @TargetApi(VERSION_CODES.HONEYCOMB)
    public static void enableStrictMode() {
        if (Utils.isVersionNoLessThanGingerbread()) {
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder =
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyLog();
            StrictMode.VmPolicy.Builder vmPolicyBuilder =
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog();

            if (Utils.isVersionNoLessThanHoneycomb()) {
                threadPolicyBuilder.penaltyFlashScreen();
                vmPolicyBuilder
                        .setClassInstanceLimit(ImageGridActivity.class, 1)
                        .setClassInstanceLimit(ImageDetailActivity.class, 1);
            }
            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }

    public static boolean isVersionNoLessThanFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= VERSION_CODES.FROYO;// 2.2
    }

    public static boolean isVersionNoLessThanGingerbread() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD; // >=2.3
    }

    public static boolean isVersionNoLessThanHoneycomb() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB; // >=3.0
    }

    public static boolean isVersionNoLessThanHoneycombMR1() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR1; // >=3.1
    }

    public static boolean isVersionNoLessThanJellyBean() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN; // >=4.1
    }

    public static boolean isVersionNoLessThanKitKat() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT; // >=4.4
    }
}
