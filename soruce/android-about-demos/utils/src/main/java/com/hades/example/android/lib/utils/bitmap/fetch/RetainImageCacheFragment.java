package com.hades.example.android.lib.utils.bitmap.fetch;

import android.os.Bundle;

import com.hades.example.android.lib.base.BaseFragment;

public class RetainImageCacheFragment extends BaseFragment {
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
