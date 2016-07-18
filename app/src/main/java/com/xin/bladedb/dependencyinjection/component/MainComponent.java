package com.xin.bladedb.dependencyinjection.component;

import com.xin.bladedb.AddBladeFragment;
import com.xin.bladedb.BladeListFragment;
import com.xin.bladedb.MainActivity;
import com.xin.bladedb.dependencyinjection.module.AppModule;
import com.xin.bladedb.dependencyinjection.module.FireBaseAuthModule;
import com.xin.bladedb.dependencyinjection.module.FirebaseDataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Xin on 7/17/2016.
 */
@Singleton
@Component(modules = {
        FireBaseAuthModule.class,
        FirebaseDataModule.class,
        AppModule.class
})
public interface MainComponent {
    void inject(MainActivity mainActivity);
    void inject(BladeListFragment fragment);
    void inject(AddBladeFragment fragment);
}
