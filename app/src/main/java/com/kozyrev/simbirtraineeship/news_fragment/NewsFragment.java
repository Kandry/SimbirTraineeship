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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.NewsAdapter;
import com.kozyrev.simbirtraineeship.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
        news = new LinkedList<>();

        Event event1 = new Event("Спонсоры отремонтируют школу-интернат");
        event1.setDescription("Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области школой-интернатом, в которой реализовывается областная программа ремонта");

        Event event2 = new Event("Конкурс по вокальному пению в детском доме №6");
        event2.setDescription("Димитровградский детский дом №6 устраивает областной конкурс по вокальному пению");

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        try {
            event1.setStartDate(format.parse("21-Sep-2020").getTime());
            event1.setEndDate(format.parse("20-Oct-2020").getTime());
            event2.setStartDate(format.parse("20-Oct-2020").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        news.add(event1);
        news.add(event2);

        RecyclerView rvNews = view.findViewById(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvNews.setHasFixedSize(false);

        NewsAdapter helpsAdapter = new NewsAdapter(news, getContext());
        rvNews.setAdapter(helpsAdapter);
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Новости");
        setHasOptionsMenu(true);
    }


}
