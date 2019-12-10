package com.kozyrev.simbirtraineeship.news_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.NewsAdapter;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.util.List;

public class NewsFragment extends Fragment {

    private List<Event> news;

    public NewsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        initToolbar();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_news, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_news_category:
                Navigation.findNavController(getView()).navigate(R.id.action_navigation_news_to_filtersFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initViews(View view){
        news = JSONHelper.getEvents(getContext(), "events.json");

        RecyclerView rvNews = view.findViewById(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvNews.setHasFixedSize(false);

        NewsAdapter helpsAdapter = new NewsAdapter(news, getContext());
        rvNews.setAdapter(helpsAdapter);
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.nav_news);
        setHasOptionsMenu(true);
        SimpleSearchView searchView = getActivity().findViewById(R.id.toolbar_search);
        searchView.setVisibility(View.GONE);
    }
}
