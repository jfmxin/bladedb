package com.xin.bladedb.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xin.bladedb.MainApplication;
import com.xin.bladedb.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    public static final int SIGN_UP = 10;

    @BindView(R.id.signup_email)
    EditText emailField;

    @BindView(R.id.signup_password)
    EditText passwordField;

    @BindView(R.id.submit_signup)
    Button signUpButton;

    @BindView(R.id.signup_progress)
    ProgressBar progressBar;

    @BindView(R.id.signup_container)
    LinearLayout signUpContainer;

    @Inject
    FirebaseAuth firebaseAuth;

    FirebaseAuth.AuthStateListener firebaseListener;

    @Inject
    SignUpPresenter signUpPresenter;

    //Lifecycle calls
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        passwordField.setTransformationMethod(new PasswordTransformationMethod());
        ((MainApplication) getApplication()).getFireBaseComponent().inject(this);
        signUpPresenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseListener = new FirebaseAuth.AuthStateListener() {
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
        firebaseAuth.addAuthStateListener(firebaseListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseListener);
        }
    }

    //Signup View Calls
    @Override
    public void hideProgress() {
        signUpContainer.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        signUpContainer.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmailWarning(String message) {
        emailField.setError(message);
    }

    @Override
    public void showPasswordWarning(String message) {
        passwordField.setError(message);
    }

    @Override
    public void returnToLogin(String email, String password) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("email", email);
        resultIntent.putExtra("password", password);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    //OnClickListeners
    @OnClick(R.id.submit_signup)
    public void signUp() {
        signUpPresenter.signUp(emailField.getText().toString(), passwordField.getText().toString());
    }
}