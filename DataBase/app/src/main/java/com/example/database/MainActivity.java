package com.example.database;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ListView a;
    Button ad;
    ArrayList<Contact> tri;
    Adapter adapter;
    SQLDataHelper sqlDataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ad = findViewById(R.id.them);
        a= findViewById(R.id.list);
        tri = new ArrayList<>();
        sqlDataHelper = new SQLDataHelper(this);
        tri = (ArrayList<Contact>) sqlDataHelper.getAllContact();
        tri.add(new Contact("trí","123"));
        adapter = new Adapter(tri, this, new MyLongClick() {
            @Override
            public void onLongClick(int position) {
                sqlDataHelper.deleteContactBy(tri.get(position).getId());
                getAllData();
            }

            @Override
            public void onClick(int position) {
                showDialogContact(tri.get(position));
            }
        });
        a.setAdapter(adapter);
        getAllData();
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogContact(null);
            }
        });
//        a.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                sqlDataHelper.deleteContactBy(tri.get(position).getId());
//                getAllData();
//            }
//        });

    }
    public void showDialogContact(final Contact myContactEdit){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_content,null,false);
        final EditText trau,tre;
        trau = view.findViewById(R.id.check);
        tre = view.findViewById(R.id.call);
        Button btnCancel = view.findViewById(R.id.thoat);
        Button btnSave = view.findViewById(R.id.chuan);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        if(myContactEdit != null){
            trau.setText(myContactEdit.getName());
            tre.setText(myContactEdit.getPhone());
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myContactEdit == null){
                    Contact contact = new Contact(trau.getText().toString(),tre.getText().toString());
                    sqlDataHelper.addContact(contact);
                }else {
                    Contact contact = new Contact(myContactEdit.getId(),trau.getText().toString(),tre.getText().toString());
                    sqlDataHelper.updateContactByID(contact);
                }
                getAllData();
                alertDialog.cancel();
            }
        });
    }

    private void getAllData() {
        tri= (ArrayList<Contact>) sqlDataHelper.getAllContact();
        adapter.notifyDataSetChanged(tri);
    }

}
