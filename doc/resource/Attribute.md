 # 属性 
 常用于自定义 View。
 
`AttributeActivity.java`   
`AlphaImageView.java`   
`res_attribution.xml`  
 
 - `attr` VS `declare-styleable`：
 
 ```
  <!-- 定义一个属性 -->
    <!--<attr name="duration" />-->
    <attr name="duration" format="integer" />

    <!-- 定义一个styleable对象来组合多个属性 -->
    <declare-styleable name="AlphaImageView">
        <attr name="duration" />
    </declare-styleable>
 ```
 
 ```
  public AlphaImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlphaImageView);
        // 获取duration参数
        int duration = typedArray.getInt(R.styleable.AlphaImageView_duration, 0);
        // 计算图像透明度每次改变的大小
        alphaDelta = 255 * SPEED / duration;
    }
 ```
 
 ```
  public AlphaImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlphaImageView);
        // 获取duration参数
        int duration = typedArray.getInt(R.styleable.AlphaImageView_duration, 0);
        // 计算图像透明度每次改变的大小
        alphaDelta = 255 * SPEED / duration;
}
 ```