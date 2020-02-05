package com.kozyrev.simbirtraineeship.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.news_fragment.NewsFragmentView;
import com.kozyrev.simbirtraineeship.utils.DateFormating;
import com.squareup.picasso.Picasso;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Event> news;
    private NewsFragmentView newsFragmentView;

    public NewsAdapter(List<Event> news, NewsFragmentView newsFragmentView){
        this.news = news;
        this.newsFragmentView = newsFragmentView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(cardView);

        cardView.setOnClickListener((v) -> {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition != NO_POSITION){
                newsFragmentView.onNewsItemClick(adapterPosition);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Event event = news.get(position);

        Picasso.get()
                .load(Uri.parse(event.getPhotos().get(0)))
                .into(holder.ivNewsImage);

        holder.tvNewsName.setText(event.getName());
        holder.tvNewsDescription.setText(event.getDescription());

        String date = DateFormating.startEndDateFormat(event.getStartDate(), event.getEndDate(),"MMMM d, yyyy");
        holder.tvNewsCalendar.setText(date);
    }

    @Override
    public int getItemCount(){
        return news.size();
    }

    public void dataSetChanged(List<Event> events){
        this.news = events;
        this.notifyDataSetChanged();
    }

    public void setNews(List<Event> news) {
        this.news = news;
    }

    public List<Event> getNews() {
        return news;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivNewsImage;
        TextView tvNewsName;
        TextView tvNewsDescription;
        ConstraintLayout clNewsBottom;
        TextView tvNewsCalendar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivNewsImage = itemView.findViewById(R.id.iv_news_image);
            tvNewsName = itemView.findViewById(R.id.tv_news_name);
            tvNewsDescription = itemView.findViewById(R.id.tv_news_description);
            clNewsBottom = itemView.findViewById(R.id.cl_news_bottom);
            tvNewsCalendar = itemView.findViewById(R.id.tv_news_calendar);
        }
    }
}