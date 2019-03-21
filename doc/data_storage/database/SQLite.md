# SQLite

- Test Android Device:`Android 8.0`
- `android.xlsx` - DB page  
- `TestSQLiteActivity.java`  
    
# When use  DB?
Saving data to a database is ideal for repeating or structured data

# Store data
## How to store bitmap or large file?
- save bitmap or large -> file, store path in db.

---

# SQLite
- Android Use sqlite3
- 跨平台
- Android 运行时集成了SQLite，每个应用都可以使用
- 支持基本SQL语法
- 轻量级数据库：  
1）少量量数据  
2）一个数据库文件  
- 支持ACID关系型数据库管理系统
- 开源
- 嵌入的数据库引擎
- 安全性  
通过数据库级的共享锁来独立处理事务。对于同一个数据库，多个进程可以同时读，但只能有一个写。
- 事务处理

## SQLite VS Other SQL database
**Other SQL database:**
- Most SQL database engines uses static, rigid typing. 
- With static typing, the datatype of a value is determined by its container - the particular column in which the value is stored.

**SQLite:**
- SQLite uses a more general dynamic type system
In SQLite, the datatype of a value is associated with the value itself, not with its container.
- The dynamic type system of SQLite is backwards compatible with the more common static type systems of other database engines in the sense 
 - the dynamic typing in SQLite allows it to do things which are not possible in traditional rigidly typed databases.

## SQLite Storage Classes and Datatypes
### Storage Classes
Five：   
NULL,INTEGER,REAL,TEXT,BLOB

- REAL: float point value
- A storage class:for store in disk

## Database debuggin
- sqlite3 is in SDK dir
- use adb shell to start sqlite3

### Datatypes
- a datatype: for memory processing   
- When load into memory, storage class  =>(max size)   datatype

### Boolean Datatype    
SQLite does not have a separate Boolean storage class.    
=> INTEGER:0 (false) and 1 (true)

### Date and Time Datatype   
SQLite does not have a storage class set aside for storing dates and/or times.  
=> INTEGER,REAL,TEXT

## Android Access SQLite databases API
***highly recommend using Room instead of SQLite***

### 1. SQLite: Depressed
 ***android.database.sqlite***
 
- time consuming
- no compile-time verification of raw SQL querie

### 2. Room：highly recommend
- Easy use：object-mapping abstraction layer
- compile-time verification of raw SQL querie

---

# android.database vs android.database.sqlite
## android.database
explore data returned through a content provider.

## android.database.sqlite
manage private databases With / Not with Content Providers 

---

# Where is SQLite batabase  file?
 `/data/user/0/<package_name>/databases/<database_name>.db`  
- private   
Android stores your database in your app's private folder. 
- secure  
Your data is secure, because by default this area is not accessible to other apps or the user.

---

# `SQLiteDatabase.java`

- SQLiteDatabase的帮助类 SQLiteOpenHelper

## `CRUD`
`CRUD` = create, retrieve,update, delete  

create = insert()  
retrieve = rawQuery/query()  
update = update()  
delete = delete()  

### Insert
```
// @1. Insert the new row, returning the primary key value of the new row
// @2: Tells the framework what to do in the event that the ContentValues is empty
public long insert(String table, String nullColumnHack, ContentValues values) 
```
### Query

```
Cursor rawQuery(String sql, String[] selectionArgs)


// @2: The array of columns to return (pass null to get all)
// @3 selection:The columns for the WHERE clause
// @4 selectionArgs:The values for the WHERE clause
Cursor query(String table, String[] columns, String selection,  String[] selectionArgs, String groupBy, String having, String orderBy) 
```

- WHERE = selection and selectionArgs
- rawQuery vs query  
    rawQuery:   
    直接使用SQL语句进行查询  
    不推荐。拼接时容易拼错  
    
    query:   
    Android封装的查询API.    
    推荐。封装了拼接，简化了拼接，不易出错。  

    PS:   
    网上评测：rawQuery性能效率比query要快不少。   
    实际测试：单表的10000条/100000条 SearchAll/FuzzySearch，使用时间几乎一样.=>不进行该条优化   

- Cursor.close()  
call close() on the cursor to release its resources

#### Query total row num?  
Use: `"SELECT count(*) FROM table_name"`    
Not Use: `"SELECT * FROM table_name"` 

### Update 
`int update(String table, ContentValues values, String whereClause, String[] whereArgs)`

### Delete
`int delete(String table, String whereClause, String[] whereArgs) `

## Link 
`CRUD` 操作中`RUD`
 
- Link =      
等于

- Like ?  
模糊查询

```
SQL模糊查询，使用like比较字，加上SQL里的通配符，请参考以下：
1、LIKE'Mc%' 将搜索以字母 Mc 开头的所有字符串（如 McBadden）。
2、LIKE'%inger' 将搜索以字母 inger 结尾的所有字符串（如 Ringer、Stringer）。
3、LIKE'%en%' 将搜索在任何位置包含字母 en 的所有字符串（如 Bennet、Green、McBadden）。
4、LIKE'_heryl' 将搜索以字母 heryl 结尾的所有六个字母的名称（如 Cheryl、Sheryl）。
5、LIKE'[CK]ars[eo]n' 将搜索下列字符串：Carsen、Karsen、Carson 和 Karson（如 Carson）。
6、LIKE'[M-Z]inger' 将搜索以字符串 inger 结尾、以从 M 到 Z 的任何单个字母开头的所有名称（如 Ringer）。
7、LIKE'M[^c]%' 将搜索以字母 M 开头，并且第二个字母不是 c 的所有名称（如MacFeather）。
```
## Sqlite Transaction 事务处理
```
beginTransaction() // 开启一个事务
setTransactionSuccessful() // 设置事务的标志为成功
endTransaction() // 结束事务. 检查事务的标志是否为成功? Yes则提交事务，No则回滚事务。
```
### When use Transaction?
- 批量的向Sqlite中插入大量数据.

单独的使用添加方法导致应用响应缓慢.   
原因：sqlite插入数据的时候默认一条语句就是一个事务，有多少条数据就有多少次磁盘操作。如初始8000条记录也就是要8000次读写磁盘操作。同时也是为了保证数据的一致性，避免出现数据缺失等情况。

- 多个步骤要么同时成功,要么同时失败   
经典例子：  
升级数据库时，删除旧数据和添加新数据的操作必须一起完成，否则就还要继续保留原来的旧数据。

```
try{
    db.beginTransaction();
    db.execSQL(update_sql1);
    db.execSQL(insert_sql2);
    db.setTransactionSuccessful();
}finally{
    db.endTransaction();
    db.close();
}

```
## execSQL
```
void execSQL(String sql) throws SQLException
void execSQL(String sql, Object[] bindArgs) throws SQLException
```
- Execute a single SQL statement that is NOT a SELECT/INSERT/UPDATE/DELETE
- execSQL vs CRUD？   
    使用 Transaction 后，execSQL与CRUD的性能差不多，而CRUD的可读性和可维护性更高。  
    So：  
    Use Transaction + CRUD instead of Transaction + execSQL

    测试结论：    
    `android.xlsx` - DB page  
    `TestSQLiteActivity.java`

## Create table with PRIMARY KEY AUTOINCREMENT
```
Case1:
insert key=1
insert key=2
Delete all records in this table.
insert key=3, not 1. // DB record it

Case2:
insert key=1
insert key=2
Delete the DB file.
insert key=1
```
---
# SQLiteOpenHelper
## `void onCreate(SQLiteDatabase db)`
- Thread = = Thread runing `getWritableDatabase()`/`getReadableDatabase()`
- Called when the database is created for the first time

## `void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) `

### How to update DB?
If change the database schema, must increment the database version. Then auto call  `onUpgrade`
```
public static final int DATABASE_VERSION = 1;  => 2
void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is to simply to discard the data and start over
        Log.d(TAG, "onUpgrade: oldVersion=" + oldVersion + ",newVersion=" + newVersion);
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
```

## `void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)`

## `getWritableDatabase()`/ `getReadableDatabase()`
- can be long-running => called in background thread.

- 同一个/不同Context实例，每次通过SQLiteOpenHelper..getWritableDatabase() / getReadableDatabase()拿到的SQLiteDatabase 同一个实例吗?  
    1 SQLiteOpenHelper保存的SQLiteDatabase实例存在，且满足判定条件时，才不会重新=.  
    2 SQLiteOpenHelper 不是单例  

- `getWritableDatabase()` vs  `getReadableDatabase()`  
    getReadableDatabase:   
    First 以读写方式打开DB.If 磁盘空间满了，重新尝试以只读方式打开DB。  
    
    getWritableDatabase:  
    直接以读写方式打开DB. If 磁盘空间满了，直接报错。

- 根据DB版本号,auto invork onDowngrade/onUpgrade to downgrade/upgrade db
```
SQLiteDatabase getDatabaseLocked(boolean writable){
    ...
    final File filePath = mContext.getDatabasePath(mName);
    SQLiteDatabase.OpenParams params = mOpenParamsBuilder.build();
    try {
        db = SQLiteDatabase.openDatabase(filePath, params);
        // Keep pre-O-MR1 behavior by resetting file permissions to 660
        setFilePermissionsForDb(filePath.getPath());
    } catch (SQLException ex) {
        if (writable) {
            throw ex;
        }
        Log.e(TAG, "Couldn't open " + mName
                + " for writing (will try read-only):", ex);
        params = params.toBuilder().addOpenFlags(SQLiteDatabase.OPEN_READONLY).build();
        db = SQLiteDatabase.openDatabase(filePath, params);
    }
    
    ...
    
    if (version > mNewVersion) {
        onDowngrade(db, version, mNewVersion);
    } else {
        onUpgrade(db, version, mNewVersion);
    }
    ...
}
```

## close()
- called in onDestroy() of Activity/Fragment/Application subclass

# `Cursor.java`
## cursor.getColumnIndexOrThrow
## cursor.getColumnIndex
## cursor.getInt(int) // int: colum index. >=0

# ERROR
## FIXED_ERROR:`java.lang.IllegalStateException: getDatabase called recursively`  
https://blog.csdn.net/adayabetter/article/details/44516217

```
public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: " + LogHelper.getThreadInfo()); // ,[thread =2,main]
        db.execSQL(SQL_CREATE_ENTRIES);

        // Use db instread of db2
        // SQLiteDatabase db2 = getReadableDatabase();
  
    }
```

## FIXED_ERROR: `java.lang.NullPointerException: Attempt to invoke virtual method 'android.database.sqlite.SQLiteDatabase android.content.Context.openOrCreateDatabase(...)` 

Reason:  
Context is prepared well until onCreate() finished.

```
public class TestSQLiteActivity extends Activity {

//  private FeedSQLiteOpenHelper dbHelper = new FeedSQLiteOpenHelper(getContext());
    private FeedSQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new FeedSQLiteOpenHelper(this);
    }
```
 
## FIXED_ERROR: `SQLiteDatabase: Error inserting _id=1 col2=City col3=1 android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: table1._id (code 1555)`

Reason:  
插入数据时，主键重复.   
正确的做法：插入数据时，不插入主键，主键为自动

## CursorWindow: Window is full: requested allocation 404 bytes, free space 321 bytes, window size 2097152 bytes
100000条的单表：无条件查询时出现此wrong。   
一般开发中会加很多条件，不会一次性查询这么多条数据。

## FIXED_ERROR:android.database.sqlite.SQLiteException: no such column: A (code 1): , while compiling: UPDATE table1 SET col2= 1553048517967 WHERE col2=A

Reason:  
String 在直接拼接时要用单引号

```
// String sql = "UPDATE table1 SET col2= 1553048517967 WHERE col2=A "; // error

// Way1:
String sql = "UPDATE table1 SET col2= '1553048517967' WHERE col2='A' "; // ok
db.execSQL(sql);

// Way2:
db.execSQL("UPDATE table1  SET col2=?  WHERE col2 = ?", new Object[]{String.valueOf(System.currentTimeMillis()), "A"}); // ok
```

## FIXED_ERROR: android.database.sqlite.SQLiteException: no such table: news (code 1)
在插入数据时 not 建立 table news

## java.lang.IllegalStateException: attempt to re-open an already-closed object: SQLiteQuery: SELECT * FROM table1

```
error use:
Cusor.close();
Cusor.get();
```

## java.lang.IllegalStateException: attempt to re-open an already-closed object: SQLiteDatabase: /data/user/0/com.hades.example.android/databases/FeedReader.db

```
error use:
SQLiteDatabase.close()
SQLiteDatabase db = dbHelper.getWritableDatabase()
```
# Refs
- [Room](https://developer.android.google.cn/training/data-storage/room)
- [Save data using SQLite](https://developer.android.google.cn/training/data-storage/sqlite.html)
- [Data and file storage overview-Database](https://developer.android.google.cn/guide/topics/data/data-storage#db)
- [android.database](https://developer.android.google.cn/reference/android/database/package-summary)
- [android.database.sqlite](https://developer.android.google.cn/reference/android/database/sqlite/package-summary)
- [Datatypes In SQLite Version 3](https://www.sqlite.org/datatype3.html)
- [Command Line Shell For SQLite](https://www.sqlite.org/cli.html)
- https://blog.csdn.net/justwanttofly/article/details/78912701
- https://blog.csdn.net/fantianheyey/article/details/9199235
- https://blog.csdn.net/qq_42672839/article/details/81584172
- https://blog.csdn.net/adayabetter/article/details/44516217
- [SQLite事务、升级数据库](https://www.cnblogs.com/orlion/p/5350683.html)
- [SQLite数据库的增删改查和事务(transaction)调用](https://www.cnblogs.com/amosli/p/3784998.html)