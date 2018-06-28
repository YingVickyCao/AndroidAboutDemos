# Android 消息机制，又叫 Handler 消息传递机制  
- `MessageHandlerActivity.java`   
- `HandlerThreadFragment.java`
- `HandlerThreadFragment4Refactor.java`
- `SumFragment.java`
- `MsgThread2MainFragment.java`

---
## Android 消息传递机制
### 为什么有Android 消息传递机制？  
- 由于性能优化考虑，Android 的 UI 操作不是线程安全。
- 主要是为了解决 Android 多线程问题 —— Android 的 UI 操作不是线程安全，只允许 UI 线程修改 UI 组件。这样导致子线程无法动态改变界面的属性值。      
借助于 Handler 的消息传递机制，子线程发送消息到UI 线程，UI 线程负责更新 UI 组件。    

### Handler 消息传递方向？  
- main -> thread 
- thread -> main（主要） 

### UI 线程   
- UI 线程，又叫主线程/main 线程/main thread。    
- 当程序第一次启动时，Android 默认同时启动一条主线程。主线程负责处理与 UI 相关的事件，如用户的按键事件、用户接触屏幕的事件以及屏幕绘图事件，并把相关的的事件分发到对应的组件进行处理。
- ANR   
尽量避免再 UI 线程中执行耗时操作，因为这样可能导致ANR。   
ANR异常，只要在 UI 线程中执行耗时操作，都会引发 ANR。因为这会导致 Android 应用无法响应输入事件和 Broadcast。 

### Handler 消息传递机制模型  
![Handler 消息传递机制模型](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/msg_handler/msg_handler_model.png)

---

## Handler、Loop、MessageQueue 的工作原理  
### Handler：  
- 功能：  
1）发送消息    
- 使用 Handler 发送消息给必须指定的、Loop管理的MessageQueue。  
2）处理消息(handleMessage())  
- Handler负责处理Looper分给它的消息。  
- 若 Handler 能正常工作，在当前线程必须有一个MessageQueue，否则没有MessageQueue保存消息。  
- 由于MessageQueue 由 Looper 负责管理，因此，若 Handler 能正常工作，在当前线程必须有一个Looper对象。
- 常见方法  

![Handler](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/msg_handler/handler_common_func1.png)  

![Handler](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/msg_handler/handler_common_func2.png)  

### Message（消息）:  
- Handler 接受和处理的消息对象  

### MessageQueue（消息队列）：
-  功能：按先进先出的方式管理Message。
- MessageQueue包含Message。
- MessageQueue 由 Looper 负责管理。

`Looper.java`
```
private Looper(boolean quitAllowed) {
        mQueue = new MessageQueue(quitAllowed);
        mThread = Thread.currentThread();
    }
```

### Looper：
- 功能：Looper的 loop()方法负责不断地从MessageQueue 取出消息，并把消息交给对应的（发送该消息的） Handler 进行处理。  
- 每个线程只有一个 Looper。 
- 创建 Looper对象时，会在Looper自己的构造函数中创建MessageQueue对象。

### 使用 Handler 时，如何保证当前线程有 Looper 对象？    
#### 情况1：UI 线程
- UI 线程中，系统已经自动初始化一个 Looper 对象，因此程序可以直接创建 Handler，通过Handler发送、处理消息。  

#### 情况2：子线程  
- 子线程中，必须自己创建一个 Looper 对象，并启动它。
-  使用 Handler 之前必须创建一个 Looper。否则ERROR：  
`MsgThread2MainFragment.java`    
`java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()`   

- 调用Looper的`prepare()`方法为当前线程创建一个 Looper实例。    
- prepare()方法保证每个线程最多有一个Looper 对象。
   
`Looper.java`  
```
 /** Initialize the current thread as a looper.
      * This gives you a chance to create handlers that then reference
      * this looper, before actually starting the loop. Be sure to call
      * {@link #loop()} after calling this method, and end it by calling
      * {@link #quit()}.
      */
    public static void prepare() {
        prepare(true);
    }

    private static void prepare(boolean quitAllowed) {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper(quitAllowed));
    }

    /**
     * Return the Looper object associated with the current thread.  Returns
     * null if the calling thread is not associated with a Looper.
     */
    public static @Nullable Looper myLooper() {
        return sThreadLocal.get();
    }

   /**
     * Run the message queue in this thread. Be sure to call
     * {@link #quit()} to end the loop.
     */
    public static void loop() {
        final Looper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        final MessageQueue queue = me.mQueue;

        // Make sure the identity of this thread is that of the local process,
        // and keep track of what that identity token actually is.
        Binder.clearCallingIdentity();
        final long ident = Binder.clearCallingIdentity();

        for (;;) {
            Message msg = queue.next(); // might block
            if (msg == null) {
                // No message indicates that the message queue is quitting.
                return;
            }

            // This must be in a local variable, in case a UI event sets the logger
            final Printer logging = me.mLogging;
            if (logging != null) {
                logging.println(">>>>> Dispatching to " + msg.target + " " +
                        msg.callback + ": " + msg.what);
            }

            final long slowDispatchThresholdMs = me.mSlowDispatchThresholdMs;

            final long traceTag = me.mTraceTag;
            if (traceTag != 0 && Trace.isTagEnabled(traceTag)) {
                Trace.traceBegin(traceTag, msg.target.getTraceName(msg));
            }
            final long start = (slowDispatchThresholdMs == 0) ? 0 : SystemClock.uptimeMillis();
            final long end;
            try {
                msg.target.dispatchMessage(msg);
                end = (slowDispatchThresholdMs == 0) ? 0 : SystemClock.uptimeMillis();
            } finally {
                if (traceTag != 0) {
                    Trace.traceEnd(traceTag);
                }
            }
            if (slowDispatchThresholdMs > 0) {
                final long time = end - start;
                if (time > slowDispatchThresholdMs) {
                    Slog.w(TAG, "Dispatch took " + time + "ms on "
                            + Thread.currentThread().getName() + ", h=" +
                            msg.target + " cb=" + msg.callback + " msg=" + msg.what);
                }
            }

            if (logging != null) {
                logging.println("<<<<< Finished to " + msg.target + " " + msg.callback);
            }

            // Make sure that during the course of dispatching the
            // identity of the thread wasn't corrupted.
            final long newIdent = Binder.clearCallingIdentity();
            if (ident != newIdent) {
                Log.wtf(TAG, "Thread identity changed from 0x"
                        + Long.toHexString(ident) + " to 0x"
                        + Long.toHexString(newIdent) + " while dispatching to "
                        + msg.target.getClass().getName() + " "
                        + msg.callback + " what=" + msg.what);
            }

            msg.recycleUnchecked();
        }
    }

```

`SumFragment.java`
```
class SumThread extends Thread {
        public Handler mHandler;

        public void run() {
            Looper.prepare();

            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    /**
                     * 在子线程中执行
                     */
                    if (msg.what == HANDLER_MSG_KEY_1) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        LogHelper.logThreadInfo(TAG, "SumThread -> handleMessage()", String.valueOf(upper));
                        long sum = MockHeavyWork.sum(upper);

                        /**
                         * thread -> main
                         */
                        updateResult(sum);
                    }
                }
            };

            Looper.loop();
        }
    }
```
- 使用Looper.loop()启动Looper。  
- `loop()`使用死循环不断从MessageQueue中取得消息

--- 
## 如何保证线程安全？
- 只能在主线程才能更新UI。不要在UI线程外直接操作UI
- 不要Block UI Thread

--- 
## 子线程 -> 主线程？
 - Activity. runOnUiThread(Runnable action)
- Handler.post、Handler.send
- View.post(Runnable) 、View.postDelay(Runnable , long)
- AsyncTask
- EventBus
- RxJava2

以上所有方式，最终是调用enterQueue方法。  
 
--- 

## QA for handler? 
### Handler是什么？
- （1）Handler允许发送和处理Message（消息）和Runnable objects，这里的Runnable objects与消息队列（MessageQueue）关联。   
- （2）每一个Handler实例只与一个线程和这个线程的消息队列相关联。  
- （3）当创建一个新Handler时，它被绑定到创建这个handler的线程或这个线程的消息队列。从那一刻起，它会传递Message和runnables到这个消息队列，并且当Message和runnables从消息队列中出来时执行他们。  
 
### Hanlder作用:
- （1）执行计划任务。在预定的实现执行某些任务，可以模拟定时器
- （2）线程间通信。最经典操作是，子线程完成耗时任务，执行完成后，会发送
- （3）确保操作始终在某个特定的线程中运行。例如，数据库的查询要在同一个子线程中运行。
 
### Handler的实现原理？或 简述Android的消息机制？或者MessageQueue、Looper、Handler、或者，与Thread的关系以及作用？或者Handler与线程的关系？或者，Handler 异步实现方法？

**Android的消息机制和Window的消息机制很相似，都是使用消息队列实现的。**  
 Android的消息机制涉及到了Thread（线程）、Looper（循环）、MessageQueue（消息队列）、消息（Message）、Handler.    
其中
Thread中关联唯一的Looper、MessageQueue 。  
Handler通过Handler(Looper looper)方法关联唯一的Thread。  
MessageQueue包含Message。  
 
- （1）消息（Message）：包含被传送数据。
- （2）消息队列（MessageQueue）：管理消息（Message），使得传递的消息有序，否则顺序一乱图片显示不正确。  
考虑在消息队列中找消息，如果没有则创建，如果有则利用。这样消息的回收能提高效率，防止程序出现内存泄露。
- （3）Handler：Handler允许发送和处理Message（消息）和Runnable objects。 Hanlder负责存消息、取消息、管理消息队列的出队和入队操作。
- （4）Looper：不断循环并分发消息。获取和分发消息的循环是死循环。Looper获取消息队列后，不断从消息队列中获取消息。当消息为空时，退出循环。当消息非空时，分发消息。
 
![Handler 消息传递机制模型](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/msg_handler/msg_handler_model_2.png)

### Handler一定是在主线程中吗？或者，如何在子线程中创建Handler?  
不一定。  
通过HandlerThread创建一个含有Looper的子线程：`HandlerThread handlerThread = new HandlerThread("threadone"); `     
启动线程后：`handlerThread.start()  `  
- 使用getLooper方法得到这个子线程的Looper：    `Looper looper = handlerThread.getLooper(); `   
把Looper指定到Handler：final Handler myHandler = new Handler(looper)   
那么，Handler就关联到了这个子线程。    
 
### Handler的post 和sentMessage方法，使用的是一个队列还是两个？
 
是一个消息队列。  
最终都会调用`setMessagAtTime()`方法。  
这个方法中执行`enqueueMessage()`方法将Message送到关联的消息队列中。  
 
### 子线程中建立一个handler，然后sendMessage会怎样？
程序会崩溃。  
`MsgThread2MainFragment.java`    
`java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()`    
主线程中使用new Handler()，默认会关联到主线程。      
子线程中new Handler()，Looper 需要使用`Looper.prepare()`来创建一个 Looper。关联一个Handler到子线程 = 关联Looper到这个Handler。    
  
### Handler中不同的消息有可能被同时处理么？
不会。按MessageQueue先进先出的顺序执行。
 
### 多个Handler使用同一个Thread / 同一个Thread中可以有多个Handler吗？
可以。
 
### 多个Handler使用同一个Thread/同一个Thread中使用多个Handler，Message会混淆么？为什么？
不会。  
因为Message的构造方法中会执行.target = handler。这个target就是要Looper分发Message时，要分发到的handler。 
### 如何指定Handler运行的线程？
第一步：得到要运行线程的Looper。   
如果是子线程，一定要执行Looper.   
Prepare()方法得到子线程的Looper。  
执行Looper.loop()方法使得让Looper开始工作，从消息队列里取消息，处理消息。   

第二步：调用Handler(Looper)以确定其使用的Looper。  

### 当Handler在处理消息时，如果收到removeMessage()， 会中断这个处理么？或者，对于正在执行的Message，调用removeMessage会有什么后果？
不会。  
removeMessage只能移除队列中的Message  

## QA for Looper
### Looper的实现原理是什么？
Looper的实现和Windows编程的消息机制非常的类似。    
Looper不断从循环，从消息队列中获取消息，并把消息发送给对应的Handler，然后Handler来处理消息。    

### 如何得到一个Looper？
- （1）`Loopr.myLooper()`：返回跟当前线程关联的Looper。  
- （2）`Looper.getMainLooper()`：返回主线程关联的Looper。
- （3）在 UI 线程中new Handler，自动关联主线程。
- （4）通过`HandlerThread.getLooper()`获取该HandlerThread的Looper。

### 子线程建立handler , 构造的时候传入主线程的Looper?  
Handler关联的是主线程。消息会在主线中被处理。