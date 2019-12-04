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
import com.squareup.picasso.Picasso;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.net.URI;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Event> news;
    public NewsAdapter(List<Event> news, Context context){
        AndroidThreeTen.init(context);
        this.news = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Event event = news.get(position);
        /*
        Picasso.get()
                .load(Uri.parse(event.getImageUri()))
                .into(holder.ivNewsImage);*/

        Picasso.get()
                .load(R.drawable.image_man)
                .into(holder.ivNewsImage);

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
