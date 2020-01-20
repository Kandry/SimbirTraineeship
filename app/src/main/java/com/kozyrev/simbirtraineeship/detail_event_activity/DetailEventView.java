package com.kozyrev.simbirtraineeship.detail_event_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.ImagesEventAdapter;
import com.kozyrev.simbirtraineeship.adapter.UsersEventAdapter;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.model.User;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.kozyrev.simbirtraineeship.utils.Constants.EVENT_ID;

public class DetailEventView extends AppCompatActivity implements DetailEventContract.View {

    private static final String TAG = "DetailEventView";
    private static final String KEY = "DetailEventView";

    private ProgressBar pbLoading;
    private ConstraintLayout clContent;
    private TextView toolbarDetailEventTitle,
            tvDetailEventTitle,
            tvDetailEventCalendar,
            tvDetailEventOrganizer,
            tvDetailEventAddresses,
            tvDetailEventPhone,
            tvDetailEventMail,
            tvDetailEventDescription,
            tvPlusUsersCount;
    private RecyclerView rvDetailEventImages,
            rvDetailEventUsers;

    private DetailEventPresenter detailEventPresenter;

    private Event event = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidThreeTen.init(this);

        setContentView(R.layout.activity_detail_event);
        initToolbar();

        initViews();

        detailEventPresenter = new DetailEventPresenter(this, this);

        if (savedInstanceState != null) {
            event = savedInstanceState.getParcelable(KEY);
            setDataToViews(event);
        } else {
            Intent intent = getIntent();
            int id = intent.getIntExtra(EVENT_ID, -1);
            detailEventPresenter.requestEventData(id);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY, event);
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar_detail_event);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(view ->  {
            onBackPressed();
        });
    }
    private void initViews(){
        pbLoading = findViewById(R.id.pb_loading_detail);

        clContent = findViewById(R.id.constraintLayout);

        toolbarDetailEventTitle = findViewById(R.id.toolbar_detail_event_title);
        tvDetailEventTitle = findViewById(R.id.tv_detail_event_title);
        tvDetailEventCalendar = findViewById(R.id.tv_detail_event_calendar);
        tvDetailEventOrganizer = findViewById(R.id.tv_detail_event_organizer);
        tvDetailEventAddresses = findViewById(R.id.tv_detail_event_address);
        tvDetailEventPhone = findViewById(R.id.tv_detail_event_phone);
        tvDetailEventMail = findViewById(R.id.tv_detail_event_mail);
        tvDetailEventDescription = findViewById(R.id.tv_detail_event_description);

        rvDetailEventImages = findViewById(R.id.rv_detail_event_images);
        rvDetailEventImages.setHasFixedSize(true);
        rvDetailEventImages.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        rvDetailEventUsers = findViewById(R.id.rv_detail_event_users);
        rvDetailEventUsers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        tvPlusUsersCount = findViewById(R.id.tv_plus_users_count);
    }

    @Override
    public void setDataToViews(Event event) {
        if (event != null){
            if (this.event == null) this.event = event;

            toolbarDetailEventTitle.setText(event.getName());
            tvDetailEventTitle.setText(event.getName());
            tvDetailEventCalendar.setText(dateFormat(event.getStartDate(), event.getEndDate()));
            tvDetailEventOrganizer.setText(event.getOrganizer());
            tvDetailEventAddresses.setText(event.getAddress());

            List<String> phoneNumbersList = event.getPhoneNumbers();
            if (phoneNumbersList.size() > 0) {
                StringBuilder phoneNumbers = new StringBuilder();
                for (int i = 0; i < phoneNumbersList.size() - 1; i++) {
                    phoneNumbers.append(phoneNumbersList.get(i)).append("\n");
                }
                phoneNumbers.append(phoneNumbersList.get(phoneNumbersList.size() - 1));
                tvDetailEventPhone.setText(phoneNumbers.toString());
            }

            tvDetailEventDescription.setText(event.getDescription());

            List<String> imagesRes = new LinkedList<>(event.getImagesUri());

            ImagesEventAdapter imagesEventAdapter = new ImagesEventAdapter(imagesRes);
            rvDetailEventImages.setAdapter(imagesEventAdapter);

            List<User> eventUsers = new ArrayList<>();
            eventUsers.add(new User(100001, R.drawable.avatar_3, "Дмитрий Валерьевич"));
            eventUsers.add(new User(100002, R.drawable.avatar_2, "Евгений Александров"));
            eventUsers.add(new User(100003, R.drawable.avatar_1, "Виктор Кузнецов"));
            eventUsers.add(new User(100004, R.drawable.photo_2, "Дмитрий Валерьевич"));
            eventUsers.add(new User(100005, R.drawable.photo_4, "Евгений Александров"));
            eventUsers.add(new User(100006, R.drawable.avatar_1, "Виктор Кузнецов"));

            UsersEventAdapter usersEventAdapter;

            if (eventUsers.size() > 5){
                List<User> users5 = new LinkedList<>();
                for (int i = 0; i < 5; i++) users5.add(eventUsers.get(i));

                usersEventAdapter = new UsersEventAdapter(users5);
                int otherUsersCount = eventUsers.size() - 5;
                tvPlusUsersCount.setText("+ " + otherUsersCount);
            } else {
                usersEventAdapter = new UsersEventAdapter(eventUsers);
                tvPlusUsersCount.setText("");
            }

            rvDetailEventUsers.setAdapter(usersEventAdapter);
        }
    }

    @Override
    public void showEmptyView() {
        clContent.setVisibility(View.GONE);
    }

    @Override
    public void hideEmptyView() {
        clContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, R.string.communication_error, Toast.LENGTH_LONG).show();
    }

    private String dateFormat(long dateStart, long dateEnd){
        String date = "";

        if (dateEnd > -1){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM");

            LocalDate startLocalDate = Instant.ofEpochMilli(dateStart).atZone(ZoneId.systemDefault()).toLocalDate();
            String startDate = formatter.format(startLocalDate);

            LocalDate endLocalDate = Instant.ofEpochMilli(dateEnd).atZone(ZoneId.systemDefault()).toLocalDate();
            String endDate = formatter.format(endLocalDate);

            LocalDate localDate = LocalDate.now();
            long diffDays = ChronoUnit.DAYS.between(localDate, startLocalDate);
            if (diffDays > 0) date = "Осталось " + diffDays + " дней ";

            date += "(" + startDate + " - " + endDate + ")";
        } else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            LocalDate localDate = Instant.ofEpochMilli(dateStart).atZone(ZoneId.systemDefault()).toLocalDate();
            date = formatter.format(localDate);
        }

        return date;
    }
}