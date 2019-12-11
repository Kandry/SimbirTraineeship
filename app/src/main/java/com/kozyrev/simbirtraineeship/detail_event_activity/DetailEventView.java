package com.kozyrev.simbirtraineeship.detail_event_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Event;

import static com.kozyrev.simbirtraineeship.utils.Constants.EVENT_ID;

public class DetailEventView extends AppCompatActivity implements DetailEventContract.View {

    private static final String TAG = "DetailEventView";

    private TextView toolbarDetailEventTitle,
            tvDetailEventTitle,
            tvDetailEventCalendar,
            tvDetailEventOrganizer,
            tvDetailEventAddresses,
            tvDetailEventPhone,
            tvDetailEventMail,
            tvDetailEventDescription;
    private RecyclerView rvDetailEventImages,
            rvDetailEventUsers;
    private BottomNavigationView bnvDetailEvent;

    private DetailEventPresenter detailEventPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        initToolbar();
        initViews();

        Intent intent = getIntent();
        int id = intent.getIntExtra(EVENT_ID, -1);

        detailEventPresenter = new DetailEventPresenter(this, this);
        detailEventPresenter.requestEventData(id);
    }

    @Override
    public void setDataToViews(Event event) {
        if (event != null){
            toolbarDetailEventTitle.setText(event.getName());
            tvDetailEventTitle.setText(event.getName());
            //tvDetailEventCalendar.setText(event.getName());
            tvDetailEventOrganizer.setText(event.getOrganizer());
            tvDetailEventAddresses.setText(event.getAddress());

            StringBuilder phoneNumbers = new StringBuilder();
            for (String phone: event.getPhoneNumbers()){
                phoneNumbers.append(phone + "\n");
            }
            tvDetailEventPhone.setText(phoneNumbers.toString());

            tvDetailEventMail.setText(event.getEmail());
            tvDetailEventDescription.setText(event.getDescription());
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, R.string.communication_error, Toast.LENGTH_LONG).show();
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar_detail_event);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initViews(){
        toolbarDetailEventTitle = findViewById(R.id.toolbar_detail_event_title);
        tvDetailEventTitle = findViewById(R.id.tv_detail_event_title);
        tvDetailEventCalendar = findViewById(R.id.tv_detail_event_calendar);
        tvDetailEventOrganizer = findViewById(R.id.tv_detail_event_organizer);
        tvDetailEventAddresses = findViewById(R.id.tv_detail_event_address);
        tvDetailEventPhone = findViewById(R.id.tv_detail_event_phone);
        tvDetailEventMail = findViewById(R.id.tv_detail_event_mail);
        tvDetailEventDescription = findViewById(R.id.tv_detail_event_description);

        rvDetailEventImages = findViewById(R.id.rv_detail_event_images);
        rvDetailEventUsers = findViewById(R.id.rv_detail_event_users);

        bnvDetailEvent = findViewById(R.id.bnv_detail_event);
    }
}
