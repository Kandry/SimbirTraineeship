package com.kozyrev.simbirtraineeship.search_fragment;

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
import androidx.viewpager.widget.ViewPager;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.google.android.material.tabs.TabLayout;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.SearchFragmentPagerAdapter;

public class SearchFragment extends Fragment {

    private SimpleSearchView searchView;

    public SearchFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initToolbar();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager vpSearch = view.findViewById(R.id.vp_search);
        SearchFragmentPagerAdapter fragmentPagerAdapter = new SearchFragmentPagerAdapter(this.getChildFragmentManager(), 0); //getActivity().getSupportFragmentManager()
        vpSearch.setAdapter(fragmentPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tl_search);
        tabLayout.setupWithViewPager(vpSearch);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItem = menu.findItem(R.id.top_nav_search);
        searchView.setMenuItem(menuItem);
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        searchView = getActivity().findViewById(R.id.toolbar_search);

        toolbarTitle.setText(R.string.nav_search);
        searchView.setVisibility(View.VISIBLE);
        setHasOptionsMenu(true);
    }
}
