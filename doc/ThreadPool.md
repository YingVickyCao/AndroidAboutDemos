# Thread Pool
## Why?
创建许多thread -> performance ↓
=> reuse pooled thread -> impormance ↑

## How work?
BlockingQueue:FIFO – First in first out

![ThreadPool](https://cdn1.howtodoinjava.com/wp-content/uploads/2015/03/Thread_pool.png)

---

# ThreadPoolExecutor: Thread Pool in Java
## How Work?  
![ScheduledThreadPoolExecutor and ThreadPoolExecutor](http://ww1.sinaimg.cn/large/bda5cd74gy1ft9rdddpi7j20sg0fwgno.jpg)

## How to create Thread Pool? 
5 kinds of Thread Pool

### (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_NUM)
``` 
public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
```
- Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue.  
当task超次N，排队等候
- The threads in the pool will exist until it is explicitly {@link ExecutorService#shutdown shutdown}

### (ThreadPoolExecutor) Executors.newCachedThreadPool()
```
  public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }
```
- @param corePoolSize the number of threads to keep in the pool, even if they are idle
- Creates a thread pool that creates new threads as needed, but  will reuse previously constructed threads when they are available. 
- execute many short-lived asynchronous tasks
- Calls to {@code execute} will reuse previously constructed threads if available. If no existing thread is available, a new thread will be created and added to the pool.
- Threads that have not been used for 60s are terminated and removed from the cache. Thus, a pool that remains idle for long enough will not consume any resources

###  Executors.newScheduledThreadPool(THREAD_NUM)
```
public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }
```
- Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically

### (ForkJoinPool) Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors())
```
public static ExecutorService newWorkStealingPool() {
        return new ForkJoinPool
            (Runtime.getRuntime().availableProcessors(),
             ForkJoinPool.defaultForkJoinWorkerThreadFactory,
             null, true);
    }
```
- Creates a work-stealing thread pool using all {@link Runtime#availableProcessors available processors} as its target parallelism level.
- ForkJoinPool

### Executors.newSingleThreadExecutor()
```
public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
```
-  Creates an Executor that uses a single worker thread operating  off an unbounded queue.
- if this single thread terminates due to a failure during execution prior to shutdown, a new one will take its place if needed to execute subsequent tasks.)  
- Tasks are guaranteed to execute sequentially, and no more than one task will be active at any given time

## 执行方法
-  execute()：没有返回值
- submite(): returns a Future representing that task. Future 可以尝试取消task
- ScheduledFuture.cancel(true):   
确保task被停止exec后不会再被exec，而不是立刻就停止exec.   
需要手动设置标志位，提前终止task，否则等待task执行完才能立刻结束  

## 结束方法
ExecutorService.shutdown():
- 不再接受新的task。
- 已提交的所有任务继续执行。当任务执行完毕时自动终止该任务。

ExecutorService.shutdownNow():
- 立刻尝试取消所有已执行task，不等待各个任务执行完毕
- 最终实现Thread.interrupt().若方法not work，则是不能取消.
- 实际测试：  
不生效：不能立刻取消。需要手动设定条件来取消线程


## ThreadPoolExecutor 使用场景

ThreadPoolExecutor|使用场景
---|---
newFixedThreadPool|可预测或可限制线程个数
newCachedThreadPool|execute many short-lived asynchronous tasks
newScheduledThreadPool|延时执行、定期执行、执行周期任务
newWorkStealingPool|并发执行、大量task，
newSingleThreadExecutor|顺序执行tasks，每次仅执行一个task

---

# Ref
- https://www.jianshu.com/p/135c89001b61
- https://blog.csdn.net/holmofy/article/details/82714665
- https://howtodoinjava.com/multi-threading/java-thread-pool-executor-example
- https://www.baeldung.com/thread-pool-java-and-guava
- https://developer.android.google.cn/training/multiple-threads/create-threadpool
- https://developer.android.google.cn/training/multiple-threads/define-runnable