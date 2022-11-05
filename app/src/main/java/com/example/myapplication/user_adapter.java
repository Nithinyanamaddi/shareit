package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class user_adapter extends RecyclerView.Adapter<user_adapter.user_holder> {
    private ArrayList<user> users;
    private Context context;
    private OnUserClickListener onUserClickListener;

    public user_adapter(ArrayList<user> users, Context context, OnUserClickListener onUserClickListener) {
        this.users = users;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    interface OnUserClickListener{
        void onUserClicked(int  position);
    }

    @NonNull
    @Override
    public user_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_holder,parent,false);

        return new user_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull user_holder holder, int position) {
        holder.txtUserName.setText(users.get(position).getUsername());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class user_holder extends RecyclerView.ViewHolder{
        TextView txtUserName;
        ImageView imageview;
        public user_holder(@NonNull View itemView) {
            super(itemView);
            txtUserName=itemView.findViewById(R.id.textUserName);
            imageview=itemView.findViewById(R.id.img_pro);
        }
    }
}
