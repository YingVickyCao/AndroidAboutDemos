# ViewAnimator

![ViewAnimator以及子类](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/widget/ViewAnimation.png)

## 说明
- ViewAnimator将多个 View“叠”在一起。在切换 View 时显示指定的动画效果。

## XML属性

![ViewAnimator XML属性](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/widget/ViewAnimation2.png)

## Common FUNS  
- `switcher.setInAnimation(getActivity(), R.anim.slide_in_right);` // 标识显示视图时使用的动画
- `switcher.setOutAnimation(getActivity(), R.anim.slide_out_left);` // 标识隐藏视图时使用的动画
- `switcher.showPrevious();` // 显示上一屏
- `switcher.showNext();` // 显示下一屏