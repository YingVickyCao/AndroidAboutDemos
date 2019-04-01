# Material Design主题中button的效果：底部有阴影，单击时有水波涟漪效果
API >= 21

## 1. 阴影
### 阴影是Z轴相关属性决定的

```
1. 视图的Z值由两个分量表示：
1)Elevation：静态的分量  
android:elevation
2) translation：用于动画的动态的分量
android:stateListAnimator => 动画drawable 中 android:propertyName="translationZ"

2. Z值的计算公式为：
Z = elevation + translationZ
```

##  android:elevation
elevation 值决定了Z轴方向阴影大小
- android:stateListAnimator="@null"时，设置android:elevation无效，android:background（RippleDrawable）有效：   
即android:stateListAnimator="@null"时，底部无阴影，有水波效果。

- android:stateListAnimator="@animator"时，设置android:elevation有效，android:background（RippleDrawable）有效：     
即android:stateListAnimator="@animator"时，底部阴影，有水波效果。

## 2. 水波涟漪效果
### 带约束的ripple
```
<?xml version="1.0" encoding="utf-8"?>
<ripple xmlns:android="http://schemas.android.com/apk/res/android"
    android:color="@color/ripple_material_light_app">
    <!--android:color="?android:attr/colorControlHighlight">-->
    
    <item android:drawable="@drawable/btn_default_mtrl_shape" />
</ripple>
```

- android:color : 单击button后水波涟漪效果的颜色     
- item 的Drawable: 是涟漪效果的范围，也是背景。  
涟漪效果会展示在该Drawable之上.  
shape属性是可以兼容的，并且涟漪的显示效果会因为Drawable的不同而不同.   
约束必须设置一个item的背景，该背景色是真实存在的，并且涟漪效果会受到该背景色的影响而不同:背景色和涟漪色对比越大，背景色颜色越深，涟漪效果越明显.  
当背景为透明时涟漪效果不能显示.

### 不带约束的ripple

```
<?xml version="1.0" encoding="utf-8"?>
<ripple xmlns:android="http://schemas.android.com/apk/res/android"
    android:color="@color/ripple_material_light_app" />
```

- 涟漪可以超出当前设置的View进行展示，整个涟漪其实会显示成一个逐渐放大的园，最大为当前布局的父容器边界

### 带对比色的ripple

```
<?xml version="1.0" encoding="utf-8"?>
<ripple xmlns:android="http://schemas.android.com/apk/res/android"
    android:color="@color/ripple_material_light_app">
    <!--android:color="?android:attr/colorControlHighlight">-->

    <item
        android:id="@android:id/mask"
        android:drawable="@drawable/btn_default_mtrl_shape" />
</ripple>
```

- 背景不是真实可见的，只是起到范围约束和涟漪效果影响的功能，在触发涟漪之前不会被显示出来。并且可以和普通的背景搭配使用，也就是普通的背景用于视图的显示，这种背景用于约束涟漪效果。

## Refs：
- https://blog.csdn.net/ccw0054/article/details/72845347
- https://www.jianshu.com/p/c1d17a39bc09