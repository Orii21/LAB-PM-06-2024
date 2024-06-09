package com.example.tugas4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InstagramAdapter extends RecyclerView.Adapter<InstagramAdapter.InstagramViewHolder> {

    private List<Instagram> instagramList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Instagram instagram);
    }

    public InstagramAdapter(List<Instagram> instagramList, OnItemClickListener listener) {
        this.instagramList = instagramList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InstagramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_instagram, parent, false);
        return new InstagramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstagramViewHolder holder, int position) {
        holder.bind(instagramList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return instagramList.size();
    }

    public void updateList(List<Instagram> newList) {
        instagramList = newList;
        notifyDataSetChanged();
    }

    static class InstagramViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvUsername;
        private final ImageView ivProfile;

        public InstagramViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            ivProfile = itemView.findViewById(R.id.iv_profile);
        }

        public void bind(final Instagram instagram, final OnItemClickListener listener) {
            tvUsername.setText(instagram.getUsername());
            ivProfile.setImageResource(instagram.getFotoProfile());
            itemView.setOnClickListener(view -> listener.onItemClick(instagram));
        }
    }
}
