package com.hades.android.example.android_about_demos.widget.videoview;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.hades.android.example.android_about_demos.R;

public class VideoViewRotateScreenTipActivity extends Activity {
    private static final String TAG = VideoViewRotateScreenTipActivity.class.getSimpleName();

    private VideoView mVideoView;
    private View mPortraitPosition;
    private View mPortraitContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_videoview_rotate_screen_tip_layout);

        mPortraitPosition = findViewById(R.id.main_portrait_position);
        mPortraitContent = findViewById(R.id.main_portrait_content);
        mVideoView = findViewById(R.id.main_videoview);

        // We use a post to call initVideoView because
        // we need the width and height of mPortraitPosition
        mVideoView.post(new Runnable() {
            @Override
            public void run() {
                initVideoView();
            }
        });
    }

    private void initVideoView() {
        mVideoView.setMediaController(new MediaController(this));
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bigbuck);
        mVideoView.setVideoURI(uri);
        mVideoView.start();
        setVideoViewPosition();
    }

    /**
     * 当屏幕方向改变时，改变视屏的位置和大小。
     */
    private void setVideoViewPosition() {
        switch (getResources().getConfiguration().orientation) {
            /**
             *横屏时，videoView全屏显示
             */
            case Configuration.ORIENTATION_LANDSCAPE: {
                // 隐藏portrait content
                mPortraitContent.setVisibility(View.GONE);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                mVideoView.setLayoutParams(params);
                break;
            }

            /**
             * 竖屏时，videoView 和白色背景视图具有相同的大小和位置，因此，获取白色背景视图的位置大小，并将其设置为 videoView 的布局参数
             */
            case Configuration.ORIENTATION_UNDEFINED:
            case Configuration.ORIENTATION_PORTRAIT:
            default: {
                // 显示portrait content
                mPortraitContent.setVisibility(View.VISIBLE);

                int[] locationArray = new int[2];
                /**
                 * 取白色背景视图的位置大小
                 */
                mPortraitPosition.getLocationOnScreen(locationArray);

                // 其设置为 videoView 的布局参数
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mPortraitPosition.getWidth(), mPortraitPosition.getHeight());
                params.leftMargin = locationArray[0];
                params.topMargin = locationArray[1];
                mVideoView.setLayoutParams(params);
                break;
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        setVideoViewPosition();
        Log.d(TAG, "onConfigurationChanged: " + newConfig.getLayoutDirection());
        super.onConfigurationChanged(newConfig);
    }
}