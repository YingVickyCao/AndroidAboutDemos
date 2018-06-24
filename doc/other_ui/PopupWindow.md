# PopupWindow
`PopupWindowFragment.java`

## FUNC
创建对话框风格的窗口

## METHOD
```
  private void showPopupWindow(View v) {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.other_ui_popupwindow_popup, null);
        final PopupWindow popup = new PopupWindow(root, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        /**
         * 点击空白区域， dismiss PopupWindow。
         */
        popup.setOutsideTouchable(true);
        popup.setAnimationStyle(android.R.style.Animation_Translucent);
        root.findViewById(R.id.dismissPopupWindow).setOnClickListener(
                v1 -> {
                    popup.dismiss();
                });

        // 以下拉方式显示
//        popup.showAsDropDown(v);
        //将PopupWindow显示在指定位置
        popup.showAtLocation(mBtn, Gravity.CENTER, 20, 20);
    }
```
---
## References:
- https://www.jianshu.com/p/799dbb86f908
- https://www.cnblogs.com/jzyhywxz/p/7039503.html