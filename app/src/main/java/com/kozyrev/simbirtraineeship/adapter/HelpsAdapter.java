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
import com.kozyrev.simbirtraineeship.model.HelpItem;

import java.util.List;

public class HelpsAdapter extends RecyclerView.Adapter<HelpsAdapter.ViewHolder> {

    private List<HelpItem> helps;

    public HelpsAdapter(List<HelpItem> helps){
        this.helps = helps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_help_item, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        HelpItem help = helps.get(position);
        holder.ivHelpImage.setImageResource(help.getImage());
        holder.tvHelpName.setText(help.getName());
    }

    @Override
    public int getItemCount(){
        return helps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivHelpImage;
        TextView tvHelpName;

        ViewHolder(@NonNull View itemView){
            super(itemView);

            ivHelpImage = itemView.findViewById(R.id.iv_help_image);
            tvHelpName = itemView.findViewById(R.id.tv_help_name);
        }
    }
}
