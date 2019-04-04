package demo.service_sundydemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

// TODO ����һ���������Ĳ��ţ���Ϊ�������̲߳������ֵġ����ڻ���ר���о�
/**
 * <pre>
 * ʹ��Start Service�������֡�
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
		// ������������������stop���ͷ���Դ�ˣ��������²���
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
			// TODO ����
			// �ͷ���Դ
			mediaPlayer.release();
			// ȡ����Դ
			mediaPlayer = null;
			break;

		}
	}
}
