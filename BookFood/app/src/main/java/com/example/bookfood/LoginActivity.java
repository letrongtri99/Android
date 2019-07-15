package com.example.bookfood;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    TextView dangKi,dangNhap;
    EditText ten,ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ten = findViewById(R.id.user);
        ma = findViewById(R.id.pass);
        dangKi = findViewById(R.id.register);
        dangNhap = findViewById(R.id.login);
        dangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,20);
            }
        });
        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String a = ten.getText().toString();
                final String b = ma.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            User user = snapshot.getValue(User.class);
                            assert user!=null;
                            if(a.equals(user.getuName()) && b.equals(user.getPassword())){
                                Intent intent = new Intent(LoginActivity.this,ListFoodActitvity.class);
                                startActivityForResult(intent,8);
                                break;
                            }
                            else{
                                Toast.makeText(LoginActivity.this,"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                String[] mangtenFile = filepath.split("\\.");

            }
        });
    }
}
