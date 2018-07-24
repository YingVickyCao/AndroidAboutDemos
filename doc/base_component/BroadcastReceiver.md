
# Broadcast

## BroadcastReceiver  
- 系统级别的全局监听器     
- 用于监听系统全局的广播消息
- 实现系统中不同组件之间的通信

---
## PO

## Defaullt run on UI Thread.

### prefer using context registration over manifest declaration

### Local broadcasts, use `LocalBroadcastManager.registerReceiver(BroadcastReceiver, IntentFilter)/unregisterReceiver(BroadcastReceiver)`  

### register and unregister the receiver, when you no longer need it or the context is no longer valid.
- onCreate(Bundle) - onDestroy()      
- onResume() - onPause().    // Best

### Do not broadcast sensitive information using an implicit intent. 

### limit the broadcasts that your app receives.

### The namespace for broadcast actions is global.

### Do not start activities from broadcast receivers because the user experience is jarring.

---
## BroadcastReceiver Lifecycle
只有一个 `public void onReceive(Context context, Intent intent) `.  

---
## Explicit intents  VS Implicit intents  
### Explicit intents     
specify which application will satisfy the intent, by supplying either the target app's `package name` or a `fully-qualified component class name`.   
You'll typically use an explicit intent to start a component in your own app, because you know the class name of the **activity** or **service** you want to start. For example, you might start a new activity within your app in response to a user action, or start a service to download a file in the background.  

- => .class   
- => -> Local app's activity/service
- Sending explicit intent
```
// Executed in an Activity, so 'this' is the Context
// The fileUrl is a string URL, such as "http://www.example.com/image.png"
Intent downloadIntent = new Intent(this, DownloadService.class);
downloadIntent.setData(Uri.parse(fileUrl));
startService(downloadIntent);
```
### Implicit intents   
Implicit intents do not name a specific component, but instead declare a general `action` to perform, which allows a component from `another app` to handle it.   

- => action  
- => -> another app's  component
- Sending an implicit intent
```
// Create the text message with a string
Intent sendIntent = new Intent();
sendIntent.setAction(Intent.ACTION_SEND);
sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
sendIntent.setType("text/plain");

// Verify that the intent will resolve to an activity
if (sendIntent.resolveActivity(getPackageManager()) != null) {
    startActivity(sendIntent);
}
```
- Receiving an implicit intent  
To advertise which implicit intents your app can receive, declare one or more intent filters for each of your app components with an `<intent-filter>` element in your `manifest` file. 

```
<activity android:name="ShareActivity">
    <intent-filter>
        <action android:name="android.intent.action.SEND"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <data android:mimeType="text/plain"/>
    </intent-filter>
</activity>

````
## QA: Explicit Broadcast VS Implicit Broadcast（显示广播  VS 隐式广播）?    
-  没有Explicit Broadcast概念。
-  只有Implicit Broadcast概念。  
- 只见看到发送Implicit Broadcast。
- 没有看到发送Explicit Broadcast。  

## Receiving broadcast
- Manifest-declared receivers
- Context-registered receivers


- targetSdkVersion >=26

Sending broadcasts          |Receiving broadcasts       |Receiving broadcasts
---|---|---
targetSdkVersion >=26         |manifest-declared receivers| context-registered receivers
Send an Implicit Broadcast  |NO.                        | Yes


- targetSdkVersion <=25

Sending broadcasts          |Receiving broadcasts       |Receiving broadcasts
---|---|---
targetSdkVersion <=25         |manifest-declared receivers| context-registered receivers
Send an Implicit Broadcast  |Yes.                        | Yes


## Manifest-declared receivers
```
<!-- ERROR:
        BroadcastQueue: Background execution not allowed: receiving Intent { act=com.hades.android.example.android_about_demos.app_component.broadcast.SimpleReceiver.ONE flg=0x10 (has extras) }
        to com.hades.android.example.android_about_demos/.app_component.broadcast.SimpleReceiver
        -->
        <receiver android:name=".app_component.broadcast.SimpleReceiver">
            <!-- If no intent-filter, can not receive anything. -->
            <intent-filter>
                <!-- 指定该BroadcastReceiver所响应的Intent的Action -->
                <action android:name="com.hades.android.example.android_about_demos.app_component.broadcast.SimpleReceiver.ONE" />
            </intent-filter>
        </receiver>

```
- The system package manager registers the receiver when the app is installed. The receiver then becomes a separate entry point into your app which means that the system can start the app and deliver the broadcast if the app is not currently running.

- The system creates a new BroadcastReceiver component object to handle each broadcast that it receives. This object is valid only for the duration of the call to onReceive(Context, Intent). Once your code returns from this method, the system considers the component no longer active.

- ***When `targetSdkVersion >=26`, Manifest-declared receivers can not implicit broadcasts, except for a few implicit broadcasts  (broadcasts that do not target your app specifically)  that are [exempted from that restriction](https://developer.android.google.cn/guide/components/broadcast-exceptions).***
- If no ` <intent-filter>`, cannot receive anything.	

---

## Context-registered receivers

```
IntentFilter filter = new IntentFilter();
filter.addAction(ACTION_ONE);
// QA：Receiving an explicit intent
registerReceiver(mSimpleReceiver, filter);
```

- Context-registered receivers receive broadcasts as long as their registering context is valid.       
For an example, if you register within an Activity context, you receive broadcasts as long as the activity is not destroyed. If you register with the Application context, you receive broadcasts as long as the app is running.    

- To stop receiving broadcasts, call unregisterReceiver(android.content.BroadcastReceiver).   
Be sure to unregister the receiver when you no longer need it or the context is no longer valid.     

Be mindful of where you register and unregister the receiver, for example, if you register a receiver in onCreate(Bundle) using the activity's context, you should unregister it in onDestroy() to prevent leaking the receiver out of the activity context.   
If you register a receiver in onResume(), you should unregister it in onPause()to prevent registering it multiple times (If you don't want to receive broadcasts when paused, and this can cut down on unnecessary system overhead).   

Do not unregister in onSaveInstanceState(Bundle), because this isn't called if the user moves back in the history stack.     

=> onCreate(Bundle) - onDestroy()      
=> onResume() - onPause().    // Best

![Figure 1. A simplified illustration of the activity lifecycle.](https://developer.android.google.cn/images/activity_lifecycle.png)  

--- 
## system broadcasts
### Changes to system broadcasts
- Android >= 7.0(25), cannot send `ACTION_NEW_PICTURE(添加新图片)`, `ACTION_NEW_VIDEO(添加新视频 )` broadcast.
- `targetSdkVersion >=Andrpod O(8.0,26)`, `CONNECTIVITY_ACTION(网络连接与断开状态的变化)`, only `context-registered receivers`, cannot  `manifest-declared receivers` , except for a few implicit broadcasts that are [exempted from that restriction](https://developer.android.google.cn/guide/components/broadcast-exceptions.html).

---

## Effects on process state
- Calling `onReceive()`, host process = foreground process. -> 除极端内存压力外，系统保持运行。
- Only `manifest-declared receivers`, returns from ` onReceive()` , host process = low-priority process -> system memory under process -> kill host process.

```
  /*
    07-24 18:22:53.475 15540-15540/app D/SimpleReceiver: onReceive,[thread =2,main],------->
    07-24 18:22:53.475 15540-15540/app D/SimpleReceiver: onReceive,[thread =2,main],hashCode=180628956
    07-24 18:22:53.475 15540-15540/app D/SimpleReceiver: onReceive: action=com.hades.android.example.android_about_demos.app_component.broadcast.SimpleReceiver.ONE,info=implicit Broadcast
    07-24 18:22:53.485 15540-15540/app D/SimpleReceiver: startLongRunningBackgroundThreads,[thread =2,main],------->
    07-24 18:22:53.486 15540-15540/app D/SimpleReceiver: startLongRunningBackgroundThreads,[thread =2,main],<-------
    07-24 18:22:53.475 15540-15540/app D/SimpleReceiver: onReceive,[thread =2,main],<-------
    07-24 18:22:54.488 15540-15966/app D/SimpleReceiver: startLongRunningBackgroundThreads,[thread =34165,Thread-7],count=1
    07-24 18:22:55.489 15540-15966/app D/SimpleReceiver: startLongRunningBackgroundThreads,[thread =34165,Thread-7],count=2
    ...
     */
    private void startLongRunningBackgroundThreads() {
        LogHelper.printThreadInfo(TAG, "startLongRunningBackgroundThreads", "------->");
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for (; ; ) {
                    count++;
                    try {
                        Thread.sleep(1000);
                        LogHelper.printThreadInfo(TAG, "startLongRunningBackgroundThreads", "count=" + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        LogHelper.printThreadInfo(TAG, "startLongRunningBackgroundThreads", "<-------");
    }
```
- For this reason, you should not start long running background threads from a broadcast receiver.

### How to start long running background threads from a broadcast receiver?  
- uses `goAsync()` to flag that it needs more time to finish after onReceive() is complete.    
- schedule a `JobService` from the receiver using the `JobScheduler`.

--------------------------------------------------------------------------------
## Sending broadcasts
- sendOrderedBroadcast(Intent, String)
- sendBroadcast(Intent)
- LocalBroadcastManager.sendBroadcast() 

If you don't need to send broadcasts across apps, use local broadcasts.
The implementation is much more efficient (no interprocess communication needed) and you don't need to worry about any security issues related to other apps being able to receive or send your broadcasts.
--------------------------------------------------------------------------------

## Security considerations and best practices
### Do not broadcast sensitive information using an implicit intent.   

The information can be read by any app that registers to receive the broadcast. There are three ways to control who can receive your broadcasts:   
- You can specify a permission when sending a broadcast.  

- In Android 4.0 and higher, you can specify a package with setPackage(String) when sending a broadcast. The system restricts the broadcast to the set of apps that match the package.  
- You can send local broadcasts with LocalBroadcastManager.  

### limit the broadcasts that your app receives
  When you register a receiver, any app can send potentially malicious broadcasts to your app's receiver. There are three ways to limit the broadcasts that your app receives:
  
- You can specify a permission when registering a broadcast receiver.  
- For manifest-declared receivers, you can set the android:exported attribute to "false" in the manifest. The receiver does not receive broadcasts from sources outside of the app.  
- You can limit yourself to only local broadcasts with LocalBroadcastManager.  

### The namespace for broadcast actions is global. 
Make sure that action names and other strings are written in a namespace you own, or else you may inadvertently conflict with other apps.  

### Do not start activities from broadcast receivers because the user experience is jarring; especially if there is more than one receiver. Instead, consider displaying a notification.

---

## `LocalBroadcastManager` 
- app级别的局部监听器
- Local broadcasts 
1. single process app.
2. Only Itself action, not system action.
3. comminute with other components only in this app.     

### Why?
- much more efficient (no interprocess communication needed) ,
- don't need to worry about any security issues related to other apps being able to receive or send your broadcasts.  

### Usage
- Only context-registered receivers.
- LocalBroadcastManager注册的广播，发送广播的时候must 使用LocalBroadcastManager.sendBroadcast(intent); 否则接收不到广播。
- `LocalBroadcastManager.getInstance(this).registerReceiver(mSimpleReceiver, filter);`
- `LocalBroadcastManager.getInstance(this).sendBroadcast(intent);`

### How?
- Conext = ApplicationContext  =>   避免了当前Context的内存泄漏
- LocalBroadcastManager instance = single instance
 - LocalBroadcastManager 含有 Handler，cache BroadcastReceiver 对象。利用Handler依次直接调用onReceive方法。
 

### LocalBroadcastManager VS Context style
- LocalBroadcastManager:Handler,No ICP.
- Context style:Binder, ICP

---
## QA: Broadcast `onReceive()`运行在哪个线程？主线程。

## References
- Broadcasts overview   https://developer.android.google.cn/guide/components/broadcasts
- Intents and Intent Filters   https://developer.android.google.cn/guide/components/intents-filters
- Application Fundamentals   https://developer.android.google.cn/guide/components/fundamentals
- Activity https://developer.android.google.cn/reference/android/app/Activity
- Understand the Activity Lifecycle https://developer.android.google.cn/guide/components/activities/activity-lifecycle
- Android O and the Implicit Broadcast Ban  https://commonsware.com/blog/2017/04/11/android-o-implicit-broadcast-ban.html