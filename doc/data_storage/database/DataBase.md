
# DataBase

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
---

# `SQLiteDatabase.java`

- `CRUD`= create, retrieve,update, delete  
create = insert()  
retrieve = rawQuery/query()  
update = update()  
delete = delete()  

- SQLiteDatabase 帮助类 SQLiteOpenHelper

## Insert
```
// @1. Insert the new row, returning the primary key value of the new row
// @2: Tells the framework what to do in the event that the ContentValues is empty
public long insert(String table, String nullColumnHack, ContentValues values) 
```
## Query

`Cursor rawQuery(String sql, String[] selectionArgs)`

```
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
Android自己封装的查询API.    
推荐。封装了拼接，简化了拼接，不易出错。    

- Cursor.close()  
call close() on the cursor to release its resources

## Update 
`int update(String table, ContentValues values, String whereClause, String[] whereArgs)`

## Delete
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
---
# SQLiteOpenHelper
## `void onCreate(SQLiteDatabase db)`
- Thread = = Thread runing `getWritableDatabase()`/`getReadableDatabase()`
- Called when the database is created for the first time

- ERROR:java.lang.IllegalStateException: getDatabase called recursively  
https://blog.csdn.net/adayabetter/article/details/44516217

```
public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: " + LogHelper.getThreadInfo()); // ,[thread =2,main]
        db.execSQL(SQL_CREATE_ENTRIES);

        // FIX_ERROR:java.lang.IllegalStateException: getDatabase called recursively
        // Use db instread of db2
        // SQLiteDatabase db2 = getReadableDatabase();
  
    }
```

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

# close()
- called in onDestroy() of Activity/Fragment/Application subclass

# `Cursor.java`
## cursor.getColumnIndexOrThrow
## cursor.getColumnIndex
## cursor.getInt(int) // int: colum index. >=0

Ref:
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