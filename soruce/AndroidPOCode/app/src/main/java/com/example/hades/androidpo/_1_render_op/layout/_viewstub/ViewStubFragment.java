package com.example.hades.androidpo._1_render_op.layout._viewstub;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.hades.androidpo.R;

public class ViewStubFragment extends Fragment {
    private static final String TAG = ViewStubFragment.class.getSimpleName();

    private View mRoot;
    private ViewStub mViewStub;
    private View mapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewstub_layout, container, false);
        view.findViewById(R.id.showMap).setOnClickListener(this::onShowMapBtnClick);
        mRoot = view;
        return view;
    }

    /**
     * mViewStub GONE
     * mapView is null
     * mViewStub is null
     * mapView VISIBLE
     */
    private void onShowMapBtnClick(View v) {
        mViewStub = mRoot.findViewById(R.id.map_stub);
        mapView = mRoot.findViewById(R.id.map_view);

        //printMapStubStatus();
        //printMapViewStatus();

        onShowMap(v);

        mapView = mRoot.findViewById(R.id.map_view);
        mViewStub = mRoot.findViewById(R.id.map_stub);

        //printMapStubStatus();
        //printMapViewStatus();
    }

    private void printMapStubStatus() {
        // GONE
        if (null != mViewStub) {
            // VISIBLE
            if (View.VISIBLE == mViewStub.getVisibility()) {
                Log.d(TAG, "mViewStub VISIBLE ");
            } else if (View.INVISIBLE == mViewStub.getVisibility()) {
                Log.d(TAG, "mViewStub INVISIBLE ");
            } else {
                Log.d(TAG, "mViewStub GONE ");
            }
        } else {
            Log.d(TAG, "mViewStub is null");
        }

    }

    private void printMapViewStatus() {
        if (null != mapView) {
            if (View.VISIBLE == mapView.getVisibility()) {
                Log.d(TAG, "mapView VISIBLE ");
            } else if (View.INVISIBLE == mapView.getVisibility()) {
                Log.d(TAG, "mapView INVISIBLE ");
            } else {
                Log.d(TAG, "mapView GONE ");
            }

        } else {
            Log.d(TAG, "mapView is null");
        }
    }

    public void onShowMap(View v) {
        if (null != mViewStub) {
            // 加载布局的方式1：
            // mViewStub.inflate();
            // 加载布局的方式2：
            //mViewStub.setVisibility(View.VISIBLE);
            /**
             * setVisibility只是在ViewStub第一次延迟初始化时,并且visibility是非 GONE 时,调用了 inflate 方法.
             * 所以，虽然是INVISIBLE，但还是显示出来。
             * 建议使用VISIBLE，而非INVISIBLE，更好理解。
             */
            mViewStub.setVisibility(View.INVISIBLE);

        }
    }

}