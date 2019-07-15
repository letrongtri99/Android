package com.example.firebaselistdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ArrayList<User> users;
    Context context;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference d = firebaseDatabase.getReference();

    public UserAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_user,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        User user = users.get(i);
        viewHolder.nam.setText(user.getName());
        viewHolder.call.setText(user.getPhoneNumber());
    }


    @Override
    public int getItemCount() {
        return users.size();
    }
    public void removeUser(int vitri){
        User u = users.get(vitri);
        d.child(u.getPhoneNumber()).setValue(null);
        d.child(u.getName()).setValue(null);
        users.remove(vitri);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nam,call;
        Button del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nam = itemView.findViewById(R.id.name);
            call = itemView.findViewById(R.id.phone);
            del = itemView.findViewById(R.id.xoa);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeUser(getAdapterPosition());
                }
            });
        }
    }
}
