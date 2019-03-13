package com.hades.example.android.a.app_component.Intent_and_intent_filter;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.hades.example.android.a.R;

/*
    Mock OpenApp: Open link url from Chrome App to open Other App Activity.
    If not Good Access
       String url ="intent://jump?anything#Intent;scheme=android_about_demos_b;package=com.hades.example.android.b;end"
    Then
      String url =android_about_demos_b://jump?anything#Intent;scheme=android_about_demos_b;package=com.hades.example.android.b;end

     window.location=url

   */
public class TestAccessRemoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intent_filter_4_access_remote_activity_with_implicit_intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }
        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/web/web_open_app.html");

        findViewById(R.id.test1).setOnClickListener(v -> test1());
        findViewById(R.id.test2).setOnClickListener(v -> test2());
        findViewById(R.id.test3).setOnClickListener(v -> test3());
        findViewById(R.id.test4).setOnClickListener(v -> test4());
        findViewById(R.id.test5).setOnClickListener(v -> test5());
        findViewById(R.id.test6).setOnClickListener(v -> test6());
        findViewById(R.id.test7).setOnClickListener(v -> test7());
        findViewById(R.id.test8).setOnClickListener(v -> test8());
    }

    /*
     Not match: Pop up choose dialog
     */
    private void test1() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }

    /*
    Match Case 1:
    Intent {
    act=android.intent.action.VIEW
    cat=[android.intent.category.DEFAULT,android.intent.category.BROWSABLE]
    dat=android_about_demos.b://jump
    flg=0x18080000
    cmp=com.hades.example.android.b/.app_component.Intent_and_intent_filter.TestReceiveImplicitIntentActivity }

    mData= android_about_demos_b://jump
        scheme= android_about_demos_b
        uriString=android_about_demos_b://jump
        host=NOT CACHED
     */
    private void test2() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("android_about_demos_b://jump"));
        startActivity(intent);
    }

    /*
    Match Case 1:
      Intent {
          act=android.intent.action.VIEW
          cat=[android.intent.category.DEFAULT,android.intent.category.BROWSABLE]
          dat=android_about_demos_b://jump?anything
          flg=0x10400000
          cmp=com.hades.example.android.b/.app_component.Intent_and_intent_filter.TestReceiveImplicitIntentActivity
      }

      mData= android_about_demos_b://jump?anything
        scheme= android_about_demos_b
        uriString=android_about_demos_b://jump?anything
        host=NOT CACHED
     */
    private void test3() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("android_about_demos_b://jump?anything"));
        startActivity(intent);
    }

    /*
    Match case2
     Intent {
         act=android.intent.action.VIEW
         cat=[android.intent.category.DEFAULT,android.intent.category.BROWSABLE]
         dat=https://www.android_about_demos_b.com/folder/sub/etc
         flg=0x18080000
         cmp=com.hades.example.android.b/.app_component.Intent_and_intent_filter.TestReceiveImplicitIntentActivity
     }

     mData= https://www.android_about_demos_b.com/folder/sub/etc
        scheme= https
        uriString=https://www.android_about_demos_b.com/folder/sub/etc
        host=www.android_about_demos_b.com
     */
    private void test4() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("https://www.android_about_demos_b.com/folder/sub/etc"));
        startActivity(intent);
    }

    /*
     Intent {
     act=android.intent.action.VIEW
     cat=[android.intent.category.DEFAULT,android.intent.category.BROWSABLE]
     dat=https://www.android_about_demos_b.com/folder/sub/etc/abc
     flg=0x18080000 cmp=com.hades.example.android.b/.app_component.Intent_and_intent_filter.TestReceiveImplicitIntentActivity }


     mData= https://www.android_about_demos_b.com/folder/sub/etc/abc
        scheme= https
        uriString=https://www.android_about_demos_b.com/folder/sub/etc/abc
        host=www.android_about_demos_b.com
     */
    private void test5() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("https://www.android_about_demos_b.com/folder/sub/etc/abc"));
        startActivity(intent);
    }

    /*
        Not match: Pop up choose dialog
    */
    private void test6() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("https://www.android_about_demos_b.com/folder/subFolder/etc"));
        startActivity(intent);
    }

    /*
    ERROR:android.content.ActivityNotFoundException: No Activity found to handle Intent { dat=intent://jump?anything }
     */
    private void test7() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("intent://jump?anything#Intent;scheme=android_about_demos_b;package=com.hades.example.android.b;end"));
        startActivity(intent);
    }

    /*
    Match case1
    Intent {
        dat=android_about_demos_b://jump?anything
        flg=0x10000000
        cmp=com.hades.example.android.b/.app_component.Intent_and_intent_filter.TestReceiveImplicitIntentActivity }

     mData= android_about_demos_b://jump?anything#Intent;scheme=android_about_demos_b;package=com.hades.example.android.b;end
            scheme= android_about_demos_b
            uriString=android_about_demos_b://jump?anything#Intent;scheme=android_about_demos_b;package=com.hades.example.android.b;end
            host=NOT CACHED
     */
    private void test8() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("android_about_demos_b://jump?anything#Intent;scheme=android_about_demos_b;package=com.hades.example.android.b;end"));
        startActivity(intent);
    }
}