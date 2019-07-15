package com.buiquangkhai.firebaselist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    UserAdapter userAdapter;
    RecyclerView recyclerViewUser;
    ArrayList<User> users = new ArrayList<>();
    Button btnUpdateUser;
    Button btnAddUser;
    EditText edtNameUser, edtPhoneNumber;
    int pos;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewUser = (RecyclerView) findViewById(R.id.recyclerViewUser);
        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnUpdateUser = (Button) findViewById(R.id.btnUpdateUser);
        edtNameUser = (EditText) findViewById(R.id.edtNameUser);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        getData();

        userAdapter = new UserAdapter(users,MainActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewUser.setLayoutManager(layoutManager);
        recyclerViewUser.setAdapter(userAdapter);
// lấy các thuộc tính của User cần thêm => tạo 1 user => add user vào danh sách => cập nhật Adapter

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    User user=postSnapshot.getValue(User.class);
                    users.add(user);
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toast.makeText(MainActivity.this,"app",Toast.LENGTH_SHORT).show();

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameUser = edtNameUser.getText().toString();
                String phoneNumber = edtPhoneNumber.getText().toString();
                User user = new User(nameUser,phoneNumber);
                myRef.child(phoneNumber).setValue(user);
           //     users.add(user);
              //  userAdapter.notifyDataSetChanged();
                edtNameUser.setText("");
                edtPhoneNumber.setText("");
            }
        });

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameUser = edtNameUser.getText().toString();
                String phoneNumber = edtPhoneNumber.getText().toString();
                User user = new User(nameUser,phoneNumber);
                myRef.child(phoneNumber).setValue(user);
                edtNameUser.setText("");
                edtPhoneNumber.setText("");
            }
        });



    }

    public void setPosition(int position){
        pos = position;
        User user = users.get(position);
        edtNameUser.setText(user.getName());
        edtPhoneNumber.setText(user.getPhoneNumber());
// Toast.makeText(MainActivity.this,pos+"",Toast.LENGTH_LONG).show();
    }

    private void getData(){
        User u1 = new User("Quan","1234");
        User u2 = new User("Van", "2345");
        User u3 = new User("Thao", "3456");
        User u4 = new User("Hoai", "4567");
//        users.add(u1);
//        users.add(u2);
//        users.add(u3);
//        users.add(u4);
      //  myRef.child(u1.getPhoneNumber()).setValue(u1);
//        myRef.child(u2.getPhoneNumber()).setValue(u2);
//        myRef.child(u3.getPhoneNumber()).setValue(u3);
//        myRef.child(u4.getPhoneNumber()).setValue(u4);
    }

}
