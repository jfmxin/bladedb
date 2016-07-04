package com.xin.bladedb;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Singleton;

import dagger.Provides;
import timber.log.Timber;

/**
 * Created by Xin on 7/4/2016.
 */
public class LoginModule {

    @Provides
    @Singleton
    FirebaseAuth providesFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    FirebaseAuth.AuthStateListener providesAuthStateListener(){
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Timber.d("User is signed in.");
                } else {
                    // User is signed out
                    Timber.d("User is signed out.");
                }
            }
        };
    }
}
