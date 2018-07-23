# Drawable
- `ClipDrawableActivity`
- `LayerDrawableActivity`
- `ShapeDrawableActivity`
- `StateListDrawableActivity`

## QA:android drawable 最小支持API
`drawable-v21` : API >= 21

## QA:`android:foreground` vs `android:backgroud`
`android:foreground`:    
- 前景色。它指定的 drawable 在 View 的上方绘制的。
- 做透明遮罩层    
- When >= Android 6.0(23) , View support.   
- When < Android 6.0(23) , Only FrameLayout support.  

- 小Demo小知识-`android:foreground`与`android:backgroud` htts://www.jianshu.com/p/b5ecd39ed494  

## StateListDrawable
![StateListDrawable](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/resources/state_list_drawable.png)

使用 android:color （Text Color）/ android:drawable(background/foreground/src) :指定颜色或 Drawable 对象。

## LayerDrawable
- LayerDrawable包含一个 Drawable 数组，按照Drawable对象的数组顺序来绘制它们，索引最大的Drawable 对象将会被绘制在最上面。
- android:id：为 Drawable 对象指定一个标识。
- android:buttom/top/left/right：用于指定一个长度，用于指定将 Drawable 绘制到 目标组件的指定位置。  

## ShapeDrawable
- 定义一个几何形状（如矩形、圆形、线形等）。
![ShapeDrawable](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/resources/shape_drawabe.png)

## ClipDrawable
- ClipDrawable代表从其他位图上截取的一个“图片片段”。
![ClipDrawable](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/resources/clip_drawable.png)

## LevelListDrawable 
`item_view_check_icon.xml`  

- 管理一组轮流替换的 drawables
- `android:maxLevel="0"`默认显示
- setLevel()= `android:maxLevel`值为level的 drawable。
- `level-list` - LevelListDrawable -> Only used by `ImageView`-`src`  

```
<?xml version="1.0" encoding="utf-8"?>
<level-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:drawable="@android:drawable/star_off"
        android:maxLevel="0" />

    <item
        android:drawable="@android:drawable/star_on"
        android:maxLevel="1" />
</level-list>
```
## References:
- Android 样式的开发：drawable 汇总篇 https://blog.csdn.net/kite30/article/details/52554309