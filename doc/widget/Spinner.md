# Spinner
# 1 Spinner 属性s
## 1.1 Show style
```
android:spinnerMode="dropdown" 
android:spinnerMode="dialog"
```
## 1.2 Spinner popup not cover action bar
- `android:dropDownVerticalOffset="@dimen/size_50"`
- android:dropDownHorizontalOffset——spinnerMode=”dropdown”时，下拉的项目选择窗口在垂直方向相对于Spinner窗口的偏移量 
对
## 1.3 Custom Spinner background
`android:background="@android:color/holo_blue_bright"`

## 1.4 Custom Spinner popup background,  background and text color of default and selected Item 
`public View getDropDownView(int position, View convertView, ViewGroup parent)`

## 1.5 android:dropDownWidth
在spinnerMode=”dropdown”时，设定下拉框的宽度

## 1.6 android:popupBackground:
在spinner=”dropdown”时，设置下拉列表的背景。 

## 1.7 事件
- setOnItemSelectedListener
- Custom in Adapter

## 1.8 android:prompt
error: failed linking file resources.

## 1.9 默认显示上次位置
`setSelection(pos);`

## 1.10 下拉箭头颜色
`android:background="@drawable/spinner_background_material"`

## 2. Spinner TBD
```
  <Spinner
            android:id="@+id/spinnerWithCustomDropdown3"
            android:layout_width="match_parent"
            android:layout_height="@dimen/size_50"
            android:background="@drawable/spinner_background_material"
            android:dropDownWidth="100.0dp"
            android:dropDownVerticalOffset="@dimen/size_50"
            android:dropDownSelector="@drawable/list_choice_background_material"
            android:entries="@array/phoneType"
            android:popupBackground="@drawable/popup_background_material" />
```

- 利用`android:background="@drawable/spinner_background_material"`只修改了下拉箭头，没有修改背景色
- 利用`android:dropDownSelector="@drawable/list_choice_background_material"`修改了 fouced popup item 的背景色，没有修改foucs popup Item 的背景颜色
- `android:popupBackground="@drawable/popup_background_material"`只修改了整个popup 背景色

`simple_spinner_dropdown_item.xml`
```
<?xml version="1.0" encoding="utf-8"?>
<CheckedTextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@android:id/text1"
    style="@style/Widget.Material.Light.DropDownItem.Spinner_app"
    android:layout_width="match_parent"
    android:layout_height="48dip"
    android:ellipsize="marquee"
    android:singleLine="true"
    android:textColor="@color/selector_btn_click_text_color" />
<!--  没有用。因为没有选中状态 -->
    <!--android:textColor="@color/primary_text_disable_only_material_light_app" />-->
```
- `android:textColor="@color/selector_btn_click_text_color"`只修改了selected popup Item的 字体颜色，没有修改focus popup Item的字体颜色

# Refs
```
android.R.layout.simple_spinner_dropdown_item
android.R.layout.simple_spinner_item

 <style name="Widget.Material.Spinner" parent="Widget.Spinner.DropDown">
        <item name="background">@drawable/spinner_background_material</item>
        <item name="dropDownSelector">?attr/listChoiceBackgroundIndicator</item>
        <item name="popupBackground">@drawable/popup_background_material</item>
        <item name="popupElevation">@dimen/floating_window_z</item>
        <item name="popupAnimationStyle">@empty</item>
        <item name="popupEnterTransition">@transition/popup_window_enter</item>
        <item name="popupExitTransition">@transition/popup_window_exit</item>
        <item name="dropDownVerticalOffset">0dip</item>
        <item name="dropDownHorizontalOffset">0dip</item>
        <item name="overlapAnchor">true</item>
        <item name="dropDownWidth">wrap_content</item>
        <item name="popupPromptView">@layout/simple_dropdown_hint</item> // error: attribute android:popupPromptView is private. error: failed linking file resources.
        <item name="gravity">start|center_vertical</item>
        <item name="disableChildrenWhenDisabled">true</item>
    </style>
```