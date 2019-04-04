# [2018_android_2]runtime permission

## demo 环境：
- OS： mac OS 10.13.1 (17B1003)
- android studio 3.0
- gradle 4.1
- [Code Base](//github.com/YingVickyCao/android-about-demos)   
[RuntTimePermissionTestActivity.java](https://github.com/YingVickyCao/android-about-demos/blob/master/app/src/main/java/com/hades/android/example/android_about_project/runtime_permission/RuntTimePermissionTestActivity.java)   

   classpath 'com.android.tools.build:gradle:3.0.0'  
   compile 'com.android.support:appcompat-v7:26.+'  


# 背景
## 问题：app 在 Android 6.0 (API level 23)上，使用SmsManager发送短信出错：

```
ERROR:   java.lang.SecurityException: Sending SMS message: uid 10078 does not have android.permission.SEND_SMS.
```
## 问题原因：
查android 官网发现，When run on Android 6.0 (API 23)，or targetSdkVersion Android 6.0 (API 23)，danger permission需要动态申请权限。

## 问题解决：
- 方案1 将targetSDKVersion人为地降到小于23，这样就变成了还是默认使用权限，但是这种并不是Google所推荐使用的。治标不治本。
- 方案2 When Android 6.0 (API 23)，or targetSdkVersion Android 6.0 (API 23)，实现APP支持运行时权限


# 正文

## 1. normal permissions and dangerous permissions
Android 6.0 (API 23) 开始，引入了normal permissions（普通权限） 和dangerous permissions（危险权限） 的概念。  


- [PASS]无论是normal permissions ，还是dangerous permissions ，都必须list在manifest文件中。

- [PASS]For dangerous permissions,当运行OS <=Android 5.1 (API level 22),或者targetSdkVersion <=22时，不需要做特殊的处理。因为在安装时会用户必须授权权限。如果用户不授权，system不会安装app。  
![安装时用户必须授权权限](https://yingvickycao.github.io/doc/android/runtime_permission/image.png)  

- [PASS]For dangerous permissions，当运行OS >= Android 6.0 (API level 23),或者targetSdkVersion >=23时，要做特殊的处理。使用每个dangerous permissions之前， 必须request。  
![运行时授权权限](https://yingvickycao.github.io/doc/android/runtime_permission/image2.png)  


- From android 官网：

> On all versions of Android, your app needs to declare both the normal and the dangerous permissions it needs in its app manifest, as described in Declaring Permissions. However, the effect of that declaration is different depending on the system version and your app's target SDK level:  
>> ● If the device is running Android 5.1 (API level 22)or lower, or your app's target SDKis 22 or lower(targetSdkVersion <=22) : If you list a dangerous permission in your manifest, the user has to grant the permission when they install the app; if they do not grant the permission, the system does not install the app at all. 
● If the device is running Android 6.0 (API level 23) or higher, and your app's target SDK is 23 or higher (targetSdkVersion >=23): The app has to list the permissions in the manifest, and it must request each dangerous permission it needs while the app is running. The user can grant or deny each permission, and the app can continue to run with limited capabilities even if the user denies a permission request. 


> Note: Beginning with Android 6.0 (API level 23), users can revoke permissions from any app at any time, even if the app targets a lower API level. You should test your app to verify that it behaves properly when it's missing a needed permission, regardless of what API level your app targets.  

> Note: Your app still needs to explicitly request every permission it needs, even if the user has already granted another permission in the same group. In addition, the grouping of permissions into groups may change in future Android releases. Your code should not rely on the assumption that particular permissions are or are not in the same group.

[https://developer.android.google.cn/training/permissions/requesting.html](https://developer.android.google.cn/training/permissions/requesting.html)

## 2. dangerous permissions

[https://developer.android.com/guide/topics/permissions/requesting.html#normal-dangerous](https://developer.android.com/guide/topics/permissions/requesting.html#normal-dangerous)

![ dangerous permissions](https://yingvickycao.github.io/doc/android/runtime_permission/image3.png)  


## 3. For dangerous permissions,when not needs request runtime permission?
 When device is running Android 6.0 (API level 23) or higher, and your app's target SDK is 23 or higher (targetSdkVersion >=23)
 
- Your app only needs permissions for actions that it performs directly. 
- Your app does not need permission if it is requesting that another app perform the task or provide the information.

## 4. How to request dangerous permissions?

### 重要的函数：
- checkSelfPermission()：检查是否已经具有了相关权限。
- shouldShowRequestPermissionRationale()：判断是否需要向用户解释，为什么需要这些权限。
- requestPermissions() ：申请相关权限

### Sample
[RuntTimePermissionTestActivity.java](https://github.com/YingVickyCao/android-about-demos/blob/master/app/src/main/java/com/hades/android/example/android_about_project/runtime_permission/RuntTimePermissionTestActivity.java)

RuntTimePermissionTestActivity.java 以SEND_SMS（发送短信）、RECEIVE_SMS（使用SMSReceiver接收短信）、permission_group.SMS 为例子。

### android SMS 是什么缩写
SMS是Short Messaging Service（短消息服务）的缩写，是一种使用移动设备可以发送和接收文本信息的技术。

### 以例子说明如何request runtime permission.

#### Step1，检查Permission是否granted。  
```
PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), permission)
```  
true，已经授权，直接执行doSendMessage。  
false，没有授权，执行下一步检查是否需要显示请求权限原因。  


- (1) 检查一个Permission是否granted:  

```
PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS)
```

- (2) 检查多个Permissions是否granted:
```
PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) 
&&
PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECEIVE_SMS)
```

- (3) 检查permission_group是否granted:
```
PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), Manifest.permission_group.SMS) 
```

#### Step2，检查Permission是否显示请求权限原因。  
true，需要显示请求权限原因，显示一个dialog/Snarkbar，允许用户做选择。  
false，不需要显示请求权限原因，直接执行requestPermissions。  

- （1）检查一个Permission是否显示请求权限原因：
ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.SEND_SMS)

- （2）检查多个Permissions是否显示请求权限原因：
ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.SEND_SMS) 
&&
ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.RECEIVE_SMS)

- （2）检查permission_group是否显示请求权限原因：
ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission_group.SMS)

#### Step3，Request permission
ActivityCompat.requestPermissions 调用后，在onRequestPermissionsResult()中根据requestCode判断是否grandted,并做对应处理。
```
public static void requestPermissions(final @NonNull Activity activity,final @NonNull String[] permissions, final @IntRange(from = 0) int requestCode)
```
说明：  
(1) permissions：是一个String[]，可以放一个或多个permission。  
(2) requestCode：在onRequestPermissionsResult()中处理。每次请求request permissions时requestCode设置要不同。

- (1) request一个permission：
```
ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_MESSAGE);
```

- (2) request多个permissions：
```
ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_SMS}, REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_RECEIVE_MESSAGE);
```

- (3)使用permission_group申请：
```
ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission_group.SMS}, REQUEST_CODE_4_REQUEST_PERMISSIONS_GROUP_4_SMS)
```

#### Step4 处理 request Permission返回结果

```
 @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: requestCode=" + requestCode);
        switch (requestCode) {
            case REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_MESSAGE: {
                onRequestPermissionsResult4SendMessage(requestCode, permissions, grantResults);
                break;
            }

            default:
                // other 'case' lines to check for other permissions this app might request
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

  private void onRequestPermissionsResult4SendMessage(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_MESSAGE != requestCode) {
            Log.e(TAG, "onRequestPermissionsResult4SendMessage: requestCode is wrong");
            return;
        }
        // Check if the only required permission has been granted
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           // 授权了，执行task，此处是发送短信操作。
            doSendMessage();
            // Send message permission has been granted, Message can be sent.
            Log.i(TAG, "Send message permission has now been granted. Can send message.");

            // 授权了，给出对应提示信息。
            showPermissionAvailable4SendMessage();

        } else {
            // permission denied, boo! Disable the functionality that depends on this permission.
            Log.i(TAG, "Send message permission was NOT granted.");
           // 没有被授权，给出对应提示信息。
            showPermissionsNotGranted();
        }
    }
```

## 5. request runtime permission 流程总结
![process chart for request runtime permission](https://yingvickycao.github.io/doc/android/runtime_permission/process_chart_4_request_runtime_permission.png)


6 总结
  - 不要使用Permission Group来request dangerous permissions。原因是将来API 不同，permissions groups可能不同。
  
- 仅仅申请需要的权限，申请太多不要的dangerous permissions可能会使得用户感觉app不安全，导致用户卸载app。
  
- Show dangerous permissions rationale，简洁明了。

- 任何时候APP都要在执行危险权限前去检查是否具有相关权限，即使刚刚执行过这项操作，因为用户很有可能去设置应用中关闭了相关权限。

- [pass]申请权限时，如果组内有别的权限已经获得了用户授权，系统不再弹出询问对话框，而是自动授权该权限。  
例如，申请Manifest.permission.SEND_SMS权限时，用户已经授权了Manifest.permission.SEND_SMS权限，系统则会自动授权group SMS中的所有权限，不再询问用户；

- 兼容问题：Use the Android Support Library to check for, and request, permissions.  

checkSelfPermission和requestPermissions从API 23才加入，低于23版本，需要在运行时判断。  

使用Support Library v4中提供的方法，可以避免判断。  
```
ContextCompat.checkSelfPermission
ActivityCompat.requestPermissions
ActivityCompat.shouldShowRequestPermissionRationale
```
例如：使用ContextCompat.checkSelfPermission ， 而不是Activity.checkSelfPermission()。google 官网推荐这种方式。


## 遗留的问题：
  
1. Android官方开发指导还提到一点，为避免给用户带来糟糕的用户体验，shouldShowRequestPermissionRationale 这里的解释说明应该是异步的，不要阻塞用户的操作。  
没有看懂是什么意思。  
大家知道了，可以告诉我，我会非常感谢。


# Ref
- [https://developer.android.com/training/permissions/index.html](://developer.android.com/training/permissions/index.html)
- [https://developer.android.com/training/permissions/declaring.html](https://developer.android.com/training/permissions/declaring.html)
- [https://developer.android.com/training/permissions/requesting.html](https://developer.android.com/training/permissions/requesting.html)
-[https://developer.android.com/guide/topics/permissions/requesting.html#normal-dangerous](https://developer.android.com/guide/topics/permissions/requesting.html#normal-dangerous)  
PS: 中国访问, 把com -> google.cn 就可以打开了。  


- [http://www.jianshu.com/p/0beb6243d650](http://www.jianshu.com/p/0beb6243d650)
- [http://www.jianshu.com/p/e1ab1a179fbb/](http://www.jianshu.com/p/e1ab1a179fbb/)
- [https://inthecheesefactory.com/blog/things-you-need-to-know-about-android-m-permission-developer-edition/en](https://inthecheesefactory.com/blog/things-you-need-to-know-about-android-m-permission-developer-edition/en)
- [http://www.jianshu.com/p/f346b7446610](http://www.jianshu.com/p/f346b7446610)  

  EasyPermissions
- [https://www.cnblogs.com/whycxb/p/6818685.html](://www.cnblogs.com/whycxb/p/6818685.html)

  rxpermissions
- [https://github.com/tbruyelle/RxPermissions](https://github.com/tbruyelle/RxPermissions)

  android send sms  
- [http://www.cnblogs.com/huhx/p/sendMessage.html](http://www.cnblogs.com/huhx/p/sendMessage.html)

    snackbars： 
- [http://wiki.jikexueyuan.com/project/material-design/components/snackbars-and-toasts.html](http://wiki.jikexueyuan.com/project/material-design/components/snackbars-and-toasts.html)
- [http://blog.csdn.net/qq_22706515/article/details/51151654](http://blog.csdn.net/qq_22706515/article/details/51151654)
- [http://blog.csdn.net/sdjianfei/article/details/51583023](http://blog.csdn.net/sdjianfei/article/details/51583023)

  Android Support v4，v7，v13的区别以及 v4，v7包冲突问题

- [http://blog.csdn.net/shuaiyou_comon/article/details/75425639?locationNum=5&fps=1](http://blog.csdn.net/shuaiyou_comon/article/details/75425639?locationNum=5&fps=1)