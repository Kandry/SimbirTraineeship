package com.kozyrev.simbirtraineeship.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>  {

    private List<Category> categories;

    public CategoriesAdapter(List<Category> categories){
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_filter, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Category category = categories.get(position);
        holder.tvCardFilter.setText(category.getName());
        holder.switchCardFilter.setChecked(category.isActive());
        holder.switchCardFilter.setOnCheckedChangeListener((compoundButton, b) -> category.setActive(b));
    }

    @Override
    public int getItemCount(){
        return categories.size();
    }

    public void dataSetChanged(List<Category> categories){
        this.categories = categories;
        this.notifyDataSetChanged();
    }

    public List<Category> getCategories(){
        return categories;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCardFilter;
        SwitchCompat switchCardFilter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCardFilter = itemView.findViewById(R.id.tv_card_filter);
            switchCardFilter = itemView.findViewById(R.id.switch_card_filter);
        }
    }
}
