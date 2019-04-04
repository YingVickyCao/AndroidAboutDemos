package demo.thead_handler_downloadimgtools_callback;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * <pre>
 * ��װ���ع�����
 * </pre>
 * 
 * @author caoying
 * 
 */
public class DownloadTools {
	private ProgressDialog mProgressDialog;

	public DownloadTools(Context context) {
		// TODO Auto-generated constructor stub
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setTitle("��������");
		mProgressDialog.setMessage("���������У����Ժ�...");
	}

	public void download(final String path, final DownloadCallback callback) {

		// ��������
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 100) {
					callback.getContent((byte[]) msg.obj);
					mProgressDialog.dismiss();
				}
			};
		};

		// ִ�к�ʱ����������ͼƬ
		class MyThreadRunnable implements Runnable {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(path);

				try {
					HttpResponse httpResponse = httpClient.execute(httpPost);

					if (200 == httpResponse.getStatusLine().getStatusCode()) {
						byte[] result = EntityUtils.toByteArray(httpResponse.getEntity());

						Message message = Message.obtain();
						message.obj = result;
						message.what = 100;
						handler.sendMessage(message);
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		new Thread(new MyThreadRunnable()).start();
		mProgressDialog.show();
	}

	// ʹ�ýӿڻص��õ����ؽ����
	public interface DownloadCallback {
		public void getContent(byte[] result);
	}

}
