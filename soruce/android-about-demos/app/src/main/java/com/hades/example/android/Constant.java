package com.hades.example.android;

import com.hades.example.android.lib.utils.FileUtil;

public class Constant {
    public final static int KEY_SEND = 0x345;
    public final static int KEY_UPDATE_UI = 0x123;

    public final static int PERIOD_1_MINUTE = 1000;

    public final static String SHARED_PREFERENCES_FILE_NAME = "sf";
    public final static String SHARED_PREFERENCES_KEY_DOWNLOAD = "DOWNLOAD";

    public final static String IMAGE_FILE_NAME = "house.jpg";
    public final static String IMAGE_PATH = FileUtil.buildFileNameInSD(Constant.IMAGE_FILE_NAME);
    public final static String IMAGE_URL = "https://YingVickyCao.github.io/img/resources/house.jpg";
    public final static String IMAGE_URL2 = "https://yingvickycao.github.io/img/resources/ic_adjust_black_18dp.png";

    public final static String MP3_NAME = "mp3_1.mp3";
    public final static String MP3_NAME_2 = "https://yingvickycao.github.io/audio/mp3.mp3";


}
