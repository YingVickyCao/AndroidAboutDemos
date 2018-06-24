# Notification
`NotificationFragment.java`

## 功能
style
- 普通通知
- 下载进度的通知
- BigTextStyle
- InboxStyle
- BigPictureStyle
- hangup横幅通知
- MediaStyle
- 自定义通知栏布局

通过`NotificationManager`发送通知。

---
## QA: notify 只显示一条？

`NotificationManager.notify(int id, Notification notification)`,若 id 一样，则只显示一条 = 自动替换新的内容。

---
## QA: java.lang.IllegalArgumentException: Invalid notification (no valid small icon)
Small Icon 必须有。高版本（如Android7.0）中仅仅显示Small Icon，不显示Ticker。

---
## QA:Android中getResources().getDrawable() Deprecated?

https://blog.csdn.net/zheng0203/article/details/62909177

---
## References:
- Android Notification常见样式总结
 https://blog.csdn.net/w804518214/article/details/51231946