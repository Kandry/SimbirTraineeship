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
import com.kozyrev.simbirtraineeship.model.Filter;

import java.util.List;

public class FiltersAdapter extends RecyclerView.Adapter<FiltersAdapter.ViewHolder>  {

    private List<Filter> filters;

    public FiltersAdapter(List<Filter> filters){
        this.filters = filters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_filter, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Filter filter = filters.get(position);
        holder.tvCardFilter.setText(filter.getName());
        holder.switchCardFilter.setChecked(filter.isActive());
    }

    @Override
    public int getItemCount(){
        return filters.size();
    }

    public void dataSetChanged(List<Filter> filters){
        this.filters = filters;
        this.notifyDataSetChanged();
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
