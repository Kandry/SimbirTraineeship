package com.kozyrev.simbirtraineeship.filters_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.CategoriesAdapter;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.ArrayList;
import java.util.List;

public class FiltersFragmentView extends Fragment implements FiltersFragmentContract.View {

    private static final String TAG = "FiltersFragmentView";

    private List<Category> categories;

    private Toolbar toolbar;
    private CategoriesAdapter categoriesAdapter;

    private FiltersFragmentPresenter filtersFragmentPresenter;

    public FiltersFragmentView(){}

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

        filtersFragmentPresenter = new FiltersFragmentPresenter(this, getContext());
        filtersFragmentPresenter.requestDataFromFile();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.filters_toolbar_title);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(view ->  {
                toolbar.setNavigationIcon(null);
                getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    private void initViews(View view){
        categories = new ArrayList<>();

        RecyclerView rvFilters = view.findViewById(R.id.rv_filters);
        rvFilters.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvFilters.setHasFixedSize(false);
        DividerItemDecoration horizontalDecorator = new DividerItemDecoration(rvFilters.getContext(), DividerItemDecoration.VERTICAL);
        horizontalDecorator.setDrawable(getResources().getDrawable(R.drawable.horizontal_divider));
        rvFilters.addItemDecoration(horizontalDecorator);

        categoriesAdapter = new CategoriesAdapter(categories);
        rvFilters.setAdapter(categoriesAdapter);
    }

    @Override
    public void setDataToRecyclerView(List<Category> categories) {
        if (categories != null) categoriesAdapter.dataSetChanged(categories);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(getActivity(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }
}
