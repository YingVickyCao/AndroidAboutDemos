# String VS StringBuffer VS StringBuilder  

`TestAppend.java`
```
public class TestAppend {
    /*
     String +, time=2484
     StringBuffer append, time=7
     StringBuilder append, time=6
     */
    public void test() {
        long startTime = System.currentTimeMillis();
        testStringAppend();

        long endTime = System.currentTimeMillis();
        System.out.println("String +, time=" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        testStringBufferAppend();
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer append, time=" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        testStringBuilderAppend();
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder append, time=" + (endTime - startTime));
    }

    private void testStringAppend() {
        String str = new String();
        for (int i = 0; i < 10000; i++) {
            str += "hello";
        }
    }

    private void testStringBufferAppend() {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            str.append("hello");
        }
    }

    private void testStringBuilderAppend() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            str.append("hello");
        }
    }
}

```

```
str+="hello"
// 等价于 str = str  + "hello"
```
会自动被JVM优化成 => 
```
StringBuffer str2 = new StringBuffer(str);
str2.append("hello");
str2.toString();
```

## String、StringBuffer以及StringBuilder的区别
- String不可变。字符拼接等操作会自动被JVM优化 - 借助StringBuffer。
- 与StringBuilder相比，StringBuffer类的成员方法前面多了一个关键字：synchronized.    
即StringBuffer是线程安全的，StringBuilder是非线程安全。  
其他没有区别。

```
public StringBuilder insert(int index, char str[], int offset, int len)  {
       super.insert(index, str, offset, len);
return this;
  }
```

```
public synchronized StringBuffer insert(int index, char str[], int offset,  int len) {
        super.insert(index, str, offset, len);
        return this;
    }
```

## String、StringBuffer以及StringBuilder的执行效率
- StringBuilder > StringBuffer > String
- 排序是相对的，不一定在所有情况下都是这样。
比如String str = "hello"+ "world"的效率就比 StringBuilder st  = new StringBuilder().append("hello").append("world")要高。  

## String、StringBuffer以及StringBuilder的选择
- 当字符串相加操作或者改动较少的情况下，使用 String str
- 当字符串相加操作较多的情况下，使用StringBuilder
- 当字符串相加操作较多的情况下 + 多线程，使用StringBuffer。