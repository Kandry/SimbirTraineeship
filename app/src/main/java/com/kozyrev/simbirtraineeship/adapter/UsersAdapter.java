package com.kozyrev.simbirtraineeship.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> friends;

    public UsersAdapter(List<User> friends){
        this.friends = friends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_friend, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        User friend = friends.get(position);
        holder.ivFriendPhoto.setImageResource(friend.getPhotoId());
        holder.tvFriendName.setText(friend.getName());
    }

    @Override
    public int getItemCount(){
        return friends.size();
    }

    public void dataSetChanged(List<User> friends){
        this.friends = friends;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivFriendPhoto;
        TextView tvFriendName;

        ViewHolder(@NonNull View itemView){
            super(itemView);

            ivFriendPhoto = itemView.findViewById(R.id.iv_friend_photo);
            tvFriendName = itemView.findViewById(R.id.tv_friend_name);
        }
    }
}
