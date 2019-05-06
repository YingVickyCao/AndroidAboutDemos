package com.hades.example.android.app_component.cp.dict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hades.example.android.R;

import java.util.List;
import java.util.Map;

import static com.hades.example.android.lib.mock.DummyContentFragment.KEY_SEARCH_RESULT;

public class DictSearchResultActivity extends Activity {
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_dict_search_result_popup);

        listView = findViewById(R.id.tableContentList);
        showDictSearchResult();
    }

    private void showDictSearchResult() {
        List<Map<String, String>> list = getDictSearchResultData();
        SimpleAdapter adapter = new SimpleAdapter(DictSearchResultActivity.this, list, R.layout.cp_dict_search_result_popup_item_view, new String[]{"word", "detail"}, new int[]{R.id.word, R.id.detail});
        listView.setAdapter(adapter);
    }

    private List<Map<String, String>> getDictSearchResultData() {
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        @SuppressWarnings("unchecked")
        List<Map<String, String>> list = (List<Map<String, String>>) data.getSerializable(KEY_SEARCH_RESULT);
        return list;
    }
}
