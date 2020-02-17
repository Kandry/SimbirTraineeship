package com.kozyrev.simbirtraineeship.news_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
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
import com.kozyrev.simbirtraineeship.utils.SparseBooleanArrayParcelable;

import java.util.ArrayList;
import java.util.List;

import static com.kozyrev.simbirtraineeship.utils.Constants.EVENT_ID;

public class NewsFragmentView extends Fragment implements com.kozyrev.simbirtraineeship.news_fragment.View, NewsItemClickListener{

    private static final String KEY = "NewsFragmentView_News";
    public static final String KEY_SBA = "CustomBooleanArray";

    private List<Event> news = null;
    private NewsAdapter newsAdapter;
    private SparseBooleanArray categories = null;

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

        if (getArguments() != null) {
            categories = getArguments().getParcelable(KEY_SBA);

            if (categories != null){
                newsFragmentPresenter.setCategories(categories);
                newsFragmentPresenter.requestDataFromFile();
            }
        }

        if (savedInstanceState != null) {
            categories = savedInstanceState.getParcelable(KEY_SBA);
            news = savedInstanceState.getParcelableArrayList(KEY);
            setDataToRecyclerView(newsFragmentPresenter.filterNews(news, categories));
        } else {
            if (categories == null) newsFragmentPresenter.requestDataFromFile();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY, (ArrayList<Event>) news);
        outState.putParcelable(KEY_SBA, new SparseBooleanArrayParcelable(categories));
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
                Bundle bundle = new Bundle();
                bundle.putParcelable(KEY_SBA, new SparseBooleanArrayParcelable(categories));
                Navigation.findNavController(getView()).navigate(R.id.action_navigation_news_to_filtersFragment, bundle);
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
        if (categories == null) categories = newsFragmentPresenter.getCategories();
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
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }
}
