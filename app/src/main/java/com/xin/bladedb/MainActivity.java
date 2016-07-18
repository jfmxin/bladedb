package com.xin.bladedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sign_out_button)
    Button signOutButton;

    @Inject
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MainApplication) getApplication()).getMainComponent().inject(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame_layout, BladeListFragment.newInstance()).commit();
    }

    @OnClick(R.id.sign_out_button)
    public void signOut() {
        Timber.d("Signing Out");
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}