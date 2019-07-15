package com.example.bookfood;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListFoodActitvity extends AppCompatActivity {
    Product product = new Product();
    DatabaseReference myData = FirebaseDatabase.getInstance().getReference();
    Uri uri;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
    StorageTask storageTask;
    String b,mUri;
    CircleImageView circleImageView;
    RecyclerView recyclerView;
    ArrayList<Product> arrayList = new ArrayList<>();
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food_actitvity);
        recyclerView = findViewById(R.id.recycler);
        productAdapter = new ProductAdapter(ListFoodActitvity.this,arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productAdapter);
        myData.child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Product product1 = snapshot.getValue(Product.class);
                    assert product1!= null;
                    arrayList.add(product1);
                }
                productAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.choose_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.product:
                AlertDialog.Builder builder = new AlertDialog.Builder(ListFoodActitvity.this);
                final View view = LayoutInflater.from(ListFoodActitvity.this).inflate(R.layout.dialog_content,null);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
                final EditText mieuta, iD, donvi,tienTe,name;
                mieuta = view.findViewById(R.id.describe);
                iD = view.findViewById(R.id.id1);
                donvi = view.findViewById(R.id.unit);
                tienTe = view.findViewById(R.id.money);
                name = view.findViewById(R.id.tensanpham);
                Button thoat,sua;
                thoat = view.findViewById(R.id.escape);
                sua = view.findViewById(R.id.add);
                thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });

                sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = mieuta.getText().toString();
                        b = iD.getText().toString();
                        String c = donvi.getText().toString();
                        String d = tienTe.getText().toString();
                        String e = name.getText().toString();
                        Product product = new Product(b,d,a,mUri,e,c);
                        myData.child("Product").child(product.getpId()).setValue(product);
                        alertDialog.cancel();
                    }
                });
                circleImageView = view.findViewById(R.id.picture);
                circleImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OpenImage();
                    }
                });
                break;
            case R.id.history:
                Intent u = new Intent(ListFoodActitvity.this,OrderActivity.class);
                startActivity(u);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void OpenImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 11);
    }
    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void upLoadImage() {
        final ProgressDialog pd = new ProgressDialog(ListFoodActitvity.this);
        pd.setMessage("Uploading...");
        pd.show();
        if (uri != null) {
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
            storageTask = fileReference.putFile(uri);
            storageTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot , Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot>task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        mUri = downloadUri.toString();
                        Glide.with(getApplicationContext()).load(mUri).into(circleImageView);
                        pd.dismiss();
                    }
                    else{
                        Toast.makeText(ListFoodActitvity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ListFoodActitvity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        }else {
            Toast.makeText(ListFoodActitvity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null && data.getData()!=null){
            uri = data.getData();
            if (storageTask!=null && storageTask.isInProgress()){
                Toast.makeText(ListFoodActitvity.this, "In progress...", Toast.LENGTH_SHORT).show();
            }
            else {
                upLoadImage();
            }

        }
    }
}
