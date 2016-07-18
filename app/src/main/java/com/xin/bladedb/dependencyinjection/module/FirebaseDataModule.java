package com.xin.bladedb.dependencyinjection.module;

import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xin on 7/17/2016.
 */
@Module
public class FirebaseDataModule {
    @Provides
    @Singleton
    FirebaseDatabase providesDatabase(){
        return FirebaseDatabase.getInstance();
    }
}