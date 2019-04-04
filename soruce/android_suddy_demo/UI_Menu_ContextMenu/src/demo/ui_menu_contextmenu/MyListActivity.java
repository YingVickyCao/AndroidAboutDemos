package demo.ui_menu_contextmenu;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyListActivity extends ListActivity {

	String[] cities = { "�ɶ�", "֣��", "����", "�Ϻ�", "ɽ��", "����", "����", "֣��", "����", "Ϋ��", "�ൺ", "̩��", "�޳�", "����" };

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView1 = (ListView) findViewById(android.R.id.list);

		// ����AdapterView OnItemSelectedListener
		listView1.setOnItemSelectedListener(listener);
		// ���� AdapterView OnItemSelectedListener
		listView1.setOnItemClickListener(clickListener);

		// ����AbsListView.MultiChoiceModeListener
		// multiChoiceModeListener�����ø��¼����ڳ���ListView Item������ContextMenu��
		listView1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView1.setMultiChoiceModeListener(multiChoiceModeListener);

		// ��������Դ
		// ����������
		// ��������Դ��������������
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
		// �������������������
		listView1.setAdapter(adapter);

		NotificationManager nm = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

	}

	final AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(MyListActivity.this, "ѡ���ˣ�" + parent.getSelectedItemId(), 2000).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	};

	final AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(MyListActivity.this, "ѡ����::��" + parent, 2000).show();
		}
	};

	final AbsListView.MultiChoiceModeListener multiChoiceModeListener = new AbsListView.MultiChoiceModeListener() {

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stubF
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// TODO Auto-generated method stub
			switch (item.getItemId()) {
			case R.id.action_settings:
				Toast.makeText(MyListActivity.this, "�����settins��ť", Toast.LENGTH_SHORT).show();
				return true;

			case R.id.action_open:
				Toast.makeText(MyListActivity.this, "����˴򿪰�ť", Toast.LENGTH_SHORT).show();
				return true;

			case R.id.action_close:
				Toast.makeText(MyListActivity.this, "����˹رհ�ť", Toast.LENGTH_SHORT).show();
				return true;

			default:
				return false;
			}
		}

		@Override
		public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
			// TODO Auto-generated method stub

		}
	};

}
