# NumberPicker
`NumberPickerFragment.java`

## FUNC
- 让用户输入数值，通过键盘输入数值，或拖动选择数值

---

## METHOD
```
np.setMinValue(1);
np.setMaxValue(10);
np.setValue(5); // init value
NumberPicker.getValue() // 获取当前选定值，返回值int类型
NumberPicker.setFormatter(Formatter formatter) 

```
---

## EVENT

```
np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
```

```
NumberPicker.setOnScrollListener(OnScrollListener onScrollListener) 
滑动状态变化监听事件，分为3种状态：
SCROLL_STATE_TOUCH_SCROLL：用户按下去然后滑动
SCROLL_STATE_FLING： 正在滑动中的状态
SCROLL_STATE_IDLE： NumberPicker停止滑动
```