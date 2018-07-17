# IntentService
## Service本身存在的问题：
- Service不会专门启动一个单独的线程。
- Service与它所在的应用位于同一个进程中。

## android stopSelf() VS stopSelf(int id) VS stoService()
- stopSelf() or stopService():   
 Once requested to stop with stopSelf() or stopService(), the system destroys the service as soon as possible.  
- stopSelf(int id) :    
 the ID will not match and the service will not stop.
- `public int onStartCommand(@Nullable Intent intent, int flags, int startId)`    
startId:系统自动生成。每次调用`onStartCommand`自动加1.

- https://stackoverflow.com/questions/22485298/stopself-vs-stopselfint-vs-stopserviceintent  
- https://blog.csdn.net/mingli198611/article/details/8782772
- https://blog.csdn.net/Liu_yunzhao/article/details/78145569?locationNum=8&fps=1

## IntentService VS Service
使用队列管理请求Intent，在同一个子线程中执行耗时任务。-> IntentService不会阻塞主线程。

## IntentService 如何处理Intent？
- IntentService在创建时会一个HandlerThread，以及跟该Thread 的Loop关联的ServiceHandler。    
=> `UI Thread -> Thread`
- 当客户端通过Intent来启动StartService时，IntentService将Intent加入队列。
- IntentService会依次在该线程中按次序处理队列中的Intent。
- 该线程保证同一时刻只处理一个Intent。
- IntentService不会阻塞主线程。

### Demo：mock heavy task = 5s，连续start IntentService 4次。  
- `TestIntentServiceActivity.java`
- `MyIntentService.java`

```
07-17 08:49:12.490 7056-7056/app D/MyIntentService: onStartCommand,intent=Intent { cmp=com.hades.android.example.android_about_demos/.app_component.service.startservice.intent_service.MyIntentService },flags=0,startId=1,thread =2,main
07-17 08:49:12.490 7056-8373/app D/MyIntentService: onHandleIntent,thread =31065,IntentService[MyIntentService]
07-17 08:49:13.028 7056-7056/app D/MyIntentService: onStartCommand,intent=Intent { cmp=com.hades.android.example.android_about_demos/.app_component.service.startservice.intent_service.MyIntentService },flags=0,startId=2,thread =2,main
07-17 08:49:13.438 7056-7056/app D/MyIntentService: onStartCommand,intent=Intent { cmp=com.hades.android.example.android_about_demos/.app_component.service.startservice.intent_service.MyIntentService },flags=0,startId=3,thread =2,main
07-17 08:49:13.748 7056-7056/app D/MyIntentService: onStartCommand,intent=Intent { cmp=com.hades.android.example.android_about_demos/.app_component.service.startservice.intent_service.MyIntentService },flags=0,startId=4,thread =2,main
07-17 08:49:17.491 7056-8373/app D/MyIntentService: onHandleIntent: finish task

07-17 08:49:17.502 7056-8373/app D/MyIntentService: onHandleIntent,thread =31065,IntentService[MyIntentService]
07-17 08:49:22.507 7056-8373/app D/MyIntentService: onHandleIntent: finish task

07-17 08:49:22.513 7056-8373/app D/MyIntentService: onHandleIntent,thread =31065,IntentService[MyIntentService]
07-17 08:49:27.514 7056-8373/app D/MyIntentService: onHandleIntent: finish task

07-17 08:49:27.518 7056-8373/app D/MyIntentService: onHandleIntent,thread =31065,IntentService[MyIntentService]
07-17 08:49:32.519 7056-8373/app D/MyIntentService: onHandleIntent: finish task
07-17 08:49:32.557 7056-7056/app D/MyIntentService: onDestroy: 
```
## IntentService 的特征
- 创建单独的一个子线程处理所有Intent请求。
- 在`onHandleIntent()`中处理Intent请求。
- 当所有请求结束后，自动调用`stopItself()`停住。
- 默认视线`onBind()`

```
@Override
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }
```
- 默认实现`onStartCommand()`方法提供了默认实现：将请求Intent添加到队列。    
- 重写"`onHandleIntent()`"，不重写`onBind()`,`onStartCommand()`   