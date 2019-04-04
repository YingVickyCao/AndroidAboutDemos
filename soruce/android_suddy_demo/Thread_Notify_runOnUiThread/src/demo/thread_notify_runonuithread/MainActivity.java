package demo.thread_notify_runonuithread;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * <pre>
 * http://www.it165.net/pro/html/201410/23774.html
 * 
 * Demo����:
 *  �����߳��и���UI�ķ�ʽ��
 *  
 *  ����Activity.runOnUiThread(Runnable runnable)����.
 *  
 * �÷�����Դ������:
 * 
 *  Runs the specified action on the UI thread. If the current thread is the UI
 *  thread, then the action is executed immediately. If the current thread is
 *  not the UI thread, the action is posted to the event queue of the UI thread.
 * 
 *  @param action the action to run on the UI thread
 *  public final void runOnUiThread(Runnable action) {
 *     if (Thread.currentThread() != mUiThread) {
 *         mHandler.post(action);
 *     } else {
 *         action.run();
 *       }
 *  }
 *  
 *  
 *  Դ���е����ע��̫��ϸ��������,��һ��!
 *  ��UI�߳���ִ�и�Runnable.
 *  �����ǰ�߳���UI�߳�,��ô��Runnable������ִ��.
 *  �����ǰ���̲߳���UI�߳������UI�߳�handler��post()�����������UI�̵߳���Ϣ������.
 *  ע��:����runOnUiThread(Runnable runnable)������ʱ����
 *  
 *  �ο�����:
 *  http://blog.csdn.net/guolin_blog/article/details/9991569
 *  Thank you very much
 * </pre>
 */
public class MainActivity extends Activity {
	private TextView mTextView;
	private Activity mActivity;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
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

	private void init() {
		mActivity = this;
		mTextView = (TextView) findViewById(R.id.textView);
		mButton = (Button) findViewById(R.id.button);
		mButton.setOnClickListener(new OnClickListenerImpl());
	}

	private class OnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			new Thread() {
				public void run() {

					// ִ�м���
					final long result = doCaculate();

					mActivity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mTextView.setText("result = " + result);
						}
					});
				};
			}.start();
		}
	}

}