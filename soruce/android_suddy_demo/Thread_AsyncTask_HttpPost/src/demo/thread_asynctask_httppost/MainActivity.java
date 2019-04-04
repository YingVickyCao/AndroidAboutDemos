package demo.thread_asynctask_httppost;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.c4_1_asynctask_httppost.R;
import com.google.gson.JsonObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class Data {
		String loginid;

		public String getLoginid() {
			return loginid;
		}

		public void setLoginid(String loginid) {
			this.loginid = loginid;
		}

		String pwd;

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
	}

	// 浣跨敤鏅�?��post鏂瑰紡璇锋眰鏈嶅姟鍣�?	// http://blog.csdn.net/dadoneo/article/details/6233366
	public void sendDefault(View view) {
		String url = "http://10.19.105.105/area_market/trunk/index.php?s=/Api/terminal/index";
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = new JSONObject();
		try {
			Data data = new Data();
			data.setLoginid("40006");
			data.setPwd("123456");

			jsonObject.put("partnerid", "yapm");
			jsonObject.put("version", "1.0");
			jsonObject.put("validation", "txt");
			jsonObject.put("sn", "67221430043319");
			jsonObject.put("data", data);
			System.out.println("-->" + jsonObject.toString());
			try {
				HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
				String retSrc = EntityUtils.toString(httpResponse.getEntity());
				System.out.println("-->" + retSrc.toString());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 浣跨敤async post鏂瑰紡璇锋眰鏈嶅姟鍣�?	public void sendAsyncPostF(View view) {

	}
}
