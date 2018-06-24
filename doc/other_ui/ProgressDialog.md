# ProgressDialog
- `ProgressDialogFragment.java`

- ProgressDialog = AlertDialog + ProgressBar

- 有两种形式：  
环形风格 - ProgressDialog.STYLE_SPINNER    
水平风格（模糊进度、进度） - ProgressDialog.STYLE_HORIZONTAL

## METHOD
- 即使`ProgressDialog.setCancelable(false)`;  
当`setButton()`后，点击了 Button后ProgressDialog消失，点击空白区域不消失。  

```
  public void showIndeterminateBarProgressDialogWithBtns(View source) {
        ProgressDialog pd = new ProgressDialog(getUsedContext());
        pd.setIcon(R.drawable.tools);
        pd.setTitle("任务正在执行中");
        pd.setMessage("任务正在执行中，敬请等待...");
        pd.setCancelable(true);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 是否模糊显示进度
        pd.setIndeterminate(true);
        pd.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        pd.dismiss();
                    }
                });
        pd.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        pd.show();
    }
                
```