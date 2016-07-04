package com.xin.bladedb;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xin on 7/4/2016.
 */
@Module
public class AppModule {

    Application application;
    @Singleton
    @Provides
    Application providesMainApplication( Application application ){
        return application;
    }
}
