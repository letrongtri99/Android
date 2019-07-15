package com.example.mixonfragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<User> userArrayList = new ArrayList<>();
    UserAdapter userAdapter;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference me = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        User x = new User("Clash of Clan đang phát trực tiếp", "34 phút trước", R.drawable.clan);
        me.child(x.getTenNguoi()).setValue(x, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError == null){
                    Toast.makeText(MainActivity.this,"Lưu thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Fail rồi",Toast.LENGTH_LONG).show();
                }
            }
        });
        userArrayList.add(new User("Clash of Clan đang phát trực tiếp", "34 phút trước", R.drawable.clan));
        userArrayList.add(new User("Đỗ Hùng Dũng vừa ghi bàn", "2 phút trước", R.drawable.dung));
        userArrayList.add(new User("Phú vừa phẫn nỗ về bình luận của bạn", "25 phút trước", R.drawable.phu));
        userAdapter = new UserAdapter(userArrayList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);
        toolbar = findViewById(R.id.tool);
        if (toolbar != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        getMenuInflater().inflate(R.menu.search_view,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.dialog_content,null,false);
                final EditText info, time;
                info = view.findViewById(R.id.thongbao);
                time = view.findViewById(R.id.call);
                Button luu,thoat;
                thoat = view.findViewById(R.id.thoat);
                luu = view.findViewById(R.id.chuan);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
                thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
                luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = info.getText().toString();
                        String b = time.getText().toString();
                        userArrayList.add(new User(a,b,R.drawable.minh));
                        userAdapter.notifyDataSetChanged();
                    }
                });
                return true;
             default:
                 return super.onOptionsItemSelected(item);
        }
    }
}
