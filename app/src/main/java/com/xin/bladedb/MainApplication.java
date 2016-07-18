package com.xin.bladedb;

import android.app.Application;

import com.xin.bladedb.dependencyinjection.component.DaggerFirebaseAuthComponent;
import com.xin.bladedb.dependencyinjection.component.DaggerMainComponent;
import com.xin.bladedb.dependencyinjection.component.FirebaseAuthComponent;
import com.xin.bladedb.dependencyinjection.component.MainComponent;
import com.xin.bladedb.dependencyinjection.module.AppModule;

import timber.log.Timber;

/**
 * Created by Xin on 7/4/2016.
 */
public class MainApplication extends Application{
    FirebaseAuthComponent firebaseAuthComponent;
    MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }

    public FirebaseAuthComponent getFireBaseComponent() {
        if( firebaseAuthComponent == null ){
            firebaseAuthComponent = DaggerFirebaseAuthComponent.builder()
                    .appModule( new AppModule(this))
                    .build();
        }
        return firebaseAuthComponent;
    }

    public MainComponent getMainComponent() {
        if( mainComponent == null ){
            mainComponent = DaggerMainComponent.builder()
                    .appModule( new AppModule(this))
                    .build();
        }
        return mainComponent;
    }
}