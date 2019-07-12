package com.hades.example.android.resource.anim;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

public class TestTweenAnimationFragment extends Fragment {
    private static final String TAG = TestTweenAnimationFragment.class.getSimpleName();

    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_tween_animation, container, false);

        image = view.findViewById(R.id.imageView);

        view.findViewById(R.id.alpha).setOnClickListener(v -> alpha());
        view.findViewById(R.id.scale).setOnClickListener(v -> scale());
        view.findViewById(R.id.translate).setOnClickListener(v -> translate());
        view.findViewById(R.id.rotate).setOnClickListener(v -> rotate());
        view.findViewById(R.id.combineAnim).setOnClickListener(v -> combineAnim());

        return view;
    }

    /**
     * https://developer.android.google.cn/guide/topics/resources/animation-resource
     * <p>
     * https://www.cnblogs.com/dongweiq/p/6863521.html
     * https://www.cnblogs.com/carbs/p/5302908.html
     * <p>
     * LayerAnimation
     * https://blog.csdn.net/witernie/article/details/55050965
     * <p>
     * AlphaAnimation
     * https://www.cnblogs.com/sishuiliuyun/p/3167581.html
     */
    private void alpha() {
        final Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha); // AlphaAnimation
        // 设置动画结束后保留结束状态
        anim.setFillAfter(false);
        Log.d(TAG, "alpha: ");
        image.startAnimation(anim);
    }

    private void scale() {
        final Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_scale); // ScaleAnimation
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }

    private void translate() {
        final Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translate); // TranslateAnimation
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }

    private void rotate() {
        final Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_rotate); // RotateAnimation
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }

    private void combineAnim() {
        final Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_set); // AnimationSet
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }
}