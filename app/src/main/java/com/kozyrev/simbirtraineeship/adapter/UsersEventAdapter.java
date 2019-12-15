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

public class UsersEventAdapter extends RecyclerView.Adapter<UsersEventAdapter.ViewHolder> {

    private List<User> users;

    public UsersEventAdapter(List<User> users){
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_user_event, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        User user = users.get(position);
        holder.ivUserPhotoEvent.setImageResource(user.getPhotoId());
    }

    @Override
    public int getItemCount(){
        return users.size();
    }

    public void dataSetChanged(List<User> users){
        this.users = users;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivUserPhotoEvent;

        ViewHolder(@NonNull View itemView){
            super(itemView);

            ivUserPhotoEvent = itemView.findViewById(R.id.iv_user_photo_event);
        }
    }
}
