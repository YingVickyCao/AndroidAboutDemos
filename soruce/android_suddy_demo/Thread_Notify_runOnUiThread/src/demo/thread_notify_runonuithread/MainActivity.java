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
 * Demo描述:
 *  在子线程中更改UI的方式三
 *  
 *  调用Activity.runOnUiThread(Runnable runnable)方法.
 *  
 * 该方法的源码如下:
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
 *  源码中的这段注释太详细和贴心了,赞一个!
 *  在UI线程中执行该Runnable.
 *  如果当前线程是UI线程,那么该Runnable会立即执行.
 *  如果当前的线程不是UI线程则调用UI线程handler的post()方法将其放入UI线程的消息队列中.
 *  注意:勿在runOnUiThread(Runnable runnable)中做耗时操作
 *  
 *  参考资料:
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
	 *  执行计算。
	 *  这是一个耗时操作
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

					// 执行计算
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