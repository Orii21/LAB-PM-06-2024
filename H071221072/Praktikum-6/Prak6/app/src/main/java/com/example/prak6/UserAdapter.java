package com.example.prak6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList = new ArrayList<>();
    private Context context;

    public UserAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("USER_ID", user.getId());
                intent.putExtra("USER_NAME", user.getFirst_name() + " " + user.getLast_name());
                intent.putExtra("USER_EMAIL", user.getEmail());
                intent.putExtra("USER_AVATAR", user.getAvatar());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void addUsers(List<User> users) {
        userList.addAll(users);
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_profile;
        private TextView tv_name;
        private TextView tv_email;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_email = itemView.findViewById(R.id.tv_email);
        }

        public void bind(User user) {
            Picasso.get().load(user.getAvatar()).into(iv_profile);
            tv_name.setText(user.getFirst_name() + " " + user.getLast_name());
            tv_email.setText(user.getEmail());
        }
    }
}
