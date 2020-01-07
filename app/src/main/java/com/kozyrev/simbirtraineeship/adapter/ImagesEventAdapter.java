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
    private List<String> imagesUri;

    /*public ImagesEventAdapter(List<Integer> resourcesId){
        this.resourcesId = resourcesId;
    }*/

    public ImagesEventAdapter(List<String> imagesUri){
        this.imagesUri = imagesUri;
    }


    @NonNull
    @Override
    public ImagesEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_image_event, parent, false);
        return new ImagesEventAdapter.ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesEventAdapter.ViewHolder holder, int position){
        String image = imagesUri.get(position);
        Picasso.get()
                .load(image)
                .into(holder.ivImageEvent);
    }

    @Override
    public int getItemCount(){
        return imagesUri.size();
    }

    public void dataSetChanged(List<String> imagesUri){
        this.imagesUri = imagesUri;
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
