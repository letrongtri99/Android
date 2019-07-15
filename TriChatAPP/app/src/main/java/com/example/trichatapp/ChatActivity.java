package com.example.trichatapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Chat> arrayList = new ArrayList<>();
    ChatAdapter chatAdapter;
    ImageButton imageButton;
    EditText text;
    DatabaseReference myData = FirebaseDatabase.getInstance().getReference();
    String toi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listView = findViewById(R.id.danhsach);
        listView.setDivider(null);
        imageButton = findViewById(R.id.nhan);
        text = findViewById(R.id.gui);
        Intent intent = getIntent();
        toi = intent.getStringExtra("sender");
        final String ban = intent.getStringExtra("receiver");
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = text.getText().toString();
                Chat chat = new Chat(toi,ban,message);
                DatabaseReference myMes = FirebaseDatabase.getInstance().getReference();
                myMes.child("ListChats").push().setValue(chat);
                text.setText("");
            }
        });
        myData.child("ListChats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    assert chat!=null;
                    Log.d("key" , chat.getSender() + " " + chat.getReceiver());
                    if ((chat.getSender().equals(toi) && chat.getReceiver().equals(ban)) || (chat.getSender().equals(ban) && chat.getReceiver().equals(toi))) {
                        arrayList.add(chat);
                    }
                    chatAdapter = new ChatAdapter(ChatActivity.this,arrayList);
                    listView.setAdapter(chatAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        myData.child("ListUsers").child(toi).child("status").setValue("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        myData.child("ListUsers").child(toi).child("status").setValue("offline");
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        myData.child("ListUsers").child(toi).child("status").setValue("offline");
//    }
}
