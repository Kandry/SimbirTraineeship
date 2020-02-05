package com.kozyrev.simbirtraineeship.search_fragment.search_events_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.EventsAdapter;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchEventsFragmentView extends Fragment implements com.kozyrev.simbirtraineeship.search_fragment.search_events_fragment.View {

    private static final String KEY_QUERY = "Query";

    private SearchEventsFragmentPresenter searchEventsFragmentPresenter;

    private SimpleSearchView searchView;
    private ConstraintLayout clEventsSearchEmpty;
    private ConstraintLayout clEventsSearchContent;
    private TextView tvEventsResult;
    private RecyclerView recyclerView;

    private List<Event> searchEvents = new ArrayList<>();
    private String searchQuery = "";

    public SearchEventsFragmentView(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        searchEventsFragmentPresenter = new SearchEventsFragmentPresenter(this);
        searchEventsFragmentPresenter.requestDataFromFile();

        if (savedInstanceState != null) {
            searchQuery = savedInstanceState.getString(KEY_QUERY);
            searchView.setQuery(searchQuery, false);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_QUERY, searchQuery);
    }

    private void initViews(View view){
        clEventsSearchEmpty = view.findViewById(R.id.cl_events_search_empty);
        clEventsSearchContent = view.findViewById(R.id.cl_events_search_content);

        searchView = getActivity().findViewById(R.id.toolbar_search);

        tvEventsResult = view.findViewById(R.id.tv_events_result);
        recyclerView = view.findViewById(R.id.rv_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);
        DividerItemDecoration horizontalDecorator = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        horizontalDecorator.setDrawable(getResources().getDrawable(R.drawable.horizontal_divider));
        recyclerView.addItemDecoration(horizontalDecorator);
    }

    @SuppressLint("CheckResult")
    @Override
    public void setDataToRecyclerView(List<Event> events) {
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> searchView.setOnQueryTextListener(new SimpleSearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        emitter.onNext(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        emitter.onNext(newText);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextCleared() {
                        showEmptyView();
                        return false;
                    }
                }))
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    if (!searchQuery.equals(query)) {
                        searchQuery = query;
                    }
                    if (searchQuery.length() > 0) {
                        searchEvents.clear();
                        searchEvents.addAll(searchEventsFragmentPresenter.filterEvens(events, searchQuery));
                        setDataToViews(searchQuery, searchEvents);
                    } else showEmptyView();
                });
    }

    private void setDataToViews(String query, List<Event> events){
        if (events.size() > 0) {
            EventsAdapter eventsAdapter = new EventsAdapter(events);
            recyclerView.setAdapter(eventsAdapter);
            tvEventsResult.setText("Ключевые слова: " + query + " \nРезультаты поиска: " + events.size() + " результатов");
            hideEmptyView();
        } else showEmptyView();
    }

    @Override
    public void showEmptyView() {
        clEventsSearchContent.setVisibility(View.GONE);
        clEventsSearchEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        clEventsSearchEmpty.setVisibility(View.GONE);
        clEventsSearchContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }
}
