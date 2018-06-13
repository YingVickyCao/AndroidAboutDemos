# RatingBar

`RatingBarFragment.java`  

## XML 
```
    <!-- android:isIndicator 是否允许用户修改。true 为不允许修改 -->
    <!-- android:numStars 总共多少个星级-->
    <!-- android:rating 默认星级 -->
    <!-- android:stepSize 设置每次至少需要修改多少个星级 -->

    <!-- android:max 和 android:progress 是否添加没有影响 -->
    <!-- android:max 评分最大值，继承 ProgressBar，没有任何问题。 -->
    <!-- android:progress 当前进度，继承 ProgressBar，没有任何问题。 -->
    <RatingBar
        android:id="@+id/rating"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:max="255"
        android:isIndicator="false"
        android:numStars="5"
        android:rating="3"
        android:progress="255"
        android:stepSize="0.5" />

```

### Ratingbar自定义替换系统图片

`style.xml`
```
  <style name="roomRatingBar" parent="@android:style/Widget.RatingBar">
        <item name="android:progressDrawable">@drawable/ratingbar_drawable</item>
        <item name="android:minHeight">48dip</item>
        <item name="android:maxHeight">48dip</item>
    </style>
```


`ratingbar_drawable.xml`
```
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@android:id/background"
        android:drawable="@android:drawable/button_onoff_indicator_off" />
    <item
        android:id="@android:id/secondaryProgress"
        android:drawable="@android:drawable/button_onoff_indicator_off" />
    <item
        android:id="@android:id/progress"
        android:drawable="@android:drawable/button_onoff_indicator_on" />

</layer-list>
```

`widget_rating_bar.xml`
```
 <RatingBar
        style="@style/roomRatingBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:isIndicator="false"
        android:max="255"
        android:numStars="5"
        android:progress="255"
        android:rating="3"
        android:stepSize="0.5" />
```
## Event 
 - `setOnRatingBarChangeListener`

## References:
- Android Ratingbar自定义替换系统图片 https://blog.csdn.net/u011179438/article/details/52447233
