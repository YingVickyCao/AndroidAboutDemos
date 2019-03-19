package com.hades.example.android.widget.custom_view.cascadelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * Created by hades on 17/03/2018.
 * 扑克牌游戏中的玩家手牌
 */
public class CascadeLayout2Fragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.widget_cascadelayout_layout_2, container, false);
    }
}