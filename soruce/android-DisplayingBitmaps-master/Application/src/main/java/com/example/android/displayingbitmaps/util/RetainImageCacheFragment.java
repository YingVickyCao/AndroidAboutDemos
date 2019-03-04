package com.example.android.displayingbitmaps.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class RetainImageCacheFragment extends Fragment {
    public static final String TAG = RetainImageCacheFragment.class.getSimpleName();

    private Object mStoredObject;

    public RetainImageCacheFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retainedFragmentOverConfigurationChange();
    }

    private void retainedFragmentOverConfigurationChange() {
        setRetainInstance(true);
    }

    public void setObject(Object object) {
        mStoredObject = object;
    }

    public Object getObject() {
        return mStoredObject;
    }
}
