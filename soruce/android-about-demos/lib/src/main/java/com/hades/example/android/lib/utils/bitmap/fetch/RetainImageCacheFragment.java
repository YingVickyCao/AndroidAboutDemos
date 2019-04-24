package com.hades.example.android.lib.utils.bitmap.fetch;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

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
