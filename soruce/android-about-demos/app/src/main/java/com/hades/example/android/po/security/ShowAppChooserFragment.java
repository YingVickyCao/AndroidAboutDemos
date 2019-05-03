package com.hades.example.android.po.security;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

import java.util.List;

public class ShowAppChooserFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.security_app_chooser, container, false);
        view.findViewById(R.id.click).setOnClickListener(v -> click());
        return view;
    }

    private void click() {
        send();
    }

    private void send() {
        Intent intent = new Intent(Intent.ACTION_SEND                                                                                             );
        intent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
        List<ResolveInfo> activities = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
        if (activities.size() > 1) {
            String title = "Share";
            Intent chooser = Intent.createChooser(intent, title);
            startActivity(chooser);
        } else if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}