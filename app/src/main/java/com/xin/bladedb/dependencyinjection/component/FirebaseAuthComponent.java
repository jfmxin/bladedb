package com.xin.bladedb.dependencyinjection.component;

import com.xin.bladedb.dependencyinjection.module.AppModule;
import com.xin.bladedb.dependencyinjection.module.FireBaseAuthModule;
import com.xin.bladedb.login.LoginActivity;
import com.xin.bladedb.signup.SignUpActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Xin on 7/4/2016.
 */

@Singleton
@Component(modules = {
        FireBaseAuthModule.class,
        AppModule.class
})
public interface FirebaseAuthComponent {
    void inject(LoginActivity loginActivity);
    void inject(SignUpActivity signupactivity);
}
