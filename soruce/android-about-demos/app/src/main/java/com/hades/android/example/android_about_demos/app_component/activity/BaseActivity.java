package com.hades.android.example.android_about_demos.app_component.activity;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.hades.android.example.android_about_demos.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseActivity extends AppCompatActivity {
    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    private View topic;
    private ScrollView mScrollView;
    private View mFragmentRoot;

    private View mRoot;
    private RxPermissions rxPermissions;

    protected void initViews() {
        topic = findViewById(R.id.topic);
        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);

//        showDefaultFragment();
        showBtns();
    }

    protected void showBtns() {
        if (null != mScrollView) {
            mScrollView.setVisibility(View.VISIBLE);
        }

        if (null != topic) {
            topic.setVisibility(View.VISIBLE);
        }

        if (null != mFragmentRoot) {
            mFragmentRoot.setVisibility(View.GONE);
        }
    }

    protected void hideBtns() {
        if (null != mScrollView) {
            mScrollView.setVisibility(View.GONE);
        }
        if (null != topic) {
            topic.setVisibility(View.GONE);
        }
        if (null != mFragmentRoot) {
            mFragmentRoot.setVisibility(View.VISIBLE);
        }
    }

    protected void showCurrentTest() {

    }

    private void showDefaultFragment() {
        hideBtns();
        showCurrentTest();
    }

    protected boolean isShowDetail() {
        return null != mFragmentRoot && mFragmentRoot.getVisibility() == View.VISIBLE;
    }

    protected void removeDetailFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentRoot);
        getFragmentManager().beginTransaction().remove(fragment).commit();
    }

    protected void showFragment(Fragment fragment) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, fragment.getClass().getSimpleName()).commit();
    }

    protected void showActivity(Class<?> dest) {
        startActivity(new Intent(this, dest));
    }

    @Override
    public void onBackPressed() {
        if (isIgnoreBack()) {
            super.onBackPressed();
            return;
        }

        if (isShowDetail()) {
            showBtns();
            removeDetailFragment();
        } else {
            super.onBackPressed();
        }
    }

    protected boolean isIgnoreBack() {
        return false;
    }

    protected void needCheckPermission(boolean checkPermission) {
        if (checkPermission) {
            rxPermissions = new RxPermissions(this);
            rxPermissions.setLogging(true);
        }
    }

    public void setRoot(View root) {
        mRoot = root;
    }

    /**
     * @param permissions new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION}
     */
    protected void checkPermission(final String permissionRationale, final String... permissions) {
        if (!isGranted(permissions)) {
            askUser2GrantPermissions(permissionRationale, permissions);
        }
    }

    private boolean isGranted(final String... permissions) {
        if (permissions == null) {
            throw new RuntimeException("permissions is null");
        }

        for (String permission : permissions) {
            if (!rxPermissions.isGranted(permission)) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param permissions Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION
     */
    protected void requestPermission(final String... permissions) {
        rxPermissions.request(permissions).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                if (granted) {
                    Toast.makeText(BaseActivity.this, "permission available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BaseActivity.this, "permission not granted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * @param permissions Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION
     */
    protected void askUser2GrantPermissions(final String permissionRationale, final String... permissions) {
        rxPermissions.shouldShowRequestPermissionRationale(this, permissions).subscribe(new io.reactivex.functions.Consumer<Boolean>() {
            @Override
            public void accept(Boolean shouldShowRequestPermissionRationale) throws Exception {
                if (shouldShowRequestPermissionRationale) {
                    Snackbar.make(mRoot, permissionRationale, Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    requestPermission(permissions);
                                }
                            })
                            .show();
                } else {
                    requestPermission(permissions);
                }
            }
        });
    }
}