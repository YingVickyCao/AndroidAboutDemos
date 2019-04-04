package demo.thread_handler_downloadimage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.c21_2_handler_downloadimage.R;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * <pre>
 * ʹ�����߳�Thread����ͼƬ��ʹ��Handler���ͣ����̣߳��ͽ���ͼƬ�����̣߳���
 * </pre>
 * @author caoying
 *
 */
public class MainActivity extends ActionBarActivity {
	private ImageView mImgIv;

	private String IMG_PATH = "https://www.baidu.com/img/bd_logo1.png";
	private static int DOWNLOAD_COMPLETE = 200;

	private DownloadImgHandler mHandler;
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImgIv = (ImageView) findViewById(R.id.iv_img);

		mHandler = new DownloadImgHandler();

		mProgressDialog = new ProgressDialog(MainActivity.this);
		mProgressDialog.setTitle("��������");
		mProgressDialog.setMessage("��������ͼƬ�����Ժ�...");
	}

	/**
	 * <pre>
	 * ����˰�ťʹ��Handler����ͼƬ
	 * </pre>
	 * 
	 * @param view
	 */
	public void doDownloadImg(View view) {
		mProgressDialog.show();
		new Thread(new DownloadImgRunnable()).start();
	}

	private class DownloadImgRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// ����ͼƬ
			try {
				// mProgressDialog.show(); //
				// ERROR��д���������ɳ����������Ϊ��������߳��в��ܸ���UI����������߳�AsyncTask�п��Ը���UI��

				// ��������
				URL url = new URL(IMG_PATH);	// cheked MalformedURLException
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();	// checked IOException
				httpURLConnection.setDoInput(true);
				httpURLConnection.connect();

				// �����غ��ͼƬ��Դת��ΪBitMap��ʽ��
				InputStream inputStream = httpURLConnection.getInputStream();
				// mBitmap = BitmapFactory.decodeStream(inputStream);

				// ʹ��Handler�����߳��з������ݡ�
				Message message = Message.obtain();
				message.what = DOWNLOAD_COMPLETE;
				message.obj = BitmapFactory.decodeStream(inputStream);
				mHandler.sendMessage(message);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private class DownloadImgHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			// ʹ��Handler�����߳��н������ݡ�
			// �����Ѿ������
			if (DOWNLOAD_COMPLETE == msg.what) {
				mImgIv.setImageBitmap((Bitmap) msg.obj);
				mProgressDialog.dismiss();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
