/**
 * Analyse Handler of Android
 */
package sundy.android.demo.processthread;

import sundy.android.demo.R;
import sundy.android.demo.configration.CommonConstants;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * <pre>
 * 对应于思维导图中《Lesson2_Alfred:Android的多线程与异步任务 - 让我们通过代码来了解问题》内容
 * @author sundy
 * </pre>
 */
public class HandlerConceptActivity extends Activity {

	final String result = "sundy.android.demo.HandlerKey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_handlerconcept);

		/**
		 * <pre>
		 * lab1对应的事件处理
		 * 多个Handler使用同一个Thread.
		 * 同一个Thread中可以有多个Handler么？
		 * 同一个Thread中使用多个Handler的Message会混淆么？为什么？
		 * </pre>
		 */
		findViewById(R.id.btn_Handler1).setOnClickListener(btn_Handler1_lsn);

		// Handler可以创建到任意线程中吗？
		// Handler Lab2.1 , in new thread create handler send message .
		findViewById(R.id.btn_Handler2_1).setOnClickListener(btn_Handler2_1_lsn);

		// Handler可以创建到任意线程中吗？
		// 如何指定Handler运行的线程?
		findViewById(R.id.btn_Handler2_2).setOnClickListener(btn_Handler2_2_lsn);

		// 如何指定Handler运行的线程
		// Handler Lab3.1 child thread get main thread's looper and send message
		findViewById(R.id.btn_Handler3_1).setOnClickListener(btn_Handler3_1_lsn);

		// Handler Lab3.2 using looper.prepare() and looper.loop() .
		findViewById(R.id.btn_Handler3_2).setOnClickListener(btn_Handler3_2_lsn);

		// 同一个Handler中不同的消息会同时执行么？
		findViewById(R.id.btn_Handler4).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				HandlerThread lab4Thread = new HandlerThread("lab4_thread");
				lab4Thread.start();
				Handler lab4Handler = new DelayHandler(lab4Thread.getLooper());
				lab4Handler.sendMessage(getMessage("Lab4  A"));
				lab4Handler.sendMessage(getMessage("Lab4  B"));
			}
		});

		// 对于正在执行的Message，调用removeMessage会有什么后果？
		HandlerThread lab5Thread = new HandlerThread("lab5_thread");
		lab5Thread.start();

		final Handler lab5Handler = new DelayHandler(lab5Thread.getLooper());

		findViewById(R.id.btn_Handler5_1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Message message = getMessage("Lab5");
				message.what = 1;
				lab5Handler.sendMessage(message);
			}
		});

		findViewById(R.id.btn_Handler5_2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				lab5Handler.removeMessages(1);
			}
		});
	}

	/**
	 * <pre>
	 * lab1对应的事件处理
	 * 多个Handler使用同一个Thread.
	 * 同一个Thread中可以有多个Handler么？
	 * 同一个Thread中使用多个Handler的Message会混淆么？为什么？
	 * </pre>
	 */
	View.OnClickListener btn_Handler1_lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					// 计算结果
					long result = doCaculate();

					// 要发送的Message
					Message message1 = getMessage("btn_Handler1 result = " + result + ",send by mainHandler1");
					Message message2 = getMessage("btn_Handler1 result = " + result + ",send by mainHandler2");

					// 发送Message
					mainHandler1.sendMessage(message1);
					mainHandler2.sendMessage(message2);
				}

			}).start();
		}
	};

	View.OnClickListener btn_Handler2_1_lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// 计算结果
					long result = doCaculate();
					// 要发送的Message
					Message message = getMessage("btn_Handler2_1 result = " + result);

					// 不可行。
					// 不能在Thread直接创建Handler，而这个Thread没有Looper.prepare()。
					Handler handler = new Handler();
					handler.sendMessage(message);
				}

			}).start();
		}
	};

	View.OnClickListener btn_Handler2_2_lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 打印线程信息
			Toast.makeText(HandlerConceptActivity.this, Thread.currentThread().getName() + "----" + Thread.currentThread().getId(), Toast.LENGTH_SHORT).show();

			// 通过HandlerThread线程得到Looper，然后通过Looper关联Handler
			// 创建一个名为threadone、含有Looper的线程
			HandlerThread handlerThread = new HandlerThread("threadone");
			// 启动线程
			handlerThread.start();
			// 得到含有的Looper
			Looper looper = handlerThread.getLooper();

			// 指定该子线程Looper
			final Handler myHandler = new Handler(looper) {
				// 处理消息
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);

					// 打印线程信息、计算结果
					Toast.makeText(HandlerConceptActivity.this, Thread.currentThread().getName() + "----" + Thread.currentThread().getId() + "----" + msg.getData().getString(result + ""),
							Toast.LENGTH_SHORT).show();
				}
			};

			myHandler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// 计算结果
					final long result = doCaculate();
					// 发送消息
					myHandler.sendMessage(getMessage("btn_Handler2_2 result=" + result));
				}
			});
		}
	};

	// 如何指定Handler运行的线程
	View.OnClickListener btn_Handler3_1_lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// 计算结果
					long result = doCaculate();
					handler.sendMessage(getMessage("btn_Handler3_1 result = " + result));
				}

			}).start();

		}
	};

	View.OnClickListener btn_Handler3_2_lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// 计算结果
					long result = doCaculate();
					Looper.prepare();
					handler2.sendMessage(getMessage("btn_Handler3_2 result = " + result));
					Looper.loop();
				}

			}).start();
		}
	};

	// init handler in main thread .
	Handler mainHandler1 = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Toast.makeText(HandlerConceptActivity.this, Thread.currentThread().getName() + "----" + Thread.currentThread().getId() + "----mainHandler1 received " + msg.getData().getString(result),
					5000).show();
		}

	};

	// init handler in main thread .
	Handler mainHandler2 = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Toast.makeText(HandlerConceptActivity.this, Thread.currentThread().getName() + "----" + Thread.currentThread().getId() + "----mainHandler2 received " + msg.getData().getString(result),
					5000).show();
		}
	};

	// 如何指定Handler运行的线程
	// 指定主线程Looper
	Handler handler = new Handler(Looper.getMainLooper()) {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Toast.makeText(HandlerConceptActivity.this, Thread.currentThread().getName() + "----" + Thread.currentThread().getId() + "----" + msg.getData().getString(result), 5000).show();
		}
	};

	// 如何指定Handler运行的线程
	// 默认为主线程Looper
	Handler handler2 = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Toast.makeText(HandlerConceptActivity.this, Thread.currentThread().getName() + "----" + Thread.currentThread().getId() + "----" + msg.getData().getString(result), 5000).show();
		}
	};

	class DelayHandler extends Handler {
		public DelayHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Log.i(CommonConstants.LOGCAT_TAG_NAME, "Start message: " + msg.getData().getString(result));
			try {
				Thread.sleep(5 * 1000);
			} catch (Exception e) {
			}
			Log.i(CommonConstants.LOGCAT_TAG_NAME, "Finish message: " + msg.getData().getString(result));
		}
	}

	/**
	 * <pre>
	 * Define a common Message , I will invoke it in different labs .
	 * 得到一个Message。
	 * 取得Message实例，并把要传递的内容放进Message，最后返回这个Message。
	 * @param content 要传递的内容
	 * @return		要返回的Message
	 * </pre>
	 */
	Message getMessage(String content) {
		Bundle data = new Bundle();
		data.putString(result, content);

		Message returnMsg = new Message();
		returnMsg.setData(data);

		return returnMsg;
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

}
