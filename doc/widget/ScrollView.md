# ScrollView
- `NestedScrollViewHasRecycleViewFragment.java` 
- `ScrollViewHasListViewFragment.java` 
- `ScrollViewAboveListViewFragment.java`

## Rule 
Never add a RecyclerView or ListView to a scroll view. Doing so results in poor user interface performance and a poor user experience.  

## 情况1：Only ListView

## 情况2：Top ListView Below ScrollView  
- 各自滑动不受影响  
https://yingvickycao.github.io/img/android/tdd/ScrollView_above_ListView.mp4

## 情况3：Top ScrollView Below ListView => = 情况2
- 各自滑动不受影响  

## 情况4：ScrollView has ListView    
- => ScrollView 中嵌套一个ListView / ScrollView + ListView  
- 目的：滑动整体。  
- 实际上会出现各种问题。  

### 问题1：ListView显示不全    
当ScrollView里的元素要填满ScrollView时，使用"match_parent"是不管用的，为`ScrollView`设置`android:fillViewport="true" 。  
https://yingvickycao.github.io/img/android/tdd/ScrollView_has_ListView_not_full_height.mp4
https://yingvickycao.github.io/img/android/tdd/ScrollView_has_Listview.mp4

### 问题2: 仅仅ListView可以滑动，ScrollView不可以滑动。 
***解决1：重写ListView，重写计算高度为item * itemHeight***    
Depressed，因为重写之后，不一定彻底解决，不同设备可能出现各种各样的问题。

解决2：ScrollView + ListView -> `android.support.v4.widget.NestedScrollView`  + RecyclerView

### ***问题3：ListView获取了焦点***  


## ScrollView + ListView -> NestedScrollView + RecyclerView
- `android.support.v4.widget.NestedScrollView`   
https://yingvickycao.github.io/img/android/tdd/NestedScrollview_has_RecycleView.mp4

### 问题1：整体滑动不连贯 

```
<!-- android:nestedScrollingEnabled="false"  : 滑动时连贯 -->
    <android.support.v7.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:nestedScrollingEnabled="false" />
``` 

### ***问题2：RecyclerView获取了焦点***  


---
## QA: After add fragment nested ScrollView, fragment swipe whole page when having too many items?

- `WrongSwipeWhoPageActivity.java`
- `WrongSwipeWhoPageFragment.java`

```
<ScrollView>
    <LinearLayout>
         <LinearLayout
            android:id="@+id/content"
            android:orientation="vertical" />
            
         <FrameLayout
            android:id="@+id/fragmentRoot"
            android:orientation="vertical" />
            
    </LinearLayout>
    
</ScrollView>
```

- Fragment可以看成一个特殊的view.     
ScrollView 的子 views 超出屏幕，就会 滑动。因此。要解决这种 bug，调整成 ScrollView和FrameLayout 为同层级即可。


=>

```
<FrameLayout>
    <ScrollView
       android:id="@+id/content">
    </ScrollView>

    <FrameLayout
        android:id="@+id/fragmentRoot"
        android:orientation="vertical" />
</FrameLayout>
```

## References:
- ScrollView https://developer.android.google.cn/reference/android/widget/ScrollView
- NestedScrollView https://developer.android.google.cn/reference/android/support/v4/widget/NestedScrollView
- https://www.jianshu.com/p/791c0a4acc1c
- https://blog.csdn.net/u012124438/article/details/53495951