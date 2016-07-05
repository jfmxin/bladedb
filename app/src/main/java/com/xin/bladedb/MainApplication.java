package com.xin.bladedb;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Xin on 7/4/2016.
 */
public class MainApplication extends Application{
    FireBaseComponent fireBaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        fireBaseComponent = DaggerFireBaseComponent.builder()
                .appModule( new AppModule(this))
                .build();
    }

    public FireBaseComponent getFireBaseComponent() {
        return fireBaseComponent;
    }
}
