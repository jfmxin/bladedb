package com.xin.bladedb;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.xin.bladedb.model.Blade;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBladeFragment extends Fragment {

    @BindView(R.id.blade_name_field)
    EditText bladeName;

    @BindView(R.id.blade_plyCount_field)
    EditText bladePlyCount;

    @BindView(R.id.blade_thickness_field)
    EditText bladeThickness;

    @BindView(R.id.blade_weight_field)
    EditText bladeWeight;

    @BindView(R.id.blade_ply1_field)
    EditText bladePly1;

    @BindView(R.id.blade_ply2_field)
    EditText bladePly2;

    @BindView(R.id.blade_ply3_field)
    EditText bladePly3;

    @BindView(R.id.blade_ply4_field)
    EditText bladePly4;

    @BindView(R.id.blade_ply5_field)
    EditText bladePly5;

    @BindView(R.id.blade_ply6_field)
    EditText bladePly6;

    @BindView(R.id.blade_ply7_field)
    EditText bladePly7;

    @Inject
    FirebaseDatabase database;
    public static AddBladeFragment newInstance(){
        return new AddBladeFragment();
    }

    public AddBladeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplication()).getMainComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_blade, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.submit_blade)
    public void submitBlade(){
        DatabaseReference reference = database.getReference().child("blades");
        Blade blade = new Blade();
        blade.name = bladeName.getText().toString();
        blade.weight = Integer.valueOf(bladeWeight.getText().toString());
        blade.thickness = Double.valueOf(bladeThickness.getText().toString());
        blade.plyCount = Integer.valueOf(bladePlyCount.getText().toString());
        blade.ply1 = bladePly1.getText().toString();
        blade.ply2 = bladePly2.getText().toString();
        blade.ply3 = bladePly3.getText().toString();
        blade.ply4 = bladePly4.getText().toString();
        blade.ply5 = bladePly5.getText().toString();
        blade.ply6 = bladePly6.getText().toString();
        blade.ply7 = bladePly7.getText().toString();
        reference.setValue(blade);
    }
}