package com.kozyrev.simbirtraineeship.news_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.ArrayList;
import java.util.List;

import static com.kozyrev.simbirtraineeship.utils.Constants.EVENT_ID;

public class NewsFragmentView extends Fragment implements com.kozyrev.simbirtraineeship.news_fragment.View, NewsItemClickListener{

    private static final String KEY = "NewsFragmentView";

    private List<Event> news;
    private NewsAdapter newsAdapter;

    private ProgressBar pbLoading;
    private RecyclerView rvNews;

    private NewsFragmentPresenter newsFragmentPresenter;

    public NewsFragmentView(){}

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

        newsFragmentPresenter = new NewsFragmentPresenter(this);

        if (savedInstanceState != null) {
            news = savedInstanceState.getParcelableArrayList(KEY);
            setDataToRecyclerView(news);
        } else {
            newsFragmentPresenter.requestDataFromFile();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY, (ArrayList<Event>) news);
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

    private void initViews(View view){
        pbLoading = view.findViewById(R.id.pb_loading_news);

        rvNews = view.findViewById(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvNews.setHasFixedSize(false);

        news = new ArrayList<>();
        newsAdapter = new NewsAdapter(news, this);

        rvNews.setAdapter(newsAdapter);
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.nav_news);
        setHasOptionsMenu(true);
        SimpleSearchView searchView = getActivity().findViewById(R.id.toolbar_search);
        searchView.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Event> events) {
        if (events != null) {
            this.news = events;

            NewsDiffUtilCallback newsDiffUtilCallback = new NewsDiffUtilCallback(newsAdapter.getNews(), news);
            DiffUtil.DiffResult newsDiffResult = DiffUtil.calculateDiff(newsDiffUtilCallback);

            newsAdapter.setNews(news);
            newsDiffResult.dispatchUpdatesTo(newsAdapter);
        }
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
    public void showEmptyView() {
        rvNews.setVisibility(View.GONE);
    }

    @Override
    public void hideEmptyView() {
        rvNews.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        newsFragmentPresenter.clearCategories();
        super.onStop();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }
}
