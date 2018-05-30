#  String

- `TestString.java`  
- 'String.java `

```
public final class String  implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];
 
    /** The offset is the first index of the storage that is used. */
    private final int offset;
 
    /** The count is the number of characters in the String. */
    private final int count;
 
    /** Cache the hash code for the string */
    private int hash; // Default to 0
 
    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -6849794470754667710L;
     ......
     
    public String substring(int beginIndex, int endIndex) {
    if (beginIndex < 0) {
        throw new StringIndexOutOfBoundsException(beginIndex);
    }
    if (endIndex > count) {
        throw new StringIndexOutOfBoundsException(endIndex);
    }
    if (beginIndex > endIndex) {
        throw new StringIndexOutOfBoundsException(endIndex - beginIndex);
    }
    return ((beginIndex == 0) && (endIndex == count)) ? this :
        new String(offset + beginIndex, endIndex - beginIndex, value);
    }
 
 public String concat(String str) {
    int otherLen = str.length();
    if (otherLen == 0) {
        return this;
    }
    char buf[] = new char[count + otherLen];
    getChars(0, count, buf, 0);
    str.getChars(0, otherLen, buf, count);
    return new String(0, count + otherLen, buf);
    }
 
 public String replace(char oldChar, char newChar) {
    if (oldChar != newChar) {
        int len = count;
        int i = -1;
        char[] val = value; /* avoid getfield opcode */
        int off = offset;   /* avoid getfield opcode */
 
        while (++i < len) {
        if (val[off + i] == oldChar) {
            break;
        }
        }
        if (i < len) {
        char buf[] = new char[len];
        for (int j = 0 ; j < i ; j++) {
            buf[j] = val[off+j];
        }
        while (i < len) {
            char c = val[off + i];
            buf[i] = (c == oldChar) ? newChar : c;
            i++;
        }
        return new String(0, len, buf);
        }
    }
    return this;
  }   
}
```

- final -> 不能继承 -> 不能覆盖/重写
- constant
Strings are constant; their values cannot be changed after they are created.  
Java中的String具有不变性。       
无论是sub操、concat还是replace操作都不是在原有的字符串上进行的，而是重新生成了一个新的字符串对象。也就是说进行这些操作后，最原始的字符串并没有被改变。  
即： 所有对String对象的修改操作（包括append，substring，concat，replace，trim等），在具体实现中返回的都是一个全新的string对象副本，这些中间和最终副本都会放到运行时常量区中。  
“对String对象的任何改变都不影响到原对象，相关的任何change操作都会生成新的对象” 。    
- String类其实是通过char数组来保存字符串的
- A String represents a string in the UTF-16 format  

##  TestString2->test()   的字节码分析  
- https://github.com/YingVickyCao/AndroidAboutDemos/blob/master/doc/java_memory.xlsx
- `TestString2.java`


```
public class TestString2 {
    public void test() {
        String str1 = new String("abc");
        String str2 = "abc";
        // 中间创建的对象：1个StringBuffer ， 1 个 String
        String str3 = "ab" + new String("c");
    }
}
```

```
   //0 ~ 9 对应的是第一行的java语句：String str1 = "abc";
       0: new           #21                 // class java/lang/String
       3: dup
       /*
       **装载一个常量字符串 ，符号#23代表的字符串对象就是 “abc”,
       **常量字符串在程序运行之前就已经被创建
       */
       4: ldc           #23                 // String abc
       /*str1不指向常量字符串“abc”,
       **而是将这个常量字符串作为构造函数的实参传入
       **在java堆中重新创建了一个全新的对象
       */
       6: invokespecial #25                 // Method java/lang/String."<init>":(Ljava/lang/String;)V
       9: astore_1


      //从这里开始到12 都是第二行的Java代码: String str2 = "abc";
      /*
      ** 直接调用运行时常量池中的对象
      */
      10: ldc           #23                 // String abc
      12: astore_2

     //从此处开始到最后对应第三行的Java代码:String str3 = "ab" + new String("c");
      /*
      **对于这种new 对象与 常量字符串相结合的方式，
      **JAVA编译器在处理过程中创建了一个StringBuilder对象用于处理异构字符串的拼接工作
      ** "ab"对应另一个常量字符串 “c”则运行时动态创建的String对象

       中间创建的对象：1个StringBuffer ， 1 个 String
      **/
      // StringBuffer
      13: new           #28                 // class java/lang/StringBuffer
      16: dup
      17: ldc           #30                 // String ab
      19: invokespecial #32                 // Method java/lang/StringBuffer."<init>":(Ljava/lang/String;)V

      // c -> new String
      22: new           #21                 // class java/lang/String
      25: dup
      26: ldc           #33                 // String c
      28: invokespecial #25                 // Method java/lang/String."<init>":(Ljava/lang/String;)V
      31: invokevirtual #35                 // Method java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;

      // toString:() -> new String
      34: invokevirtual #39                 // Method java/lang/StringBuffer.toString:()Ljava/lang/String;
      37: astore_3
      38: return
```

- 编译器默认用的字符串拼接器是StringBuffuer类
- 通常情况下动态的string对象做拼接用的都是StringBuilder类。 
-  字节码中的符号含义：    
```
new 创建一个对象
dup = duplicate
LDC  = LoaD Constant 将int,float或String型常量值从常量池中推送至栈顶 
invokespecial调用构造函数、实例化方法
invokevirtual 调用实例方法 
getfield 获取对象的一个实例属性(field)，push到操作数栈
putfield通过对象的引用和指向常量池CONSTANT_Fieldref_info类型的索引，给对象的属性赋值
```

## JDK `String.java` / `StringBuffer.java`源码？  

android SDK `StringBuffer` - `append` and `toString()`方法被屏蔽掉了。 =>> JDK   

- Mac下查看已安装的jdk版本及其安装目录:   
`/usr/libexec/java_home -V` -> Finder , “Go to finder”  -> "src.zip" -> "java.lang.String.java"

- `StringBuffer.java -> append() -> (native)System.arraycopy()`      
- `StringBuffer.java -> toString() -> new String(toStringCache, true)`       

## jvm--Java中init和clinit
TODO

 
## References:
- https://developer.android.com/reference/java/lang/String

- Java杂谈4——Java中的字符串存储  https://www.cnblogs.com/yahokuma/p/3677576.html
- Java中字符串内存位置浅析 https://www.cnblogs.com/holten/p/5782596.html
- java中String和new String还有对象中的String字符串在内存中的存储   https://blog.csdn.net/penyoudi1/article/details/79163567
- java clinit
https://blog.csdn.net/u013309870/article/details/72975536  
https://www.cnblogs.com/diyunpeng/archive/2010/07/11/1775200.html
- java class 文件常量区 https://blog.csdn.net/w3045872817/article/details/73251068  