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
 *  Demo描述:
 * 在子线程中更改UI的方式一
 * 
 * 在子线程中利用主线程的Handler的post()方法
 * 更改UI这个在子线程中sendMessage()原理和形式都很类似.
 * 
 * 详细分析:
 * 在Handler调用其post()方法时,方法的调用顺序如下:
 * Handler的post()方法--->Handler的sendMessageDelayed()方法---> Handler的sendMessageAtTime()方法 , --->又回到了前面熟悉的sendMessageAtTime()
 * 
 * 详细的代码如下:
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
 * 在这个详细的过程中可以看到在post(Runnable r)中利用该方法的输入参数r调用了getPostMessage(r)
 * 将r封装成了一个Message.
 * 重点是getPostMessage(r)方法中的 m.callback = r
 * 将r赋值给了Message的callback!!!!!
 * 
 * 回顾一下出队时调用的dispatchMessage()方法
 * 下面接着看Handler的dispatchMessage(Message msg)方法:
 *  public void dispatchMessage(Message msg) {
 *       //1 message的callback
 *    if (msg.callback != null) {
 *       handleCallback(msg);
 *    } else {
 *       //2 handler的callback
 *       if (mCallback != null) {
 *           if (mCallback.handleMessage(msg)) {
 *               return;
 *          }
 *      }
 *      //3 Handler的handleMessage()
 *      handleMessage(msg);
 *    }
 *  }
 * 
 * 
 * 在1处就判断出该Message的callback不为null,于是调用 handleCallback(msg);
 * 详细代码如下:
 * private static void handleCallback(Message message) {
 *   message.callback.run();
 * }
 * 该方法比较简单:就是直接调用post(Runnable r)的输入参数(Runnable对象)的run()方法.
 * 
 * 小结:
 * 1 在子线程中利用post(Runnable r)更新UI,原理和sendMessage()类似.
 * 2 注意:
 *   在下面的示例中mHandler是主线程的Handler.
 *   所以“在子线程中利用post(Runnable r)更新UI”这个说法不是特别准确.
 *   确切地说还是在子线程中发送了消息到主线程的消息队列从而更新了UI.
 * 3 调用post(Runnable r)不会开启一个新的线程,UI的更新是在主线程中完成的！！！！！！！！！！
 *   在下面的示例中可在两个地方输出线程ID发现两个值是一样的.
 *   所以在post方法中勿做耗时操作
 * 
 * 
 * 参考资料:
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
		System.out.println("UI线程ID=" + Thread.currentThread().getId());
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