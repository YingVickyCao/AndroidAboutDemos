package com.hades.android.example.android_about_demos.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.baseSerialization).setOnClickListener(v -> baseSerialization());
        findViewById(R.id.baseDeserialization).setOnClickListener(v -> baseDeserialization());
        findViewById(R.id.arraySerialization).setOnClickListener(v -> arraySerialization());
        findViewById(R.id.customSerialization).setOnClickListener(v -> customSerialization());
        findViewById(R.id.customDeserialization).setOnClickListener(v -> customDeserialization());
        findViewById(R.id.transientCustomSerialization).setOnClickListener(v -> transientCustomSerialization());
        findViewById(R.id.collectionSerialization).setOnClickListener(v -> collectionSerialization());
        findViewById(R.id.collectionDeserialization).setOnClickListener(v -> collectionDeserialization());
        findViewById(R.id.collectionDeserialization2).setOnClickListener(v -> collectionDeserialization2());
    }

    /**
     * <pre>
     *     {
     * "firstName": "John",
     * "lastName": "Smith",
     * "sex": "male",
     * "age": 25,
     * "address":
     * {
     * "streetAddress": "21 2nd Street",
     * "city": "New York",
     * "state": "NY",
     * "postalCode": "10021"
     * },
     * "phoneNumber":
     * [
     * {
     * "type": "home",
     * "number": "212 555-1234"
     * },
     * {
     * "type": "fax",
     * "number": "646 555-4567"
     * }
     * ]
     * }
     * </pre>
     */


    /**
     * primitive type -> json string
     */
    public void baseSerialization() {
        Gson gson = new Gson();
        Log.d(TAG, gson.toJson(1));
        Log.d(TAG, gson.toJson("Johh"));
        Log.d(TAG, gson.toJson(10L));
        Log.d(TAG, gson.toJson(new Date()));
        Log.d(TAG, gson.toJson(new int[]{1, 2}));
    }

    /**
     * json string -> primitive type
     */
    public void baseDeserialization() {
        Gson gson = new Gson();
        int one = gson.fromJson("1", int.class);
        Log.d(TAG, one + "");
        Integer oneInteger = gson.fromJson("1", Integer.class);
        Log.d(TAG, oneInteger + "");
        Long oneLong = gson.fromJson("1", Long.class);
        Log.d(TAG, oneLong + "");
        Boolean falseBoolean = gson.fromJson("false", Boolean.class);
        Log.d(TAG, falseBoolean + "");
        String str = gson.fromJson("\"abc\"", String.class);
        Log.d(TAG, str + "");
    }


    /**
     * Array  -> json string
     */
    public void arraySerialization() {
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};

        Gson gson = new Gson();

        // Serialization
        String s1 = gson.toJson(ints);     // ==> [1,2,3,4,5]
        String s2 = gson.toJson(strings);  // ==> ["abc", "def", "ghi"]

        Log.d(TAG, s1);
        Log.d(TAG, s2);
    }

    /**
     * json string -> array
     */
    public void arrayDerialization() {
        String jsonArray = "[1,2,3,4,5]";

        Gson gson = new Gson();

        // Deserialization
        int[] ints2 = gson.fromJson(jsonArray, int[].class);

        StringBuilder builder = new StringBuilder();

        for (int i : ints2) {
            builder.append(i + ",");
        }
        Log.d(TAG, builder.toString());
    }

    /**
     * bean -> json string
     */
    // TODO:顺序？
    public void customSerialization() {
        String json = new Gson().toJson(new InfoBean());
        Log.d(TAG, json);
    }

    /**
     * json string -> bean
     */
    public void customDeserialization() {
        String json = "{\"age\":100,\"firstName\":\"John\",\"lastName\":\"Smith\",\"sex\":\"male\"}";
        Log.d(TAG, new Gson().fromJson(json, InfoBean.class).toString());
    }


    /**
     * transient bean -> json string
     */
    // TODO:顺序？
    public void transientCustomSerialization() {
        String json = new Gson().toJson(new TransientInfoBean("Hello", "Smith", "male", (short) 25));
        Log.d(TAG, json);
    }

    /**
     * json string -> bean
     */
    public void transientCustomDeserialization() {
//        String json = "{\"age\":100,\"firstName\":\"John\",\"lastName\":\"Smith\",\"sex\":\"male\"}";
        String json = "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"sex\":\"male\"}";
        Log.d(TAG, new Gson().fromJson(json, TransientInfoBean.class).toString());
    }

    public void collectionSerialization() {
        Gson gson = new Gson();
        Collection<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 100);

        // Serialization
        String json = gson.toJson(ints);  // ==> json is [1,2,3,4,5]
        Log.d(TAG, json);
    }

    /**
     * json string -> Collection
     */
    public void collectionDeserialization() {
        Gson gson = new Gson();

        String json = "[1,2,3,4,5]";
        Log.d(TAG, "collectionDeserialization," + json);

        // Deserialization
        Type collectionType = new TypeToken<Collection<Integer>>() {
        }.getType();

        Collection<Integer> ints2 = gson.fromJson(json, collectionType);
        Log.d(TAG, "collectionDeserialization," + ints2.toString());
    }

    /**
     * json string -> Collection
     */
    public void collectionDeserialization2() {
        Gson gson = new Gson();

        // Serialization
        String json = "[1,2,3,4,5]";
        Log.d(TAG, "collectionDeserialization2," + json);

        // Deserialization
        Type collectionType = new TypeToken<ArrayList<Integer>>() {
        }.getType();

        Collection<Integer> ints2 = gson.fromJson(json, collectionType);
        Log.d(TAG, "collectionDeserialization2," + ints2.toString());
    }


//    /**
//     * Serializing and Deserializing Generic Types
//     */
//    @OnClick(R.id.genericTypes)
//    public void genericTypes() {
//        Gson gson = new Gson();
//
//        Foo<String> foo1 = new Foo<String>("abc");
//        String json = gson.toJson(foo1);
//        Log.d(TAG, "genericTypes: " + json);
//        Log.d(TAG, "genericTypes: " + gson.fromJson(json, foo1.getClass()));
//
//
//        Foo<Integer> foo12 = new Foo<Integer>(1024);
//        json = gson.toJson(foo12);
//        Log.d(TAG, "genericTypes: " + json);
//        Log.d(TAG, "genericTypes: " + gson.fromJson(json, foo1.getClass()));
//
//        Foo<InfoBean> foo13 = new Foo<InfoBean>(new InfoBean());
//        json = gson.toJson(foo13);
//        Log.d(TAG, "genericTypes: " + json);
//        Log.d(TAG, "genericTypes: " + gson.fromJson(json, foo1.getClass()));
//
//    }
}
