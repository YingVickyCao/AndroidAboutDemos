# SearchView

`SearchViewFragment.java`

## XML 
常见XML 属性|描述
---|---
android:iconifiedByDefault|是否自动缩小为图标
android:queryHint|提示文本

### METHOD

常见函数|描述
---|---
setIconifiedByDefault(bool)|是否自动缩小为图标
setSubmitButtonEnabled(bool)|是否显示搜索按钮
setQueryHint|提示文本

## EVENT
```
mSv.setOnQueryTextListener(new SearchView.OnQueryTextListener())
```