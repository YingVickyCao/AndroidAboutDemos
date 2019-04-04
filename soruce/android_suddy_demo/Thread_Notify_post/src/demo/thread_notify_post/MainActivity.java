package demo.thread_notify_post;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <pre>
 *  Demo����:
 * �����߳��и���UI�ķ�ʽһ
 * 
 * �����߳����������̵߳�Handler��post()����
 * ����UI��������߳���sendMessage()ԭ�����ʽ��������.
 * 
 * ��ϸ����:
 * ��Handler������post()����ʱ,�����ĵ���˳������:
 * Handler��post()����--->Handler��sendMessageDelayed()����---> Handler��sendMessageAtTime()���� , --->�ֻص���ǰ����Ϥ��sendMessageAtTime()
 * 
 * ��ϸ�Ĵ�������:
 * public final boolean post(Runnable r){
 *    return  sendMessageDelayed(getPostMessage(r), 0);
 * }
 * 
 * 
 * private final Message getPostMessage(Runnable r) {
 *    Message m = Message.obtain();
 *    m.callback = r;
 *    return m;
 * }
 * 
 * 
 * public final boolean sendMessageDelayed(Message msg, long delayMillis){
 *   if (delayMillis < 0) {
 *       delayMillis = 0;
 *      }
 *   return sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayMillis);
 * }
 * 
 * 
 * �������ϸ�Ĺ����п��Կ�����post(Runnable r)�����ø÷������������r������getPostMessage(r)
 * ��r��װ����һ��Message.
 * �ص���getPostMessage(r)�����е� m.callback = r
 * ��r��ֵ����Message��callback!!!!!
 * 
 * �ع�һ�³���ʱ���õ�dispatchMessage()����
 * ������ſ�Handler��dispatchMessage(Message msg)����:
 *  public void dispatchMessage(Message msg) {
 *       //1 message��callback
 *    if (msg.callback != null) {
 *       handleCallback(msg);
 *    } else {
 *       //2 handler��callback
 *       if (mCallback != null) {
 *           if (mCallback.handleMessage(msg)) {
 *               return;
 *          }
 *      }
 *      //3 Handler��handleMessage()
 *      handleMessage(msg);
 *    }
 *  }
 * 
 * 
 * ��1�����жϳ���Message��callback��Ϊnull,���ǵ��� handleCallback(msg);
 * ��ϸ��������:
 * private static void handleCallback(Message message) {
 *   message.callback.run();
 * }
 * �÷����Ƚϼ�:����ֱ�ӵ���post(Runnable r)���������(Runnable����)��run()����.
 * 
 * С��:
 * 1 �����߳�������post(Runnable r)����UI,ԭ���sendMessage()����.
 * 2 ע��:
 *   �������ʾ����mHandler�����̵߳�Handler.
 *   ���ԡ������߳�������post(Runnable r)����UI�����˵�������ر�׼ȷ.
 *   ȷ�е�˵���������߳��з�������Ϣ�����̵߳���Ϣ���дӶ�������UI.
 * 3 ����post(Runnable r)���Ὺ��һ���µ��߳�,UI�ĸ����������߳�����ɵģ�������������������
 *   �������ʾ���п��������ط�����߳�ID��������ֵ��һ����.
 *   ������post������������ʱ����
 * 
 * 
 * �ο�����:
 * http://blog.csdn.net/guolin_blog/article/details/9991569
 * Thank you very much
 * </pre>
 */

public class MainActivity extends Activity {
	private TextView mTextView;
	private Handler mHandler;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		mHandler = new Handler();
		mTextView = (TextView) findViewById(R.id.textView);
		mButton = (Button) findViewById(R.id.button);
		mButton.setOnClickListener(new OnClickListenerImpl());
		System.out.println("UI�߳�ID=" + Thread.currentThread().getId());
	}

	/**
	 * <pre>
	 * Caculate the process .
	 *  ִ�м��㡣
	 *  ����һ����ʱ����
	 * @return
	 * </pre>
	 */
	private long doCaculate() {
		long returnResult = 0;

		long j = 98, i = 99, k = 100;

		for (int m = 0; m < 10000; m++) {
			returnResult = j * i * k * m;
		}
		return returnResult;
	}

	private class OnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			new Thread() {
				public void run() {

					final long result = doCaculate();

					System.out.println("run --" + Thread.currentThread().getName() + "--" + Thread.currentThread().getId());

					// Causes the Runnable r to be added to the message queue.
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							System.out.println("run --> post --> run" + Thread.currentThread().getName() + "--" + Thread.currentThread().getId());
							mTextView.setText("result = " + result);
						}
					});

				};
			}.start();

		}
	}

}