package com.hades.example.android;

import android.os.Environment;

import java.io.File;

public class Constant {
    public final static int KEY_SEND = 0x345;
    public final static int KEY_RECEIVE = 0x123;

    public final static String SHARED_PREFERENCES_FILE_NAME = "sf";
    public final static String SHARED_PREFERENCES_KEY_DOWNLOAD = "DOWNLOAD";

    public final static String IMAGE_FILE_NAME = "house.jpg";
    public final static String IMAGE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + Constant.IMAGE_FILE_NAME;
    public final static String IMAGE_URL = "https://YingVickyCao.github.io/img/resources/house.jpg";

}
