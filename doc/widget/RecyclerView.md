
# RecyclerView

## PO
### `RecyclerView.setHasFixedSize(true)`
当 Item 的改变不影响RecyclerView的宽高时，设置setHasFixedSize(true)，并通过增删改插的方法刷新RecyclerView。而不是任何数据变动均通过`notifyDataSetChanged()`. 仅仅当宽高改变时，使用`notifyDataSetChanged()`整体刷新 list。

- 增删改插的方法：  
```
notifyItemChanged(int)
notifyItemInserted(int)
notifyItemRemoved(int)

notifyItemRangeChanged(int, int)
notifyItemRangeInserted(int, int)
notifyItemRangeRemoved(int, int)
```

- 原理：
1. 增删改插的方法：    
=>  
```  
onItemRangeChanged();
onItemRangeInserted();
onItemRangeMoved();
onItemRangeRemoved();
```
=> RecyclerView.java - `triggleUpdateProcesssor()`
```
void triggerUpdateProcessor() {
    // 根据mHasFixedSize判断是否requestLayout()
    if (POST_UPDATES_ON_ANIMATION && mHasFixedSize && mIsAttached) {
        ViewCompat.postOnAnimation(RecyclerView.this, mUpdateChildViewsRunnable);
    } else {
        mAdapterUpdateDuringMeasure = true;
        requestLayout();
    }
}
```  
=> `requestLayout()`?

2. `notifyDataSetChanged()`
=> `onChanged()`   
```
@Override
        public void onChanged() {
            assertNotInLayoutOrScroll(null);
            mState.mStructureChanged = true;

            setDataSetChangedAfterLayout();
            if (!mAdapterHelper.hasPendingUpdates()) {
                requestLayout();
            }
        }
```
=> `requestLayout()`:整体刷list

- `RecyclerView setHasFixedSize(true)`的意义  https://blog.csdn.net/wsdaijianjun/article／details/74735039   

## 上下滑动RecyclerView 边缘时，有阴影？去掉?
```
android:overSchollMode="never"
```

## 滑动RecyclerView时，不显示滚动条。 How？
```
android:scrollbars="none"
```

##  Choose CardView or RecyclerView?  

## Communicating with Other Fragments
- Fragment -> Activity -> Other Fragments


## LayoutManager
- `LinearLayoutManager`
- `GridLayoutManager`

## ViewHolder 重复利用率？
The view holder  objects are managed by an adapter.    
The adapter creates vuew holders as need.   

- data num = 50
```
public void onBindViewHolder(final ViewHolder holder, int position) 
```
- one screen view item  = 13
- create view count = 19 = 13 + 3 + 3;  
```
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
```

## QA: `app:layoutManager` 不起作用? 
If RecyclerView = root view, it works.
```
<!-- QA: app:layoutManager 不起作用?If RecyclerView = root view, it can work -->
<!-- QA: tools:listitem 不起作用? If RecyclerView = root view, it can work -->
<android.support.v7.widget.RecyclerView
    android:id="@+id/list"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_below="@+id/subject"
    android:layout_marginLeft="16dp"
    android:layout_marginRight="16dp"
    app:layoutManager="android.support.v7.widget.LinearLayoutManager"
    tools:listitem="@layout/widget_recyclerview_dummy_item" />
```

## QA: `tools:listitem 不起作用? 
If RecyclerView = root view, it works.
```
<!-- QA: app:layoutManager 不起作用?If RecyclerView = root view, it can work -->
<!-- QA: tools:listitem 不起作用? If RecyclerView = root view, it can work -->
<android.support.v7.widget.RecyclerView
    android:id="@+id/list"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_below="@+id/subject"
    android:layout_marginLeft="16dp"
    android:layout_marginRight="16dp"
    app:layoutManager="android.support.v7.widget.LinearLayoutManager"
    tools:listitem="@layout/widget_recyclerview_dummy_item" />
```

## TBD:`addItemDecoration()` not work?

## `android:nestedScrollingEnabled="false"`:滑动时粘连

## References:
- https://medium.cn/@ipaulpro/drag-and-swipe-recyclerview-b9456d2b1aaf
- https://medium.cn/@ipaulpro/drag-and-swipe-recyclerview-6a6f0c422efd