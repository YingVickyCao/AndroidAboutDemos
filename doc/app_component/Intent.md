## PendintIntent 
- PendintIntent是对Intent的包装。
- 一般`PendintIntent.getActivity()`/`PendintIntent.getService()`/`PendintIntent.getBroadcast()`等获取PendintIntent。
- 与Intent不同，PendintIntent通常会传给其他应用组件，由其他应用程序来执行PendintIntent包装的`Intent`。