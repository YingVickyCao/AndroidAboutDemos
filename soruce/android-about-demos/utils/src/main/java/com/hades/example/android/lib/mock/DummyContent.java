package com.hades.example.android.lib.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS_1 = new ArrayList<>();
    public static final List<DummyItem> ITEMS_4 = new ArrayList<>();
    public static final List<DummyItem> ITEMS_3 = new ArrayList<>();
    public static final List<DummyItem> ITEMS_1000 = new ArrayList<>();
    public static final List<DummyItem> ITEMS_10000 = new ArrayList<>();
    public static final List<DummyItem> ITEMS_100000 = new ArrayList<>();
    public static final ArrayList<String> ITEMS_STRING_3 = new ArrayList<>();

    /**
     * A com.hades.example.android.map of sample (dummy) items, by ID.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<>();
    public static final Map<Integer, DummyItem> ITEM_MAP = new HashMap<Integer, DummyItem>();
    public static final int COUNT = 50;

    public static Map<Integer, DummyItem> ITEM_MAP() {
        if (ITEM_MAP.isEmpty()) {
            for (int i = 1; i <= COUNT; i++) {
                DummyItem item = createDummyItem(i);
                ITEM_MAP.put(item.id, item);
            }
        }
        return ITEM_MAP;
    }

    public static List<DummyItem> ITEMS() {
        if (ITEMS.isEmpty()) {
            for (int i = 1; i <= COUNT; i++) {
                ITEMS.add(createDummyItem(i));
            }
        }
        return ITEMS;
    }

    public static List<DummyItem> ITEMS_1() {
        if (ITEMS_1.isEmpty()) {
            ITEMS_1.addAll(createList_3());
        }
        return ITEMS_1;
    }

    public static List<DummyItem> ITEMS_3() {
        if (ITEMS_3.isEmpty()) {
            ITEMS_3.addAll(createList_3());
        }
        return ITEMS_3;
    }

    public static List<DummyItem> ITEMS_4() {
        if (ITEMS_4.isEmpty()) {
            ITEMS_4.addAll(createList_4());
        }
        return ITEMS_4;
    }

    public static List<DummyItem> ITEMS_1000() {
        if (ITEMS_1000.isEmpty()) {
            ITEMS_1000.addAll(createList_1000());
        }
        return ITEMS_1000;
    }

    public static List<DummyItem> ITEMS_10000() {
        if (ITEMS_10000.isEmpty()) {
            ITEMS_10000.addAll(createList_1000());
        }
        return ITEMS_10000;
    }

    public static List<DummyItem> ITEMS_100000() {
        if (ITEMS_100000.isEmpty()) {
            ITEMS_100000.addAll(createList_100000());
        }
        return ITEMS_100000;
    }

    public static ArrayList<String> ITEMS_STRING_3() {
        if (ITEMS_STRING_3.isEmpty()) {
            ITEMS_STRING_3.addAll(createStringList_3());
        }
        return ITEMS_STRING_3;
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(position, "Item " + position, position);
    }

    private static List<DummyItem> createList_3() {
        List<DummyItem> list = new ArrayList<>();
        list.add(new DummyItem(1, "City", 1));
        list.add(new DummyItem(2, "China", 11));
        list.add(new DummyItem(3, "D", 2));
        return list;
    }

    private static List<DummyItem> createList_4() {
        List<DummyItem> list = new ArrayList<>();
        list.add(new DummyItem(1, "ABC", 2));
        list.add(new DummyItem(2, "Book", 9));
        list.add(new DummyItem(3, "99%", 9));
        list.add(new DummyItem(4, "OP", 15));
        return list;
    }

    private static List<DummyItem> createList_1() {
        List<DummyItem> list = new ArrayList<>();
        list.add(new DummyItem(1, "Agile", 1911));
        return list;
    }

    private static List<DummyItem> createList_1000() {
        return createList(1000);
    }

    private static List<DummyItem> createList_10000() {
        return createList(10000);
    }

    private static List<DummyItem> createList_100000() {
        return createList(100000);
    }

    private static ArrayList<String> createStringList_3() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        return list;
    }

    private static List<DummyItem> createList(int size) {
        List<DummyItem> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new DummyItem(i + 1, "Dummy" + (i + 1), i + 1));
        }
        return list;
    }
}
