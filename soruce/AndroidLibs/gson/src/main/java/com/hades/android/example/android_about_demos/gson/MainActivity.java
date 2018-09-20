package com.hades.android.example.android_about_demos.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hades.android.example.android_about_demos.gson.bean.Stu;
import com.hades.android.example.android_about_demos.gson.bean.StuHasSerializedName;
import com.hades.android.example.android_about_demos.gson.bean.StuHasSerializedName2;
import com.hades.android.example.android_about_demos.gson.bean.StuHasTransient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new Gson();

        findViewById(R.id.baseSerialization).setOnClickListener(v -> baseSerialization());
        findViewById(R.id.baseDeserialization).setOnClickListener(v -> baseDeserialization());

        findViewById(R.id.arraySerialization).setOnClickListener(v -> arraySerialization());
        findViewById(R.id.arrayDerialization).setOnClickListener(v -> arrayDerialization());

        findViewById(R.id.customSerialization).setOnClickListener(v -> customSerialization());
        findViewById(R.id.customDeserialization).setOnClickListener(v -> customDeserialization());

        findViewById(R.id.transientCustomSerialization).setOnClickListener(v -> transientCustomSerialization());
        findViewById(R.id.transientCustomDeserialization).setOnClickListener(v -> transientCustomDeserialization());

        findViewById(R.id.collectionSerialization).setOnClickListener(v -> collectionSerialization());
        findViewById(R.id.collectionDeserialization).setOnClickListener(v -> collectionDeserialization());
        findViewById(R.id.collectionDeserialization2).setOnClickListener(v -> collectionDeserialization2());

        findViewById(R.id.genericTypesSerialization).setOnClickListener(v -> genericTypesSerialization());
        findViewById(R.id.genericTypesDeserialization).setOnClickListener(v -> genericTypesDeserialization());

        findViewById(R.id.serializedNameSerialization).setOnClickListener(v -> serializedNameSerialization());
        findViewById(R.id.serializedNameDeserialization).setOnClickListener(v -> serializedNameDeserialization());

        findViewById(R.id.serializedNameSerialization2).setOnClickListener(v -> serializedNameSerialization2());
        findViewById(R.id.serializedNameDeserialization2).setOnClickListener(v -> serializedNameDeserialization2());
    }

    /**
     * primitive type -> json string
     */
    public void baseSerialization() {
        Log.d(TAG, gson.toJson(1));     // 1
        Log.d(TAG, gson.toJson("A"));   // "A
        Log.d(TAG, gson.toJson(10L));   // 10
        Log.d(TAG, gson.toJson(new Date()));    // "Sep 5, 2018 22:57:10"
        Log.d(TAG, gson.toJson(new int[]{1, 2}));   // [1,2]
    }

    /**
     * json string -> primitive type
     */
    public void baseDeserialization() {
        int one = gson.fromJson("1", int.class);
        Log.d(TAG, one + "");   // 1

        Integer oneInteger = gson.fromJson("1", Integer.class);
        Log.d(TAG, oneInteger + "");    // 1

        Long oneLong = gson.fromJson("1", Long.class);
        Log.d(TAG, oneLong + "");  // 1

        Boolean falseBoolean = gson.fromJson("false", Boolean.class);
        Log.d(TAG, falseBoolean + "");  // false

        String str = gson.fromJson("\"abc\"", String.class);
        Log.d(TAG, str + ""); // abc

        String str2 = gson.fromJson("def", String.class);
        Log.d(TAG, str2 + ""); // def
    }

    /**
     * Array  -> json string
     */
    public void arraySerialization() {
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};

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

        // Deserialization
        int[] ints2 = gson.fromJson(jsonArray, int[].class);

        StringBuilder builder = new StringBuilder();

        for (int i : ints2) {
            builder.append(i + ",");
        }
        Log.d(TAG, builder.toString()); // => 1,2,3,4,5,
    }

    /**
     * bean -> json string
     */
    // TODO:顺序？
    public void customSerialization() {
        String json = gson.toJson(new Stu());
        Log.d(TAG, json); // => {"age":25,"firstName":"Catty","lastName":"Smith","sex":"male"}
    }

    /**
     * json string -> bean
     */
    public void customDeserialization() {
        String json = "{\"age\":100,\"firstName\":\"John\",\"lastName\":\"Smith\",\"sex\":\"male\"}";
        Log.d(TAG, gson.fromJson(json, Stu.class).toString()); // => {firstName='John', lastName='Smith', sex='male', age=100}

        String json2 = "{age:100,firstName:John,lastName:Smith,sex:male}";
        Log.d(TAG, gson.fromJson(json2, Stu.class).toString()); // => {firstName='John', lastName='Smith', sex='male', age=100}
    }


    /**
     * transient bean -> json string
     */
    // TODO:顺序？
    public void transientCustomSerialization() {
        String json = gson.toJson(new StuHasTransient("Hello", "Smith", "male", (short) 25));
        Log.d(TAG, json); // => {"firstName":"Hello","lastName":"Smith","sex":"male"}, does not have transient age 25
    }

    /**
     * json string -> bean
     */
    public void transientCustomDeserialization() {
//        String json = "{\"age\":100,\"firstName\":\"John\",\"lastName\":\"Smith\",\"sex\":\"male\"}";
        String json = "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"sex\":\"male\"}";
        Log.d(TAG, gson.fromJson(json, StuHasTransient.class).toString());
    }

    public void collectionSerialization() {
        Collection<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        ints.add(100);

        // Serialization
        String json = gson.toJson(ints);  // ==> json is [1,2,3,4,5]
        Log.d(TAG, json);
    }

    /**
     * json string -> Collection
     */
    public void collectionDeserialization() {
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
        // Serialization
        String json = "[1,2,3,4,5]";
        Log.d(TAG, "collectionDeserialization2," + json);

        // Deserialization
        Type collectionType = new TypeToken<ArrayList<Integer>>() {
        }.getType();

        Collection<Integer> ints2 = gson.fromJson(json, collectionType);
        Log.d(TAG, "collectionDeserialization2," + ints2.toString());
    }

    /**
     * Generic Types
     */
    public void genericTypesSerialization() {


//        Foo<String> foo1 = new Foo<>("abc");
//        String json = gson.toJson(foo1);
//        Log.d(TAG, "genericTypesSerialization: json=" + json); // => {"value":"abc"}


//        Foo<Integer> foo12 = new Foo<Integer>(1024);
//        json = gson.toJson(foo12);
//        Log.d(TAG, "genericTypes: " + json);
//        Log.d(TAG, "genericTypes: " + gson.fromJson(json, foo1.getClass()));
//
//        Foo<Stu> foo13 = new Foo<Stu>(new Stu());
//        json = gson.toJson(foo13);
//        Log.d(TAG, "genericTypes: " + json);
//        Log.d(TAG, "genericTypes: " + gson.fromJson(json, foo1.getClass()));

    }

    /**
     * 泛型擦除:对于Java来说List<String> 和List<User> 这俩个的字节码文件只一个那就是List.class
     */
    private void genericTypesDeserialization() {
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        List<String> stringListJson = gson.fromJson(jsonArray, new TypeToken<List<String>>() {
        }.getType());
        Log.d(TAG, "genericTypesDeserialization: " + stringListJson); // => [Android, Java, PHP]
    }

    /*
    属性重命名 @SerializedName 注解
    Actual: {"name":"A", "age":24, "email_address":"ABC@example.com" }
    Expect: {"name":"A", "age":24, "emailAddress":"ABC@example.com" }
     */
    private void serializedNameSerialization() {
        StuHasSerializedName stu = new StuHasSerializedName("A", 24, "ABC@example.com");
        String json = gson.toJson(stu);

        Log.d(TAG, json); // => {"age":24,"emailAddress":"ABC@example.com","name":"A"}
    }

    private void serializedNameDeserialization() {
        String json = "{{\"age\":24,\"emailAddress\":\"ABC@example.com\",\"name\":\"A\"}";
        // ERROR:com.google.gson.JsonSyntaxException: com.google.gson.stream.MalformedJsonException: Expected name at line 1 column 2 path
        Log.d(TAG, gson.fromJson(json, StuHasSerializedName.class).toString());
    }


    private void serializedNameSerialization2() {
        StuHasSerializedName2 stu = new StuHasSerializedName2("A", 24, "ABC@example.com");
        String json = gson.toJson(stu);

        Log.d(TAG, json); // => {"age":24,"emailAddress":"ABC@example.com","name":"A"}
    }

    private void serializedNameDeserialization2() {
        String json = "{{\"age\":24,\"emailAddress\":\"ABC@example.com\",\"name\":\"A\"}";
        // ERROR:com.google.gson.JsonSyntaxException: com.google.gson.stream.MalformedJsonException: Expected name at line 1 column 2 path
        StuHasSerializedName2 stu = gson.fromJson(json, StuHasSerializedName2.class);
        Log.d(TAG, stu.toString());
    }
}