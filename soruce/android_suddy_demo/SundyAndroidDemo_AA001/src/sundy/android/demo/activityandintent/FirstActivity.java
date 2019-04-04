package sundy.android.demo.activityandintent;

import sundy.android.demo.R;
import sundy.android.demo.configration.CommonConstants;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity {
    /** Called when the activity is first created. */
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(CommonConstants.LOGCAT_TAG_NAME, "onCreate")  ;
        if(savedInstanceState != null)
        {
        	Log.i("restore state in onCreate:",savedInstanceState.getString("sundydemo"))  ;
        }
        
        setContentView(R.layout.layout_activity1);
        Log.v("sundylog", "print verbose")  ;
        Log.d("sundylog", "print debug")  ;
        Log.i("sundylog", "print Info")  ;
        Log.w("sundylog", "print Warn")  ;
        Log.e("sundylog", "print Error")  ;
        try {
			throw new Exception("dfd")  ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("sundylog", e.getMessage())  ;
		}
		
		
		
		//����SecondActivity��ť
		Button button23 = (Button)findViewById(R.id.button23)  ;
		button23.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(FirstActivity.this,SecondActivity.class) ;
				_intent.putExtra("sundyintent", "��ã��ܸ�����ʶ��")  ;
				_intent.putExtra("sundyintent1", "��ã��ܸ�����ʶ��1")  ;
				//MainActivity.this.startActivity(_intent)  ;
				FirstActivity.this.startActivityForResult(_intent, 1234)  ;
			}
			
		})  ;
		
		//���Ű�ť
		Button buttonDial = (Button)findViewById(R.id.buttonDial)  ;
		buttonDial.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent() ;
				_intent.setAction(Intent.ACTION_DIAL)  ;
				_intent.setData(Uri.parse("tel:15608071871"))  ;
				startActivity(_intent)  ;
			}
			
		})  ;
        
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onDestroy")  ;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onPause")  ;
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onRestart")  ;
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null)
        {
        	Log.i("restore state:",savedInstanceState.getString("sundydemo"))  ;
        }
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putString("sundydemo", "�ҵ�����,����ʡ��һ����...")  ;
		Log.i("save state:","�ҵ�����,����ʡ��һ����...")  ;
		//super.onSaveInstanceState(outState);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onResume")  ;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onStart")  ;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onStop")  ;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1234)
		{
			String returnValue = data.getStringExtra("sundyintentreturn") ;
			Toast.makeText(this, "����ֵ��"+returnValue, 4000).show()  ;
		}
		
	}
}