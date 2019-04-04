package com.example.hades.androidpo._2_memory_op.sparse_array;

import android.util.SparseArray;

import java.util.HashMap;

/**
 * Google 5X(Android 8.0)
 */
public class TestSparseArray {
    private final int MAX = 100;

    // HashMap 的正序插入
    // 100 ->    HashMap:Insert used time=19,used memory=0
    // 500 -> HashMap:Insert used time=99,used memory=0
    // 1000 -> HashMap:Insert used time=174,used memory=0
    // 5000 -> HashMap:Insert used time=916,used memory=0
    // 65536 -> HashMap:Insert used time=8143,used memory=9714854
    // 100000 -> HashMap:Insert used time=16106,used memory=9774632
    public void hashMapInsert() {

        long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory();

        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < MAX; i++) {
            map.put(i, String.valueOf(i));
        }

        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory();
        System.out.println(String.valueOf(MAX) + " -> HashMap:Insert used time=" + (endTime - startTime) + ",used memory=" + (endMemory - startMemory));
    }

    // SparseArray 的正序插入
    // 100->SparseArray:Insert used time=17,used memory=0
    // 500->SparseArray:Insert used time=64,used memory=0
    // 1000->SparseArray:Insert used time=117,used memory=0
    // 5000->SparseArray:Insert used time=563,used memory=0
    // 65536 ->  Insert used time=7018,used memory=4340983
    // 100000 -> Insert used time=10627,used memory=6257528
    public void sparseArrayInsert() {
        long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory();

        SparseArray<String> spare = new SparseArray<>();

        for (int i = 0; i < MAX; i++) {
            spare.put(i, String.valueOf(i));
        }

        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory();
        System.out.println(String.valueOf(MAX) + "->SparseArray:Insert used time=" + (endTime - startTime) + ",used memory=" + (endMemory - startMemory));
    }

    // HashMap 的倒序插入
    // 100->HashMap: Reserve insert used time=19,used memory=0
    // 500->HashMap: Reserve insert used time=90,used memory=0
    // 1000->HashMap: Reserve insert used time=177,used memory=0
    // 5000->HashMap: Reserve insert used time=86,used memory=0
    // 65536 ->  HashMap: Reserve insert used time=11043,used memory=9671560
    // 100000 -> HashMap: Reserve insert used time=16397,used memory=9679631
    public void hashMapReverseInsert() {
        long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory();

        HashMap<Integer, String> map = new HashMap<>();
        for (int i = MAX - 1; i >= 0; i--) {
            map.put(i, String.valueOf(i));
        }

        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory();
        System.out.println(String.valueOf(MAX) + "->HashMap: Reserve insert used time=" + (endTime - startTime) + ",used memory=" + (endMemory - startMemory));
    }

    // SparseArray 的倒序插入
    // 100->SparseArray:Reserve insert used time=9,used memory=0
    // 500->SparseArray:Reserve insert used time=41,used memory=0
    // 1000->SparseArray:Reserve insert used time=126,used memory=0
    // 5000->SparseArray:Reserve insert used time=352,used memory=0
    // 65536->SparseArray:Reserve insert used time=28216,used memory=7597008
    // 100000->SparseArray:Reserve insert used time=67165,used memory=6785831
    public void sparseArrayReserveInsert() {
        long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory();

        SparseArray<String> spare = new SparseArray<>();

        for (int i = MAX - 1; i >= 0; i--) {
            spare.put(i, String.valueOf(i));
        }

        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory();
        System.out.println(String.valueOf(MAX) + "->SparseArray:Reserve insert used time=" + (endTime - startTime) + ",used memory=" + (endMemory - startMemory));
    }


    // HashMap 的查找
    // 100->HashMap: Search used time=1,used memory=0
    // 500->HashMap: Search used time=20,used memory=0
    // 1000->HashMap: Search used time=39,used memory=0
    // 5000->HashMap: Search used time=160,used memory=0
    // 65536->HashMap: Search used time=1657,used memory=0
    // 100000->HashMap: Search used time=3334,used memory=6485263
    public void hashMapSearch() {
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < MAX; i++) {
            map.put(i, String.valueOf(i));
        }

        long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory();
        for (int i = 0; i < MAX; i++) {
            map.get(i);
        }
        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory();
        System.out.println(String.valueOf(MAX) + "->HashMap: Search used time=" + (endTime - startTime) + ",used memory=" + (endMemory - startMemory));
    }

    // SparseArray的查找
    // 100->SparseArray:search used time=0,used memory=0
    // 500->SparseArray:search used time=3,used memory=0
    // 1000->SparseArray:search used time=6,used memory=0
    // 5000->SparseArray:search used time=11,used memory=0
    // 65536->SparseArray:search used time=30,used memory=0
    // 100000->SparseArray:search used time=39,used memory=0
    public void sparseArraySearch() {
        SparseArray<String> spare = new SparseArray<>();

        for (int i = 0; i < MAX; i++) {
            spare.put(i, String.valueOf(i));
        }

        long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory();
        for (int i = 0; i < MAX; i++) {
            spare.get(i);
        }

        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory();
        System.out.println(String.valueOf(MAX) + "->SparseArray:search used time=" + (endTime - startTime) + ",used memory=" + (endMemory - startMemory));
    }


    // HashMap 删除操作
    // 100->HashMap: remove used time=1,used memory=0
    // 500->HashMap: remove used time=22,used memory=0
    // 1000->HashMap: remove used time=24,used memory=0
    // 5000->HashMap: remove used time=96,used memory=0
    // 65536->HashMap: remove used time=1275,used memory=0
    // 100000->HashMap: remove used time=5177,used memory=-6198802
    public void hashMapRemove() {
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < MAX; i++) {
            map.put(i, String.valueOf(i));
        }

        long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory();
        for (int i = 0; i < MAX; i++) {
            map.remove(i);
        }
        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory();
        System.out.println(String.valueOf(MAX) + "->HashMap: remove used time=" + (endTime - startTime) + ",used memory=" + (endMemory - startMemory));
    }

    // 100->SparseArray:remove used time=1,used memory=0
    // 500->SparseArray:remove used time=3,used memory=0
    // 1000->SparseArray:remove used time=5,used memory=0
    // 5000->SparseArray:remove used time=12,used memory=0
    // 65536->SparseArray:remove used time=31,used memory=0
    // 100000->SparseArray:remove used time=40,used memory=0
    public void sparseArrayRemove() {
        SparseArray<String> spare = new SparseArray<>();

        for (int i = 0; i < MAX; i++) {
            spare.put(i, String.valueOf(i));
        }

        long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory();
        for (int i = 0; i < MAX; i++) {
            spare.remove(i);
        }

        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory();
        System.out.println(String.valueOf(MAX) + "->SparseArray:remove used time=" + (endTime - startTime) + ",used memory=" + (endMemory - startMemory));
    }


    public void sparseArrayOPs() {
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(100, "A");
        sparseArray.put(200, "B");
        sparseArray.put(300, "C");

        System.out.println(sparseArray.toString()); // {100=A, 200=B, 300=C}

        System.out.println(sparseArray.size());  // 3

        System.out.println(sparseArray.get(4)); // null
        System.out.println(sparseArray.get(4, "F")); // F

        System.out.println(sparseArray.indexOfKey(4)); // -1
        System.out.println(sparseArray.indexOfKey(200));// 1

        System.out.println(sparseArray.indexOfValue("V")); // -1
        System.out.println(sparseArray.indexOfValue("B")); // 1

        sparseArray.delete(4);
        System.out.println(sparseArray.toString()); // {100=A, 200=B, 300=C}
        sparseArray.delete(100);
        System.out.println(sparseArray.toString()); // {200=B, 300=C}

        sparseArray.remove(4);
        System.out.println(sparseArray.toString()); // {200=B, 300=C}
        sparseArray.remove(300);
        System.out.println(sparseArray.toString());// {200=B}

        sparseArray.append(500, "G");
        System.out.println(sparseArray.toString()); // {200=B, 500=G}

        System.out.println(sparseArray.keyAt(1)); // 500
        System.out.println(sparseArray.valueAt(1)); // G
    }
}

