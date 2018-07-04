# Drawable
- `ClipDrawableActivity`
- `LayerDrawableActivity`
- `ShapeDrawableActivity`
- `StateListDrawableActivity`

## StateListDrawable
![StateListDrawable](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/resources/state_list_drawable.png)

使用 android:color / android:drawable :指定颜色或 Drawable 对象。

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