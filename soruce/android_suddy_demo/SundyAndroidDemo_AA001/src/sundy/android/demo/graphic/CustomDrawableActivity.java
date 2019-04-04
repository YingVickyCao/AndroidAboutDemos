package sundy.android.demo.graphic;

import android.app.Activity;
import android.os.Bundle;

public class CustomDrawableActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState) ;
		
		CustomDrawableView newView = new CustomDrawableView(this) ;
		setContentView(newView) ;
	}

}
