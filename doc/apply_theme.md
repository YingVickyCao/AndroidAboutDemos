# Apply theme

## Usage 

### Step1: Define one attr
`styles.xml`
```
<attr name="pageRootBg" format="reference" />`
```

### Step2: Use attr in style and layout

`theme_choose_page.xml`
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="?attr/pageRootBg">

</RelativeLayout>
```

`styles.xml`  
```
 <style name="AppTheme.Red">
        <item name="pageRootBg">@android:color/holo_red_dark</item>
</style>

<style name="AppTheme.Green">
        <item name="pageRootBg">@android:color/holo_green_dark</item>
</style>
```

### Step3: set theme in activity 
`ThemeChoosePageAActivity.java`
```
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.theme_choose_page);
 }
 
void setTheme() {
    boolean isRedTheme = SFMock.getInstance().isRedTheme();
    if (isRedTheme) {
        setTheme(R.style.AppTheme_Red);
    } else {
        setTheme(R.style.AppTheme_Green);
    }
}
```
### Step4: Handle choose theme click action
`ThemeChoosePageAActivity.java`

```
void doSetGreenThemeBtnClick() {
    boolean isRedTheme = SFMock.getInstance().isRedTheme();
    if (!isRedTheme) {
        return;
    }
    SFMock.getInstance().useRedTheme(false);

    applyTheme();
}

private void applyTheme() {
    finish();
    TaskStackBuilder.create(this).addNextIntent(getIntent()).startActivities();
}
```