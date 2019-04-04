package demo.service_sundydemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

// TODO 这是一个不完美的播放，因为是在主线程播放音乐的。后期会做专题研究
/**
 * <pre>
 * 使用Start Service播放音乐。
 * 
 * </pre>
 */
public class PlayMusicService extends Service {
	private static String FILE_NAME;
	private static String TAG = "SunnyDemo";

	private MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		FILE_NAME = getClass().getSimpleName() + "----";
		Log.i(TAG, FILE_NAME + "onCreate");

		mediaPlayer = MediaPlayer.create(PlayMusicService.this, R.raw.maria);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		Log.i(TAG, FILE_NAME + "onStartCommand");

		final int op_status = intent.getIntExtra("op_status", Tools.MUSIC_PLAY);
		playMusic(op_status);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		Log.i(TAG, FILE_NAME + "onDestroy");
	}

	public void playMusic(int op_status) {
		switch (op_status) {
		// 如果不加上这个，调用stop就释放资源了，不能重新播放
		case Tools.MUSIC_PLAY:
			if (null == mediaPlayer) {
				mediaPlayer = MediaPlayer.create(PlayMusicService.this, R.raw.maria);
			}
			mediaPlayer.start();
			break;

		case Tools.MUSIC_PAUSE:
			mediaPlayer.pause();
			break;

		case Tools.MUSIC_STOP:
			mediaPlayer.stop();
			// TODO 性能
			// 释放资源
			mediaPlayer.release();
			// 取消资源
			mediaPlayer = null;
			break;

		}
	}
}
