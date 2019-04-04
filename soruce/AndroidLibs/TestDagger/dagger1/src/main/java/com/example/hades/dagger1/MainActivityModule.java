package com.example.hades.dagger1;

import com.example.hades.dagger1.Instance.MultiModule;
import com.example.hades.dagger1.Instance.Plus;
import com.example.hades.dagger1.Instance.Sum;
import com.example.hades.dagger1.singleton.CModule;

import dagger.Module;
import dagger.Provides;

/**
 * 找到了生成Plus实例的方法：injects = Plus.class -> Plus @Inject 构造函数
 * 找到了生成Multi实例的方法：includes = MultiModule.class. -> MultiModule. 至于MultiModule是通过什么方式提供Multi，不关心。
 */
@Module(injects = {MainActivity.class, Plus.class}, includes = {MultiModule.class, CModule.class})
public class MainActivityModule {
    /**
     * 找到了生成Sum实例的方法：new Sum
     */
    @Provides
    Sum provideSum() {
        return new Sum();
    }
}