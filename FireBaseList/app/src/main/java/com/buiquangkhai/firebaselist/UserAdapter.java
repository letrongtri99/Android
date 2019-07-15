package com.buiquangkhai.firebaselist;

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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ArrayList<User> users;
    Context context;
    public UserAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }
    // public UserAdapter(ArrayList<User> users) {
// this.users = users;
// }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_user, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.txtNameUser.setText(user.getName());
        holder.txtPhoneNumber.setText(user.getPhoneNumber());
    }
    public void removeUser(int pos){

        User user = users.get(pos);
        myRef.child(user.getPhoneNumber()).setValue(null);
//        users.remove(pos);
//        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return users.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameUser;
        TextView txtPhoneNumber;
        Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameUser = itemView.findViewById(R.id.txtnameUser);
            txtPhoneNumber = itemView.findViewById(R.id.txtPhoneNumber);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeUser(getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).setPosition(getAdapterPosition());
// if (context instanceof MainActivity){
// ((MainActivity) context).setPosition(getAdapterPosition());
// }
                }
            });
        }
    }
}