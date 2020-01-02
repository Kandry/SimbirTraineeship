package com.kozyrev.simbirtraineeship.detail_event_activity.fragment_shirt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.ImagesEventAdapter;
import com.kozyrev.simbirtraineeship.adapter.UsersEventAdapter;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.model.User;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShirtFragment extends Fragment {

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

    private int id;

    private List<Integer> imagesResId;
    private List<User> eventUsers;

    public ShirtFragment(int id){
        this.id = id;
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_detail, container, false);
        initToolbar();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initData(){
        Event event = getEvent(id);
        if (event != null){
            toolbarDetailEventTitle.setText(event.getName());
            tvDetailEventTitle.setText(event.getName());

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

            tvDetailEventCalendar.setText(date);

            tvDetailEventOrganizer.setText(event.getOrganizer());
            tvDetailEventAddresses.setText(event.getAddress());

            List<String> phoneNumbersList = event.getPhoneNumbers();
            if (phoneNumbersList.size() > 0) {
                StringBuilder phoneNumbers = new StringBuilder();
                for (int i = 0; i < phoneNumbersList.size() - 1; i++) {
                    phoneNumbers.append(phoneNumbersList.get(i) + "\n");
                }
                phoneNumbers.append(phoneNumbersList.get(phoneNumbersList.size() - 1));
                tvDetailEventPhone.setText(phoneNumbers.toString());
            }

            //tvDetailEventMail.setText(event.getEmail());
            tvDetailEventDescription.setText(event.getDescription());

            imagesResId = new LinkedList<>();
            imagesResId.add(R.drawable.cardimage_1);
            imagesResId.add(R.drawable.cardimage_2);
            imagesResId.add(R.drawable.cardimage_3);

            ImagesEventAdapter imagesEventAdapter = new ImagesEventAdapter(imagesResId);
            rvDetailEventImages.setAdapter(imagesEventAdapter);

            eventUsers = new ArrayList<>();
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

    private void initToolbar(){
        toolbarDetailEventTitle = getActivity().findViewById(R.id.toolbar_detail_event_title);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar_detail_event);

        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(view ->  {
            getActivity().onBackPressed();
        });
    }

    private void initViews(View view){
        tvDetailEventTitle = view.findViewById(R.id.tv_detail_event_title);
        tvDetailEventCalendar = view.findViewById(R.id.tv_detail_event_calendar);
        tvDetailEventOrganizer = view.findViewById(R.id.tv_detail_event_organizer);
        tvDetailEventAddresses = view.findViewById(R.id.tv_detail_event_address);
        tvDetailEventPhone = view.findViewById(R.id.tv_detail_event_phone);
        tvDetailEventMail = view.findViewById(R.id.tv_detail_event_mail);
        tvDetailEventDescription = view.findViewById(R.id.tv_detail_event_description);

        rvDetailEventImages = view.findViewById(R.id.rv_detail_event_images);
        rvDetailEventImages.setHasFixedSize(true);
        rvDetailEventImages.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        rvDetailEventUsers = view.findViewById(R.id.rv_detail_event_users);
        rvDetailEventUsers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        tvPlusUsersCount = view.findViewById(R.id.tv_plus_users_count);
    }

    private Event getEvent(int id){
        List<Event> events = JSONHelper.getEvents(getContext(), getContext().getString(R.string.events_filename));
        Event finalEvent = null;
        for(Event event: events){
            if (event.getId() == id) finalEvent = event;
        }
        return finalEvent;
    }
}
