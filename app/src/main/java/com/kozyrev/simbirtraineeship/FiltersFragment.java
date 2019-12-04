package com.kozyrev.simbirtraineeship;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.adapter.FiltersAdapter;
import com.kozyrev.simbirtraineeship.model.Filter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FiltersFragment extends Fragment {

    private List<Filter> filters;

    private FiltersAdapter filtersAdapter;

    public FiltersFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);
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
        inflater.inflate(R.menu.top_menu_filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Фильтры");
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(view ->  {
                toolbar.setNavigationIcon(null);
                getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    private void initViews(View view){
        filters = new ArrayList<>();
        filters.add(new Filter("Деньги"));
        filters.add(new Filter("Вещи"));
        filters.add(new Filter("Проф. помощь"));
        filters.add(new Filter("Волонтерство"));

        RecyclerView rvFilters = view.findViewById(R.id.rv_filters);
        rvFilters.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvFilters.setHasFixedSize(false);
        DividerItemDecoration horizontalDecorator = new DividerItemDecoration(rvFilters.getContext(), DividerItemDecoration.VERTICAL);
        horizontalDecorator.setDrawable(getResources().getDrawable(R.drawable.horizontal_divider));
        rvFilters.addItemDecoration(horizontalDecorator);

        filtersAdapter = new FiltersAdapter(filters);
        rvFilters.setAdapter(filtersAdapter);
    }
}
