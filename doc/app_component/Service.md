## Service
- Device = android SangSung S8
- Service is background task

## Activity VS Service  
同：  
- Service是四大组件中与Activity最相似的组件，都是可执行的程序。      
- 一旦Service被启动起来，与Activity一样，它有自己的生命周期。  
- 创建、配置Service与创建、配置相似。  
- Service与Activity都是从Context继承，均可调用Context中`getResources()`/`getContentResolver()`.
=> BroadcastReceiver  = 全局的事件监听器

异：
- Service一直在后台运行，它没有用户界面。
- Unlike the activity lifecycle callback methods, you are not required to call the superclass implementation of these callback methods.

## Activity and Service的选择标准？
界面／交互-> Activity，Or -> Service。

## Types of Service
### 分类1：Foreground or Background
- Foreground   
A foreground service performs some operation that is noticeable to the user. For example, an audio app would use a foreground service to play an audio track. Foreground services must display a Notification. Foreground services continue running even when the user isn't interacting with the app.

- Background  
A background service performs an operation that isn't directly noticed by the user. For example, if an app used a service to compact its storage, that would usually be a background service.  
Note: If your app targets API level 26 or higher, the system imposes [restrictions on running background services](https://developer.android.google.cn/about/versions/oreo/background) when the app itself isn't in the foreground. In most cases like this, your app should use a [scheduled job](https://developer.android.google.cn/topic/performance/scheduling.html) instead.

### 分类2：Unbounded or Bounded
- Unbounded service  = StartService =  Foreground + Background  
- Bounded service   = BoundService  

### 分类3：Local service and Remote service

## The service lifecycle. 
![The service lifecycle](https://developer.android.google.cn/images/service_lifecycle.png)

- onCreate()  - Service 第一次被创建
- onDestroy() - 关闭之前被回调

-  `onStartCommand()` - `每次`客户端调用`startService(Intent)`方法启动该Service时被回调。

- onBind()
- `onUnbind()` - 当该Service上`所有`客户端`断开`链接时被回调

## Unbounded service VS  Bounded service
- Unbounded service 与访问者之间没有太多联系，两者分开。因此，Service和访问者之间无法进行通信、交换数据。  
=> `BroadcastResolver`
- Bounded service 与访问者之间有联系。因此，Service和访问者之间通过IBinder对象进行通信、交换数据。 
=> `IBinder`

---

# Unbounded Service 
## start and stop service
- onStartCommand／onCreate（）定义业务代码。
- 调用startService()启动Service。   
若Service没有创建，则创建service - onCreate，然后并启动 - onStartCommand
- 调用stopService()停止Service。

```
// case1: 1 start , 1 stop
D/StartServiceTest1Activity: start: startService
D/FirstService: onCreate
D/FirstService: onStartCommand
D/StartServiceTest1Activity: stop: stopService
D/FirstService: onDestroy
```
## start 和stop时，是否需要同一个Intent？不需要。
```
// case1: 1 start , 1 stop
D/StartServiceTest1Activity: start: startService
D/FirstService: onCreate
D/FirstService: onStartCommand
D/StartServiceTest1Activity: stop: stopService
D/FirstService: onDestroy
```
```
// case2: 1 start -> 2 stop
D/StartServiceTest1Activity: start: startService
D/FirstService: onCreate
D/FirstService: onStartCommand
D/StartServiceTest1Activity: jump: startActivity
D/StartServiceTest2Activity: stop: stopService
D/FirstService: onDestroy
```

## start 和stop时，是否需要为同一个Context？不需要。
```
// case3: 1 - getApplicationContext() start, 1 stop
D/StartServiceTest1Activity: start: startService,context = getApplicationContext()
D/FirstService: onCreate
D/FirstService: onStartCommand
D/StartServiceTest1Activity: stop: stopService
D/FirstService: onDestroy
```

```
// case4: 1 start, 1 stop - getApplicationContext()
D/StartServiceTest1Activity: start: startService
D/FirstService: onCreate
D/FirstService: onStartCommand
D/StartServiceTest1Activity: stop: stopService,context = getApplicationContext()
D/FirstService: onDestroy
```
## 多次startService() ，Lifecycle?
- Service每次创建时回调onCreate
- Service每次启动时回调onStartCommand。
- (同一个Context/不同Contxt)多次启动一个已创建的Service，将不会回调onCreate，仅仅回调onStartCommand。  
启动后，若只是Unbounded service，仅调用一次stopService()就结束service即，执行onDestroy().     
继续调用次stopService()，虽然程序不报错，但不起作用。

即
For same or different Context instance:
1. startService() -> 1 onCreate / N onStartCommand
2. stopService() -> 1 onDestroy()
3. onCreate -> N onStartCommand -> 1 onDestroy()



```
// start 1+
D/StartServiceTest1Activity: start: startService
D/FirstService: onCreate
D/FirstService: onStartCommand

// start 2+
D/StartServiceTest1Activity: start: startService
D/FirstService: onStartCommand

// stop All = [1,2]
D/StartServiceTest1Activity: stop: stopService
D/FirstService: onDestroy
```
## StartService 运行在哪个线程？UI Thread
- StartService  不会主动创建 子 Thread
- StartService 默认运行在UI Thread。 

```
D/StartServiceTest1Activity: start: startService
D/FirstService: onCreate,thread =2,main
D/FirstService: onStartCommand,thread =2,main
```
## Service如何结束
- stopService() -> onDestroy()
- 执行完耗时操作，手动stopSelf()
- UI 线程中执行耗时操作，超过最大执行时间后ANR
- 系统资源紧缺，回收Service资源。

## 多次start，考虑数据同步问题？需要。考虑先后问题。
```
D/StartServiceTest1Activity: start: startService
D/FirstService: onCreate,thread =2,main
D/FirstService: onStartCommand,1

D/FirstService: mockHeavyWork,msg=i=1,thread =5989,Thread-7
D/FirstService: mockHeavyWork,msg=i=2,thread =5989,Thread-7

D/StartServiceTest1Activity: start: startService
D/FirstService: onStartCommand,2

D/FirstService: mockHeavyWork,msg=i=2,thread =5991,Thread-8
D/FirstService: mockHeavyWork,msg=i=3,thread =5989,Thread-7
D/FirstService: mockHeavyWork,msg=i=3,thread =5991,Thread-8

D/StartServiceTest1Activity: stop: stopService, context = StartServiceTest1Activity
D/FirstService: onDestroy,thread =2,main

D/FirstService: mockHeavyWork,msg=i=4,thread =5989,Thread-7
D/FirstService: mockHeavyWork,msg=i=4,thread =5991,Thread-8
D/FirstService: mockHeavyWork,msg=i=5,thread =5989,Thread-7
D/FirstService: mockHeavyWork,msg=i=5,thread =5991,Thread-8
D/FirstService: mockHeavyWork,msg=i=6,thread =5989,Thread-7
D/FirstService: mockHeavyWork,msg=i=6,thread =5991,Thread-8
D/FirstService: mockHeavyWork,msg=i=7,thread =5989,Thread-7
D/FirstService: mockHeavyWork,msg=i=7,thread =5991,Thread-8
D/FirstService: mockHeavyWork,msg=i=8,thread =5989,Thread-7
D/FirstService: mockHeavyWork,msg=i=8,thread =5991,Thread-8
D/FirstService: mockHeavyWork,msg=i=9,thread =5989,Thread-7
D/FirstService: mockHeavyWork,msg=i=9,thread =5991,Thread-8
D/FirstService: mockHeavyWork,msg=i=10,thread =5989,Thread-7
```
## Service 在UI 线程执行耗时操作，多久ANR？
- ANR = 5s
- 前台Serivice ANR = 30s
- 后台Serivice ANR = 30s

```
07-14 13:11:08.158 23495-23495/app_package_name D/FirstService: onCreate,thread =2,main
07-14 13:11:09.159 23495-23495/app_package_name D/FirstService: mockHeavyWork,msg=i=1,thread =2,main
...
07-14 13:11:38.223 23495-23495/app_package_name D/FirstService: mockHeavyWork,msg=i=30,thread =2,main
07-14 13:11:38.336 23495-23503/app_package_name I/zygote64: Thread[3,tid=23503,WaitingInMainSignalCatcherLoop,Thread*=0x74fe8c4400,peer=0x13000020,"Signal Catcher"]: reacting to signal 3
07-14 13:11:38.476 23495-23503/app_package_name I/zygote64: Wrote stack traces to '/data/anr/traces.txt'
07-14 13:11:39.478 23495-23495/app_package_name D/FirstService: mockHeavyWork,msg=i=31,thread =2,main
07-14 13:11:40.479 23495-23495/app_package_name D/FirstService: mockHeavyWork,msg=i=32,thread =2,main
07-14 13:11:41.500 23495-23495/app_package_name D/FirstService: mockHeavyWork,msg=i=33,thread =2,main
```
- ![WaitingInMainSignalCatcherLoop](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/ANR/anr.png)

### Service在UI Thread执行耗时操作，会怎么样？ ANR
```
// 点击button，执行StopService => 系统忙着在UI Thread执行耗时操作，捕捉不到任何事件，最终ANR。
07-14 13:44:52.975 27001-27001/hades D/StartServiceTest1Activity: start: startService
07-14 13:44:52.983 27001-27001/hades D/FirstService: onCreate,thread =2,main
07-14 13:44:52.983 27001-27001/hades D/FirstService: onStartCommand,thread =2,main
07-14 13:44:52.983 27001-27001/hades D/FirstService: mockHeavyWork,msg=i=0,thread =2,main
07-14 13:44:53.989 27001-27001/hades D/FirstService: mockHeavyWork,msg=i=1,thread =2,main
...
07-14 13:45:18.055 27001-27001/hades D/FirstService: mockHeavyWork,msg=i=25,thread =2,main
07-14 13:45:18.859 27001-27007/hades I/zygote64: Thread[3,tid=27007,WaitingInMainSignalCatcherLoop,Thread*=0x74fe8c4400,peer=0x16f00020,"Signal Catcher"]: reacting to signal 3
07-14 13:45:18.958 27001-27007/hades I/zygote64: Wrote stack traces to '/data/anr/traces.txt'
07-14 13:45:19.056 27001-27001/hades D/FirstService: mockHeavyWork,msg=i=26,thread =2,main
....
07-14 13:45:22.059 27001-27001/hades D/FirstService: mockHeavyWork,msg=i=29,thread =2,main
07-14 13:45:23.061 27001-27001/hades D/FirstService: mockHeavyWork,msg=i=30,thread =2,main
```
### Service onDestroy()后，子线程会立刻停止吗？不会。

```
07-14 13:49:10.455 27986-27986/hades D/StartServiceTest1Activity: start: startService
07-14 13:49:10.466 27986-27986/hades D/FirstService: onCreate,thread =2,main
07-14 13:49:10.467 27986-27986/hades D/FirstService: onStartCommand,thread =2,main
07-14 13:49:10.468 27986-30074/hades D/FirstService: mockHeavyWork,msg=i=0,thread =6701,Thread-7
07-14 13:49:11.471 27986-30074/hades D/FirstService: mockHeavyWork,msg=i=1,thread =6701,Thread-7
07-14 13:49:12.472 27986-30074/hades D/FirstService: mockHeavyWork,msg=i=2,thread =6701,Thread-7
07-14 13:49:13.474 27986-30074/hades D/FirstService: mockHeavyWork,msg=i=3,thread =6701,Thread-7
07-14 13:49:14.475 27986-30074/hades D/FirstService: mockHeavyWork,msg=i=4,thread =6701,Thread-7
07-14 13:49:14.978 27986-27986/hades D/StartServiceTest1Activity: stop: stopService, context = StartServiceTest1Activity
07-14 13:49:14.983 27986-27986/hades D/FirstService: onDestroy,thread =2,main
07-14 13:49:15.477 27986-30074/hades D/FirstService: mockHeavyWork,msg=i=5,thread =6701,Thread-7
07-14 13:49:16.479 27986-30074/hades D/FirstService: mockHeavyWork,msg=i=6,thread =6701,Thread-7
07-14 13:49:17.480 27986-30074/hades D/FirstService: mockHeavyWork,msg=i=7,thread =6701,Thread-7
```
-  添加 bool Flag，判断是否要结束线程。

```
private void mockHeavyWorkInThread4CheckStop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = mNum; i < MAX_NUM; i++) {
                    if (mIsForceStop) {
                        LogHelper.printThreadInfo(TAG, "mockHeavyWork,force stop", "i=" + i );
                        return;
                    }
                    LogHelper.printThreadInfo(TAG, "mockHeavyWork", "i=" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
    }

// Service被关闭之前回调
    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.d(TAG, "onDestroy");
        mIsForceStop = true;
        LogHelper.printThreadInfo(TAG, "onDestroy");
    }
```
```
07-14 14:21:52.765 5229-5229/app D/StartServiceTest1Activity: start: startService
07-14 14:21:52.772 5229-5229/app D/FirstService: onCreate,thread =2,main
07-14 14:21:52.772 5229-5229/app D/FirstService: onStartCommand,thread =2,main
07-14 14:21:52.773 5229-6422/app D/FirstService: mockHeavyWork,msg=i=0,thread =7737,Thread-7

07-14 14:21:53.255 5229-5229/app D/StartServiceTest1Activity: stop: stopService, context = StartServiceTest1Activity
07-14 14:21:53.267 5229-5229/app D/FirstService: onDestroy,thread =2,main
07-14 14:21:53.774 5229-6422/app D/FirstService: mockHeavyWork,force stop,msg=i=1,thread =7737,Thread-7
```
---

# Bounded Service 
- `BoundedServiceTestActivity.java`
- `BoundedService.java`  
        
## bindService
```
// flags:指定绑定时如果Service仍未创建时是否自动创建Service。
boolean bindService(Intent,ServiceConnection, @BindServiceFlags int flags);
```

- flags = Service.BIND_AUTO_CREATE:自动创建   
```
bindService(Intent, ServiceConnection, Service.BIND_AUTO_CREATE);
```  

```
// bind
D/BoundedServiceTestActivity: bindService: 
D/BoundedService: onCreate: 
D/BoundedService: onBind: 
D/BoundedServiceTestActivity: onServiceConnected: 

// get data from binder
D/BoundedServiceTestActivity: getServiceStatus: binder.getCount()=0

// unbind
D/BoundedServiceTestActivity: unbindService: 
D/BoundedService: onUnbind: 
D/BoundedService: onDestroy: 
```

- flags = 0: 不自动创建
```
bindService(intent, mConn, 0);
```

```
D/BoundedServiceTestActivity: bindService: 
```

## `ServiceConnection() - onServiceDisconnected`
- 当Service所在当宿主进程由于异常终止或者其他原因终止，导致该Service与访问者之间断开连接时，回调该方法.
- 当调用者主动使用unbindService()时，不回调该方法。


### Ony for boundservice, When multiple startService() ，Lifecycle?
- `1`个Context实例`N`次执行`bindService()`,并不会重新绑定。仅仅执行`onBind()`一次。  
要`1`次`ubbindservice()` -> service `onDestroy()` 
- `N`个不同Context实例，每个执行`1`次，共`N`次执行`bindService()`,绑定N次，但只有第`1`个执行`onBind()`，后面`N-1`仅执行`onServiceConnected`。  
要`N`次`ubbindservice()` -> service `onDestroy()` 

```
// bind 1+
D/BoundedServiceTestActivity: bindService: 
D/BoundedService: onCreate: 
D/BoundedService: onBind: 
D/BoundedServiceTestActivity: onServiceConnected: 

// bind 2+
D/BoundedServiceTestActivity: bindService: 

// unbind All
D/BoundedServiceTestActivity: unbindService: 
D/BoundedService: onUnbind: 
D/BoundedService: onDestroy: 
```

### Bounded Service 默认运行在哪个线程？
无论是在主线程，还是子线程中执行`bindService()`，`onBind()`始终运行在主线程。

```
// Call `bindService()` in UI Thread
D/BoundedServiceTestActivity: bindService: 
D/BoundedService: onCreate,thread =2,main
D/BoundedService: onBind,thread =2,main

D/BoundedServiceTestActivity: unbindService: 
D/BoundedService: onUnbind,thread =2,main
D/BoundedService: onDestroy,thread =2,main
```

```
// Call `bindService()` in Thread
D/BoundedServiceTestActivity: bindServiceWithAutoCreateInThread,thread =12375,Thread-12
D/BoundedService: onCreate,thread =2,main
D/BoundedService: onBind,thread =2,main
D/BoundedServiceTestActivity: onServiceConnected: 

D/BoundedServiceTestActivity: unbindService: 
D/BoundedService: onUnbind,thread =2,main
D/BoundedService: onDestroy,thread =2,main
```

##  IBinder
![IBinder](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/app_component/service/IBinder_1.png)

![IBinder](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/app_component/service/IBinder_2.png)

#### 继承Binder，实现IBinder对象

## Managing the lifecycle of a bound service
When a service is unbound from all clients, the Android system destroys it (unless it was also started with `onStartCommand()`). As such, you don't have to manage the lifecycle of your service if it's purely a bound service—the Android system manages it for you based on whether it is bound to any clients.  
However, if you choose to implement the `onStartCommand()` callback method, then you must explicitly stop the service, because the service is now considered to be started. In this case, the service runs until the service stops itself with `stopSelf()` or another component calls `stopService()`, regardless of whether it is bound to any clients.    
Additionally, if your service is started and accepts binding, then when the system calls your `onUnbind()`method, you can optionally return `true` if you would like to receive a call to `onRebind()` the next time a client binds to the service. `onRebind()` returns void, but the client still receives the `IBinder` in its `onServiceConnected()` callback. The following figure illustrates the logic for this kind of lifecycle.     


![The lifecycle for a service that is started and also allows binding.](https://developer.android.google.cn/images/fundamentals/service_binding_tree_lifecycle.png)

Figure 1. The lifecycle for a service that is started and also allows binding.

### 例子：特殊的生命周期：Service已经通过`startServie()`启动了。然后bindService() -> bindService() -> unbindService() -> bindService() -> unbindService() , 生命周期？  
当Activity调用bindService()绑定一个已经启动（called  onCreate()）的Service，系统仅仅把Service内部IBinder对象传给Activity，并`不会`把该Service生命周期完全绑定在一起。    
当调用unbindService()取消与该Service的绑定时，只是切断该Actvity与Service之间的关联，并不会停止该Service组件。  

```
// Service被断开连接时回调该方法
    @Override
    public boolean onUnbind(Intent intent) {
//        Log.d(TAG, "onUnbind: ");
        LogHelper.printThreadInfo(TAG, "onUnbind");
        return true;
    }
```
```
// start service
D/BoundedServiceTestActivity: startService: 
D/BoundedService: onCreate,thread =2,main
D/BoundedService: onStartCommand: 

// bind 1+ 
D/BoundedServiceTestActivity: bindService: 
D/BoundedService: onBind,thread =2,main
D/BoundedServiceTestActivity: onServiceConnected: 

// bind 2+
D/BoundedServiceTestActivity: bindService: 

// unbind ALL = [1,2]
D/BoundedServiceTestActivity: unbindService: 
D/BoundedService: onUnbind,thread =2,main

// bind 3+
D/BoundedServiceTestActivity: bindService: 
D/BoundedServiceTestActivity: onServiceConnected: 
D/BoundedService: onRebind: 

// unbind All = [3]
D/BoundedServiceTestActivity: unbindService: 
D/BoundedService: onUnbind,thread =2,main

// stop service
D/BoundedServiceTestActivity: stopService: 
D/BoundedService: onDestroy,thread =2,main
```

### If the same service class both have bondSercie and unbundService, when call service onDestroy()?
```
// bundService和startService 不分顺序
bundService             intSize++
startService            isStart=true
```
```
// unbundService和stoptService 不分顺序
unbundService           intSize--
stopService/stopself    isStart=false
```

Onbly when `intSize==0 && isStart ==false` => onDestroy()

## ERROR:unbind already unbinded service, ERROR:`java.lang.IllegalArgumentException: Service not registered:`

```
D/BoundedServiceTestActivity: bindService: 
D/BoundedService: onCreate,thread =2,main
D/BoundedService: onBind,thread =2,main
D/BoundedServiceTestActivity: onServiceConnected: 

D/BoundedServiceTestActivity: unbindService: 
D/BoundedService: onUnbind,thread =2,main
D/BoundedService: onDestroy,thread =2,main

D/BoundedServiceTestActivity: unbindService: 
D/AndroidRuntime: Shutting down VM
E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.hades.android.example.android_about_demos, PID: 6394
    java.lang.IllegalArgumentException: Service not registered: com.hades.android.example.android_about_demos.app_component.service.bindservice.BoundedServiceTestActivity$1@6cd0cc1
        at android.app.LoadedApk.forgetServiceDispatcher(LoadedApk.java:1490)
        at android.app.ContextImpl.unbindService(ContextImpl.java:1655)   
``` 

=>
`BoundedService.java`  
mIsBounded = true;

`BoundedServiceTestActivity.java`
```
  private void unbindService() {
        if (null == mBinder || !isBound) {
            return;
        }
        Log.d(TAG, "unbindService: ");
        unbindService(mConn);
        mBinder = true;
        isBound = false;
    
    }
```

# Explicit Intent

bindService(ExplicitIntent，ServiceConnection，Context.BIND_AUTO_CREATE)

## Local  BoundServce
- Local BoundServce, bind success.

## Remote  BoundServce
- If app -> B app, bind  fail.
- If B app,app -> B app, bind  success.

## Remote activity
- startActivity(ExplicitIntent) success.

`TestLocalBoundServiceActivity.java`  
`TestRemoteBoundServiceActivity.java                                          `


---

# System Service
## AuioService
![Service.AUDIO_SERVICE](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/app_component/service/audio_service.png)

## Vibrator
![Service.VIBRATOR_SERVICE](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/app_component/service/vibrator.png)

# References:
- Serices https://developer.android.google.cn/guide/components/services
- Bound Services https://developer.android.google.cn/guide/components/bound-services
- Android性能优化（七）之你真的理解ANR吗？https://www.jianshu.com/p/af13abc5f0c8
- https://www.jianshu.com/p/794e41fb6604