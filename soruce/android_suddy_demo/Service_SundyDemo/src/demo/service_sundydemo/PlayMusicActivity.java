package demo.service_sundydemo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * <pre>
 * ʹ��Activity����music��
 * ʹ��Start Service����music��
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

		// ����raw�ļ����µ�������Դ
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

	// ʹ��Activity����music
	public void doClick(View view) {
		Log.i(TAG, FILE_NAME + "doClick");
		switch (view.getId()) {
		// ʹ��Activityֱ�Ӳ�������
		case R.id.btn_start_norm:
			// ������������������stop���ͷ���Դ�ˣ��������²���
			if (null == mediaPlayer) {
				mediaPlayer = MediaPlayer.create(PlayMusicActivity.this, R.raw.maria);
			}
			mediaPlayer.start();
			break;

		// ʹ��Activity��ͣ����
		case R.id.btn_pause_norm:
			mediaPlayer.pause();
			break;

		// ʹ��Activityֹͣ����
		case R.id.btn_stop_norm:
			mediaPlayer.stop();
			// TODO ����
			// �ͷ���Դ
			mediaPlayer.release();
			// ȡ����Դ
			mediaPlayer = null;
			break;
		}
	}

	// ʹ��Start Service����music
	public void doClick_Service(View view) {
		Log.i(TAG, FILE_NAME + "doClick_Service");
		switch (view.getId()) {

		// ʹ��Start Service��������
		case R.id.btn_start_service:
			// TODO ����
			Intent intent1 = getIntent();
			intent1.setClass(PlayMusicActivity.this, PlayMusicService.class);
			intent1.putExtra("op_status", Tools.MUSIC_PLAY);
			startService(intent1);
			break;

		// ʹ��Start Service��ͣ����
		case R.id.btn_pause_service:
			Intent intent2 = getIntent();
			intent2.setClass(PlayMusicActivity.this, PlayMusicService.class);
			intent2.putExtra("op_status", Tools.MUSIC_PAUSE);
			startService(intent2);
			break;

		// ʹ��Start Serviceֹͣ����
		case R.id.btn_stop_service:
			Intent intent3 = getIntent();
			intent3.setClass(PlayMusicActivity.this, PlayMusicService.class);
			intent3.putExtra("op_status", Tools.MUSIC_STOP);
			startService(intent3);
			break;
		}
	}
}
