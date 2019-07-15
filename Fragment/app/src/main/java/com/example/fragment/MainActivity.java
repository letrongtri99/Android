package com.example.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mot,hai,ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mot = findViewById(R.id.chuyen);
        hai = findViewById(R.id.nua);
        ba = findViewById(R.id.xoa);
        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA fragmentA = (FragmentA) getFragmentManager().findFragmentById(R.id.tri);
                fragmentA.hien.setText("Khôn lắm");
            }
        });
        hai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                FragmentB fragmentB = new FragmentB();
                Bundle bundle = new Bundle();
                bundle.putString("ten","Lê Trọng Trí");
                fragmentB.setArguments(bundle);
                fragmentTransaction1.add(R.id.me,fragmentB,"fragB");
                fragmentTransaction1.commit();
            }
        });
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB fragmentA = (FragmentB) getFragmentManager().findFragmentByTag("fragB");
                if(fragmentA != null) {
                    FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    fragmentTransaction2.remove(fragmentA);
                    fragmentTransaction2.commit();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Không tồn tại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
