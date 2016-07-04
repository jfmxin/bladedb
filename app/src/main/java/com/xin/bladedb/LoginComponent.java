package com.xin.bladedb;

import dagger.Component;

/**
 * Created by Xin on 7/4/2016.
 */
@Component(modules = {
        LoginModule.class,
        AppModule.class
})
public interface LoginComponent {
    void inject(MainActivity mainActivity);
}
