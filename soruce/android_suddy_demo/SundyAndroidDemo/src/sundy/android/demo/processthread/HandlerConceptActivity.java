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
 * ��Ӧ��˼ά��ͼ�С�Lesson2_Alfred:Android�Ķ��߳����첽���� - ������ͨ���������˽����⡷����
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
		 * lab1��Ӧ���¼�����
		 * ���Handlerʹ��ͬһ��Thread.
		 * ͬһ��Thread�п����ж��Handlerô��
		 * ͬһ��Thread��ʹ�ö��Handler��Message�����ô��Ϊʲô��
		 * </pre>
		 */
		findViewById(R.id.btn_Handler1).setOnClickListener(btn_Handler1_lsn);

		// Handler���Դ����������߳�����
		// Handler Lab2.1 , in new thread create handler send message .
		findViewById(R.id.btn_Handler2_1).setOnClickListener(btn_Handler2_1_lsn);

		// Handler���Դ����������߳�����
		// ���ָ��Handler���е��߳�?
		findViewById(R.id.btn_Handler2_2).setOnClickListener(btn_Handler2_2_lsn);

		// ���ָ��Handler���е��߳�
		// Handler Lab3.1 child thread get main thread's looper and send message
		findViewById(R.id.btn_Handler3_1).setOnClickListener(btn_Handler3_1_lsn);

		// Handler Lab3.2 using looper.prepare() and looper.loop() .
		findViewById(R.id.btn_Handler3_2).setOnClickListener(btn_Handler3_2_lsn);

		// ͬһ��Handler�в�ͬ����Ϣ��ͬʱִ��ô��
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

		// ��������ִ�е�Message������removeMessage����ʲô�����
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
	 * lab1��Ӧ���¼�����
	 * ���Handlerʹ��ͬһ��Thread.
	 * ͬһ��Thread�п����ж��Handlerô��
	 * ͬһ��Thread��ʹ�ö��Handler��Message�����ô��Ϊʲô��
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

					// ������
					long result = doCaculate();

					// Ҫ���͵�Message
					Message message1 = getMessage("btn_Handler1 result = " + result + ",send by mainHandler1");
					Message message2 = getMessage("btn_Handler1 result = " + result + ",send by mainHandler2");

					// ����Message
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
					// ������
					long result = doCaculate();
					// Ҫ���͵�Message
					Message message = getMessage("btn_Handler2_1 result = " + result);

					// �����С�
					// ������Threadֱ�Ӵ���Handler�������Threadû��Looper.prepare()��
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
			// ��ӡ�߳���Ϣ
			Toast.makeText(HandlerConceptActivity.this, Thread.currentThread().getName() + "----" + Thread.currentThread().getId(), Toast.LENGTH_SHORT).show();

			// ͨ��HandlerThread�̵߳õ�Looper��Ȼ��ͨ��Looper����Handler
			// ����һ����Ϊthreadone������Looper���߳�
			HandlerThread handlerThread = new HandlerThread("threadone");
			// �����߳�
			handlerThread.start();
			// �õ����е�Looper
			Looper looper = handlerThread.getLooper();

			// ָ�������߳�Looper
			final Handler myHandler = new Handler(looper) {
				// ������Ϣ
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);

					// ��ӡ�߳���Ϣ��������
					Toast.makeText(HandlerConceptActivity.this, Thread.currentThread().getName() + "----" + Thread.currentThread().getId() + "----" + msg.getData().getString(result + ""),
							Toast.LENGTH_SHORT).show();
				}
			};

			myHandler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// ������
					final long result = doCaculate();
					// ������Ϣ
					myHandler.sendMessage(getMessage("btn_Handler2_2 result=" + result));
				}
			});
		}
	};

	// ���ָ��Handler���е��߳�
	View.OnClickListener btn_Handler3_1_lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// ������
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
					// ������
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

	// ���ָ��Handler���е��߳�
	// ָ�����߳�Looper
	Handler handler = new Handler(Looper.getMainLooper()) {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Toast.makeText(HandlerConceptActivity.this, Thread.currentThread().getName() + "----" + Thread.currentThread().getId() + "----" + msg.getData().getString(result), 5000).show();
		}
	};

	// ���ָ��Handler���е��߳�
	// Ĭ��Ϊ���߳�Looper
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
	 * �õ�һ��Message��
	 * ȡ��Messageʵ��������Ҫ���ݵ����ݷŽ�Message����󷵻����Message��
	 * @param content Ҫ���ݵ�����
	 * @return		Ҫ���ص�Message
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

}
