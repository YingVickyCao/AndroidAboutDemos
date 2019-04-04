/**
 * Lesson4 Activity ת�� ����ֵ ���Լ��������ڷ�����ʾ��
 */
package sundy.android.demo.activityandintent;

import sundy.android.demo.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class StartNewActivity extends Activity
{
	private static final String	TAG	= "Activity01";
	private final int REQUEST_CODE_ACTIVITY = 1234  ;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_startnewactivity);
		Log.v(TAG, "onCreate");
		Button button1 = (Button)findViewById(R.id.buttonStartNewActivity);
		/* ����button���¼���Ϣ */
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				/* �½�һ��Intent���� */
				Intent intent = new Intent();
				/* ָ��intentҪ�������� */
				intent.setClass(StartNewActivity.this, NewActivity.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				StartNewActivity.this.finish();
			}
		});
		/******************************/
		Button button3 = (Button)findViewById(R.id.buttonCloseCurActivity);
		/* ����button���¼���Ϣ */
		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				/* �رյ�ǰ��Activity */
				StartNewActivity.this.finish();
			}
		});
		
		/***************����Dialog*****************/
		Button button4 = (Button)findViewById(R.id.buttonShowDialog)  ;
		button4.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(StartNewActivity.this) ;
				builder.setMessage("Are you sure you want to exit?")
			       .setCancelable(false)
			       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                
			           }
			       })
			       .setNegativeButton("No", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();
			           }
			       });
				builder.create().show()  ;
				
			}
			
		}) ;
	}

	public void onStart()
	{
		super.onStart();
		Log.v(TAG, "onStart");
	}
	
	public void onResume()
	{
		super.onResume();
		Log.v(TAG, "onResume");
	}
	
	public void onPause()
	{
		super.onPause();
		Log.v(TAG, "onPause");
	}
	
	public void onStop()
	{
		super.onStop();
		Log.v(TAG, "onStop");
	}

	public void onDestroy()
	{
		super.onDestroy();
		Log.v(TAG, "onDestroy");
	}

	public void onRestart()
	{
		super.onRestart();
		Log.v(TAG, "onReStart");
	}
	
}
