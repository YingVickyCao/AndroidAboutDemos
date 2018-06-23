# ViewFlipper
- `ViewFlipperFragment.java`
 

## 功能
- 添加多个`View`，切换`View`时能指定动画。   
- 本身不含滑动切换。但可以通过添加 onTouch()来增加。

## Common xml attrs

常见XML 属性|描述
---|---
android:flipInterval="1000"|设置切换的间隔时间（ms）

## Common Funs

常见函数|描述
---|---
showPrevious()|显示上一个 view
showNext()|显示下一个View
startFlipping()|开始 auto
stopFlipping()|停止 auto
setInAnimation|设置进入动画
setOutAnimation|设置移出动画
addView(View child)|动态添加 view
getDisplayedChild()|当前 Showed View 的 index
viewFlipper.getChildCount()|Items 的总数


## `ViewFlipper` VS `AdapterViewFlipper`
同：  
- 添加多个 `View`，并能控制View 切换的动画效果
- ViewFlipper 与AdapterViewFlipper 指定相同 XML 属性。

异：  
- ViewFlipper 通过 `addView(View v)` / XML 包含的方式添加多个 View
- AdapterViewFlipper 通过传入 `Adapter`，Adapteri负责提供多个 View。


---

## QA: 点击切换 View时，禁止轮询?
`ViewFlipperFragment.java`

```
 private boolean isLast() {
        return viewFlipper.getDisplayedChild() == (viewFlipper.getChildCount() - 1);
    }

    private boolean isFirst() {
        return viewFlipper.getDisplayedChild() == 0;
    }
```
---

## QA：重写的ViewFlipper，总是 ERROR：加载xml 失败。
一定要重写的所有的构造函数。即使没有新增内容，也要列出来。

```
public class CustomViewFlipper extends ViewFlipper {
    public CustomViewFlipper(Context context) {
        super(context);
    }

    // 因为没有重写此构造函数，总是加载 XML失败。
    public CustomViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
```

---

## QA: auto 切换 View时，禁止轮询?
重写ViewFlipper,`showNext()`,`showPrevious()`时做是否为第一个或最后一个判断。

`CustomViewFlipper.java`
```
public class CustomViewFlipper extends ViewFlipper {
    public CustomViewFlipper(Context context) {
        super(context);
    }


    public CustomViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void showNext() {
        /**
         * auto 切换 View时，禁止轮询。
         */
        if (isLast()) {
            Toast.makeText(getContext(), "Already last one", Toast.LENGTH_SHORT).show();
            return;
        }
        super.showNext();
    }

    @Override
    public void showPrevious() {
        /**
         * auto 切换 View时，禁止轮询。
         */
        if (isFirst()) {
            Toast.makeText(getContext(), "Already first one", Toast.LENGTH_SHORT).show();
            return;
        }
        super.showPrevious();
    }

    private boolean isLast() {
        return getDisplayedChild() == (getChildCount() - 1);
    }

    private boolean isFirst() {
        return getDisplayedChild() == 0;
    }
}
```

## [TODO]QA: auto always flip Now  

`ViewFlipper.java`
```
   /**
     * Internal method to start or stop dispatching flip {@link Message} based
     * on {@link #mRunning} and {@link #mVisible} state.
     */
    private void updateRunning() {
        updateRunning(true);
    }
```
源码无法重写，不能fix。

---

## QA： 滑动ViewFlipper，切换 View？ 

重写ViewFlipper 的onTouch()，通过判断 up 和 down 的 x 差值，得出左滑还是右滑。

`ViewFlipperFragment.java`
```
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        /* 滑动ViewFlipper，切换 View
         */
        viewFlipper.setOnTouchListener(this);

    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTouchDownX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mTouchUpX = event.getX();
            if (mTouchUpX - mTouchDownX > 100) {
                prev(v);

            } else if (mTouchDownX - mTouchUpX > 100) {
                next(v);
            }
            return true;
        }
        return false;
    }
```