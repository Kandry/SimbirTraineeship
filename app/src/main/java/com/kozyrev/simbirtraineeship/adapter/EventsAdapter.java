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
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<Event> events;

    public EventsAdapter(List<Event> events){
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Event event = events.get(position);
        holder.tvCardEvent.setText(event.getName());
    }

    @Override
    public int getItemCount(){
        return events.size();
    }

    public void dataSetChanged(List<Event> events){
        this.events = events;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCardEvent;
        ImageButton ivCardEvent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCardEvent = itemView.findViewById(R.id.tv_card_event);
            ivCardEvent = itemView.findViewById(R.id.iv_card_event);
        }
    }
}
