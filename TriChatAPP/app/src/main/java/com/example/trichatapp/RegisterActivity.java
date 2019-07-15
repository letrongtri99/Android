package com.example.trichatapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    TextView text;
    EditText sua,call,danh;
    Button chinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.txt);
        sua = findViewById(R.id.ha);
        call= findViewById(R.id.sdt);
        danh=findViewById(R.id.name);
        chinh = findViewById(R.id.ho);
//        databaseReference.child("DoAn").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String tri = dataSnapshot.getKey();
//                String chuot = String.valueOf(dataSnapshot.getValue());
//                text.setText(tri+" " + chuot);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        chinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String s = sua.getText().toString();
                String ten = sua.getText().toString();
                String sdt = call.getText().toString();
                String user = danh.getText().toString();
                User user1 = new User(sdt,ten,user,"offline","null");
                databaseReference.child("ListUsers").child(user1.getUsername()).setValue(user1);

//                databaseReference.child("NhanVien").child(nv.getTen()).setValue(nv);
//                databaseReference.child("DoAn").setValue(s);
//                databaseReference.child("ListUser").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                            NhanVien vien = dataSnapshot.getValue(NhanVien.class);
//                            assert vien != null;
//                            if(vien.getTen().equals(ten)){
//                                databaseReference.child("NhanVien").child(ten).removeValue();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//                databaseReference.child("NhanVien").child("115").removeValue();
            }
        });
//        databaseReference.child("NhanVien").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    NhanVien nv1 = snapshot.getValue(NhanVien.class);
//                    text.append(nv1.getTen()+" "+nv1.getSDT()+" ");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
