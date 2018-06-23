# AlertDialog

`AlertDialogFragment.java`

![AlertDialog](https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/android/other_ui/dialog/AlertDialog.png)
## XML 
略

### METHOD

常见函数|描述
---|---
AlertDialog.Builder.setTitle(String)、setCustomTitle(View)|设置标题
AlertDialog.Builder.setIcon()|设置 Icon
AlertDialog.Builder.setMessage()|内容区 = 普通文本
AlertDialog.Builder.setItems(items, new DialogInterface.OnClickListener() )|内容区 = 简单列表
AlertDialog.Builder..setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() )|内容区 = 单选列表
AlertDialog.Builder.setMultiChoiceItems(items, new boolean[]{false, true, false, true}, new DialogInterface.OnMultiChoiceClickListener())|内容区 = 多选列表
AlertDialog.Builder.setAdapter(new ArrayAdapter<>(getUsedContext(), R.layout.other_ui_alertdialog_customList_itemview, items), new DialogInterface.OnClickListener())|内容区 = 自定义列表
AlertDialog.Builder.setView(customView)|内容区 = 自定义View
AlertDialog.Builder.setPositiveButton("确定", new DialogInterface.OnClickListener())|添加确定按钮
AlertDialog.Builder.setNeutralButton("忽略", new DialogInterface.OnClickListener())|添加修饰按钮
AlertDialog.Builder.setNegativeButton("取消", new DialogInterface.OnClickListener())|添加取消按钮

## EVENT
在METHOD。

---
## Dialog style Activity

```
    <activity
        android:name=".other_ui.dialog.DialogStyleActivity"
        android:theme="@android:style/Theme.Material.Dialog" />
```