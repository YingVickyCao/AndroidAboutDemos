package com.demo.SunnyDemo.ui;

import android.app.Activity;
import android.os.Bundle;

/**
 * <pre>
 * BitmapDrawable¡∑œ∞
 * </pre>
 */
public class BitmapDrawableActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		BitmapDrawableView bitmapDrawableView = new BitmapDrawableView(BitmapDrawableActivity.this);
		setContentView(bitmapDrawableView);
	}

}
