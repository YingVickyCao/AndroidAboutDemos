/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hades.example.android.resource.drawable._bitmap.three_level_cache;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.mock.Images;
import com.hades.example.android.lib.utils.VersionUtil;
import com.hades.example.android.lib.utils.bitmap.cache.ImageCacheParams;
import com.hades.example.android.lib.utils.bitmap.fetch.IImageWorker;
import com.hades.example.android.lib.utils.bitmap.fetch.ImageFetcher;

public class ImageGridFragment extends BaseFragment implements IImageWorker {
    private static final String TAG = ImageGridFragment.class.getSimpleName();
    private static final String IMAGE_CACHE_DIR = "thumbs";

    private int mImageThumbSize;
    private int mImageThumbSpacing;
    private ImageAdapter mAdapter;
    public ImageFetcher mImageFetcher;
    private GridView mGridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);

        mAdapter = new ImageAdapter(getActivity(), Images.imageThumbUrls);

        mImageFetcher = new ImageFetcher(getActivity(), mImageThumbSize);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mImageFetcher.addImageCache(getActivity().getSupportFragmentManager(), getImageCacheParams());
    }

    private ImageCacheParams getImageCacheParams() {
        return new ImageCacheParams(getActivity(), IMAGE_CACHE_DIR, 0.25f);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.res_image_grid_fragment, container, false);

        mGridView = v.findViewById(R.id.gridView);
        mGridView.setAdapter(mAdapter);
        setOnItemClickListener4GridView();
        pauseFetchImageViewOnScrollStateChanged();
        adjustGridViewItemHeightOnGlobalLayout();
        return v;
    }

    private void setOnItemClickListener4GridView() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                jumpImageDetailActivity(v, id);
            }
        });
    }

    private void jumpImageDetailActivity(View v, long id) {
        final Intent i = new Intent(getActivity(), ImageDetailActivity.class);
        i.putExtra(ImageDetailActivity.EXTRA_IMAGE, (int) id);
        if (VersionUtil.isVersionNoLessThan4_1()) {
            ActivityOptions options = ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight());
            getActivity().startActivity(i, options.toBundle());
        } else {
            startActivity(i);
        }
    }

    private void pauseFetchImageViewOnScrollStateChanged() {
        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    if (!VersionUtil.isNoLessThanV3()) {
                        mImageFetcher.setPauseWork(true);
                    }
                } else {
                    mImageFetcher.setPauseWork(false);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    private void adjustGridViewItemHeightOnGlobalLayout() {
        mGridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (mAdapter.getNumColumns() == 0) {
                            final int numColumns = getNumColumns();
                            if (numColumns > 0) {
                                mAdapter.setNumColumns(numColumns);
                                int height = getColumnWidth(numColumns);
                                if (mAdapter.setItemHeight(height)) {
                                    mImageFetcher.setImageSize(height, height);
                                }
                                Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    }
                });
    }

    private int getNumColumns() {
        return (int) Math.floor(mGridView.getWidth() / (mImageThumbSize + mImageThumbSpacing));
    }

    private int getColumnWidth(final int numColumns) {
        return (mGridView.getWidth() / numColumns) - mImageThumbSpacing;
    }

    @Override
    public void onResume() {
        super.onResume();
        mImageFetcher.setExitTasksEarly(false);
        mAdapter.notifyDataSetChanged();
        mAdapter.setListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.setListener(null);
        mImageFetcher.setPauseWork(false);
        mImageFetcher.setExitTasksEarly(true);
        mImageFetcher.flushCache();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mImageFetcher.closeCache();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_cache:
                clearCache();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearCache() {
        mImageFetcher.clearCache();
        Toast.makeText(getActivity(), R.string.clear_cache_complete_toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadImage(String url, ImageView boundImageView) {
        mImageFetcher.loadImage(url, boundImageView);
    }
}