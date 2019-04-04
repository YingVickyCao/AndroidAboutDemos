package demo.one_eight_intentservice_downimg;

import android.app.IntentService;
import android.content.Intent;

public class DownloadService extends IntentService {

	public DownloadService() {
		super("HelloIntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub

		// Ä£ÄâºÄÊ±²Ù×÷
		long endTime = System.currentTimeMillis() + 5 * 1000;
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
				}
			}
		}

	}

}
