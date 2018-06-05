# runtime permission - RxPermissions
`RxPermissionsTestActivity.java`  

## Add dependency
`implementation 'com.tbruyelle.rxpermissions2:rxpermissions:0.9.4@aar'`  

## HUsage

```
rxPermissions = new RxPermissions(this); // where this is an Activity instance
rxPermissions.setLogging(true);
```

### Check if grant ome permission
```
rxPermissions.isGranted(Manifest.permission.SEND_SMS);
```


### Check if grant some permissions  

```
rxPermissions.isGranted(Manifest.permission.SEND_SMS) && rxPermissions.isGranted(Manifest.permission.RECEIVE_SMS)
```

### Check if grant one group permissions  
```
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
       return !rxPermissions.isGranted(Manifest.permission_group.SMS);
 } 
```

### Request one permission
- one permission / some permissions / one group permission :都支持。 需要替换掉就可以。

```
 rxPermissions.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.SEND_SMS).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean shouldShowRequestPermissionRationale) throws Exception {
                if (shouldShowRequestPermissionRationale) {
                    showRequestPermissionRationale4SendMessage();
                } else {
                    requestPermission4SendMessage();
                }
            }
        });

 private void showRequestPermissionRationale4SendMessage() {
      Snackbar.make(mLayout, R.string.permission_rationale_4_send_message,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestPermission4SendMessage();
                    }
                })
                .show();
 }

 private void requestPermission4SendMessage() {
        rxPermissions.request(Manifest.permission.SEND_SMS).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                if (granted) {
                    doSendMessage();
                    showPermissionAvailable4SendMessage();
                } else {
                    showPermissionsNotGranted();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

```
