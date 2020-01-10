package com.kozyrev.simbirtraineeship.news_fragment;

import android.content.Intent;
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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.NewsAdapter;
import com.kozyrev.simbirtraineeship.detail_event_activity.DetailEventView;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.util.ArrayList;
import java.util.List;

import static com.kozyrev.simbirtraineeship.utils.Constants.EVENT_ID;

public class NewsFragment extends Fragment implements NewsItemClickListener{

    private List<Event> news;
    private List<Event> allNews;
    private NewsAdapter newsAdapter;

    public NewsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initNews();
        super.onCreate(savedInstanceState);
    }

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

    @Override
    public void onNewsItemClick(int position) {
        Intent intent = new Intent(getContext(), DetailEventView.class);
        intent.putExtra(EVENT_ID, news.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onStop() {
        JSONHelper.clearCategories(getContext());
        super.onStop();
    }

    private void initNews(){
        allNews = JSONHelper.getEvents(getContext(), getString(R.string.events_filename), JSONHelper.BackThreadType.ASYNCTASK);
        news = new ArrayList<>();
        for (Event event : allNews){
            news.add(event);
        }
        newsAdapter = new NewsAdapter(news, getContext(), this);
    }

    private void updateNews(){
        List<Category> categories = JSONHelper.getCategories(getContext(), getString(R.string.categories_filename), JSONHelper.BackThreadType.ASYNCTASK);
        for (Event event: allNews) {
            List<Integer> categoriesID = event.getCategoriesID();
            boolean inNews = false;

            for (Category category: categories) {
                if ((categoriesID.contains(category.getId())) && (category.isActive())) inNews = true;
            }

            if (!inNews && news.contains(event)){
                news.remove(event);
            }

            if (inNews && !news.contains(event)){
                news.add(event);
            }
        }
    }

    private void initViews(View view){
        RecyclerView rvNews = view.findViewById(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvNews.setHasFixedSize(false);

        rvNews.setAdapter(newsAdapter);

        updateNews();

        NewsDiffUtilCallback newsDiffUtilCallback = new NewsDiffUtilCallback(newsAdapter.getNews(), news);
        DiffUtil.DiffResult newsDiffResult = DiffUtil.calculateDiff(newsDiffUtilCallback);

        newsAdapter.setNews(news);
        newsDiffResult.dispatchUpdatesTo(newsAdapter);
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.nav_news);
        setHasOptionsMenu(true);
        SimpleSearchView searchView = getActivity().findViewById(R.id.toolbar_search);
        searchView.setVisibility(View.GONE);
    }
}
