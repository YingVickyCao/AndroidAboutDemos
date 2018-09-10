## 2.2 使用 ViewStub 实现 view 的延迟加载

```java
 @Override   
    public void setVisibility(int visibility) {   
        // 第一次延迟初始化
        if (mInflatedViewRef != null) {   
            View view = mInflatedViewRef.get();   
            if (view != null) {   
                view.setVisibility(visibility);   
            } else {   
                throw new IllegalStateException("setVisibility called on un-referenced view");   
            }   
        } else {   
            super.setVisibility(visibility);   
            // setVisibility只是在ViewStub第一次延迟初始化时,并且visibility是非 GONE 时,调用了 inflate 方法.   
            if (visibility == VISIBLE || visibility == INVISIBLE) {   
                inflate();  
            }   
        }   
    }   
```

```java

/**     * Inflates the layout resource identified by {@link #getLayoutResource()} 
     * and replaces this StubbedView in its parent by the inflated layout resource. 
     * 
     * @return The inflated layout resource. 
     * 
     */ 
    public View inflate() { 
        final ViewParent viewParent = getParent(); 
 
        if (viewParent != null && viewParent instanceof ViewGroup) { 
            if (mLayoutResource != 0) { 
                final ViewGroup parent = (ViewGroup) viewParent; 
                final LayoutInflater factory = LayoutInflater.from(mContext); 
                final View view = factory.inflate(mLayoutResource, parent, 
                        false); 
 
                if (mInflatedId != NO_ID) { 
                   // android:inflatedId的用途
                    view.setId(mInflatedId); 
                } 
 
                final int index = parent.indexOfChild(this); 
                // ViewStub在初始化后从视图层级中移除
                parent.removeViewInLayout(this); 
 
                 // ViewStub的layoutParameters应用:如果 ViewStub 设置了LayoutParams，则使用LayoutParams。否则，忽略。
                final ViewGroup.LayoutParams layoutParams = getLayoutParams(); 
                if (layoutParams != null) { 
                    parent.addView(view, index, layoutParams); 
                } else { 
                    parent.addView(view, index); 
                }

               // mInflatedViewRef通过弱引用形式,建立ViewStub与加载的View的联系.
               mInflatedViewRef = new WeakReference<View>(view); 
 
                if (mInflateListener != null) { 
                    mInflateListener.onInflate(this, view); 
                } 
 
                return view; 
            } else { 
                throw new IllegalArgumentException("ViewStub must have a valid layoutResource"); 
            } 
        } else { 
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent"); 
        } 
    } 

```

通过阅读下面的inflate方法实现，得知：
- android:inflatedId的用途
- ViewStub在初始化后从视图层级中移除
- ViewStub的layoutParameters应用：如果 ViewStub 设置了LayoutParams，则使用LayoutParams。否则，忽略。
- mInflatedViewRef通过弱引用形式,建立ViewStub与加载的View的联系.