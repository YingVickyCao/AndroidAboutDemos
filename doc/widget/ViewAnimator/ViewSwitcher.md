# ViewSwitcher
- `ViewSwitcherFragment.java`

## XML

```
    <!-- android:animateFirstView	定义 ViewAnimation 首次显示时是否对当前视图应用动画  -->
    <!-- android:inAnimation 标识显示视图时使用的动画 -->
    <!-- android:outAnimation 标识隐藏视图时使用的动画 -->
    <ViewSwitcher
        android:id="@+id/jumpViewSwitcher"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:animateFirstView="false"
        android:inAnimation="@anim/slide_in_right"
        android:outAnimation="@anim/slide_out_left" />
```

## References:
- [Android]笔记20:ViewAnimator-仿android系统launcher界面  https://blog.csdn.net/david_520042/article/details/76066651