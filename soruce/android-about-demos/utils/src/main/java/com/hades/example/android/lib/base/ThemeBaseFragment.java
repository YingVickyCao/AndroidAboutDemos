package com.hades.example.android.lib.base;

import android.app.TaskStackBuilder;

import com.hades.example.android.lib.R;
import com.hades.example.android.lib.mock.SFMock;

public class ThemeBaseFragment extends BaseFragment {

    protected void setLightTheme() {
        boolean isRedTheme = SFMock.getInstance().isRedTheme();
        if (isRedTheme) {
            return;
        }
        SFMock.getInstance().useRedTheme(true);

        applyTheme();
    }

    protected void setDarkTheme() {
        boolean isRedTheme = SFMock.getInstance().isRedTheme();
        if (!isRedTheme) {
            return;
        }
        SFMock.getInstance().useRedTheme(false);

        applyTheme();
    }

    protected void applyTheme() {
        getActivity().finish();
        TaskStackBuilder.create(getActivity()).addNextIntent(getActivity().getIntent()).startActivities();
    }

    protected void setTheme() {
        boolean isLightTheme = SFMock.getInstance().isRedTheme();
        if (isLightTheme) {
            getActivity().setTheme(R.style.AppTheme_Light);
        } else {
            getActivity().setTheme(R.style.AppTheme_Dark);
        }
    }
}
