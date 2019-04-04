package demo.service_sundydemo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * <pre>
 * 使用Activity播放music。
 * 使用Start Service播放music。
 * 
 * <pre>
 */
public class PlayMusicActivity extends Activity {
	private static String FILE_NAME;
	private static String TAG = "SunnyDemo";
	
	
	private MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.service_music);
		FILE_NAME = getClass().getSimpleName() + "----";
		Log.i(TAG, FILE_NAME + "onCreate");

		// 加载raw文件夹下的音乐资源
		mediaPlayer = MediaPlayer.create(PlayMusicActivity.this, R.raw.maria);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();

		Log.i(TAG, FILE_NAME + "onRestart");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		Log.i(TAG, FILE_NAME + "onStart");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		Log.i(TAG, FILE_NAME + "onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		Log.i(TAG, FILE_NAME + "onStop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		Log.i(TAG, FILE_NAME + "onDestroy");
	}

	// 使用Activity操作music
	public void doClick(View view) {
		Log.i(TAG, FILE_NAME + "doClick");
		switch (view.getId()) {
		// 使用Activity直接播放音乐
		case R.id.btn_start_norm:
			// 如果不加上这个，调用stop就释放资源了，不能重新播放
			if (null == mediaPlayer) {
				mediaPlayer = MediaPlayer.create(PlayMusicActivity.this, R.raw.maria);
			}
			mediaPlayer.start();
			break;

		// 使用Activity暂停音乐
		case R.id.btn_pause_norm:
			mediaPlayer.pause();
			break;

		// 使用Activity停止播放
		case R.id.btn_stop_norm:
			mediaPlayer.stop();
			// TODO 性能
			// 释放资源
			mediaPlayer.release();
			// 取消资源
			mediaPlayer = null;
			break;
		}
	}

	// 使用Start Service操作music
	public void doClick_Service(View view) {
		Log.i(TAG, FILE_NAME + "doClick_Service");
		switch (view.getId()) {

		// 使用Start Service播放音乐
		case R.id.btn_start_service:
			// TODO 性能
			Intent intent1 = getIntent();
			intent1.setClass(PlayMusicActivity.this, PlayMusicService.class);
			intent1.putExtra("op_status", Tools.MUSIC_PLAY);
			startService(intent1);
			break;

		// 使用Start Service暂停音乐
		case R.id.btn_pause_service:
			Intent intent2 = getIntent();
			intent2.setClass(PlayMusicActivity.this, PlayMusicService.class);
			intent2.putExtra("op_status", Tools.MUSIC_PAUSE);
			startService(intent2);
			break;

		// 使用Start Service停止播放
		case R.id.btn_stop_service:
			Intent intent3 = getIntent();
			intent3.setClass(PlayMusicActivity.this, PlayMusicService.class);
			intent3.putExtra("op_status", Tools.MUSIC_STOP);
			startService(intent3);
			break;
		}
	}
}
