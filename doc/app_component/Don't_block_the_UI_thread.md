# Process and threads  

## `Choreographer Skipped 37 frames! The application may to doing too much work  on its main thread.`-> near = ANR     
***[TODO]frames***  

- activity的初始化工作  
- layout.xml 中有很多图或大图片 -> 
1. source设置图片  
2. 优化图片   
`BitmapUtils`/`Glice`/`Picaso`/自己写     
- source && list 使用很多图片 ->  优化图片.  
- 界面刷新频繁  -> check if need fresh => invisible? repeat ? delegate == null?  
- debug use `[TODO]StrickMode`

## Thread-safe methods  
1. boud service - IBinder methods, such onBind(),  must be implemented to be thread-safe. 
- same process => user thread A , <- service thread A 
- not same process -> user thread A, service thread is chosen from a pool of threads that system maintains in the same process as the IBinder(It's not exected in the UI thread of process).  

***[TODO]IBinder***   

2. ContentPrivider - methods that respond to those requests - query(),insert(),delete(),update(),and getType() - are called from a pool of threads in the content provider's process, not the UI thread for the process.  

## Interprocess communication    
IPC = Interprocess communication    
RPCs = remote procedure calls  

## `[TODO]StrickMode`     

## Threads and UI object references 
### Explict references 

For `delegate == null`:    
Example1:   
work thread contains a ref to a View.   
But before work completes , View is removed from  the view hierachy.        
When those two actions happen simultaneously, the references keeps the View object in memory and sets properties on it.  
However, the user never sees this object, and the app deletes the object once the references to it gone.  
=> 短时间内存泄露  

Example2:  
View objects contains references to the activity that owns them. If that activity is destroyed, but there remains a threaded block of work that references it - directly or indirectly - the garbage collector will not cokllect the activity until that block of work finishes executing.  

### Implict references   
a no-static inner class of some activities.  


### Rule   

 => static classes , or in 


## References:
- subsribeOn VS observeOn - www.jisanshu.com/p/da80a0834aae：          
- Binder https://developer.android.com/reference/android/os/Binder  
- ContentPrivider https://developer.android.com/reference/android/content/ContentPrivider 
- Process and threads overview  https://developer.android.com/guide/components/process-and-threads   
- Better performance through threading https://developer.android.com/topic/performance/threads   
- StrictMode https://blog.csdn.net/bboyfeiyu/article/details/50512459    



