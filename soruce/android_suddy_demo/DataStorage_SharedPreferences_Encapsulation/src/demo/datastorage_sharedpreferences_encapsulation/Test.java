package demo.datastorage_sharedpreferences_encapsulation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {
	public void test1() {
		HashSet<String> set = new HashSet<String>();
		set.add("hello");
		set.add("world");
		String fileName = "login";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bool_type", true);
		map.put("float_type", 10.5455);
		map.put("int_type", 100);
		map.put("long_type", 13463464L);
		map.put("string_type", "cy");
		map.put("set_type", set);
		SFTools tools = new SFTools(getContext());
		tools.saveRFData(fileName, map);
		// write-->{string_type=cy, set_type=[hello, world], bool_type=true, long_type=13463464, int_type=100, float_type=10.5455}
		System.out.println("write-->" + map.toString());

		// read-->{string_type=cy, bool_type=true, set_type=[hello, world], long_type=13463464, int_type=100}
		Map<String, ?> map2 = tools.getRFData(fileName);
		System.out.println("read-->" + map2.toString());
	}
}
