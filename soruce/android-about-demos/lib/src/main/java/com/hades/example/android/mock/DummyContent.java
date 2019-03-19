package com.hades.example.android.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<>();
    public static final List<DummyItem> ITEMS_1 = new ArrayList<>();
    public static final List<DummyItem> ITEMS_2 = new ArrayList<>();
    public static final List<DummyItem> ITEMS_3 = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, DummyItem> ITEM_MAP = new HashMap<Integer, DummyItem>();

    private static final int COUNT = 50;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
        ITEMS_1.addAll(createList1());
        ITEMS_2.addAll(createList2());
        ITEMS_3.addAll(createList3());
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(position, "Item " + position, position);
    }

    private static List<DummyItem> createList1() {
        List<DummyItem> list = new ArrayList<>();
        list.add(new DummyItem(1, "City", 1));
        list.add(new DummyItem(2, "China", 11));
        list.add(new DummyItem(3, "D", 2));
        return list;
    }

    private static List<DummyItem> createList2() {
        List<DummyItem> list = new ArrayList<>();
        list.add(new DummyItem(1, "ABC", 2));
        list.add(new DummyItem(2, "hello", 204));
        list.add(new DummyItem(3, "Book", 9));
        /**
         * FIXED_ERROR:
         * 2019-03-18 17:56:23.276 2700-2700/com.hades.example.android E/SQLiteDatabase: Error inserting col2=Book _id=3 col3=9
            android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: table3._id (code 1555)
         */
//        list.add(new DummyItem(3, "OP", 15));
        list.add(new DummyItem(4, "OP", 15));
        return list;
    }

    private static List<DummyItem> createList3() {
        List<DummyItem> list = new ArrayList<>();
        list.add(new DummyItem(1, "Agile", 1911));
        return list;
    }
}
