# Animation

## View Animation
![animator](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/resources/animator.png)

### Tween animation（补间动画）

- res/anim
- xml 以<set></set> 为根元素
- 思路：设置一个开始状态（fromXxx）、一个结束状态（toXxx）。
-
```
 android:interpolator="@android:anim/accelerate_interpolator"： 指定动画的变化速度
 android:shareInterpolator="true":让<set></set> 元素下的所有变换效果使用相同的动画速度。
 android:fillAfter="true" -  动画结束后保留变化后结果。
 android:fillBefore="false" -  动画结束保留变化前效果 。
```

- The file must have a single root element: either <set>, <objectAnimator>, or <animator>(ValueAnimator). You can group animation elements together inside the <set> element, including other <set> elements.

#### ValueAnimator
ValueAnimator：不会对UI造成改变，不能直接实现动画效果。
它本身并不会作用与任何一个属性，它可以产生你想要的各种数值。
不会对UI造成改变，不能直接实现动画效果。需要通过对动画的监听去做一些操作，在监听中将这个值设置给对应的属性，对应的属性才会改变。
ValueAnimator是针对值进行动画，支持整形，浮点型，颜色，对象等类型。

### ObjectAnimator
直接动画所给的对象,他会调用对象对应属性的get/set方法吧属性的值设置给对象的属性，直接实现动画效果。

### TimeAnimator：
这个也不直接实现动画效果，只是提供一个监听回调，返回动画执行的总时间，距离上次动画执行的时间等。

## Property Animation

## Interpolators
- 动画速度  https://www.cnblogs.com/huolongluo/p/6792362.html

## References:
- Animation  https://developer.android.google.cn/guide/topics/resources/animation-resource
- Property Animation https://developer.android.google.cn/guide/topics/graphics/prop-animation#choreography
- ValueAnimator https://developer.android.google.cn/reference/android/animation/ValueAnimator
- ValueAnimator https://www.jianshu.com/p/2966227ea0b4
- ValueAnimator https://www.cnblogs.com/huolongluo/p/6792362.html