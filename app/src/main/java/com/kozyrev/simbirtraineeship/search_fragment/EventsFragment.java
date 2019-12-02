package com.kozyrev.simbirtraineeship.search_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.EventsAdapter;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EventsFragment extends Fragment {

    private List<Event> randomEvents = new ArrayList<>();
    private List<Event> events;

    private EventsAdapter eventsAdapter;

    private TextView tvEventsResult;

    public EventsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View view){
        randomEvents.add(new Event("Благотворительный концерт им. Васи Пупкина"));
        randomEvents.add(new Event("Слет волонтёрских организаций Самарской области"));
        randomEvents.add(new Event("Акция Красная летночка"));
        randomEvents.add(new Event("Квест: В поисках Немо"));
        randomEvents.add(new Event("Викторина: как хорошо ты знаешь свой город"));
        randomEvents.add(new Event("Слёт волонтёров серебрянного возраста"));
        randomEvents.add(new Event("Соц. исследование: Ты доволен своей жизнью?"));

        int eventsCount = (int) (Math.random() * 7);
        events = new LinkedList<>();
        for (int i = 0; i < eventsCount; i++) events.add(randomEvents.get(i));

        tvEventsResult = view.findViewById(R.id.tv_events_result);
        tvEventsResult.setText("Ключевые слова: мастер-класс, помощь\nРезультаты поиска: " + events.size() + " результатов");

        RecyclerView recyclerView = view.findViewById(R.id.rv_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);
        DividerItemDecoration horizontalDecorator = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        horizontalDecorator.setDrawable(getResources().getDrawable(R.drawable.horizontal_divider));
        recyclerView.addItemDecoration(horizontalDecorator);

        eventsAdapter = new EventsAdapter(events);
        recyclerView.setAdapter(eventsAdapter);
    }
}
