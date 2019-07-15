package com.example.firebasedemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView hien,ra;
    EditText chinh;
    Button them;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hien = findViewById(R.id.id1);
        ra=findViewById(R.id.id2);
        chinh=findViewById(R.id.id3);
        them = findViewById(R.id.id4);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference m = database.getReference();
        //đẩy lên
        m.child("message").setValue("Hello, World");
        //đẩy dùng map
        Map<String,Integer> me = new HashMap<String, Integer>();
        me.put("XeTho",2);
        m.child("Xe").setValue(me);

        SinhVien sinhVien = new SinhVien("Trí",1999,"TXuan");
        m.child("sv").setValue(sinhVien);
        m.child("sinhVien").push().setValue(sinhVien);
        //trả về
        m.child("user1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user t = dataSnapshot.getValue(user.class);
                hien.setText(t.getName());
                ra.setText(t.getId()+"");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = chinh.getText().toString();
                m.child("user1").child("name").setValue(n);
            }
        });
    }
}
