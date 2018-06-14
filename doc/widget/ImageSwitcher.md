# ImageSwitcher

- `ImageSwitcherFragment.java`

## 说明
- ImageSwitcher使用工厂模式创建需要的ImageView。
- ImageSwitcher提供了一个ViewFactory，该ViewFactory生成的 View 组件必须是 ImageView。
- 切换图片时，调用ImageSwitcher 的 setImageDrawable()/setImageResource()/setImageURI(Uri uri) 更换图片。

- ImageSwitcher VS ImageView：  
相同点：均用于显示图片
不同点：ImageSwitcher可指定切换图片时的动态效果，效果更炫。

## Common Funcs

- `imageSwicher.setFactory()`  
- `imageSwicher.setOnTouchListener(this::onTouch2)`
- `imageSwicher.setInAnimation()`
- `imageSwicher.setOutAnimation()`
- `imageSwicher.setImageResource(arrayPictures[pictureIndex])`

```
/**
 * ImageSwitcher 设置ViewFactory，使用makeView（）返回 ImageView
 */
imageSwicher.setFactory(this::makeView2);

/**
 * 设置ImageSwitcher左右滑动事件
 */
imageSwicher.setOnTouchListener(this::onTouch2);

```

```
public View makeView2() {
    ImageView imageView = new ImageView(getActivity());
    imageView.setImageResource(arrayPictures[pictureIndex]);
    return imageView;
}

```

```
* 设置图片切换的动画
* 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left_2和slide_in_right_2
*/
imageSwicher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left_2));
imageSwicher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right_2));

/**
 * 设置当前要看的图片
 */
imageSwicher.setImageResource(arrayPictures[pictureIndex]);
```
## References：
- Android中ImageSwitcher详解 http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2013/0305/966.html