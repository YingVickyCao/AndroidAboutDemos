package com.demo.SunnyDemo.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.demo.SunnyDemo.R;

/**
 * <pre>
 *  ������ת��Activity����manifest�����á�
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
