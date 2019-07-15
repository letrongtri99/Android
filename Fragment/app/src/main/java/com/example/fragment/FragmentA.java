package com.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

public class FragmentA extends Fragment {
    TextView hien;
    EditText dau;
    Button cuoi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        hien = view.findViewById(R.id.fragmenta);
        dau = view.findViewById(R.id.sua1);
        cuoi = view.findViewById(R.id.clicka);
        cuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button a = (Button) getActivity().findViewById(R.id.chuyen);
                a.setText(dau.getText().toString());
            }
        });
        return view;
    }
}
