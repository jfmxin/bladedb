package com.xin.bladedb.login;

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

    @BindView(R.id.sign_in)
    Button signInButton;

    @BindView(R.id.sign_up)
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
        setContentView(R.layout.activity_login);
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

    @OnClick(R.id.sign_in)
    public void signIn() {
        final String email = emailField.getText().toString();
        final String password = passwordField.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Timber.d("Signed in with Email: %s", task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        } else {
                            continueToMainActivity();
                        }
                    }
                });
    }

    @OnClick(R.id.sign_up)
    public void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent, SignUpActivity.SIGN_UP);
    }

    public void continueToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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
}