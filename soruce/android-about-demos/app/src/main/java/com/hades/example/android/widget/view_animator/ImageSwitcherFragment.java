package com.hades.example.android.widget.view_animator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * ImageSwitcher
 * 一个左右滑动浏览图片的Demo
 */
public class ImageSwitcherFragment extends BaseFragment {

    private ImageSwitcher imageSwicher;

    // 图片数组
    private int[] arrayPictures = {R.drawable.dot_image, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4};

    // 要显示的图片在图片数组中的Index
    private int pictureIndex;
    // 左右滑动时手指按下的X坐标
    private float touchDownX;
    // 左右滑动时手指松开的X坐标
    private float touchUpX;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_imageswitcher, container, false);

        imageSwicher = view.findViewById(R.id.imageSwicher);
        /**
         * ImageSwitcher 设置Factory，使用makeView（）返回 ImageView
         */
        imageSwicher.setFactory(this::makeView2);

        /**
         * 设置ImageSwitcher左右滑动事件
         */
        imageSwicher.setOnTouchListener(this::onTouch2);
        return view;
    }

    public View makeView2() {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(arrayPictures[pictureIndex]);
        return imageView;
    }

    public boolean onTouch2(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 取得左右滑动时手指按下的X坐标
            touchDownX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 取得左右滑动时手指松开的X坐标
            touchUpX = event.getX();
            // 从左往右，看前一张
            if (touchUpX - touchDownX > 100) {
                // 取得当前要看的图片的index
                pictureIndex = pictureIndex == 0 ? arrayPictures.length - 1 : pictureIndex - 1;

                /**
                 * 设置图片切换的动画
                 */
                imageSwicher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                imageSwicher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right));

                // 设置当前要看的图片
                imageSwicher.setImageResource(arrayPictures[pictureIndex]);

                // 从右往左，看下一张
            } else if (touchDownX - touchUpX > 100) {
                // 取得当前要看的图片的index
                pictureIndex = pictureIndex == arrayPictures.length - 1 ? 0 : pictureIndex + 1;
                /**
                     * 设置图片切换的动画
                     * 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left_2和slide_in_right_2
                     */
                    imageSwicher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left_2));
                    imageSwicher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right_2));

                    /**
                     * 设置当前要看的图片
                     */
                    imageSwicher.setImageResource(arrayPictures[pictureIndex]);

            }
            return true;
        }
        return false;
    }
}