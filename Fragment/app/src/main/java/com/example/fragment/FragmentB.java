package com.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

public class FragmentB extends Fragment {
    TextView hang;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        hang = view.findViewById(R.id.fragmentb);
        Bundle bundle = getArguments();
        if(bundle != null){
            hang.setText(bundle.getString("ten"));
        }
        return view;
    }
}
