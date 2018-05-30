# ScrollView
- `NestedScrollViewHasRecycleViewFragment.java` 
- `ScrollViewHasListViewFragment.java` 

## ScrollView + ListView
### QA：ListView显示不全    
当ScrollView里的元素要填满ScrollView时，使用"match_parent"是不管用的，为ScrollView设置android:fillViewport="true"。  

### QA: ScrollView 中嵌套一个ListView，仅仅ListView可以滑动，ScrollView不可以滑动。 
***解决1：重写ListView，重写计算高度为item * itemHeight***    
Depressed，因为重写之后，不一定彻底解决，不同设备可能出现各种各样的问题。

解决2：ScrollView + ListView -> `android.support.v4.widget.NestedScrollView`  + RecyclerView

## NestedScrollView + RecyclerView
- `android.support.v4.widget.NestedScrollView`  + RecyclerView
- android:nestedScrollingEnabled="false" : 滑动时连贯

```
<!-- android:nestedScrollingEnabled="false"  : 滑动时连贯 -->
    <android.support.v7.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:nestedScrollingEnabled="false" />
``` 
Never add a RecyclerView or ListView to a scroll view. Doing so results in poor user interface performance and a poor user experience.

## References:
- ScrollView https://developer.android.google.cn/reference/android/widget/ScrollView
- NestedScrollView https://developer.android.google.cn/reference/android/support/v4/widget/NestedScrollView
- https://www.jianshu.com/p/791c0a4acc1c
- https://blog.csdn.net/u012124438/article/details/53495951