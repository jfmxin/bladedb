package com.xin.bladedb.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.xin.bladedb.MainApplication;
import com.xin.bladedb.R;
import com.xin.bladedb.login.LoginPresenter;
import com.xin.bladedb.login.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class SignUpActivity extends AppCompatActivity implements LoginView {

    public static final int SIGN_UP = 10;

    @BindView(R.id.signup_email)
    EditText emailField;

    @BindView(R.id.signup_password)
    EditText passwordField;

    @BindView(R.id.submit_signup)
    Button signInButton;

    @BindView(R.id.google_signup)
    Button googleSignInButton;

    @Inject
    FirebaseAuth firebaseAuth;

    @Inject
    FirebaseAuth.AuthStateListener firebaseListener;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        passwordField.setTransformationMethod(new PasswordTransformationMethod());
        ((MainApplication) getApplication()).getFireBaseComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
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
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @OnClick(R.id.submit_signup)
    public void signUp() {
        final String email = emailField.getText().toString();
        final String password = passwordField.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Timber.d("Signed up with Email: %s", task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Sign up complete!", Toast.LENGTH_SHORT).show();
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("email", email);
                            resultIntent.putExtra("password", password);
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        }
                    }
                });
    }
}