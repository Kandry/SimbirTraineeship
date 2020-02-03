package com.kozyrev.simbirtraineeship.filters_fragment;

import android.os.Bundle;
import android.util.Log;
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
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.CategoriesAdapter;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.ArrayList;
import java.util.List;

public class FiltersFragmentView extends Fragment implements com.kozyrev.simbirtraineeship.filters_fragment.View {

    private static final String TAG = "FiltersFragmentView";
    private static final String KEY = "FiltersFragmentView";

    private List<Category> categories;

    private Toolbar toolbar;
    private CategoriesAdapter categoriesAdapter;

    private ProgressBar pbLoading;
    private ConstraintLayout clFiltersContent;

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

        filtersFragmentPresenter = new FiltersFragmentPresenter(this);

        if (savedInstanceState != null) {
            categories = savedInstanceState.getParcelableArrayList(KEY);
            setDataToRecyclerView(categories);
        } else {
            filtersFragmentPresenter.requestDataFromFile();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY, (ArrayList<Category>) categories);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_filters_ok:
                toolbar.setNavigationIcon(null);
                filtersFragmentPresenter.setDataToFile(categoriesAdapter.getCategories());
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        pbLoading = view.findViewById(R.id.pb_loading_filters);
        clFiltersContent = view.findViewById(R.id.cl_filters_content);

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
        if (categories != null) {
            this.categories = categories;
            categoriesAdapter.dataSetChanged(this.categories);
        }
    }

    @Override
    public void showEmptyView() {
        clFiltersContent.setVisibility(View.GONE);
    }

    @Override
    public void hideEmptyView() {
        clFiltersContent.setVisibility(View.VISIBLE);
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
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(getActivity(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        filtersFragmentPresenter.setDataToFile(categoriesAdapter.getCategories());
        super.onStop();
    }
}
