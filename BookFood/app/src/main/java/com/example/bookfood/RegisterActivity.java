package com.example.bookfood;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText name,pasS,id;
    TextView sign;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.ngdung);
        pasS = findViewById(R.id.mkhau);
        id = findViewById(R.id.userID);
        sign = findViewById(R.id.dangki);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = name.getText().toString();
                String b = pasS.getText().toString();
                String c = id.getText().toString();
                final User user = new User(b,c,a);
                databaseReference.child("User").child(user.getuId()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Thành công",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
