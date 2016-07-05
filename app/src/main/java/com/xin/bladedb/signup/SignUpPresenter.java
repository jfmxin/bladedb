package com.xin.bladedb.signup;

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

public class SignUpPresenter {
    @Inject
    FirebaseAuth firebaseAuth;

    SignUpView signUpView;

    @Inject
    public SignUpPresenter() {
    }

    public void setView(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    public void signUp(final String email, final String password) {
        signUpView.showProgress();
        boolean validInput = true;
        if (email.isEmpty()) {
            signUpView.showEmailWarning("Email cannot be empty!");
        }
        if (password.isEmpty()) {
            signUpView.showPasswordWarning("Password cannot be empty!");
        }
        if (validInput) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            signUpView.hideProgress();
                            Timber.d("Signed up with Email: %s", task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                signUpView.showToast("Sign up failed.");
                            } else {
                                signUpView.showToast("Success!");
                                signUpView.returnToLogin(email, password);
                            }
                        }
                    });
        }
    }
}