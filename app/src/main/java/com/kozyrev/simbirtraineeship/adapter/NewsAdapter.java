package com.kozyrev.simbirtraineeship.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.news_fragment.NewsFragment;
import com.squareup.picasso.Picasso;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.net.URI;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Event> news;
    private NewsFragment newsFragment;

    public NewsAdapter(List<Event> news, Context context, NewsFragment newsFragment){
        AndroidThreeTen.init(context);
        this.news = news;
        this.newsFragment = newsFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(cardView);

        cardView.setOnClickListener((v) -> {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition != NO_POSITION){
                newsFragment.onNewsItemClick(adapterPosition);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Event event = news.get(position);

        Picasso.get()
                .load(Uri.parse(event.getImagesUri().get(0)))
                .into(holder.ivNewsImage);
/*
        Picasso.get()
                .load(R.drawable.image_man)
                .into(holder.ivNewsImage);*/

        holder.tvNewsName.setText(event.getName());
        holder.tvNewsDescription.setText(event.getDescription());
        String date = "";

        if (event.getEndDate() > -1){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM");

            LocalDate startLocalDate = Instant.ofEpochMilli(event.getStartDate()).atZone(ZoneId.systemDefault()).toLocalDate();
            String startDate = formatter.format(startLocalDate);

            LocalDate endLocalDate = Instant.ofEpochMilli(event.getEndDate()).atZone(ZoneId.systemDefault()).toLocalDate();
            String endDate = formatter.format(endLocalDate);

            LocalDate localDate = LocalDate.now();
            long diffDays = ChronoUnit.DAYS.between(localDate, startLocalDate);
            if (diffDays > 0) date = "Осталось " + diffDays + " дней ";

            date += "(" + startDate + " - " + endDate + ")";
        } else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            LocalDate localDate = Instant.ofEpochMilli(event.getStartDate()).atZone(ZoneId.systemDefault()).toLocalDate();
            date = formatter.format(localDate);
        }

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
