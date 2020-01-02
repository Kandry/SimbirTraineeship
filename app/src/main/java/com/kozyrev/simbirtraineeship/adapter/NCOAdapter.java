package com.kozyrev.simbirtraineeship.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.NCO;

import java.util.List;

public class NCOAdapter extends RecyclerView.Adapter<NCOAdapter.ViewHolder> {
    private List<NCO> ncos;

    public NCOAdapter(List<NCO> ncos){
        this.ncos = ncos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_nco, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        NCO nco = ncos.get(position);
        holder.tvCardNCO.setText(nco.getName());
    }

    @Override
    public int getItemCount(){
        return ncos.size();
    }

    public void dataSetChanged(List<NCO> ncos){
        this.ncos = ncos;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCardNCO;
        ImageButton ivCardNCO;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCardNCO = itemView.findViewById(R.id.tv_card_nco);
            ivCardNCO = itemView.findViewById(R.id.iv_card_nco);
        }
    }
}
