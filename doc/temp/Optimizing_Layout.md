# Optimizing Layout

## Overdraw  
#### reduce transparency.-> ↓ alpha rendering    
- transparent animation   => animation 
- fad out   => animation/drawable 
- drop transparency shadows.  => bg  
- translucent alpha view text => textColor 

## Double taxation   
- Double taxation = perform more than one layout-and-measure iteration.  

### Rule  
1. Besting several instances of LinearLayout that use the layout_weight parameter can be especially expensive as each child needs to be measured twice.    
https://github.com/YingVickyCao/AndroidHacks/blob/master/doc/50AndroidHacks_Hack1.md   

2. GridView: double layout-and-measure pass ： Chid layout  - weight/grivity -> no preprocessing 

3. GridView: double layout-and-measure pass ：RelativeLayout

4. Horizontal LinearLayout: double layout-and-measure pass    

5. Vertical LinearLayout adding `measureWithLargestChild`: double layout-and-measure pass if need.  

##  Optimizing Layout Hierachies => `↑ -> ↓`       
### Rule
1. Deep layouts. ->Use flatter layouts . The default maximum depth is 10.  Best <=3.       
2.  减少`LinearLayout` -> view hierachy deeper  ->`TableLayout` / `RelativeLayout` / `ConstraintLayout`    
`>= Android 2.3(API level 9)` : `android.support.constraint.ConstraintLayout`     

- `OptimizeLayoutFragment.java`  -> `flattening the layout`        
- `fragment_reduce_hierachy_before_layout.xml`        
- `fragment_reduce_hierarchy_after_layout.xml`      
- `fragment_reduce_hierarchy_after_layout2.xml`      

3. LinearLayout(ImageView +  TextView)  -> Use compound drawables.      
`fragment_use_compound_drawable_layout.xml`    

4. Remove useless leaf.    
5. Remove useless parent.    

6. Root FrameLayout with no bg or padding  -> `<merge>`      

## Others
### Rule    

1.Remove useless attrs.        
RelativeLayout  -> LinearLayout , but still "android:layout_alignParentStart"     

2. Remove no use resources -> values,xml / drawales/ layout   

3. RelativeLayout  -> ConstraintLayout  

4. Re-using Layouts with <include/> and <merge/>       
https://github.com/YingVickyCao/AndroidHacks/blob/master/doc/50AndroidHacks_Hack2.md    

5. 使用 ViewStub 实现 view 的延迟加载      
https://github.com/YingVickyCao/AndroidHacks/blob/master/doc/50AndroidHacks_Hack2.md     

6. Making ListView Scrolling Smooth   
Use a Background Thread  + Hold View Objects in a View Holder        
- Hold View Objects in a View Holder https://github.com/YingVickyCao/AndroidHacks/blob/master/doc/50AndroidHacks_Hack25.md   

## Tools to examine and optimize layout  
### ***[TODO]Systrace***

### Hierachy Viewer  
-  一般不用。 耗时，tool没反应/出问题。
-  查找复杂界面的某个 view  -> `Layout Inspector`  
-  左-> 右， onMeasure/layout/draw.
-  红色不一定代表性能问题:相对其他 view 而言的性能，

![Hierachy_Viewer](https://yingvickycao.github.io/img/android/Hierachy_Viewer.png)

![Hierachy_Viewer](https://yingvickycao.github.io/img/android/Hierachy_Viewer2.jpg)

![Hierachy_Viewer](https://yingvickycao.github.io/img/android/Hierachy_Viewer3.jpg)

### Lint    
- Lint https://developer.android.google.cn/studio/write/lint.html
- ![Lint example](https://developer.android.google.cn/topic/performance/images/lint-display.png)

- Lint  ->  `Performance`      
- 查看current 修改     
- AS  

## Layout Inspector
- Debug Your Layout with Layout Inspector https://developer.android.google.cn/studio/debug/layout-inspector.html      

![Layout Inspector](https://developer.android.google.cn/studio/images/debug/layout-inspector-callouts_2x.png)

## Reference  
- Optimizing Layout Hierarchies  https://developer.android.google.cn/training/improving-layouts/optimizing-layout
- Performance and view hierarchies https://developer.android.google.cn/topic/performance/rendering/optimizing-view-hierarchies
- Reduce overdraw https://developer.android.google.cn/topic/performance/rendering/overdraw
- measureWithLargestChild  https://blog.csdn.net/a87b01c14/article/details/49420449
- TableLayout  www.runoob.com/w3cnote/android-tutorial-tablelayout.html    

- ConstraintLayout  https://github.com/YingVickyCao/AndroidAboutDemos/blob/master/doc/ConstraintLayout.md  