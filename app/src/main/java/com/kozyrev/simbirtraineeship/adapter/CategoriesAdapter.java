package com.kozyrev.simbirtraineeship.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>  {

    private HashMap<Category, Boolean> categories;
    private List<Category> categoryList = new ArrayList<>();

    public CategoriesAdapter(HashMap<Category, Boolean> categories){
        this.categories = categories;
        categoryList.addAll(categories.keySet());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_filter, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Category category = categoryList.get(position);
        holder.tvCardFilter.setText(category.getName());
        holder.switchCardFilter.setChecked(categories.get(category));
        holder.switchCardFilter.setOnCheckedChangeListener((compoundButton, bool) -> categories.put(category, bool));
    }

    @Override
    public int getItemCount(){
        return categories.size();
    }

    public void dataSetChanged(HashMap<Category, Boolean> categories){
        this.categories = categories;
        categoryList.clear();
        categoryList.addAll(categories.keySet());
        this.notifyDataSetChanged();
    }

    public HashMap<Category, Boolean> getCategories(){
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
