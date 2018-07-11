# `ContentProvider` - 数据共享标准
- `DictUserActivity.java`
- `DictActivity.java`

- `SMSActivity.java`  
- `ContactContentProviderActivity.java`  
- `MediaActivity.java`  

## 一个应用程序操作另一应用程序数据的方式：    
### 直接操作
- SharedPreferences，Deprecated  >=4.2   
- 文件，Deprecated  >=4.2   
- 数据库，Deprecated  >=4.2   

### 标准 API
- ContentProvider    

## Android 为什么提供了ContentProvider？   
- 为了在应用程序之间交换数据。    
- ContentProvider是不同应用程序直接进行数据交换的标准 API。  

## ContentProvider  
- 一个应用通过`ContentProvider` 把数据操作接口暴露给其他应用使用，其他应用通过`ContentResolver`来操作暴露的数据。  
- 一个应用通过`ContentProvider`把数据操作接口暴露给其他应用使用，不管该应用是否启动启动，其他应用程序都可以通过该接口操作该应用的内部数据，包括`CRUD`-`增 (insert)、删(delete)、改(update)、查(query)`等。  
- `ContentProvider`是单例模式。当多个应用通过`ContentResolver`来操作`ContentProvider`提供的数据时，`ContentResolver`调用数据操作将会委托给同一个`ContentProvider`处理。  
- `ContentProvider`的 CRUD 方法，默认运行在创建`ContentProvider`的线程池中其中一个线程。       
`DictContentProvider.java`      
`insert,thread =180,Binder:3178_2`       
-  `ContentProvider`所操作的数据，部分来源于是数据库。也可以来源于 XML、网络、文件等存储方式。  
 
### ContentProvider 常见方法  


### register provider in Manifest   
```
<provider
            android:name=".app_component.cp.dict.DictContentProvider"
            android:authorities="com.hades.android.example.android_about_demos.app_component.cp.dict.DictContentProvider"
            android:exported="true" />
```

### ContentProvider 的声明周期  
ContentProvider  only `onCreate` - only called once。  

### 监听ContentProvider的数据变化  
只要 CRUD 导致的ContentProvider数据变化，调用`notifyChange`，用于通知所有注册在该 Uri 上的监听者：该ContentProvider所共享的数据发生了改变。  

`DictContentProvider.java`
```
// 通知数据已经改变
getContext().getContentResolver().notifyChange(wordUri, null)
```
## ContentResolver
 ContentResolver有对应的`CRUD`- 赠、删、改、查方法。 

## Uri 类比 URL  
### URL  
`http://www.abc.com:8080/index.php`  
- `http://`:协议  
- `www.abc.com`：域名。指定访问的网站。域名是固定的。  
- `index.php`：网站资源部分。当访问者需要访问不同资源时，此部分动态改变。  

### Uri
`Dict.java`
content://com.hades.android.example.android_about_demos.app_component.cp.DictContentProvider/words    
- `content://`:ContentProvider 的默认协议  
- `AUTHORITY`:指定ContentProvider 的唯一 ID，系统通过此 ID 来找到对应的ContentProvider。
- `words`：资源部分，或，数据部分。此部分动态改变。

### Uri 的形式
- `content://com.hades.android.example.android_about_demos.app_component.cp.DictContentProvider/words`  // 全部数据  
- `content://com.hades.android.example.android_about_demos.app_component.cp.DictContentProvider/word`   // 1条数据  
- `content://com.hades.android.example.android_about_demos.app_component.cp.DictContentProvider/word/2/word` // word数据中ID 为2记录  
- `content://com.hades.android.example.android_about_demos.app_component.cp.DictContentProvider/word/detail/` // word数据中detail  

## `ContentProvider` 与 `ContentResolver`的关系：     
- 'Uri'是 `ContentProvider` 与 `ContentResolver`进行数据交换的标识。    
- 应用 A 通过`ContentResolver`执行CRUD操作（必须指定Uri），Android 系统会根据`Uri`找到对应的`ContentProvider`（通常位于应用 B）。    

![ContentProvider、Uri、ContentResolver 的关系](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/app_component/cp/cp_cr_and_uri.png)

## `UriMatcher`     
`ContentProvider`使用`UriMatcher` 对 Uri 参数进行判断。只有合法的 Uri 才能操作。  

### `UriMatcher`  methods   

![UriMatcher](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/app_component/cp/UriMatcher.png)

`Dict.java`
- `content://com.hades.android.example.android_about_demos.app_component.cp.dict.DictContentProvider/words`
- `content://com.hades.android.example.android_about_demos.app_component.cp.dict.DictContentProvider/word`

`DictContentProvider.java`   
```
Dict.AUTHORITY = com.hades.android.example.android_about_demos.app_component.cp.dict.DictContentProvider 

private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);`
matcher.addURI(Dict.AUTHORITY, "words", 1);
matcher.addURI(Dict.AUTHORITY, "word/#", 2); // # 为通配符

matcher.match("content://com.hades.android.example.android_about_demos.app_component.cp.dict.DictContentProvider/words");    // => 返回标识码 1
matcher.match("content://com.hades.android.example.android_about_demos.app_component.cp.dict.DictContentProvider/word/2");  // => 返回标识码 2
matcher.match("content://com.hades.android.example.android_about_demos.app_component.cp.dict.DictContentProvider/word/10"); // => 返回标识码 2
```
## `ContentUris`    
操作数据库的 Uri 字符串的工具类    

![ContentUris](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/app_component/cp/ContentUris.png)

## 使用操作系统提供的 `ContentProvider`   
### 联系人 - `ContactContentProviderActivity.java`  
![联系人](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/app_component/cp/system_cp_contact.png)

- 多媒体 - `MediaActivity.java`    
![多媒体](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/app_component/cp/system_cp_media.png)