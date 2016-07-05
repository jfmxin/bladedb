package com.xin.bladedb.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Xin on 7/4/2016.
 */
public class LoginPresenter {

    @Inject
    FirebaseAuth firebaseAuth;

    LoginView loginView;

    @Inject
    public LoginPresenter() {

    }

    public void setView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String email, String password) {
        loginView.showProgress();
        boolean validInput = true;
        if (email.isEmpty()) {
            loginView.showEmailWarning("Email cannot be empty!");
        }
        if (password.isEmpty()) {
            loginView.showPasswordWarning("Password cannot be empty!");
        }
        if (validInput) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            loginView.hideProgress();
                            Timber.d("Signed in with Email: %s", task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                loginView.showErrorMessage("Authentication failed.");
                            } else {
                                loginView.continueToMainActivity();
                            }
                        }
                    });
        }
    }
}
