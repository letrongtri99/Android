package com.example.trichatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView listView;
    ArrayList<User> data = new ArrayList<>();
    ChatStatusAdapter adapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String currentUser;
    Button chuyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.list);

        final Intent intent = getIntent();
        currentUser = intent.getStringExtra("user");
        chuyen = findViewById(R.id.proFile);
        chuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this,ProfileActivity.class);
                i.putExtra("current",currentUser);
                startActivityForResult(i,20);
            }
        });
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("ListUsers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    assert user!=null;
                    if(!user.getUsername().equals(currentUser)) {
                        data.add(user);
                    }
                }
                adapter = new ChatStatusAdapter(Main2Activity.this,data);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(Main2Activity.this,ChatActivity.class);
                intent1.putExtra("sender",currentUser);
                intent1.putExtra("receiver",data.get(position).getUsername());
                startActivityForResult(intent1,18);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseReference.child("ListUsers").child(currentUser).child("status").setValue("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        databaseReference.child("ListUsers").child(currentUser).child("status").setValue("offline");
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        databaseReference.child("ListUsers").child(currentUser).child("status").setValue("offline");
//    }
}
