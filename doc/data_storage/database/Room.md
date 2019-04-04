# Room

# Refs

- [[Android]Save data in a local database using Room](https://developer.android.google.cn/training/data-storage/room/index.html)
- [[Android]Room](https://developer.android.google.cn/jetpack/androidx/releases/room)
- [[Android]Defining data using Room entities](https://developer.android.google.cn/training/data-storage/room/defining-data)
- [[Android]Accessing data using Room DAOs](https://developer.android.google.cn/training/data-storage/room/accessing-data)
- [Create views into a database](https://developer.android.google.cn/training/data-storage/room/creating-views)
- [[Android]Room Persistence Library - 7 Pro-tips for Room](https://developer.android.google.cn/topic/libraries/architecture/room)
- [Migrating Room databases](https://developer.android.google.cn/training/data-storage/room/migrating-db-versions)
- [SQLite documentation for rowid tables](https://www.sqlite.org/rowidtable.html)
- [Android FTS性能测试](https://blog.csdn.net/zhanglianyu00/article/details/71158479)
- https://www.jianshu.com/p/29e5e8c75450
- SQLite中SELECT基本形式 https://blog.csdn.net/daxueba/article/details/79848521
- [Androidx和Android support库共存问题解决](https://www.jianshu.com/p/f7a7a8765294)
- https://blog.csdn.net/maxwell_nc/article/details/48766223
- https://blog.csdn.net/mybvc/article/details/8019738
- [Android流行ORM框架性能对比及Room踩坑总结](https://sq.163yun.com/blog/article/177878979956150272)
- http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/1123/8794.html

# Test
```
implementation 'androidx.room:room-runtime:2.0.0'
annotationProcessor "androidx.room:room-compiler:2.0.0"
```

# 1 Room

## 1.1 Room advantage?
- Room provides an abstraction layer over SQLite：可读性强，易
- Room 编译判错，不是运行时判错：更安全
- Room不使用反射：更高效
- Default Room doesn't support database access on the main thread.  
```
Note: 
Room doesn't support database access on the main thread unless you've called
allowMainThreadQueries() on the builder because it might lock the UI for a long period of time.
Asynchronous queries—queries that return instances of LiveData or Flowable—are exempt from this rule
because they asynchronously run the query on a background thread when needed.

```

## 1.2 Why save data in local?   
Still browse content while offline. After device is back online, sync to server.

## 1.3 There are 3 major components in Room:
- Database,Entity,DAO
- DAO = Data Access Objects
- Entity = Table

![Figure 1. Room architecture diagram](https://developer.android.google.cn/images/training/data-storage/room_architecture.png)

## 1.4 `@Entity`	
- JavaBeans conventions in Room
- field is public, provide getter/setter 
- Javabean has an empty constructor,full or partial constructors
```
Caution: Table names in SQLite are case-insensitive.
```

### Primary key
- Each entity must define at least 1 field as a primary key

### Ignore fields

### Provide table search support（FTS）
### Index specific columns

## 1.5 DAO

- A DAO can be either an interface or an abstract class. If it's an abstract class, it can optionally have a constructor that takes a RoomDatabase as its only parameter. 
- Room creates each DAO implementation at compile time.

### @Update
- It uses a query that matches against the primary key of each entity.

### @Delete
- It uses the primary keys to find the entities to delete.

### @Querry
- Each @Query method is verified at compile time, so if there is a problem with the query, a compilation error occurs instead of a runtime failure.
- It gives a warning if only some field names match.
- It gives an error if no field names match.
- 返回的row ID = rowid，并不是插入的行号RecNo
```
db.insert
ERROR: Returns:
the row ID of the newly inserted row, or -1 if an error occurred
```

Support
- Returning subsets of columns
- Support Return LiveData: when database is updated, async LiveData
- Support Return cursor
- Support Reactive queries with RxJava
- Support Querying multiple tables

## 1.6 Room vs other DB Libs
ORLite：
- 反射，对象关系映射数据库

GreenDao
- 对象关系映射数据库

Room：
- 2017 Android Architecture Component 一部分。
- 对象关系映射数据库
- 支持LiveData：动态刷新和绑定组件生命周期

![](https://nos.netease.com/cloud-website-bucket/20180718113642e1a94786-efdd-4953-9ceb-cfd8d58fc1dd.png)

## 1.7 补充：`RecNo` VS `id` VS `rowId`

### RecNo
单调递增，无论数据表怎么变化，只要查询结果的第一条就是1（表头为0），和表没有任何关系

### id
- 单调递增的，如果删除了某一条记录，id还是会继续递增，不会调整，
- id属于数据表的字段，和数据有对应关系，无论怎么查询，id都不会变化。 
- 可以自行设置，或者是数据库自动设置（rowid的最大值+1），到达最大值后不复用已经删除的值，抛异常SQLITE_FULL 。

### rowId
- sqlite中自动维护的一个隐藏的列rowid
- rowid和integer primary key autoincrement的字段有什么区别？  
默认情况下，SQLite会自动存在一个RowID列，从1开始，每添加一条记录+1。  
当设置主键的类型为integer时，查询RowID等于主键  
- 单调递增，直到达到最大值后，复用已经删除的rowid作为新的值 
- 由sqlite自己维护的列.
- 显式指定查询 rowid  
`select rowid,* from table`

- 查询RecNo, rowId，id     
`select rowid,* from table order by id desc`

# 2 ERRORS
## 2.1 `FIXED_ERROR: @Dao error: An entity must have at least 1 field annotated with @PrimaryKey`
Entity对应table，Entity至少有一个主键。If 缺少主键，编译出次错

## 2.2 `FIXED_ERROR:error: You must annotate primary keys with @NonNull. "user" is nullable. SQLite considers this a bug and Room does not allow it. See SQLite docs for details: https://www.sqlite.org/lang_createtable.html`

主键是int，因此This field不能设置@NonNull

## 2.3 Androidx和Android support库共存问题解决
``` 
Manifest merger failed : Attribute application@appComponentFactory value=(android.support.v4.app.CoreComponentFactory) from [com.android.support:support-compat:28.0.0] AndroidManifest.xml:22:18-91
    is also present at [androidx.core:core:1.0.0] AndroidManifest.xml:22:18-86 value=(androidx.core.app.CoreComponentFactory).
    Suggestion: add 'tools:replace="android:appComponentFactory"' to <application> element at AndroidManifest.xml:36:5-364:19 to override.
    
```
降级Room: ->2.0.0

## 2.4 `FIXED_ERROR:Note: Failed to read get kotlin metadata for [Ljava.lang.Object;@5fa74586`	
最新版本有问题，降低版本

## 2.5 `java.lang.IllegalStateException: Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.`

表结构改变了 -> 数据库升级

## 2.6 `FIXED_ERROR: Method returns long[] but it should return one of the following: `void`.If you want to return the list of row ids from the query, your insertion method can receive only 1 parameter.`

@Insert 函数，仅仅参数只有一个时才有返回值，否则为Void

```
@Insert
List<Long> insertScores(Score score1, Score score2);
````
=>
```
@Insert
    void insertScores(Score score1, Score score2);
```

## 2.7 SQLITE_CONSTRAINT_FOEIGNKEY
```
Error Code : 787 (SQLITE_CONSTRAINT_FOEIGNKEY)
    Caused By : Abort due to constraint violation.
    (FOREIGN KEY constraint failed (code 787))

teachDao.insertScores(mMock.buildScore(2, 2, 98), mMock.buildScores_v3());
data stu.stu_id, course.course_id are notexist
    3,1
    3,2
    3,3
```

# 3 PO

## 3.1 Init  RoomDatabase  following singleton design pattern. While app runs in multiple processes, use enableMultiInstanceInvalidation() since Room Version 2.1.0-alpha01

```
Note: 
If your app runs in a single process, you should follow the singleton design pattern when
instantiating an AppDatabase object. Each RoomDatabase instance is fairly expensive, and you rarely
need access to multiple instances within a single process.

If your app runs in multiple processes, include enableMultiInstanceInvalidation() in your database
builder invocation. That way, when you have an instance of AppDatabase in each process, you can
invalidate the shared database file in one process, and this invalidation automatically propagates to
the instances of AppDatabase within other processes.

```
## 3.2 Doesn't use database access on the main thread.

# 4 TBD
- FTS 性能测试
- Migration
- Room PO
- [[Android]Room Persistence Library - 7 Pro-tips for Room](https://developer.android.google.cn/topic/libraries/architecture/room)
- http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/1123/8794.html