package com.hades.example.android.other_ui.manager_phone_desktop.wallpaper.static_wallpaper;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

import java.io.IOException;

public class TestStaticWallpaperFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.static_wallpaper_settting, container, false);
        view.findViewById(R.id.set).setOnClickListener(v -> set());
        return view;
    }

    private void set() {
        new Thread(this::setStaticWallpaper).start();
    }

    @SuppressLint({"MissingPermission", "ResourceType"})
    private void setStaticWallpaper() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getActivity());
        try {
            wallpaperManager.setResource(R.drawable.photo2);  // expected resource of type
            getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}