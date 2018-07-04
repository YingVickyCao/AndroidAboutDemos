# 资源自适应
考虑不同屏幕的适应性问题：屏幕尺寸、分辨率分辨率、屏幕方向。

## 屏幕方向
`DisableRotateActivity.java`  
- /res/layout-land/  
- /res/layout-port/  = /res/layout/ (default )  

## 屏幕尺寸  
`ScreenSizeTestActivity.java`  
- sw = smallest width	  
屏幕尺寸至少宽度       
The "smallest width" screen size qualifier allows you to provide alternative layouts for screens that have a minimum width measured in [density-independent pixels](https://developer.android.google.cn/training/multiscreen/screendensities.html#TaskUseDP)(dp or dip).  

- w = width	  
屏幕尺寸可用宽度    

```
// Use the smallest width qualifier
res/layout/main_activity.xml           	# For handsets (smaller than 600dp available width)
res/layout-sw600dp/main_activity.xml   	# For 7” tablets (600dp wide and bigger)

// Use the available width qualifier
res/layout-w600dp/main_activity.xml  	# For 7” tablets or any screen with 600dp available width (possibly landscape handsets)


// Add orientation qualifiers
res/layout/main_activity.xml               # For handsets
res/layout-land/main_activity.xml          # For handsets in landscape
res/layout-sw600dp/main_activity.xml       # For 7” tablets
res/layout-sw600dp-land/main_activity.xml  # For 7” tablets in landscape

```

```
Here's how other smallest width values correspond to typical screen sizes:
  ● 320dp: a typical phone screen (240x320 ldpi, 320x480 mdpi, 480x800 hdpi, etc).
  ● 480dp: a large phone screen ~5" (480x800 mdpi).
  ● 600dp: a 7” tablet (600x1024 mdpi).
  ● 720dp: a 10” tablet (720x1280 mdpi, 800x1280 mdpi, etc).
Figure 4 provides a more detailed view of how different screen dp widths generally correspond to different screen sizes and orientations.
```
![Figure 4. Recommended width breakpoints to support different screen sizes](https://developer.android.google.cn/images/screens_support/layout-adaptive-breakpoints_2x.png)

## 屏幕分辨率
`DipTestActivity.java`  

![Figure 1. Relative sizes for bitmaps at different density sizes](https://developer.android.google.cn/images/screens_support/devices-density_2x.png)

- Pre-scaling of resources (such as bitmap drawables)    
Pre-scaling -> pixel device used.      
Usually, you should not disable pre-scaling.   
- Auto-scaling of pixel dimensions and coordinates
- disable pre-scaling    
`res/drawable-nodpi/icon.png`  
There are some situations in which you might not want Android to pre-scale a resource. The easiest way to avoid pre-scaling is to put the resource in a resource directory with the `nodpi` configuration qualifier.   

- Use vector graphics instead    
vector graphics can scale to any size without scaling artifacts, though they're usually best for illustrations such as icons, not photographs.

## References:
- Support different screen sizes   https://developer.android.google.cn/training/multiscreen/screensizes
- Support different pixel densities  https://developer.android.google.cn/training/multiscreen/screendensities
- Declare restricted screen support  https://developer.android.google.cn/guide/practices/screens-distribution