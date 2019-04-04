package sundy.android.demo.sensor;

import java.io.IOException;

import sundy.android.demo.R;

import android.R.bool;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Administrator
 *
 */
public class SensorPlayerActivity extends Activity implements SensorEventListener {
    /** Called when the activity is first created. */
	private SensorManager sensorManager ;
	private Sensor sensor ;
	private static MediaPlayer mediaPlayer  ;
	private Boolean isPlaying  = false ;
	private Boolean turnPositive = true ;
	
	private float x, y, z, last_x, last_y, last_z;
	 private long lastUpdate;
	 // record times of useful shakes for test
	 private int mTimes;
	 // ԽСԽ����
	 private static final int SHAKE_THRESHOLD = 3000;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sensorpalyer);
        
        sensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE)  ;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)  ;
        
        mediaPlayer = MediaPlayer.create(this, R.raw.maria) ;
        
        Button button1 =(Button)findViewById(R.id.button1) ;
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!mediaPlayer.isPlaying())
			      {		    	 
			    	    Log.i("sundy", "start") ;
						mediaPlayer.start() ;  	  
			      }
			      else
			      {
			    	  Log.i("sundy", "stop") ;
			    	  mediaPlayer.pause() ;
			    	  
			      }
			}
		}) ;
    
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sensorManager.unregisterListener(this) ;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)  ;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		doOnSensorChangedJob(event.sensor.getType(), event.values) ;
		
	}
	
	
	/**
	 * �ж��ֻ�������
	 * @param x 
	 * @param y
	 * @param z
	 * @return ������ֻ�������÷���1 �� ������÷���0 ����������-1
	 */
	private int turnPositive(float x, float y, float z) 
	{
		if(Math.abs(x)<1 && Math.abs(y)<1)
		{
			if(z>9 && z<10)
				return 1 ;
			else if(z>-12 && z<-9)
				return 0 ;
			else 
				return -1 ;
		}else {
			return -1  ;
		}
		
	}
	
	
	/**
	 * �����Ӧ���ı����
	 * @param sensor ������
	 * @param values ��������ֵ
	 */
	private void doOnSensorChangedJob(int sensor, float[] values) 
	{
	  if (sensor == Sensor.TYPE_ACCELEROMETER) 
	  {
		   long curTime = System.currentTimeMillis();
		   // ��100���� �ٶ�
		   if ((curTime - lastUpdate) > 100) {
		    long diffTime = (curTime - lastUpdate);
		    x = values[SensorManager.DATA_X];
		    y = values[SensorManager.DATA_Y];
		    z = values[SensorManager.DATA_Z];
		    float speed = Math.abs(x + y - last_x - last_y - last_z)
		      / diffTime * 10000;
		    // Log.i("Throw", "Throw at speed " + speed);
		    if (speed > SHAKE_THRESHOLD) {
		     // ��⵽ҡ�κ�ִ�еĴ���
		      Toast.makeText(this, "��⵽˦��:x="+x+"y="+y+"z="+z, 3000).show() ;
		      if(!mediaPlayer.isPlaying())
		      {		    	 
		    	    Log.i("sundy", "start") ;
					mediaPlayer.start() ;  	  
		      }
		      else
		      {
		    	  Log.i("sundy", "pause") ;
		    	  mediaPlayer.pause();
		    	  
		      }
		    }
		    
		    //����ֻ���ת 
		    int _returnIndex = turnPositive(x, y, z) ;
		    if(_returnIndex ==1)
		    {
		    	Log.i("sundy", "����"+_returnIndex) ;
		    	if(!mediaPlayer.isPlaying())
		    	{
		    		mediaPlayer.start() ;
		    	}		    	
		    	turnPositive = true ;
		    }else if(_returnIndex ==0)
		    {
		    	Log.i("sundy", "����"+_returnIndex) ;
		    	if(mediaPlayer.isPlaying())
		    	{
		    		mediaPlayer.pause() ;
		    	}		    	
		    	turnPositive = false ;
		    }
		    
		    last_x = x;
		    last_y = y;
		    last_z = z;
		    lastUpdate = curTime;
		   }
		}
	}
	
	
	
	
}