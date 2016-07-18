package com.xin.bladedb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.xin.bladedb.model.Blade;

import javax.inject.Inject;

public class BladeListFragment extends Fragment {

    @Inject
    FirebaseDatabase database;

    public BladeListFragment() {
        // Required empty public constructor
    }

    public static BladeListFragment newInstance() {
        BladeListFragment fragment = new BladeListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplication()).getMainComponent().inject(this);
        Blade blade = new Blade();
        blade.name = "Clipper Rosewood";
        blade.plyCount = 7;
        blade.thickness = 6.9;
        blade.weight = 93;
        blade.ply1="Rosewood";
        blade.ply2="Ayous";
        blade.ply3="Ayous";
        blade.ply4="Ayous";
        blade.ply5="Ayous";
        blade.ply6="Ayous";
        blade.ply7="Rosewood";
        DatabaseReference reference = database.getReference().child("blades");
        reference.child(blade.name).setValue(blade);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blade_list, container, false);
    }
}
