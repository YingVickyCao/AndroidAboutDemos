package com.hades.example.android.lib.utils;

import android.content.Context;
import android.util.TypedValue;

public class ThemeUtils {
    /**
     * @param context
     * @param attrResId e.g. R.attr.color1
     * @return e.g. Light: <item name="color1">@android:color/holo_red_light</item> Dark : <item name="color1">@android:color/holo_orange_dark</item>
     */
    public static int getResourceIdByAttrId(Context context, int attrResId) {
        TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(attrResId, tv, true);
        return tv.resourceId;
    }

    /**
     * @param context
     * @param attrResId e.g. android.R.attr.actionBarSize (TYPE_DIMENSION)
     * @return e.g. dp
     */
    public static int getDataInValueIdByAttrId(Context context, int attrResId) {
        TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(attrResId, tv, true);
        return tv.data;
    }
}
