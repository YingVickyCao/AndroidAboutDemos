# QA about string

- `StringQA.java`

## Java常量池
- 类在加载完成之后，会在内存中存储类中的一些字面量（本身即是值如10，“abc”）。
- 对于字符串常量来说，Java会保证常量池中的字面量不会有多个副本，但是Java代码中可能不同的变量的值是相同的。
- 在编译期间，这两个变量值所在地址是相同的。而且Java在编译期间会对字符串进行一定的处理。
- 如果一个字符串采用拼接的方式，并且拼接的内容都是字面量的话，那么会自动将字符串先拼接完再赋值。
- 如果常量池中已经有了拼接完成之后的字面量，那么此变量的值的地址就是常量池中的完整字符串的地址。
- String在赋值完成之后修改，是会产生新的变量的。比如：
```
String str = "reeves";
str = "abc";
```
那么实际上在常量池中存储了"reeves"和"abc"两个字面值，在字符串变量赋予新的值的时候并不会改变原先存储的值，它会再新建一个字符串，而在栈中变量存储的值的地址是变了的。

## QA：String s = new String("xyz");  创建了几个String Object？
提示方式不合理。

##  QA：String s = new String("xyz");  在运行时涉及几个String实例？
答案：两个，一个是字符串字面量"xyz"所对应的、驻留（intern）在一个全局共享的字符串常量池中的实例，另一个是通过new String(String)创建并初始化的、内容与"xyz"相同的实例，在堆中，并指向字符串常量池中的“xyz”。

```
0: new     #3; //class java/lang/String //只调用了一次new
3: dup
4: ldc     #4; //String xyz
6: invokespecial #5;//Method java/lang/String."<init>":（Ljava/lang/String;)V
9:   astore_1

```

## QA：`String s = new String("xyz");` 涉及用户声明的几个String类型的变量？  
答案：一个，就是String s。   

## QA：`String s = null;`  涉及用户声明的几个String类型的变量？
答案：一个，就是String s。   

## QA:`String s1 = new String("xyz");  String s2 = new String("xyz");`  创建几个String实例？
答案：2个。  

引用类型的变量:  
- Java里变量就是变量，引用类型的变量只是对某个对象实例或者null的引用，不是实例本身。   
- 声明变量的个数跟创建实例的个数没有必然关系。   
- 在Java语言里，“new”表达式是负责创建实例的，其中会调用构造器去对实例做初始化。并不是“构造器返回了新创建的对象的引用”，而是new表达式的值是新创建的对象的引用。 

```
String s1 = "a";
String s2 = s1.concat("");
String s3 = null;
new String(s1);
```

这段代码会涉及3个String类型的变量：   
1、s1，指向下面String实例的   
2、s2，指向与s1相同   
3、s3，值为null，不指向任何实例    
以及3个String实例， 
1、"a"字面量对应的驻留的字符串常量的String实例   
2、""字面量对应的驻留的字符串常量的String实例   
（String.concat()是个有趣的方法，当发现传入的参数是空字符串时会返回this，所以这里不会额外创建新的String实例）   
3、通过new String(String)创建的新String实例；没有任何变量指向它。


## 例子:
```
  public void sample1() {
        String str1 = "reeves";
        String str2 = "reeves";
        System.out.println(str1 == str2); // true
   }
```
在编译期间变量str1和str2值得地址都可以确定，因为两个变量的值相同，指向常量池中的地址也相同，因此使用“==”符号来判断两者值得地址是否相同时，返回的是true。  

## 例子：
```
public void sample2() {
        String str1 = "reeves";
        String str2 = "ree" + "ves";
        System.out.println(str1 == str2); // true
    }
```
Java语言在编译期间，对于字符串拼接且拼接元素都是字面量的情况，会自动将拼接字符串拼接完整之后再赋值，因此 String str2 = "ree"+"ves"; 就相当于 String str2 = "reeves"; 而“reeves”字面量在常量池中存在，因此str2的引用地址和str1相同。


## 例子
```
public void sample3() {
        String s = new String("reeves");
 }
```
第1步：使用new关键字新建String对象时，会在堆中新创建一个字符串对象。  
第2步：然后，Java也会监测常量池中是否有“reeves”字面量。如果没有，那么在常量池中再新建一个“reeves”的字面量。  
第3步：传递常量池中字符串给string 构造函数。String 对象中存储了指向了常量池中“reeves”的地址。  
第4步：赋值，s 指向String 对象的地址。  

## 例子
```
public void sample4() {
        String str1 = "reeves";
        String str2 = str1.intern();
        System.out.println(str1 == str2); //true
 }
```
String的intern()方法会监测常量池中是否有该字符串的值的字面量，如果有，返回字面量的地址，如果没有，则新建字面量并返回新建字面量的地址。

##  例子
```
public void sample6() {
        String a = "hello2";
        String b = "hello" + 2;
        System.out.println((a == b)); // true
  }
```
"hello"+2在编译期间就已经被优化成"hello2"  ， 因此，均指向常量池中的"hello2"。

## 例子
```
public void sample7() {
        String a = "hello2";
        String b = "hello";
        String c = b + 2;
        System.out.println((a == c)); // false
}
```
有符号引用(c 中的 b)的存在，所以  String c = b + 2;不会在编译期间被优化，不会把b+2当做字面常量来处理的，因此这种方式生成的对象保存在堆上的。因此a和c指向的并不是同一个对象。　

## 
```
public void sample8() {
        String a = "hello2";
        final String b = "hello";
        String c = b + 2;
        System.out.println((a == c)); // false
 }
```
对于被final修饰的变量，对final变量的访问在编译期间都会直接被替代为真实的值。String c = b + 2;在编译期间就会被优化成：String c = "hello" + 2; ->  String c = "hello2";

## 例子
```
public void sample9() {
        String a = "hello2";
        final String b = getHello();
        String c = b + 2;
        System.out.println((a == c)); // false
 }
private String getHello() {
        return "hello";
}
```
虽然将b用final修饰了，但是由于其赋值是通过方法调用返回的，那么它的值只能在运行期间确定，因此a和c指向的不是同一个对象。

## 代码1）和2）的区别是什么？
```
public void sample10() {
        String str1 = "I";
        //str1 += "love"+"java";        1)
        str1 = str1 + "love" + "java";      //2)
 }
```
- 1）的效率比2）的效率要高，1）中的"love"+"java"在编译期间会被优化成"lovejava"，而2）中的不会被优化。  
- 在1）中只进行了一次append操作，而在2）中进行了两次append操作。

## References:
- String 对象  http://rednaxelafx.iteye.com/blog/774673
- Java String直接赋值和new对象的相关问题  https://www.jianshu.com/p/01ad56dd8978
- 探秘Java中String、StringBuilder以及StringBuffer https://www.cnblogs.com/dolphin0520/p/3778589.html