# Android 事件处理

## 事件处理机制
Android提供事件处理机制有2种方式：基于监听的事件处理、基于回调的事件处理。  

## 基于监听的事件处理机制   

### 监听的处理模型 
主要涉及到的三类对象：    

![监听的处理模型](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/event_handler/base_on_listener_1.png)


- 任意界面组件都可以作为事件源。
- 事件的产生不用程序员关心。它是由系统自动产生的。
- 事件监听器（类 - XXXListener）的核心是它所包含的方法，这些方法方法也被成为事件处理器（Event Handler）。    
事件监听器，是实现特定接口的 Java 类的实例。  
因此，实现事件监听器是整个事件处理的核心。  

### 事件处理流程   
- 基于监听的事件处理机制采用的是委托(Delegate)方式。
- 委派式事件处理方式体现了人类社会的分工写作。
![事件处理流程  ](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/event_handler/base_on_listener_2.png)

事件处理模型的简化理解：  
- 当事件源发生事件时，系统将会执行该事件源上的监听器对应处理方法。  
- 普通 Java 程序的方法是由程序主动调用的。事件处理中的事件处理器方法是由系统负责调用。  

### 使用方式：
- 找出`事件源`
- 实现事件监听器，关键是实现处理器的方法。
- 注册监听器
setXXXListener（XXXListener）

### QA:为什么btn 的 Click 处理 没有发现事件的踪迹？  

`EventHandlerBaseOnListenFragment.java`    
```
 // 基于监听的事件处理
   view.findViewById(R.id.bn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTip();
            }
        });
```
Android 对事件监听模型做了进一步简化：  
- 如果事件源出发的事件足够简单，事件里面封装的信息比较有限，不会封装事件对象，不将事件对象传入事件监听器。 - Click Action  
- 如果事件源出发的事件复杂，事件里面封装的信息比较详细，会封装事件对象（XxxEvent），将事件对象传入事件监听器。  - Touch Action ,MotionEvent    

`android.view.java`  
```
 /**
     * Interface definition for a callback to be invoked when a touch event is
     * dispatched to this view. The callback will be invoked before the touch
     * event is given to the view.
     */
    public interface OnTouchListener {
        /**
         * Called when a touch event is dispatched to a view. This allows listeners to
         * get a chance to respond before the target view.
         *
         * @param v The view the touch event has been dispatched to.
         * @param event The MotionEvent object containing full information about
         *        the event.
         * @return True if the listener has consumed the event, false otherwise.
         */
        boolean onTouch(View v, MotionEvent event);
    }
```

```
    /**
     * Interface definition for a callback to be invoked when a view is clicked.
     */
    public interface OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onClick(View v);
    }
```

### 以 View 为例，内部接口：
- `Vew.OnTouchListener`
- `Vew.OnClickListener`
- `Vew.OnLongClickListener`
- `Vew.OnDragListener`
- `Vew.OnFocusChangeListener`

### 事件监听器 的形式：
在程序中实现监听器的有5种形式：  
- 内部类形式     
`InnerClassListenFragment.java`  
 将事件监听器类定义成当前类的内部类

- 外部类形式   
`OuterClassListenFragment.java`   
 将事件监听器类定义成一个外部类  

- Activity 本身作为事件监听器类    
`ActivityItselfListenerActivity.java`  
让 Activity 本身实现监听器接口，并实现事件处理方法。

- 匿名内部类形式。 
`AnonymousInnerClassListenFragment.java`    
使用匿名内部类创建事件监听器对象。    
因为大部分事件监听器只使用一次，  使用形式最多。   

- XML  onClick    
`XmlListenerActivity.java`  
`android:onClick="onBtnClick" `  
`public void onBtnClick(View v) `

---
## 基于回调的事件处理
- 事件源与事件监听器是统一的，事件监听器完全消失了。  当用户在组件傻姑娘激发某个事件时，组件自己特定的方法将会负责处理该事件。   
- 为了使用回调机制类处理 GUI 组件上发生的事件，需要继承GUI组件，并重写该类的事件处理机制方法来实现。

![回调方法  ](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/event_handler/base_on_callback_1.png)

### 使用方式
重写该类的事件处理机制方法  
- 重写 View 的回调方法
- 重写Activity的回调方法

### QA: 同时设置监听和回到，setOnClickListener设置无效 
`EventHandlerBaseOnCallbackFragment`
```
 /**
         * QA: setOnClickListener设置无效
         * 1 boolean onTouchEvent(MotionEvent event) return true, onTouchEvent生效，setOnClickListener无效。
         * 因为onTouchEvent 已经处理完事件，不会继续传递。
         *
         * 2 boolean onTouchEvent(MotionEvent event) return false, onTouchEvent调用后，setOnClickListener也有效。
         * 因为onTouchEvent 没处理完事件，会继续传递。
         */
        view.findViewById(R.id.bn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTip();
            }
        });
```


### 基于回调的事件传播

![基于回调的事件传播  ](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/event_handler/base_on_callback_2.png)

当组件发生了事件时，Android 事件最先触发的是绑定的事件监听器，然后才触发该组件的事件回调方法，最后还会传播到该组件所在的 Activity。 
如果任何一个事件处理方法返回了返回了 true，则该事件不会继续向外传播。  
即：  
事件监听器 ->  该组件的事件回调方法 -> 该组件所在的 Activity(重写事件回调方法)

- Log  = false
```
EventHandlerBaseOnCallbackActivity: onTouch: ACTION_DOWN in setOnTouchListener
MyButton: onTouchEvent: ACTION_DOWN in MyButton
EventHandlerBaseOnCallbackActivity: onTouchEvent: ACTION_DOWN in Activity
EventHandlerBaseOnCallbackActivity: onTouchEvent: ACTION_UP in Activity
```
- Log  = true

```
EventHandlerBaseOnCallbackActivity: onTouch: ACTION_DOWN in setOnTouchListener
EventHandlerBaseOnCallbackActivity: onTouch: ACTION_UP in setOnTouchListener
```

`EventHandlerBaseOnCallbackActivity.java`  
```
/**
 * 基于回调的事件处理
 */
public class EventHandlerBaseOnCallbackActivity extends Activity {
    private static final String TAG = EventHandlerBaseOnCallbackActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_handler_base_on_callback);

        View btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: setOnClickListener");
            }
        });

//        btn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    Log.d(TAG, "onTouch: ACTION_DOWN in setOnTouchListener");
//
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    Log.d(TAG, "onTouch: ACTION_UP in setOnTouchListener");
//                }
//                return false;
//            }
//        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d(TAG, "onTouch: ACTION_DOWN in setOnTouchListener");

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "onTouch: ACTION_UP in setOnTouchListener");
                }
                return true;
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "onTouchEvent: ACTION_DOWN in Activity");

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d(TAG, "onTouchEvent: ACTION_UP in Activity");
        }
        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.d(TAG, "onTouchEvent: ACTION_DOWN in Activity");
//
//        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            Log.d(TAG, "onTouchEvent: ACTION_UP in Activity");
//        }
//        return false;
//    }
}
```
`MyButton.java`
```
@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {
    private static final String TAG = MyButton.class.getSimpleName();

    public MyButton(Context context, AttributeSet set) {
        super(context, set);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "onTouchEvent: ACTION_DOWN in MyButton");

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d(TAG, "onTouchEvent: ACTION_UP in MyButton");
        }
        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.d(TAG, "onTouchEvent: ACTION_DOWN in MyButton");
//
//        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            Log.d(TAG, "onTouchEvent: ACTION_UP in MyButton");
//        }
//        // false，表示没有完全处理该事件，该事件依然向外扩散
//        return false;
//    }
}
```
## 基于回调的事件处理  VS  基于监听的事件处理  
1. 基于监听的事件处理  ： => 交外卖
- 事件源、事件、事件监听器。  
- 事件源和事件监听器是分离的。当事件源发生特定事件时，该事件交给事件监听器负责处理。  
- 更有优势。 使用最多。    
分工更明确，事件源和事件监听器由两个类分开实现，因此具有更好的可维护性。      
Android事件处理机制保证基于监听的事件监听器会优先出发。    

2. 基于回调的事件处理  ：  => 自提
- 事件源（事件监听器）、事件。  
- 事件源和事件监听器是统一的。当事件源发生特定事件时，该事件还是由事件源本身处理。  
- 优点：  
提高程序的内聚性。