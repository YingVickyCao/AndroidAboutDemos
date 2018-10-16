# Build Android Native Development

---

## Reference
- [Installing the Android Development Environment](https://spring.io/guides/gs/android/#android-dev-env)
- [androiddevtools](http://www.androiddevtools.cn)
- [Android China](https://developer.android.google.cn/index.html)

---

## Used Common Command

### Mac
- 启动Terminal终端工具,在/Users/你的用户名 中创建`.bash_profile` File  
`touch ~/.bash_profile`

-  打开并编辑 `.bash_profile` File  
`vim ~/.bash_profile`  
OR  
`open ~/.bash_profile`

- 执行`source`命令,把 `.bash_profile` File 中配置的命令写入系统。  
`source ~/.bash_profile`

说明：
- ~表示用户目录，即/Users/你的用户名/
- 如果不执行`source`命令， `.bash_profile` File 中配置的命令无效。   
- 如果在 `Terminal` 中直接使用export 设置命令，则效果时临时的。   
把关闭并打开新`Terminal`窗口，会发现在 `Terminal` 中直接使用export 设置命令已经不再有效。  
所以，正确的设置方法是：  
使用`.bash_profile` File 配置命令，并执行`source`命令使之永久生效。  

### Win7
cmd中输入该命令，用于跳转到环境变量设置窗口：   
`rundll32 sysdm.cpl,EditEnvironmentVariables`

### Win10
同Win7

---

## 1. JDK

### 设置JDK的环境变量

#### MAC
Mac 不需要设置JDK的环境变量。安装时自动设置。  

#### Win7 

- JAVA_HOME = D:\Program Files\Java
- CLASSPATH = .;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;
- Path = %JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;

#### Win10
- JAVA_HOME = D:\Program Files\Java
- Path = %JAVA_HOME%\bin        
 Path = %JAVA_HOME%\jre\bin     

查看Win10 内部版本号：Win10 win+R 打开“运行”。【win+R】 打开“运行” -> winver    => Version1709(OS Build 1699.125)，则Win10的版本是7    
[查看Win10 内部版本号](https://jingyan.baidu.com/article/4f34706e1f0754e387b56db7.html)

####  测试JDK的环境变量：
- java
- javac
- java -version

---

## 2. SDK
### 配置SDK 环境变量：

#### Mac
-  `export ANDROID_HOME=~/Library/Android/sdk`
-  `export PATH=${PATH}:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools`

**使用` echo $ANDROID_HOME `检查此变量是否已正确设置。**

#### Linux
- `export ANDROID_HOME=/<installation location>/android-sdk-linux`
- `export PATH=${PATH}:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools`

#### Win7
- `set ANDROID_HOME=D:\android-sdk-windows`
- `set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools`

OR

- ANDROID_HOME = D:\android-sdk-windows
- PATH = %ANDROID_HOME%\tools\;%ANDROID_HOME%\platform-tools\;

#### Win10 
- ANDROID_HOME = D:\android-sdk-windows
- PATH = %ANDROID_HOME%\tools\
- PATH = %ANDROID_HOME%\platform-tools\

### 配置AVD模拟器的缓存路径的环境变量：
#### MAC
无

#### Win7 
ANDROID_SDK_HOME = D:\ANDROID_SDK_HOME

#### Win10
同Win7

### 测试SDK的环境变量 ：
- android 
- android -h
- adb

### Android SDK Manager  代理配置
`mirrors.zzu.edu.cn` 端口：`80`      
[androiddevtools](http://www.androiddevtools.cn)

说明：  
现在Android在中国有Android SDK在线更新镜像服务器，所以不需要设置。  
如果中国Android SDK在线更新镜像服务器不能使用时，需要进行Android SDK Manager  代理配置。

---

## 3. NDK
### MAC
```
# NDk
export NDK_HOME=~/Library/Android/sdk/ndk-bundle
export PATH=$PATH:$NDK_HOME/
```

使用echo $NDK_HOME

#### 检查是否配置成功
```
$ cd $NDK_HOME
$ ndk-build
Android NDK: Could not find application project directory !    
Android NDK: Please define the NDK_PROJECT_PATH variable to point to it.   
```
---

## 4.Gradle
### 配置Gradle 环境变量：
#### Mac OS X
- `export GRADLE_HOME=~/Library/gradle`
- `export PATH=$PATH:$GRADLE_HOME/bin`

#### Win7
- `set GRADLE_HOME=D:\Program Files\gradle`
- `set PATH=%GRADLE_HOME%\bin`

#### Win10
- GRADLE_HOME=D:\Program Files\gradle
- PATH=%GRADLE_HOME%\bin;

### 配置gradle的本地仓库地址

#### MAC
GRADLE_USER_HOME = /Users/hades/.gradle

#### Win7
GRADLE_USER_HOME = D:\Users\hades\.gradle

#### Win10
同Win7

### 测试Gradle的环境变量：
gradle -v 