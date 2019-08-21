package com.hades.android.example.rxjava2._subscribeOn_vs_observeOn;

import com.hades.android.example.rxjava2.LogHelper;
import io.reactivex.*;
import io.reactivex.functions.Function;
import org.reactivestreams.Subscriber;

import java.net.MalformedURLException;

public class Test {
    // https://www.cnblogs.com/okadanana/p/5975992.html


//    private void test(){
//        Observable.just(String.valueOf(30))
//                .map(new Function<String,Integer>() { // 操作1
//                    @Override
//                    public Integer apply(String num) throws Exception {
//                        return Integer.valueOf(num) > 10? Integer.valueOf(num): 15;
//                    }
//                })
//                .flatMap(new Function<Integer, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Integer s) throws Exception {
//                        return Observable.just(String.valueOf(s));
//                    }
//                })                // 操作2
//                .subscribeOn(Subscr)
//                .map                    //操作3
//                .flatMap                //操作4
//                .observeOn(main)
//                .map                    //操作5
//                .flatMap                //操作6
//                .subscribeOn(io)        //!!特别注意
//                .subscribe(handleData)
//    }
//
//    private void counter() {
//        for (int i = 0; i < 30; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            i++;
//            System.out.println(i + "," + LogHelper.getThreadInfo());
//        }
//    }
}
