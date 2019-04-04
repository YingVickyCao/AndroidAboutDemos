package com.demo.SunnyDemo.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.demo.SunnyDemo.R;

/**
 * <pre>
 *  测试跳转到Activity不在manifest中配置。
 * @author caoying
 * </pre>
 */
public class NoManifestActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nomanifest);
	}
}
