# Use static final for constants

Rule:
- static final,instead of static.  

## Bad:
```
static int intVal = 42;
static String strVal = "Hello, world!";

```

The compiler generates a class initializer method, called `<clinit>`, that is executed when the class is first used. The method stores the value 42 into `intVal`, and extracts a reference from the classfile string constant table for strVal. When these values are referenced later on, they are accessed with field lookups.


## Good:
We can improve matters with the "final" keyword:  

```
static final int intVal = 42;
static String strVal = "Hello, world!";
```
- new:新建对象
- bipush 将常量42压入栈bipush 将常量压入栈
- ldc : load constant
- getstatic:获取类的静态字段；
- pustatic:设置类的静态字段；
- invokestatic:调用类的静态方法；
- invokespecial:调用需要特殊处理的实例方法，包括实例初始化方法，私有方法和父类方法
- invokevirtual指令:调用对象的实例方法，根据对象的实际类型进行分派(虚拟机分派)。
- aload_0 : 在非静态方法中，aload_0 表示对this的操作。在static 方法中，aload_0表示对方法的第一参数的操作。


The class no longer requires a <clinit> method, because the constants go into static field initializers in the dex file. Code that refers to intVal will use the integer value 42 directly, and accesses to strVal will use a relatively inexpensive "string constant" instruction instead of a field lookup.

Note: This optimization applies only to primitive types and String constants, not arbitrary reference types. Still, it's good practice to declare constants static final whenever possible.



## References:
- Performance tips  https://developer.android.com/training/articles/perf-tips  

JVM 字节码指令 
- https://www.cnblogs.com/xlyslr/p/5751039.html
- aload_0   https://blog.csdn.net/DViewer/article/details/51138148
- https://www.cnblogs.com/chenqiangjsj/archive/2011/04/03/2004231.html
- http://www.jb51.net/article/36407.htm