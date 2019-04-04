package demo.ui_listview_divpage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * <pre>
 * ListView之翻页功能。
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends Activity {
	private static int num = 0;
	private final static String TAG = "CY";
	private ListView mContentLv;
	private ProgressDialog mProgressDialog;
	// 存放总页数
	private int mTotalPage = 1;
	// 存放获取的所有数据
	private List<String> mListData;
	// 关联ListView的适配器
	private MyAdapter mMyAdapter;
	// 是否需要翻页
	private boolean mNeedFDivPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mProgressDialog = new ProgressDialog(MainActivity.this);
		mProgressDialog.setMessage("正在下载");
		// 设置点击ProgressDialog Dialog消失
		mProgressDialog.setCancelable(false);
		// 设置点击ProgressDialog之外的地方Dialog消失
		mProgressDialog.setCanceledOnTouchOutside(false);

		mContentLv = (ListView) findViewById(R.id.lv_content);
		mMyAdapter = new MyAdapter();
		mListData = new ArrayList<String>();

		new MyAsyncTask().execute(mTotalPage);
		mContentLv.setOnScrollListener(scrollLsn);
	}

	private AbsListView.OnScrollListener scrollLsn = new AbsListView.OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub
			// 当需要翻页且状态为停止时，说明可以重新从网络获取数据了。
			if (mNeedFDivPage && (AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState)) {
				new MyAsyncTask().execute(mTotalPage + 1);
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
			Log.d(TAG, "In scrollLsn onScroll func,firstVisibleItem=" + firstVisibleItem + ",visibleItemCount=" + visibleItemCount + ",totalItemCount=" + totalItemCount);
			// 当的第一个可见元素 与可见元素个数和等于总数时，说明这一行已经是最后一个数据。此时，需要翻页了。
			mNeedFDivPage = (firstVisibleItem + visibleItemCount == totalItemCount) ? true : false;
		}
	};

	class MyAdapter extends BaseAdapter {
		List<String> list;

		public void setData(List<String> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView textView = null;
			if (null == convertView) {
				textView = new TextView(MainActivity.this);
			} else {
				textView = (TextView) convertView;
			}
			textView.setHeight(155);
			textView.setText(getItem(position).toString());
			return textView;
		}
	}

	// 第一个参数是请求页数
	class MyAsyncTask extends AsyncTask<Integer, Void, List<String>> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Log.i(TAG, "In MyAsyncTask,onPreExecute called");
			mProgressDialog.show();
		}

		@Override
		protected List<String> doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			Log.i(TAG, "In MyAsyncTask,doInBackground called");
			// TODO 模拟网络数据交互
			return getData(params[0]);
		}

		@Override
		protected void onPostExecute(List<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			Log.i(TAG, "In MyAsyncTask,onPostExecute called");

			mListData.addAll(result);
			mMyAdapter.setData(mListData);

			// 仅当第一次需要设置适配器，其他时notifyDataSetChanged。
			if (mTotalPage == 1) {
				mContentLv.setAdapter(mMyAdapter);
			}

			// 数据变动后，通知View刷新
			mMyAdapter.notifyDataSetChanged();
			mTotalPage++;

			mProgressDialog.dismiss();
		}
	}

	private List<String> getData(int requestPage) {
		List<String> list = new ArrayList<String>();
		if (list.isEmpty()) {
			list.clear();
			Log.d(TAG, "In doInBackground，list is null");
		}

		for (int i = 0; i < 5; i++) {
			num++;
			list.add("行" + num);
		}

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
