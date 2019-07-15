package com.example.trichatapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText ten,matkhau;
    Button dangnhap,dangki;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ten = findViewById(R.id.username);
        matkhau = findViewById(R.id.pass);
        dangnhap = findViewById(R.id.login);
        dangki = findViewById(R.id.dki);
        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,15);
            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = ten.getText().toString();
                String pass = matkhau.getText().toString();
                Login(user,pass);
            }
        });
    }
    private void Login(final String username, final String password){
        DatabaseReference myData = FirebaseDatabase.getInstance().getReference();
        myData.child("ListUsers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    assert user!=null;
                    if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                        Toast.makeText(LoginActivity.this,"Thành công,",Toast.LENGTH_LONG).show();
                        //dung trong chat adapter
                        SharedPreferences pre = getSharedPreferences("mydata",MODE_PRIVATE);
                        SharedPreferences.Editor editor = pre.edit();
                        editor.putString("nguoidung",username);
                        editor.putString("status",user.getStatus());
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this,Main2Activity.class);
                        intent.putExtra("user",username);
                        startActivityForResult(intent, 15);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
