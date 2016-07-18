package com.xin.bladedb.dependencyinjection.module;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Created by Xin on 7/4/2016.
 */
@Module
public class FireBaseAuthModule {

    @Provides
    @Singleton
    FirebaseAuth providesFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }
}
