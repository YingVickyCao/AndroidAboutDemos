package com.hades.example.android.resource.drawable._bitmap.three_level_cache;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.utils.VersionUtil;
import com.hades.example.android.lib.utils.bitmap.fetch.ImageFetcher;
import com.hades.example.android.lib.utils.bitmap.fetch.OnImageLoadedListener;

public class ImageDetailFragment extends BaseFragment implements OnImageLoadedListener {
    private static final String IMAGE_DATA_EXTRA = "extra_image_data";
    private String mImageUrl;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private ImageFetcher mImageFetcher;

    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment f = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString(IMAGE_DATA_EXTRA, imageUrl);
        f.setArguments(args);

        return f;
    }

    public ImageDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString(IMAGE_DATA_EXTRA) : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.res_image_detail_fragment, container, false);
        mImageView = v.findViewById(R.id.imageView);
        mProgressBar = v.findViewById(R.id.progressbar);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Use the parent activity to load the image asynchronously into the ImageView (so a single
        // cache can be used over all pages in the ViewPager
        if (ImageDetailActivity.class.isInstance(getActivity())) {
            mImageFetcher = ((ImageDetailActivity) getActivity()).getImageFetcher();
            mImageFetcher.loadImage(mImageUrl, mImageView, this);
        }

        // Pass clicks on the ImageView to the parent activity to handle
        if (OnClickListener.class.isInstance(getActivity()) && VersionUtil.isNoLessThanV3()) {
            mImageView.setOnClickListener((OnClickListener) getActivity());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImageView != null) {
            mImageFetcher.cancelWork(mImageView);
            mImageView.setImageDrawable(null);
        }
    }

    @Override
    public void onImageLoaded(boolean success) {
        if (success) {
            mProgressBar.setVisibility(View.GONE);
        } else {
            // TODO: ERROR
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
