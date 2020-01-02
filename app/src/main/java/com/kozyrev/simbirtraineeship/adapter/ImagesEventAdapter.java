package com.kozyrev.simbirtraineeship.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagesEventAdapter extends RecyclerView.Adapter<ImagesEventAdapter.ViewHolder>  {

    private List<Integer> resourcesId;

    public ImagesEventAdapter(List<Integer> resourcesId){
        this.resourcesId = resourcesId;
    }

    @NonNull
    @Override
    public ImagesEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_image_event, parent, false);
        return new ImagesEventAdapter.ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesEventAdapter.ViewHolder holder, int position){
        int resId = resourcesId.get(position);
        Picasso.get()
                .load(resId)
                .into(holder.ivImageEvent);
    }

    @Override
    public int getItemCount(){
        return resourcesId.size();
    }

    public void dataSetChanged(List<Integer> resourcesId){
        this.resourcesId = resourcesId;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImageEvent;

        ViewHolder(@NonNull View itemView){
            super(itemView);

            ivImageEvent = itemView.findViewById(R.id.iv_image_event);
        }
    }
}
