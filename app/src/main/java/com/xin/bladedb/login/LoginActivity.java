package com.xin.bladedb.login;

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
import com.xin.bladedb.MainActivity;
import com.xin.bladedb.MainApplication;
import com.xin.bladedb.R;
import com.xin.bladedb.signup.SignUpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.input_email)
    EditText emailField;

    @BindView(R.id.input_password)
    EditText passwordField;

    @BindView(R.id.login)
    Button loginButton;

    @BindView(R.id.sign_up)
    Button signUpButton;

    @BindView(R.id.login_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.login_container)
    LinearLayout loginContainer;

    @Inject
    LoginPresenter loginPresenter;

    @Inject
    FirebaseAuth firebaseAuth;

    FirebaseAuth.AuthStateListener firebaseListener;

    //Android lifecycle calls
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        passwordField.setTransformationMethod(new PasswordTransformationMethod());
        ((MainApplication) getApplication()).getFireBaseComponent().inject(this);
        loginPresenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseListener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Timber.d("User is signed in.");
                    continueToMainActivity();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Timber.d("Activity Result.");
        switch (requestCode) {
            case SignUpActivity.SIGN_UP:
                Timber.d("Passing data through");
                if (resultCode == RESULT_OK) {
                    emailField.setText(data.getStringExtra("email"));
                    passwordField.setText(data.getStringExtra("password"));
                }
                break;
            default:
                break;
        }
    }

    //Login View
    @Override
    public void hideProgress() {
        loginContainer.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        loginContainer.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void continueToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showEmailWarning(String message) {
        emailField.setError(message);
    }

    @Override
    public void showPasswordWarning(String message) {
        passwordField.setError(message);
    }

    // OnClickListeners
    @OnClick(R.id.login)
    public void login() {
        loginPresenter.login(emailField.getText().toString(), passwordField.getText().toString());
    }

    @OnClick(R.id.sign_up)
    public void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent, SignUpActivity.SIGN_UP);
    }

}