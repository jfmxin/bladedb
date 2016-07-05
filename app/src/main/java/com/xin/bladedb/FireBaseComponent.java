package com.xin.bladedb;

import com.xin.bladedb.login.LoginActivity;
import com.xin.bladedb.signup.SignUpActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Xin on 7/4/2016.
 */

@Singleton
@Component(modules = {
        FireBaseModule.class,
        AppModule.class
})
public interface FireBaseComponent {
    void inject(LoginActivity loginActivity);
    void inject(SignUpActivity signUpActivity);
}
